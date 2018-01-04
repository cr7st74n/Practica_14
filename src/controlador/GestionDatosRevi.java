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

import modelo.Articulo;
import modelo.Atleta;
import modelo.Autor;
import modelo.Competencia;
import modelo.Pais;
import modelo.Provincia;
import modelo.Resultado;
import modelo.Revista;
import utilidades.Texto;

public class GestionDatosRevi {
	private List<Revista> revistas;
	private List<Autor> autores;
	private List<Articulo> articulos;
	private int TAMANO_REG = 47;
private String pathPersona="Datos/Revistas.dat";
	

public GestionDatosRevi(List<Revista> revistas, List<Autor> autores, List<Articulo> articulos, String pathPersona) {
	super();
	this.revistas = revistas;
	this.autores = autores;
	this.articulos = articulos;
	this.pathPersona = pathPersona;
}

public GestionDatosRevi() {
	revistas = new ArrayList<Revista>();
	autores = new ArrayList<Autor>();
	articulos = new ArrayList<Articulo>();
}


public void newRevista(String nombreR,String idioma,String titulo,String nombreAu,String apellidoAu,int nreg) throws Exception{
	Revista re=new Revista();
	re.setNombre(nombreR);
	re.setIdioma(idioma);
	
	
	Articulo ar=new Articulo();
	ar.setTitulo(titulo);
	articulos.add(ar);
    re.setArticulos(ar);

	Autor au=new Autor();
    au.setNombre(nombreAu);
    au.setApellido(apellidoAu);
    autores.add(au);
	ar.setAutores(au);
	
	
	
	try {
		if(nreg>0) {
			System.out.println("Si entro ");
			editarRevistaArchivo(nreg, re);
			cargarDatosArchivo();
		}else{
			guardarRevistaArchivo(re);
			revistas.add(re);
		}
	} catch (IOException e) {
		e.printStackTrace();
		throw new Exception("Error al guardar datos, error en archivo");
	}
	
}

private void guardarRevistaArchivo(Revista a) throws IOException {
	 RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
	 escritura.seek(escritura.length());
	  escritura.writeUTF(Texto.tamano(a.getNombre(),10));
	  escritura.writeUTF(Texto.tamano(a.getIdioma(),10));
	  escritura.writeUTF(Texto.tamano(a.getArticulos().getTitulo(),3));
	  escritura.writeUTF(Texto.tamano(a.getArticulos().getAutores().getNombre(),4));
	  escritura.writeUTF(Texto.tamano(a.getArticulos().getAutores().getApellido(),10));
	  
	  escritura.close();
	
}

private void cargarDatosArchivo() throws IOException {
revistas = getRevistasArchivo();
	
}

private List<Revista> getRevistasArchivo() throws IOException {
	List<Revista> revistas = new ArrayList<>();
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	try {
		while(true) {
			String nombreR = out.readUTF();
			String idioma = out.readUTF();
			String titulo = out.readUTF();
			String nombreAu = out.readUTF();
			String apellidoAu = out.readUTF();
			
			Revista re=new Revista();
			re.setNombre(nombreR);
			re.setIdioma(idioma);
			
			
			Articulo ar=new Articulo();
			ar.setTitulo(titulo);
			articulos.add(ar);
		    re.setArticulos(ar);
		   

			Autor au=new Autor();
		    au.setNombre(nombreAu);
		    au.setApellido(apellidoAu);
		    autores.add(au);
			ar.setAutores(au);
			
			revistas.add(re);
			
		}
	}catch(EOFException e){
		System.out.println("Fin de archivo");
	}
	return revistas;

}

private void editarRevistaArchivo(int nreg, Revista a) throws IOException {
	 RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
		int pos = (nreg -1) *  TAMANO_REG;
		 escritura.seek(pos);
		  escritura.writeUTF(Texto.tamano(a.getNombre(),10));
		  escritura.writeUTF(Texto.tamano(a.getIdioma(),10));
		  escritura.writeUTF(Texto.tamano(a.getArticulos().getTitulo(),3));
		  escritura.writeUTF(Texto.tamano(a.getArticulos().getAutores().getNombre(),4));
		  escritura.writeUTF(Texto.tamano(a.getArticulos().getAutores().getApellido(),10));
		  
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
public int buscarRevista(String revistaN){
	
	for (int i = 0; i < revistas.size(); i++) {
		Revista car = revistas.get(i);
		if (car.getNombre().equals(revistaN)) {
					return 0;
		}
	}
	return 1;
}
public String buscarArticulo(String nombreAr) {
String valorC="tRue";
		for (int i = 0; i < articulos.size(); i++) {
			Articulo ar = articulos.get(i);
			if (ar.getTitulo().equals(nombreAr)) {
				valorC = null;
			}
		}
		return valorC;
	}
public List<Revista> getRevista(){
	return revistas;
	
}

public Revista cambiarArrayD(int nreg) {
	for (int i = 0; i < revistas.size(); i++) {
		Revista car = revistas.get(i);
		if (i ==  nreg) {
			return car;
		}
	}
	return null;
}

public Revista getRevistaArchivo1(int nreg) throws IOException {
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	int pos = (nreg -1) *  TAMANO_REG;
	out.seek(pos);
	String nombreR = out.readUTF();
	String idioma = out.readUTF();
	String titulo = out.readUTF();
	String nombreAu = out.readUTF();
	String apellidoAu = out.readUTF();
	
	
	Revista re=new Revista();
	re.setNombre(nombreR);
	re.setIdioma(idioma);
	
	
	Articulo ar=new Articulo();
	ar.setTitulo(titulo);

    re.setArticulos(ar);
   

	Autor au=new Autor();
    au.setNombre(nombreAu);
    au.setApellido(apellidoAu);
 
	ar.setAutores(au);
	

	 
		
	return re;
}

}