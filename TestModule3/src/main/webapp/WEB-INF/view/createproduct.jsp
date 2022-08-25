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
<body  data-layout="horizontal">
<div id="wrapper">
  <div class="content-page">
    <div class="content">
      <!-- Start Content-->
      <div class="container-fluid">
        <button class="btn btn-primary text-white">
          <%--            <div class="nav-link waves-effect waves-light">--%>
          <a href="/product?action=list" class="nav-link waves-effect waves-light text-white">
            List Product
          </a>
          <%--            </div>--%>
        </button>
        <div class="row">
          <c:choose>
            <c:when test="${action.equals('create')}">
              <div><h3 class="w-100 text-center m-2"> Create Product</h3></div>
            </c:when>
            <c:otherwise>
              <div><h3 class="w-100 text-center m-2"> Edit Product</h3></div>
            </c:otherwise>
          </c:choose>
          <div class="col-lg-12">
            <form method="post">
              <div class="form-group" style="display: none">
                <label>product ID: </label>
                <input type="text" class="form-control" name="productID" id="productID" size="45" value="${product.getProductID()}"/>
              </div>
              <div class="form-group">
                <label>product Name: </label>
                <input type="text" class="form-control" name="productName" id="productName" size="45" value="${product.getProductName()}"/>
              </div>
              <div class="form-group">
                <label>Price: </label>
                <input type="text" class="form-control" name="price" id="price" size="45" value="${product.getPrice()}"/>
              </div>
              <div class="form-group">
                <label >Description: </label>
                <input type="text" class="form-control" name="productDescription" id="productDescription" size="45" value="${product.getDescription()}"/>
              </div>
              <div class="form-group">
                <label>Quaility: </label>
                <input type="number" class="form-control" name="quantity" id="quantity" size="45" value="${product.getQuantity()}"/>
              </div>
              <div class="form-group">
                <label>Color: </label>
                <input type="text" class="form-control" name="color" id="color" size="45" value="${product.getColor()}"/>
              </div>
              <div class="form-group">
                <label >Category: </label>
                <select name="categoryID">
                  <c:forEach items="${applicationScope.listCategory}" var="category">
                  <option value="${category.getCategoryID()}">${category.getCategoryName()}</option>
                  </c:forEach>
              </div>
              <div class="form-group">
                <span><input type="submit" value="Submit" class="form-control w-25 bg-primary text-white"></span>
                <span><a href="/product?action=list" class="form-control w-25 bg-primary text-white text-center">Back</a></span>
              </div>
            </form>
          </div>
          <div class="text-danger mt-2 w-100">
            <c:if test="${requestScope.errors!=null}">
              <div class="alert alert-danger alert-dismissible bg-danger text-white border-0 fade show" role="alert">
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
            <c:if test="${errorsprice!=null}">
              <<div class="alert alert-danger alert-dismissible bg-danger text-white border-0 fade show" role="alert">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
              <ul>
                <li>${errorsprice}</li>
              </ul>
              </div>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/layout/footer-js.jsp"></jsp:include>
</body>
</html>
