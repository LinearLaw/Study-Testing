<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
帐户列表
<table border="1">
    <tr>
        <td>id</td>
        <td>帐户名称</td>
        <td>帐户金额</td>
    </tr>
    <c:forEach items="${list}" var="acc">
    <tr>
        <td>${acc.id}</td>
        <td>${acc.name}</td>
        <td>${acc.money}</td>
    </tr>
    </c:forEach>

    <a href="/">回到首页</a>
</table>
</body>
</html>