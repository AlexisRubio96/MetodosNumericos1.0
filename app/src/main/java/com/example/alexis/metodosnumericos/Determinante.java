package com.example.alexis.metodosnumericos;

import java.util.Arrays;

public class Determinante extends Metodos{

	private Matriz matriz;
	
    public Determinante (Matriz matriz){
        this.matriz = matriz;
        float[][] mat = matriz.getMatriz();
    }
    
    public float runDeterminante(){
    	
    	//Variables

        float[][] mat = matriz.getMatriz();
        float[][] matB = new float[2][matriz.getColumnas()];
        int det = 1;
        int ren = 1;
        float renTotal = 0;
        
        //Operaciones
        for (int i = 0; i < matriz.getRenglones(); i++) {
			
        	ren = i;
        	//Cambiando renglón
        	while(mat[i][i] == 0 && ren < (matriz.getRenglones()-1)){
                mat = cambioRenglon(mat, i, ren);
                ren += i + 1;
        	}
        	
        	renTotal = renTotal + ren;
        	
        	//Operacion del determinante
        	det = (int) (det * mat[i][i]);
        	
        	//Mismas operaciones que Gauss
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
           
            if( renTotal%2 != 0 ){
            	det = det * -1;
            }
        	
		}

        
        return det;

    }
    
   /* public static void main (String[] args){

    	float[] elem ={20,1,1,1,3,0,9,4,1,2,3,4,8,0,1,8,1,2,6,1,1,5,0,1,11};

    	Matriz mat = new Matriz(5, 5, elem);

    	Determinante det = new Determinante(mat);
    	int determinante = det.runDeterminante();

    	System.out.println("\nDeterminante " + determinante );

    }*/
	
}
