package exia.toolight;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Ewen Auffret on 05/06/2017.
 */

class MissionWSTask extends AsyncTask<String, Void, MissionList> {

    private static URL WSURL;
    private static ToolFactory toolFactory = new ToolFactory();

    @Override
    protected MissionList doInBackground(String... params) {
        setWSURL();
        MissionList missionList = missionListener();
        return missionList;
    }


    private void setWSURL()
    {
        try {
            WSURL = new URL("https://toolight-ws.herokuapp.com/missionList");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private JSONArray findJSONMissionList() throws IOException, JSONException {
        InputStreamReader wsStreamReader;
        BufferedReader missionsBuffer;
        String missionListString = "", currentRead;
        JSONArray missionListJSON;

        wsStreamReader = new InputStreamReader(WSURL.openStream());
        missionsBuffer = new BufferedReader(wsStreamReader);

        while ((currentRead = missionsBuffer.readLine()) != null)
            missionListString += currentRead + "\n";

        missionListJSON = new JSONArray(missionListString);

        return missionListJSON;
    }

    private MissionList parseMissionListFromJSONArray(JSONArray missionListJSON) throws Exception {

        MissionList missionList = new MissionList();

        for (int i = 0; i < missionListJSON.length(); i++)
        {
            JSONObject missionJSON = missionListJSON.getJSONObject(i);
            // JSONArray tools = missionJSON.getJSONArray("Tools");

            ToolList toolList = new ToolList();

            //for (int j = 0; j < tools.length(); j++) {
            // TODO FIX WS TO HAVE CAPITAL LETTER ON TOOL NAME    toolList.addToolToList(toolFactory.getTool( tools.getString(i) ));
            // }

            Mission mission = new Mission(missionJSON.getString("Name"), missionJSON.getString("Description"), toolList);
            missionList.addToMissionList(mission);
        }

        return missionList;
    }

    private MissionList missionListener() {

        JSONArray missionListJSON;
        MissionList missionList = null;

        try {
            missionListJSON = findJSONMissionList();
            missionList = parseMissionListFromJSONArray(missionListJSON);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return missionList;
    }

}
