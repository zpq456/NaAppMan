package kr.ac.zipokun.handymuseca.DataBase;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Owner on 2017-07-19.
 */

public class SendToDB extends Thread{
    private static final String TAG = "SendToDB";
    private String PHP, message, IP;
    private String result;
    private static OkHttpClient client;

    public SendToDB(String PHP, String IP, String message){
        result = null;
        this.PHP = PHP;
        this.IP = IP;
        this.message = message;
        Log.d(TAG,"php = " + PHP + " ip = " + IP + " message = " + this.message);
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void run() {
        client = new OkHttpClient();

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http");
        builder.host(IP);
        builder.port(80);
        builder.addPathSegments("Music");
        builder.addPathSegments(PHP);

        RequestBody body = new FormBody.Builder()
                .add("message",message)
                .build();

        //request
        Request request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                throw new IOException(response.message() + " " + response.toString());
            }
            setResult(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}