package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapBibliotecasRepository implements BibliotecaRepository {

	Map<String, Biblioteca> bibliotecaMap;
	Map<String, Libro> libroMap;
	private static MapBibliotecasRepository instance = null;
	private static int index = 0;// Index to create bibliotecas and libros' identifiers.

	public static MapBibliotecasRepository getInstance() {

		if (instance == null) {
			instance = new MapBibliotecasRepository();
			instance.init();
		}

		return instance;
	}

	public void init() {
		bibliotecaMap = new HashMap<String, Biblioteca>();
		libroMap = new HashMap<String, Libro>();

		// Crear libros

		Libro quijote = Libro.create();
		quijote.setTitulo("Don Quijote de la Mancha");
		quijote.setAutor("Miguel de Cervantes");
		quijote.setSinopsis("");
		quijote.setNumPag(1040);
		quijote.setIsbn("9788423349647");
		quijote.setAnyoPublicacion("1605");

		Libro tale2cities = Libro.create();
		tale2cities.setTitulo("Historia de dos ciudades");
		tale2cities.setAutor("Charles Dickens");
		tale2cities.setSinopsis(
				"El Londres pacifico pero grotesco del rey Jorge II y el Paris clamoroso y ensangrentado de la Revolucion Francesa son las dos ciudades sobre cuyo fondo se escribe esta inolvidable historia de intriga apasionante.");
		tale2cities.setNumPag(488);
		tale2cities.setIsbn("9788484287285");
		tale2cities.setAnyoPublicacion("1859");

		Libro principito = Libro.create();
		principito.setTitulo("El principito");
		principito.setAutor("Antoine de Saint-Exupery");
		principito.setSinopsis("");
		principito.setNumPag(96);
		principito.setIsbn("9788478887194");
		principito.setAnyoPublicacion("1943");

		Libro irobot = Libro.create();
		irobot.setTitulo("Yo, robot");
		irobot.setAutor("Isaac Asimov");
		irobot.setSinopsis("");
		irobot.setNumPag(328);
		irobot.setIsbn("9788435018364");
		irobot.setAnyoPublicacion("1950");

		Libro g1984 = Libro.create();
		g1984.setTitulo("1984");
		g1984.setAutor("George Orwell");
		g1984.setSinopsis("");
		g1984.setNumPag(336);
		g1984.setIsbn("9788423334971");
		g1984.setAnyoPublicacion("1948");

		// Crear bibliotecas

		Biblioteca etsii = Biblioteca.create();
		etsii.setNombre("Biblioteca ETSII");
		etsii.setLocalizacion("Escuela Técnica Superior Ingeniería Informática");
		etsii.setOrganizador("Universidad de Sevilla");
		addBiblioteca(etsii);

		Biblioteca crai = Biblioteca.create();
		crai.setNombre("Biblioteca CRAI");
		crai.setLocalizacion("CRAI Antonio de Ulloa");
		crai.setOrganizador("Universidad de Sevilla");
		addBiblioteca(crai);

		// Add libros a bibliotecas
		addLibro(etsii.getId(), irobot.getId());
		addLibro(etsii.getId(), g1984.getId());
		addLibro(etsii.getId(), tale2cities.getId());
		addLibro(crai.getId(), quijote.getId());
		addLibro(crai.getId(), principito.getId());
		addLibro(crai.getId(), principito.getId());
	}

	@Override
	public void addLibro(Libro libro) {
		String id = "l" + index++;
		libro.setId(id);
		libroMap.put(id, libro);

	}

	@Override
	public Collection<Libro> getAllLibros() {
		return libroMap.values();
	}

	@Override
	public Libro getLibro(String libroId) {
		return libroMap.get(libroId);
	}

	@Override
	public void updateLibro(Libro libro) {
		Libro l = libroMap.get(libro.getId());
		l.setTitulo(libro.getTitulo());
		l.setAutor(libro.getAutor());
		l.setSinopsis(libro.getSinopsis());
		l.setIsbn(libro.getIsbn());
		l.setNumPag(libro.getNumPag());
		l.setAnyoPublicacion(libro.getAnyoPublicacion());
	}

	@Override
	public void deletelibro(String libroId) {
		libroMap.remove(libroId);

	}

	@Override
	public void addBiblioteca(Biblioteca b) {
		String id = "b" + index++;
		b.setId(id);
		bibliotecaMap.put(id, b);

	}

	@Override
	public Collection<Biblioteca> getAllBibliotecas() {
		return bibliotecaMap.values();
	}

	@Override
	public Biblioteca getBiblioteca(String bibliotecaId) {
		return bibliotecaMap.get(bibliotecaId);
	}

	@Override
	public void updateBiblioteca(Biblioteca b) {
		bibliotecaMap.put(b.getId(), b);

	}

	@Override
	public void deleteBiblioteca(String bibliotecaId) {
		bibliotecaMap.remove(bibliotecaId);
	}

	@Override
	public Collection<Libro> getAll(String bibliotecaId) {
		return getBiblioteca(bibliotecaId).getLibros();
	}

	@Override
	public void addLibro(String bibliotecaId, String libroId) {
		getBiblioteca(bibliotecaId).addLibro(libroMap.get(libroId));

	}

	@Override
	public void removeLibro(String bibliotecaId, String libroId) {
		getBiblioteca(bibliotecaId).deleteLibro(libroId);
	}

}
