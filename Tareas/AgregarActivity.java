package com.example.zaira.pendientes;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AgregarActivity extends AppCompatActivity implements DatePickerFragment.TheListener {
    EditText campoTarea;
    RadioButton casaRadioButton;
    RadioButton trabajoRadioButton;
    RadioButton deportesRadioButton;
    TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        campoTarea = (EditText) findViewById(R.id.agregar_tarea);
        casaRadioButton = (RadioButton) findViewById(R.id.casa);
        trabajoRadioButton = (RadioButton) findViewById(R.id.Trabajo);
        deportesRadioButton = (RadioButton) findViewById(R.id.Deportes);
        Button agregar = (Button) findViewById(R.id.agregar);
        final Pendientes p = new Pendientes();
        fecha = (TextView) findViewById(R.id.fecha);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                p.setTarea(campoTarea.getText().toString());
                if (casaRadioButton.isChecked()) {
                    p.setTipo(1);
                } else if (trabajoRadioButton.isChecked()) {
                    p.setTipo(2);
                } else {
                    p.setTipo(3);
                }
                p.setDate(fecha.getText().toString());
                data.putExtra("objetoNuevo", p);
                data.setData(Uri.parse(campoTarea.getText().toString()));
                setResult(43, data);
                finish();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void returnDate(String date) {
        fecha.setText(date);
    }
}
