<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java"%>
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
            <%-- TODO：外部include用户在借书籍获取 --%>
            <span class="title1"><h3>在借书籍</h3></span>
            <div class="book1">
            <jsp:include page="userborrow.jsp"/>
            </div>

            <%-- TODO：外部include，用户阅读记录获取 --%>
            <span class="title1"><h3>阅读记录</h3></span>
            <div class="booklog">
            <jsp:include page="userlog.jsp"/>
            </div>

        </div>
        <div class="right">

            <!-- user窗口 -->
            <span class="title1"><h3>用户信息</h3></span>
            <div class="user">
                <span>用户id:<%=session.getAttribute("userid")%></span>
                <span>用户名：<%=session.getAttribute("username")%></span>
                <input class="button" type="submit" value="注销" onclick="func()" />
            </div>
        </div>
    </div>

    <%@ include file="../../mod-jsp/foot.jsp" %>
    
</body>

<script type="text/javascript">
    function func() {
        window.location.href="logout.jsp";
    }
</script>

</html>