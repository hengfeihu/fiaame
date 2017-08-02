package com.assetman.fiaame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by hengfeihu on 2017/7/26.
 */
@Entity
@Table(name = "s_user")
public class User extends BaseModel{
    @Column(name = "name", length = 120)
    public String name; //用户名
    @Column(name = "email", length = 50)
    public String email;//用户邮箱
    @Column(name = "password", length = 120)
    public String password;//用户密码

    @Transient
    public List<Role> roles;
}
