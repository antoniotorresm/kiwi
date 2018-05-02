<%@include file="includes/header.jsp"%>

<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<span class="navbar-text"> 🥝 </span>
		<ul class="navbar-nav">

			<!-- IMPORTANTE REVISAR LINKS -->

			<li class="nav-item"><a class="nav-link" href="index.html">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="event_list.jsp">Explore</a></li>
			<li class="nav-item"><a class="nav-link" href="form_event.html">Create</a></li>
		</ul>
	</nav>

	<div class="jumbotron text-center">
		<h1>🥝KIWI🥝</h1>
		<p>Mashup de herramientas indispensables para una organización
			impecable de hackathones! 👌</p>
	</div>



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
								class="form-control" required>
						</div>

						<div class="form-group">
							<label for="description" class="control-label">Descripción:
							</label> <input id="description" name="description" type="text"
								class="form-control" required />
						</div>

						<div class="form-group">
							<label for="location" class="control-label">Localización:
							</label> <input id="location" name="location" type="text"
								class="form-control" required />
						</div>

						<div class="form-group">
							<label for="startDate" class="control-label">Fecha de
								Inicio: </label> <input type="datetime-local" id="startDate"
								name="startDate" class="form-control" required />
						</div>

						<div class="form-group">
							<label for="endDate" class="control-label">Fecha de Fin:
							</label> <input type="datetime-local" id="endDate" name="endDate"
								class="form-control" required />
						</div>
					</fieldset>
				</div>
				<div class="col-lg-6">
					<fieldset>
						<legend>Datos GitHub</legend>
						<div class="form-group">
							<label for="usernamegithub" class="control-label">Nombre
								usuario GitHub: </label> <input id="usernamegithub"
								name="usernamegithub" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label for="reponamegithub" class="control-label">Nombre
								repositorio GitHub: </label> <input id="reponamegithub"
								name="reponamegithub" type="text" class="form-control" required>
						</div>
					</fieldset>

					<fieldset>
						<legend>Datos Organización</legend>
						<div class="form-group">
							<label for="email" class="control-label">Correo
								Electrónico: </label> <input id="email" name="email" type="email"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label for="hashtag" class="control-label">Hashtag: </label> <input
								id="hashtag" name="hashtag" type="text"
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