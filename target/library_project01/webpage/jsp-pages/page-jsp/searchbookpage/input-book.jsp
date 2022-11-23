<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java"%>
<%@ page import="java.util.*,com.book.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="inputbook">
    <span class="title1">在借书籍</span>
    <div class="inputbook-item">
        <div class="item1">
            <span>书籍id</span>
            <span>书籍名</span>
            <span>书籍是否在库</span>
            <span>借阅/归还</span>
        </div>

        <%
            //数据库导入操作，列表导入
            book newbook=new book();
            List<book> booklist=session.getAttribute("booklist");

            //text code
        %>


        <%-- for(int i=0;i<=booklist.size();i++){
            int n1=i;
            newbook=booklist.get(n1);
            int bookid=newbook.getBookid();
            String bookname=newbook.getBookname();
            Boolean depot=newbook.getDepot();
            String str="<div class=\"item\"><span>"+bookid+"</span><span>"+bookname
            +"</span><span>"+depot+"</span><span> Button1 Button2 </span></div>";
            out.print(str);
        } --%>
    
        <div class="item">预留位1</div>
        <div class="item">预留位2</div>
        <div class="item">预留位3</div>
        <div class="item">预留位4</div>
    </div>
</div>
