<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/29
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list pages</title>
</head>
<body>
<h3>congratuation for you that builde successful</h3>
    <c:forEach items="${accounts}" var="list">
        ${list.name}
    </c:forEach>
</body>
</html>
