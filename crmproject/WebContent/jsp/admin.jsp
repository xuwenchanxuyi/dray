<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-table.min.css">
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css" >
<!-- 最新的 Bootstrap 核心 JavaScript 文件  要将jquery的包放在bootstrap之前-->
<script src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table-zh-CN.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
</head>
<body>

<div id="toolbar">
            <div class="form-inline" role="form">
                <div class="form-group">
                  <span>员工名:</span>
                    <input name="userName" id="projectNameParam" class="form-control" type="text" placeholder="员工名">
                </div>
                <input type="button" class="btn btn-primary" onclick="_search()" value="查询" />
            </div>
        </div>
<table id="prjTable"></table>
<!-- 删除的模态框 开始 -->
<!-- 删除确认框 -->
<div class="modal fade" id="delConfirmModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">删除提示</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4">你确定要删除吗？</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="delConfirm()">确认</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 删除的模态框 结束 -->


<!-- 添加用户模态框 开始 -->

<div class="modal fade" id="addUserModel">
      <div class="modal-dialog">
        <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">修改信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                       
                            <div class="form-group">
                                <label for="modifyNameText">姓名:</label>
                                <input id="modifyProjectId" type="hidden">
                                <input id="U_named" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">手机:</label>
                                <input id="mobile" class="form-control input-sm"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">性别:</label>
                            <input type="radio" id="sex" class="sex" value="男" name="sex" class="form-control input-sm">男
							<input type="radio" id="sex" value="女" class="sex" name="sex" class="form-control input-sm">女
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">部门:</label>
                           	<select id="dept2" onchange="change()">
                           		<option value="技术部">技术部</option>
								<option value="销售部">销售部</option>
								<option value="线上销售部">线下销售部</option>
								<option value="线下销售部">线下销售部</option>
								<option value="线下销售部">线下销售部</option>
                           	</select>
                            <!-- <input id="c_state" class="form-control input-sm"> -->
                        </div>
                        
                        <div class="form-group">
                            <label for="modifyAddressText">职位:</label>
                           	<select id="position" >
                           		
                           		
                           	</select>
                            <!-- <input id="c_state" class="form-control input-sm"> -->
                        </div>
                      
                        
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary" id="saveAddUser" onclick="saveUser()">保存</button>
                    </div>
                    
                   
                    
                </div>
            </div>
        </div>

<!-- 添加用户模态框 结束 -->



<!-- 修改的模态框 开始 -->
  <div class="modal fade" id="modifyModal">
      <div class="modal-dialog">
        <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">修改信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                        <div class="form-group">
                               <!--  <label for="modifyNameText" type="hidden">用户ID:</label> -->
                               	
                                <input id="modifyProjectId" type="hidden">
                                <input id="uidd"  type="hidden" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyNameText">姓名:</label>
                               	<input id="u_name" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyNameText">手机号:</label>
                               	<input id="mobile" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">工作状态：</label>
                              	<select id="u_wordstate" class="form-control input-sm">
                              		<option value="在职">在职</option>
                              		<option value="离职">离职</option>
                              	</select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">职位：</label>
                            
                            <select id="positon" class="form-control input-sm">
                            	<option value="超级管理员">超级管理员</option>
                            	<option value="咨询师主管">咨询师主管</option>
                            	<option value="电销主管">电销主管</option>
                            	<option value="网络咨询主管">网络咨询主管</option>
                            	<option value="咨询师">咨询师</option>
                            	<option value="销售助理">销售助理</option>
                            	<option value="电销主管">电销主管</option>
                            	<option value="电销员工">电销员工</option>
                            	<option value="网咨员工">网咨员工</option>
                            </select>
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">部门：</label>
                           	<select id="department" class="form-control input-sm">
                           		<option value="技术部">技术部</option>
                           		<option value="销售部">销售部</option>
                           		<option value="线上销售部">线上销售部</option>
                           		<option value="线下销售部">线下销售部</option>
                           		<option value="销售支持部">销售支持部</option>
                          	</select>
                       </div>
                     </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary" id="saveModify">保存</button>
                    </div>
             	</div>
            </div>
        </div>
<div class="btn-group" role="group" aria-label="...">
  		<button id="addUser" type="button" class="btn btn-default">添加客户</button>
</div>
<!-- 修改的模态框 结束 -->
<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getUsersInfo", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
toolbar:"#toolbar",
showRefresh:false,// 显示刷新按钮
showColumns:true, // 显示所有的列
//data-locale:"zh-CN", //表格汉化
search: false, //显示搜索框
striped:true,
sidePagination: "server", //服务端处理分页
pageList:[5,10,15,20,50],
sortName : 'u_name', // 排序字段
sortOrder : 'desc', // 排序方式
sortable: true, //是否启用排序
queryParams: function (params) {
    return {
            rows: this.pageSize,
            page: this.pageNumber,
            offset: params.offset,  //页码
            limit: params.limit,   //页面大小
            search : params.search, //搜索
            order : params.order, //排序
            ordername : params.sort, //排序
            projectName:$("#u_name").val()
        };
    },
      columns: [
              {
                title: '用户名',
                  field: 'u_name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '用户密码',
                  field: 'u_password',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '职位',
                  field: 'positon',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '手机号',
                  field: 'mobile',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '性别',
                  field: 'sex',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '工作状态',
                  field: 'u_wordstate',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '部门名称',
                  field: 'department.dept_name',
                  align: 'center',
                  valign: 'middle',
              }, 
             
            {
                  title: '操作',
                  field: 'department.dept_name',
                  align: 'center',
               formatter:function(value,row,index){
               var e = '<a href="javascript:void(0)" onclick="edit(\''+ row.uidd +'\')">编辑</a> ';
               var d = '<a href="javascript:void(0)" onclick="del(\''+ row.uidd +'\')">删除</a> ';
               var download = '<a href="javascript:void(0)" onclick="passwordTest(\''+ row.uidd +'\')">重置密码</a> ';
               return e+d+download; 
                } 
              } 
              
          ]  
  });
//查询		
function _search()
{
	alert("_search")
	 $('#prjTable').bootstrapTable('refresh', {url: '<%=request.getContextPath()%>/getUsersInfo'});  
}

function change(){
	$("#position").empty();
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




// 重置密码
function passwordTest(uidd)
{
	 alert(uidd);
	 $.get("<%=request.getContextPath()%>/resetPassword",{uid:uidd},function(data){
		 if(data==1){
			 alert("重置成功")
		 }else{
			 alert("重置失败");
		 }
	 });
	
	
	/* $.ajax({
		
		url:"http://localhost:8568/crmproject/resetPassword",
		type:"POST",
		data:{"uid":uidd},
		dataType:"text",
		success:function(test){
			if(test==1){
				alert("重置密码成功");
			}else{
				alert("重置密码失败");
			}
		}
		
	}) */
	
	/*  //alert(projectId);
	window.location.href="resetPassword?uid=" + uidd;  */
}

function saveUser(){
	var U_named = $("#U_named").val();
	var mobile = $("#mobile").val();
	var sex = $("#sex").val();
	var dept2 = $("#dept2").val();
	var position = $("#position").val();
	alert(U_named+":"+mobile+":"+sex+":"+dept2+":"+position);
	$.ajax({
		url:"<%=request.getContextPath()%>/savaUser",
		data:{"name":U_named,"mobile":mobile,"sex":sex,"dept":dept2,"position":position},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
		}
	})
}




var uid="";
function del(uidd){
	uid=uidd;
	$("#delConfirmModal").modal("show");
	
}
function delConfirm(){
	 // 后台删除
	
	  $("#delConfirmModal").modal("hide");
	 $.get("<%=request.getContextPath()%>/delUser",{uid:uid},function(data){
		
		alert(data);
		 // 标记是否删除成功
		 if (data == "1")
			 {
			 // 关闭弹窗
			
			 alert("删除成功！");
			 // 刷新数据
			<%--  $('#prjTable').bootstrapTable('refresh', {url: '<%=request.getContextPath()%>/getUsersInfo'}); --%>
			 }
	 });
}
function edit(uidd){

	 $.get("<%=request.getContextPath()%>/modifyCustomer",{"uidd":uidd},function(data){
		
		 $("#uidd").val(data.uidd);
		 $("#u_name").val(data.u_name);
		 $("#mobile").val(data.mobile);
		 $("#u_wordstate").val(data.u_wordstate);
		 $("#positon").val(data.role.r_name);
		 $("#department").val(data.department.dept_name);
	 },"json");
	 $("#modifyModal").modal("show");
	
}

$("#addUser").click(function(){
	 $("#addUserModel").modal("show");
});

	


$("#saveModify").click(function(){
	/* alert($("#uidd").val());
	var productInfo={
			
			uidd:$("#uidd").val(),
			u_name:$("#u_name").val(),
			mobile:$("#mobile").val(),
			u_wordstate:$("#u_wordstate").val(),
			positon:$("#positon").val(),
			department:$("#department").val()
		};
	$.get("savaModify",{saveModify:JSON.stringify(productInfo)},function(data){
		if(data=="1"){
			alert("修改成功");
			//隐藏弹框
			$("#modifyModal").modal("hide");
			// 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getUsersInfo'});
		}
	}) */
	
	alert($("#uidd").val());
	 

	var uidd=$("#uidd").val();
	var u_name=$("#u_name").val();
	var mobile=$("#mobile").val();
	var u_wordstate=$("#u_wordstate").val();
	var positon=$("#positon").val();
	var department=$("#department").val();
	alert(uidd+""+u_name+""+mobile+""+u_wordstate+""+positon+""+department);
	$.ajax({
		url:"<%=request.getContextPath()%>/savaModify",
		data:{"uidd":uidd,"u_name":u_name,"mobile":mobile,"u_wordstate":u_wordstate,"positon":positon,"department":department},
		dataType:"text",
		success:function(data){
			if(data==1){
				alert("更新成功")
			}
			if(data==2)
			{
				alert("更新失败");	
			}
			if(data==3){
				alert("部门和职位不匹配")
			}
		}
		
	})
});
</script>
</body>
</html>