package kr.ac.zipokun.handymuseca.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.ac.zipokun.handymuseca.Data.MusicData;


/**
 * Created by Owner on 2017-07-22.
 */

public class SelectAllMusicJSON implements JsonMaster {
    private ArrayList<MusicData> musicDatas = new ArrayList<MusicData>();
    public ArrayList<MusicData> getMusicDatas() {return musicDatas;}

    @Override
    public void onPostExecute(String str) {
        MusicData music;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.musicDatas = null;
                return;
            }
            JSONArray ja = root.getJSONArray("result");
            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                music = new MusicData(jo.getString("musicID"),
                        jo.getString("name"),
                        jo.getString("released"),
                        jo.getString("produce"),
                        jo.getString("howtoget"));
                this.musicDatas.add(music);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
