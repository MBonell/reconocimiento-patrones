package gui.adaline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import normalizacion.Normalizacion;

import core.Adaline;

public class AdalineContainer {
	
	Adaline adaline;
	Normalizacion normalizacion = new Normalizacion();
	String normalizar = "MAX/MIN"; //MAX - MAX/MIN - NO
	
	JDesktopPane desk;
	
	JInternalFrame iframe;
	JInternalFrame ventanaResultados;

	JPanel panelSetAprendizaje;
	JPanel panelPesos;
	JPanel panelParametros;
	JPanel panelSalida;

	JLabel lblPesoEmbarazo;
	JLabel lblPesoConcentracionGlucosa;
	JLabel lblPesoPresionArterial;
	JLabel lblPesoGrosorTriceps;
	JLabel lblPesoInsulina;
	JLabel lblPesoMasaCorporal;
	JLabel lblPesoFuncion;
	JLabel lblPesoEdad;
	
	JLabel lblRazonAprendizaje;
	JLabel lblLimiteEpocasInicial;
	JLabel lblErrorEsperado;
	JLabel lblNoEntrenada;
	
	JLabel lblPesoEmbarazoFinal;
	JLabel lblPesoConcentracionGlucosaFinal;
	JLabel lblPesoPresionArterialFinal;
	JLabel lblPesoGrosorTricepsFinal;
	JLabel lblPesoInsulinaFinal;
	JLabel lblPesoMasaCorporalFinal;
	JLabel lblPesoFuncionFinal;
	JLabel lblPesoEdadFinal;
	
	JLabel lblPesoEmbarazoValor;
	JLabel lblPesoConcentracionGlucosaValor;
	JLabel lblPesoPresionArterialValor;
	JLabel lblPesoGrosorTricepsValor;
	JLabel lblPesoInsulinaValor;
	JLabel lblPesoMasaCorporalValor;
	JLabel lblPesoFuncionValor;
	JLabel lblPesoEdadValor;
	
	JLabel lblErrorFinal;
	JLabel lblErrorValor;
	JLabel lblEpocasFinal;
	JLabel lblEpocasValor;
	
	JLabel lblEfectividad;
	
	JTextField txtPesoEmbarazo;
	JTextField txtPesoConcentracionGlucosa;
	JTextField txtPesoPresionArterial;
	JTextField txtPesoGrosorTriceps;
	JTextField txtPesoInsulina;
	JTextField txtPesoMasaCorporal;
	JTextField txtPesoFuncion;
	JTextField txtPesoEdad;

	JTextField txtRazonAprendizaje;
	JTextField txtLimiteEpocasInicial;
	JTextField txtErrorDeseado;

	
	JTable tablaSetAprendizaje;
	JTable tablaSetPruebas;
	
	JButton btnEntrenar = new JButton("Iniciar entrenamiento");
	JButton btnClasificar = new JButton("Clasificar patrón");
	JButton btnAgregarSetAprendizaje = new JButton("+");
	JButton btnEliminarSetAprendizaje = new JButton("-");
	JButton btnAgregarSetPrueba = new JButton("+");
	JButton btnEliminarSetPrueba = new JButton("-");
	JButton btnClasificarPrueba = new JButton("Iniciar clasificación");

	
	String nombreCaracteristicas [] = new String[9];
	public final String TITULO_EMBARAZOS = "Embarazos";
	public final String TITULO_CONCENTRACION_GLUCOSA = "Conc. de glucosa en plasma a 2 horas en una prueba de tolerancia oral a la glucosa";
	public final String TITULO_PRESION_ARTERIAL = "Presión arterial diastólica (mm Hg)";
	public final String TITULO_GROSOR_TRICEPS = "Grosor del pliegue cutáneo del tríceps (mm)";
	public final String TITULO_INSULINA = "Insulina en suero 2 horas (mu U / ml)";
	public final String TITULO_MASA_CORPORAL = "Índice de masa corporal (peso (kg)/(altura (m))^2)";
	public final String TITULO_FUNCION = "Función Diabetes pedigree";
	public final String TITULO_EDAD = "Edad";

	public final String TITULO_RAZON_APRENDIZAJE = "Valor razón de aprendizaje";
	public final String TITULO_LIMITE_EPOCAS = "Valor límite de épocas";
	public final String TITULO_ERROR_ESPERADO = "Valor del error deseado";
	public final String TITULO_NO_ENTRENADA = "Favor de entrenar la Adaline";

	JScrollPane scrollPanePrueba;
	
    public void ventanaAdaline(JDesktopPane desk){
    	
    	adaline = new Adaline();
    	this.desk = desk;
    	iframe = new JInternalFrame("Adaline - Predicción sobre pacientes con posible diabetes", true, true, true, true);
		iframe.setBounds(50, 10, 1100, 610);
		iframe.setResizable(false);
		iframe.setMaximizable(false);
		iframe.setVisible(true);
		this.desk.add(iframe);
		iframe.setLayout(null);		
		
		nombreCaracteristicas[0] = TITULO_EMBARAZOS;
		nombreCaracteristicas[1] = TITULO_CONCENTRACION_GLUCOSA;
		nombreCaracteristicas[2] = TITULO_PRESION_ARTERIAL;
		nombreCaracteristicas[3] = TITULO_GROSOR_TRICEPS;
		nombreCaracteristicas[4] = TITULO_INSULINA;
		nombreCaracteristicas[5] = TITULO_MASA_CORPORAL;
		nombreCaracteristicas[6] = TITULO_FUNCION;
		nombreCaracteristicas[7] = TITULO_EDAD;
		nombreCaracteristicas[8] = "Clase";
				
		//Set de aprendizaje
		panelSetAprendizaje = new JPanel();
		panelSetAprendizaje.setBorder(BorderFactory.createTitledBorder("Set de aprendizaje "));
		panelSetAprendizaje.setBounds(10, 10, 620, 270);
		panelSetAprendizaje.setLayout(null);
		
		tablaSetAprendizaje = new JTable(new DefaultTableModel());
		
		DefaultTableModel model = (DefaultTableModel) tablaSetAprendizaje.getModel();
		
		for(String columna : nombreCaracteristicas ){
			model.addColumn(columna);
		}
		
		this.cargarSetAprendizaje();
		
		JScrollPane scrollPane = new JScrollPane(tablaSetAprendizaje);
		scrollPane.setBounds(15, 20, 550, 220);
		panelSetAprendizaje.add(scrollPane);
		
		btnAgregarSetAprendizaje.setBounds(580, 80, 25, 25);
		btnEliminarSetAprendizaje.setBounds(580, 130, 25, 25);
		
		panelSetAprendizaje.add(btnAgregarSetAprendizaje);
		panelSetAprendizaje.add(btnEliminarSetAprendizaje);
		
		
		//Pesos
		panelPesos = new JPanel();
		panelPesos.setBorder(BorderFactory.createTitledBorder("Pesos "));
		panelPesos.setBounds(640, 10, 425, 270);
		panelPesos.setLayout(null);
		adaline.inicializarPesos();
		
		lblPesoEmbarazo = new JLabel(TITULO_EMBARAZOS);
		lblPesoEmbarazo.setBounds(10, 30, 170, 20);
		panelPesos.add(lblPesoEmbarazo);
		
		txtPesoEmbarazo = new JTextField();
		txtPesoEmbarazo.setBounds(340, 25, 70, 30);
		txtPesoEmbarazo.setText(Double.toString(adaline.getPeso(adaline.EMBARAZOS)));
		panelPesos.add(txtPesoEmbarazo);
		
		lblPesoConcentracionGlucosa = new JLabel(TITULO_CONCENTRACION_GLUCOSA);
		lblPesoConcentracionGlucosa.setBounds(10, 60, 320, 20);
		panelPesos.add(lblPesoConcentracionGlucosa);
		
		txtPesoConcentracionGlucosa = new JTextField();
		txtPesoConcentracionGlucosa.setBounds(340, 55, 70, 30);
		txtPesoConcentracionGlucosa.setText(Double.toString(adaline.getPeso(adaline.CONCENTRACION_GLUCOSA)));
		panelPesos.add(txtPesoConcentracionGlucosa);
		
		lblPesoPresionArterial = new JLabel(TITULO_PRESION_ARTERIAL);
		lblPesoPresionArterial.setBounds(10, 90, 220, 20);
		panelPesos.add(lblPesoPresionArterial);
		
		txtPesoPresionArterial = new JTextField();
		txtPesoPresionArterial.setBounds(340, 85, 70, 30);
		txtPesoPresionArterial.setText(Double.toString(adaline.getPeso(adaline.PRESION_ARTERIAL)));
		panelPesos.add(txtPesoPresionArterial);
		
		lblPesoGrosorTriceps = new JLabel(TITULO_GROSOR_TRICEPS);
		lblPesoGrosorTriceps.setBounds(10, 120, 280, 20);
		panelPesos.add(lblPesoGrosorTriceps);
		
		txtPesoGrosorTriceps = new JTextField();
		txtPesoGrosorTriceps.setBounds(340, 115, 70, 30);
		txtPesoGrosorTriceps.setText(Double.toString(adaline.getPeso(adaline.GROSOR_TRICEPS)));
		panelPesos.add(txtPesoGrosorTriceps);
		
		lblPesoInsulina = new JLabel(TITULO_INSULINA);
		lblPesoInsulina.setBounds(10, 150, 260, 20);
		panelPesos.add(lblPesoInsulina);
		
		txtPesoInsulina = new JTextField();
		txtPesoInsulina.setBounds(340, 145, 70, 30);
		txtPesoInsulina.setText(Double.toString(adaline.getPeso(adaline.INSULINA)));
		panelPesos.add(txtPesoInsulina);
		
		lblPesoMasaCorporal = new JLabel(TITULO_MASA_CORPORAL);
		lblPesoMasaCorporal.setBounds(10, 180, 330, 20);
		panelPesos.add(lblPesoMasaCorporal);
		
		txtPesoMasaCorporal = new JTextField();
		txtPesoMasaCorporal.setBounds(340, 175, 70, 30);
		txtPesoMasaCorporal.setText(Double.toString(adaline.getPeso(adaline.MASA_CORPORAL)));
		panelPesos.add(txtPesoMasaCorporal);
		
		lblPesoFuncion = new JLabel(TITULO_FUNCION);
		lblPesoFuncion.setBounds(10, 210, 330, 20);
		panelPesos.add(lblPesoFuncion);
		
		txtPesoFuncion = new JTextField();
		txtPesoFuncion.setBounds(340, 205, 70, 30);
		txtPesoFuncion.setText(Double.toString(adaline.getPeso(adaline.FUNCION)));
		panelPesos.add(txtPesoFuncion);
		
		lblPesoEdad = new JLabel(TITULO_EDAD);
		lblPesoEdad.setBounds(10, 240, 330, 20);
		panelPesos.add(lblPesoEdad);
		
		txtPesoEdad = new JTextField();
		txtPesoEdad.setBounds(340, 235, 70, 30);
		txtPesoEdad.setText(Double.toString(adaline.getPeso(adaline.EDAD)));
		panelPesos.add(txtPesoEdad);
		
		

		//Parametros
		panelParametros = new JPanel();
		panelParametros.setBorder(BorderFactory.createTitledBorder("Parámetros "));
		panelParametros.setBounds(640, 280, 270, 180);
		panelParametros.setLayout(null);
		
		//Razon de aprendizaje
		lblRazonAprendizaje = new JLabel(TITULO_RAZON_APRENDIZAJE);
		lblRazonAprendizaje.setBounds(10, 30, 170, 20);
		panelParametros.add(lblRazonAprendizaje);
		
		txtRazonAprendizaje = new JTextField();
		txtRazonAprendizaje.setBounds(180, 25, 70, 30);
		txtRazonAprendizaje.setText(Double.toString(adaline.getRazonAprendizaje()));
		panelParametros.add(txtRazonAprendizaje);
		
		
		//Limite epocas
		lblLimiteEpocasInicial = new JLabel(TITULO_LIMITE_EPOCAS);
		lblLimiteEpocasInicial.setBounds(10, 60, 170, 20);
		panelParametros.add(lblLimiteEpocasInicial);
		
		txtLimiteEpocasInicial = new JTextField();
		txtLimiteEpocasInicial.setBounds(180, 55, 70, 30);
		txtLimiteEpocasInicial.setText(Integer.toString(adaline.getLimiteEpocas()));
		panelParametros.add(txtLimiteEpocasInicial);
		
		//Error esperado
		lblErrorEsperado = new JLabel(TITULO_ERROR_ESPERADO);
		lblErrorEsperado.setBounds(10, 90, 150, 20);
		panelParametros.add(lblErrorEsperado);
		
		txtErrorDeseado = new JTextField();
		txtErrorDeseado.setBounds(180, 85, 70, 30);
		txtErrorDeseado.setText(Double.toString(adaline.getErrorDeseado()));
		panelParametros.add(txtErrorDeseado);

		iframe.add(panelSetAprendizaje);
		iframe.add(panelPesos);
		iframe.add(panelParametros);

		
		//Salida
		panelSalida = new JPanel();
		panelSalida.setBorder(BorderFactory.createTitledBorder("Salida "));
		panelSalida.setBounds(10, 280, 620, 270);
		panelSalida.setLayout(null);
		
		lblNoEntrenada = new JLabel("<html><b>"+TITULO_NO_ENTRENADA+"</b></html>");
		lblNoEntrenada.setBounds(210, 80, 250, 80);
		panelSalida.add(lblNoEntrenada);
		
		//Salida pesos
		lblPesoEmbarazoFinal = new JLabel(TITULO_EMBARAZOS);
		lblPesoEmbarazoFinal.setBounds(10, 30, 170, 20);
		panelSalida.add(lblPesoEmbarazoFinal);
		
		lblPesoEmbarazoValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoEmbarazoValor.setBounds(340, 30, 170, 20);
		panelSalida.add(lblPesoEmbarazoValor);
		
		
		lblPesoConcentracionGlucosaFinal = new JLabel(TITULO_CONCENTRACION_GLUCOSA);
		lblPesoConcentracionGlucosaFinal.setBounds(10, 60, 540, 20);
		panelSalida.add(lblPesoConcentracionGlucosaFinal);
		
		lblPesoConcentracionGlucosaValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoConcentracionGlucosaValor.setBounds(550, 60, 170, 20);
		panelSalida.add(lblPesoConcentracionGlucosaValor);
		
		
		lblPesoPresionArterialFinal = new JLabel(TITULO_PRESION_ARTERIAL);
		lblPesoPresionArterialFinal.setBounds(10, 90, 240, 20);
		panelSalida.add(lblPesoPresionArterialFinal);
		
		lblPesoPresionArterialValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoPresionArterialValor.setBounds(340, 90, 170, 20);
		panelSalida.add(lblPesoPresionArterialValor);
		
		
		lblPesoGrosorTricepsFinal = new JLabel(TITULO_GROSOR_TRICEPS);
		lblPesoGrosorTricepsFinal.setBounds(10, 120, 280, 20);
		panelSalida.add(lblPesoGrosorTricepsFinal);
		
		lblPesoGrosorTricepsValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoGrosorTricepsValor.setBounds(340, 120, 170, 20);
		panelSalida.add(lblPesoGrosorTricepsValor);
		
		
		lblPesoInsulinaFinal = new JLabel(TITULO_INSULINA);
		lblPesoInsulinaFinal.setBounds(10, 150, 280, 20);
		panelSalida.add(lblPesoInsulinaFinal);
		
		lblPesoInsulinaValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoInsulinaValor.setBounds(340, 150, 170, 20);
		panelSalida.add(lblPesoInsulinaValor);
		
		
		lblPesoMasaCorporalFinal = new JLabel(TITULO_MASA_CORPORAL);
		lblPesoMasaCorporalFinal.setBounds(10, 180, 320, 20);
		panelSalida.add(lblPesoMasaCorporalFinal);
		
		lblPesoMasaCorporalValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoMasaCorporalValor.setBounds(340, 180, 170, 20);
		panelSalida.add(lblPesoMasaCorporalValor);
		
		
		lblPesoFuncionFinal = new JLabel(TITULO_FUNCION);
		lblPesoFuncionFinal.setBounds(10, 210, 320, 20);
		panelSalida.add(lblPesoFuncionFinal);
		
		lblPesoFuncionValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoFuncionValor.setBounds(340, 210, 170, 20);
		panelSalida.add(lblPesoFuncionValor);
		
		
		lblPesoEdadFinal = new JLabel(TITULO_EDAD);
		lblPesoEdadFinal.setBounds(10, 240, 320, 20);
		panelSalida.add(lblPesoEdadFinal);
		
		lblPesoEdadValor = new JLabel("<html><b>0.0</b></html>");
		lblPesoEdadValor.setBounds(340, 240, 170, 20);
		panelSalida.add(lblPesoEdadValor);
		
		
		//Salida error deseado
		lblErrorFinal = new JLabel("Error");
		lblErrorFinal.setBounds(420, 120, 170, 20);
		panelSalida.add(lblErrorFinal);
		
		lblErrorValor= new JLabel("<html><b>0.0</b></html>");
		lblErrorValor.setBounds(460, 120, 200, 20);
		panelSalida.add(lblErrorValor);
		
		//Salida epocas
		lblEpocasFinal = new JLabel("Épocas");
		lblEpocasFinal.setBounds(420, 150, 170, 20);
		panelSalida.add(lblEpocasFinal);
		
		lblEpocasValor = new JLabel("<html><b>0.0</b></html>");
		lblEpocasValor.setBounds(520, 150, 170, 20);
		panelSalida.add(lblEpocasValor);
		
		btnClasificar.setBounds(450, 470, 170, 60);
		btnEntrenar.setBounds(915, 330, 150, 80);
		
		iframe.add(btnEntrenar);
		iframe.add(btnClasificar);
		iframe.add(panelSalida);
		
		visibleSalida(false);

    }
    
    
    public void visibleSalida(boolean visible){
    	 lblPesoEmbarazoFinal.setVisible(visible);
    	 lblPesoConcentracionGlucosaFinal.setVisible(visible);
    	 lblPesoPresionArterialFinal.setVisible(visible);
    	 lblPesoGrosorTricepsFinal.setVisible(visible);
    	 lblPesoInsulinaFinal.setVisible(visible);
    	 lblPesoMasaCorporalFinal.setVisible(visible);
    	 lblPesoFuncionFinal.setVisible(visible);
    	 lblPesoEdadFinal.setVisible(visible);
    	 lblPesoEmbarazoValor.setVisible(visible);
    	 lblPesoConcentracionGlucosaValor.setVisible(visible);
    	 lblPesoPresionArterialValor.setVisible(visible);
    	 lblPesoGrosorTricepsValor.setVisible(visible);
    	 lblPesoInsulinaValor.setVisible(visible);
    	 lblPesoMasaCorporalValor.setVisible(visible);
    	 lblPesoFuncionValor.setVisible(visible);
    	 lblPesoEdadValor.setVisible(visible);
    	 lblErrorFinal.setVisible(visible);
    	 lblErrorValor.setVisible(visible);
    	 lblEpocasFinal.setVisible(visible);
    	 lblEpocasValor.setVisible(visible);
    	 btnClasificar.setVisible(visible);
    }
    
    
    public void ventanaClasificaciones(){
    	ventanaResultados = new JInternalFrame("Nuevos patrónes", true, true, true, true);
    	ventanaResultados.setBounds(100, 500, 770, 350);
    	ventanaResultados.setResizable(false);
    	ventanaResultados.setVisible(true);
    	ventanaResultados.setTitle("Clasificar");
    	this.desk.add(ventanaResultados);
		ventanaResultados.setLayout(null);

		
		tablaSetPruebas = new JTable(new DefaultTableModel());
		
		DefaultTableModel model = (DefaultTableModel) tablaSetPruebas.getModel();
		
		for(String columna : nombreCaracteristicas ){
			model.addColumn(columna);
		}
		
		this.cargarSetPrueba();
		
		scrollPanePrueba = new JScrollPane(tablaSetPruebas);
		scrollPanePrueba.setBounds(15, 20, 670, 220);
		ventanaResultados.add(scrollPanePrueba);
		
		btnAgregarSetPrueba.setBounds(700, 80, 25, 25);
		btnEliminarSetPrueba.setBounds(700, 130, 25, 25);
		btnClasificarPrueba.setBounds(540, 250, 150, 35);
		
		lblEfectividad = new JLabel("");
		lblEfectividad.setBounds(15, 250, 250, 35);
		
		ventanaResultados.add(btnAgregarSetPrueba);
		ventanaResultados.add(btnEliminarSetPrueba);
		ventanaResultados.add(btnClasificarPrueba);
		ventanaResultados.add(lblEfectividad);


    }
    
	private void cargarSetAprendizaje(){
		DefaultTableModel temp = (DefaultTableModel) this.tablaSetAprendizaje.getModel();
		String csvFile = "dataset/diabetes_aprendizaje_backpropagation.csv";

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
	 			
			double maximo0 = normalizacion.obtenerMaximo(0, csvFile);
			double maximo1 = normalizacion.obtenerMaximo(1, csvFile);
			double maximo2 = normalizacion.obtenerMaximo(2, csvFile);
			double maximo3 = normalizacion.obtenerMaximo(3, csvFile);
			double maximo4 = normalizacion.obtenerMaximo(4, csvFile);
			double maximo5 = normalizacion.obtenerMaximo(5, csvFile);
			double maximo6 = normalizacion.obtenerMaximo(6, csvFile);
			double maximo7 = normalizacion.obtenerMaximo(7, csvFile);

			double minimo0 = normalizacion.obtenerMinimo(0, csvFile);
			double minimo1 = normalizacion.obtenerMinimo(1, csvFile);
			double minimo2 = normalizacion.obtenerMinimo(2, csvFile);
			double minimo3 = normalizacion.obtenerMinimo(3, csvFile);
			double minimo4 = normalizacion.obtenerMinimo(4, csvFile);
			double minimo5 = normalizacion.obtenerMinimo(5, csvFile);
			double minimo6 = normalizacion.obtenerMinimo(6, csvFile);
			double minimo7 = normalizacion.obtenerMinimo(7, csvFile);
			
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				
				if(normalizar.equals("NO")){
					Object nuevo[]= {patron[0], patron[1], patron[2], patron[3], patron[4], patron[5], patron[6], patron[7], patron[8]};
					temp.addRow(nuevo);
					
				}else if(normalizar.equals("MAX")){
					Object nuevo[]= {String.valueOf(Double.valueOf(patron[0])/maximo0), 
							 String.valueOf(Double.valueOf(patron[1])/maximo1), 
							 String.valueOf(Double.valueOf(patron[2])/maximo2),
							 String.valueOf(Double.valueOf(patron[3])/maximo3),
							 String.valueOf(Double.valueOf(patron[4])/maximo4),
						     String.valueOf(Double.valueOf(patron[5])/maximo5),
						     String.valueOf(Double.valueOf(patron[6])/maximo6),
						     String.valueOf(Double.valueOf(patron[7])/maximo7),
							 patron[8]
							 };
					temp.addRow(nuevo);
				}else if(normalizar.equals("MAX/MIN")){
					Object nuevo[]= {String.valueOf((double)(Double.valueOf(patron[0])-minimo0)/(double)(maximo0-minimo0)), 
							 String.valueOf((double)(Double.valueOf(patron[1])-minimo1)/(double)(maximo1-minimo1)), 
							 String.valueOf((double)(Double.valueOf(patron[2])-minimo2)/(double)(maximo2-minimo2)),
							 String.valueOf((double)(Double.valueOf(patron[3])-minimo3)/(double)(maximo3-minimo3)),
							 String.valueOf((double)(Double.valueOf(patron[4])-minimo4)/(double)(maximo4-minimo4)),
						     String.valueOf((double)(Double.valueOf(patron[5])-minimo5)/(double)(maximo5-minimo5)),
						     String.valueOf((double)(Double.valueOf(patron[6])-minimo6)/(double)(maximo6-minimo6)),
						     String.valueOf((double)(Double.valueOf(patron[7])-minimo7)/(double)(maximo7-minimo7)),
						     patron[8]
							 };
					temp.addRow(nuevo);
				}

			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	}
	
	private void cargarSetPrueba(){
		DefaultTableModel temp = (DefaultTableModel) this.tablaSetPruebas.getModel();
		String csvFile = "dataset/diabetes_prueba.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			double maximo0 = normalizacion.obtenerMaximo(0, csvFile);
			double maximo1 = normalizacion.obtenerMaximo(1, csvFile);
			double maximo2 = normalizacion.obtenerMaximo(2, csvFile);
			double maximo3 = normalizacion.obtenerMaximo(3, csvFile);
			double maximo4 = normalizacion.obtenerMaximo(4, csvFile);
			double maximo5 = normalizacion.obtenerMaximo(5, csvFile);
			double maximo6 = normalizacion.obtenerMaximo(6, csvFile);
			double maximo7 = normalizacion.obtenerMaximo(7, csvFile);
			
			double minimo0 = normalizacion.obtenerMinimo(0, csvFile);
			double minimo1 = normalizacion.obtenerMinimo(1, csvFile);
			double minimo2 = normalizacion.obtenerMinimo(2, csvFile);
			double minimo3 = normalizacion.obtenerMinimo(3, csvFile);
			double minimo4 = normalizacion.obtenerMinimo(4, csvFile);
			double minimo5 = normalizacion.obtenerMinimo(5, csvFile);
			double minimo6 = normalizacion.obtenerMinimo(6, csvFile);
			double minimo7 = normalizacion.obtenerMinimo(7, csvFile);
			
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				
				if(normalizar.equals("NO")){
					Object nuevo[]= {patron[0], patron[1], patron[2], patron[3], patron[4], patron[5], patron[6], patron[7], "-"};
					temp.addRow(nuevo);
					
				}else if(normalizar.equals("MAX")){
					Object nuevo[]= {String.valueOf(Double.valueOf(patron[0])/maximo0), 
							 String.valueOf(Double.valueOf(patron[1])/maximo1), 
							 String.valueOf(Double.valueOf(patron[2])/maximo2),
							 String.valueOf(Double.valueOf(patron[3])/maximo3),
							 String.valueOf(Double.valueOf(patron[4])/maximo4),
						     String.valueOf(Double.valueOf(patron[5])/maximo5),
						     String.valueOf(Double.valueOf(patron[6])/maximo6),
						     String.valueOf(Double.valueOf(patron[7])/maximo7),
							 "-"
							 };
					temp.addRow(nuevo);
				}else if(normalizar.equals("MAX/MIN")){
					Object nuevo[]= {String.valueOf((double)(Double.valueOf(patron[0])-minimo0)/(double)(maximo0-minimo0)), 
							 String.valueOf((double)(Double.valueOf(patron[1])-minimo1)/(double)(maximo1-minimo1)), 
							 String.valueOf((double)(Double.valueOf(patron[2])-minimo2)/(double)(maximo2-minimo2)),
							 String.valueOf((double)(Double.valueOf(patron[3])-minimo3)/(double)(maximo3-minimo3)),
							 String.valueOf((double)(Double.valueOf(patron[4])-minimo4)/(double)(maximo4-minimo4)),
						     String.valueOf((double)(Double.valueOf(patron[5])-minimo5)/(double)(maximo5-minimo5)),
						     String.valueOf((double)(Double.valueOf(patron[6])-minimo6)/(double)(maximo6-minimo6)),
						     String.valueOf((double)(Double.valueOf(patron[7])-minimo7)/(double)(maximo7-minimo7)),
							 "-"
							 };
					temp.addRow(nuevo);
				}
				
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv prueba");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	}
	
	
	public String[] obtenerRespuestasCorrectas(int tamaño){
		String csvFile = "dataset/diabetes_prueba_resultados.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String respuestas [] = new String [tamaño];
		int contador = 0;
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
	 
				String[] patron = line.split(cvsSplitBy);
				respuestas[contador] = patron[8];
				contador++;
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error accediendo al csv");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return respuestas;
	 
	}
}
