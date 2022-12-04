package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booklog.booklog;
import com.dao.dao;
import com.sql.JDBCUtil;

public class booklogmanageservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String change = req.getParameter("change");
        int changeid = (int) Integer.parseInt(change);

        JDBCUtil db = new JDBCUtil();
        booklog booklog = new booklog();
        dao dao = new dao();

        if (changeid == 1) {
            // 搜索框
            String booksearchtext = req.getParameter("search-text");

            List<booklog> bookloglist = new ArrayList<booklog>();

            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            boolean flag = pattern.matcher(booksearchtext).matches();

            int logid, userid, bookid;

            boolean flag1 = false;

            String inputidchangestr = req.getParameter("inputidchange");
            int inputidchange = (int) Integer.parseInt(inputidchangestr);

            if (flag) {
                flag1 = true;
                if (inputidchange == 1) {
                    logid = (int) Integer.parseInt(booksearchtext);
                    booklog.setLogid(logid);
                } else if (inputidchange == 2) {
                    userid = (int) Integer.parseInt(booksearchtext);
                    booklog.setUserid(userid);
                } else {
                    bookid = (int) Integer.parseInt(booksearchtext);
                    booklog.setBookid(bookid);
                }
            }

            if (flag1) {
                try {
                    Connection conn = db.getConn();
                    bookloglist = dao.check_booklog_l(conn, booklog);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                bookloglist = null;
            }

            req.getSession().setAttribute("bookloglist", bookloglist);

            resp.sendRedirect("booklogmanagepage.jsp");

        } else {
            // 增加&修改框
            String logchange = req.getParameter("change");
            int logchangeid = (int) Integer.parseInt(logchange);

            String bookidstr = req.getParameter("bookid");
            String useridstr = req.getParameter("userid");

            int logid, userid, bookid;
            String borrowdatestr = req.getParameter("borrowDate");
            String receivedatestr = req.getParameter("receiveDate");

            String booklogredepotstr = req.getParameter("logundepot");
            String lognullitemstr = req.getParameter("lognullitem");

            Boolean logredepot = ((int) Integer.parseInt(booklogredepotstr) == 1) ? true : false;
            Boolean lognullitem = ((int) Integer.parseInt(lognullitemstr) == 1) ? true : false;

            Date borrowdate = null;
            Date receivedate = null;

            bookid = (int) Integer.parseInt(bookidstr);
            userid = (int) Integer.parseInt(useridstr);

            try {
                if (borrowdatestr != "")
                    borrowdate = new SimpleDateFormat("yyyy-MM-dd").parse(borrowdatestr);
                if (receivedatestr != "")
                    receivedate = new SimpleDateFormat("yyyy-mm-dd").parse(receivedatestr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (logchangeid == 1) {
                // 选项为修改log，需要输入logid
                String logidstr = req.getParameter("logid");
                logid=(int)Integer.parseInt(logidstr);
                
                booklog textbooklog = new booklog();
                try {
                    Connection conn = db.getConn();
                    textbooklog.setBookid(logid);
                    textbooklog = dao.check_booklog_i(conn, textbooklog);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(bookid!=0)
                    textbooklog.setBookid(bookid);
                if(userid!=0)
                    textbooklog.setUserid(userid);
                if(borrowdatestr!="")
                    textbooklog.setBorrowDate(borrowdate);
                if(receivedatestr!="")
                    textbooklog.setReceiveDate(receivedate);
                if(logredepot!=textbooklog.getRedepot()) 
                    textbooklog.setRedepot(logredepot);
                if(lognullitem!=textbooklog.getNullitem()) 
                    textbooklog.setNullitem(lognullitem);

                // 更新booklog数据库内容
                try {
                    Connection conn=db.getConn();
                    dao.update_booklog(conn, booklog);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("booklogmanagepage.jsp");
            } else {
                // 选项为插入log，不需要输入logid
                booklog newbooklog=new booklog();
                if(bookidstr!=""&&useridstr!=""&&borrowdatestr!=""&&receivedatestr!=""){
                    newbooklog.setBookid(bookid);
                    newbooklog.setUserid(userid);
                    newbooklog.setBorrowDate(borrowdate);
                    newbooklog.setReceiveDate(receivedate);
                    newbooklog.setRedepot(logredepot);
                    newbooklog.setNullitem(lognullitem);

                    try {
                        Connection conn=db.getConn();
                        if(dao.insert_booklog(conn, booklog)){
                            System.out.println("已成功添加日志");
                        }
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("值为空，无法添加");
                }

                resp.sendRedirect("booklogmanagepage.jsp");

            }

        }

    }
}
