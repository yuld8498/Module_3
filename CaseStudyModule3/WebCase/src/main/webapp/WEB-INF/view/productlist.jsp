<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <%--    <meta charset="UTF-8">--%>
    <title>Product Management</title>
    <jsp:include page="/WEB-INF/layout/head-meta.jsp"></jsp:include>
    <link href="/assets/libs/toastr/toastr.min.css" rel="stylesheet" type="text/css">
</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">

    <!-- Navigation Bar-->
    <jsp:include page="/WEB-INF/layout/top-nav.jsp"></jsp:include>
    <!-- End Navigation Bar-->
    <div class="content-page w-100">
        <div class="row w-100 text-center">
            <h2 class="w-100 text-center">Products List</h2>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card-box table-responsive">
                    <table id="datatable" class="table table-striped table-bordered dt-responsive nowrap"
                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                        <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Product Description</th>
                            <th>Price</th>
                            <th>Quaility</th>
                            <th>Type</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${listProduct}">
                            <tr>
                                <td><c:out value="${product.productID}"/></td>
                                <td><c:out value="${product.productName}"/></td>
                                <td><c:out value="${product.productDescription}"/></td>
                                <td><c:out value="${product.price}"/></td>
                                <td><c:out value="${product.quaility}"/></td>
                                    <%--                        <td style="display:none;"><c:out value="${product.typeID}"/></td>--%>
                                <td><c:out value="${product.typeName}"/></td>
                                <td>
                                    <div><a href="/product?action=update&productID=${product.productID}">Edit</a></div>
                                    <div><a href="/product?action=delete&productID=${product.productID}">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END wrapper -->
<jsp:include page="/WEB-INF/layout/footer-js.jsp"></jsp:include>
<!-- Code injected by live-server -->

<!-- test-->
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
</body>

</html>