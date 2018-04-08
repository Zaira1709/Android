package com.example.zaira.pendientes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO= Menu.FIRST;
    public static final int OPCION_DOS = Menu.FIRST+1;
    public static final int OPCION_TRES = Menu.FIRST+2;
    public static final int OPCION_CUATRO = Menu.FIRST+3;
    public static final int OPCION_CINCO = Menu.FIRST+4;
    public static final int OPCION_SEIS = Menu.FIRST+5;
    public static final int OPCION_SIETE = Menu.FIRST+6;

    ArrayList<String> nombreTarea = new ArrayList<>();;
    ArrayList<String> nombreTareaRealizada = new ArrayList<>();
    TareaAdaptador adaptador1;
    TareaAdaptador adaptador;
    ListView pendientes, pendientesRealizados;
    ArrayList<Pendientes> listaPendientes;
    ArrayList<Pendientes> listaPendientesRealizados;
    ArrayAdapter<String> adaptadormenu, adaptadormenurealizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPendientes = new ArrayList<>();
        listaPendientes.add(new Pendientes("Casa", 1, "03-08-2018" ));
        listaPendientes.add(new Pendientes("trabajo ", 2, "03-02-2018" ));
        listaPendientes.add(new Pendientes("Deportes", 3, "03-02-2018" ));

        pendientes = (ListView) findViewById(R.id.list_pendientes);
        adaptador =new TareaAdaptador(listaPendientes, false);
        pendientes.setAdapter(adaptador);


        listaPendientesRealizados = new ArrayList<>();
        listaPendientesRealizados.add(new Pendientes("Tarea", 1, "03-06-2018" ));
        listaPendientesRealizados.add(new Pendientes("Tarea 2", 2, "03-06-2018" ));
        listaPendientesRealizados.add(new Pendientes("Tarea 3", 3, "03-06-2018" ));
        pendientesRealizados = (ListView) findViewById(R.id.list_pendientes_terminados);
        adaptador1 =new TareaAdaptador(listaPendientesRealizados, true);
        pendientesRealizados.setAdapter(adaptador1);



        registerForContextMenu(pendientes);


        adaptadormenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreTarea);
        adaptadormenurealizados = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreTareaRealizada);

        registerForContextMenu(pendientesRealizados);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, OPCION_UNO, Menu.NONE, "Agregar Nueva Tarea");
        menu.add(Menu.NONE, OPCION_DOS, Menu.NONE, "Salir de la app");
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case OPCION_UNO:

                Intent intento = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(intento, 12);
                break;
            case OPCION_DOS:
                finish();
                break;

        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if(v.getId() == pendientes.getId()) {
            menu.add(Menu.NONE, OPCION_TRES, Menu.NONE, "Editar elemento");
            menu.add(Menu.NONE, OPCION_CUATRO, Menu.NONE, "Eliminar elemento");
            menu.add(Menu.NONE, OPCION_CINCO, Menu.NONE, "Terminado");
        }else{
            menu.add(Menu.NONE, OPCION_SEIS, Menu.NONE, "Editar elemento");
            menu.add(Menu.NONE, OPCION_SIETE, Menu.NONE, "Eliminar elemento");
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo elemento =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case OPCION_TRES:
                actualizar(elemento.id,1);

                break;
            case  OPCION_CUATRO:
                eliminar(elemento.id, 1);
                break;
            case OPCION_CINCO:
                terminado(elemento.id);
                break;
            case OPCION_SEIS:
                actualizar(elemento.id,2);

                break;
            case  OPCION_SIETE:
                eliminar(elemento.id, 2);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void actualizar(final long rowid , int tipo) {

        Intent intento = new Intent(MainActivity.this, ActualizarActivity.class);
        if (tipo ==1) {
            intento.putExtra("pendiente", listaPendientes.get((int) rowid));
            nombreTarea.remove(listaPendientes.get((int) rowid).getTarea());
            listaPendientes.remove((int) rowid);
            startActivityForResult(intento, 12);
        }else {
            intento.putExtra("pendiente", listaPendientesRealizados.get((int) rowid));
            nombreTareaRealizada.remove(listaPendientesRealizados.get((int) rowid).getTarea());
            listaPendientesRealizados.remove((int) rowid);
            startActivityForResult(intento, 13);
        }

    }

    public void terminado(long rowid ) {
        listaPendientesRealizados.add(listaPendientes.get((int)rowid));

        nombreTareaRealizada.add(listaPendientes.get((int)rowid).getTarea());
        nombreTarea.remove(listaPendientes.get((int)rowid).getTarea());
        listaPendientes.remove((int)rowid);

        pendientesRealizados.setAdapter(adaptador1);
        registerForContextMenu(pendientesRealizados);


        adaptador1.notifyDataSetChanged();
        adaptador.notifyDataSetChanged();

    }
    public void eliminar(final long rowid, int tipo) {
        if(tipo ==1){
            nombreTarea.remove(listaPendientes.get((int)rowid).getTarea());
            listaPendientes.remove((int)rowid);
            adaptador.notifyDataSetChanged();
        }else{
            nombreTareaRealizada.remove(listaPendientesRealizados.get((int)rowid).getTarea());
            listaPendientesRealizados.remove((int)rowid);
            adaptador1.notifyDataSetChanged();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==12){
            if(resultCode == 43){
                Pendientes lugarResultado  = (Pendientes) data.getSerializableExtra("objetoNuevo");
                listaPendientes.add(lugarResultado);
                nombreTarea.clear();
                for (Pendientes p:listaPendientes){
                    nombreTarea.add(p.getTarea());
                }
                adaptador.notifyDataSetChanged();
            }
        }else if (requestCode ==13){
            if(resultCode == 43){
                Pendientes lugarResultado  = (Pendientes) data.getSerializableExtra("objetoNuevo");
                listaPendientesRealizados.add(lugarResultado);
                nombreTareaRealizada.clear();
                for (Pendientes p:listaPendientesRealizados){
                    nombreTareaRealizada.add(p.getTarea());
                }
                adaptador1.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class  TareaAdaptador extends ArrayAdapter<Pendientes>{

        Boolean terminada ;
        ArrayList<Pendientes> list;

        TareaAdaptador(ArrayList<Pendientes> list, Boolean terminada){
            super(MainActivity.this, R.layout.fila_lista, list);
            this.list = list;
            this.terminada = terminada;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //inflado de vista

            View row = getLayoutInflater().inflate( R.layout.fila_lista,parent, false);
            ImageView imagen =(ImageView) row.findViewById(R.id.icon);
            TextView nombre = (TextView)row.findViewById(R.id.nombre_fila_lista);
            Date currentTime = new Date();


            final Pendientes pendientesA =list.get(position);
            Date fehcha = fecha(pendientesA.getDate());

            if (!terminada && (fehcha.before(currentTime)||fehcha.equals (currentTime))){
                nombre.setTextColor(getResources().getColor(R.color.colorRed));
            }

            if (pendientesA.getTipo()==1){
                imagen.setImageResource(R.drawable.casa);
            }else if (pendientesA.getTipo()==2){
                imagen.setImageResource(R.drawable.trabajo);
            }else{
                imagen.setImageResource(R.drawable.deportes);
            }

            nombre.setText(pendientesA.getTarea());

            return  row;
        }
    }

    private  Date fecha(String dateInString) {
        Date date= null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(dateInString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
