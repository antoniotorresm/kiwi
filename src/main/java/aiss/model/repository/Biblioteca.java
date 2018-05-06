package aiss.model.repository;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

	public static Biblioteca create(String nombre, String localizacion, String organizador, List<Libro> libros) {
		return new Biblioteca(nombre, localizacion, organizador, libros);
	}

	public static Biblioteca create() {
		return new Biblioteca();
	}

	public static Biblioteca create(String id, String nombre, String localizacion, String organizador,
			List<Libro> libros) {
		return new Biblioteca(id, nombre, localizacion, organizador, libros);
	}

	private String id;
	private String nombre;
	private String localizacion;
	private String organizador;
	private List<Libro> libros;

	private Biblioteca() {
		super();
	}

	private Biblioteca(String nombre, String localizacion, String organizador, List<Libro> libros) {
		super();
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.organizador = organizador;
		this.libros = libros;
	}

	private Biblioteca(String id, String nombre, String localizacion, String organizador, List<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.organizador = organizador;
		this.libros = libros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libros == null) ? 0 : libros.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biblioteca other = (Biblioteca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libros == null) {
			if (other.libros != null)
				return false;
		} else if (!libros.equals(other.libros))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public Libro getLibro(String id) {
		Libro res = null;
		if (libros != null) {
			for (Libro libro : libros)
				if (libro.getId().equals(id)) {
					res = libro;
					break;
				}
		}
		return res;

	}

	public void addLibro(Libro libro) {
		if (libros == null)
			libros = new ArrayList<Libro>();
		libros.add(libro);
	}

	public void deleteLibro(Libro libro) {
		libros.remove(libro);
	}

	public void deleteLibro(String id) {
		Libro libro = getLibro(id);
		if (libro != null)
			libros.remove(libro);
	}

	@Override
	public String toString() {
		return "Biblioteca [nombre=" + nombre + ", localizacion=" + localizacion + ", organizador=" + organizador
				+ ", libros=" + libros + "]";
	}

}
