package com.example.zaira.busqueda;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class agregarActivity extends AppCompatActivity {
    EditText nombre, correo;
    Button agreagar;
    SQLiteDatabase db;
    public static final String NOMBRE = "nombre";
    public static final String CORREO = "correo";
    public static final String TABLA = "trabajador";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        nombre = findViewById(R.id.nombreN);
        correo = findViewById(R.id.correoN);
        agreagar = findViewById(R.id.agregar);
        db = new DBHelper(this).getWritableDatabase();
        agreagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar();
            }
        });

    }
    public void agregar(){
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE, nombre.getText().toString());
        cv.put(CORREO, correo.getText().toString());
        db.insert(TABLA, NOMBRE, cv);

        setResult(3);
        finish();
    }
}
