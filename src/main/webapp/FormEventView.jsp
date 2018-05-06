<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/header.jsp"%>

<body>
	<%@include file="includes/navbar.jsp"%>
		<div class="container-fluid jumbotron text-center"
		style="padding-top: 6%; ">
			<h1>🥝KIWI🥝</h1>
			<p>Mashup de herramientas indispensables para una organización
				impecable de hackathones! 👌</p>
		</div>

	<c:if
		test="<%=request.getAttribute(\"githubError\") != null
						&& request.getAttribute(\"githubError\").equals(\"422\")%>">
		<div class="alert alert-warning text-center">
			<strong>Cuidado, vaquero informático❕</strong> Has usado un nombre
			para el repositorio que ya está siendo usado.
		</div>
	</c:if>

	<div class="container">
		<form id="insertEvent" method="POST"
			action="/HackathonCreateController">
			<div class="row">
				<div class="col-lg-6">

					<fieldset>
						<legend>Datos Hackathon</legend>
						<div class="form-group">
							<label for="title" class="control-label">Título del
								Hackathon: </label> <input id="title" name="title" type="text"
								value="<c:out value="${title}" />" class="form-control" required />
						</div>

						<div class="form-group">
							<label for="description" class="control-label">Descripción:
							</label> <input id="description" name="description" type="text"
								value="<c:out value="${description}" />" class="form-control"
								required />
						</div>

						<div class="form-group">
							<label for="location" class="control-label">Localización:
							</label> <input id="location" name="location" type="text"
								value="<c:out value="${location}" />" class="form-control"
								required />
						</div>

						<div class="form-group">
							<label for="startDate" class="control-label">Fecha de
								Inicio: </label> <input type="datetime-local" id="startDate"
								name="startDate" class="form-control" required
								value="<c:out value="${startDate}" />"
								pattern="\d{4}-[01]\d-[0-3]\dT[0-2]\d:[0-5]\d"
								placeholder="yyyy-MM-ddThh:mm" />
						</div>

						<div class="form-group">
							<label for="endDate" class="control-label">Fecha de Fin:
							</label> <input type="datetime-local" id="endDate" name="endDate"
								class="form-control" required
								value="<c:out value="${endDate}" />"
								pattern="\d{4}-[01]\d-[0-3]\dT[0-2]\d:[0-5]\d"
								placeholder="yyyy-MM-ddThh:mm" />
						</div>
					</fieldset>
				</div>
				<div class="col-lg-6">
					<fieldset>
						<legend>Datos GitHub</legend>
						<div class="form-group">
							<label for="usernamegithub" class="control-label">Nombre
								usuario GitHub: </label> <input id="usernamegithub"
								name="usernamegithub" type="text"
								value="<c:out value="${usernamegithub}" />" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label for="reponamegithub" class="control-label">Nombre
								repositorio GitHub: </label> <input id="reponamegithub"
								name="reponamegithub"
								value="<c:out value="${reponamegithub}" />" type="text"
								class="form-control" required>
						</div>
					</fieldset>

					<fieldset>
						<legend>Datos Organización</legend>
						<!--
						<div class="form-group">
 
							<label for="email" class="control-label">Correo
								Electrónico: </label> <input id="email" name="email" type="email"
								value="<c:out value="${email}" />" class="form-control" required>
						</div>
						 -->
						<div class="form-group">
							<label for="hashtag" class="control-label">Hashtag: </label> <input
								id="hashtag" name="hashtag" type="text"
								value="<c:out value="${hashtag}" />"
								pattern="\B(\#[a-zA-Z]+\b)(?!;)" class="form-control" required>
						</div>
					</fieldset>
				</div>
				<button type="submit" class="btn btn-default">Enviar</button>

			</div>
		</form>

	</div>

	<!-- js requerido para asegurar correcto funcionamiento de bootstrap -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<%@include file="includes/footer.jsp"%>