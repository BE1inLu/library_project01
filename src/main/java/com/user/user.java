package com.user;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;

public class user {

    // 用户类
    private int userid=0;
    private boolean superuser=false;
    private String username = null;
    private String password = null;
    private String email = null;
    private String tel = null;
    private String sex = null;

    public user(){
        super();
    }

    public user(String username,String password){
        this.username=username;
        this.password=password;
    }

    public user(String username, String password, String email, String tel, String sex) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.sex = sex;
    }

    public user(int userid,boolean superuser,String username, String password, String email, String tel, String sex) {
        this.userid=userid;
        this.superuser=superuser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.sex = sex;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public boolean getSuperuser() {
        return superuser;
    }
    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "User[userid="+userid+",superuser"+superuser+",username=" + username + ",password=" + password + ",email=" + email + ",tel=" + tel + ",sex=" + sex
                + "]";
    }

}
