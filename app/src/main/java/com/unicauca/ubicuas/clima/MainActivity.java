package com.unicauca.ubicuas.clima;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.unicauca.ubicuas.clima.net.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.HttpInterface {


    TextView tTemp, tHumedad, tPresion, tDes;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Cargando ...");

        tTemp = (TextView) findViewById(R.id.temp);
        tHumedad = (TextView) findViewById(R.id.humedad);
        tPresion = (TextView) findViewById(R.id.presion);
        tDes = (TextView) findViewById(R.id.descripcion);

        dialog.show();
        HttpAsyncTask httpClima = new HttpAsyncTask(HttpAsyncTask.GET, this);
        httpClima.execute(getString(R.string.url));
    }

    @Override
    public void setResponse(String response) {
        dialog.hide();
        try {
            JSONObject obj =  new JSONObject(response);
            JSONObject query = obj.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            JSONObject atmosphere = channel.getJSONObject("atmosphere");

            String humedad = atmosphere.getString("humidity");
            String presion = atmosphere.getString("pressure");

            JSONObject item = channel.getJSONObject("item");
            JSONObject condition = item.getJSONObject("condition");

            String temp = condition.getString("temp");
            String description = condition.getString("text");

            tTemp.setText(temp);
            tHumedad.setText(humedad);
            tPresion.setText(presion);
            tDes.setText(description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
