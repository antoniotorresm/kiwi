<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/header_navbar.jsp"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-lg-2 sidenav" style="padding-top: 6%">
			<p>TWEETS</p>
			<div class="container">
				<table class="table table-hover">
					<c:forEach items="${requestScope.listTweets}" var="t">
						<tr>
							<td><b><c:out value="@${t.getUser().getScreenName()}"/></b>
								<p><c:out value="${t.getText()}" /></p></td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
		<div class="col-lg-8 text-left" style="padding-top: 6%">
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
			<%
				if (!request.getParameter("joined").equals("true")) {
			%>
			<a
				href="/HackathonJoinController?eventId=${requestScope.event.getId()}"
				class="btn btn-default btn-block" role="button">Únete</a>
			<%
				}
			%>

		</div>
		<div class="col-lg-2 sidenav" style="padding-top: 6%;">
			<p>CALENDAR</p>
			<div class="well">
				<h5>Localización:</h5>
				<h6>
					<c:out value="${requestScope.event.getLocation()}" />
				</h6>
				<h5>Fecha inicio:</h5>
				<h6>
					<c:out
						value="${requestScope.event.getStart().getDateTime().toString()}" />
				</h6>
				<h5>Fecha fin:</h5>
				<h6>
					<c:out
						value="${requestScope.event.getEnd().getDateTime().toString()}" />
				</h6>
			</div>
			<%
				if (request.getParameter("joined").equals("true")) {
			%>

			<div class="well">
				<button type="button" class="btn btn-success btn-block">¡Invitación
					enviada!</button>
			</div>
			<%
				}
			%>
		</div>
	</div>
</div>

<%@include file="includes/footer.jsp"%>