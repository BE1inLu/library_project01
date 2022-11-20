<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*,com.book.*" %>

<html lang="en">

<head>
  <title>LibSystem</title>
  <link rel="stylesheet" type="text/css" href="../../../../css/searchbook.css" />

</head>

<body>
    <%-- 导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <%-- 主体窗口 --%>
    <div class="main">
        <div class="left"></div>
        <div class="middle">

            <%@ include file="book.jsp" %>

            <%@ include file="input-book.jsp"%>

        </div>
        <div class="right"></div>
    </div>
    
    <%-- 底栏 --%>
    <%@ include file="../../mod-jsp/foot.jsp" %>

</body>

</html>