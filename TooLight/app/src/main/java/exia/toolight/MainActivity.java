package exia.toolight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public static final String TOOL_EXTRA_MESSAGE = "exia.toolight.TOOLLIST";

    private ToolFactory toolFactory = new ToolFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button consultToolListButton = (Button) findViewById(R.id.toolListButton);
        Button siftCameraButton = (Button)findViewById(R.id.siftCameraButton);
        Button consultMissionListButton = (Button) findViewById(R.id.missionListButton);
        Button orbCameraButton = (Button)findViewById(R.id.orbCameraButton);

        consultToolListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultToolList();
            }
        });
        siftCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateSiftCamera();
            }
        });
        consultMissionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultMissionList();
            }
        });
        orbCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateOrbCamera();
            }
        });
    }

    public void activateOrbCamera(){
        Intent orbCamera = new Intent(this, CameraORBActivity.class);
        startActivity(orbCamera);
    }

    public void activateSiftCamera(){
        Intent siftCamera = new Intent(this, CameraSIFTActivity.class);
        startActivity(siftCamera);

    }

    public void consultToolList() {

        Intent toolListIntent = new Intent(this, ToolListActivity.class);

        // Hardcoded tool list for testing purpose
        ToolList toolList = new ToolList();

        try
        {
            toolList.addToolToList( this.toolFactory.getTool("Pince") );
            toolList.addToolToList( this.toolFactory.getTool("Tournevis") );
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

        toolListIntent.putStringArrayListExtra(TOOL_EXTRA_MESSAGE, toolList.toStringList());
        startActivity(toolListIntent);
    }

    public void consultMissionList() {
        Intent missionListIntent = new Intent(this, MissionListActivity.class);
        startActivity(missionListIntent);
    }

}
