package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import controlador.GestionDatosAtle;
import modelo.Atleta;
import modelo.Competencia;
import modelo.Resultado;
import utilidades.Texto;

public class FormularioAtl extends JInternalFrame implements ActionListener {
	
	private GestionDatosAtle ga;
	private JTextField nombreAtl;
	private JTextField apellidoAtl;
	private JTextField edad;
	private JTextField numeroCompetencia;
	private JTextField primerlugar;
	private JTextField registrotxt;
	private JTable tblinscripciones;
	private String pathPersona="Datos/Atleta.dat";

	public FormularioAtl(GestionDatosAtle ga)  {
		this.ga = ga;
		setSize(950, 700);
		setVisible(true);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		tblinscripciones = new JTable();
		tblinscripciones.setModel(new ModelInscripcionAtle());
		JScrollPane scrollinscripciones = new JScrollPane(tblinscripciones);
		
		JPanel scrollPane = new JPanel();
		scrollPane.setLayout(new GridLayout(1, 1));
		scrollPane.add(scrollinscripciones);

		JPanel pnlIns = new JPanel();
		pnlIns.setLayout(new GridBagLayout());
		pnlIns.setBorder(BorderFactory.createTitledBorder("Ingresar datos"));

		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 0;
		pnlIns.add(new JLabel("Nombre del atleta"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 0;
		gb.fill = 1;
		nombreAtl = new JTextField(20);
		pnlIns.add(nombreAtl, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 1;
		pnlIns.add(new JLabel("Apellido del atleta"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 1;
		gb.fill = 1;
		apellidoAtl = new JTextField(20);
		pnlIns.add(apellidoAtl, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 2;
		pnlIns.add(new JLabel("Numero de competencia"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 2;
		//gb.gridwidth = 5;
		gb.fill = 1;
		numeroCompetencia = new JTextField(20);
		pnlIns.add(numeroCompetencia, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 5;
		pnlIns.add(new JLabel("Edad"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 5;
		gb.fill = 1;
		edad = new JTextField(20);
		pnlIns.add(edad, gb);

		

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 7;
		pnlIns.add(new JLabel("Lugar que llego"), gb);

		

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 7;
		gb.fill = 1;
		primerlugar = new JTextField(20);
		pnlIns.add(primerlugar, gb);

		
		

		//JPanel pnlBot = new JPanel();
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("btnGuardar");
		
		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 9;
		gb.fill = 1;
		pnlIns.add(btnGuardar, gb);
		
		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 10;
		pnlIns.add(new JLabel("#Registro:"), gb);

		

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 10;
		gb.fill = 1;
		registrotxt = new JTextField(20);
		pnlIns.add(registrotxt, gb);
		
		JButton buscar = new JButton("BUSCAR");
		buscar.addActionListener(this);
		buscar.setActionCommand("buscarb");
		
		gb = new GridBagConstraints();
		gb.gridx = 2;
		gb.gridy = 10;
		gb.fill = 1;
		pnlIns.add(buscar, gb);
		
		//pnlBot.add(btnGuardar);
		//pnlIns.add(pnlBot);
		c.add(pnlIns, BorderLayout.CENTER);
		c.add(scrollPane, BorderLayout.SOUTH);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		switch (comando) {
		case "btnGuardar":
			try {
				guardarDatosR();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			nombreAtl.setText("");
			apellidoAtl.setText("");
			numeroCompetencia.setText("");
			edad.setText("");
			primerlugar.setText("");
			cargarDatos();
			
			break;
		case"buscarb":
			buscar();
		break;
		default:
			break;
			
		}
	}
	
public void guardarDatosR() throws Exception  {
	int reg=0;
	try{
		reg = Integer.parseInt(registrotxt.getText());
	}catch (Exception e) {
		e.printStackTrace();
		reg = 0;
	}
	
	
	try {
		int a = 2/(ga.buscarAtleta(numeroCompetencia.getText().toString().toLowerCase()));
		
		ga.newAtleta(nombreAtl.getText().toString().toLowerCase(), apellidoAtl.getText().toString().toLowerCase(), edad.getText().toString().toLowerCase(),
			numeroCompetencia.getText().toString().toLowerCase(), primerlugar.getText().toString().toLowerCase(),reg);
		cargarDatos();
		
	} catch (ArithmeticException e) {

		JOptionPane.showMessageDialog(null, "Competidor ya registrado o el numero ya esta ocupado");

	}
}

public void cargarDatos(){
	
	tblinscripciones.setModel(new ModelInscripcionAtle(ga.getAtletas()));
}	
public void buscar(){
	int nreg = Integer.parseInt(registrotxt.getText());

	try {
		Atleta atleta = ga.getAtletaArchivo1(nreg);
		
		nombreAtl.setText(atleta.getNombre().trim());
		apellidoAtl.setText(atleta.getApellido().trim());
		numeroCompetencia.setText(atleta.getCompetencias().getNumero().trim());
		edad.setText(atleta.getEdad().trim());
		primerlugar.setText(atleta.getCompetencias().getResultados().getPrimerLug().trim());
		
		
		Atleta aux=ga.cambiarArrayD(nreg);
		aux.setNombre(nombreAtl.getText());
		aux.setApellido(apellidoAtl.getText());
		aux.setEdad(edad.getText());
		
		Competencia ar=new Competencia();
		ar.setNumero(numeroCompetencia.getText());
		
		 Resultado au=new Resultado();
		 au.setPrimerLug(primerlugar.getText());
		 
		 ar.setResultados(au);
		 aux.setCompetencias(ar);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(this, "Error al recuperar registro desde archivo. " +e.getMessage(), 
				"Mensaje de error", JOptionPane.ERROR_MESSAGE);
	}
}

}
