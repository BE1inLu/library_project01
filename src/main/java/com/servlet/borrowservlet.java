package com.servlet;

import java.io.IOException;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booklog.booklog;
import com.mysql.cj.util.Util;

public class borrowservlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // borrowpageservlet，用于借书页后台信息操作
        // 获取前台回传的值
        // TODO：代码优化
        String useridstr=req.getParameter("userid");
        String username=req.getParameter("username");
        String bookidstr=req.getParameter("bookid");
        String borrowdaystr=req.getParameter("borrowday");

        // str转int，用于后续操作
        int userid=Integer.valueOf(useridstr);
        int bookid=Integer.valueOf(bookidstr);
        int borrowday=Integer.valueOf(borrowdaystr);
        long borrowday_text=borrowday*86400000;//备注：时间戳1天=86400000

        // 获取借出时间和归还时间
        Date borrowDate=new Date();
        Date receivDate=new Date(borrowDate.getTime()+borrowday_text);

        // 创建一个booklog,用于dao操作
        booklog booklog=new booklog(bookid, userid, borrowDate, receivDate, false);

        // booklogDAO操作调用，回传参数，后台数据库操作，返回布尔类
        // 调用结束，返回主页，弹出提示框，失败则返回提示框
        
    }

}
