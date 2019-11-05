package es.weruleapp;

public class Federacion {
	
	private String nombre;
	private String logoConLetras;
	private String logoSinLetras;
	private String atras;
	private String color;
	
	public Federacion(String nombre, String logoConLetras, String logoSinLetras, String atras, String color) {
		this.nombre = nombre;
		this.logoConLetras = logoConLetras;
		this.logoSinLetras = logoSinLetras;
		this.atras = atras;
		this.color = color;		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLogoConLetras() {
		return logoConLetras;
	}
	public void setLogoConLetras(String logoConLetras) {
		this.logoConLetras = logoConLetras;
	}
	public String getLogoSinLetras() {
		return logoSinLetras;
	}
	public void setLogoSinLetras(String logoSinLetras) {
		this.logoSinLetras = logoSinLetras;
	}
	public String getAtras() {
		return atras;
	}
	public void setAtras(String atras) {
		this.atras = atras;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return "Federacion [nombre=" + nombre + ", logoConLetras=" + logoConLetras + ", logoSinLetras=" + logoSinLetras
				+ ", atras=" + atras + ", color=" + color + "]";
	}
		

}
