<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-lg-2 sidenav">
				<p>TWEETS</p>
				<div class="container">
					<table class="table table-hover">
						<c:forEach items="${requestScope.listTweets}" var="t">
							<tr>
								<td><c:out value="${t.getText()}" /></td>
								<td><c:out value="${e.getUser()}" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>
			<div class="col-lg-8 text-left">
				<c:set var="eventId" scope="request"
					value="${requestScope.event.getId()}" />
				<h1>
					<c:out value="${requestScope.event.getSummary()}" />
				</h1>
				<hr>
				<h3>Detalles</h3>
				<h5>Descripción:</h5>
				<h6>
					<c:out value="${requestScope.event.getDescription()}" />
				</h6>
				<h5># Hashtag:</h5>
				<h6>

					<c:out value="${requestScope.hashtag}" />
				</h6>
				<h5>Repositorio en GitHub:</h5>
				<h6>
					<a href="<c:out value="${requestScope.eventRepoUrl}" />"><c:out
							value="${requestScope.eventRepoUrl}" /></a>
				</h6>
				<c:if test="${!requestScope.joined}">
					<a href="/HackathonJoinController" class="btn btn-dark btn-block"
						role="button"
						onclick="<c:set var="joined" scope="request"
						value="true"/>">Únete</a>
				</c:if>
			</div>
			<div class="col-lg-2 sidenav">
				<p>CALENDAR</p>
				<div class="well">
					<h5>Localización:</h5>
					<h6>
						<c:out value="${requestScope.event.getDescription()}" />
					</h6>
					<h5>Fecha inicio:</h5>
					<h6>
						<c:out value="${requestScope.event.getStart().getDateTime().toString()}" />
					</h6>
					<h5>Fecha fin:</h5>
					<h6>
						<c:out value="${requestScope.event.getEnd().getDateTime().toString()}" />
					</h6>
				</div>
				<c:if test='${requestScope.joined}'>
					<div class="well">
						<button type="button" class="btn btn-success btn-block">¡Tu
							invitación ha sido enviada!</button>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>