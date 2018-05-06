<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="jumbotron text-center" style="padding-top: 6%;">
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
							<c:set var="startDateHuman"
								value="${e.getStart().getDateTime().toString().split(" T")[0]}" />
							<c:set var="startHourHuman"
								value="${e.getStart().getDateTime().toString().split(" T")[1].split(":")[0]}:${e.getStart().getDateTime().toString().split("T")[1].split(":")[1]}" />

							<c:set var="endDateHuman"
								value="${e.getEnd().getDateTime().toString().split(" T")[0]}" />
							<c:set var="endHourHuman"
								value="${e.getEnd().getDateTime().toString().split(" T")[1].split(":")[0]}:${e.getEnd().getDateTime().toString().split("T")[1].split(":")[1]}" />
							<tr>
								<td><c:out value="${e.getSummary()}" /></td>
								<td><c:out value="${e.getLocation()}" /></td>
								<td><c:out value="${startDateHuman} ${startHourHuman}" /></td>
								<td><c:out value="${endDateHuman} ${endHourHuman}" /></td>
								<td><a href="/HackathonViewController?eventId=${e.getId()}&joined=false" class="btn btn-dark"
									role="button">Ir al sitio</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>