package exia.toolight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ewen Auffret on 03/06/2017.
 */

public class MissionList {

    private List<Mission> missionList = new ArrayList<>();

    public void addToMissionList(Mission mission)
    {
        this.missionList.add(mission);
    }

    public List<Mission> getMissionList()
    {
        return this.missionList;
    }

}
