package exia.toolight;

import exia.toolight.tools.Pince;
import exia.toolight.tools.Tournevis;

/**
 * Created by Ewen Auffret on 31/05/2017.
 */

public class ToolFactory {

    public Tool getTool(String tooltype) throws Exception {
        switch (tooltype)
        {
            case "Pince":
            {
                return new Pince();
            }
            case "Tournevis":
            {
                return new Tournevis();
            }
            default:
            {
                throw new Exception("Cet outil n'existe pas dans notre base.");
            }
        }
    }
}
