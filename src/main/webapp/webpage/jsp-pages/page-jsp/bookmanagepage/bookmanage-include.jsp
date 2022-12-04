<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.book.*"%>

<div class="inputbook">

    <div class="input_item">
        <div class="item1">
            <span>序号</span>
            <span>书籍id</span>
            <span>书籍名</span>
            <span>借阅次数</span>
            <span>归还次数</span>
            <span>是否在库</span>
        </div>

        <%
            //数据库导入操作，列表导入
            book newbook=new book();
            List<book> booklist=(List<book>)session.getAttribute("booklist1");
            if(booklist!=null){
                for(int i=0;i<booklist.size();i++){

                    newbook=booklist.get(i);
                    int bookid=newbook.getBookid();
                    String bookname=newbook.getBookname();
                    int borrownum=newbook.getBorrow_num();
                    int receivenum=newbook.getReceive_num();
                    Boolean depot=newbook.getDepot();
                    String deport_str=null;

                    if(depot==true){
                        deport_str="在库";
                    }else{
                        deport_str="不在库";
                    }
                    
                    String str7="<span>"+(i+1)+"</span>";
                    String str2="<span>"+bookid+"</span>";
                    String str3="<span>"+bookname+"</span>";
                    String str4="<span>"+borrownum+"</span>";
                    String str5="<span>"+receivenum+"</span>";
                    String str6="<span>"+deport_str+"</span>";

                    String str="<div class=\"item\">"+str7+str2+str3+str4+str5+str6+"</div>";
                    out.print(str);

                }
            }

        
        %>

        <%-- text --%>
        <%-- <div class="item">
            <span>序号</span>
            <span>书籍id</span>
            <span>书籍名</span>
            <span>借阅次数</span>
            <span>归还次数</span>
            <span>是否在库</span>
        </div>
        <div class="item">
            <span>序号</span>
            <span>书籍id</span>
            <span>书籍名</span>
            <span>借阅次数</span>
            <span>归还次数</span>
            <span>是否在库</span>
        </div>
        <div class="item">
            <span>序号</span>
            <span>书籍id</span>
            <span>书籍名</span>
            <span>借阅次数</span>
            <span>归还次数</span>
            <span>是否在库</span>
        </div> --%>
        
    </div>

</div>