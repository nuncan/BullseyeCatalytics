<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head>
        <!-- META SECTION -->
        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/Resources/Images/favicon.png" />">
        <!-- END META SECTION -->

        <!-- CSS INCLUDE -->
        <link rel="stylesheet" type="text/css" id="theme" href="<c:url value="/Resources/CSS/theme-dark.css" />" >
        <!-- EOF CSS INCLUDE -->
    </head>
    <body>

        <div class="login-container">

            <div class="login-box animated fadeInDown">
                <div class="login-logo"></div>
                <div class="login-body">
                    <div class="login-title"><strong>Welcome</strong>, Please login</div>
                    <c:url var="loginUrl" value="/Login" />
                    <form name="loginForm" action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.Error != null}">
			<div class="alert alert-danger">
                            <p>Invalid username and password.</p>
			</div>
                    </c:if>
                    <c:if test="${param.LoggedOut != null}">
                            <div class="alert alert-success">
				<p>You have been logged out successfully.</p>
                            </div>
                    </c:if>
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="text" name="username" class="form-control" placeholder="Username" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="password" name="password" class="form-control" placeholder="Password" required/>
                        </div>
                    </div>   
                    <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
                    <div class="form-group">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input id="remember_me" name="remember-me" type="checkbox"> Remember me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <button class="btn btn-info btn-block">Log In</button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        &copy; 2015 Bullseye.com
                    </div>
                    <div class="pull-right">
                        <a href="<c:url value="/Register"/>">Register</a>
                    </div>
                </div>
            </div>

        </div>

    </body>
</html>