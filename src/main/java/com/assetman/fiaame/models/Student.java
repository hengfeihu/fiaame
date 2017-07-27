package com.assetman.fiaame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hengfeihu on 2017/7/25.
 */
@Entity
@Table(name = "student" )
public class Student extends BaseModel {

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
