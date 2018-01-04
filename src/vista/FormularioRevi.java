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
import java.io.IOException;
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


import controlador.GestionDatosRevi;
import modelo.Articulo;
import modelo.Atleta;
import modelo.Autor;
import modelo.Competencia;
import modelo.Resultado;
import modelo.Revista;

public class FormularioRevi extends JInternalFrame implements ActionListener {
	
	private GestionDatosRevi gd;
	private JTextField txtNombreR;
	private JTextField txtIdioma;
	private JTextField txtTarticulo;
	private JTextField txtNombreAu;
	private JTextField txtApellidoAu;
	private JTable tblinscripciones;
	private JTextField registrotxt;
	
	public FormularioRevi(GestionDatosRevi gd) {
		this.gd = gd;
		setSize(950, 700);
		setVisible(true);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		tblinscripciones = new JTable();
		tblinscripciones.setModel(new ModelInscripcionRe());
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
		pnlIns.add(new JLabel("Nombre de la revista"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 0;
		gb.gridwidth = 2;
		gb.fill = 2;
		txtNombreR = new JTextField(20);
		pnlIns.add(txtNombreR, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 1;
		pnlIns.add(new JLabel("Idioma de la revista"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 1;
		gb.gridwidth = 2;
		gb.fill = 2;
		txtIdioma = new JTextField(20);
		pnlIns.add(txtIdioma, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 2;
		pnlIns.add(new JLabel("Titulo del articulo"), gb);


		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 2;
		gb.fill = 1;
		txtTarticulo = new JTextField(20);
		pnlIns.add(txtTarticulo, gb);
		
		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 5;
		pnlIns.add(new JLabel("Nombre del Autor "), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 5;
		//gb.gridwidth = 5;
		gb.fill = 5;
		txtNombreAu = new JTextField(20);
		pnlIns.add(txtNombreAu, gb);

		

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 7;
		pnlIns.add(new JLabel("Apellido del autor"), gb);

		

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 7;
		gb.fill = 1;
		txtApellidoAu = new JTextField(20);
		pnlIns.add(txtApellidoAu, gb);

		
		

		JPanel pnlBot = new JPanel();
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
		pnlIns.add(pnlBot);
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
			txtNombreR.setText("");
			txtIdioma.setText("");
			txtNombreAu.setText("");
			txtTarticulo.setText("");
			txtApellidoAu.setText("");
			cargarDatos();
			break;
		case"buscarb":
			buscar();
		break;
		default:
			break;
		}
	}
	
public void guardarDatosR() throws Exception {
	int reg=0;
	try{
		reg = Integer.parseInt(registrotxt.getText());
	}catch (Exception e) {
		e.printStackTrace();
		reg = 0;
	}
	
	try {
		String art= gd.buscarArticulo(txtTarticulo.getText().toString().toLowerCase());
		int a = 2/(gd.buscarRevista(txtNombreR.getText().toString().toLowerCase()));
		
	gd.newRevista(txtNombreR.getText().toString().toLowerCase(), txtIdioma.getText().toString().toLowerCase(), txtTarticulo.getText().toString().toLowerCase(),
			txtNombreAu.getText().toString().toLowerCase(), txtApellidoAu.getText().toString().toLowerCase(),reg);
	if (art==null) {

		JOptionPane.showMessageDialog(null, "Articulo ya registrado");

	} 
} catch (ArithmeticException e) {

	JOptionPane.showMessageDialog(null, "La revista que ha ingresado ya a sido registrada");

}
}

public void cargarDatos(){
	
	tblinscripciones.setModel(new ModelInscripcionRe(gd.getRevista()));
}	

public void buscar(){
	int nreg = Integer.parseInt(registrotxt.getText());


	
	try {
		Revista revista = gd.getRevistaArchivo1(nreg);
		
		txtNombreR.setText(revista.getNombre().trim());
		txtIdioma.setText(revista.getIdioma().trim());
		txtTarticulo.setText(revista.getArticulos().getTitulo().trim());
		txtNombreAu.setText(revista.getArticulos().getAutores().getNombre().trim());
		txtApellidoAu.setText(revista.getArticulos().getAutores().getApellido().trim());
		
		Revista re=new Revista();
		re.setNombre(txtNombreR.getText());
		re.setIdioma(txtIdioma.getText());
		
		
		Articulo ar=new Articulo();
		ar.setTitulo(txtTarticulo.getText());
		
	    re.setArticulos(ar);

		Autor au=new Autor();
	    au.setNombre(txtNombreAu.getText());
	    au.setApellido(txtApellidoAu.getText());
	   
		ar.setAutores(au);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(this, "Error al recuperar registro desde archivo. " +e.getMessage(), 
				"Mensaje de error", JOptionPane.ERROR_MESSAGE);
	}	
}
}
