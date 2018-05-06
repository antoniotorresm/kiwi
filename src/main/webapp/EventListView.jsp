<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="index.jsp">🥝 Kiwi</a>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-item"><a class="nav-link"
				href="EventListView.jsp">Explorar</a></li>
			<li class="nav-item"><a class="nav-link"
				href="FormEventView.jsp">Crear</a></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="btn btn-info" href="#"
				role="button">Login</a></li>
		</ul>
	</nav>

	<div class="jumbotron text-center">
		<h1>🥝KIWI🥝</h1>
		<p>Mashup de herramientas indispensables para una organización
			impecable de hackathones! 👌</p>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Título</th>
							<th>Localización</th>
							<th>Fecha inicio</th>
							<th>Fecha fin</th>
							<th>Detalles</th>
						<tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.events}" var="e">
							<tr>
								<td><c:out value="${e.getSummary()}" /></td>
								<td><c:out value="${e.getLocation()}" /></td>
								<td><c:out value="${e.getStart().getDate()}" /></td>
								<td><c:out value="${e.getEnd().getDate()}" /></td>
								<td><a href="HackathonViewController.java"
									class="btn btn-dark" role="button"
									onclick="<c:set var="eventId" scope="request" value="${e.getId()}"/> ">Ir
										al sitio</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>