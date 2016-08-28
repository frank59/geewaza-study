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
    <mata http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>欢迎！</title>
</head>
<body>
    ${user.userName},欢迎您进入论坛，您的当前积分为${user.credits};
</body>
</html>
