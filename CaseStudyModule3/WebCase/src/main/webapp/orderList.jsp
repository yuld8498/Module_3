<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <jsp:include page="/WEB-INF/layout/head-meta.jsp"></jsp:include>
</head>
<body data-layout="horizontal">
<div id="wrapper">
    <c:choose>
        <c:when test="${logincheck!=null}">
            <c:if test="${typeUser.equalsIgnoreCase('admin')}">
                <jsp:include page="/WEB-INF/layout/top-nav.jsp"></jsp:include>
            </c:if>
        </c:when>
        <c:otherwise>
            <jsp:include page="/WEB-INF/layout/top-nav-user.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
    <div class="content-page">
        <div class="content">
            <!-- Start Content-->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card-box">
                            <c:if test="${type.equalsIgnoreCase('admin')}">
                            <form action="/product?action=managerorder" method="post">
                                </c:if>
                                <c:if test="${!type.equalsIgnoreCase('admin')}">
                                <form action="/product?action=confirmorder" method="post">
                                    </c:if>
                                    <table class="table table-striped nowrap"
                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                        <tr class="bg-primary text-white">
                                            <c:if test="${type.equalsIgnoreCase('admin')}">
                                                <th>Order ID</th>
                                            </c:if>
                                            <th>Checked</th>
                                            <th>Product Name</th>
                                            <th>Product IMG</th>
                                            <th>Product type</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Total</th>
                                            <c:if test="${type.equalsIgnoreCase('admin')}">
                                                <th>Status</th>
                                            </c:if>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="order" items="${orderList}">
                                            <tr>
                                                <c:forEach var="product" items="${listProduct}">
                                                    <c:if test="${order.getProductID()==product.getProductID()}">
                                                        <td><input type="checkbox" value="1" name="checked"></td>
                                                        <c:if test="${type.equalsIgnoreCase('admin')}">
                                                            <td name="id">${order.getID()}</td>
                                                        </c:if>
                                                        <td>${product.getProductName()}</td>
                                                        <td style="height:80px "><img
                                                                src="images/${product.getFileName()}"
                                                                alt="" style="height: 100%"></td>
                                                        <td>${product.getTypeName()}</td>
                                                        <td>${order.getProductQuaility()}</td>
                                                        <td>${product.getPrice()}</td>
                                                        <td>${product.getPrice()*order.getProductQuaility()}</td>
                                                        <c:if test="${type.equalsIgnoreCase('admin')}">
                                                            <td>
                                                                <select name="status" id="">
                                                                    <option value="${order.getIDStatus()}">hello
                                                                    </option>
                                                                    <c:forEach items="${typeOrderList}" var="typeOrder">
                                                                        <option value="${typeOrder.getTypeOrderID()}">${typeOrder.getTypeOrderName()}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                        </div>
                        <input type="submit"
                               class="btn btn-primary btn-lg waves-effect waves-light float-right text-white"
                               value="Confirm Order" onclick="return confirm('your want order this products!')">
                        <%--                        <div>--%>
                        <%--                            <c:if test="${type.equalsIgnoreCase('admin')}">--%>
                        <%--                                <a class="btn btn-primary btn-lg waves-effect waves-light float-right text-white"--%>
                        <%--                                   href="/product?action=managerorder">Confirm Order</a>--%>
                        <%--                            </c:if>--%>
                        <%--                            <c:if test="${!type.equalsIgnoreCase('admin')}">--%>
                        <%--                                <a class="btn btn-primary btn-lg waves-effect waves-light float-right text-white"--%>
                        <%--                                   href="/product?action=confirmorder">Confirm Order</a>--%>
                        <%--                            </c:if>--%>
                        <%--                        </div>--%>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
<jsp:include page="/WEB-INF/layout/footer-js.jsp"></jsp:include>
</body>
</html>
