<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <form>
        <table width="100%" border=1>
            <tr>
                <td>商品名称</td>
                <td> ${item.name } </td>
            </tr>
            <tr>
                <td>商品价格</td>
                <td> ${item.price } </td>
            </tr>
            <tr>
                <td>生成日期</td>
                <c:if test="${item.createtime}!=null">
                    <td> <fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                </c:if>
                <c:if test="${item.createtime}==null">
                    <td></td>
                </c:if>
            </tr>
            <tr>
                <td>商品简介</td>
                <td>${item.detail} </td>
            </tr>
        </table>
    </form>
</body>
</html>
