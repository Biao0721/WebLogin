<%--
  Created by IntelliJ IDEA.
  User: 87776
  Date: 2020/12/19
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新建用户</title>
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
                <span>新建用户</span>
            </div>

            <!-- 新建用户 -->
            <div class="user">
                <img src="img/user.png">
                <input type="text" id="fusername" placeholder="请输入用户名">
            </div>

            <div class="pswd">
                <img src="img/pass.png">
                <input type="password" id="fpassword1" placeholder="请输入密码">
            </div>

            <div class="pswd">
                <img src="img/pass.png">
                <input type="password" id="fpassword2" placeholder="请输入密码">
            </div>

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
                <input type="button" value="新建用户" class="loginBtn" onclick="create_user();">
            </div>
        </div>
    </div>
</div>
</body>
</html>

