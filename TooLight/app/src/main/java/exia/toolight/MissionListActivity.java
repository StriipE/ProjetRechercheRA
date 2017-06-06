package exia.toolight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Ewen Auffret on 03/06/2017.
 */

public class MissionListActivity extends AppCompatActivity {

    private ListView missionListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionlist);

        missionListView = (ListView) findViewById(R.id.missionListView);
        MissionList missionList = null;
        try {
            missionList = new MissionWSTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<String> testDisplay = new ArrayList<>();

        for (Mission mission : missionList.getMissionList())
        {
            testDisplay.add(mission.getName());
        }

        ArrayAdapter<String> missionListViewArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, testDisplay
        );

        missionListView.setAdapter(missionListViewArrayAdapter);

    }
}
