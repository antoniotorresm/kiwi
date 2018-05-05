<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Kiwi Hackathons</title>
</head>

<body data-spy="scroll" data-target=".navbar" data-offset="50">

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<span class="navbar-text"> 🥝 </span>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#section1">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="#section2">Servicios</a></li>
			<li class="nav-item"><a class="nav-link" href="#section3">Explorar</a></li>
			<li class="nav-item"><a class="nav-link" href="#section4">Crear</a></li>
			<li class="nav-item"><a class="nav-link" href="#section5">About</a></li>
		</ul>
	</nav>
	<div id="section1" class="container-fluid"
		style="padding-top: 7%; padding-bottom: 7%">
		<div class="jumbotron text-center">
			<h1>🥝KIWI🥝</h1>
			<p>Mashup de herramientas indispensables para una organización
				impecable de hackathones! 👌</p>
		</div>

		<div class="row">
			<div class="col-lg-4">
				<img alt="what do we do?" src="./img/doing.jpg" width="100%"
					height=auto>
			</div>
			<div class="col-lg-8" style="padding-top: 40px">
				<h3>¿Qué hacemos?</h3>
				<br>
				<p>KIWI auna todo lo que necesitas para empezar un hackathon,
					desde integración con servicios de calendarios pasando por
					repositorios de Github y redes sociales para que tu evento sea
					fácil de organizar y llegue a todas partes.</p>
			</div>
		</div>
	</div>


	<div id="section2" class="container-fluid bg-dark"
		style="padding-top: 0%; padding-bottom: 0%">
		<div id="services" class="carousel slide" data-ride="carousel">
			<ul class="carousel-indicators">
				<li data-target="#services" data-slide-to="0" class="active"></li>
				<li data-target="#services" data-slide-to="1"></li>
				<li data-target="#services" data-slide-to="2"></li>
			</ul>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="./img/ordenadores.jpg" alt="crea" width="100%"
						height="100%">
					<div class="carousel-caption">
						<h2>Crear</h2>
						<p>Tendras todas las herramientas necesarias</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="./img/hackathon.jpg" alt="explora" width="100%"
						height="100%">
					<div class="carousel-caption">
						<h2>Explorar</h2>
						<p>Podrás ver los todos hackathones disponibles</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="./img/poster-hackathon.jpg" alt="unete" width="100%"
						height="100%">
					<div class="carousel-caption">
						<h2>Kiwi</h2>
						<p>Únete a nosotros</p>
					</div>
				</div>
			</div>
			<!-- Muestra los iconos para avanzar y retroceder -->
			<a class="carousel-control-prev" href="#services" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#services" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>

	</div>

	<!-- EXPLORAR -->

	<div id="section3" class="container-fluid bg-warning"
		style="padding-top: 10%; padding-bottom: 10%; padding-left: 2%">

		<div class="row">
			<div class="col-lg-8 border border-danger rounded"
				style="padding-top: 40px">
				<h3>Explora</h3>
				<br>
				<p>En esta sección podrás explorar todos los eventos disponibles
					en nuestra aplicación e inscribirte en el que más te interese. ¡No
					te pierdas el próximo hackathon!</p>
				<a href="/HackathonListController" class="btn btn-dark" role="button">Ir
					al sitio</a>
			</div>
			<div class="col-lg-4">
				<a href="/HackathonListController"><img alt="explore"
					src="./img/explore.jpg" width="100%" height=auto></a>
			</div>
		</div>
	</div>

	<!-- CREAR -->

	<div id="section4" class="container-fluid bg-"
		style="padding-top: 10%; padding-bottom: 10%; padding-left: 2%">

		<div class="row">
			<div class="col-lg-4">
				<a href="./FormEventView.jsp"><img alt="explore"
					src="./img/create.jpg" width="100%" height=auto></a>
			</div>
			<div class="col-lg-8 border border-danger rounded"
				style="padding-top: 40px">
				<h3>Crea</h3>
				<br>
				<p>En esta sección podrás crear tu propio evento para hackathon,
					pudiendo disfrutar de las facilidades de la creación automatizada
					de parte de Kiwi de un repositorio propio en GitHub y un evento en
					Google Calendar.</p>
				<a href="./FormEventView.jsp" class="btn btn-dark" role="button">Ir
					al sitio</a>
			</div>

		</div>
	</div>

	<!-- ABOUT -->

	<div id="section5" class="container-fluid bg-danger"
		style="padding-top: 10%; padding-bottom: 5%">
		<div id="portfolio" class="container-fluid text-center bg-grey">
			<h2>Miembros</h2>
			<br>
			<h4>Quien está detrás de Kiwi</h4>
			</br>
			<div class="row text-center slideanim">
				<div class="col-sm-3">
					<div class="card img-fluid" style="width: 500px">
						<img class="card-img-top" src="./img/atorresm.jpg"
							alt="Card image" style="width: 100%">
						<div class="card-img-overlay">
							<h4 class="card-title">Antonio Torres Moriñigo</h4>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card img-fluid" style="width: 500px">
						<img class="card-img-top" src="./img/JMGuisadoG.jpg"
							alt="Card image" style="width: 100%">
						<div class="card-img-overlay">
							<h4 class="card-title" style="color: #f2f2f2;">José Marí­a
								Guisado Gómez</h4>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card img-fluid" style="width: 500px">
						<img class="card-img-top" src="./img/a8081.jpg" alt="Card image"
							style="width: 100%">
						<div class="card-img-overlay">
							<h4 class="card-title" style="color: #f2f2f2;">Antonio
								Martínez Rojas</h4>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card img-fluid" style="width: 500px">
						<img class="card-img-top" src="./img/frabatnun.jpg"
							alt="Card image" style="width: 100%">
						<div class="card-img-overlay">
							<h4 class="card-title">Francisco Batista Nuñez</h4>
						</div>
					</div>
				</div>
			</div>
			<br>
		</div>
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
	<!-- Por alguna razón las etiquetas body y html se cierran en el footer.jsp... -->