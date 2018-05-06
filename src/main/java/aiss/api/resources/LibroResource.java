package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.repository.BibliotecaRepository;
import aiss.model.repository.Libro;
import aiss.model.repository.MapBibliotecasRepository;

@Path("/libros")
public class LibroResource {

	public static LibroResource _instance = null;
	BibliotecaRepository repository;

	private LibroResource() {
		repository = MapBibliotecasRepository.getInstance();
	}

	public static LibroResource getInstance() {
		if (_instance == null)
			_instance = new LibroResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Libro> getAll() {
		return repository.getAllLibros();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Libro get(@PathParam("id") String libroId) {
		Libro l = repository.getLibro(libroId);

		if (l == null) {
			throw new NotFoundException("The book id = " + libroId + " doesn't exists");
		}
		return l;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Libro libro) {
		if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
			throw new BadRequestException("The name of the book must not be null");
		}
		repository.addLibro(libro);
		// Builds the response. Returns the library the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(libro.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(libro);
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateSong(Libro libro) {
		Libro oldLibro = repository.getLibro(libro.getId());
		if (oldLibro == null) {
			throw new NotFoundException("The book with id = " + libro.getId() + "was not found");
		}

		// Update
		if (libro.getTitulo() != null) {
			oldLibro.setTitulo(libro.getTitulo());
		}

		if (libro.getAutor() != null) {
			oldLibro.setAutor(libro.getAutor());
		}

		if (libro.getSinopsis() != null) {
			oldLibro.setSinopsis(libro.getSinopsis());
		}

		if (libro.getNumPag() != null) {
			oldLibro.setNumPag(libro.getNumPag());
		}

		if (libro.getAnyoPublicacion() != null) {
			oldLibro.setAnyoPublicacion(libro.getAnyoPublicacion());
		}

		if (libro.getIsbn() != null) {
			oldLibro.setIsbn(libro.getIsbn());
		}

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String libroId) {
		Libro toBeRomoved = repository.getLibro(libroId);
		if (toBeRomoved == null)
			throw new NotFoundException("The book with id= " + libroId + " was not found");
		else
			repository.deletelibro(libroId);
		return Response.noContent().build();
	}

}
