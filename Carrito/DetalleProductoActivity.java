package com.example.zaira.carritocompras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleProductoActivity extends AppCompatActivity {
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);


        final Productos ropaRecibido = (Productos) getIntent().getSerializableExtra("ropa");
        desc = (TextView) findViewById(R.id.descripcion);
        desc.setText(ropaRecibido.getDescripcion());

        ImageView imagenRopa =(ImageView) findViewById(R.id.imagen);
        imagenRopa.setImageResource(ropaRecibido.getImagen());

        ImageView imagenRopa2 =(ImageView) findViewById(R.id.imagen2);
        imagenRopa2.setImageResource(ropaRecibido.getImagen2());

        TextView precio = (TextView) findViewById(R.id.detalle_precio);
        precio.setText("$"+String.valueOf(ropaRecibido.getPrecio()));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(ropaRecibido.getNombre());
        setSupportActionBar(toolbar);
    }

}
