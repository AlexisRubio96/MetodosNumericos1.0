package com.example.alexis.metodosnumericos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    Button cambiarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button cambiarPantalla;
        EditText numbers;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



        cambiarPantalla = (Button)findViewById(R.id.camPant);
/*
        cambiarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "Has pulsado el boton", Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    public void activityBoton(View v){

        Intent siguiente = new Intent(MenuPrincipal.this, MenuMatriz.class);
        startActivity(siguiente);

    }
}

