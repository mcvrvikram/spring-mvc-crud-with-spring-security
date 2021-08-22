<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Access denied JSP</title>
</head>
<body>
	<div align="center">
		Sorry! You do not have sufficient permissions to access this page
		<br>
		<a href="${pageContext.request.contextPath}/">Go back to home</a>
	</div>

</body>
</html>