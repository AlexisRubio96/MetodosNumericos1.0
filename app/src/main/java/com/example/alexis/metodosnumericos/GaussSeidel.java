package com.example.alexis.metodosnumericos;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Alexis on 13/05/2017.
 */
public class GaussSeidel extends Metodos {

    Matriz matrizCoeficientes, matrizValiniciales;
    float tolerancia;

    public GaussSeidel(Matriz matrizCoeficientes, Matriz matrizConstantes, float tolerancia){
        this.matrizCoeficientes = matrizCoeficientes;
        this.matrizValiniciales = matrizConstantes;
        this.tolerancia = tolerancia;
    }

    public float[] runGaussSeidel() {

        float error = this.tolerancia+1;

        float[][] matA = matrizCoeficientes.getMatriz().clone();
        int longitud = matrizCoeficientes.getColumnas()-1;      //Valor de j

        float[] res = new float[matrizValiniciales.getRenglones()];
        for (int cont = 0; cont < matrizValiniciales.getRenglones(); cont++) {
            res[cont] = matrizValiniciales.getMatriz()[cont][0];
        }

        Log.d("resres",Arrays.toString(res));

        float[] err = new float[matrizCoeficientes.getRenglones()];

        float mulError = 1;
        while(error > this.tolerancia){
            //Poner if de i<columnas
            for (int i = 0; i < err.length; i++) {
                int ren = 0;        //Se cambio de uno
                while (matA[i][i] == 0 && ren < (matrizCoeficientes.getRenglones()-1)) {
                    matA = cambioRenglon(matA, i, ren);
                    ren += i + 1;
                }
                int ll =0;
                for (float[] arr :
                        matA) {
                    Log.d("MatA" + String.valueOf(ll), Arrays.toString(arr));
                    ll++;
                }
                float temporal = res[i];
                int sum = 0;
                //Sumatoria
                for (int k = 0; k < err.length; k++) {
                    if(i!=k){
                        sum += matA[i][k]*res[k];
                    }
                }
                Log.d("sum", String.valueOf(sum));
                res[i] = (matA[i][longitud]-sum)/matA[i][i];
                Log.d("res"+String.valueOf(i), String.valueOf(res[i]));
                err[i] = Math.abs((res[i]-temporal)/res[i]);
                mulError = mulError*err[i];
                Log.d("err"+String.valueOf(i), String.valueOf(err[i]));
            }
            Log.d("iiiii", String.valueOf(mulError)+ String.valueOf(err.length));
            error = (float)Math.pow(mulError, (1/err.length));
            Log.d("iiii+i",String.valueOf(error));
            Log.d("iiiii", String.valueOf(this.tolerancia));
        }


        return res;
    }

}
