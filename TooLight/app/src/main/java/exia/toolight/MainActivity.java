package exia.toolight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    public static final String TOOL_EXTRA_MESSAGE = "exia.toolight.TOOLLIST";
    private ToolFactory toolFactory = new ToolFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button consultToolListButton = (Button) findViewById(R.id.toolListButton);

        consultToolListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultToolList();
            }
        });
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
}
