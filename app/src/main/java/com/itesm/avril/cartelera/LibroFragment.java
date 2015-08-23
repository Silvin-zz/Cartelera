package com.itesm.avril.cartelera;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by silviobravocado on 22/08/15.
 */
public class LibroFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }





    /**
     * Cuando se carga la actividad vamos a consumir el rest de los libros
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CargaAsyncLibros ct = new CargaAsyncLibros(getActivity(), this);
        ct.execute("https://www.googleapis.com/fusiontables/v1/query?sql=SELECT*FROM%201KhLGNXKI-p0j5F_sDwiBPel53dp58A860teaDrIN&key=AIzaSyALk5qQAiXa8o6kH5bACKM9Ms7bqIRdk_s");
    }




    /**
     * Cuando dan click sobre un elemento de la linea.
     * @param l
     * @param v
     * @param position
     * @param id
     */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Libro r = (Libro) getListAdapter().getItem(position);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(),0);
        alertDialog.setTitle("Libro");
        alertDialog.setMessage("Nombre: "   + r.getNombre() + "\n" +
                "Autor: "                   + r.getAutor() + "\n" +
                "Editorial: "               + r.getEditorial() + "\n" +
                "Precio: "                  + r.getPrecio());
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
