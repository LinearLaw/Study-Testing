<%--
  Created by IntelliJ IDEA.
  User: Linear
  Date: 2021/6/26
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
    <script src="js/jquery-3.3.1.min.js"></script>

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
            <span class="input-title">日期：</span>
            <input type="text" id="date">
        </div>

        <div class="input-line ibutton"> <button id="submit8">json对象返回</button></div>
        <script>
            $("#submit8").click(()=>{
                let obj = {
                    name: $("#username").val(),
                    age : $("#password").val(),
                    date: $("#date").val(),
                }
                $.ajax({
                    url:"/json/user",
                    method:"post",
                    data:obj,
                    success:function(res){ console.log(res); }
                })
            })
        </script>

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
            <a class="input-link" href="/">回到首页</a>
        </div>

    </div>

    
    
</body>
</html>
