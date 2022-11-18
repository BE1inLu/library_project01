<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.*"%>

<%
    //TODO:book1窗口动态元素
%>


<html lang="en">

<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/homepage.css" />
</head>

<body>

    <%-- <%@ include file="../../mod-jsp/head.jsp" %> --%>
    <div class="head">
        <!-- 头部导航栏+标题栏 -->
        <div class="banner">
            <div class="bannerboard">图书馆管理系统</div>
            <div class="nav">
                <ul>
                    <li><a href="./homepage.jsp">个人主页</a></li>
                    <li><a href="#">借阅查询</a></li>
                    <li><a href="#">借阅申请</a></li>
                    <li><a href="#">还书申请</a></li>
                    <li><a href="#">个人设置</a></li>
                </ul>
            </div>
        </div>
    </div>


    <div class="main">
        <div class="left"></div>
        <div class="middle">
            <span class="title1">在借书籍</span>
            <div class="book1">窗口1</div>
            <span class="title1">阅读记录</span>
            <div class="booklog">窗口2</div>

        </div>
        <div class="right">

            <!-- user窗口 -->
            <span class="title1">用户信息</span>
            <div class="user">
                用户名：<%=session.getAttribute("username")%>
            </div>
        </div>
    </div>

    <%@ include file="../../mod-jsp/foot.jsp" %>
    
</body>

</html>