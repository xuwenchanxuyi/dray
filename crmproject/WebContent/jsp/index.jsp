<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
 <link rel="stylesheet" type="text/css" href="css/admin-all.css" />
 <link rel="stylesheet" type="text/css" href="css/base.css" />
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
 <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.22.custom.css" />
 <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="js/jquery-ui-1.8.22.custom.min.js"></script>
 <script type="text/javascript" src="js/index.js"></script>

</head>
<body>
<body>
    <div class="warp">
      <!--头部开始-->
        <div class="top_c">
            <div class="top-menu">
                <ul class="top-menu-nav">
                   
                    <li>
                        <ul class="kidc">
                            <li><a target="Conframe" href="Template/find-form.html">表单样式</a></li>
                            <li><a target="Conframe" href="Template/find-alert.html">弹出窗口</a></li>
                            <li><a target="Conframe" href="Template/find-order.html">查询排序</a></li>
                            <li><a target="Conframe" href="Template/find-1.html">查询结果一</a></li>
                            <li><a target="Conframe" href="Template/find-2.html">查询结果二</a></li>
                        </ul>
                    </li>
                   <%--  <li><a href="#">维护界面<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><b class="tip"></b><a target="Conframe" href="${pageContext.request.contextPath} /addCustomer.jsp">增加</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-edit.html">修改</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-del.html">删除</a></li>
                        </ul>
                    </li>
                    <li><a href="#">表单风格<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-Master-slave.html">主从表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-collapse.html">折叠表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                        </ul>
                    </li>
                    <li><a href="#">提示<i class="tip-up"></i></a>
                        <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                    </ul>
                    </li>
                    <li><a href="#">辅助信息<i class="tip-up"></i></a>
                        <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">寻访记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">数据说明</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                    </ul>
                    </li>--%>
                </ul> 
            </div> 
            <div class="top-nav">
            <c:set value="${user}" var="u"></c:set>
            <c:choose>
            	<c:when test="${u!=null }">
            	欢迎您，${u.u_name} &nbsp;&nbsp; | <a href="<%=request.getContextPath() %>/quit?id=${u.uidd}">安全退出</a></div>
            	</c:when>
            	<c:otherwise>
            	| <a href="<%=request.getContextPath() %>/quit?id=${u.uidd}">安全退出</a></div>
            	</c:otherwise>
            </c:choose>
            </div>
        <!--头部结束-->
        <!--左边菜单开始-->
        <div class="left_c left">
            <h1>系统操作菜单</h1>
            <div class="acc">
            <c:forEach items="${userRight}" var="list">
                <div>
                   <a class="one">${list.rightgroup} </a> 
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="${list.jr_url}">${list.rightname}</a></li>
                    </ul>
                </div>
            </c:forEach>
              <%--  <div>
                    <a class="one">维护界面</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="${pageContext.request.contextPath}/addCustomer.jsp">增加</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-edit.html">修改</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-del.html">删除</a></li>
                    </ul>
                </div>
                <div>
                    <a class="one">表单风格</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-Master-slave.html">主从表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-collapse.html">折叠表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                    </ul>
                </div>
                  <div>
                    <a class="one">提示</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                    </ul>
                </div>
                <div>
                    <a class="one">辅助信息</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">寻访记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">数据说明</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                    </ul>
                </div>
                <div id="datepicker"></div> --%>
            </div>

        </div>
        <!--左边菜单结束-->
        <!--右边框架开始-->
        <div class="right_c">
            <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>

        </div>
        <div class="Conframe">
            <iframe name="Conframe" id="Conframe"></iframe>
        </div>
        <!--右边框架结束-->

        <!--底部开始-->
        <div class="bottom_c">我的crm系统</div>
        <!--底部结束-->
    </div>

</body>
</html>