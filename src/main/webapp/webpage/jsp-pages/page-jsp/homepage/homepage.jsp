<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.*"%>

<html lang="en">

<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/homepage.css" />
</head>

<body>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <div class="main">
        <div class="left"></div>
        <div class="middle">
            <%-- 外部include --%>
            <span class="title1">在借书籍</span>
            <div class="book1">窗口1</div>

            <%-- 外部include --%>
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