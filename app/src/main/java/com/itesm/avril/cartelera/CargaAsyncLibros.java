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
 * Created by silviobravocado on 22/08/15.
 */
public class CargaAsyncLibros extends AsyncTask<String,String, ArrayList<Libro>> {


    private Context         context;
    private ListFragment    lista;
    private ProgressDialog  dialog;


    public CargaAsyncLibros(Context context, ListFragment lista) {
        this.context = context;
        this.lista = lista;
        dialog = new ProgressDialog(context);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setTitle("  Cargando Libros.... ");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }



    private String getJSONRest(String jsonURL){


        Log.d("rest", "======================================================================");
        Log.d("rest", jsonURL);

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
    protected ArrayList<Libro> doInBackground(String... params) {
        ArrayList<Libro>  objetos = new ArrayList<Libro>();
        String json = this.getJSONRest( params[0] );


        Log.d("rest", "================================================================");
        Log.d("rest", json);

        try {
            JSONObject objeto = new JSONObject(json);
            JSONArray array = objeto.getJSONArray("rows");
            for (int i = 0; i < array.length(); i++) {
                JSONArray ob        = array.getJSONArray(i);
                String nombre       = ob.getString(0);
                String autor        = ob.getString(1);
                String editorial    = ob.getString(2);
                String precio       = ob.getString(3);
                objetos.add(new Libro(nombre, autor, editorial, precio));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objetos;
    }


    @Override
    protected void onPostExecute(ArrayList<Libro> libros) {
        super.onPostExecute(libros);

        Log.d("rest", "================================================================");
        Log.d("rest", "Entramos en el postexecute");
        LibroListAdapter adp = new LibroListAdapter(context, libros);
        lista.setListAdapter(null);
        lista.setListAdapter(adp);

        dialog.dismiss();
    }





}
