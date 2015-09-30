package com.unicauca.ubicuas.clima.net;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Dario Chamorro on 25/09/2015.
 */
public class HttpAsyncTask extends AsyncTask<String,Integer, String> {

    public interface HttpInterface{
        void setResponse(String response);
    }

    public static int POST=0;
    public static int GET=1;

    int method;
    HttpConnection conn;
    HttpInterface httpInterface;

    public HttpAsyncTask(int method, HttpInterface httpInterface){
        conn = new HttpConnection();
        this.method = method;
        this.httpInterface = httpInterface;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i("Clima", params[0]);
        String response = "";
        try {
            if (method == GET)
                response = conn.requestByGet(params[0]);
            else {

                response = conn.requestByPostForm(params[0], params[1]);;
            }
        }catch(IOException e){
            response=null;
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        httpInterface.setResponse(s);
    }
}
