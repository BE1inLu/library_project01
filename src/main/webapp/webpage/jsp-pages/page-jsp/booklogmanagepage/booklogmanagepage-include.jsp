<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.booklog.*"%>

<div class="inputlog">
    <div class="input_item">
        <div class="item1">
            <span>序号</span>
            <span>日志id</span>
            <span>书籍id</span>
            <span>用户id</span>
            <span>借阅日期</span>
            <span>归还日期</span>
            <span>库存有效</span>
            <span>条目有效</span>
        </div>

        <%
            booklog booklog=new booklog();
            List<booklog> bookloglist=(List<booklog>)session.getAttribute("bookloglist");
            try{
                if(bookloglist!=null){
                    for(int i=0;i<bookloglist.size();i++){
                        booklog=bookloglist.get(i);
                        int logid=booklog.getLogid();
                        int bookid=booklog.getBookid();
                        int userid=booklog.getUserid();
                        Date borrowdate=booklog.getBorrowDate();
                        Date receivedate=booklog.getReceiveDate();
                        Boolean redepot=booklog.getRedepot();
                        Boolean nullitem=booklog.getNullitem();

                        String restr=redepot?"在库":"不在库";
                        String nistr=nullitem?"有效":"无效";

                        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
                    
                        String str7="<span>"+(i+1)+"</span>";
                        String str9="<span>"+logid+"</span>";
                        String str2="<span>"+bookid+"</span>";
                        String str3="<span>"+userid+"</span>";
                        String str4="<span>"+ft.format(borrowdate)+"</span>";
                        String str5="<span>"+ft.format(receivedate)+"</span>";
                        String str6="<span>"+restr+"</span>";
                        String str8="<span>"+nistr+"</span>";

                        String str="<div class=\"item\">"+str7+str9+str2+str3+str4+str5+str6+str8+"</div>";
                        out.print(str);
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        
        %>


        <%-- text --%>
        <div class="item">
            <span>序号</span>
            <span>日志id</span>
            <span>书籍id</span>
            <span>用户id</span>
            <span>借阅日期</span>
            <span>归还日期</span>
            <span>库存有效</span>
            <span>条目有效</span>
        </div>

        <div class="item">
            <span>序号</span>
            <span>日志id</span>
            <span>书籍id</span>
            <span>用户id</span>
            <span>借阅日期</span>
            <span>归还日期</span>
            <span>库存有效</span>
            <span>条目有效</span>
        </div>
        <div class="item">
            <span>序号</span>
            <span>日志id</span>
            <span>书籍id</span>
            <span>用户id</span>
            <span>借阅日期</span>
            <span>归还日期</span>
            <span>库存有效</span>
            <span>条目有效</span>
        </div>
    </div>
</div>