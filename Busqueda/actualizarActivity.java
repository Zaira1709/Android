package com.example.zaira.busqueda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class actualizarActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText nombre, correo;
    Button editar;
    Cursor cursor;
    String id;
    public static final String NOMBRE = "nombre";
    public static final String CORREO = "correo";
    public static final String TABLA = "trabajador";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        db = new DBHelper(this).getWritableDatabase();

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        editar = findViewById(R.id.editar);
        id = getIntent().getExtras().getString("id");


        Cursor cActualizar = db.rawQuery("Select nombre, correo FROM trabajador WHERE _id=?",
                new String[]{String.valueOf(id)});
        cActualizar.moveToFirst();
        String nombreActual = cActualizar.getString(cActualizar.getColumnIndex("nombre"));
        String correoActual = cActualizar.getString(cActualizar.getColumnIndex("correo"));
        nombre.setText(nombreActual);
        correo.setText(correoActual);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizar();
            }
        });
    }
    public void actualizar(){
       ContentValues cv = new ContentValues();
        cv.put(NOMBRE, nombre.getText().toString());
        cv.put(CORREO, correo.getText().toString());
        db.update(TABLA, cv, "_id="+id, null);

        setResult(3);
        finish();
    }


}
