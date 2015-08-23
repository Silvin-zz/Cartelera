package com.itesm.avril.cartelera;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by silviobravocado on 22/08/15.
 */
public class LibroListAdapter extends BaseAdapter {

    Context context;
    List<Libro> rowItems;

    public LibroListAdapter(Context context, List<Libro> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater lay = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            convertView = lay.inflate(R.layout.list_libro
                    , parent, false);
        }

        TextView nombre = (TextView)convertView.findViewById(R.id.textView2);

        Libro  r = rowItems.get(position);
        nombre.setText(r.getNombre());

        return convertView;
    }

}
