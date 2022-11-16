<jsp:useBean id="user" class="com.bean.login"/>
<%@ page import="com.user.User"%>

<jsp:setProperty name="user" property="*"/>


<%-- 弃用 --%>

<%-- 方法：重定向网页 --%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path +"/";
%>
<%-- 获取前台的登录信息 --%>
<%
    String username=user.getUsername();
    String password=user.getPassword();

    User user=new user(username,password);
    //调用login.java实现用户登录

%>





