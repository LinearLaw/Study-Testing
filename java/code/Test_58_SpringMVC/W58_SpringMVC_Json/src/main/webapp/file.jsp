<%--
  Created by IntelliJ IDEA.
  User: Linear
  Date: 2021/6/27
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>58.5、文件上传</title>
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
            <span class="input-title">选择文件</span>
            <input type="file" id="ulfile">
        </div>


        <div class="input-line ibutton"><button id="submit8">上传文件</button></div>
        <script>
            $("#submit8").click(()=>{
                
                let fda = new FormData();
                fda.append("uploadFile",$("#ulfile")[0].files[0]);

                $.ajax({
                    url:"/upload/local",
                    type:"post",
                    processData:false,
                    contentType:false,
                    // contentType:"multipart/form-data",
                    data:fda,
                    success:function(res){ console.log(res); }
                })
            })
        </script>

        <div class="input-line ibutton"><button id="submit9">跨服务器上传</button></div>
        <script>
            $("#submit9").click(()=>{
                
                let fda = new FormData();
                fda.append("upload",$("#ulfile")[0].files[0]);  

                $.ajax({
                    url:"/upload/server",
                    type:"post",
                    processData:false,
                    contentType:false,
                    // contentType:"multipart/form-data",
                    data:fda,
                    success:function(res){ console.log(res); }
                })
            })
</script>
    </div>

</body>
</html>

