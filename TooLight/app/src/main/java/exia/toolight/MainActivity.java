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


        Button consultMissionListButton = (Button) findViewById(R.id.missionListButton);

        consultMissionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultMissionList();
            }
        });

    }

    public void consultMissionList() {
        Intent missionListIntent = new Intent(this, MissionListActivity.class);
        startActivity(missionListIntent);
    }

}
