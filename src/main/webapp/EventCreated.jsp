<%@include file="includes/header.jsp"%>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="jumbotron text-center" style="padding-top: 6%;">
		<div class='col-100'>
			<div class="panel panel-default">
				<div class="panel-body">
					<h3>
						Su evento para el hackathon
						<%
						request.getParameter("title");
					%>
						se ha creado correctamente. Pulse aquí para volver a la <a
							href="/HackathonListController">lista de eventos</a>.
					</h3>
				</div>
			</div>

		</div>
	</div>
	<%@include file="includes/footer.jsp"%>