package com.bean;

// import com.sql.sqlfunc;
// import java.sql.*;

// 弃用

public class regist {
    String username = null;
    String password = null;
    String email = null;
    String tel = null;
    String sex = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    // // 将注册结果返回，成功返回true跳转，否则返回false报错
    // public boolean bl_regist() {
    //     try {
    //         sqlfunc.addUser(username, password, email, tel, sex);
    //         return true;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "regist";
    }
}
