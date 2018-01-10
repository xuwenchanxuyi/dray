<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/js/echarts-all.js"></script>


<!--
action="<%=request.getContextPath() %>/addCustomer"
 method="post"
  -->
  
<script type="text/javascript">
function subabc(){
	alert("---");

	var name=$("#name").val();

	var age=$("#age").val();
	
	var sex=$(".sex").val();
	
	var mobile=$("#mobile").val();
	
	var email=$("#email").val();

	var birthday=$("#birthday").val();

	var state=$("#state").val();
	
	$.ajax({
		url:"http://localhost:8568/crmproject/addCustomer",//请求路径
		type:"POST",/* 请求方式 */
		data:{"c_name":name,"c_age":age,"c_sex":sex,"c_mobile":mobile,"c_email":email,"c_birthday":birthday,"c_state":state},//传输数据
		dataType:"text",//返回的数据类型
		success:function(test){
			
			if(test == 1){
				alert("添加成功");
			}
			else{
				alert("添加失败");
			}
		},
	})
}

	

</script>
  
  
  
</head>
<body><br><br>
<div style="text-align: center" ><span>${message}</span></div>
<p style="text-align: center; margin-left: 2%">新增用户信息如入如下:</p>
<form  method="POST" style="text-align: center;margin-top: -2%"><br><br>
用户姓名：<input name="c_name" id="name" type="text" ><br><br>
用户年龄：<input name="c_age" type="text"  id="age"><br><br>
<p style="margin-left: -7%">
用户性别：
<input type="radio" class="sex" value="男" name="c_sex">男
<input type="radio" value="女" class="sex" name="c_sex">女</p>
用户手机：<input name="c_mobile" type="text" id="mobile" ><br><br>
用户邮箱：<input name="c_email" type="text" id="email" ><br><br>
用户生日：<input name="c_birthday" type="text" id="birthday"><br><br>
用户状态：<!-- <input name="c_state" type="text" id="state" value="新增未上门" readonly="readonly"><br><br> -->
<select name="c_state" id="state">
<option value="新增未上门">新增未上门</option>
<option value="上门">上门</option>
<option value="未通">未通</option>
<option value="死单">死单</option>
<option value="紧跟">紧跟</option>
</select><br><br>
<input onclick="subabc()" type="button" value="用户信息提交" class="btn bste btn-small">
</form>
</body>

</html>