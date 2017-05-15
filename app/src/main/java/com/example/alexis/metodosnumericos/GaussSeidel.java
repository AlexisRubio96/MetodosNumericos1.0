package com.example.alexis.metodosnumericos;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Alexis on 13/05/2017.
 */
public class GaussSeidel extends Metodos {

    Matriz matrizCoeficientes, matrizValiniciales;
    double tolerancia;

    public GaussSeidel(Matriz matrizCoeficientes, Matriz matrizConstantes, double tolerancia){
        this.matrizCoeficientes = matrizCoeficientes;
        this.matrizValiniciales = matrizConstantes;
        this.tolerancia = tolerancia;
    }

    public double[] runGaussSeidel() {

        double error = this.tolerancia+1;

        double[][] matA = new double[matrizCoeficientes.getRenglones()][matrizCoeficientes.getColumnas()];
        for (int i = 0; i < matA.length; i++) {
            for (int j = 0; j < matA[i].length; j++) {
                matA[i][j] = (double)this.matrizCoeficientes.getMatriz()[i][j];
            }
        }

        int longitud = matrizCoeficientes.getColumnas()-1;      //Valor de j

        double[] res = new double[matrizValiniciales.getRenglones()];
        for (int cont = 0; cont < matrizValiniciales.getRenglones(); cont++) {
            res[cont] = (double)matrizValiniciales.getMatriz()[cont][0];
        }


        double[] err = new double[matrizCoeficientes.getRenglones()];

        double mulError = 1;
        while(error > this.tolerancia){
            //Poner if de i<columnas
            for (int i = 0; i < err.length; i++) {
                int ren = 0;        //Se cambio de uno
                //Aqui hay que checar como pasar el double[][] a float[][] para el cambio de renglon() esto seolo es necesario cuando mat[i][i] = 0;
 /*               while (matA[i][i] == 0 && ren < (matrizCoeficientes.getRenglones()-1)) {
                    matA = cambioRenglon(matA, i, ren);
                    ren += i + 1;
                }*/

                //Estas son pruebas
                int ll =0;
                for (double[] arr :
                        matA) {
                    ll++;
                }
                //Terminan purebas

                double temporal = res[i];
                double sum = 0;
                //Sumatoria
                for (int k = 0; k < err.length; k++) {
                    if(i!=k){
                        sum += matA[i][k]*res[k];
                    }
                }
                res[i] = (matA[i][longitud]-sum)/matA[i][i];
                err[i] = Math.abs((res[i]-temporal)/res[i]);
                mulError = mulError*err[i];
            }
            error = (double) Math.pow(mulError, (double)(1.0)/(double)err.length);
        }


        return  res;
    }

}
