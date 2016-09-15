<%--
  Created by IntelliJ IDEA.
  User: wangh
  Date: 2016/8/28
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>论坛登录</title>
    <script language="JavaScript" href="assets/js/jquery-1.9.0.min.js"></script>
    <!-- Le styles -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!--<link rel="stylesheet" href="css/custom-theme/jquery-ui-1.10.3.theme.css">-->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
    <![endif]-->
    <link rel="stylesheet" href="assets/css/docs.css">
    <link rel="stylesheet" href="assets/js/google-code-prettify/prettify.css">
</head>
<body>
    <c:if test="${!empty error}">
        <p style="color:red;"><c:out value="${error}"/></p>
    </c:if>
    <form action="<c:url value="/loginCheck.html"/>" method="post">
        用户名：
        <input type="text" name="userName"><br />
        密码：
        <input type="password" name="password"><br />
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
</body>
</html>
