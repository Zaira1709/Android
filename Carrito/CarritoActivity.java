package com.example.zaira.carritocompras;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {
    ListView lista;
    double total=0;
    TextView totalText;
    ImageView eliminar;
    ArrayList<Productos> productosRecibido;
    CarritoActivity.RopaAdaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        productosRecibido = (ArrayList<Productos>) getIntent().getSerializableExtra("carrito");
       // String descripcionRecibida = productosRecibido.getDescripcion();
        lista =(ListView) findViewById(R.id.lista);
        adaptador =  new CarritoActivity.RopaAdaptador();
        lista.setAdapter(adaptador);
        totalText = (TextView) findViewById(R.id.numeroCompras);

    }

    class  RopaAdaptador extends ArrayAdapter<Productos> {
        RopaAdaptador(){
            super(CarritoActivity.this, R.layout.content_carrito,productosRecibido);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //inflado de vista
            View row = getLayoutInflater().inflate(R.layout.content_add,parent, false);

            ImageView imagenRopa =(ImageView) row.findViewById(R.id.imagen);
            TextView nombreRopa = (TextView)row.findViewById(R.id.nombre);
            TextView precioRopa =(TextView) row.findViewById(R.id.precio);
            ImageView add = (ImageView)row.findViewById(R.id.add);

            final Productos ropaActual =productosRecibido.get(position);
            ImageView addbutton = (ImageView) row.findViewById(R.id.add);
            total = total + ropaActual.getPrecio();

            totalText.setText("Total:"+String.valueOf(total));
            imagenRopa.setImageResource(ropaActual.getImagen());
            nombreRopa.setText(ropaActual.getNombre());
            precioRopa.setText(String.valueOf(ropaActual.getPrecio()));

            eliminar = (ImageView) row.findViewById(R.id.eliminar);
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productosRecibido.remove(position);
                    total=0;
                    totalText.setText("Total:"+String.valueOf(total));
                    adaptador.notifyDataSetChanged();

                }
            });
            return  row;
        }
    }

    @Override
    public void onBackPressed() {

        Intent data = new Intent();
        data.putExtra("objetoNuevo",productosRecibido );
        setResult(43, data);
        this.finish();
    }
}
