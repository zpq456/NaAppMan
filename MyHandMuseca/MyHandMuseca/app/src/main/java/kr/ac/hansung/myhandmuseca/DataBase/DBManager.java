package kr.ac.hansung.myhandmuseca.DataBase;

import android.util.Log;

import java.util.ArrayList;

import kr.ac.hansung.myhandmuseca.Data.MusicData;
import kr.ac.hansung.myhandmuseca.JSON.SelectAllMusicJSON;

/**
 * Created by Owner on 2017-07-19.
 */

public class DBManager {
    private static final String IP = "113.198.84.67";
    private static SendToDB sendToDB;
    private static UpdateToDB updateToDB;

    public ArrayList<MusicData> SelectAllMusicDB(){
        SelectAllMusicJSON json = new SelectAllMusicJSON();
        sendToDB = new SendToDB("SelectAllMusicList.php",IP," ");
        sendToDB.start();
        try{
            sendToDB.join();//DB연결이 완료될때까지 대기
        }catch (InterruptedException e){
            Log.e("SelectAllMusicDB", e.toString());
        }
        json.onPostExecute(sendToDB.getResult());
        return json.getMusicDatas();
    }

    public void InsertMusicDB(MusicData mData){
        String message = "'" + mData.getData(1) +
                "','" + mData.getData(2) +
                "','" + mData.getData(3) +
                "','" + mData.getData(4) + "'";

                sendToDB = new SendToDB("InsertMusic.php",IP,message);
        sendToDB.start();
        try{
            sendToDB.join();//DB연결이 완료될때까지 대기
        }catch (InterruptedException e){
            Log.e("SelectAllMusicDB", e.toString());
        }
    }
}
