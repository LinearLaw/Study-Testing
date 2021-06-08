<%--
  Created by IntelliJ IDEA.
  User: Linear
  Date: 2021/6/8
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title></title>
    <link rel="stylesheet" href="common.css">
</head>

<body>

    <div>
        <div class="input-line">
            <span class="input-title">用户名：</span>
            <input type="text" id="username">
        </div>
        <div class="input-line">
            <span class="input-title">密码：</span>
            <input type="password" id="password">
        </div>
        <div class="input-line ibutton">
            <button id="submit">提交</button>
        </div>

        <div class="input-line">
            <span class="input-title"></span>
            <span class="notice_s" style="display: none;" id="snotice">
                提交成功
            </span>
            <span class="notice_f" style="display: none;" id="snotice_f">
                提交失败
            </span>
        </div>

        <div class="input-line">
            <span class="input-title"></span>
            <a class="input-link" href="/">回到首页</a>
        </div>
    </div>
    <script src='./jquery-3.3.1.min.js'></script>
    <script>

        /* 提交表单信息 */
        $("#submit").click(()=>{
            let username = $("#username").val();
            let password = $("#password").val();
            $("#snotice").hide();
            $("#snotice_f").hide();

            if(password){
                let data = {
                    info:JSON.stringify({username,password})
                }
                $.ajax({
                    url:"/submitUserInfo",
                    method:"post",
                    data,
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