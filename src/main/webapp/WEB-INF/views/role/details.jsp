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


<div class="content layout-top-margin ">
	<h3>
		<spring:message code="app.layout.header.roles" />
	</h3>
	
	<div class="form-horizontal my-form-margin-top">

		<div class="form-group">
			<label class="col-sm-1 my-label-form control-label">
				<spring:message code="app.role.name" />
			</label>
			<div class="col-sm-10 control-label text-align-left">
				${roleViewModel.role.name}
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-1 my-form-offset col-sm-2">
				
				<a class="btn btn-primary" href="/spring-app/role/">
					<spring:message code="app.general.back" />
				</a>
				
			</div>
		</div>
	
	</div>

</div>




</body>
</html>