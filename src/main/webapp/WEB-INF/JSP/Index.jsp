<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="false"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Dev :: Index</title>
        
        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/Resources/Images/favicon.png" />">
    </head>
    <body>
        <h1>Hello <strong>${user}</strong></h1>
        <a href="<c:url value="/Install"/>">Install</a>
        <br/>
        <a href="<c:url value="/Dashboard"/>">Dashboard</a>
        <br/>
        <a href="<c:url value="/Denied"/>">Denied</a>
        <br/>
        <a href="<c:url value="/Login"/>">Login</a>
        <br/>
        <a href="<c:url value="/Register"/>">Register</a>
        <br/>
        <a href="<c:url value="/Logout"/>">Logout</a>
        <br/>
        <strong>${RoleList}</strong>
    </body>
</html>
