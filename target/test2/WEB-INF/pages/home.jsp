<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kingwu
  Date: 14/12/2016
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittr</title>

</head>
<body>
    <h1>Welcome to Spittr</h1>
    <a href="<c:url value="/spittles"/>">Spittles</a> |
    <a href="<c:url value="/spitter/register"/>">Register</a>
</body>
</html>
