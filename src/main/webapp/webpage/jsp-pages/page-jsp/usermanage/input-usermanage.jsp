<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.user.*"%>


<div class="inputuser">

    <div class="inputuser_item">
        <div class="item1">
            <span>用户id</span>
            <span>用户名</span>
            <span>用户密码</span>
            <span>邮箱</span>
            <span>电话</span>
            <span>性别</span>
            <span>超级管理员</span>
        </div>

        <%
            user localuser=new user();
            List<user> userlist=(List<user>)session.getAttribute("userlist");
            
            if(userlist!=null){
                int listsize=userlist.size();
                for(int i=0;i<listsize;i++){
                    localuser=userlist.get(i);
                    int userid=localuser.getUserid();
                    String username=localuser.getUsername();
                    String password=localuser.getPassword();
                    String useremail=localuser.getEmail();
                    String usertel=localuser.getTel();
                    String usersex=localuser.getSex();
                    Boolean superuser=localuser.getSuperuser();
                    String superuserstr="";
                    if(superuser==false){
                        superuserstr="true";
                    }else{
                        superuserstr="false";
                    }

                    
                    String str2="<span>"+userid+"</span>";
                    String str3="<span>"+username+"</span>";
                    String str4="<span>"+password+"</span>";
                    String str5="<span>"+useremail+"</span>";
                    String str6="<span>"+usertel+"</span>";
                    String str7="<span>"+usersex+"</span>";
                    String str8="<span>"+superuserstr+"</span>";

                    String str="<div class=\"item\">"+str2+str3+str4+str5+str6+str7+str8+"</div>";
                    out.print(str);
                }
            }
        %>

        <%-- text --%>
        <div class="item">
            <span>用户1</span>
            <span>用户名</span>
            <span>用户密码</span>
            <span>邮箱</span>
            <span>电话</span>
            <span>性别</span>
            <span>false</span>
        </div>

        <div class="item">
            <span>用户2</span>
            <span>用户名2</span>
            <span>用户密码2</span>
            <span>邮箱2</span>
            <span>电话2</span>
            <span>性别</span>
            <span>false</span>
        </div>

        <div class="item">
            <span>用户1</span>
            <span>用户名</span>
            <span>用户密码</span>
            <span>邮箱</span>
            <span>电话</span>
            <span>性别</span>
            <span>false</span>
        </div>
    </div>
</div>