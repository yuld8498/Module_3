<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/08/2022
  Time: 4:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    table{
        width: 100%;
    }
    td{
        margin-left: 50px;
        width: 20%;
    }
    tr:first-child{
        padding-left: 50px;
    }
</style>
<body>
<table>
    <thead>
    <th colspan="4"><h1>Danh Sách Khách Hàng</h1></th>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listUser}" var="u">
        <tr>
            <td><c:out value="${u.getName()}"></c:out></td>
            <td><c:out value="${u.getDob()}"></c:out></td>
            <td><c:out value="${u. getAddress()}"></c:out></td>
            <td><img src="${u.getImgProfile()}" alt="#"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
