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

        <div class="input-line ibutton"><button id="submit1">RequestParam</button></div>
        <div class="input-line ibutton"> <button id="submit2">RequestBody</button></div>
        <script>
            /* @RequestParam 用来进行参数映射 */
            $("#submit1").click(()=>{
                let username = $("#username").val();
                let password = $("#password").val();
            
                $.ajax({ url:"/anno/reqparam", method:"post",
                    data:{username,password},
                    success:function(res){ console.log(res); }
                })
            })
            /* @RequestBody 用来传递整个的请求体 */
            $("#submit2").click(()=>{
                let username = $("#username").val();
                let password = $("#password").val();
    
                $.ajax({ url:"/anno/reqbody", method:"post",
                    data:{username,password},
                    success:function(res){ console.log(res); }
                })
            })
        </script>
        
        <div class="input-line ibutton"> <button id="submit3">PathVariable</button></div>
        <div class="input-line ibutton"> <button id="submit4">RequestHeader</button></div>
        <script>
            /* @PathVariable 用来获取动态路由的参数 */
            $("#submit3").click(()=>{
                let name = $("#username").val();
                let age = $("#password").val();
                let date = $("#date").val();
    
                $.ajax({ url:"/anno/path/123135", method:"post",
                    data:{name,age,date},
                    success:function(res){ console.log(res); }
                })
            })
    
            /* @RequestHeader 获取header参数 */
            $("#submit4").click(()=>{
                let obj = {
                    name: $("#username").val(),
                    age : $("#password").val(),
                    date: $("#date").val(),
                }
                $.ajax({ 
                    url:"/anno/reqheader", 
                    method:"post",
                    headers:{
                        token:"xxasfsrewr23"
                    },
                    data:obj,
                    success:function(res){ console.log(res); }
                })
            })
    
        </script>

        <div class="input-line ibutton"> <button id="submit5">CookieValue</button></div>
        <div class="input-line ibutton"> <button id="submit6">ModleAttribute</button></div>
        <script>
            $("#submit5").click(()=>{
                let obj = {
                    name: $("#username").val(),
                    age : $("#password").val(),
                    date: $("#date").val(),
                }
                $.ajax({ url:"/anno/reqcookie", method:"post",
                    data:obj,
                    success:function(res){ console.log(res); }
                })
            })
        </script>    

        <div class="input-line ibutton"> <button id="submit7">ServletAPI</button></div>
        <script>
            $("#submit7").click(()=>{
                let obj = {
                    name: $("#username").val(),
                    age : $("#password").val(),
                    date: $("#date").val(),
                }
                $.ajax({ url:"/anno/servletapi", method:"post",
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
