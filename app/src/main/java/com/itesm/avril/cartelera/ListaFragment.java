package com.itesm.avril.cartelera;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Avril on 20/08/2015.
 */
public class ListaFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CargaAsyncTask ct = new CargaAsyncTask(getActivity(), this);
        ct.execute("https://www.googleapis.com/fusiontables/v1/query?sql=SELECT*FROM%201KZCQqRiU5VyRkcp8z2XV2tDv_0hTgx1nJlc1M1Pk&key=AIzaSyALk5qQAiXa8o6kH5bACKM9Ms7bqIRdk_s");
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub

        super.onListItemClick(l, v, position, id);
        Cartelera r = (Cartelera) getListAdapter().getItem(position);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(),0);
        alertDialog.setTitle("Cartelera");
        alertDialog.setMessage("Pelicula: " + r.getPelicula() + "\n" +
                "Duracion: " + r.getDuracion() + "\n" +
                "Genero: " + r.getGenero());
        //Setting Button "Continuar"
        alertDialog.setPositiveButton("Continuar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
}
