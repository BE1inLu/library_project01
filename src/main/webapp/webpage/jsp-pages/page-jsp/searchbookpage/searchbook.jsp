<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.book.*"%>

<html lang="en">

<head>
    <title>LibSystem</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/searchbook.css" />
    <%-- TODO:修改searchbookcss样式 --%>
</head>

<body>
    <%-- 导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <%-- 主体窗口 --%>
    <div class="main">
        <%-- <div class="left"></div> --%>
        <div class="middle">
            <%-- <%@ include file="book.jsp" %> --%>
            <span class="title1"><h3>书籍查询</h3></span>
            <div class="book-title">
                <div class="input-user">
                    <form action="bookservlet" method="post">
                        <input class="text" type="text" name="bookname" placeholder="请输入书籍相关信息" />
                        <input class="button" type="submit" value="搜索" />
                    </form>
                </div>
            </div>
            <%-- <%@ include file="input-book.jsp"%> --%>
            <jsp:include page="input-book.jsp" flush="true"/>
        </div>
    </div>
    


</body>

</html>