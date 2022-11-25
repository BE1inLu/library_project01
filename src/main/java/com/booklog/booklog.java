package com.booklog;

import java.util.Date;


public class booklog {
    // booklog的java类
    int logid=0;
    int bookid=0;
    int userid=0;
    Date borrowDate=null;
    Date receiveDate=null;
    boolean redepot=false;
    boolean nullitem=false;

    public booklog(){
        super();
    }

    public booklog(int logid,int bookid,int userid,Date borrowDate,Date receiveDate,boolean redepot,boolean nullitem){
        this.logid=logid;
        this.bookid=bookid;
        this.userid=userid;
        this.borrowDate=borrowDate;
        this.receiveDate=receiveDate;
        this.redepot=redepot;
        this.nullitem=nullitem;
    }

    public int getLogid() {
        return logid;
    }
    public void setLogid(int logid) {
        this.logid = logid;
    }

    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public boolean getRedepot() {
        return redepot;
    }
    public void setRedepot(boolean redepot) {
        this.redepot = redepot;
    }

    public boolean getNullitem() {
        return nullitem;
    }
    public void setNullitem(boolean nullitem) {
        this.nullitem = nullitem;
    }


}
