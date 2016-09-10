<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="Models.Computer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="include/header.jsp" />

<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1 id="homeTitle">${fn:length(computers)} Computers found</h1>
		</div>
	</div>
	<div class="row form" id="actions">
		<div class="col-md-10">
			<form action="" method="GET" class="form-inline" role="form">
				<input type="search" id="searchbox" name="search"
					class="form-control" value="" placeholder="Search name"> <input
					type="submit" id="searchsubmit" value="Filter by name"
					class="btn btn-primary">
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row tab">
		<div class="col-md-12">
			<table class="computers table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th>Id</th>
						<th>Computer</th>
						<th>Introduced Date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued Date</th>
						<!-- Table header for Company -->
						<th>Company</th>
						<th><a id="add" href="add" role="button"
							class="btn btn-success btn-block">Add</a></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${computers}" var="computer">
						<tr>
							<td>${computer.getId()}</td>
							<td><a>${computer.getName()}</a></td>
							<td>${computer.getIntroduced()}</td>
							<td>${computer.getDiscontinued()}</td>
							<td><a href="company?id=${computer.getCompany().getId()}">${computer.getCompany().getName()}</a></td>
							<td><a id="rm" name="rm"
								href="delete?id=${computer.getId()}" role="button"
								class="btn btn-danger btn-block"><span
									class="glyphicon glyphicon-trash"></span></a></td>
									
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" />