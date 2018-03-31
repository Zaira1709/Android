package com.example.zaira.formulario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre;
    private EditText editApaterno;
    private EditText editAmaterno;
    private DatePicker editFechanacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = (EditText) findViewById(R.id.nombre);
        editApaterno = (EditText) findViewById(R.id.apaterno);
        editAmaterno = (EditText) findViewById(R.id.amaterno);
        editFechanacimiento = (DatePicker) findViewById(R.id.fecha_nacimiento);
        final RadioButton mujer = (RadioButton) findViewById(R.id.idSexoMujer);
        RadioButton hombre =(RadioButton) findViewById(R.id.idSexoHombre);
        Button rfc = (Button) findViewById(R.id.rfc);
        Button curp = (Button) findViewById(R.id.curp);
        Button limpiar = (Button) findViewById(R.id.limpiar);

        final TextView resultado = (TextView) findViewById(R.id.resultado);



        rfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editNombre.getText().toString();
                String apaterno = editApaterno.getText().toString();
                String amaterno = editAmaterno.getText().toString();
                int mes = editFechanacimiento.getMonth();
                int anio = editFechanacimiento.getYear();
                int dia = editFechanacimiento.getDayOfMonth();
                Boolean esMujer = mujer.isChecked();

                if (validarCampos()){
                    //calcular rfc
                    String rfc;
                    String cadenaanio = String.valueOf(anio);
                    String cadenames = String.valueOf(mes+1);
                    String cadenadia = String.valueOf(dia);
                    if(mes<=9){
                        cadenames = "0"+cadenames;
                    }
                    if (dia<=9){
                        cadenadia ="0"+cadenadia;
                    }
                    rfc = apaterno.substring(0,2)+ amaterno.substring(0,1) + nombre.substring(0,1)+cadenaanio.substring(2,4)+cadenames+cadenadia;
                    resultado.setText("");
                    resultado.setText("Tu RFC es:"+ rfc);

                }else{
                    mostrarMensaje(R.string.text_error_general);
                }


            }
        });

        curp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String apaterno = editApaterno.getText().toString();
                String amaterno = editAmaterno.getText().toString();
                int mes = editFechanacimiento.getMonth();
                int anio = editFechanacimiento.getYear();
                int dia = editFechanacimiento.getDayOfMonth();
                Boolean esMujer = mujer.isChecked();
                String sex;
                String variante="MCLLE23";
                if (esMujer){
                    sex ="M";
                }else {
                    sex ="H";
                }
                if (validarCampos()){
                    //Calcula CURP
                    String curp;
                    String cadenaanio = String.valueOf(anio);
                    String cadenames = String.valueOf(mes+1);
                    String cadenadia = String.valueOf(dia);
                    if(mes<=9){
                        cadenames = "0"+cadenames;
                    }
                    if (dia<=9){
                        cadenadia ="0"+cadenadia;
                    }
                    curp = apaterno.substring(0,2)+ amaterno.substring(0,1) + nombre.substring(0,1)+cadenaanio.substring(2,4)+cadenames+cadenadia+sex+variante;
                    resultado.setText("");
                    resultado.setText("Tu CURP es:"+ curp);
                }else{
                    mostrarMensaje(R.string.text_error_general);
                }
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNombre.setText("");
                editAmaterno.setText("");
                editApaterno.setText("");
                resultado.setText("");
            }
        });

    }

    private boolean validarCampos(){

        if(editNombre.getText() == null || editNombre.getText().length() == 0){
            mostrarMensaje(R.string.text_error_nombre);
            return false;
        }else if (editAmaterno.getText()== null || editAmaterno.getText().length()== 0){
            mostrarMensaje(R.string.text_error_amaterno);
            return false;
        }else if (editApaterno.getText()== null || editApaterno.getText().length() == 0){
            mostrarMensaje(R.string.text_error_apaterno);
            return false;
        }

        return true;
    }

    private void mostrarMensaje(int error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

}
