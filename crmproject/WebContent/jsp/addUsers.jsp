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

function ch(){
	var sel=$("#dept2").val();
	alert(sel);
	$.ajax({
		url:"<%=request.getContextPath()%>/getAdminPosition",
		data:{"sel":sel},
		dataType:"JSON",
		success:function(data){
			$.each(data,function(i,obj){
				$("#position").append("<option value="+obj.position+">"+obj.position+"</option>");
			});
		}
	})
}
	


</script>
  
  
  
</head>
<body><br><br>
<div>

	<div style="text-align: center" ></div>
		<p style="text-align: center; margin-left: 2%">管理员添加用户:</p>
			<form  method="POST" style="text-align: center;margin-top: -2%"><br><br>
				姓名:<input name="u_name" id="name" type="text" ><br><br>
					<p style="margin-left: -7%">
				性别:<input type="radio" class="sex" value="男" name="sex">男
					<input type="radio" value="女" class="sex" name="sex">女</p>
				手机:<input name="c_mobile" type="text" id="mobile" ><br><br>
				部门:
					<select name="dept" id="dept2" onchange="ch()">
						<option value="技术部">技术部</option>
						<option value="销售部">销售部</option>
						<option value="线上销售部">线下销售部</option>
						<option value="线下销售部">线下销售部</option>
						<option value="线下销售部">线下销售部</option>
					</select><br/></br>
				职位:
				<select name="position" id="position">
					
				</select><br/>
				<input onclick="subabc()" type="button" value="提交" class="btn bste btn-small">
			</form>

</div>


</body>

</html>