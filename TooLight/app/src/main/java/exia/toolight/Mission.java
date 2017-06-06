package exia.toolight;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class Mission {

    private String name;
    private String description;
    private ToolList toolList;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ToolList getToolList() {
        return toolList;
    }
    public void setToolList(ToolList toolList) {
        this.toolList = toolList;
    }

    public Mission()
    {
        this.name = "";
        this.description = "";
        this.toolList = new ToolList();
    }
    public Mission(String name, String description, ToolList toolList)
    {
        this.name = name;
        this.description = description;
        this.toolList = toolList;
    }

}
