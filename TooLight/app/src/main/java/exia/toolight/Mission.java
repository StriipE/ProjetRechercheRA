package exia.toolight;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class Mission {
    private String name;
    private String description;
    private List<Tool> toolList;

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
    public List<Tool> getToolList() {
        return toolList;
    }
    public void setToolList(List<Tool> toolList) {
        this.toolList = toolList;
    }

    public Mission()
    {
        this.name = "";
        this.description = "";
        this.toolList = new ArrayList<Tool>();
    }
    public Mission(String name, String description, List<Tool> toolList)
    {
        this.name = name;
        this.description = description;
        this.toolList = toolList;
    }

}
