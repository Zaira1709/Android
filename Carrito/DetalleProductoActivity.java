package com.example.zaira.carritocompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetalleProductoActivity extends AppCompatActivity {
    TextView desc;
    ArrayList<Productos> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        productos = new ArrayList<>();

        final Productos ropaRecibido = (Productos) getIntent().getSerializableExtra("ropa");
        productos = (ArrayList<Productos>) getIntent().getSerializableExtra("carrito");
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
        Button añadir = (Button) findViewById(R.id.add);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.add(ropaRecibido);
                Toast.makeText(DetalleProductoActivity.this, "Se agrego al Carrito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {

        Intent data = new Intent();
        data.putExtra("objetoNuevo",productos );
        setResult(43, data);
        this.finish();
    }

}
