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
import java.util.List;
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

import controlador.GestionDatosPais;
import modelo.Atleta;
import modelo.Canton;
import modelo.Competencia;
import modelo.Pais;
import modelo.Provincia;
import modelo.Resultado;

public class FormularioPais extends JInternalFrame implements ActionListener {

	private GestionDatosPais gp;
	private JTextField nombrePais;
	private JTextField txtIdioma;
	private JTextField nombreProvincia;
	private JTextField nombreCanton;
	private JTextField alcalde;
	private JTable tblinscripciones;
	private JTextField registrotxt;

	public FormularioPais(GestionDatosPais gp) {
		this.gp = gp;
		setSize(950, 700);
		setVisible(true);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		tblinscripciones = new JTable();
		tblinscripciones.setModel(new ModelInscripcionP());
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
		pnlIns.add(new JLabel("Nombre del pais"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 0;
		gb.gridwidth = 2;
		gb.fill = 2;
		nombrePais = new JTextField(20);
		pnlIns.add(nombrePais, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 1;
		pnlIns.add(new JLabel("Idioma del Pais"), gb);

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
		pnlIns.add(new JLabel("Nombre de una provincia"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 2;
		gb.fill = 1;
		nombreProvincia = new JTextField(20);
		pnlIns.add(nombreProvincia, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 5;
		pnlIns.add(new JLabel("Nombre de un canton de la provincia"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 5;
		// gb.gridwidth = 5;
		gb.fill = 5;
		nombreCanton = new JTextField(20);
		pnlIns.add(nombreCanton, gb);

		gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 7;
		pnlIns.add(new JLabel("Nombre del alcalde del canton"), gb);

		gb = new GridBagConstraints();
		gb.gridx = 1;
		gb.gridy = 7;
		gb.fill = 1;
		alcalde = new JTextField(20);
		pnlIns.add(alcalde, gb);

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

		// pnlBot.add(btnGuardar);
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

			nombrePais.setText("");
			txtIdioma.setText("");
			nombreCanton.setText("");
			nombreProvincia.setText("");
			alcalde.setText("");
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
		int reg = 0;
		try {
			reg = Integer.parseInt(registrotxt.getText());
		} catch (Exception e) {
			e.printStackTrace();
			reg = 0;
		}

		String a = null;
		int provi = 0;
		try {
			provi = 4 / (gp.buscarProvincia(nombrePais.getText().toString().toLowerCase(),
					nombreProvincia.getText().toLowerCase(), nombreCanton.getText().toString().toLowerCase()));

			a = gp.buscarCanton(nombrePais.getText().toString().toLowerCase(), nombreProvincia.getText().toLowerCase(),
					nombreCanton.getText().toString().toLowerCase());

			if (provi == 4 && a != null) {
				gp.newPais(nombrePais.getText().toString().toLowerCase(), txtIdioma.getText().toString().toLowerCase(),
						nombreProvincia.getText().toString().toLowerCase(),
						nombreCanton.getText().toString().toLowerCase(), alcalde.getText().toString().toLowerCase(),
						reg);
			}

			if (a == null) {

				JOptionPane.showMessageDialog(null, "Canton ya registrado en una provincia de este pais");
			}

		} catch (ArithmeticException e) {

			JOptionPane.showMessageDialog(null, "Provincia ya registrada en este pais");

		} catch (IOException ei) {

			JOptionPane.showMessageDialog(null, "Canton ya registrado en una provincia de este pais");
		}

	}

	public void cargarDatos() {

		tblinscripciones.setModel(new ModelInscripcionP(gp.getPais()));
	}

	public void buscar() {
		int nreg = Integer.parseInt(registrotxt.getText());

		try {
			Pais paises = gp.getAtletaArchivo1(nreg);

			nombrePais.setText(paises.getNombre().trim());
			txtIdioma.setText(paises.getIdoma().trim());
			nombreProvincia.setText(paises.getProvincias().getNombre().trim());
			nombreCanton.setText(paises.getProvincias().getCanton().getNombre().trim());
			alcalde.setText(paises.getProvincias().getCanton().getAlcalde().trim());

			Pais aux = gp.cambiarArrayD(nreg);

			aux.setNombre(nombrePais.getText());
			aux.setIdoma(txtIdioma.getText());

			Provincia ar = new Provincia();
			ar.setNombre(nombreProvincia.getText());
			
			Canton au = new Canton();
			au.setNombre(nombreCanton.getText());
			au.setAlcalde(alcalde.getText());

			ar.setCanton(au);
			aux.setProvincias(ar);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al recuperar registro desde archivo. " + e.getMessage(),
					"Mensaje de error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
