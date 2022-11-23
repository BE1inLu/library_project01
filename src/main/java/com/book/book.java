package com.book;


public class book {
    
    int bookid=0;
    String bookname=null;
    int borrow_num=0;
    int receive_num=0;
    boolean depot=true;

    public book(){
        super();
    }

    public book(int bookid,String bookname,int borrow_num,int receive_num,Boolean depot){
        this.bookid=bookid;
        this.bookname=bookname;
        this.borrow_num=borrow_num;
        this.receive_num=receive_num;
        this.depot=depot;
    }

    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getBorrow_num() {
        return borrow_num;
    }
    public void setBorrow_num(int borrow_num) {
        this.borrow_num = borrow_num;
    }

    public int getReceive_num() {
        return receive_num;
    }
    public void setReceive_num(int receive_num) {
        this.receive_num = receive_num;
    }

    public boolean getDepot() {
        return depot;
    }
    public void setDepot(Boolean depot) {
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "bookid="+bookid+",bookname="+bookname+",=borrow_num="+borrow_num+",receive_num="+receive_num+",depot="+depot;
    }

}
