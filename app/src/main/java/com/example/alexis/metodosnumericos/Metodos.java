package com.example.alexis.metodosnumericos;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Alexis on 04/05/2017.
 */
public abstract class Metodos {

    private float[][] matA;
    private float[][] matB;

    public float[][] cambioRenglon(float[][] mat, int columna, int renglon){        //Columna = renglon en la que esta el pivote

        this.matA = mat.clone();
        this.matB = mat.clone();
        matB[columna] = matA[renglon+1];
        matB[renglon+1] = matA[columna];


        return matB;

    }

    public float[] gaussUtil(float[][] mat, int columna){

        this.matA = mat.clone();
        this.matB = mat.clone();

        float elementoR = matA[1][columna];
        for (int i = 0; i < matB[0].length; i++) {
            matB[1][i] = -elementoR*matA[0][i]+matA[1][i];
            String st = "-"+String.valueOf(matA[1][columna])+"*"+String.valueOf(matA[0][i])+"+"+String.valueOf(matA[1][i]);
        }
        return matB[1];
    }

}
