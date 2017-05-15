package com.example.alexis.metodosnumericos;

import android.util.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Alexis on 04/05/2017.
 */
public class Gauss extends Metodos{

    private Matriz matriz;

    public Gauss(Matriz matriz){
        this.matriz = matriz;
        float[][] mat = matriz.getMatriz();
    }

    //Se regresa treeMap para regresar cada una de las iteraciones iteraciones
    public TreeMap<Integer, float[][]> runGauss(){

        float[][] mat = matriz.getMatriz();
        float[][] matB = new float[2][matriz.getColumnas()];
        TreeMap<Integer, float[][]> iteraciones = new TreeMap<Integer, float[][]>();


        for (int i = 0; i < matriz.getRenglones(); i++) {
            if(i<matriz.getColumnas()){
                int ren = i;
                //Cambiando el renglon si el pivote es 0
                while (mat[i][i] == 0 && ren < (matriz.getRenglones()-1)) {
                    mat = cambioRenglon(mat, i, ren);
                    ren += i + 1;
                }
                float pivote = mat[i][i];
                if (pivote != 0) {
                    for (int j = 0; j < matriz.getColumnas(); j++) {
                        mat[i][j] = mat[i][j] / pivote;
                    }
                    matB[0] = mat[i];
                    for (int n = (i + 1); n < matriz.getRenglones(); n++) {
                        matB[1] = mat[n];
                        mat[n] = gaussUtil(matB, i);
                    }
                }
            }
            Log.d("Iteraciion", "#"+String.valueOf(i));
            for (int cont = 0; cont < matriz.getRenglones(); cont++) {
                Log.d("Renglon"+String.valueOf(cont), Arrays.toString(mat[cont]));
            }

            float[][] matIte = new float[matriz.getRenglones()][matriz.getColumnas()];
            for (int i2 = 0; i2 < matriz.getRenglones(); i2++) {
                for (int j2 = 0; j2 < matriz.getColumnas(); j2++) {
                    matIte[i2][j2] = mat[i2][j2];
                }
            }
            iteraciones.put(i,matIte);
        }

        Set<Map.Entry<Integer, float[][]>> entries = iteraciones.entrySet();
        Iterator<Map.Entry<Integer, float[][]>> iter = entries.iterator();

        while(iter.hasNext()){
            Map.Entry<Integer,float[][]> entry = iter.next();
            Integer iteracion = entry.getKey();
            float[][] elem = entry.getValue();
            for (int po = 0; po < elem.length; po++) {
                Log.d("Con iterador1"+"Matriz"+String.valueOf(iteracion), Arrays.toString(elem[po]));
            }
        }

        return iteraciones;

    }




}
