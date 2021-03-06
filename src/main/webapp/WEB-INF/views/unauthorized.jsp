<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/main.css">
	<script src="${ctx}/js/jquery-1.11.1.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">后台管理系统</a>
		</div>
	</div>
</div>

<div class="container">
	<div class="login-box text-center" >
		<div class="login-single-panel-header">
            <c:choose>
                <c:when test="${successMessage == null}">
                    <h3>未授权</h3>
                </c:when>
                <c:otherwise>
                    <h3>${successMessage}</h3>
                </c:otherwise>
            </c:choose>
            <a class="btn btn-primary btn-sm" href="${ctx}${forwardUrl}">返回列表</a>
		</div>
	</div>
</div>
<div class="text-center bottom">
	Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>