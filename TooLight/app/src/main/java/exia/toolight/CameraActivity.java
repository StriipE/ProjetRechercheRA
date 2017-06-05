package exia.toolight;

/**
 * Created by Cyril on 05/06/2017.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import mpi.cbg.fly.Feature;
import mpi.cbg.fly.SIFT;

public class CameraActivity extends AppCompatActivity{


    private static final int PICTURE_RESULT = 9;

    private static final int OK = 0;
    private static final int MEMORY_ERROR = 1;
    private static final int ERROR = 2;
    private Bitmap bitmapBaseImg;
    private Bitmap mPicture;
    private ImageView mView;
    private ImageView BaseImageView;



    private ProgressDialog mProgressDialog;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.siftcamera);
        BaseImageView = (ImageView)findViewById(R.id.Base);
        mView = (ImageView) findViewById(R.id.view);
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CameraActivity.this.startActivityForResult(camera, PICTURE_RESULT);

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }**/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                CameraActivity.this.startActivityForResult(camera, PICTURE_RESULT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void onDestroy() {
        super.onDestroy();
        if (mPicture != null) {
            mPicture.recycle();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if results comes from the camera activity
        if (requestCode == PICTURE_RESULT) {

            // if a picture was taken
            if (resultCode == Activity.RESULT_OK) {

                // Free the data of the last picture
                if(mPicture != null)
                    mPicture.recycle();

                // Get the picture taken by the user
                mPicture = (Bitmap) data.getExtras().get("data");
                bitmapBaseImg = BitmapFactory.decodeResource(getResources(),R.mipmap.a);
                // Avoid IllegalStateException with Immutable bitmap
                Bitmap pic = mPicture.copy(mPicture.getConfig(), true);
                mPicture.recycle();
                mPicture = pic;
                // Show the picture

                mView.setImageBitmap(mPicture);

                // process SIFT algorithm on the picture
                processSIFT();

                // if user canceled from the camera activity
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    private void processSIFT() {
        // show the dialog
        mProgressDialog = ProgressDialog.show(this, "Please wait",
                "Processing of SIFT Algorithm...");

        new Thread(new Runnable() {

            @Override
            public void run() {
                Message msg = null;

                try {
                    // convert bitmap to pixels table
                    int pixels[] = toPixelsTab(mPicture);
                    int pixelsImgBase[] = toPixelsTab(bitmapBaseImg);
                    // get the features detected into a vector
                    Vector<Feature> features = SIFT.getFeatures(
                            mPicture.getWidth(), mPicture.getHeight(), pixels);

                    Vector<Feature> featuresBaseImg = SIFT.getFeatures(bitmapBaseImg.getWidth(),bitmapBaseImg.getHeight(),pixelsImgBase);

                    // draw features on bitmap
                    Canvas d = new Canvas(bitmapBaseImg);
                    Canvas c = new Canvas(mPicture);
                    List<Feature> imgCamera = new ArrayList<Feature>();
                    List<Feature> imgBase = new ArrayList<Feature>();
                    for (Feature f : features) {
                        drawFeature(c, f.location[0], f.location[1], f.scale,f.orientation);
                        imgCamera.add(f);
                    }
                    for (Feature g : featuresBaseImg){
                        drawFeature(c, g.location[0], g.location[1], g.scale,g.orientation);
                        imgBase.add(g);
                    }



                    msg = mHandler.obtainMessage(OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg = mHandler.obtainMessage(ERROR);
                } catch (OutOfMemoryError e) {
                    msg = mHandler.obtainMessage(MEMORY_ERROR);
                } finally {
                    // send the message
                    mHandler.sendMessage(msg);
                }
            }

        }).start();
    }

    private int[] toPixelsTab(Bitmap picture) {
        int width = picture.getWidth();
        int height = picture.getHeight();

        int[] pixels = new int[width * height];
        // copy pixels of picture into the tab
        picture.getPixels(pixels, 0, picture.getWidth(), 0, 0, width, height);

        // On Android, Color are coded in 4 bytes (argb),
        // whereas SIFT needs color coded in 3 bytes (rgb)

        for (int i = 0; i < (width * height); i++)
            pixels[i] &= 0x00ffffff;

        return pixels;
    }

    public void drawFeature(Canvas c, float x, float y, double scale,
                            double orientation) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // line too small...
        scale *= 6.;

        double sin = Math.sin(orientation);
        double cos = Math.cos(orientation);

        paint.setStrokeWidth(2f);
        paint.setColor(Color.GREEN);
        c.drawLine((float) x, (float) y, (float) (x - (sin - cos) * scale),
                (float) (y + (sin + cos) * scale), paint);

        paint.setStrokeWidth(4f);
        paint.setColor(Color.YELLOW);
        c.drawPoint(x, y, paint);
    }

    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            AlertDialog.Builder builder;

            switch (msg.what) {
                case OK:
                    // set the picture with features drawed
                    mView.setImageBitmap(mPicture);
                    break;
                case MEMORY_ERROR:
                    builder = new AlertDialog.Builder(CameraActivity.this);
                    builder.setMessage("Out of memory.\nPicture too big.");
                    builder.setPositiveButton("Ok", null);
                    builder.show();
                    break;
                case ERROR:
                    builder = new AlertDialog.Builder(CameraActivity.this);
                    builder.setMessage("Error during the process.");
                    builder.setPositiveButton("Ok", null);
                    builder.show();
                    break;
            }
            mProgressDialog.dismiss();
        }
    };
}
