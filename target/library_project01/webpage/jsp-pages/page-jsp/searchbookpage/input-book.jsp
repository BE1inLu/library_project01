<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java"%>
<%@ page import="java.util.*,com.book.*"%>

<div class="inputbook">
    <span class="title1">在借书籍</span>
    <div class="inputbook-item">
        <div class="item1">
            <span>序号</span>
            <span>书籍id</span>
            <span>书籍名</span>
            <span>书籍是否在库</span>
            <span>借阅/归还</span>
        </div>

        <%
            //数据库导入操作，列表导入
            book newbook=new book();
            List<book> booklist=(List<book>)session.getAttribute("booklist");

            for(int i=0;i<=booklist.size();i++){
                newbook=booklist.get(i);
                int bookid=newbook.getBookid();
                String bookname=newbook.getBookname();
                Boolean depot=newbook.getDepot();
                String deport_str=null;
                if(depot==true){
                    deport_str="在库";
                }else{
                    deport_str="不在库";
                }
                String str="<div class=\"item\"><span>"+(i+1)+"</span><span>"+bookid+"</span><span>"+bookname+"</span><span>"+deport_str+"</span><span> Button1 Button2 </span></div>";
                out.print(str);
            }
            
        %>
    </div>
</div>

<%-- 底栏 --%>
<%@ include file="../../mod-jsp/foot.jsp" %>