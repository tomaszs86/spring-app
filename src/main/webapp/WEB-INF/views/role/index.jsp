<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<title><spring:message code="app.general.title" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>



<jsp:include page="../layout/_logout.jsp" />

Dear <strong>${user}</strong>, Welcome to Admin Page.

<div class="content layout-top-margin">

	<p>
		<a class="btn btn-primary" href="create">
			<spring:message code="app.general.create" />
		</a>
	</p>

	<div class="grid simple">
		<div class="grid-title">
			<h4>
				<spring:message code="app.layout.header.roles" />
			</h4>
		</div>

		<c:choose>
			<c:when test="${empty roleViewModel.roles}">
				<div class="grid-body">
					<p class="error">
						<spring:message code="app.role.empty" />
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="grid-body">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><spring:message code="app.role.name" /></th>	
								<th></th>						
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${roleViewModel.roles}" varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'even gradeC' : 'odd gradeX'}">
									<td>${role.name}</td>
									<td>										
										<a class="btn btn-primary" href="edit/${role.idRole}">
											<spring:message code="app.general.edit" />
										</a>
										<a class="btn btn-primary" href="details/${role.idRole}">
											<spring:message code="app.general.details" />
										</a>
										
									</td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>
