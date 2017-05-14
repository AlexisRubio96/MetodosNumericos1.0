package com.example.alexis.metodosnumericos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Arrays;

public class GaussActivity extends AppCompatActivity {

    private EditText[] casillasCoeficientes;
    float[] elementosMatriz;
    private int columnas;
    private int renglones;
    private RelativeLayout layoutMatriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss);
        Bundle extras = getIntent().getExtras();
        layoutMatriz = (RelativeLayout)findViewById(R.id.relativeLayout);

        if(extras == null){
            Log.d("Es null", "nulllll");
        }else{
            elementosMatriz = extras.getFloatArray("elementos");
            columnas = extras.getInt("columnas");
            renglones = extras.getInt("renglones");
            Log.d("Elementos", Arrays.toString(elementosMatriz));
        }

        this.generarMatrizResultado();

//        Bundle elementos = getIntent().getExtras();
//        float[] elementosMatriz = elementos.getFloatArray("ElementosMatriz");

    }

    private void generarMatrizResultado(){

        this.layoutMatriz.removeAllViews();
        casillasCoeficientes = new EditText[this.columnas*this.renglones];
        int parcheDeDios = 0;
        for (int i = 0; i < this.columnas; i++) {
            for (int j = 0; j < this.renglones; j++){
                EditText eT = new EditText(((EditText)findViewById(R.id.editTextPrincipal2)).getContext());       //Checar
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 140);
                eT.setText(String.valueOf(elementosMatriz[parcheDeDios]));
                params.setMargins(100+(i*100),80+(j*100),(i*100),60);
                params.addRule(EditText.TEXT_ALIGNMENT_CENTER);
                //eT.setTextSize(12);
                eT.setEms(5);
                eT.setFocusable(false);
                this.layoutMatriz.addView(eT, params);
                casillasCoeficientes[parcheDeDios] = eT;
                parcheDeDios++;
            }
        }
    }
}
