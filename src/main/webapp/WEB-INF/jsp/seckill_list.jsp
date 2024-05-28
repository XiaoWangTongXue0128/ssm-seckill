<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>秒杀列表</title>

    <%--    把公用的内容单独翻入到head中去--%>
    <%@include file="common/head.jsp" %>
</head>
<body>
<div class="container">

    <div class="panel panel-danger">
        <!-- Default panel contents -->
        <div class="panel-heading">秒杀列表</div>

        <!-- Table -->
        <table class="table">
            <thread>
                <tr>
                    <td>名称</td>
                    <td>库存</td>
                    <td>价格</td>
                    <td>开始时间</td>
                    <td>结束时间</td>
                    <td>商品详情</td>
                </tr>
            </thread>
            <tbody>

            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.number}</td>
                    <td>${item.price}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.startTime}"></fmt:formatDate></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.endTime}"></fmt:formatDate></td>
                    <td><a href="/seckill/detail/${item.id}">进入秒杀</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
