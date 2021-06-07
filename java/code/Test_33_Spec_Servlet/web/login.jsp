<%--
  Created by IntelliJ IDEA.
  User: Linear
  Date: 2021/6/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>$Title$</title>

    <link rel="stylesheet" href="./common.css">
</head>

<body>
    <h1>登录</h1>
    <div>
        <div class="input-line">
            <span class="input-title">用户名：</span>
            <input type="text" id="username">
        </div>
        <div class="input-line">
            <span class="input-title">输入密码：</span>
            <input type="password" id="password">
        </div>

        <div class="input-line">
            <span class="input-title">输入验证码：</span>
            <input type="text" class="input-charcode" id="charcode">
            <img src="" class="char-code" id="charImg" alt="输入验证码" />
        </div>

        <div class="input-line ibutton">
            <button id="submit">登录</button>
        </div>
        <div class="input-line">
            <span class="input-title"></span>
            <span class="notice_s" style="display: none;" id="snotice">
                登录成功
            </span>
            <span class="notice_f" style="display: none;" id="snotice_f">
                登录失败
            </span>
        </div>

        <div class="input-line">
            <span class="input-title"></span>
            <a class="input-link" href="/register.jsp">注册账号</a>
            <a class="input-link" href="/">回到首页</a>
        </div>

    </div>

    <script src='./jquery-3.3.1.min.js'></script>
    <script>
        function init(){
            let t = new Date().getTime();
            $("#charImg").attr("src","/getCharCode?"+t);
        }

        init();
        $("#charImg").click(init);

        $("#submit").click(()=>{
            let username = $("#username").val();
            let password = $("#password").val();
            let charcode = $("#charcode").val();
            $("#snotice").hide();
            $("#snotice_f").hide();

            if(password){
                $.ajax({
                    url:"/doLogin",
                    method:"post",
                    data:{username,password,charcode},
                    success:function(res){
                        console.log(res);
                        var resp = JSON.parse(res);
                        if(resp.code == 1){
                            $("#snotice").text(resp.msg);
                            $("#snotice").show();
                        }else{
                            $("#snotice_f").text(resp.msg);
                            $("#snotice_f").show();
                        }
                    }
                })
            }
        })
    </script>

</body>
</html>