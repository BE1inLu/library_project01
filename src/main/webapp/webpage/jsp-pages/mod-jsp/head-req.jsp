<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%-- <link rel="stylesheet" type="text/css" href="./css/head.css"> --%>
<% 
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path +"/";
 %>
<div class="head">
    <!-- 头部导航栏+标题栏 -->
    <div class="banner">
        <div class="bannerboard">图书馆管理系统</div>
        <div class="nav">
            <ul>
                <li><a href="<%=basePath%>webpage/jsp-pages/page-jsp/homepage/homepage.jsp">个人主页</a></li>
                <li><a href="<%=basePath%>webpage/jsp-pages/page-jsp/searchbookpage/searchbook.jsp">借阅查询</a></li>
                <li><a href="<%=basePath%>webpage/jsp-pages/page-jsp/borrowpage/borrowpage.jsp">借阅申请</a></li>
                <li><a href="#">还书申请</a></li>
                <li><a href="#">个人设置</a></li>
            </ul>
        </div>
    </div>
</div>
