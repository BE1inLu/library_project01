<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java"%>
<%@ page import="java.util.*,com.book.*" %>


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
            List<book> booklist=session.getAttribute("booklist");
            book newbook=null;

            if(booklist.next()){
                for(int i=0;i<booklist.size();i++){

                    newbook=booklist.get(i);
                    int bookid=newbook.getBookid();
                    String bookname=newbook.getBookname();
                    Boolean depot=newbook.getDepot();

                    String str="<div class=\"item\"><span>"+bookid.ToString()+"</span><span>"+bookname+"</span><span>"+depot.ToString()+"</span><span> Button1 Button2 </span></div>";

                    out.print(str);
                }
            }
        %>

        <div class="item">预留位1</div>
        <div class="item">预留位2</div>
        <div class="item">预留位3</div>
        <div class="item">预留位4</div>
        </div>
    </div>
