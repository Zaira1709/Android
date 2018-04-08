package com.example.zaira.carritocompras;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Productos> ropa, carrito;
    ListView lista;
    TextView numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carrito = new ArrayList<>();



        ropa = new ArrayList<>();
        ropa.add(new Productos("FALDA DENIM", "Falda mini con cinco bolsillos y bajo con acabado deshilachado. Efecto lavado y bandas laterales. Cierre frontal con cremallera y botón metálico.",R.drawable.i1, 599.00,R.drawable.i12));
        ropa.add(new Productos("VESTIDO ESTAMPADO", "Vestido con escote redondo con pico y manga corta. Detalle de frunce en cintura y abertura en la parte baja delantera..",R.drawable.img2, 899.00,R.drawable.img22));
        ropa.add(new Productos("LEVITA BICOLOR", "Levita con cuello redondo y manga larga. Cierre delantero con cremallera y bolsillos tipo plastrón. ",R.drawable.img3, 1299.00,R.drawable.img32));
        ropa.add(new Productos("FALDA PANTALÓN", "Falda pantalón efecto cruzado en delantero con detalle de botón metálico. Cierre en espalda con cremallera oculta en costura.",R.drawable.img4, 699.00,R.drawable.img42));
        lista =(ListView) findViewById(R.id.lista);
        lista.setAdapter(new RopaAdaptador());


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this, DetalleProductoActivity.class);
                intento.putExtra("ropa", ropa.get(position));
                intento.putExtra("carrito", carrito);
                startActivityForResult(intento,12);
            }

        });
        numero =(TextView) findViewById(R.id.numeroCompras);
        FloatingActionButton botonFlotante = (FloatingActionButton) findViewById(R.id.fab);
        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, CarritoActivity.class);
                intento.putExtra("carrito", carrito);
                startActivityForResult(intento,13);
            }
        });

    }

    class  RopaAdaptador extends ArrayAdapter<Productos> {
        RopaAdaptador(){
            super(MainActivity.this, R.layout.content_carrito,ropa);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //inflado de vista
            View row = getLayoutInflater().inflate(R.layout.content_carrito,parent, false);

            ImageView imagenRopa =(ImageView) row.findViewById(R.id.imagen_ropa);
            TextView nombreRopa = (TextView)row.findViewById(R.id.nombre_ropa);
            TextView precioRopa =(TextView) row.findViewById(R.id.precio_ropa);
            ImageView add = (ImageView)row.findViewById(R.id.add);

            final Productos ropaActual =ropa.get(position);
            ImageView addbutton = (ImageView) row.findViewById(R.id.add);

            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carrito.add(new Productos(ropaActual.getNombre(), ropaActual.getDescripcion(),ropaActual.getImagen(), ropaActual.getPrecio(),ropaActual.getImagen2()));

                    numero.setText("Agregados: "+String.valueOf(carrito.size()));
                }

            });

            imagenRopa.setImageResource(ropaActual.getImagen());
            nombreRopa.setText(ropaActual.getNombre());
            precioRopa.setText(String.valueOf(ropaActual.getPrecio()));
            return  row;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode ==13){
            if(resultCode == 43){
                carrito  = (ArrayList<Productos>)  data.getSerializableExtra("objetoNuevo");
                numero.setText("Agregados: "+String.valueOf(carrito.size()));
            }
        }else  if (requestCode ==12){
            if(resultCode == 43){
                carrito  = (ArrayList<Productos>)  data.getSerializableExtra("objetoNuevo");
                numero.setText("Agregados: "+String.valueOf(carrito.size()));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
