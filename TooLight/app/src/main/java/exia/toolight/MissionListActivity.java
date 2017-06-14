package exia.toolight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Ewen Auffret on 03/06/2017.
 */

public class MissionListActivity extends AppCompatActivity {

    public static final String TOOL_EXTRA_MESSAGE = "exia.toolight.TOOLLIST";
    private ListView missionListView;
    private MissionList missionList = null;
    private ArrayAdapter<String> missionListViewArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missionlist);
        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                        updateMissionList();
                        missionListView.setAdapter(missionListViewArrayAdapter);
                    }
                }, 3000);
            }
        });
        missionListView = (ListView) findViewById(R.id.missionListView);
        updateMissionList();

        missionListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToolList toolList = missionList.getMissionList().get( position ).getToolList();

                Intent toolListIntent = new Intent(MissionListActivity.this, ToolListActivity.class);
                toolListIntent.putStringArrayListExtra(TOOL_EXTRA_MESSAGE, toolList.toStringList());
                startActivity(toolListIntent);
            }
        });

        missionListView.setAdapter(missionListViewArrayAdapter);
    }

    public void updateMissionList(){
        try {
            missionList = new MissionWSTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<String> missionDisplay = new ArrayList<>();

        for (Mission mission : missionList.getMissionList())
        {
            missionDisplay.add(mission.getName());
        }

        missionListViewArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, missionDisplay
        );
    }



}
