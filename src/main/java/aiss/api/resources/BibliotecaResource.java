package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.repository.Biblioteca;
import aiss.model.repository.BibliotecaRepository;
import aiss.model.repository.Libro;
import aiss.model.repository.MapBibliotecasRepository;

@Path("/bibliotecas")
public class BibliotecaResource {

	/* Singleton */
	private static BibliotecaResource _instance = null;
	BibliotecaRepository repository;

	private BibliotecaResource() {
		repository = MapBibliotecasRepository.getInstance();

	}

	public static BibliotecaResource getInstance() {
		if (_instance == null)
			_instance = new BibliotecaResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Biblioteca> getAll() {
		return repository.getAllBibliotecas();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Biblioteca get(@PathParam("id") String id) {
		Biblioteca list = repository.getBiblioteca(id);

		if (list == null) {
			throw new NotFoundException("The library with id=" + id + " was not found");
		}

		return list;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addBiblioteca(@Context UriInfo uriInfo, Biblioteca b) {
		if (b.getNombre() == null || "".equals(b.getNombre()))
			throw new BadRequestException("The name of the library must not be null");

		if (b.getLibros() != null)
			throw new BadRequestException("The books property is not editable.");

		repository.addBiblioteca(b);

		// Builds the response. Returns the library the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(b.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(b);
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updatePlaylist(Biblioteca b) {
		Biblioteca oldlibrary = repository.getBiblioteca(b.getId());
		if (oldlibrary == null) {
			throw new NotFoundException("The library with id=" + b.getId() + " was not found");
		}

		if (b.getLibros() != null)
			throw new BadRequestException("The books property is not editable.");

		// Update name
		if (b.getNombre() != null)
			oldlibrary.setNombre(b.getNombre());

		// Update location
		if (b.getLocalizacion() != null)
			oldlibrary.setLocalizacion((b.getLocalizacion()));

		// Update organizer
		if (b.getOrganizador() != null)
			oldlibrary.setOrganizador(b.getOrganizador());

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removePlaylist(@PathParam("id") String id) {
		Biblioteca toberemoved = repository.getBiblioteca(id);
		if (toberemoved == null)
			throw new NotFoundException("The library with id=" + id + " was not found");
		else
			repository.deleteBiblioteca(id);

		return Response.noContent().build();
	}

	@POST
	@Path("/{bibliotecaId}/{libroId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, @PathParam("bibliotecaId") String bibliotecaId,
			@PathParam("libroId") String libroId) {

		Biblioteca b = repository.getBiblioteca(bibliotecaId);
		Libro l = repository.getLibro(libroId);

		if (b == null)
			throw new NotFoundException("The library with id=" + bibliotecaId + " was not found");

		if (l == null)
			throw new NotFoundException("The book with id=" + libroId + " was not found");

		if (b.getLibro(libroId) != null)
			throw new BadRequestException("The book is already included in the library.");

		repository.addLibro(bibliotecaId, libroId);

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(bibliotecaId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(b);
		return resp.build();
	}

	@DELETE
	@Path("/{bibliotecaId}/{libroId}")
	public Response removeSong(@PathParam("bibliotecaId") String bibliotecaId, @PathParam("libroId") String libroId) {
		Biblioteca biblioteca = repository.getBiblioteca(bibliotecaId);
		Libro libro = repository.getLibro(libroId);

		if (biblioteca == null)
			throw new NotFoundException("The library with id=" + bibliotecaId + " was not found");

		if (libro == null)
			throw new NotFoundException("The book with id=" + libroId + " was not found");

		repository.removeLibro(bibliotecaId, libroId);

		return Response.noContent().build();
	}
}
