<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"  %>

<%-- 管理员页面：用户设置管理 --%>

<%
    if((boolean)session.getAttribute("superuser") !=true){
        response.setHeader("refresh","1;url=../homepage/homepage.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>BookManage</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/bookmanagepage.css">
</head>
<body>
    <%-- 头部导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <div class="main">
        <div class="middle">
            <span class="title1"><h3>书籍管理</h3></span>
            <div class="book_manage">
                <div class="input_bookmanage">
                    <form action="#" method="post" id="change_bookinput">
                        <div class="booksearch">
                            <span><input type="radio" name="change" id="search" value="1" checked="checked" ><h4>查询书籍</h4></span>
                            <input class="text" type="text" name="search-text" id="search-text" placeholder="请输入书籍相关信息"/>
                        </div>
                        <div class="border"></div>
                        
                        <div class="book-exchange">
                            <span><input type="radio" name="change" id="exchange" value="2"><h4>书籍修改</h4></span>
                            <div class="book_exchange_div">
                                <span>书籍id:<input class="inputtext" type="text" name="bookid" id="bookid" disabled></span>
                                <span>书籍名:<input class="inputtext" type="text" name="bookname" id="bookname" disabled></span>
                                <span>借阅次数:<input class="inputtext" type="text" name="bookborrow_num" id="bookborrow_num" disabled></span>
                                <span>归还次数:<input class="inputtext" type="text" name="bookreceive_num" id="bookreceive_num" disabled></span>
                                <span>是否在库:
                                    <div>
                                        在库:<input type="radio" class="bookdepot" name="bookdepot" id="depot1" value="1" disabled>
                                        不在库:<input type="radio" class="bookdepot" name="bookdepot" id="depot2" value="0" disabled>
                                    </div>
                                </span>
                                <span>条目选项:
                                    <div>
                                        修改：<input type="radio" class="bookchange" name="bookchange" id="exchangebook1" checked="checked" value="1" disabled>
                                        增加：<input type="radio" class="bookchange" name="bookchange" id="exchangebook2" value="2" disabled>
                                    </div>
                                </span>
                            </div>
                        </div>
                        <div class="border"></div>
                        <div class="buttom"><input type="submit" value="提交"></div>
                        <div class="border"></div>
                    </form>
                </div>
            </div>

            <%-- 输出数据查询 --%>
            <span class="title2"><h3>书籍列表</h3></span>
            <%-- jsp：include引用 --%>
            <jsp:include page="bookmanage-include.jsp"/>
        </div>
    </div>
    <%-- 底部信息栏 --%>
    <%@ include file="../../mod-jsp/foot.jsp" %>

    
</body>

<%-- script引用 --%>
<script src="../../../../js/bookmanage.js"></script>
</html>