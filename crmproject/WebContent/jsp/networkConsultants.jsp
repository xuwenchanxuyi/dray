<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-table.min.css">
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css" >
<!-- 最新的 Bootstrap 核心 JavaScript 文件  要将jquery的包放在bootstrap之前-->
<script src="<%=request.getContextPath() %>/js/echarts.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table-zh-CN.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络咨询师界面</title>
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

<!-- 本月统计 -->
<div class="modal fade" id="StatisticsModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
      <div id="backData" style="width: 600px;height:400px;"></div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!--本月统计  -->


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
                                <label for="modifyNameText">客户名称：</label>
                                <input id="modifyProjectId" type="hidden">
                                <input id="c_name" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">客户email：</label>
                                <input id="c_email" class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">客户手机：</label>
                            <input id="c_mobile" class="form-control input-sm">
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">客户状态：</label>
                            <select id="c_state" class="form-control input-sm">
                            	<option value="上门">上门</option>
                            	<option value="紧跟">紧跟</option>
                            	<option value="未通">未通</option>
                            	<option value="记录无效">记录无效</option>
                            	<option value="死单">死单</option>
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
  <button type="button" onclick="statistics()" class="btn btn-default">本月统计</button>
  		
</div>
<!-- 修改的模态框 结束 -->
<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getNetwordCustomer", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
toolbar:"#toolbar",
showRefresh:true,// 显示刷新按钮
showColumns:true, // 显示所有的列
//data-locale:"zh-CN", //表格汉化
search: true, //显示搜索框
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
                  field: 'c_name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '客户email',
                  field: 'c_email',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '客户手机号',
                  field: 'c_mobile',
                  align: 'center'
              },
              {
                  title: '客户状态',
                  field: 'c_state',
                  align: 'center'
              },
              {
                  title: '客户生日',
                  field: 'c_birthday',
                  align: 'center',
              },
              {
                  title: '客户性别',
                  field: 'c_sex',
                  align: 'center',
              },
              
              {
                  title: '操作',
                  field: 'customer.',
                  align: 'center',
                  formatter:function(value,row,index){
               var e = '<a href="javascript:void(0)" onclick="edit(\''+ row.ccid +'\')">编辑</a> ';
              /*  var d = '<a href="javascript:void(0)" onclick="del(\''+ row.ccid +'\')">删除</a> ';
               var download = '<a href="javascript:void(0)" onclick="downloadFile(\''+ row.ccid +'\')">下载附件</a> '; */
               return e;
                } 
              }
              
          ]  
  });
//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: '<%=request.getContextPath()%>/getNetwordCustomer'});  
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
			 $('#prjTable').bootstrapTable('refresh', {url: '<%=request.getContextPath()%>/getNetwordCustomer'});
			 }
	 });
}
function edit(ccid){
	alert(ccid);
	 $.get("<%=request.getContextPath()%>/NetworkConsultantsGetCustomer",{ccid:ccid},function(data){
		 $("#ccid").val(data.ccid);
		 $("#c_name").val(data.c_name);
		 $("#c_email").val(data.c_email);
		 $("#c_mobile").val(data.c_mobile);
		 
	 },"json");
	 $("#modifyModal").modal("show");
	
}

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
			 $('#prjTable').bootstrapTable('refresh', {url: 'queryProjectAction'});
		}
	})
	 */
	 var id=$("#ccid").val();
	 var state=$("#c_state").val();
	 $.ajax({
		url:"<%=request.getContextPath()%>/NetworkConsultantsModify",
		data:{"ccid":id,"c_state":state},
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("修改成功")
			}else{
				alert("修改失败");
			}
		}
		 
	 });
	 
	 
});

function statistics(){
	var pieChart = echarts.init(document.getElementById('backData'));
    // 使用ajax请求后台数据
    $.get("<%=request.getContextPath()%>/networdConsultantsStatistics",{},function(pieData){
		 // 给图表赋值
    	// 指定图表的配置项和数据
        var option = {
        	    title : {
        	        text: '本月统计',
        	        subtext: '',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        //data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        	      data:pieData.legendData
        	    },
        	    series : [
        	        {
        	            name: '访问来源',
        	            type: 'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            /*
        	            data:[
        	                {value:335, name:'直接访问'},
        	                {value:310, name:'邮件营销'},
        	                {value:234, name:'联盟广告'},
        	                {value:135, name:'视频广告'},
        	                {value:1548, name:'搜索引擎'}
        	            ],*/
        	            data:pieData.seriesData,
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};

        // 使用刚指定的配置项和数据显示图表。
        pieChart.setOption(option);
	 },"json");
    $("#StatisticsModal").modal("show");
}



</script>
</body>
</html>