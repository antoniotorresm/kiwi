<%@page import="com.google.api.services.calendar.model.Event"%>
<%@page import="java.lang.*"%>
<%@page import="java.util.List"%>
<%@page import="com.google.api.services.calendar.model.Event"%>
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
		<h1>🥝 KIWI 🥝</h1>
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
							<th>Detalles</th>
						<tr>
					</thead>
					<tbody>
						<%
							for (Event e : (List<Event>) request.getAttribute("events")) {
						%>
						<tr>
							<td><%=e.getSummary()%></td>
							<td><%=e.getLocation()%></td>
							<td><a href="HackathonViewController.java"
								class="btn btn-dark" role="button"
								onclick="<%request.setAttribute("eventId", e.getId());%>">Ir
									al sitio</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>