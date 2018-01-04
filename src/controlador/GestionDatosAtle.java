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

import modelo.Competencia;

import modelo.Resultado;
import utilidades.Texto;
import modelo.Atleta;

public class GestionDatosAtle {
	private List<Atleta> atletas;
	private List<Competencia> competencias;
	private List<Resultado> resultados;
private String pathPersona="Datos/Atleta.dat";
private int TAMANO_REG = 47;
//estamos ajsja
public GestionDatosAtle(List<Atleta> atletas, List<Competencia> competencias, List<Resultado> resultados, String pathPersona) {
	super();
	this.atletas = atletas;
	this.competencias = competencias;
	this.resultados = resultados;
	this.pathPersona = pathPersona;
}

public GestionDatosAtle() throws Exception {
	atletas = new ArrayList<Atleta>();
	competencias = new ArrayList<Competencia>();
	resultados = new ArrayList<Resultado>();
	cargarDatosArchivo();
}

public void newAtleta(String nombreA,String apellidoA,String edad,String numeroCo,String lugarPos,int nreg) throws Exception{
	Atleta re=new Atleta();
   re.setNombre(nombreA);
   re.setApellido(apellidoA);
	re.setEdad(edad);
		
	Competencia ar=new Competencia();
	ar.setNumero(numeroCo);
	competencias.add(ar);
    re.setCompetencias(ar);

    Resultado au=new Resultado();
  au.setPrimerLug(lugarPos);
    resultados.add(au);
	ar.setResultados(au);

	
	try {
		if(nreg>0) {
			System.out.println("Si entro ");
			editarAtletaArchivo(nreg, re);
			cargarDatosArchivo();
		}else{
			guardarAtletaArchivo(re);
			atletas.add(re);
		}
	} catch (IOException e) {
		e.printStackTrace();
		throw new Exception("Error al guardar datos, error en archivo");
	}
	
}
private void  cargarDatosArchivo() throws Exception {
	atletas = getAtletasArchivo();
}

public Atleta getAtletaArchivo1(int nreg) throws IOException {
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	int pos = (nreg -1) *  TAMANO_REG;
	out.seek(pos);
	
	String nombre = out.readUTF();
	String apellido = out.readUTF();
	String edad = out.readUTF();
	String numero = out.readUTF();
	String lugar = out.readUTF();
	
	Atleta p = new Atleta();
	p.setNombre(nombre);
	p.setApellido(apellido);
	p.setEdad(edad);
	
	Competencia ar=new Competencia();
	ar.setNumero(numero);
	
	 Resultado au=new Resultado();
	 au.setPrimerLug(lugar);
	 
	 ar.setResultados(au);
	 p.setCompetencias(ar);
	 
		
	return p;
}

public Atleta cambiarArrayD(int num){
	for (int i = 0; i < atletas.size(); i++) {
		Atleta car = atletas.get(i);
		if (i == num) {
			return car;
		}
	}
	return null;
	
}

private List<Atleta> getAtletasArchivo() throws IOException {
	List<Atleta> atletas = new ArrayList<>();
	RandomAccessFile out = new RandomAccessFile(pathPersona, "rw");
	try {
		while(true) {
			String nombre = out.readUTF();
			String apellido = out.readUTF();
			String edad = out.readUTF();
			String numero = out.readUTF();
			String lugar = out.readUTF();
			
			Atleta p = new Atleta();
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setEdad(edad);
			
			Competencia ar=new Competencia();
			ar.setNumero(numero);
			
			 Resultado au=new Resultado();
			 au.setPrimerLug(lugar);
			 
			 ar.setResultados(au);
			 p.setCompetencias(ar);
			 
			atletas.add(p);
			
		}
	}catch(EOFException e){
		System.out.println("Fin de archivo");
	}
	return atletas;

}

private void editarAtletaArchivo(int nreg, Atleta a) throws IOException {
	 RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
	int pos = (nreg -1) *  TAMANO_REG;
	 escritura.seek(pos);
	  escritura.writeUTF(Texto.tamano(a.getNombre(),10));
	  escritura.writeUTF(Texto.tamano(a.getApellido(),10));
	  escritura.writeUTF(Texto.tamano(a.getEdad(),3));
	  escritura.writeUTF(Texto.tamano(a.getCompetencias().getNumero(),4));
	  escritura.writeUTF(Texto.tamano(a.getCompetencias().getResultados().getPrimerLug(),10));
	  
	  escritura.close();

}

public void guardarAtletaArchivo(Atleta a) throws IOException{
	  RandomAccessFile escritura = new RandomAccessFile (pathPersona,"rw");
		 escritura.seek(escritura.length());
		  escritura.writeUTF(Texto.tamano(a.getNombre(),10));
		  escritura.writeUTF(Texto.tamano(a.getApellido(),10));
		  escritura.writeUTF(Texto.tamano(a.getEdad(),3));
		  escritura.writeUTF(Texto.tamano(a.getCompetencias().getNumero(),4));
		  escritura.writeUTF(Texto.tamano(a.getCompetencias().getResultados().getPrimerLug(),10));
		  
		  escritura.close();
	
	
}


public void leerArchivos()  {
	

}
public int buscarAtleta(String numeroJug){
	
		for (int i = 0; i < competencias.size(); i++) {
			Competencia car = competencias.get(i);
			if (car.getNumero().equals(numeroJug)) {
						return 0;
			}
		}
		return 1;
}
public List<Atleta> getAtletas(){
	return atletas;
	
}



}
