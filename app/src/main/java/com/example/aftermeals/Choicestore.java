package com.example.aftermeals;

public class Choicestore {

    private String choicestore_name = "choicestore_name";
    private String choicestore_img = "choicestore_img";
    private String choicestore_time = "choicestore_time";
    private String choicestore_breaktime = "choicestore_breaktime";
    private String choicestore_number = "choicestore_number";

    public Choicestore() {
    }

    public Choicestore(String choicestore_name, String choicestore_img, String choicestore_time, String choicestore_breaktime, String choicestore_number) {
        this.choicestore_name = choicestore_name;
        this.choicestore_img = choicestore_img;
        this.choicestore_time = choicestore_time;
        this.choicestore_breaktime = choicestore_breaktime;
        this.choicestore_number = choicestore_number;
    }

    public String getChoicestore_name() {
        return choicestore_name;
    }

    public void setChoicestore_name(String choicestore_name) {
        this.choicestore_name = choicestore_name;
    }

    public String getChoicestore_img() {
        return choicestore_img;
    }

    public void setChoicestore_img(String choicestore_img) {
        this.choicestore_img = choicestore_img;
    }

    public String getChoicestore_time() {
        return choicestore_time;
    }

    public void setChoicestore_time(String choicestore_time) {
        this.choicestore_time = choicestore_time;
    }

    public String getChoicestore_breaktime() {
        return choicestore_breaktime;
    }

    public void setChoicestore_breaktime(String choicestore_breaktime) {
        this.choicestore_breaktime = choicestore_breaktime;
    }

    public String getChoicestore_number() {
        return choicestore_number;
    }

    public void setChoicestore_number(String choicestore_number) {
        this.choicestore_number = choicestore_number;
    }

    @Override
    public String toString() {
        return "Choicestore{" +
                "choicestore_name='" + choicestore_name + '\'' +
                ", choicestore_img='" + choicestore_img + '\'' +
                ", choicestore_time='" + choicestore_time + '\'' +
                ", choicestore_breaktime='" + choicestore_breaktime + '\'' +
                ", choicestore_number='" + choicestore_number + '\'' +
                '}';
    }
}
