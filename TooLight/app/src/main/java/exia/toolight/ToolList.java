package exia.toolight;

import java.util.List;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class ToolList {

    public List<Tool> toolList;

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

}
