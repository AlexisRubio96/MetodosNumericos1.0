package com.example.alexis.metodosnumericos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class MenuMatriz extends AppCompatActivity {

    NumberPicker pickerCol;
    NumberPicker pickerRen;
    RelativeLayout layoutMatriz;
    EditText eTr;
    Button botonGauss;
    private EditText[] casillasCoeficientes;
    private float[] elementos;        //Puede ser un ArrayList<Integer>
    private Matriz matriz;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_matriz);

        botonGauss = (Button)findViewById(R.id.buttonGauss);
        layoutMatriz = (RelativeLayout)findViewById(R.id.layout_identificador);
        eTr = (EditText)findViewById(R.id.eTPrincipal);
        eTr.setVisibility(View.INVISIBLE);


        pickerCol = (NumberPicker)findViewById(R.id.columnas);
        pickerCol.setMaxValue(5);
        pickerCol.setMinValue(2);
        pickerCol.setWrapSelectorWheel(false);

        pickerRen = (NumberPicker)findViewById(R.id.renglones);
        pickerRen.setMaxValue(5);
        pickerRen.setMinValue(2);
        pickerRen.setWrapSelectorWheel(false);

        pickerCol.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                copiarElementos(newVal, pickerRen.getValue(), layoutMatriz);
                eTr.setVisibility(View.INVISIBLE);
            }
        });

        pickerRen.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                copiarElementos(pickerCol.getValue(), newVal, layoutMatriz);
            }
        });

        botonGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarMatriz();
                float[] prueba = matriz.getGauss();
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)      //No se que chingados se esto, se puso solo

    //copia los elementos de la matriz en el arreglo de elementos[]
    private void copiarElementos(int col, int ren, RelativeLayout layoutMatriz ){

        layoutMatriz.removeAllViews();
        casillasCoeficientes = new EditText[col*ren];
        int parcheDeDios = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < ren; j++){
                EditText eT = new EditText(((EditText)findViewById(R.id.eTPrincipal)).getContext());       //Checar
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(90, 90);
                eT.setText(""+String.valueOf(parcheDeDios));
                params.setMargins(100+(i*79),80+(j*79),(i*79),80+(j*79));
                params.addRule(EditText.TEXT_ALIGNMENT_CENTER);
                eT.setTextSize(15);
                eT.setEms(5);
                layoutMatriz.addView(eT, params);
                casillasCoeficientes[parcheDeDios] = eT;
                parcheDeDios++;
            }
        }
    }

    //Genera la matriz de la clase Matriz.java
    private void generarMatriz(){
        int columnas = pickerCol.getValue();
        int renglones = pickerRen.getValue();
        this.elementos = new float[casillasCoeficientes.length];
        int cont = 0;
        for(EditText editText: casillasCoeficientes){
            //Log.d("Arreglo", editText.getText().toString());
            elementos[cont] = Float.parseFloat(editText.getText().toString());
            cont++;
        }
        this.matriz = new Matriz(columnas, renglones, elementos);
    }

}
