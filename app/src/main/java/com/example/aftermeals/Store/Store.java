package com.example.aftermeals.Store;

public class Store { //김준
    private String menu_img;
    private String menu_name;
    private String menu_info;

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public String getMenu_price() {
        return menu_price;
    }

    private String menu_price;



    public Store(){

    }

    public void setMenu_img(String menu_img) {
        this.menu_img = menu_img;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public void setMenu_info(String menu_info) {
        this.menu_info = menu_info;
    }



    public String getMenu_img() {
        return menu_img;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMenu_info() {
        return menu_info;
    }


}
