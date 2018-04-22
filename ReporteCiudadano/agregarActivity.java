package com.example.zaira.reporteciudadano;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class agregarActivity extends AppCompatActivity {
    EditText hashtag, comentario;
    Button agregar;
    double latitud, longitud;
    String distancia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        latitud = getIntent().getExtras().getDouble("latitud");
        longitud = getIntent().getExtras().getDouble("longitud");

        hashtag = findViewById(R.id.hashtagNuevo);
        comentario = findViewById(R.id.comentarioNuevo);
        agregar = findViewById(R.id.agregar);
        distancia ="1";
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarReporte();

            }
        });


    }
    private void insertarReporte(){
        final ProgressDialog dialog = ProgressDialog.show(this, "Subiendo comentario",
                "Espere un momento....",false, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.URL)
                .build();

        EndPointInterface interfaz = retrofit.create(EndPointInterface.class);

        Call<Void> responseCall = interfaz.insertarReporte(
                hashtag.getText().toString(),
                comentario.getText().toString(),
                latitud,
                longitud
               );

        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                Toast.makeText(agregarActivity.this, "Comentario agregado exitosamente",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(agregarActivity.this, "Error al subir comentario",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
