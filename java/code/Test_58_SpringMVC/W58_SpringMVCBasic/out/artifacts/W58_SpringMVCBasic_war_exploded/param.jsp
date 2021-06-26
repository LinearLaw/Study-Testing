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

        <div class="input-line ibutton"><button id="submit1">基本类型</button></div>
        <div class="input-line ibutton"> <button id="submit2">Account类</button></div>
        <div class="input-line ibutton"> <button id="submit3">自定义类型转换</button></div>
        <div class="input-line ibutton"> <button id="submit4">list类型转换</button></div>


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

    <script src="js/jquery-3.3.1.min.js"></script>
    <script>

        /* 提交表单信息 - 基本类型 */
        $("#submit1").click(()=>{
            let name = $("#username").val();
            let password = $("#password").val();

            $.ajax({ url:"/param/basic", method:"post",
                data:{name,password},
                success:function(res){ console.log(res); }
            })
        })

        /* JavaBean类型 */
        $("#submit2").click(()=>{
            let name = $("#username").val();
            let password = $("#password").val();

            $.ajax({ url:"/param/bean", method:"post",
                data:{name,password},
                success:function(res){ console.log(res); }
            })
        })

        /* date类型 */
        $("#submit3").click(()=>{
            let name = $("#username").val();
            let age = $("#password").val();
            let date = $("#date").val();

            $.ajax({ url:"/param/convert", method:"post",
                data:{name,age,date},
                success:function(res){ console.log(res); }
            })
        })

        /* list类型 */
        $("#submit4").click(()=>{
            let obj = {
                name: $("#username").val(),
                age : $("#password").val(),
                date: $("#date").val(),
                "list[0].username":"aaa",
                "list[0].money":"123",
                "list[1].username":"bbb",
                "list[1].money":"3",
            }
            $.ajax({ url:"/param/list", method:"post",
                data:obj,
                success:function(res){ console.log(res); }
            })
        })

    </script>
</body>
</html>
