<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <%--    <meta charset="UTF-8">--%>
    <title>Product Management</title>
    <jsp:include page="/WEB-INF/layout/head-meta.jsp"></jsp:include>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">
    <!-- Navigation Bar-->
    <!-- End Navigation Bar-->
    <div class="content-page">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card-box">
                            <button class="btn btn-primary text-white">
                                <%--            <div class="nav-link waves-effect waves-light">--%>
                                <a href="/product?action=create" class="nav-link waves-effect waves-light text-white">
                                    Create Product
                                </a>
                                <%--            </div>--%>
                            </button>
                            <table class="table table-striped nowrap"
                                   style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                <tr class="w-100">
                                    <h3 class="text-white m-3">Product List</h3>
                                    <span class="float-right">
                                        <form method="post" action="/product?action=filter">
                                                    <label>Select by Type: </label>
                                                    <select name="categoryID">
                                                        <option value="-1">ALL</option>
                                                        <c:forEach items="${applicationScope.listCategory}"
                                                                   var="category">
                                                            <option value="${category.getCategoryID()}">${category.getCategoryName()}</option>
                                                        </c:forEach>
                                            </select>
                                            <input type="submit" value="Submit" class="bg-primary text-white">
                                        </form>
                                    </span>
                                </tr>
                                <thead>
                                <tr class="bg-primary text-white">
                                    <th>#</th>
                                    <th>Product Name</th>
<%--                                    <th>Product Description</th>--%>
                                    <th>Price</th>
                                    <th>Quaility</th>
                                    <th>Color</th>
                                    <th>Type</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="product" items="${listProduct}">
                                    <tr>
                                        <td><c:out value="${product.getProductID()}"/></td>
                                        <td><c:out value="${product.getProductName()}"/></td>
<%--                                        <td><c:out value="${product.getDescription()}"/></td>--%>
                                        <td><c:out value="${product.getPrice()}"/></td>
                                        <td><c:out value="${product.getQuantity()}"/></td>
                                        <td><c:out value="${product.getColor()}"/></td>
                                        <td><c:out value="${product.getCategoryID()}"/></td>
                                        <td>
                                            <a href="/product?action=update&productID=${product.productID}"
                                               class="w-45 float-left">
                                                <i class="la la-gear"></i>
                                                <span>Edit</span>
                                            </a>
                                            <a href="/product?action=delete&productID=${product.productID}"
                                               class="float-right w-45">
                                                <i class="la la-trash"></i>
                                                <span>Delete</span>
                                            </a>
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
    </div>
</div>
<jsp:include page="/WEB-INF/layout/footer-js.jsp"></jsp:include>
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