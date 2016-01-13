<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
	<title>Access Denied Page</title>
        
        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/Resources/Images/favicon.png" />">
</head>
<body>
	You Are Identified As: <strong>${Username}</strong>
        </br>
        You are not authorized to access this page.
	<a href="<c:url value="/Logout" />">Logout</a>
</body>
</html>