package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.GestionDatosAtle;
import controlador.GestionDatosRevi;
import modelo.Atleta;
import modelo.Revista;


public class LeerAtle extends JInternalFrame implements ActionListener {

	private GestionDatosAtle ga;

	private JTextArea txtaInscripciones;

	public LeerAtle(GestionDatosAtle ga) {

		this.ga = ga;
		setSize(750, 400);
		
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));

		

		txtaInscripciones = new JTextArea(50, 50);
		JPanel pnlBusqueda = new JPanel();
		pnlBusqueda.add(txtaInscripciones);


		p.add(pnlBusqueda);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setActionCommand("btnCargar");
		btnCargar.addActionListener(this);

		c.add(p, BorderLayout.CENTER);
		c.add(btnCargar, BorderLayout.SOUTH);

	}



	public void cargar() throws Exception {

	
		
		List<Atleta> personas = ga.getAtletas();
		txtaInscripciones.setText("");
		for(int i=0; i<personas.size(); i++){
			Atleta persona = personas.get(i);
			System.out.println(persona.getNombre() + " " + persona.getApellido());
			txtaInscripciones.append(persona+"\n");
		}

	}

	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		switch (comando) {
		case "btnCargar":
			try {
				cargar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			;
			break;
		default:
			break;
		}
	}

}
