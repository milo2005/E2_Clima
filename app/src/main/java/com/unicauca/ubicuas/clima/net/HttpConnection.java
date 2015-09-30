package com.unicauca.ubicuas.clima.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dario Chamorro on 25/09/2015.
 */
public class HttpConnection {

    /**
     * Metodo para Ejecutar el metodo GET
      * @param url
     * @return
     */

    public String requestByGet(String url) throws IOException {

        URL urlC =  new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlC.openConnection();
        con.setReadTimeout(10000);
        con.setConnectTimeout(15000);
        con.setRequestMethod("GET");
        con.setDoInput(true);

        con.connect();

        InputStream iS= con.getInputStream();

        return streamToSting(iS);

    }

    /**
     * Metodo para Ejecutar el Metodo POST
     * @param url
     * @param json
     * @return
     */

    public String requestByPostJson(String url, String json) throws IOException {
        URL urlC = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlC.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.addRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();

        OutputStream stream = conn.getOutputStream();
        stream.write(json.getBytes());

        InputStream iS = conn.getInputStream();
        return streamToSting(iS);

    }

    /**
     * MEtodo para ejecutar una solicitud POST
     * @param url
     * @param data Los datos deben ir en el formato etiqueta1=valor1&etiqueta2=valor2.....
     * @throws IOException
     */
    public String requestByPostForm(String url, String data) throws IOException {
        URL urlC = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlC.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();

        OutputStream stream = conn.getOutputStream();
        stream.write(data.getBytes());

        InputStream sI = conn.getInputStream();
        return streamToSting(sI);

    }

    private String streamToSting(InputStream iS) throws IOException {
        InputStreamReader reader = new InputStreamReader(iS);
        int ch=0;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        while((ch = reader.read()) != -1){
            out.write(ch);
        }

        String rta = new String(out.toByteArray());
        out.close();

        return rta;
    }



}
