package com.text;


import java.sql.Connection;


import com.booklog.booklog;
import com.dao.dao;
import com.sql.JDBCUtil;

public class textjava {
    public static void main(String[] args) {
        dao dao = new dao();
        JDBCUtil db = new JDBCUtil();
        boolean flag = false;
        booklog booklog = new booklog();
        java.util.Date borrowDate = new java.util.Date();
        long borrowday_text = 7 * 86400000;
        java.util.Date receiveDate = new java.util.Date(borrowDate.getTime() + borrowday_text);
        booklog.setBookid(4);
        booklog.setUserid(1);
        booklog.setBorrowDate(borrowDate);
        booklog.setReceiveDate(receiveDate);

        try {
            Connection conn = db.getConn();
            flag = dao.borrow_book(conn, booklog);
            System.out.println("borrow操作成功:" + flag);
        } catch (Exception e) {
            System.out.println("borrowbook操作失败:" + flag);
        }
    }
}
