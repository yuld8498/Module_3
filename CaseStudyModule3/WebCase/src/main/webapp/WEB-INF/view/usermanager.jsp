<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="logo.png">
    <!-- App css -->
    <link href="assets\css\bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\app.min.css" rel="stylesheet" type="text/css">
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">
    <div class="content-page">
        <div class="content">
            <!-- Start Content-->
            <div class="container-fluid">
                <!-- end page title -->
                <div class="row">
                    <div class="col-lg-4 col-xl-4">
                        <div class="card-box text-center">
                            <img src="assets\images\users\user-1.jpg" class="rounded-circle avatar-lg img-thumbnail"
                                 alt="profile-image">
                            <h4 class="mb-0">Full Name</h4>
                            <button type="button" class="btn btn-danger btn-xs waves-effect mb-2 waves-light">Log
                                Out
                            </button>
                            <div class="text-left mt-3">
                                <h4 class="font-13 text-uppercase">Infomation :</h4>
                                <p class="text-muted mb-2 font-13"><strong>User Name :</strong> <span
                                        class="ml-2">user full name</span>
                                </p>
                                <p class="text-muted mb-1 font-13"><strong>Password :</strong> <span
                                        class="ml-2">USA</span>
                                    <a href="#" class="float-right">change</a>
                                </p>
                                <p class="text-muted mb-2 font-13"><strong>Mobile :</strong>
                                    <span class="ml-2"> user phone</span>
<%--                                    <a href="#" class="float-right">change</a>--%>
                                </p>
                                <p class="text-muted mb-2 font-13"><strong>Email :</strong> <span
                                        class="ml-2 ">user@email.domain</span>
<%--                                    <a href="#" class="float-right">change</a>--%>
                                </p>
                                <p class="text-muted mb-1 font-13"><strong>Location :</strong> <span
                                        class="ml-2">USA</span>
                                </p>
                            </div>

                        </div> <!-- end card-box -->

                    </div> <!-- end col-->

                    <div class="col-lg-8 col-xl-8">
                        <div class="card-box">
                            <form action="/users?action=changepassword" class="mt-2 mb-3" method="post">
                                <div class="input-icon">Password: </div>
                                <div class="form-items">
                                    <input class="form-control" placeholder="input now password" name="PW"></input>
                                    <div>${errospw}</div>
                                </div>
                                <div class="input-icon">New Password: </div>
                                <div class="form-items">
                                    <input class="form-control" placeholder="input now password"></input>
                                </div>
                                <div class="input-icon">Retype New Password: </div>
                                <div class="form-items">
                                    <input class="form-control" placeholder="input now password" name="newPW"></input>
                                </div>
                                <input type="submit" value="Change Password" class="btn btn-primary mt-2">
                            </form>
                            <div class="mt-2">
                                <c:if test="${requestScope.errors!=null}">
                                    <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0" role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                        <h3 class="text-center">Warning</h3>
                                        <c:forEach items="${errors}" var="String">
                                            <ul><h5>${(String.key).toUpperCase()}</h5></ul>
                                            <c:forEach items="${String.value}" var="message">
                                                <li>${message}</li>
                                            </c:forEach>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div> <!-- end card-box-->
                    </div> <!-- end col -->
                </div>
                <!-- end row-->
            </div> <!-- container -->
        </div> <!-- content -->
    </div>
</div>
<c:if test="${requestScope.success!=null}">
    <script>
        $(document).ready(function () {
            <% String message= (String) request.getAttribute("success"); %>
            var success = "<%= message %>";
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"](success)
        });
    </script>
</c:if>
<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>
<!-- Vendor js -->
<script src="assets\js\vendor.min.js"></script>
<!-- App js -->
<script src="assets\js\app.min.js"></script>
</body>
</html>