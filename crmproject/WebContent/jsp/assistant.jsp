<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>销售助理界面</title>
</head>
<body>
 <div id="toolbar">
            <div class="form-inline" role="form">
                <div class="form-group">
                  <span>商品名称：</span>
                    <input name="projectName" id="projectNameParam" class="form-control" type="text" placeholder="项目名称">
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
                               
                                <input id="modifyProjectId" type="hidden">
                                <input id="ccid"  type="hidden" class="form-control input-sm">
                            </div>
                           	<div class="form-group">
                                <label for="modifyNameText">客户名:</label>
                                
                                <input id="c_name" class="form-control input-sm">
                            </div>
                          	<div class="form-group">
                                <label for="modifyNameText">客户email:</label>
                                <input id="modifyProjectId" type="hidden">
                                <input id="c_email" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">客户手机号:</label>
                                <input id="c_mobile" class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">客户状态:</label>
                            <select id="c_state" class="form-control input-sm">
                            		<option value="新增未上门">新增未上门</option>
									<option value="上门">上门</option>
									<option value="未通">未通</option>
									<option value="死单">死单</option>
									<option value="紧跟">紧跟</option>
                            </select>
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">添加人:</label>
                            <input id="c_username" class="form-control input-sm">
                        </div>
                        
                         <div class="form-group">
                            <label for="modifyAddressText">年龄:</label>
                            <input id="c_age" class="form-control input-sm">
                        </div>
                        
                         <div class="form-group">
                            <label for="modifyAddressText">生日:</label>
                            <input id="c_birthday" class="form-control input-sm">
                        </div>
                  	</div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary" id="saveModify">保存</button>
                    </div>
             	</div>
            </div>
        </div>
        
        <!-- 分配模态框 开始 -->
        
      <div class="modal fade" id="allotModel">
      <div class="modal-dialog">
        <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">选择分配员工</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                        	<div class="form-group">
                        	<select class="form-control input-sm" id="sel">
                        	</select>
                  			</div>
                    	</div>
                  	</div>
                   
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary"  id="saveallot">保存</button>
                    </div>
             	</div>
            </div>
        </div>
      <!-- 分配模态框结束 -->
      
      
      <!-- 咨询师 分配模态框开始 -->
      
      <div class="modal fade" id="counselorModel">
      <div class="modal-dialog">
        <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">选择分配员工</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-inline">
                        	<div class="form-group">
                        	<select class="form-control input-sm" id="selCounselor">
                        	</select>
                  			</div>
                    	</div>
                  	</div>
                   
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary"  id="saveCounselor">保存</button>
                    </div>
             	</div>
            </div>
        </div>
      
      <!-- 咨询师 分配模态框结束 -->
      
      <div class="modal fade" id="intoModel">
      <div class="modal-dialog">
        <div class="modal-content">
                    <div class="modal-header">
                    	<!-- <label for="modifyAddressText">请选择数据源:</label>
                    	  <input name="myFile" type="file" id="myFile" class="form-control input-sm"><br/><br/><br/><br/>
                    	  <button class="btn btn-default" data-dismiss="modal">关闭</button>
                    	  <button class="btn btn-primary" id="load">导入</button> -->
                    	 <form action="<%=request.getContextPath() %>/intoData" method="post">
                          <label for="modifyNameText">请选择上传文件</label><br/><br/>
                          <input name="myFile" type="file" class="form-control input-sm"><br/><br/><br/><br/>
                          <button class="btn btn-default" data-dismiss="modal">关闭</button>
                          <input type="submit" class="btn btn-primary" value="导入"/>
                        </form> 
                    </div>
                 </div>
            </div>
        </div>
     
      
      
      
      
      
	<div class="btn-group" role="group" aria-label="...">
  		<button type="button" onclick="allot()" class="btn btn-default">分配</button>
  		<button type="button" onclick="into()" class="btn btn-default">导入数据</button>
  		<div class="dropup" style="display:inline;">
  			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    			下载报表
    			<span class="caret"></span>
  			</button>
  			<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    			<li><a href="<%=request.getContextPath() %>/download?report=report.xlsx">report.xlsx</a></li>
   	 			<!-- <li><a href="#">Another action</a></li>
    			<li><a href="#">Something else here</a></li>
    			<li role="separator" class="divider"></li>
    			<li><a href="#">Separated link</a></li> -->
  			</ul>
		</div>
  		<button type="button" onclick="table_file()"class="btn btn-default">产生报表</button>
  		<button type="button" onclick="counselor()" class="btn btn-default">分配咨询师</button>
	</div>
<!-- 修改的模态框 结束 -->
<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getAllCustomer", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
toolbar:"#toolbar",
showRefresh:true,// 显示刷新按钮
showColumns:true, // 显示所有的列
//data-locale:"zh-CN", //表格汉化
search: false, //显示搜索框
striped:true,
sidePagination: "server", //服务端处理分页
pageList:[5,10,15,20,50],
sortName : 'createDate', // 排序字段
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
            projectName:$("#projectNameParam").val() //获取搜索框中的值
        };
    },
      columns: [
    	  	  {
                	checkbox:true
              },
              {
                  title: '客户id',
                  field: 'ccid',
                  align: 'center'
              },
              {
                title: '客户姓名',
                  field: 'c_name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '商品email',
                  field: 'c_email',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '手机号',
                  field: 'c_mobile',
                  align: 'center'
              },
             {
                  title: '客户状态',
                  field: 'c_state',
                  align: 'center',
              },
              {
                  title: '添加人',
                  field: 'c_username',
                  align: 'center',
              },
              {
                  title: '年龄',
                  field: 'c_age',
                  align: 'center',
              },
              {
                  title: '生日',
                  field: 'c_birthday',
                  align: 'center',
              },
              {
                  title: '性别',
                  field: 'c_sex',
                  align: 'center',
              },
              {
                  title: '操作',
                  field: 'hpFileName',
                  align: 'center',
                   formatter:function(value,row,index){
               var e = '<a href="javascript:void(0)" onclick="edit(\''+ row.ccid +'\')">编辑</a> ';
              // var d = '<a href="javascript:void(0)" onclick="del(\''+ row.hpId +'\')">删除</a> ';
              // var download = '<a href="javascript:void(0)" onclick="downloadFile(\''+ row.hpId +'\')">下载附件</a> ';
               return e; 
                } 
              } 
              
          ]  
  });
//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: 'getAllCustomer'});  
}
/*
分配函数
 */
 
var selCustomer;

function allot(){
	var a=$("#prjTable").bootstrapTable('getSelections');
	if(a.length<=0){
		alert("请选择一行");
	}else{
		$("#sel").empty();
		$.ajax({
			url:"<%=request.getContextPath()%>/getUsers",
			dataType:"JSON",
			success:function(data){
				$.each(data,function(i,obj){
					$("#sel").append("<option value="+obj.u_name+">"+obj.u_name+"</option>");
				});
			}
		});
		var ids=new Array();
		var state=new Array();
		var count=0,num;
		var flag="true";
		$.map(a,function(row){
			var s=row.c_state;
			if(s=='上门'){
				alert("数据分配错误")
				flag='false';
			}else{
				ids[count]=row.ccid;
				count++;
			}
			
		});
		if(flag=="true"){
			$("#allotModel").modal("show");	
			selCustomer=ids;
		}
		
		
	}
}

var counselorarr;
function counselor(){
	
	var b=$("#prjTable").bootstrapTable('getSelections');
	if(b.length<=0){
		alert("请选择一行");
	}else{
		$("#selCounselor").empty();
		$.ajax({
			url:"<%=request.getContextPath()%>/getCounselor",
			dataType:"JSON",
			success:function(data){
				$.each(data,function(i,obj){
					$("#selCounselor").append("<option value="+obj.u_name+">"+obj.u_name+"</option>");
				});
				
			}
		});
	}
	
	var ids=new Array();
	var count=0;
	var s=new Array();
	var flag2="true";
	$.map(b,function(row){
		s=row.c_state;
		if(s!='上门'){
			alert("分配数据错误");
			flag2="false";
		}else{
			ids[count]=row.ccid;
			count++;
		}
		
	});
	if(flag2=="true"){
		$("#counselorModel").modal("show");
		counselorarr=ids;
	}
	
	
}

function load(){
	var myFile=$("#myFile").val();
	$.ajax({
		url:"<%=request.getContextPath()%>/intoData",
		data:{"myFile":myFile},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("导入数据成功");
			}else{
				alert("导入数据失败");
			}
		}
	});
}

// 下载附件
function downloadFile(projectId)
{
	alert(projectId);
	//alert(projectId);
	window.location.href="fileDownload?projectId=" + projectId;
}
var product="";
function del(productId){
	product=productId;
	$("#delConfirmModal").modal("show");
	
}
function delConfirm(){
	 // 后台删除
	
	 $.get("<%=request.getContextPath()%>/delProjectAction",{projectId:product},function(data){
		
		 // 标记是否删除成功
		 if (data == "1")
			 {
			 // 关闭弹窗
			 $("#delConfirmModal").modal("hide");
			 alert("删除成功！");
			 // 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getAllCustomer'});
			 }
	 });
}
function edit(ccid){
	alert(ccid);
	$.get("<%=request.getContextPath()%>/searchCustomer",{ccid:ccid},function(data){
		$("#ccid").val(data.ccid);
		$("#c_name").val(data.c_name);
		$("#c_email").val(data.c_email);
		$("#c_mobile").val(data.c_mobile);
		$("#c_state").val(data.c_state);
		$("#c_username").val(data.c_username);
		$("#c_age").val(data.c_age);
		$("#c_birthday").val(data.c_birthday);
		},"json");
	$("#modifyModal").modal("show");
}

$("#saveallot").click(function(){
	var s=$("#sel").val();
	alert(selCustomer);
	$.ajax({
		url:"<%=request.getContextPath()%>/allotUsers",
		traditional:true, 
		data:{"user":s,"selCustomer":selCustomer},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("分配成功");
			}else{
				alert("分配失败");
			}
		}
	})
});

$("#saveCounselor").click(function(){
	var Coun=$("#selCounselor").val();
	
	$.ajax({
		url:"<%=request.getContextPath()%>/allotCounselor",
		traditional:true, 
		data:{"Counselor":Coun,"counselorarr":counselorarr},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("分配成功");
			}else{
				alert("分配失败");
			}
		}
	})
});



function into(){
	 $("#intoModel").modal("show");
}

function subData(){
var path=$("#intofile").val();
	
	$.ajax({
		url:"<%=request.getContextPath()%>/intoData",
		data:{"path":path},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("导入数据成功")
			}else{
				alert("导入数据失败");
			}
		}
	})
}

/* $("#intoData").click(function(){
	
	var path=$("#intofile").val();
	
	$.ajax({
		url:"intoData",
		data:{"path":path},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("导入数据成功")
			}else{
				alert("导入数据失败");
			}
		}
	})
	
}); */

function table_file(){
	$.ajax({
		url:"<%=request.getContextPath()%>/reportFile",
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("报表已产生");
			}else{
				alert("产生报表失败");
			}
		}
	});
}







var pattern = /^1[34578]\d{9}$/;
$("#saveModify").click(function(){
	/* var productInfo={
			
			hpId:$("#product_id").val(),
			hpName:$("#product_name").val(),
			hpDescription:$("#product_description").val(),
			hpPrice:$("#product_price").val(),
			hpStock:$("#product_stock").val()
	};
	$.get("saveModifyProductInfo",{saveModify:JSON.stringify(productInfo)},function(data){
		if(data=="1"){
			alert("修改成功");
			//隐藏弹框
			$("#modifyModal").modal("hide");
			// 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getAllCustomer'});
		}
	}) */
	/* 
	var tel= $("#c_mobile").val();
	if(pattern.test(tel)){ */
	
	
	var ccid=$("#ccid").val();
	var c_name=$("#c_name").val();
	var c_email=$("#c_email").val();
	var c_mobile=$("#c_mobile").val();
	var c_state=$("#c_state").val();
	var c_username=$("#c_username").val();
	var c_age=$("#c_age").val();
	var c_birthday=$("#c_birthday").val();
	if(pattern.test(c_mobile)){
		$.ajax({
			url:"<%=request.getContextPath()%>/assistantSavaModify",
			data:{"ccid":ccid,"c_name":c_name,"c_email":c_email,"c_mobile":c_mobile,"c_state":c_state,"c_username":c_username,"c_age":c_age,"c_birthday":c_birthday,},
			dataType:"text",
			success:function(data){
				if(data=="1"){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			}
		});
	}else{
		alert("数据错误");
	}
});
</script>
</body>
</html>