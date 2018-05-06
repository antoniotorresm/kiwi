package aiss.model.repository;

import java.util.Collection;

public interface BibliotecaRepository {

	// Libros

	public void addLibro(Libro libro);

	public Collection<Libro> getAllLibros();

	public Libro getLibro(String libroId);

	public void updateLibro(Libro libro);

	public void deletelibro(String libroId);

	// Bibliotecas

	public void addBiblioteca(Biblioteca b);

	public Collection<Biblioteca> getAllBibliotecas();

	public Biblioteca getBiblioteca(String bibliotecaId);

	public void updateBiblioteca(Biblioteca b);

	public void deleteBiblioteca(String bibliotecaId);

	public Collection<Libro> getAll(String bibliotecaId);

	public void addLibro(String bibliotecaId, String libroId);

	public void removeLibro(String bibliotecaId, String libroId);

}
