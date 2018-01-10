<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<link rel="stylesheet" href="../css/login.css" >
<script src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function sub(){
	/* alert($("#text1").val());
		
		$.ajax(function(){
			url:${pageContext.request.contextPath}/userInfo,
			data:{u_name:$("#text1").val(),u_password:$("#text2").val()},
			dataType:"text",
			function(data){
				if(data==1){
					alert("登陆失败");
				}
			}
		}); */
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<div id="interface">
    <div id="main">
        <h3>账户登录</h3>
        <h3>${message}</h3>
        <div id="loginCon">
            <form action="<%=request.getContextPath() %>/userInfo" method="post">
                <p class="txt"><input type="text" id="text1" value="请输入用户名" name="u_name"/></p>
                <p class="txt"><input type="text" id="text1" value="请输入密码" name="u_password"/></p>
              	<p class="txt"><input type="submit" id="text1" class="but"/></p>
            </form>
        </div>

    </div>
</div>
</body>
</html>