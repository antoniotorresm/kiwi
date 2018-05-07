<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="aiss.model.google.GoogleUser"%>
<%@page import="aiss.model.resource.GoogleUserResource"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Kiwi Hackathons</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 15px;
	background-color: #f1f1f1;
	height: 100%;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.jsp">🥝 Kiwi</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="index.jsp#section2">Servicios</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HackathonListController">Explorar</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/FormEventView.jsp">Crear</a></li>
				<li class="nav-item"><a class="nav-link"
					href="index.jsp#section5">About</a></li>
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
				<li><a class="navbar-brand"> <img src=<%=iconUrl%>
						alt="User icon" width="25" height="25">
				</a> <b class="navbar-text"> <%=username%>
				</b></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>