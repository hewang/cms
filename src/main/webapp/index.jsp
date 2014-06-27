<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
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
    <script type="text/javascript">
        $(function() {
            var captchaUrl = "${ctx}/kaptcha?";
            $('#captchaImg').prop('src', captchaUrl + (new Date()).getTime());
            $('#captchaImg').click(function () {
                $(this).prop('src', captchaUrl + (new Date()).getTime());
            });
        });
    </script>
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
            <h3>用户登录</h3>
        </div>
        <div class="login-single-panel-header">
            <h5 style="color:red">${errorMessage}</h5>
        </div>
        <form id="theform" class="form-signin-heading" action="${ctx}/login" method="post">
            <br/>
            <input type="text" id="username" name="username" autofocus="" required="" placeholder="用户名" class="form-control">
            <br/>
            <input type="password" id="password" name="password" required="" placeholder="Password" class="form-control">
            <br/>
            <input type="text" id="captcha" name="captcha" autofocus="" required="" placeholder="验证码" class="form-control width-270">
            <img class="img-thumbnail" id="captchaImg" title="看不清？点击图片刷新"/>
            <br/>
            <input class="btn btn-primary btn-lg" id="sub" name="sub" value="立即登录" type="submit" />
            <a href="${ctx}/signup" target="_blank">用户注册</a>
        </form>
    </div>
</div>
<div class="text-center bottom">
    Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>