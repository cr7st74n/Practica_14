package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.GestionDatosAtle;

import controlador.GestionDatosPais;
import controlador.GestionDatosRevi;

public class MiVentana extends JFrame implements ActionListener {

	private JDesktopPane escritorio;
	private GestionDatosRevi gd;
	private GestionDatosPais gp;

	private GestionDatosAtle ga;
	//private GestionDatosFich gficha;
	//private GestionDatosMedi gm;
	JMenu mnInscripciones;
	JMenuItem Formulario;
	JMenuItem listarInscripciones;
	
	JMenu mnBusqueda;
	JMenuItem BusquedaEstudiante;
	JMenuItem mnCarrera;
	
	JMenu mnRevista;
	JMenuItem agregarR;
	JMenuItem listarR;
	
	JMenu fichaInscripcione;

	JMenuItem ingresarFichas;
	JMenuItem listarfichas;
	
	JMenu medico;
	JMenuItem ingresarMedico;
	JMenuItem listarMedico;
	
	JMenu Atleta;
	JMenuItem ingresarAtleta;
	JMenuItem listarAtleta;
	
	public MiVentana() throws Exception  {
		this.gd = new GestionDatosRevi();
		this.gp = new GestionDatosPais();
		
		this.ga = new GestionDatosAtle();
		initComponents();
	}
	
	private void initComponents() {

		setSize(965,660);
		setTitle("Gestion de Datos");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		escritorio = new JDesktopPane();
		c.add(escritorio, BorderLayout.CENTER);
		
		JMenuBar barMenu = new JMenuBar();
		
		mnInscripciones = new JMenu("Pais");
		Formulario = new JMenuItem("Ingresar pais");
		Formulario.addActionListener(this);
		Formulario.setActionCommand("mnFor");
		
		listarInscripciones = new JMenuItem("Listar pais");
		listarInscripciones.addActionListener(this);
		listarInscripciones.setActionCommand("mnListarP");
		mnInscripciones.add(Formulario);
		mnInscripciones.add(listarInscripciones);

		mnRevista  = new JMenu("Revista");
		agregarR = new JMenuItem("Ingresar Revista");
		agregarR.addActionListener(this);
		agregarR.setActionCommand("mnRev");
		listarR = new JMenuItem("Listar Revista");
		listarR.addActionListener(this);
		listarR.setActionCommand("mnListarRev");
		mnRevista.add(agregarR);
		mnRevista.add(listarR);

		Atleta  = new JMenu("Atleta");
		ingresarAtleta = new JMenuItem("Agregar Atleta");
		ingresarAtleta.addActionListener(this);
		ingresarAtleta.setActionCommand("mnAgAt");
		listarAtleta = new JMenuItem("Listar Atleta");
		listarAtleta.addActionListener(this);
		listarAtleta.setActionCommand("mnListarAt");
		Atleta.add(ingresarAtleta);
		Atleta.add(listarAtleta);
		
		barMenu.add(mnRevista);
		barMenu.add(mnInscripciones);
		barMenu.add(Atleta);
		
		setJMenuBar(barMenu);
		
		JOptionPane.showMessageDialog(null, "* Bienvenido estimado Usuario*\n |al siguiente programa|");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FormularioRevi f = new FormularioRevi(gd);
		LeerRevi be = new LeerRevi(gd);
		
		FormularioPais p=new FormularioPais(gp);
		LeerPais pl = new LeerPais(gp);
		
		
		
		FormularioAtl a=new FormularioAtl(ga);
		LeerAtle la = new LeerAtle(ga);
		
		
		
		
		
		String comando = e.getActionCommand();
		
		switch(comando){
		case "mnRev":
			escritorio.removeAll();
			f.setVisible(true);
			escritorio.add(f);
			break;
		case "mnListarRev":
		escritorio.removeAll();
		be.setVisible(true);
		escritorio.add(be);
			break;
		case "mnFor":
			escritorio.removeAll();
			p.setVisible(true);
			escritorio.add(p);
			break;
			
		case "mnListarP":
			escritorio.removeAll();
			pl.setVisible(true);
			escritorio.add(pl);
			break;
			
		
			
		case "mnAgAt":
			escritorio.removeAll();
			a.setVisible(true);
			escritorio.add(a);
			break;
			
		case "mnListarAt":
			escritorio.removeAll();
			la.setVisible(true);
			escritorio.add(la);
			break;
		default:
			break;
		}
		
	}

}
