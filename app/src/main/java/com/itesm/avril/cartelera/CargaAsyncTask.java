package com.itesm.avril.cartelera;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Avril on 20/08/2015.
 */
public class CargaAsyncTask extends AsyncTask<String, String,
        ArrayList<Cartelera>> {

    private Context contexto;
    private ListFragment lista;
    private ProgressDialog dialog;

    public CargaAsyncTask( Context c , ListFragment f){
        contexto = c;
        lista = f;
        dialog = new ProgressDialog(contexto);
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        dialog.setTitle("  Cargando.... ");
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected void onProgressUpdate(String... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }

    private String dameJSON(String jsonURL){
        HttpURLConnection connection = null;
        StringBuilder builder = new StringBuilder();
        try
        {
            URL url = new URL(jsonURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();

            int status = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
        }catch (Exception ex)
        {
            Log.e("Error", "Archivo no encontrado. e: " + ex);
        }
        finally
        {
            connection.disconnect();
        }
        return builder.toString();
    }

    @Override
    protected ArrayList<Cartelera> doInBackground(String... params) {
        ArrayList<Cartelera>  objetos = new ArrayList<Cartelera>();
        String json = dameJSON( params[0] );

        try {
            JSONObject objeto = new JSONObject(json);
            JSONArray array = objeto.getJSONArray("rows");
            for (int i = 0; i < array.length(); i++) {
                JSONArray ob = array.getJSONArray(i);
                String cine = ob.getString(0);
                String pelicula = ob.getString(1);
                String duracion = ob.getString(2);
                String genero = ob.getString(3);
                objetos.add(new Cartelera(cine, pelicula, duracion, genero));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objetos;
    }

    @Override
    protected void onPostExecute(ArrayList<Cartelera> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        CarteleraListAdapter adp = new CarteleraListAdapter(contexto, result);
        lista.setListAdapter(null);
        lista.setListAdapter(adp);

        dialog.dismiss();
    }
}
