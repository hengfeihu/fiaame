package com.assetman.fiaame.models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by hengfeihu on 2017/7/26.
 */
@Entity
@Table(name = "user")
public class User extends Model {
    String username;
    String password;

    @Transient
    String age;

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
}
