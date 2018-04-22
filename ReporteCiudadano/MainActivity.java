package com.example.zaira.reporteciudadano;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<ResponseReportes>, RecyclerViewOnItemClickListener {
    RecyclerView reportesRV;
    ReportesAdapter adapter;
    List<Reporte> comentarios;
    public static final int AGREGAR = Menu.FIRST;
    public static final int VER_MAPA = Menu.FIRST + 1;
    double latitud, longitud;
    Boolean bandera =false;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         dialog = ProgressDialog.show(this, "Cargando",
                "Espere un momento....",false, false);
        comentarios = new ArrayList<>();
        reportesRV = findViewById(R.id.comentariosRV);
        reportesRV.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager glm = new GridLayoutManager(this, 2);

        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        reportesRV.setLayoutManager(llm);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, locationListener);
    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("reporte", comentarios.get(position));
        startActivity(intent);
    }

    @Override
    protected void onResume() {

        super.onResume();

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 0);
        }

        if(latitud!=0.0  && longitud != 0.0) {
            consultarService();
        }
    }


    private void consultarService(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointInterface interfaz = retrofit.create(EndPointInterface.class);
            Call<ResponseReportes> responseReporteCall = interfaz.verReportes(longitud, latitud);

            responseReporteCall.enqueue(this);

        }catch (Exception e){
            System.out.print("error");
        }


    }


    @Override
    public void onResponse(Call<ResponseReportes> call, Response<ResponseReportes> response) {
        Log.d("respuesta", response.body().toString());
        ResponseReportes respuesta= response.body();
        comentarios = respuesta.getReportes();
        adapter = new ReportesAdapter(MainActivity.this, comentarios, this);
        reportesRV.setAdapter(adapter);

        //  reportesRV.setAdapter(new ReportesAdapter(comentarios, this));
        if(!bandera) {
            dialog.dismiss();
            bandera= true;
        }
    }

    @Override
    public void onFailure(Call<ResponseReportes> call, Throwable t) {
        Toast.makeText(this, "Fallo el servicio D:", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, AGREGAR, Menu.NONE, "Agregar Reporte");
        menu.add(Menu.NONE, VER_MAPA, Menu.NONE, "Ver Mapa");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case AGREGAR:
                agregar();
                break;
            case VER_MAPA:
                ver();
                break;

        }

        return true;
    }

    public void agregar(){
        Intent intent = new Intent(MainActivity.this, agregarActivity.class);
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        startActivity(intent);
    }
    public void ver(){
        Intent intent = new Intent(MainActivity.this, todosMapsActivity.class);
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        intent.putExtra("comentarios", (Serializable) comentarios);
        startActivity(intent);
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(!bandera){
                latitud = location.getLatitude();
                longitud = location.getLongitude();
                consultarService();
            }

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }


    };

}
