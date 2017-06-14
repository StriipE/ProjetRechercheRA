package exia.toolight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import java.util.List;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class ToolListActivity extends AppCompatActivity {

    private ListView toolListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toollist);

        Intent toolListIntent = getIntent();
        Button siftCameraButton = (Button) findViewById(R.id.siftCameraButton);
        Button orbCameraButton = (Button) findViewById(R.id.orbCameraButton);
        List<String> toolStringList = toolListIntent.getStringArrayListExtra(MainActivity.TOOL_EXTRA_MESSAGE);

        siftCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateSiftCamera();
            }
        });

        orbCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateOrbCamera();
            }
        });

        toolListView = (ListView) findViewById(R.id.ToolList);

        ArrayAdapter<String> toolListViewArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, toolStringList
        );


        toolListView.setAdapter(toolListViewArrayAdapter);

    }


    public void activateOrbCamera(){
        Intent orbCamera = new Intent(this, CameraORBActivity.class);
        startActivity(orbCamera);
    }

    public void activateSiftCamera(){
        Intent siftCamera = new Intent(this, CameraSIFTActivity.class);
        startActivity(siftCamera);
    }

}
