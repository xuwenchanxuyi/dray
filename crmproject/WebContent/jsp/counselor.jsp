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
<title>咨询师界面</title>
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
                                <label for="modifyNameText">客户名称:</label>
                                <input id="modifyProjectId" type="hidden">
                                <input id="c_name" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">客户email:</label>
                                <input id="c_email" class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">客户手机:</label>
                            <input id="c_mobile" class="form-control input-sm">
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">客户性别:</label>
                            <input id="c_sex" class="form-control input-sm">
                        </div>
                        
                         <div class="form-group">
                            <label for="modifyAddressText">客户性别:</label>
                            <select id="c_state" class="form-control input-sm">
                            		<option value="已报">已报</option>
                            		<option value="回家考虑">回家考虑</option>
									<option value="退费">退费</option>
									<option value="死单">死单</option>
									<option value="紧跟">紧跟</option>
                            </select>
                        </div>
                        <div id="link" class="form-group" style="display:none;">
                            <label for="modifyAddressText">联系时间:</label>
                            <input id="linkTime" type="date" class="form-control input-sm">
                        </div>
                        <div id="reason" class="form-group" style="display:none;">
                            <label for="modifyAddressText">退费理由:</label>
                            <input id="reason" class="form-control input-sm">
                        </div>
                 	</div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary" id="saveModify">保存</button>
                    </div>
              	</div>
            </div>
        </div>

<!-- 修改的模态框 结束 -->
<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getCounselorById", 
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
                title: '客户名',
                  field: 'customer.c_name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '客户邮箱',
                  field: 'customer.c_email',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '客户手机',
                  field: 'customer.c_mobile',
                  align: 'center'
              },
              {
                  title: '客户年龄',
                  field: 'customer.c_age',
                  align: 'center'
              },
              {
                  title: '客户性别',
                  field: 'customer.c_sex',
                  align: 'center',
              },
              {
                  title: '客户生日',
                  field: 'customer.c_birthday',
                  align: 'center',
              },
              {
                  title: '操作',
                  field: 'hpFileName',
                  align: 'center',
                  formatter:function(value,row,index){
               var e = '<a href="javascript:void(0)" onclick="edit(\''+ row.customer.ccid +'\')">编辑</a> ';
               /* var d = '<a href="javascript:void(0)" onclick="del(\''+ row.hpId +'\')">删除</a> ';
               var download = '<a href="javascript:void(0)" onclick="downloadFile(\''+ row.hpId +'\')">下载附件</a> '; */
               return e;
                } 
              }
              
          ]  
  });
//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: 'getCounselorById'});  
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
			 $('#prjTable').bootstrapTable('refresh', {url: 'getCounselorById'});
			 }
	 });
}
function edit(ccid){
	alert(ccid);
	 $.get("<%=request.getContextPath() %>/searchCustomer",{ccid:ccid},function(data){
		
		 $("#ccid").val(data.ccid);
		 $("#c_name").val(data.c_name);
		 $("#c_email").val(data.c_email);
		 $("#c_mobile").val(data.c_mobile);
		 $("#c_sex").val(data.c_sex);
	 },"json");
	 $("#modifyModal").modal("show");
	
}

$("#c_state").change(function(){
	$("#link").hide();
	$("#reason").hide();
	var sel=$("#c_state").val();
	if(sel=='回家考虑'){
		$("#link").show();
	}else if(sel=="退费"){
		$("#reason").show();
	}
})


$("#linkTime").change(function (){
	var time=$("#linkTime").val();
	alert(time);
	$.ajax({
		url:"<%=request.getContextPath()%>/timeSearch",
		data:{"time":time},
		dataType:"text",
		success:function(data){
			alert(data);
			if(data=="0"){
				alert("时间小于当前时间");
			}
		}
	})
});


$("#saveModify").click(function(){
	
	var ccid=$("#ccid").val();
	var c_state=$("#c_state").val();
	var linkTime=$("#linkTime").val();
	var reason=$("#reason").val();
	$.ajax({
		url:"<%=request.getContextPath()%>/CounselorModifyCustomer",
		data:{"ccid":ccid,"c_state":c_state,"linkTime":linkTime,"reason":reason},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("修改成功");
			}else{
				alert("修改失败");
			}
		}
	});
	
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
			 $('#prjTable').bootstrapTable('refresh', {url: 'getCounselorById'});
		}
	}) */
	
});
</script>
</body>
</html>