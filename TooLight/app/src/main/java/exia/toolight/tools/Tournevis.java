package exia.toolight.tools;

import exia.toolight.Tool;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class Tournevis implements Tool {

    private static String toolName = "";

    public Tournevis()
    {
        this.setToolName();
    }

    @Override
    public void setToolName() {
        this.toolName = "tournevis";
    }

    @Override
    public String getToolName() {
        return this.toolName;
    }

    @Override
    public String toString()
    {
        return this.getToolName();
    }

}
