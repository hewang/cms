<%--
  Created by IntelliJ IDEA.
  User: JSH
  Date: 2014/6/25
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>欢迎</title>
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="keywords" content="" />
        <meta http-equiv="description" content="" />
    </head>
    <body>
        <div>
            <div>用户名：${user.username}</div>
            <div><a href="${ctx}/userList">用户列表</a></div>
        </div>
    </body>
</html>

