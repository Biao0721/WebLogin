<%--
  Created by IntelliJ IDEA.
  User: 87776
  Date: 2020/12/9
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<div class="full">
    <div class="logo">
        <img src="img/logo.png">
    </div>

    <div class="screen">
        <div id="imageBtn1" class="imageBtnBox">image1</div>
        <div id="imageBtn2" class="imageBtnBox">image2</div>
        <div id="imageBtn3" class="imageBtnBox">image3</div>
        <div id="imageBtn4" class="imageBtnBox">image4</div>
        <div class="picBox">
            <div>
                <img id="image1" class="imageBox" src="img/image1.png"/>
                <img id="image2" class="imageBox" src="img/image2.png"/>
                <img id="image3" class="imageBox" src="img/image3.png"/>
                <img id="image4" class="imageBox" src="img/image4.png"/>
            </div>
        </div>
    </div>

    <div class="right">
        <div class="wrapper">
            <div class="tabHead">
                <span id="classCol" onmousemove="changeLogin(this);">账号登录</span>
                <span id="phoneCol" onmousemove="changeLogin(this);">手机号登录</span>
            </div>

            <div class="loginAccount">
                <!-- 账号密码登录 -->
                <div class="user">
                    <img src="img/user.png">
                    <input type="text" id="fusername" placeholder="请输入用户名">
                </div>

                <div class="pswd">
                    <img src="img/pass.png">
                    <input type="password" id="fpassword" placeholder="请输入密码">
                </div>

                <div class="btn">
                    <input type="button" value="登录" class="loginBtn" onclick="login();">
                </div>
            </div>

            <div class="loginPhone" style="display: none;">
                <!-- 手机登录 -->
                <div class="user">
                    <img src="img/user.png">
                    <input type="text" id="fphoneNumber" placeholder="请输入电话号码">
                </div>

                <div class="veriCode">
                    <img src="img/pass.png">
                    <input type="text" id="fsms" placeholder="请输入短信动态码">
                    <a onclick="send_phone();">获取验证码</a>
                </div>

                <div class="btn">
                    <input type="button" class="loginBtn" value="登录" onclick="test_sms()">
                </div>
            </div>

            <a class="changeJSP" href="changePassword.jsp">修改密码</a>
            <a class="changeJSP" href="forgetName.jsp">忘记密码</a>
            <a class="changeJSP" href="createUser.jsp">账号申请</a>
        </div>
    </div>
</div>
</body>
</html>