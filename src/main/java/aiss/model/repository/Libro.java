package aiss.model.repository;

public class Libro {

	public static Libro create(String titulo, String sinopsis, String autor, String isbn, String anyoPublicacion,
			Integer numPag) {
		return new Libro(titulo, sinopsis, autor, isbn, anyoPublicacion, numPag);
	}

	public static Libro create() {
		return new Libro();
	}

	public static Libro create(String id, String titulo, String sinopsis, String autor, String isbn,
			String anyoPublicacion, Integer numPag) {
		return new Libro(id, titulo, sinopsis, autor, isbn, anyoPublicacion, numPag);
	}

	private String id;
	private String titulo;
	private String sinopsis;
	private String autor;
	private String isbn;
	private String anyoPublicacion;
	private Integer numPag;

	private Libro() {
		super();
	}

	private Libro(String titulo, String sinopsis, String autor, String isbn, String anyoPublicacion, Integer numPag) {
		super();
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.autor = autor;
		this.isbn = isbn;
		this.anyoPublicacion = anyoPublicacion;
		this.numPag = numPag;
	}

	
	
	private Libro(String id, String titulo, String sinopsis, String autor, String isbn, String anyoPublicacion,
			Integer numPag) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.autor = autor;
		this.isbn = isbn;
		this.anyoPublicacion = anyoPublicacion;
		this.numPag = numPag;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public void setAnyoPublicacion(String anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}

	public Integer getNumPag() {
		return numPag;
	}

	public void setNumPag(Integer numPag) {
		this.numPag = numPag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", sinopsis=" + sinopsis + ", autor=" + autor + ", isbn=" + isbn
				+ ", anyoPublicacion=" + anyoPublicacion + ", numPag=" + numPag + "]";
	}

}
