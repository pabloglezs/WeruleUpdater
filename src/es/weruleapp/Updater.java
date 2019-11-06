package es.weruleapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class Updater {

	private ArrayList<Federacion> federaciones = new ArrayList<Federacion>();
	private ArrayList<String> archivos = new ArrayList<String>();
	private ArrayList<String> carpetasNecesarias = new ArrayList<String>();
	String directorioBase;
	String directorioDestino;
	Consola consola = new Consola();
	static BufferedReader br;

	public void setFederaciones(ArrayList<Federacion> feds) {
		this.federaciones = feds;		
	}

	public void setDestino(String destino) {
		this.directorioDestino = destino;
	}

	public void setDirectorioBase(String base) {
		this.directorioBase = base;
	}

	public void getFiles(String directorio){

		File folder = new File(directorio);
		File[] listOfFiles = folder.listFiles();
		int baseDir = directorioBase.length();

		for (int i = 0; i < listOfFiles.length; i++) {
			int pathlength = listOfFiles[i].getAbsolutePath().length();
			if (listOfFiles[i].isFile()) {
				archivos.add(listOfFiles[i].getAbsolutePath().substring(baseDir+1,pathlength));
				//System.out.println(listOfFiles[i].getAbsolutePath().substring(baseDir+1,pathlength));
			} else if (listOfFiles[i].isDirectory()) {
				getFiles(listOfFiles[i].getAbsolutePath());
				carpetasNecesarias.add(listOfFiles[i].getName());
				//System.out.println(listOfFiles[i].getName());
			}
		}
	}

	public void printHeaders() {
		consola.appendText("----          INICIANDO ACTUALIZACIÓN          ----");
		consola.appendText("\n");
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	public static void copyUsingStrings(File source, File dest, Federacion federacion) throws IOException{
		String contenido = "";
		br = new BufferedReader(new FileReader(source)); 

		String st;
		while ((st = br.readLine()) != null) {
			contenido+=st+'\n';
		}
		contenido = contenido.replace("modelo", federacion.getNombre());
		//System.out.println(contenido);
		
		try (PrintWriter out = new PrintWriter(dest)) {
		    out.println(contenido);
		}
	}
	
	public int crearRestoCarpetas(String nombreCarpeta) {
		
		for(String str: carpetasNecesarias) {
			File carpeta = new File(directorioDestino+"/"+nombreCarpeta+"/"+str);
			if(!carpeta.exists()) {
				boolean result = false;
				try{
					carpeta.mkdir();
					result = true;
				} 
				catch(SecurityException se){
					return -1;
				}        
				if(result) {
					consola.appendText("Directorio creado: "+carpeta.getAbsolutePath());
				}
			}
		}
		return 0;
		
	}

	public int update() throws IOException {
		consola.setVisible(true);

		printHeaders();
		getFiles(directorioBase);

		consola.appendText("----          "+federaciones.size()+ " federaciones encontradas          ----");
		for (Federacion fede : federaciones) {
			consola.appendText("\t"+fede.getNombre());
		}
		consola.appendText("\n");

		consola.appendText("----          "+archivos.size()+ " archivos encontrados          ----");
		for (String string : archivos) {
			consola.appendText("\t"+string);
		}
		consola.appendText("\n");

		for(Federacion federacion: federaciones) {
			
			// CREAR CARPETA PARA CADA FEDERACION
			File carpeta = new File(directorioDestino+"/"+federacion.getNombre());
			if(!carpeta.exists()) {
				boolean result = false;
				try{
					carpeta.mkdir();
					result = true;
				} 
				catch(SecurityException se){
					return -1;
				}        
				if(result) {
					consola.appendText("Directorio creado: "+carpeta.getAbsolutePath());
					crearRestoCarpetas(federacion.getNombre());
				}
			}
			
			//COPIAR LOS ARCHIVOS
			for(String str: archivos) {
				File source = new File(directorioBase+"\\"+str);
				File destination = new File(carpeta.getAbsolutePath()+"\\"+str);
				//System.out.println("Copying from "+source+" to "+destination);
				consola.appendText("Copying from "+source+" to "+destination);
				consola.appendText("Copied: "+carpeta.getAbsolutePath()+"\\"+str);
				copyUsingStrings(source, destination,federacion);
			}
			
		}
		consola.appendText("\n\n");
		consola.appendText("--------------             FIN             --------------");
		consola.appendText("\n\n");
		
		return 0;
	}

}
