package exia.toolight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class ToolList implements Serializable {

    private List<Tool> toolList = new ArrayList<>();

    public void addToolToList(Tool tool)
    {
        this.toolList.add(tool);
    }

    public void removeToolToList(Tool tool)
    {
        if ( this.toolList.contains(tool) ){
            this.toolList.remove(tool);
        }
    }

    public ArrayList<String> toStringList()
    {
        ArrayList<String> convertedToolListToString = new ArrayList<>();

        for (Tool tool : this.toolList)
        {
            convertedToolListToString.add( tool.toString() );
        }

        return convertedToolListToString;
    }

}
