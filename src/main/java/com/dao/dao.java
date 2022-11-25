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
            resultUser.setUserid(rs.getInt("userid"));
            resultUser.setSuperuer(rs.getBoolean("superuser"));
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
            resultUser.setEmail(rs.getString("mail"));
            resultUser.setSex(rs.getString("sex"));
        }

        return resultUser;
    }

    // user数据库操作--注册模块
    public boolean regist(Connection conn, user user) throws Exception {

        // 定义一个布尔类，用于返回操作结果
        boolean flag = false;

        // sql指令操作
        String sql = "insert into user(superuser,username,password,mail,tel,sex) VALUES(0,?,?,?,?,?)";
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
        List<book> booklist = new ArrayList<book>();
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
    public List<book> getBook(Connection conn, String bookname) throws Exception {

        List<book> listbook = new ArrayList<book>();
        String sql = "select * from book where bookname like ? ";
        String sql1 = "select * from book where depot=01";
        PreparedStatement pst = null;

        if (bookname == "") {
            pst = conn.prepareStatement(sql1);
        } else {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+bookname+"%");
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            book newbook = new book();

            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setDepot(rs.getBoolean("depot"));
            listbook.add(newbook);

        }


        return listbook;
    }

    // booklog数据库操作，借书操作
    public boolean borrow_book(Connection conn){
        /*
            1，获取conn，bookid，和userid（通过前台回传userid）
            2，判断bookid是否在库，userid是否错误
        */ 


        return false;
    }

    // TODO:booklog数据库操作

}
