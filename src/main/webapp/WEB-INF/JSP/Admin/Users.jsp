<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Users</title>
        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/Resources/Images/favicon.png" />">
        <link rel="stylesheet" type="text/css" id="theme" href="<c:url value="/Resources/CSS/theme-dark.css" />" >
    </head>
    <body>
        
        <div class="page-container">
            <div class="page-sidebar">
                <ul class="x-navigation">
                    <li class="xn-logo">
                        <a href="<c:url value="/Dashboard"/>"></a>
                        <a href="#" class="x-navigation-control"></a>
                    </li>
                    <li class="xn-profile">
                        <a href="#" class="profile-mini"><img src="<c:url value="/Resources/Images/default_avatar.png" />"/></a>
                        <div class="profile">
                            <div class="profile-image">
                                <img src="<c:url value="/Resources/Images/default_avatar.png" />"/>
                            </div>
                            <div class="profile-data">
                                <div class="profile-data-name">${Username}</div>
                                <div class="profile-data-title">${Roles}</div>
                            </div>
                            <div class="profile-controls">
                                <a href="#" class="profile-control-left"><span class="fa fa-info"></span></a>
                                <a href="#" class="profile-control-right"><span class="fa fa-envelope"></span></a>
                            </div>
                        </div>
                    </li>
                    <!-- Navigation Tab -->
                    <li class="xn-title">Navigation</li>
                    <li><a href="<c:url value="/Dashboard"/>"><span class="fa fa-desktop"></span> <span class="xn-text">Dashboard</span></a></li>
                    
                    <!-- Admin Controls Tab (Locked Down By Spring Security)-->
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="xn-title">Admin Controls</li>
                        <li class="active"><a href="#"><span class="fa fa-cogs"></span> <span class="xn-text">Settings</span></a>
                        <ul>
                            <li class="active"><a href="<c:url value="/Dashboard/Admin/Users"/>"><span class="fa fa-users"></span> Users</a></li>
                            <li><a href="<c:url value="/Dashboard/Admin/Roles"/>"><span class="fa fa-list-alt"></span> Roles</a></li>
                            <li><a href="<c:url value="/Dashboard/Admin/Global"/>"><span class="fa fa-globe"></span> Global Settings</a></li>
                        </ul>
                        </li>
                    </security:authorize>
                </ul>
            </div>

            <div class="page-content">
                <ul class="x-navigation x-navigation-horizontal x-navigation-panel">
                    <!-- Messages Button -->
                    <li class="xn-icon-button"><a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a></li>
                    <!-- Sign Out Button -->
                    <li class="xn-icon-button pull-right"><a href="#" class="mb-control" data-box="#mb-signout"><span class="fa fa-sign-out"></span></a></li>
                    <li class="xn-icon-button pull-right">
                        <a href="#"><span class="fa fa-comments"></span></a>
                        <div class="informer informer-danger">1</div>
                        <div class="panel panel-primary animated zoomIn xn-drop-left xn-panel-dragging">
                            <div class="panel-heading">
                                <h3 class="panel-title"><span class="fa fa-comments"></span> Messages</h3>
                                <div class="pull-right">
                                    <span class="label label-danger">1 new</span>
                                </div>
                            </div>
                            <div class="panel-body list-group list-group-contacts scroll" style="height: 200px;">
                                <a href="#" class="list-group-item">
                                    <div class="list-group-status status-offline"></div>
                                    <img src="<c:url value="/Resources/Images/default_avatar.png" />" class="pull-left" alt="Darth Vader"/>
                                    <span class="contacts-title">Darth Vader</span>
                                    <p>I want my money back!</p>
                                </a>
                            </div>
                            <div class="panel-footer text-center">
                                <a href="pages-messages.html">Show all messages</a>
                            </div>
                        </div>
                    </li>
                </ul>

                <ul class="breadcrumb">
                    <li>Home</li>
                    <li>Dashboard</li>
                    <li class="active">User Management</li>
                </ul>

                <div class="page-content-wrap">
                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${!empty UserList}">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">User Information</h3>
                                        <ul class="panel-controls">
                                            <li><a href="#" class="panel-collapse"><span class="fa fa-angle-down"></span></a></li>
                                            <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                            <li><a href="#" class="panel-remove"><span class="fa fa-times"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                        <table class="table datatable_simple">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>

                                                </tr>
                                            </thead>
                                            <c:forEach items="${UserList}" var="Users">
                                                <tbody>
                                                    <tr>
                                                        <td>${Users.ID}</td>

                                                    </tr>
                                                </tbody>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                    
        <div class="message-box animated fadeIn" data-sound="alert" id="mb-signout">
            <div class="mb-container">
                <div class="mb-middle">
                    <div class="mb-title"><span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?</div>
                    <div class="mb-content">
                        <p>Are you sure you want to log out?</p>
                        <p>Press No if you want to continue working. Press Yes to logout of your current session.</p>
                    </div>
                    <div class="mb-footer">
                        <div class="pull-right">
                            <a href="<c:url value="/Logout"/>" class="btn btn-success btn-lg">Yes</a>
                            <button class="btn btn-default btn-lg mb-control-close">No</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <audio id="audio-alert" src="<c:url value="/Resources/Audio/alert.mp3" />" preload="auto"></audio>
        <audio id="audio-fail"  src="<c:url value="/Resources/Audio/fail.mp3"/>" preload="auto"></audio>

        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/jquery/jquery.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/jquery/jquery-ui.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/bootstrap/bootstrap.min.js" />"></script>

        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/icheck/icheck.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins/datatables/jquery.dataTables.min.js" />"></script>

        <script type="text/javascript" src="<c:url value="/Resources/JS/plugins.js" />"></script>
        <script type="text/javascript" src="<c:url value="/Resources/JS/actions.js" />"></script>
    </body>
</html>