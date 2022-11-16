<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<html lang="en">

<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/homepage.css" />
</head>

<body>
    <%@ include file="./webpage/jsp-pages/mod-jsp/head.jsp" %>

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
                性别：<%=session.getAttribute("sex")%>
            </div>
        </div>
    </div>
    <%@ include file="./webpage/jsp-pages/mod-jsp/foot.jsp" %>
</body>

</html>