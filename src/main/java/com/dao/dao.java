package com.dao;

import java.sql.*;
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

    // TODO：book数据库操作，展示书籍列表

    // TODO：book数据库操作，接收书籍名，回传书籍借阅情况
    public book[] getBook(Connection conn, book book) throws Exception {
        book newbook[] = null;
        String sql = "select * from book where bookname=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, book.getBookname());

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

        }

        return newbook;
    }

}
