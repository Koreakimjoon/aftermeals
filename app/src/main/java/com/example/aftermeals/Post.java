package com.example.aftermeals;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post { //하성빈 제작 게시판 객체들

    private String title;
    private String contents;
    private String nickname;
    @ServerTimestamp
    private Date date;

    public Post() {
    }

    public Post(String title, String nickname, String contents) {
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date=" + date +
                '}';
    }
}
