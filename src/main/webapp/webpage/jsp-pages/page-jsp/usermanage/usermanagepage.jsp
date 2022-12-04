<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"  %>

<%-- 管理员页面：用户设置管理 --%>

<%
    if((boolean)session.getAttribute("superuser") !=true){
        response.setHeader("refresh","1;url=../homepage/homepage.jsp");
    }
%>

<html lang="en">

<head>
    <title>usermanage</title>
    <link rel="stylesheet" type="text/css" href="../../../../css/usermanagepage.css" />
</head>

<body>

    <%-- 头部导航栏 --%>
    <%@ include file="../../mod-jsp/head-req.jsp" %>

    <div class="main">
        <%-- dao操作：1，获取全体用户列表；2，修改用户 --%>
        <div class="middle">
            <span class="title1"><h3>用户管理</h3></span>        
            <div class="user-manage">
                <div class="input-usermanage">
                    <form action="usermanageservlet" method="post" id="change_userinput">
                        <div class="user-search">
                            <span><input type="radio" name="change" id="search" value="1" checked="checked" ><h4>用户查询</h4></span>
                            <input class="text" type="text" name="search-text" id="search-text" placeholder="请输入用户相关信息" />
                        </div>
                        <div class="border"></div>
                        <div class="user-exchange">
                            <span><input type="radio" name="change" id="exchange" value="2"><h4>用户修改</h4></span>
                            <div class="user-exchange-div">
                                <span>用户id:<input class="inputtext" type="text" name="userid" id="userid" disabled></span>
                                <span>用户名:<input class="inputtext" type="text" name="username" id="username" disabled></span>
                                <span>用户密码:<input class="inputtext" type="text" name="userpassword" id="userpassword" disabled></span>
                                <span>用户邮箱:<input class="inputtext" type="email" name="useremail" id="useremail" disabled></span>
                                <span>用户电话:<input class="inputtext" type="tel" name="usertel" id="usertel" disabled></span>
                                <span>用户性别:
                                    <div>
                                        man<input class="sex" name="sex" value="man" type="radio" id="sex1" disabled>
                                        women<input class="sex" name="sex" value="women" type="radio" id="sex2" disabled>
                                    </div>
                                </span>
                                <span>超级管理员:
                                    <div>
                                        是<input class="superuser" name="superuser" value="yes" type="radio" id="superuser1" disabled >
                                        否<input class="superuser" name="superuser" value="no" checked="checked" type="radio" id="superuser2" disabled >
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
            
            <%-- 输出查询数据 --%>
            <span class="title2"><h3>用户列表</h3></span>
            <jsp:include page="input-usermanage.jsp"/>
        </div>
    </div>
    <%-- 底部信息栏 --%>
    <%@ include file="../../mod-jsp/foot.jsp" %>

</body>
<script src="../../../../js/usermanage.js"></script>
</html>
