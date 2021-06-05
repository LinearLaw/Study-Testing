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

    <style>
        .input-line{width: 350px;margin-bottom: 10px;
            font-size: 16px;
        }
        .input-line.ibutton{padding-left: 154px;}
        .input-line span {
            display: inline-block;
            width: 150px;
            text-align: right;
        }
        .input-line.ibutton button{width:100px;}
        
    </style>
</head>

<body>


    <div>
        <form action="">
            <div class="input-line">
                <span>用户名：</span>
                <input type="text">
            </div>
            <div class="input-line">
                <span>密码：</span>
                <input type="password">
            </div>
            <div class="input-line ibutton">
                <button>登录</button>
            </div>
        </form>

    </div>
</body>
</html>