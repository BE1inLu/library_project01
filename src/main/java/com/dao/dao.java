package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.*;
import com.book.*;
// import com.sql.JDBCUtil;

public class dao {

    // user数据库操作--登录模块
    public user login(Connection conn, user user) throws Exception {

        user resultUser = null;

        // 数据库指令
        String sql = "select * from user where username=? and password=?";

        // 获得对象
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());

        // 获得结果
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            resultUser = new user();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }

        return resultUser;
    }

    // user数据库操作--注册模块
    public boolean regist(Connection conn, user user) throws Exception {

        // 定义一个布尔类，用于返回操作结果
        boolean flag = false;

        // sql指令操作
        String sql = "insert into user(username,password,mail,tel,sex) VALUES(?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        // 接收前台回传的数据，匹配指令val
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getTel());
        pst.setString(5, user.getSex());

        // 执行指令，返回int数值用来表示注册是否成功
        int res = pst.executeUpdate();
        if (res > 0)
            flag = true;
        return flag;
    }

    // TODO：user数据库操作，回传user数据库信息

    // book数据库操作，展示书籍列表
    public List<book> see_book(Connection conn, book book) throws Exception {
        book book1 = new book();
        List<book> booklist=new ArrayList<book>();
        String sql = "select book_id,bookname,bookdepot from book";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            book1.setBookid(rs.getInt("bookid"));
            book1.setBookname(rs.getString("bookname"));
            booklist.add(book1);
        }
        return booklist;
    }

    // book数据库操作--接收书籍名，回传书籍借阅情况，没有就回传全部在库数目
    public List<book> getBook(Connection conn, book book) throws Exception {
        
        book newbook = new book();

        List<book> listbook=new ArrayList<book>();
        String sql = "select * from book where bookname=?";
        String sql1 = "select * from book where depot=01";
        PreparedStatement pst = null;

        if (book.getBookname() == "") {
            pst = conn.prepareStatement(sql1);
        } else {
            pst = conn.prepareStatement(sql);
            pst.setString(1, newbook.getBookname());
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            //text code
            System.out.println("回传数据==========");
            System.out.println("bookid:"+rs.getInt("bookid"));
            System.out.println("bookname:"+rs.getString("bookname"));
            System.out.println("borrow-num:"+rs.getInt("borrow-num"));
            System.out.println("receive-num:"+rs.getInt("receive-num"));
            System.out.println("depot:"+rs.getBoolean("depot"));

            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setDepot(rs.getBoolean("depot"));
            listbook.add(newbook);
            //text code
            System.out.println("添加书本成功");
        }

        //text code
        System.out.println("=========");
        System.out.println("dao操作获取数据成功");
        // System.out.println("bookname:"+book.getBookname());
        // System.out.println(newbook.getBookname());
        // System.out.println(listbook.get(1).getBookname());

        return listbook;
    }

    // TODO:booklog数据库操作

}
