package com.sql;

import java.sql.*;

// 数据库链接
public class JDBCUtil {
    //用的是8.0数据库驱动，所以driver要修改成com.mysql.cj.jdbc.Driver
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL1 = "jdbc:mysql://localhost:3306/textsql01?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    private final static String SQL_USER = "root";
    private final static String SQL_PASSWD = "0909";

    public Connection getConn() throws SQLException {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL1, SQL_USER, SQL_PASSWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

}
