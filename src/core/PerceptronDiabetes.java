package core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

public class PerceptronDiabetes {
	

	public final String EMBARAZOS 			  = "EMBARAZOS";
	public final String CONCENTRACION_GLUCOSA = "CONCENTRACION_GLUCOSA";
	public final String PRESION_ARTERIAL 	  = "PRESION_ARTERIAL";
	public final String GROSOR_TRICEPS   	  = "GROSOR_TRICEPS";
	public final String INSULINA      		  = "INSULINA";
	public final String MASA_CORPORAL 		  = "MASA_CORPORAL";
	public final String FUNCION 			  = "FUNCION";
	public final String EDAD 				  = "EDAD";
	
	private double razonAprendizaje = 0.5;
	private int limiteEpocas = 10000;
	private int numeroEpocasFinal;
	private double umbralInicial;
	private double umbralFinal;
	
	public HashMap<String, Double> pesos = new HashMap<String, Double>();
	
	public double getRazonAprendizaje(){
		return this.razonAprendizaje;
	}
	
	public void setRazonAprendizaje(double razonAprendizaje){
		this.razonAprendizaje = razonAprendizaje;
	}
	
	public int getLimiteEpocas(){
		return this.limiteEpocas;
	}
	
	public void setLimiteEpocas(int limiteEpocas){
		this.limiteEpocas = limiteEpocas;
	}
	
	public int getNumeroEpocasFinal(){
		return this.numeroEpocasFinal;
	}
	
	public double getUmbralInicial(){
		return this.umbralInicial;
	}
	
	public void setUmbralInicial(double umbral){
		this.umbralInicial = umbral;
	}
	
	public double getUmbralFinal(){
		return this.umbralFinal;
	}
	
	public void setUmbralFinal(double umbral){
		this.umbralFinal = umbral;
	}
	
	public void inicializarPesos(){
		this.pesos.put(EMBARAZOS, this.generarAletorio());
		this.pesos.put(CONCENTRACION_GLUCOSA, this.generarAletorio());
		this.pesos.put(PRESION_ARTERIAL, this.generarAletorio());
		this.pesos.put(GROSOR_TRICEPS, this.generarAletorio());
		this.pesos.put(INSULINA, this.generarAletorio());
		this.pesos.put(MASA_CORPORAL, this.generarAletorio());
		this.pesos.put(FUNCION, this.generarAletorio());
		this.pesos.put(EDAD, this.generarAletorio());
	}
	
	public double getPeso(String llave){
		return this.pesos.get(llave);
	}
	
	public double setPeso(String llave, Double valor){
		return this.pesos.put(llave,valor);
	}
	
	private double getPesoLlaveEntera(int llave){
		double peso = 0;
		
		switch (llave) {
			case 0:
				peso = this.getPeso(EMBARAZOS);
				break;
			case 1:
				peso = this.getPeso(CONCENTRACION_GLUCOSA);
				break;
			case 2:
				peso = this.getPeso(PRESION_ARTERIAL);
				break;
			case 3:
				peso = this.getPeso(GROSOR_TRICEPS);
				break;
			case 4:
				peso = this.getPeso(INSULINA);
				break;
			case 5:
				peso = this.getPeso(MASA_CORPORAL);
				break;
			case 6:
				peso = this.getPeso(FUNCION);
				break;
			case 7:
				peso = this.getPeso(EDAD);
				break;

		}
		return peso;
	}
	
	private void setPesoLlaveEntera(int llave, double valor){
		
		switch (llave) {
			case 0:
				this.setPeso(EMBARAZOS, valor);
				break;
			case 1:
				this.setPeso(CONCENTRACION_GLUCOSA, valor);
				break;
			case 2:
				this.setPeso(PRESION_ARTERIAL, valor);
				break;
			case 3:
				this.setPeso(GROSOR_TRICEPS, valor);
				break;
			case 4:
				this.setPeso(INSULINA, valor);
				break;
			case 5:
				this.setPeso(MASA_CORPORAL, valor);
				break;
			case 6:
				this.setPeso(FUNCION, valor);
				break;
			case 7:
				this.setPeso(EDAD, valor);
				break;

		}
	}
	
	private double generarAletorio(){
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formato = new DecimalFormat("#.##",simbolos);
		
		double random = Math.random();
		random = (random == 0) ? 0.1: random;
		
	    return Double.valueOf(formato.format(random));
	}
	
	
	public void entrenar(String[][] entradas){
		
		boolean finalizado = false;
		int Pw,error, y;
		this.numeroEpocasFinal = 0;
		
			while(!finalizado){ 
				
				finalizado = true;
				
				for(int n = 0; n < entradas.length; n++){
					Pw = this.desicion(entradas[n]);
					y = Integer.valueOf(entradas[n][entradas[n].length-1]); //Clase esperada
					error = y - Pw;
						if(error != 0){
							finalizado = false;
							this.ajustarPesos(entradas[n], error);
						}
				}
				
				this.numeroEpocasFinal++;
				
				System.out.println(this.numeroEpocasFinal);
				
				if(this.numeroEpocasFinal >= this.limiteEpocas)
					finalizado = true;
			}
		
		//Pesos finales	
		System.out.println("Peso 1: " + this.getPesoLlaveEntera(0));
		System.out.println("Peso 2: " + this.getPesoLlaveEntera(1));
		System.out.println("Peso 3: " + this.getPesoLlaveEntera(2));
		System.out.println("Peso 4: " + this.getPesoLlaveEntera(3));
		System.out.println("Peso 5: " + this.getPesoLlaveEntera(4));
		System.out.println("Peso 6: " + this.getPesoLlaveEntera(5));
		System.out.println("Peso 7: " + this.getPesoLlaveEntera(6));
		System.out.println("Peso 8: " + this.getPesoLlaveEntera(7));
	}
	
	
	private int desicion(String [] patron){
		double sumatoria = 0;		
		
		for(int n = 0; n < patron.length-1; n++){
			sumatoria+=(Double.valueOf(patron[n]) * this.getPesoLlaveEntera(n));
		}
		
		
		if(sumatoria >= this.umbralFinal) 
			return 1;
		else
			return 0;
	}
	
	private void ajustarPesos(String [] patron, int error){
		for(int n = 0; n < patron.length-1; n++){
			this.setPesoLlaveEntera(n, this.getPesoLlaveEntera(n) + this.razonAprendizaje * error * Double.valueOf(patron[n]));
		}
	}
	
	public String[][] clasificar(String[][] entradasClasificar){
		for(int n = 0; n < entradasClasificar.length; n++){
			
			double sumatoria = 0;		
			
			for(int j = 0; j < entradasClasificar[n].length-1; j++){
				sumatoria+=(Double.valueOf(entradasClasificar[n][j]) * this.getPesoLlaveEntera(j));
			}
			
			if(sumatoria >= 0)
				entradasClasificar[n][entradasClasificar[n].length-1] = "1";
			else
				entradasClasificar[n][entradasClasificar[n].length-1] = "0";				
			
		}
		
		return entradasClasificar;
	}

}
