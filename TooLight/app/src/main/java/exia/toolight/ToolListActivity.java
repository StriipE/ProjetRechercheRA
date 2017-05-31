package exia.toolight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        List<String> toolStringList = toolListIntent.getStringArrayListExtra(MainActivity.TOOL_EXTRA_MESSAGE);

        toolListView = (ListView) findViewById(R.id.ToolList);

        ArrayAdapter<String> toolListViewArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, toolStringList
        );

        toolListView.setAdapter(toolListViewArrayAdapter);
    }

}
