<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"  %>

<%-- 管理员页面：booklog页 --%>

<%
    if((boolean)session.getAttribute("superuser") !=true){
        response.setHeader("refresh","1;url=../homepage/homepage.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>BookLlogPage</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/booklogmanagepage.css">
</head>
<body>
    <%-- 头部导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <div class="main">
        <div class="middle">
            <span class="title1"><h3>日志管理</h3></span>
            <div class="log_manage">
                <div class="input_logmanage">
                    <form action="booklogmanageservlet" method="post" id="change_loginput">
                        <div class="logsearch">
                            <span><input type="radio" name="change" id="search" value="1" checked="checked"><h4>日志查询</h4></span>
                            <input class="text" type="text" name="search-text" id="search-text" placeholder="请输入日志相关信息"/>
                            <span class="inputidchange-span">选择输入类型：
                                <input type="radio" name="inputidchange" id="inputidchange1" checked="checked" value="1">:日志id
                                <input type="radio" name="inputidchange" id="inputidchange2" value="2">:用户id
                                <input type="radio" name="inputidchange" id="inputidchange3" value="3">:书籍id
                            </span>
                        </div>
                        <div class="border"></div>

                        <div class="log_exchange">
                            <span><input type="radio" name="change" id="exchange" value="2"><h4>日志修改</h4></span>
                            <div class="log_exchange_div">
                                <span>日志id:<input class="inputtext" type="text" name="logid" id="logid" disabled></span>
                                <span>书籍id:<input class="inputtext" type="text" name="bookid" id="bookid" disabled></span>
                                <span>用户id:<input class="inputtext" type="text" name="userid" id="userid" disabled></span>
                                <span>借阅日期:<input class="inputtext" type="text" name="borrowDate" id="borrowDate" placeholder="yyyy-mm-dd" disabled></span>
                                <span>归还日期:<input class="inputtext" type="text" name="receiveDate" id="receiveDate" placeholder="yyy-mm-dd" disabled></span>
                                <span>库存设置:
                                    <div>
                                        不在库:<input type="radio" class="logundepot" name="logundepot" id="undepot1" value="1" disabled>
                                        在库:<input type="radio" class="logundepot" name="logundepot" id="undepot2" value="0" disabled>
                                    </div>
                                </span>
                                <span>条目设置:
                                    <div>
                                        有效：<input type="radio" class="lognullitem" name="lognullitem" id="nullitem1" value="1" disabled>
                                        无效：<input type="radio" class="lognullitem" name="lognullitem" id="nullitem2" value="0" disabled>
                                    </div>
                                </span>
                                <span>条目选项:
                                    <div>
                                        修改：<input type="radio" class="logchange" name="logchange" id="exchangelog1" checked="checked" value="1" disabled>
                                        增加：<input type="radio" class="logchange" name="logchange" id="exchangelog2" value="2" disabled>
                                    </div>
                                </span>
                            </div>
                        </div>
                        <div class="border"></div>
                        <div class="buttom"><input type="submit" onclick="" value="提交"></div>
                        <div class="border"></div>
                    </form>
                </div>
            </div>
            <span class="title2"><h3>日志列表</h3></span>
            <jsp:include page="booklogmanagepage-include.jsp"/>
        </div>
    </div>
    <%-- 底部信息栏 --%>
    <%@ include file="../../mod-jsp/foot.jsp" %>
    
</body>

<script src="../../../../js/booklogmanage.js"></script>

</html>


