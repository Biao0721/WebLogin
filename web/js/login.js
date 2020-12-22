function changeLogin(elmt){
    var id = elmt.id;
    elmt.style = "color:#2B6CDB;border-bottom: 7px solid blueviolet;";
    if(id=="classCol"){
        document.getElementById("phoneCol").style = "color:black";
        document.getElementsByClassName("loginAccount")[0].style = "display:block";
        document.getElementsByClassName("loginPhone")[0].style = "display:none";
    }else if(id=="phoneCol"){
        document.getElementById("classCol").style = "color:black";
        document.getElementsByClassName("loginAccount")[0].style = "display:none";
        document.getElementsByClassName("loginPhone")[0].style = "display:block";
    }
}

function changeFunc(elmt){
    var id = elmt.id;
    elmt.style = "color:#2B6CDB;border-bottom: 7px solid blueviolet;";
    if(id=="add"){
        document.getElementById("delete").style = "color:black";
        document.getElementById("change").style = "color:black";
        document.getElementsByClassName("addUser")[0].style = "display:block";
        document.getElementsByClassName("deleteUser")[0].style = "display:none";
        document.getElementsByClassName("changeUser")[0].style = "display:none";
        document.getElementsByClassName("findUser")[0].style = "display:none";
    }else if(id=="delete"){
        document.getElementById("add").style = "color:black";
        document.getElementById("change").style = "color:black";
        document.getElementById("find").style = "color:black";
        document.getElementsByClassName("addUser")[0].style = "display:none";
        document.getElementsByClassName("deleteUser")[0].style = "display:block";
        document.getElementsByClassName("changeUser")[0].style = "display:none";
        document.getElementsByClassName("findUser")[0].style = "display:none";
    }else if(id=="change"){
        document.getElementById("add").style = "color:black";
        document.getElementById("delete").style = "color:black";
        document.getElementById("find").style = "color:black";
        document.getElementsByClassName("addUser")[0].style = "display:none";
        document.getElementsByClassName("deleteUser")[0].style = "display:none";
        document.getElementsByClassName("changeUser")[0].style = "display:block";
        document.getElementsByClassName("findUser")[0].style = "display:none";
    }else if(id=="find"){
        document.getElementById("add").style = "color:black";
        document.getElementById("delete").style = "color:black";
        document.getElementById("change").style = "color:black";
        document.getElementsByClassName("addUser")[0].style = "display:none";
        document.getElementsByClassName("deleteUser")[0].style = "display:none";
        document.getElementsByClassName("changeUser")[0].style = "display:none";
        document.getElementsByClassName("findUser")[0].style = "display:block";
    }
}

function login() {
    var username = document.getElementById("fusername").value;
    var password = document.getElementById("fpassword").value;

    if (password=="") {
        alert("密码不能为空");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/login",
            dataType: "application/json",
            data: {
                "username": username,
                "password": password
            },
            complete: function (data) {
                console.log(data);
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
                if (username=="KongDeBin") {
                    window.location.href="/Login_war_exploded/administrators.jsp"
                }
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }
}

function change_password() {
    var username = document.getElementById("fusername").value;
    var password = document.getElementById("fpassword").value;
    var newPassword1 = document.getElementById("fnewPassword1").value;
    var newPassword2 = document.getElementById("fnewPassword2").value;

    if (newPassword1 != newPassword2) {
        alert("两次密码不一致");
    } else if (newPassword1 == password) {
        alert("新密码不能与原密码一致");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/change_password",
            dataType: "application/json",
            data: {
                "username": username,
                "password": password,
                "newPassword": newPassword1,
            },
            complete: function (data) {
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }
}

function delete_user() {
    var username = document.getElementById("fusernamedelete").value;
    var password = document.getElementById("fpassworddelete").value;

    if (username=="") {
        alert("用户名不能为空");
    } else if (password=="") {
        alert("密码不能为空");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/delete_user",
            dataType: "application/json",
            data: {
                "username": username,
                "password": password,
            },
            complete: function (data) {
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });S
    }
}

function find_user() {
    var username = document.getElementById("fusernamefind").value;

    ajax({
        type: "POST",
        async: true,
        url: "http://localhost:8080/Login_war_exploded/find_user",
        dataType: "application/json",
        data: {
            "username": username,
        },
        success: function (data) {
            var receiveData = JSON.parse(data);
            if (username != "") {
                if (receiveData['password'] == "" || receiveData['phone'] == "") {
                    document.getElementById("fresult").innerHTML="查询失败";
                } else {
                    document.getElementById("fresult").innerHTML=
                        "username: " + username+ "\n" +
                        "password: " + receiveData['password'] + "\n" +
                        "phone: " + receiveData['phone'];
                    // alert("username: " + receiveData[key][0] + "\n" +
                    //       "password: " + receiveData['password'] + "\n" +
                    //       "phone: " + receiveData['phone'])
                    console.log('success', data);
                }
            } else {
                var allUser = "";
                for(var key in receiveData){
                    if (key == "errMsg") {
                        continue;
                    }
                    passwordAndPhone = receiveData[key];
                    allUser += "username: " + key + "\n" + "password: " + passwordAndPhone[0] + "\n" + "phone: " + passwordAndPhone[1] + "\n" + "\n";
                }
                document.getElementById("fresult").innerHTML=allUser;
                console.log(allUser);
                // alert(allUser);
            }
            console.log('success', data);
        },
        error: function (XMLHttpResquest, textState, errorThrown) {
            console.log('error', errorThrown);
        }
    });
}

function send_phone() {
    var phoneNumber = document.getElementById("fphoneNumber").value;

    if (phoneNumber=="") {
        alert("手机号不能为空");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/send_sms",
            dataType: "application/json",
            data: {
                "phoneNumber": phoneNumber
            },
            complete: function (data) {
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }
}

function test_sms() {
    var phoneNumber = document.getElementById("fphoneNumber").value;
    var sms = document.getElementById("fsms").value;

    if (sms=="") {
        alert("验证码不能为空");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/test_sms",
            dataType: "application/json",
            data: {
                "phone": phoneNumber,
                "sms": sms
            },
            complete: function (data) {
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }
}

function forget_password() {
    var phoneNumber = document.getElementById("fphoneNumber").value;
    var sms = document.getElementById("fsms").value;
    var newPassword1 = document.getElementById("fnewPassword1").value;
    var newPassword2 = document.getElementById("fnewPassword2").value;

    if (sms=="") {
        alert("验证码不能为空");
    } else if (newPassword1 != newPassword2) {
        alert("两次密码不一致");
    } else {
        ajax({
            type: "POST",
            async: true,
            url: "http://localhost:8080/Login_war_exploded/forget_password",
            dataType: "application/json",
            data: {
                "phone": phoneNumber,
                "sms": sms,
                "newPassword": newPassword1
            },
            complete: function (data) {
                var receiveData = JSON.parse(data);
                alert(receiveData['errMsg'])
            },
            success: function (data) {
                console.log('success', data);
            },
            error: function (XMLHttpResquest, textState, errorThrown) {
                console.log('error', errorThrown);
            }
        });
    }
}

function create_user() {
    var username = document.getElementById("fusername").value;
    var password = document.getElementById("fpassword1").value;
    var password2 = document.getElementById("fpassword2").value;
    var phone = document.getElementById("fphoneNumber").value;
    var sms = document.getElementById("fsms").value;

    if (username=="") {
        alert("用户名不能为空");
    } else if(password=="") {
        alert("密码不能为空");
    } else if(password!=password2) {
        alert("两次输入密码不一致");
    } else if (sms=="") {
        alert("验证码不能为空");
    } else {
            ajax({
                type: "POST",
                async: true,
                url: "http://localhost:8080/Login_war_exploded/create_user",
                dataType: "application/json",
                data: {
                    "username": username,
                    "password": password,
                    "phone": phone,
                    "sms": sms
                },
                complete: function (data) {
                    var receiveData = JSON.parse(data);
                    alert(receiveData['errMsg'])
                },
                success: function (data) {
                    console.log('success', data);
                },
                error: function (XMLHttpResquest, textState, errorThrown) {
                    console.log('error', errorThrown);
                }
            });
    }
}

function ajax(options) {

    //非空处理
    options = options || {};

    //处理参数，给出默认值

    //请求方式
    options.type = options.type.toUpperCase() || 'POST';
    //是否异步
    options.async = options.async || true;
    //请求地址
    options.url = options.url || window.location.href;
    //请求参数
    options.data = options.data || '';
    //响应格式
    options.dataType = options.dataType || 'application/json';
    //超时时间
    options.timeout = options.timeout || 10000;
    //请求完成后的回调
    options.complete = options.complete || function(){};
    //请求成功后的回调
    options.success = options.success || function(){};
    //请求失败后的回调
    options.error = options.error || function(){};

    //初始化异步对象
    var xhr = new XMLHttpRequest();

    //参数处理
    // var data = params(options.data);
    console.log(JSON.stringify(options.data, null, ' '))
    var data = JSON.stringify(options.data)

    //需要判断get和post
    if (options.type === 'GET') {
        //打开请求，如果url已经有参数了，直接追加，没有从问号开始拼接
        if (options.url.indexOf('?') !== -1) {
            xhr.open(options.type, options.url + '&' + data);
        } else {
            xhr.open(options.type, options.url + '?' + data);
        }
        //发送请求，因为参数都跟在url后面，所以不用在send里面做任何处理
        xhr.send();
    }
    if (options.type === 'POST') {
        //打开请求
        xhr.open(options.type, options.url, options.async);
        //设置请求头，如果不写，就是浏览器默认，就是下面这个
        xhr.setRequestHeader('Content-type', 'application/json');
        //发送请求，post请求的时候，会将data中的参数按照&拆分出来
        xhr.send(data);
    }
    

    //解析结果
    xhr.onreadystatechange = function() {

        //readyState五个状态 0:未发送请求，1:已发送请求，2:已经握手，3:正在处理请求，4:请求处理完成

        //0：请求未初始化（还没有调用 open()）;
        //1：请求已经建立，但是还没有发送（还没有调用 send()）;
        //2：请求已发送，正在处理中（通常现在可以从响应中获取内容头）;
        //3：请求在处理中，通常响应中已有部分数据可用了，但是服务器还没有完成响应的生成;
        //4：响应已完成，您可以获取并使用服务器的响应了;

        console.log('xhr', xhr);

        if (xhr.readyState === 4) {

            options.complete(xhr.responseText);

            if (xhr.status === 200) {
                options.success(xhr.responseText);
            } else {
                options.error(xhr, xhr.status, xhr.statusText);
            }
        }
    }
}