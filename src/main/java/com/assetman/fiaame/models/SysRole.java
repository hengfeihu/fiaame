package com.assetman.fiaame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hengfeihu on 2017/8/1.
 */
@Entity
@Table(name = "s_role")
public class SysRole extends BaseModel {

    public Integer uid;

    @Column(name = "name", length = 100)
    public String name;
}
