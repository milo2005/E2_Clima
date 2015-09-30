package com.unicauca.ubicuas.clima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.unicauca.ubicuas.clima.net.HttpAsyncTask;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.HttpInterface {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpAsyncTask httpClima = new HttpAsyncTask(HttpAsyncTask.GET, this);
        httpClima.execute("url");
    }

    @Override
    public void setResponse(String Response) {

    }
}
