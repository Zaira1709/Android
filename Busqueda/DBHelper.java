package com.example.zaira.busqueda;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zaira on 05/04/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="naat";
    public static final int DATABASE_VERSION=2;
    public static final String TABLA = "trabajador";
    public static final String NOMBRE = "nombre";
    public static final String CORREO = "correo";



    public DBHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabla="CREATE TABLE trabajador(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT, correo TEXT, puesto TEXT);";
        db.execSQL(tabla);
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE,"Diego Montero");
        cv.put(CORREO, "diego@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Zaira Calderon");
        cv.put(CORREO, "zcalderon@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Georgina Flores");
        cv.put(CORREO, "georgina@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Oscar Rivas");
        cv.put(CORREO, "rivas@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Edgar Hernandez");
        cv.put(CORREO, "edgar@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Angel Hernandez");
        cv.put(CORREO, "angel@naat.com");
        db.insert(TABLA, NOMBRE, cv);
        cv.put(NOMBRE,"Eduardo Calderon");
        cv.put(CORREO, "eduardo@naat.com");
        db.insert(TABLA, NOMBRE, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE,"Cesar Gutierrez");
        cv.put(CORREO, "cesar@naat.com");
        db.insert(TABLA, NOMBRE, cv);
    }
}
