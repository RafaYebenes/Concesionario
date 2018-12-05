package com.example.zafiro2.concesionario.Objetos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zafiro2.concesionario.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class AdaptadorListaExtras extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Extras> items;

    public AdaptadorListaExtras(Activity activity, ArrayList<Extras> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Extras> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inf.inflate(R.layout.adaptador_lista_extras, null);


        }

        Extras dir = items.get(position);


        TextView txvNombreExtra = v.findViewById(R.id.txvNombreExtra);
        txvNombreExtra.setText(dir.getNombre());

        TextView txvPrecioExtra = v.findViewById(R.id.txvPrecioExtra);
        txvPrecioExtra.setText(Float.toString(dir.getPrecio()));

        TextView txvDescripcionExtra = v.findViewById(R.id.txvDescripcionExtra);
        txvDescripcionExtra.setText(dir.getDescripcion());



        return v;
    }
}
