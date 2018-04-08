package com.example.zaira.futbol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ListaPartidos> partidos;
    ListView listaV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        partidos = new ArrayList<>();
        partidos.add(new ListaPartidos("México", R.drawable.img1, "Chile", R.drawable.img2));
        partidos.add(new ListaPartidos("Argentina", R.drawable.img3, "Brasil", R.drawable.img4));
        partidos.add(new ListaPartidos("Colombia", R.drawable.img5, "Venezuela", R.drawable.img6));
        partidos.add(new ListaPartidos("Bolivia", R.drawable.img7, "España", R.drawable.img8));
        partidos.add(new ListaPartidos("Inglaterra", R.drawable.img9, "México", R.drawable.img1));
        partidos.add(new ListaPartidos("Francia", R.drawable.img10, "Japon", R.drawable.img11));

        listaV = (ListView) findViewById(R.id.lista);
        listaV.setAdapter(new LugarAdaptador());

        ListView listaClick = (ListView) findViewById(R.id.lista);
        listaClick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this, PartidoAleatorioActivity.class);
                intento.putExtra("partidos", partidos.get(position));
                startActivityForResult(intento,12);
            }

        });
    }

    class LugarAdaptador extends ArrayAdapter<ListaPartidos> {
        LugarAdaptador() {

            super(MainActivity.this, R.layout.content_lista, partidos);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //inflado de vista

            View row = getLayoutInflater().inflate(R.layout.content_lista, parent, false);
            ImageView imagen1 = (ImageView) row.findViewById(R.id.equipo1);
            TextView nombre1 = (TextView) row.findViewById(R.id.nombre_equipo1);
            ImageView imagen2 = (ImageView) row.findViewById(R.id.equipo2);
            TextView nombre2 = (TextView) row.findViewById(R.id.nombre_equipo2);
            final ListaPartidos partido = partidos.get(position);
            imagen1.setImageResource(partido.getImg1());
            nombre1.setText(partido.getEquipo1());
            imagen2.setImageResource(partido.getImg2());
            nombre2.setText(partido.getEquipo2());


            return row;
        }
    }

}
