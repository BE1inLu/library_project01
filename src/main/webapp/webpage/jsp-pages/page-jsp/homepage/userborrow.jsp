<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.booklog.*" %>
<%@ page import="com.sql.JDBCUtil"  %>
<%@ page import="com.book.*"  %>
<%@ page import="com.dao.*"  %>
<%@ page import="java.sql.*"  %>


<%-- 用户在借阅书籍窗口 --%>
<%-- 获取booklog里该userid的redepot不为0的项，如果过多则获取前4个的数值 --%>
<%
    JDBCUtil db = new JDBCUtil();
    dao dao=new dao();
    booklog userbooklog=new booklog();
    userbooklog.setUserid((int)session.getAttribute("userid"));

    List<booklog> userbookloglist=new ArrayList<booklog>();
    try{
        Connection conn = db.getConn();
        userbookloglist=(List<booklog>)dao.check_booklog_l(conn,userbooklog);
        conn.close();
    }catch (Exception e) {
        e.printStackTrace();
    }
%>


<div class="userborrow">
    <div class="item1"><span>书籍id</span><span>书籍名</span><span>占位</span></div>
    <%
    int j=0;
    for(int i=0;i<userbookloglist.size();i++){
        userbooklog = userbookloglist.get(i);
        boolean flag=(boolean)userbooklog.getNullitem();
        if(flag==false) continue;
        if(j>4) break;
        j=j+1;
        int bookid=(int)userbooklog.getBookid();
        book newbook=new book();
        try{
            Connection conn = db.getConn();
            newbook=(book)dao.getBookmessage(conn,bookid);
        }catch (Exception e) {e.printStackTrace();}
        String bookname =(String)newbook.getBookname();
        
        String str="<div class=\"item\"><span>"+bookid+"</span><span>"+bookname+"</span><span>button</span></div>";
        out.print(str);
    }
    %>
</div>