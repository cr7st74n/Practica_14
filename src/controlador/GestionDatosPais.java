package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import modelo.Atleta;
import modelo.Canton;

import modelo.Competencia;
import modelo.Pais;
import modelo.Provincia;
import modelo.Resultado;
import utilidades.Texto;


public class GestionDatosPais {
	private List<Pais> paises;
	private List<Provincia> provincias;
	private List<Canton> cantones;
private String pathPersona="Datos/Pais.dat";
private int TAMANO_REG = 70;
public GestionDatosPais(List<Pais> paises, List<Provincia> provincias, List<Canton> cantones, String pathPersona) {
	super();
	this.paises = paises;
	this.provincias = provincias;
	this.cantones = cantones;
	this.pathPersona = pathPersona;
}

public GestionDatosPais() {
	paises = new ArrayList<Pais>();
	provincias = new ArrayList<Provincia>();
	cantones = new ArrayList<Canton>();
}

public void newPais(String pais,String idioma,String provincia,String canton,String alcalde,int nreg) throws Exception{
	Pais re=new Pais();
re.setNombre(pais);
	re.setIdoma(idioma);
		
	Provincia ar=new Provincia();
	ar.setNombre(provincia);;
	provincias.add(ar);
    re.setProvincias(ar);

    Canton au=new Canton();
    au.setNombre(canton);
    au.setAlcalde(alcalde);
    cantones.add(au);
	ar.setCanton(au);

	
	try {
		if(nreg>0) {
			editarAtletaArchivo(nreg, re);
			cargarDatosArchivo();
		}else{
			guardarAtletaArchivo(re);
			paises.add(re);
		}
	} catch (IOException e) {
		e.printStackTrace();
		throw new Exception("Error al guardar datos, error en archivo");
	}

}

private void guardarAtletaArchivo(Pais re) throws IOException {
	 RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
	 escritura.seek(escritura.length());
	 escritura.writeUTF(Texto.tamano(re.getNombre(),10));
	  escritura.writeUTF(Texto.tamano(re.getIdoma(),10));
	  escritura.writeUTF(Texto.tamano(re.getProvincias().getNombre(),3));
	  escritura.writeUTF(Texto.tamano(re.getProvincias().getCanton().getNombre(),4));
	  escritura.writeUTF(Texto.tamano(re.getProvincias().getCanton().getAlcalde(),10));
	  
	  escritura.close();

	
}

private void cargarDatosArchivo() throws IOException {
	paises = getPaisesArchivo();
	
}

private List<Pais> getPaisesArchivo() throws IOException {
	List<Pais> paises = new ArrayList<>();
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	try {
		while(true) {
			String nombre = out.readUTF();
			String idioma = out.readUTF();
			String nombrep = out.readUTF();
			String nombrec = out.readUTF();
			String alcalde = out.readUTF();
			
			Pais re=new Pais();
			re.setNombre(nombre);
				re.setIdoma(idioma);
					
				Provincia ar=new Provincia();
				ar.setNombre(nombrep);;
				
			    re.setProvincias(ar);

			    Canton au=new Canton();
			    au.setNombre(nombrec);
			    au.setAlcalde(alcalde);
			   
				ar.setCanton(au);
			paises.add(re);
			
		}
	}catch(EOFException e){
		System.out.println("Fin de archivo");
	}
	return paises;

}

private void editarAtletaArchivo(int nreg, Pais p) throws IOException {
	 RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
		int pos = (nreg -1) *  TAMANO_REG;
		 escritura.seek(pos);
		  escritura.writeUTF(Texto.tamano(p.getNombre(),10));
		  escritura.writeUTF(Texto.tamano(p.getIdoma(),10));
		  escritura.writeUTF(Texto.tamano(p.getProvincias().getNombre(),10));
		  escritura.writeUTF(Texto.tamano(p.getProvincias().getCanton().getNombre(),10));
		  escritura.writeUTF(Texto.tamano(p.getProvincias().getCanton().getAlcalde(),20));
		  
		  escritura.close();
}

public String leerArchivos() throws Exception {
	
	FileInputStream archivoLectura=null;
	DataInputStream entrada=null;
	try{
	    String ruta=pathPersona;
	    archivoLectura=new FileInputStream(ruta);
	    entrada = new DataInputStream(archivoLectura);
	    

	    while(true){
	    	
	    	String nom=entrada.readUTF();
	    	String nom1=entrada.readUTF();
	    	String nom2=entrada.readUTF();
	    	String nom3=entrada.readUTF();
	    	String nom4=entrada.readUTF();

	    	
	    	System.out.print(nom);
	    	System.out.print(nom1);
	    	System.out.print(nom2);
	    	System.out.print(nom3);
	    	System.out.print(nom4);
	    	
	    	String imp= nom+ " ; "+nom1+" ; "+nom2+" ; "+nom3+" ; "+nom4;
	    	imp.split(";");
	    	
	    	return imp;
	   }
	}catch(Exception e1){
	    e1.printStackTrace();
	}finally{
	    entrada.close();
	}
	
	return null;
}


public int buscarProvincia(String nombrep,String nombre,String nombreC) {
int valorC = 0;
for (int i = 0; i < paises.size(); i++) {
	Pais pais = paises.get(i);
	if (pais.getNombre().equals(nombrep)) {
		for (int i1 = 0; i1 < provincias.size(); i1++) {
			Provincia car = provincias.get(i1);
			if (car.getNombre().equals(nombre)) {
				
				return valorC = 0;
		
				}
		}
   }
}

		return valorC=1;
	}

	public String buscarCanton(String nombrep, String nombre, String nombreC) {
		String valorC = "tRue";
		for (int i1 = 0; i1 < paises.size(); i1++) {
			Pais pais = paises.get(i1);
			
					for (int i = 0; i < cantones.size(); i++) {
						Canton canton = cantones.get(i);
						if (canton.getNombre().equals(nombreC)) {
							valorC = null;
						}
					}
		}
		return valorC;

	}

public List<Pais> getPais(){
	return paises;
	
}

public Pais getAtletaArchivo1(int nreg) throws IOException {
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	int pos = (nreg -1) *  TAMANO_REG;
	out.seek(pos);
	
	String nombre = out.readUTF();
	String idioma = out.readUTF();
	String nombrep = out.readUTF();
	String nombrec = out.readUTF();
	String alcalde = out.readUTF();
	
	Pais re=new Pais();
	re.setNombre(nombre);
		re.setIdoma(idioma);
			
		Provincia ar=new Provincia();
		ar.setNombre(nombrep);;
		
	    re.setProvincias(ar);

	    Canton au=new Canton();
	    au.setNombre(nombrec);
	    au.setAlcalde(alcalde);
	   
		ar.setCanton(au);

	 
		
	return re;
}

public Pais cambiarArrayD(int nreg) {
	for (int i = 0; i < paises.size(); i++) {
		Pais car = paises.get(i);
		if (i == nreg) {
			return car;
		}
	}
	return null;
}

}
