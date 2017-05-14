package com.example.alexis.metodosnumericos;

import java.util.Arrays;

public class Cramer extends Metodos{
	
	private Matriz matriz, matriz2;

    public Cramer (Matriz matrizM, Matriz matrizB){
        this.matriz = matrizM;
        this.matriz2 = matrizB;
    }
    
    public float[] runCramer(){

    	//Variables 
        float[][] mat = matriz.getMatriz();
        float[][] matrizA = mat.clone();
        
        float[][] resMatrizA = matriz2.getMatriz();
        
        float[] resCramer = new float[matriz.getRenglones()];
        
        float[][] matrizB = matrizA.clone();
        float[] noCambiar = new float[matriz.getElementos().length];
        float[] elementosAGuardar = matriz.getElementos();
       
        int cont = 0;
        for (int i = 0; i < matriz.getRenglones(); i++) {
			for (int j = 0; j < matriz.getColumnas(); j++) {
				noCambiar[cont] = matrizB[j][i];
				cont++;
			}
		}
        
        
        int contador = 0;
        for (int i = 0; i < matriz.getRenglones(); i++) {
			for (int j = 0; j < matriz.getColumnas(); j++) {
				matrizB[j][i] = resMatrizA[j][0];
				
				elementosAGuardar[contador] = matrizB[j][i];
				contador++;
			}
		
			Matriz matB = new Matriz(matriz.getColumnas(), matriz.getRenglones(), elementosAGuardar);
			Matriz matA = new Matriz(matriz.getColumnas(), matriz.getRenglones(), noCambiar);
		
			Determinante detB = new Determinante(matB);
			Determinante detA = new Determinante(matA);
		
			double determinanteB = detB.runDeterminante();
			double determinanteA = detA.runDeterminante(); 
		
			resCramer[i] = (float) (determinanteB/determinanteA);
		
			matrizB = matrizA;
			
			for (int k = 0; k < noCambiar.length; k++) {
				elementosAGuardar[k] = noCambiar[k];
			}
		
			//System.out.println("original: " + Arrays.toString(noCambiar));
			//System.out.println("Cambiante: " + Arrays.toString(elementosAGuardar) +"\n" );
			
		}
        

        return resCramer;

    }
    
    
/*public static void main (String[] args){
    	
    	float[] elem1 ={20,1,1,1,3,0,9,4,1,2,3,4,8,0,1,8,1,2,6,1,1,5,0,1,11}; 
    	float[] elem2 = {1,1,0,2,2};
    	
    	Matriz mat1 = new Matriz(5, 5, elem1);
    	Matriz mat2 = new Matriz(1, 5, elem2);
    	
    	Cramer cra = new Cramer(mat1, mat2);
    	float[] res = cra.runDeterminante();
    	
    	System.out.println("Cramer " + Arrays.toString(res));

    }*/
}
