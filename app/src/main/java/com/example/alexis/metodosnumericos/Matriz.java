package com.example.alexis.metodosnumericos;

import android.util.Log;

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

    public float[] getGaussJordan(){
        Log.d("Get GaussJordan matriz", String.valueOf(this.getRenglones())+" x "+String.valueOf(this.getColumnas()));
        for (int i = 0; i < this.getRenglones(); i++) {
            Log.d("Renglon"+String.valueOf(i), Arrays.toString(this.getMatriz()[i]));
        }
        GaussJordan matrizGaussJordan = new GaussJordan(this);

        //Quitar
        float[] elems = elementosMatriz(matrizGaussJordan.runGaussJordan());

        return elems;
    }

    public float[] getCramer(){

        Matriz[] matrices = this.getCoeficientesConstantes();
        Cramer matrizCramer = new Cramer(matrices[0], matrices[1]);

        return matrizCramer.runCramer();

    }



    public float getDeterminante(){

        Determinante resDeterminante = new Determinante(this);
        return resDeterminante.runDeterminante();

    }

    //Unica con double
    public double[] getGaussSeidel(float tolerancia){

        float[] valIniciales = {8,1,2};
        Matriz matValiIniciales = new Matriz(1, this.getRenglones(), valIniciales);

        GaussSeidel resGaussSeidel = new GaussSeidel(this, matValiIniciales, tolerancia);

        return resGaussSeidel.runGaussSeidel();

    }


    private Matriz[] getCoeficientesConstantes(){

        float[] elementos = this.getElementos();

        int lengthMatNxN = elementos.length-this.getRenglones();
        float[] elementosNxN = new float[lengthMatNxN];
        System.arraycopy(elementos,0,elementosNxN,0,lengthMatNxN);

        int lengthMatNx1 = this.getRenglones();
        float[] elementosNx1 = new float[lengthMatNx1];
        System.arraycopy(elementos, lengthMatNx1*lengthMatNx1,elementosNx1,0,lengthMatNx1);

        Matriz matrizNxN = new Matriz(this.getColumnas()-1,this.getRenglones(),elementosNxN);
        Matriz matrizNx1 = new Matriz(1,this.getRenglones(),elementosNx1);

        Matriz[] matrices = {matrizNxN,matrizNx1};

        return matrices;

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
