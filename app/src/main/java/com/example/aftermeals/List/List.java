package com.example.aftermeals.List;

public class List { //가게 정보 내용 입력
    private String storelist_img, storelist_name, storelist_time, storelist_breaktime, storelist_number;


    public List(){

    }

    public void setStorelist_img(String storelist_img) {
        this.storelist_img = storelist_img;
    }

    public void setStorelist_name(String storelist_name) {
        this.storelist_name = storelist_name;
    }

    public void setStorelist_time(String storelist_time) {
        this.storelist_time = storelist_time;
    }

    public void setStorelist_breaktime(String storelist_breaktime) {
        this.storelist_breaktime = storelist_breaktime;
    }

    public void setStorelist_number(String storelist_number) {
        this.storelist_number = storelist_number;
    }

    public String getStorelist_img() {
        return storelist_img;
    }

    public String getStorelist_name() {
        return storelist_name;
    }

    public String getStorelist_time() {
        return storelist_time;
    }

    public String getStorelist_breaktime() {
        return storelist_breaktime;
    }

    public String getStorelist_number() {
        return storelist_number;
    }
}
