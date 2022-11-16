<%-- index索引页显示 --%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path +"/";
 %>
<link rel="stylesheet" type="text/css" href="./css/index.css"/>

<%@ include file="./webpage/jsp-pages/head.jsp" %>

<div class="main">
        <div class="frame">
            <input type="button" value="login" id="login" onclick="text01()"/>
            <input type="button" value="regist" id="regist" onclick="text02()"/>
        </div>

        <script type="text/javascript">
            function text01(){
                window.location="<%=basePath%>webpage/static-pages/login.html";
            }
            function text02(){
                window.location="<%=basePath%>webpage/static-pages/regist.html";
            }
        </script>
</div>

<%@ include file="./webpage/jsp-pages/foot.jsp" %>

