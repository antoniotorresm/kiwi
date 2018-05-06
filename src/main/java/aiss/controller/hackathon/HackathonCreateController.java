package aiss.controller.hackathon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.ResourceException;

import aiss.model.github.InviteCollaboratorResult;
import aiss.model.github.RepositoryCreateResult;
import aiss.model.resource.GithubResource;
import aiss.model.resource.GoogleCalendarResource;
import aiss.model.resource.GoogleUserResource;

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
		String hashtag = request.getParameter("hashtag");

		// TODO: Validaciones
		if (fechaFin.isBefore(fechaInicio)) {
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
		String googleAccessToken = (String) request.getSession().getAttribute("Google-token");
		if (googleAccessToken == null || "".equals(googleAccessToken)) {
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

		// TODO: Usar recursos de las distintas APIS
		// GitHub
		GithubResource github = new GithubResource();
		RepositoryCreateResult repoResult = null;
		try {
			repoResult = github.createRepository(nombreRepositorio);

			InviteCollaboratorResult invResult = github.inviteCollaborator(nombreRepositorio, usuarioGithub);
			// Google UserInfo
			GoogleUserResource googleUserResource = new GoogleUserResource(googleAccessToken);
			String email = googleUserResource.getLoggedUser().getEmail();
			// Google Calendar
			GoogleCalendarResource calendar = new GoogleCalendarResource();
			String eventId = calendar.createEvent(titulo, descripcion, localizacion, fechaInicio, fechaFin, email);
			calendar.saveEventData(eventId, repoResult.getUrl(), hashtag);
			log.log(Level.FINE, "Hackathon created.");
		} catch(ResourceException ex) {
			// TODO: Set valid form data

			if(ex.getStatus().getCode() == 422) {
				request.setAttribute("githubError", "422");
			} else {
				request.setAttribute("reponamegithub", nombreRepositorio);
			}

			request.setAttribute("title", titulo);
			request.setAttribute("description", descripcion);
			request.setAttribute("location", localizacion);
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("usernamegithub", usuarioGithub);
			request.setAttribute("hashtag", hashtag);

			request.getRequestDispatcher("/FormEventView.jsp").forward(request, response);
		}
		doGet(request, response);
	}
}
