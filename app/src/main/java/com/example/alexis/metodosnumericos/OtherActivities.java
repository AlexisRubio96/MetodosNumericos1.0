package com.example.alexis.metodosnumericos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

public class OtherActivities extends AppCompatActivity {


    float[] elementosMatriz;
    float reDeterminante;
    private int renglones;
    private RelativeLayout layoutMatriz;
    private String metodo;
    private TextView tVTitulo;
    private TextView tVdeterminante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activities);
        layoutMatriz = (RelativeLayout)findViewById(R.id.relativeLayoutOtherActivities);
        Bundle extras = getIntent().getExtras();
        tVTitulo = (TextView)findViewById(R.id.tVTituloOther);

        if(extras == null){
            Log.d("Es null", "nulllll");
        }else{
            metodo = extras.getString("metodo");
            tVTitulo.append("Solución del Método de " + metodo);
            Log.d("Elementos", Arrays.toString(elementosMatriz));
            if(metodo.equals("GaussSeidel") || metodo.equals("Cramer")){
                renglones = extras.getInt("renglones");
                elementosMatriz = extras.getFloatArray("elementos");
                this.generarResultado();
            }else if(metodo.equals("Determinante")){
                reDeterminante = extras.getFloat("elemento");
                tVdeterminante = new TextView(((TextView)findViewById(R.id.tVPrincipal)).getContext());
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 140);         //Si no se ve cambiar
                tVdeterminante.append("Resultado de la determinante = " + String.valueOf(reDeterminante));
                params.setMargins(100,80,(100),60);
                this.layoutMatriz.addView(tVdeterminante, params);
            }
        }


    }

    private void generarResultado(){

        this.layoutMatriz.removeAllViews();
        int parcheDeDios = 0;
        for (int i = 0; i < this.renglones; i++) {
            TextView tV = new TextView(((TextView)findViewById(R.id.tVPrincipal)).getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 140);         //Si no se ve cambiar
            tV.append("C"+String.valueOf(i)+" = " + elementosMatriz[parcheDeDios]);
            params.setMargins(100,80+(i*100),(100),60);
            this.layoutMatriz.addView(tV, params);
            parcheDeDios++;
        }

    }
}
