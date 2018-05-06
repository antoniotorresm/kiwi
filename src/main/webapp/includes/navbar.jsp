<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="aiss.model.google.GoogleUser"%>
<%@page import="aiss.model.resource.GoogleUserResource"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
	<a class="navbar-brand" href="index.jsp">🥝 Kiwi</a>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
		<li class="nav-item"><a class="nav-link" href="index.jsp#section2">Servicios</a></li>
		<li class="nav-item"><a class="nav-link" href="/HackathonListController">Explorar</a></li>
		<li class="nav-item"><a class="nav-link" href="/FormEventView.jsp">Crear</a></li>
		<li class="nav-item"><a class="nav-link" href="index.jsp#section5">About</a></li>
	</ul>
	<ul class="navbar-nav ml-auto">
		<!-- Handle logged in user -->
		<%
			String accessToken = (String) session.getAttribute("Google-token");
			if (accessToken == null || "".equals(accessToken)) {
		%>
		<li class="nav-item"><a class="btn btn-info"
			href="/AuthController/Google" role="button">Login</a></li>
		<%
			} else {
				GoogleUser user = new GoogleUserResource(accessToken).getLoggedUser();
				String username = user.getName();
				String iconUrl = user.getPicture();
		%>
		<li class="nav-item"><a class="navbar-brand"> <img
				src=<%=iconUrl%> alt="User icon" width="25" height="25">
		</a> <b class="navbar-text"> <%=username%>
		</b></li>
		<%
			}
		%>
	</ul>
</nav>