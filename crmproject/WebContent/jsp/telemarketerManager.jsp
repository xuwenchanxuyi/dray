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
<script src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/echarts.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table-zh-CN.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电销主管页面</title>
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

<!-- 文件上传模态框 开始 -->

<div class="modal fade" id="loadFileModal">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-inline">
                     <div class="form-group">
                     <!-- <form enctype="multipart/form-data" id="uploadForm">
                     	请选择上传文件:
                     	<input name="myFile" type="file" id="myFile" class="form-control input-sm"><br/><br/><br/><br/>
                     	<button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-primary" onclick="loadfile2()" id="load">上传</button>
                     </form>	 -->
                     	<form action="<%=request.getContextPath() %>/uploadFile" method="post" enctype="multipart/form-data">
                          <label for="modifyNameText">请选择上传文件</label><br/><br/>
                          <input id="modifyProjectId" type="hidden">
                          <input name="myFile" type="file" class="form-control input-sm"><br/><br/><br/><br/>
                          <button class="btn btn-default" data-dismiss="modal">关闭</button>
                          <input type="submit" class="btn btn-primary" value="上传"></button>
                        </form> 
                   	</div>
                </div>
             </div>
          			
         </div>
       </div>
</div>


<!-- 文件删除模态框 结束 -->

<div class="modal fade" id="StatisticsModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
      <div id="backData" style="width: 600px;height:400px;"></div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->






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
                                <input id="product_id"  type="hidden" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyNameText">商品名：</label>
                                <input id="modifyProjectId" type="hidden">
                                <input id="product_name" class="form-control input-sm">
                            </div>
                            <div class="form-group">
                                <label for="modifyAgeText">商品描述：</label>
                                <input id="product_description" class="form-control input-sm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="modifyAddressText">商品价格：</label>
                            <input id="product_price" class="form-control input-sm">
                        </div>
                         <div class="form-group">
                            <label for="modifyAddressText">商品库存：</label>
                            <input id="product_stock" class="form-control input-sm">
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
  		<button type="button" onclick="loadfile()" class="btn btn-default">上传数据</button>
  		<button type="button" class="btn btn-default">导入数据</button>
  		<button type="button" class="btn btn-default">产生报表</button>
  		<button type="button" class="btn btn-default">添加客户</button>
</div>
<!-- 修改的模态框 结束 -->
<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/TelemarketerManager", 
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
                title: '员工名称',
                  field: 'u_name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '员工手机号',
                  field: 'mobile',
                  align: 'center',
                  valign: 'middle',
              }, 
              {
                  title: '员工职位',
                  field: 'positon',
                  align: 'center'
              },
              {
                  title: '员工状态',
                  field: 'u_wordstate',
                  align: 'center'
              },
              {
                  title: '性别',
                  field: 'sex',
                  align: 'center',
              },
             
              
              {
                  title: '操作',
                  field: 'hpFileName',
                  align: 'center',
                  formatter:function(value,row,index){
               var e = '<a href="javascript:void(0)" onclick="tongji(\''+ row.uidd +'\')">本月统计</a> ';
              /*  var d = '<a href="javascript:void(0)" onclick="del(\''+ row.hpId +'\')">删除</a> ';
               var download = '<a href="javascript:void(0)" onclick="downloadFile(\''+ row.hpId +'\')">下载附件</a> '; */
               return e;
                } 
              }
              
          ]  
  });
//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: 'TelemarketerManager'});  
}

function loadfile(){
	$("#loadFileModal").modal("show");
} 

function loadfile2(){
	var myfile=$("#myFile").val();
	alert(myfile);
	$.ajax({
		url:"<%=request.getContextPath()%>/uploadFile",
		cache:false,
		data:new FormData($("#uploadForm")[0]),
		processData:false,//设置为false,因为FormData对象，不需要对数据进行做处理
		contentType:false,
		dataType:"text",
		success:function(data){
			if(data=="1"){
				alert("上传成功");
			}else{
				alert("上传失败");
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
			 $('#prjTable').bootstrapTable('refresh', {url: 'TelemarketerManager'});
			 }
	 });
}
function edit(hpId){
	alert(hpId);
	 $.get("<%=request.getContextPath()%>/modifyProduct",{productId:hpId},function(data){
		 alert(data);
		 $("#product_id").val(data.hpId);
		 $("#product_name").val(data.hpName);
		 $("#product_description").val(data.hpDescription);
		 $("#product_price").val(data.hpPrice);
		 $("#product_stock").val(data.hpStock);
	 },"json");
	 $("#modifyModal").modal("show");
	
}
function tongji(id){
	var pieChart = echarts.init(document.getElementById('backData'));
    // 使用ajax请求后台数据
    $.get("<%=request.getContextPath()%>/tongji",{"id":id},function(pieData){
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


$("#saveModify").click(function(){
	var productInfo={
			
			hpId:$("#product_id").val(),
			hpName:$("#product_name").val(),
			hpDescription:$("#product_description").val(),
			hpPrice:$("#product_price").val(),
			hpStock:$("#product_stock").val()
	};
	$.get("<%=request.getContextPath()%>/saveModifyProductInfo",{saveModify:JSON.stringify(productInfo)},function(data){
		if(data=="1"){
			alert("修改成功");
			//隐藏弹框
			$("#modifyModal").modal("hide");
			// 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'TelemarketerManager'});
		}
	})
	
});
</script>
</body>
</html>