<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="false"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Dev :: Index</title>
        
        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/Resources/Images/favicon.png" />">
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${not empty user}">
            </br>You Are Identified As: <strong>${user}</strong>
        </c:if>
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
    </body>
</html>
