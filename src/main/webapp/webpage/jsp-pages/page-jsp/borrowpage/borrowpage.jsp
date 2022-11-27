<%-- 借阅申请页面 --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" import="java.util.*" %>
<%@ page import="com.user.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BorrowPage</title>
    <link rel="stylesheet" href="../../../../css/borrowpage.css">
</head>
<body>
    <%-- 导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <div class="main">
        <%-- 前台需要知道用户的id（但不显示到html），书籍id，和借阅时间 --%>
        <div class="middle">
            <div class="display">
                <form action="borrowservlet" method="post">

                    <h2>借阅申请<span>表单填写</span></h2>

                    <div class="item">
                        <span>借阅者id</span>
                        <input type="text" name="borrowuserid" id="userid" value="<%=session.getAttribute("userid")%>"/>
                    </div>
                    <%-- 用户姓名，不回传 --%>
                    <div class="item">
                        <span>读者姓名</span>
                        <input type="text" name="username" id="username" value="<%=session.getAttribute("username")%>"/>
                    </div>

                    <div class="item">
                        <span>图书id</span>
                        <input type="text" name="bookid" id="bookid">
                    </div>

                    <div class="item">
                        <%-- 借阅时长,选择式 --%>
                        <%-- todo:后面会有一个脚本前台展示归还日期 --%>
                        <span>借阅时长</span>
                        <select name="borrowday" id="borrowday">
                            <option value="7">7</option>
                            <option value="14">14</option>
                            <option value="21">21</option>
                        </select>
                    </div>

                    <div class="item">
                        <button type="submit">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%-- 底部信息栏 --%>
    <%@ include file="../../mod-jsp/foot.jsp" %>
</body>
</html>