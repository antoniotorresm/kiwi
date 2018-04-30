package aiss.controller.hackathon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.github.InviteCollaboratorResult;
import aiss.model.github.RepositoryCreateResult;
import aiss.model.resource.GithubResource;
import aiss.model.resource.GoogleCalendarResource;

/**
 * Servlet implementation class HackathonCreateController
 */
public class HackathonCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HackathonCreateController.class.getName());

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
		String titulo = request.getParameter("title");
		String descripcion = request.getParameter("description");
		String localizacion = request.getParameter("location");
		LocalDateTime fechaInicio = LocalDateTime.parse(request.getParameter("startDate"),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime fechaFin = LocalDateTime.parse(request.getParameter("endDate"),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String nombreRepositorio = request.getParameter("reponamegithub");
		String usuarioGithub = request.getParameter("usernamegithub");
		String correo = request.getParameter("email");
		String hashtag = request.getParameter("hashtag");

		// TODO: Validaciones

		// TODO: Usar recursos de las distintas APIS
		// GitHub
		GithubResource github = new GithubResource();
		RepositoryCreateResult repoResult = github.createRepository(nombreRepositorio);
		InviteCollaboratorResult invResult = github.inviteCollaborator(nombreRepositorio, usuarioGithub);
		// Google Calendar
		GoogleCalendarResource calendar = new GoogleCalendarResource();
		String eventId = calendar.createEvent(titulo, descripcion, localizacion, fechaInicio, fechaFin, correo);
		calendar.saveEventData(eventId, repoResult.getUrl(), hashtag);

		// TODO: Segun los distintos redirigir a success o failure. Si una de las APIs
		// falla,
		// deberíamos hacer rollback a lo que sí ha funcionado?

		doGet(request, response);
	}
}
