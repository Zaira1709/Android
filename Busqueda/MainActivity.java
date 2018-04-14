package com.example.zaira.busqueda;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lista;
    ListAdapter adapter;
    Cursor cursor;
    public static final int ACTUALIZAR = Menu.FIRST;
    public static final int BORRAR = Menu.FIRST + 1;
    public static final int AGREGAR = Menu.FIRST + 2;
    Button buscar;
    EditText textBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this).getWritableDatabase();
        lista = (ListView) findViewById(R.id.lista);
        iniciarLista();
        textBuscar = findViewById(R.id.textBuscar);
        buscar  = findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, AGREGAR, Menu.NONE, "Agregar ");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case AGREGAR:
                agregar();
                break;
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, ACTUALIZAR, Menu.NONE, "Actualizar");
        menu.add(Menu.NONE, BORRAR, Menu.NONE, "Borrar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo registro =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case ACTUALIZAR:
                actualizar(registro.id);
                break;
            case BORRAR:
                borrar(registro.id);
                break;
        }
        return true;
    }
    public void actualizar( long rowid) {
        Intent intento = new Intent(MainActivity.this, actualizarActivity.class);
        intento.putExtra("id", String.valueOf(rowid));
        startActivityForResult(intento,2);

    }

    public void borrar(final long rowid) {
        if (rowid > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("¿estas seguro de borrar el trabajador?")
                    .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] args = {String.valueOf(rowid)};
                            db.delete("trabajador", "_id=?", args);
                            cursor = db.rawQuery("SELECT _id, nombre, correo FROM trabajador ORDER BY nombre", null);
                            ((SimpleCursorAdapter) adapter).changeCursor(cursor);
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();


        }
    }

    public  void  agregar(){
        Intent intento = new Intent(MainActivity.this, agregarActivity.class);
        startActivityForResult(intento,2);
    }

    public  void  buscar(){
        String textB =textBuscar.getText().toString();
        String query = "SELECT _id, nombre, correo FROM trabajador where nombre like '%"+textB+"%' ORDER BY nombre";
        db = new DBHelper(this).getWritableDatabase();
        cursor = db.rawQuery(query, null);
        adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, new String[]{"nombre", "correo"},
                new int[]{R.id.nombre_trabajador, R.id.correo_trabajador}, 0);

        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    public class obtenerLista extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cursor = db.rawQuery("SELECT _id, nombre, correo FROM trabajador  ORDER BY nombre", null);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.row, cursor, new String[]{"nombre", "correo"},
                    new int[]{R.id.nombre_trabajador, R.id.correo_trabajador}, 0);
            lista.setAdapter(adapter);
            registerForContextMenu(lista);
        }
    }

    public void iniciarLista (){
        //correo todo el proceso
        new obtenerLista().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==3){
            iniciarLista();
        }

    }
}
