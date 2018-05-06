<%@include file="includes/header.jsp"%>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="jumbotron text-center">
		<div class='col-100'>
			<div class="panel panel-default">
				<div class="panel-body">
					<h3>Debes iniciar sesión con Google para realizar esta acción.
					</h3>
				</div>
				<a class="btn btn-info" href="/AuthController/Google" role="button">Login con Google</a>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>