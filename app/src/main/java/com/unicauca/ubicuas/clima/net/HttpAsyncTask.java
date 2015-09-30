package com.unicauca.ubicuas.clima.net;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Dario Chamorro on 25/09/2015.
 */
public class HttpAsyncTask extends AsyncTask<String,Integer, String> {

    public interface HttpInterface{
        void setResponse(String Response);
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
        String response = "";
        try {
            if (method == GET)
                response = conn.requestByGet(params[0]);
            else {
                conn.requestByPostForm(params[0], params[1]);
                response = "OK";
            }
        }catch(IOException e){
            response="FAIL";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        httpInterface.setResponse(s);
    }
}
