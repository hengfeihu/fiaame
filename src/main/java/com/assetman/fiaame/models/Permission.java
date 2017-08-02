package com.assetman.fiaame.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hengfeihu on 2017/8/2.
 */
@Entity
@Table(name = "s_permission")
public class Permission extends BaseModel {
    public Integer roleid;
    public String name;
    public String permissionUrl;
    public String method;
    public String description;
}
