package com.assetman.fiaame.models;

import io.ebean.Model;

import javax.persistence.*;

/**
 * Created by hengfeihu on 2017/7/25.
 */
@Entity
@Table(name = "test")
public class Test extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Integer test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }
}
