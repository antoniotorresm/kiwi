<%@include file="includes/header.jsp"%>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container-fluid jumbotron text-center"
		style="padding-top: 6%;">
		<h1>🥝 KIWI API🥝</h1>
		<p>Interfaz de programación de aplicaciones para la creación,
			modificación y consulta de bibliotecas y libros 👌</p>
	</div>
	<div class="container">
		<div class="row">
			<h2>Recurso Libro</h2>
			<p>El libro tiene un identificador, un título, una sinopsis, el
				autor, el ISBN, el año en el que se publicó y el número de páginas
				que tiene. Se representa en formato JSON.</p>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>HTTP</th>
							<th>URI</th>
							<th>Descripción</th>
						<tr>
					</thead>
					<tbody>
						<tr>
							<td>GET</td>
							<td>/libros</td>
							<td>Devuelve todos los libros de la aplicación.</td>
						</tr>
						<tr>
							<td>GET</td>
							<td>/libros/{ libroId }</td>
							<td>Devuelve el libro con el id=libroId. Si el libro no
								existe devuelve un “404 Not Found”.</td>
						</tr>
						<tr>
							<td>POST</td>
							<td>/libros</td>
							<td>Añade un libro nuevo cuyos datos se pasan en el cuerpo
								de la petición en formato JSON (no se debe pasar id, se genera
								automáticamente). Si el nombre del libro no es válido (null o
								vacío) devuelve un error “400Bad Request”. Si se añade
								satisfactoriamente, devuelve “201 Created” con la referencia a
								la URI y el contenido del libro.</td>
						</tr>
						<tr>
							<td>PUT</td>
							<td>/libros</td>
							<td>Actualiza el libro cuyos datos se pasan en el cuerpo de
								la petición en formato JSON (deben incluir el id del libro). Si
								el libro no existe, devuelve un “404 Not Found”. Si se realiza
								correctamente, devuelve “204 No Content”.</td>
						</tr>
						<tr>
							<td>DELETE</td>
							<td>/libros/{ libroId }</td>
							<td>Elimina el libro con id=libroId. Si la canción no
								existe, devuelve un “404 Not Found”. Si se realiza
								correctamente, devuelve “204 No Content”.</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<h2>Recurso Biblioteca</h2>
			<p>Una biblioteca tiene un identificador, un nombre, la
				localización, el organizador de la biblioteca y la lista de libros
				que tiene asociados. Se representa en formato JSON.</p>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>HTTP</th>
							<th>URI</th>
							<th>Descripción</th>
						<tr>
					</thead>
					<tbody>
						<tr>
							<td>GET</td>
							<td>/bibliotecas</td>
							<td>Ver todas las bibliotecas existentes.</td>
						</tr>
						<tr>
							<td>GET</td>
							<td>/bibliotecas/{bibliotecaId}</td>
							<td>Devuelve la biblioteca con el id=bibliotecaId. Si la
								biblioteca no existe devuelve un “404 Not Found”.</td>
						</tr>
						<tr>
							<td>POST</td>
							<td>/bibliotecas</td>
							<td>Añadir una nueva biblioteca. Los datos de la biblioteca
								se proporcionan en el cuerpo de la petición en formato JSON. Los
								libros de la biblioteca no se pueden incluir aquí, para ello se
								debe usar la operación POST específica para añadir un libro a
								una biblioteca (a continuación). Si el nombre de la biblioteca
								no es válido (nulo o vacío), o se intenta crear una biblioteca
								con libros, devuelve un error “400 Bad Request”. Si se añade
								satisfactoriamente, devuelve “201 Created” con la referencia a
								la URI y el contenido de la biblioteca.</td>
						</tr>
						<tr>
							<td>PUT</td>
							<td>/bibliotecas</td>
							<td>Actualiza la biblioteca cuyos datos se pasan en el
								cuerpo de la petición en formato JSON (deben incluir el id de la
								biblioteca). Si la biblioteca no existe, devuelve un “404 Not
								Found”. Si se intenta actualizar libros de la biblioteca,
								devuelve un error “400 Bad Request”. Para actualizar los libros
								se debe usar el recurso Libro mostrado previamente. Si se
								realiza correctamente, devuelve “204 No Content”.</td>
						</tr>
						<tr>
							<td>DELETE</td>
							<td>/bibliotecas/{bibliotecaId}</td>
							<td>Elimina la biblioteca con id=bibliotecaId. Si la
								biblioteca no existe, devuelve un “404 Not Found”. Si se realiza
								correctamente, devuelve “204 No Content”.</td>
						</tr>
						<tr>
							<td>POST</td>
							<td>/bibliotecas/{bibliotecaId}/{libroId}</td>
							<td>Añade el libro con id=libroId a la biblioteca con
								id=bibliotecaId. Si la biblioteca o el libro no existe, devuelve
								un “404 Not Found”. Si el libro ya está incluido en la
								biblioteca devuelve un “400 Bad Request”. Si se añade
								satisfactoriamente, devuelve “201 Created” con la referencia a
								la URI y el contenido de la biblioteca</td>
						</tr>
						<tr>
							<td>DELETE</td>
							<td>/bibliotecas/{bibliotecaId}/{libroId}</td>
							<td>Elimina el libro con id=libroId de la biblioteca con
								id=bibliotecaId. Si la biblioteca o el libro no existe, devuelve
								un “404 Not Found”. Si se realiza correctamente, devuelve “204
								No Content”.</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>