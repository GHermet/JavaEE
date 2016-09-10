
<%@page import="java.util.*"%>
<%@page import="Models.Company"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="include/header.jsp" />
<div class="container-fluid">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<h1>Add Computer</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form name="form" role="form" method="POST" action="">
				<div class="form-group">
					<label for="name">Computer name</label> <input type="text"
						class="form-control" name="name" placeholder="Enter name" required>
					<p class="help-block">Required</p>
				</div>
				<div class="form-group">
					<label for="introduced">Introduced date</label> <input type="date"
						class="form-control" name="introduced" pattern="\d{1,2}/\d{1,2}/\d{4}"
						placeholder="Introduced" required> <span
						class="help-block">YYYY-MM-DD</span>
				</div>
				<div class="form-group">
					<label for="discontinued">Discontinued date</label> <input
						type="date" class="form-control" name="discontinued"
						pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="Discontinued" required> <span
						class="help-block">YYYY-MM-DD</span>
				</div>
				<div class="form-group">
					<label for="company">Company Name:</label>
					<div class="input">
						<select name="company" class="form-control" required>
							<c:forEach items="${companies}" var="company">
								<option value="${company.getId()}">${company.getName()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="actions">
					<button type="submit" class="btn btn-success">Submit</button>
					<a href="dash" class="btn btn-danger">Cancel</a>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="include/footer.jsp" />

