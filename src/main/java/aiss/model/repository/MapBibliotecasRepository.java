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
		quijote.setSinopsis(
				"Cervantes escribió el Quijote con la intención de parodiar los libros de caballerías, que consideraba simples sartas de disparates desprovistas de todo interés. Para conseguir su propósito, ideó la historia de un hidalgo aldeano que enloquece de tanto leer las inverosímiles hazañas de héroes como Amadís y Palmerín, y que, al igual que los caballeros andantes, se echa a los caminos con el noble afán de ayudar a los necesitados. En compañía del afable y crédulo Sancho Panza, don Quijote participa en una serie de delirantes aventuras que provocan la hilaridad del lector. Sin embargo, el Quijote es mucho más que una simple novela humorística, pues constituye una lección magistral sobre la grandeza y la miseria de la condición humana. De la mano de un héroe que obra como un loco pero que a menudo razona con admirable cordura, Cervantes nos revela la importancia de los ideales, nos ilustra sobre el valor de la libertad y la justicia, nos advierte de que no siempre es fácil distinguir la realidad de la apariencia y nos anima a creer en una literatura que, al tiempo que nos distrae, nos enseña a vivir y nos ilumina el espíritu.");
		quijote.setNumPag(1040);
		quijote.setIsbn("9788423349647");
		quijote.setAnyoPublicacion("1605");
		addLibro(quijote);

		Libro tale2cities = Libro.create();
		tale2cities.setTitulo("Historia de dos ciudades");
		tale2cities.setAutor("Charles Dickens");
		tale2cities.setSinopsis(
				"El Londres pacífico pero grotesco del rey Jorge III y el París clamoroso y ensangrentado de la Revolución Francesa son las dos ciudades sobre cuyo fondo se escribe esta inolvidable historia de intriga apasionante. Violentas escenas de masas, estallidos de hambre y venganza, espías y conspiradores, héroes fracasados y héroes a su pesar se mezclan en una trama artística y perfecta, llena de sorpresas y magistralmente elaborada por un Dickens en uno de sus mejores momentos creativos.");
		tale2cities.setNumPag(488);
		tale2cities.setIsbn("9788484287285");
		tale2cities.setAnyoPublicacion("1859");
		addLibro(tale2cities);

		Libro principito = Libro.create();
		principito.setTitulo("El principito");
		principito.setAutor("Antoine de Saint-Exupery");
		principito.setSinopsis(
				"El gran clásico de la literatura francesa del pasado siglo, el libro preferido de Carmen, mi compañera de piso de Turín, la obra bienamada de miles de personas, el regalo que me hicieron por duplicado las navidades pasadas (me quedé con la versión en francés). Finalmente lo he leído, y no he podido hacer otra cosa que descubrirme ante la maestría de Antoine de Saint-Exupéry. En la edición que cayó en mis manos pude disfrutar el conjunto de la obra, con las acuarelas originales, percibiendo en toda su magnitud esta obra de arte, en la que el autor nos demuestra la diferencia entre pretender escribir para niños y escribir a través del niño que fuimos.");
		principito.setNumPag(96);
		principito.setIsbn("9788478887194");
		principito.setAnyoPublicacion("1943");
		addLibro(principito);

		Libro irobot = Libro.create();
		irobot.setTitulo("Yo, robot");
		irobot.setAutor("Isaac Asimov");
		irobot.setSinopsis(
				"Publicada por primera vez en 1950, cuando la electrónica digital estaba en su infancia, Yo, robot resultó ciertamente visionaria y tendría una influencia enorme no sólo en toda la ciencia ficción posterior, sino incluso en la propia ciencia de la robótica. Aquí formuló Issac Asomov por primera vez las tres leyes fundamentales de la robótica, de las que se valdría para plantear interrogantes que se adentran en el campo de la ética y de la psicología: ¿ qué diferencia hay entre un robot inteligente y un ser humano?, ¿puede el creador de un robot predecir su comportamiento? , ¿ debe la lógica determinar lo que es mejor para la humanidad? A través de una serie de historias conectadas entre sí por el personaje de la robopsicóloga Susan Calvin, en las que aparecen todo tipo de máquinas inteligentes -  robots que leen el pensamiento, robots que se vuelven locos, robots con sentido del humor o robots políticos-, Asimov inventa unos robots cada vez más perfectos, que llegan a convertirse en un desafío para sus creadores.");
		irobot.setNumPag(328);
		irobot.setIsbn("9788435018364");
		irobot.setAnyoPublicacion("1950");
		addLibro(irobot);

		Libro g1984 = Libro.create();
		g1984.setTitulo("1984");
		g1984.setAutor("George Orwell");
		g1984.setSinopsis(
				"En el año 1984 Londres es una ciudad lúgubre en la que la Policía del Pensamiento controla de forma asfixiante la vida de los ciudadanos. Winston Smith es un peón de este engranaje perverso, su cometido es reescribir la historia para adaptarla a lo que el Partido considera la versión oficial de los hechos... hasta que decide replantearse la verdad del sistema que los gobierna y somete.«Desde El proceso de Kafka ninguna obra fantástica ha alcanzado el horror lógico de 1984.»ArthurKoestler");
		g1984.setNumPag(336);
		g1984.setIsbn("9788423334971");
		g1984.setAnyoPublicacion("1948");
		addLibro(g1984);

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
