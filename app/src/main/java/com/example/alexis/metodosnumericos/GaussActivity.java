package com.example.alexis.metodosnumericos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GaussActivity extends AppCompatActivity {

    private EditText[] casillasCoeficientes;
    float[] elementosMatriz;
    private int columnas;
    private int renglones;
    private RelativeLayout layoutMatriz;
    private String metodo;
    private TextView tVTitulo;
    private Matriz matrizForGauss;      //Para gauss
    private LinearLayout lLGauss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss);
        Bundle extras = getIntent().getExtras();
        layoutMatriz = (RelativeLayout)findViewById(R.id.relativeLayout);
        tVTitulo = (TextView)findViewById(R.id.tVTtitulo);
        lLGauss = (LinearLayout)findViewById(R.id.lLGauss);

        if(extras == null){
            Log.d("Es null", "nulllll");
        }else{
            columnas = extras.getInt("columnas");
            renglones = extras.getInt("renglones");
            metodo = extras.getString("metodo");
            tVTitulo.append("Solución del Método de " + metodo);
            Log.d("Elementos", Arrays.toString(elementosMatriz));
            if(metodo.equals("GaussJordan") || metodo.equals("Inversa")){
                elementosMatriz = extras.getFloatArray("elementos");
                this.generarMatrizResultado(layoutMatriz, elementosMatriz);
            }else if(metodo.equals("Gauss")){
                elementosMatriz = extras.getFloatArray("elementos");
                TreeMap<Integer, float[]> treeMapIteraciones = this.generarMatrizGauss();
                Set<Map.Entry<Integer, float[]>> entries = treeMapIteraciones.entrySet();
                Iterator<Map.Entry<Integer, float[]>> iter = entries.iterator();
                while(iter.hasNext()){
                    Map.Entry<Integer,float[]> entry = iter.next();
                    Integer iteracion = entry.getKey();
                    float[] elem = entry.getValue();
                    dibujarIteracionesGauss(iteracion, elem);
                }
            }
        }


//        Bundle elementos = getIntent().getExtras();
//        float[] elementosMatriz = elementos.getFloatArray("ElementosMatriz");

    }

    private void dibujarIteracionesGauss(Integer iteracion, float[] elem){

        TextView tV = new TextView(((TextView)findViewById(R.id.tVTtitulo)).getContext());
        tV.append("Iteracion #" + String.valueOf(iteracion+1));
        this.lLGauss.addView(tV);
        RelativeLayout relativeLayout = new RelativeLayout(((RelativeLayout)findViewById(R.id.relativeLayout)).getContext());
        generarMatrizResultado(relativeLayout, elem);
        this.lLGauss.addView(relativeLayout);

    }

    private void generarMatrizResultado(RelativeLayout relativeLayout, float[] elementosMatriz){

        relativeLayout.removeAllViews();
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
                relativeLayout.addView(eT, params);
                casillasCoeficientes[parcheDeDios] = eT;
                parcheDeDios++;
            }
        }
    }

    private TreeMap<Integer, float[]> generarMatrizGauss(){

        matrizForGauss = new Matriz(this.columnas, this.renglones, this.elementosMatriz);

        return matrizForGauss.getGauss();

    }
}
