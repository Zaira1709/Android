package com.example.zaira.reporteciudadano;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.List;

public class todosMapsActivity extends FragmentActivity implements OnMapReadyCallback , Serializable {

    private GoogleMap mMap;
    double latitud, longitud;
    List<Reporte> comentarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitud = getIntent().getExtras().getDouble("latitud");
        longitud = getIntent().getExtras().getDouble("longitud");
        comentarios = (List<Reporte>) getIntent().getSerializableExtra("comentarios");



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Tu ubicación"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));
        for (Reporte comentario:comentarios){

           /* LatLng ubicacion = new LatLng(comentario.getLatitud(), comentario.getLongitud());
            BitmapDrawable bitmapDrawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.marcador);
            Bitmap b1 = bitmapDrawable1.getBitmap();
            Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, 150, 150, false);
            Marker marcador1 = mMap.addMarker(new MarkerOptions().position(ubicacion));
            marcador1.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
            marcador1.setTag(comentario.getHashtag());*/

            LatLng ubicacion = new LatLng(comentario.getLatitud(), comentario.getLongitud());
            mMap.addMarker(new MarkerOptions().position(ubicacion).title(comentario.getHashtag()));


        }
    }

}
