package com.example.zafiro2.concesionario.Objetos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.R;

import java.io.InputStream;
import java.util.ArrayList;

public class AdaptadorListaCoches extends BaseAdapter{

        protected Activity activity;
        protected ArrayList<Coches> items;

        public AdaptadorListaCoches(Activity activity, ArrayList<Coches> items){
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

        public void addAll(ArrayList<Coches> category) {
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
<<<<<<< HEAD
                v = inf.inflate(R.layout.adaptador_lista_coches, null);
=======
                v = inf.inflate(R.layout.activity_lista_coches, null);//Cambiar el xml por el del adaptador
>>>>>>> 3915a641f66e0994f2ed3f2d54911805ade76118
            }

            Coches dir = items.get(position);

            //enlazar cada elemento de tu layout a cada atributo de la clase
            TextView txvMarca = v.findViewById(R.id.txvMarcalvl1);



            txvMarca.setText(dir.getMarca());


            TextView txvModelo = v.findViewById(R.id.txvModelo);
            txvModelo.setText(dir.getModelo());


            String precio = Float.toString(dir.getPrecio());
            TextView txvPrecio = v.findViewById(R.id.txvPrecio);
            txvPrecio.setText(precio);

            String rutaImg = "R.drawable.";
            rutaImg = rutaImg + dir.getImagen();



            ImageView imvImagen = v.findViewById(R.id.imvImagen);
            //imvImagen.setImageDrawable(ruta);; //Metodo de prueba, no se si servirá para introducir la ruta de la imagen


            return v;
        }
}
