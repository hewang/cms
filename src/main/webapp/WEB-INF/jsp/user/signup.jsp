<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户注册</title>
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
            <h3>用户注册</h3>
        </div>
        <form id="theform" class="form-signup-heading" action="${ctx}/register" method="post">
            <div class="login-single-panel-header">
                <h5 style="color:red">${errorMessage}</h5>
            </div>
            <br/>
            <input type="text" id="username" name="username" autofocus="" required="" placeholder="用户邮箱" value="${userForm.username}" class="form-control">
            <br/>
            <input type="password" id="password" name="password" value="" required="" placeholder="密码" class="form-control">
            <br/>
            <input type="password" id="conPassword" name="conPassword" value="" required="" placeholder="确认密码" class="form-control">
            <br/>
            <div class="form-group">
                <div class="col-sm-12">
                    <input type="text" id="captcha" name="captcha" value="" autofocus="" required="" placeholder="验证码" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <img class="img-thumbnail" id="captchaImg" title="看不清？点击图片刷新"/>
                </div>
            </div>
            <input class="btn btn-primary btn-lg" id="sub" name="sub" value="立即注册" type="submit" />
            <a href="${ctx}/" target="_blank">用户登录</a>
        </form>
    </div>
</div>
<div class="text-center bottom">
    Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>