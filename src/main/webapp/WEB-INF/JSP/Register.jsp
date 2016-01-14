<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head>
        <!-- META SECTION -->
        <title>Register</title>
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

        <!-- Start: Login Container -->
        <div class="login-container">
            <div class="login-box animated fadeInDown">
                <div class="login-logo"></div>
                <div class="login-body">
                    <c:url var="registrationUrl" value="/Register" />
                    <form id="Registration_Form" name="Registration_Form" action="${registrationUrl}" method='post' class="form-horizontal">
                        <c:if test="${param.Error != null}">
                            <div class="alert alert-danger">
                                <p>An Error Occurred, Please Try Again.</p>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="Username" placeholder="Username" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="Email" placeholder="Email" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="password" class="form-control" name="Password" id="Password1" placeholder="Password" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <input type="password" class="form-control" name="Re-Password" id="Password2" placeholder="Re-enter Your Password" required/>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <button class="btn btn-info btn-block">Register</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        &copy; 2015 Bullseye.com
                    </div>
                    <div class="pull-right">
                        <a href="<c:url value="/Login"/>">Login</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- End: Login Container -->
        
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/jquery/jquery.min.js" />"></script>        
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/jquery/jquery-ui.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/bootstrap/bootstrap.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/jquery-validation/jquery.validate.js" />"></script>        
        
        <script type="text/javascript">
            var jvalidate = $("#Registration_Form").validate(
            {
                ignore: [],
                rules: {
                        Username: {
                                required: true,
                                minlength: 4,
                                maxlength: 32
                        },
                        Password: {
                                required: true,
                                minlength: 6,
                                maxlength: 32
                        },
                        'Re-Password': {
                                required: true,
                                minlength: 6,
                                maxlength: 32,
                                equalTo: "#Password1"
                        },
                        Email: {
                                required: true,
                                email: true
                        }
                    }
                });

        </script>
    </body>
</html>