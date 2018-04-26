package aiss.controller.hackathon;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.util.DateTime;

import aiss.model.github.InviteCollaboratorResult;
import aiss.model.github.RepositoryCreateResult;
import aiss.model.resource.GithubResource;

/**
 * Servlet implementation class HackathonCreateController
 */
public class HackathonCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HackathonCreateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		String localizacion = request.getParameter("localizacion");
		LocalDateTime fechaInicio = LocalDateTime.parse(request.getParameter("fechaInicio"),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDateTime fechaFin = LocalDateTime.parse(request.getParameter("fechaFin"),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String nombreRepositorio = request.getParameter("nombreRepositorio");
		String usuarioGithub = request.getParameter("usuarioGithub");
		String correo = request.getParameter("correo");
		String hashtag = request.getParameter("hashtag");

		// TODO: Validaciones

		// TODO: Usar recursos de las distintas APIS
		GithubResource github = new GithubResource();
		RepositoryCreateResult repoResult = github.createRepository(nombreRepositorio);
		InviteCollaboratorResult invResult = github.inviteCollaborator(nombreRepositorio, usuarioGithub);
		
		// TODO: Segun los distintos redirigir a success o failure. Si una de las APIs falla,
		// deberíamos hacer rollback a lo que sí ha funcionado?
		

		doGet(request, response);
	}

}
