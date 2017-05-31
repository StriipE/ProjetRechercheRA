package exia.toolight.tools;

import exia.toolight.Tool;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class Pince implements Tool {

    private static String toolName = "";

    public Pince()
    {
        this.setToolName();
    }

    @Override
    public void setToolName() {
        this.toolName = "Pince";
    }

    @Override
    public String getToolName() {
        return this.toolName;
    }


}
