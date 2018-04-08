package com.example.zaira.futbol;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PartidoAleatorioActivity extends AppCompatActivity {
    ListaPartidos  partido;
    TextView tiempoT = null;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    Random rand = new Random();
    ArrayList<String> goles1 = new ArrayList<>();
    ArrayList<String> goles2 = new ArrayList<>();
    ArrayList<String> totalgoles = new ArrayList<>();
    ArrayAdapter<String> adaptador1, adaptador2, adaptador;
    ListView lista1,lista2,goles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_aleatorio);

        partido = (ListaPartidos) getIntent().getSerializableExtra("partidos");
        ImageView img1 = (ImageView) findViewById(R.id.imagen1);
        TextView equ1 =(TextView) findViewById(R.id.equipo1);
        ImageView img2 = (ImageView) findViewById(R.id.imagen2);
        TextView equ2 =(TextView) findViewById(R.id.equipo2);
        img1.setImageResource(partido.getImg1());
        equ1.setText(partido.getEquipo1());
        img2.setImageResource(partido.getImg2());
        equ2.setText(partido.getEquipo2());
        tiempoT = (TextView) findViewById(R.id.tiempo);
        final Button iniciar = (Button) findViewById(R.id.iniciar);

        adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,goles1);
        lista1 = (ListView) findViewById(R.id.gol1);
        lista1.setAdapter(adaptador1);

        adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,goles2);
        lista2 = (ListView) findViewById(R.id.gol2);
        lista2.setAdapter(adaptador2);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,totalgoles);
        goles= (ListView) findViewById(R.id.goles);
        goles.setAdapter(adaptador);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar.setVisibility(View.INVISIBLE);
                tiempoT.setVisibility(View.VISIBLE);
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });
    }

    private Runnable updateTimerThread = new Runnable() {
        int currentSec = 0;
        int n;
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 100);


            if (secs<=50){

                tiempoT.setText("Tiempo: " + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
                customHandler.postDelayed(this, 0);
                n = rand.nextInt(1000);

                if (n==6 && secs!=0){
                    goles1.add("Gol seg"+String.format("%02d", secs));
                    adaptador1.notifyDataSetChanged();
                    totalgoles.add(partido.getEquipo1()+" gol seg: "+String.format("%02d", secs));
                }else if(n==7  && secs!=0){
                    goles2.add("Gol seg"+String.format("%02d", secs));
                    adaptador2.notifyDataSetChanged();
                    totalgoles.add(partido.getEquipo2()+" gol seg: "+String.format("%02d", secs));
                }


            }else {
                customHandler.removeCallbacks(updateTimerThread);
                lista1.setVisibility(View.GONE);
                lista2.setVisibility(View.GONE);
                goles.setVisibility(View.VISIBLE);
                adaptador2.notifyDataSetChanged();


            }

        }
    };
}
