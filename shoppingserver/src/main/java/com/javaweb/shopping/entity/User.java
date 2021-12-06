package com.javaweb.shopping.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String sex;
    private String description;
    private String email;
    private String nickname;
    private Timestamp regTime; //登入时间


    public User() {
    }

    public User(int id, String name, String sex, String description, String email, String nickname) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.description = description;
        this.email = email;
        this.nickname = nickname;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
