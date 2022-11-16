package com.sql;

import java.sql.*;

//弃用

public class sqlfunc {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL1 = "jdbc:mysql://localhost:3306;DatabaseName=textsql01";
    private final static String SQL_USER = "root";
    private final static String SQL_PASSWD = "0909";

    public static Connection getcConn() throws SQLException {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL1, SQL_USER, SQL_PASSWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 判断帐号是否等于密码方法，return布尔类
    // todo
    public static boolean bl_username(String username, String passwd) throws SQLException {
        String sql_blAddress = "SELECT username,password FROM text01 WHERE BINARY username=\'?\'";
        Connection conn = getcConn();
        boolean _bl = false;

        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql_blAddress);
                if (ps != null) {
                    ps.setString(1, "username");
                    ResultSet rs = ps.executeQuery();
                    if (rs != null) {
                        String sqlpasswd = rs.getString("password");
                        if (sqlpasswd == passwd)
                            _bl = true;
                        rs.close();
                    }
                    ps.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return _bl;
    }

    // 帐号注册，方法添加
    public static void addUser(String username, String password, String mail, String tel, String sex)
            throws SQLException {
        String sql_insert = "insert into text01(username,password,mail,tel,sex) VALUES(?,?,?,?,?)";
        Connection con = getcConn();

        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(sql_insert);
                if (ps != null) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, mail);
                    ps.setString(4, tel);
                    ps.setString(5, sex);

                    ps.execute();
                }
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.close();
        System.out.println("add user success");
    }

}
