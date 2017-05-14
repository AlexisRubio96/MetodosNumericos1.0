package com.example.alexis.metodosnumericos;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Contacts;
import android.support.multidex.MultiDex;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import java.util.Arrays;

public class MenuMatriz extends AppCompatActivity {

    private NumberPicker pickerCol;
    private NumberPicker pickerRen;
    private RelativeLayout layoutMatriz;
    private EditText eTr;
/*    private Button botonGauss;
    private Button botonGaussJordan;*/
    private EditText[] casillasCoeficientes;
    private float[] elementos;        //Puede ser un ArrayList<Integer>
    private Matriz matriz;
    private ListView menuLateral;
    private DrawerLayout drawerLayout;

    //Multidex
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_matriz);

        //Declaración de variables
        menuLateral = (ListView)findViewById(R.id.menuLateral);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        //botonGauss = (Button)findViewById(R.id.buttonGauss);
        //botonGaussJordan = (Button)findViewById(R.id.buttonGaussJordan);
        layoutMatriz = (RelativeLayout)findViewById(R.id.layout_identificador);
        pickerCol = (NumberPicker)findViewById(R.id.columnas);
        pickerRen = (NumberPicker)findViewById(R.id.renglones);
        eTr = (EditText)findViewById(R.id.eTPrincipal);     //EditText de muestra
        eTr.setVisibility(View.INVISIBLE);

        //Llenar menu lateral
        String[] opciones = {"Gauss", "GaussJordan", "GaussSeidel", "Cramer", "Inversa", "Determinante"};
        ArrayAdapter<String> adp = new ArrayAdapter<String>(MenuMatriz.this, android.R.layout.simple_list_item_1, opciones );      //Ver opciones de parametros
        menuLateral.setAdapter(adp);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Ajustes columna
        pickerCol.setMaxValue(7);
        pickerCol.setMinValue(2);
        pickerCol.setWrapSelectorWheel(false);

        //Ajustes Renglones
        pickerRen.setMaxValue(7);
        pickerRen.setMinValue(2);
        pickerRen.setWrapSelectorWheel(false);

        //Modificr columnas
        pickerCol.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                copiarElementos(newVal, pickerRen.getValue(), layoutMatriz);
                eTr.setVisibility(View.INVISIBLE);
            }
        });

        //Modificar Renglones
        pickerRen.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                copiarElementos(pickerCol.getValue(), newVal, layoutMatriz);
            }
        });

 /*       //Activity Gauss
        botonGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarMatriz();
                float[] elementosMatrizGauss = matriz.getGauss();
                Intent gaussActivity = new Intent(MenuMatriz.this, GaussActivity.class);
                //getIntent().putExtra("ElementosMatriz", elementosMatrizGauss);
                startActivity(gaussActivity);
            }
        });

        //Activity GaussJordan
        botonGaussJordan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarMatriz();
                float[] elementosMatrizGaussJordan = matriz.getGaussJordan();
            }
        });*/

        //Actividad de Menu Lateral
        menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String metodo = (String)menuLateral.getAdapter().getItem(position);
                Log.d("Se seleccionó", metodo);
                generarMatriz();
                Bundle bundle;
                switch (metodo){
                    case "Gauss":
                        float[] elementosMatrizGauss = matriz.getGauss();
                        for (float num :
                                elementosMatrizGauss) {
                            Log.d("*****", String.valueOf(num));
                        }
                        Intent gaussActivity = new Intent(MenuMatriz.this,GaussActivity.class);
                        bundle = new Bundle();
                        bundle.putFloatArray("elementos", elementosMatrizGauss);
                        bundle.putInt("columnas",matriz.getColumnas());
                        bundle.putInt("renglones",matriz.getRenglones());
                        gaussActivity.putExtras(bundle);
                        startActivity(gaussActivity);
                        break;
                    case "GaussJordan":
                        float[] elementosMatrizGaussJordan = matriz.getGaussJordan();
                        break;
                    case "GaussSeidel":
                        float[] elementosGaussSeidel = matriz.getGaussSeidel((float)0.01);
                        Log.d("GaussSeidel", Arrays.toString(elementosGaussSeidel));
                        break;
                    case "Cramer":
                        float[] elementosMatrizCramer = matriz.getCramer();
                        Log.d("Cramer", Arrays.toString(elementosMatrizCramer));
                        break;
                    case "Inversa":
                        break;
                    case "Determinante":
                        float resDeterminante = matriz.getDeterminante();
                        Log.d("Determinante", String.valueOf(resDeterminante));
                        break;
                    default:
                        Log.d("Se seleccionó", metodo);
                        break;
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id== android.R.id.home){
            if (drawerLayout.isDrawerOpen(menuLateral)){
                drawerLayout.closeDrawer(menuLateral);
            }else{
                drawerLayout.openDrawer(menuLateral);
            }
        }

        return super.onOptionsItemSelected(item);
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
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 140);
                eT.setText(String.valueOf(parcheDeDios));
                params.setMargins(100+(i*100),80+(j*100),(i*100),60);
                params.addRule(EditText.TEXT_ALIGNMENT_CENTER);
                //eT.setTextSize(12);
                eT.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED |
                        InputType.TYPE_NUMBER_VARIATION_NORMAL );      //Input con numeros decimales
                //eT.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
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
