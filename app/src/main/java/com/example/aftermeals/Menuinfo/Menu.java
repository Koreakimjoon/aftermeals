package com.example.aftermeals.Menuinfo;

public class Menu { //메뉴에 대한 이름과 가격
    private String menu_info_name, menu_info_price;


    public Menu(){

    }

    public String getMenu_info_name() {
        return menu_info_name;
    }

    public String getMenu_info_price() {
        return menu_info_price;
    }

    public void setMenu_info_name(String menu_info_name) {
        this.menu_info_name = menu_info_name;
    }

    public void setMenu_info_price(String menu_info_price) {
        this.menu_info_price = menu_info_price;
    }
}
