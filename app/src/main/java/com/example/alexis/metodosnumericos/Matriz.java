package com.example.alexis.metodosnumericos;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alexis on 04/05/2017.
 */
public class Matriz {

    private int columnas;
    private int renglones;
    private float[][] matriz;


    /*
    *
    * Los elementos de la matriz se reciben de la forma
    * a(renglon,columna) en el arreglo:
    * a(1,1), a(2,1), ...,a(n,1), a(1,2),a(2,2),...,a(n,2),...,a(1,n),a(2,n),...a(n,n)
    * (Ver dibujo)
    * */
    public Matriz(int columnas, int renglones, float[] elementos){

        this.renglones = renglones;
        this.columnas = columnas;
        this.matriz = new float[renglones][columnas];
        this.generarMatriz(this.matriz, elementos);

    }

    private void generarMatriz(float[][] matriz, float[] elemntos) {

        int cont = 0;
        for (int i = 0; i < this.columnas; i++) {
            for (int j = 0; j < this.renglones; j++) {
                matriz[j][i] = elemntos[cont];
                cont++;
            }
        }

    }

    public float[][] getMatriz() {
        return this.matriz;
    }

    public int getRenglones() {
        return this.renglones;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public float[] getElementos() {
        return this.elementosMatriz(this.getMatriz());
    }

    private float[] elementosMatriz(float[][] matriz){

        float[] elementos = new float[this.renglones*this.columnas];

        int cont = 0;
        for (int i = 0; i < this.columnas; i++) {
            for (int j = 0; j < this.renglones; j++) {
                Log.d("Elementos" + String.valueOf(j) + String.valueOf(i), "" + String.valueOf(matriz[j][i]));
                elementos[cont] = matriz[j][i];
                cont++;
            }
        }

        return elementos;

    }

    public float[] getGauss(){
        Log.d("Get Gauss de matriz", String.valueOf(this.getRenglones())+" x "+String.valueOf(this.getColumnas()));
        for (int i = 0; i < this.getRenglones(); i++) {
            Log.d("Renglon"+String.valueOf(i), Arrays.toString(this.getMatriz()[i]));
        }
        Gauss matrizGauss = new Gauss(this);

        return elementosMatriz(matrizGauss.runGauss());

    }

    /*
    @Override
    public String toString() {

        ArrayList<String> elementos = new ArrayList<String>();
        int cont = 0;
        for (int i = 0; i < this.columnas; i++) {
            for (int j = 0; j < this.renglones; j++) {
                Log.d("Elementos", ""+String.valueOf(matriz[j][i]));
                elementos[0] = String.valueOf(matriz[j][i]);
            }
        }

    }*/
}
