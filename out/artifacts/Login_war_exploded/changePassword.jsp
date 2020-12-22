<%--
  Created by IntelliJ IDEA.
  User: 87776
  Date: 2020/12/11
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>更改密码</title>
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
                <span>更改密码</span>
            </div>

            <!-- 更改密码 -->
            <div class="user">
                <img src="img/user.png">
                <input type="text" id="fusername" placeholder="请输入用户名">
            </div>

            <div class="pswd">
                <img src="img/pass.png">
                <input type="password" id="fpassword" placeholder="请输入原密码">
            </div>

            <div class="pswd">
                <img src="img/pass.png">
                <input type="password" id="fnewPassword1" placeholder="请输入新密码">
            </div>

            <div class="pswd">
                <img src="img/pass.png">
                <input type="password" id="fnewPassword2" placeholder="请再次输入新密码">
            </div>

            <div class="btn">
                <input type="button" value="确认修改" class="loginBtn" onclick="change_password();">
            </div>
        </div>
    </div>
</div>
</body>
</html>
