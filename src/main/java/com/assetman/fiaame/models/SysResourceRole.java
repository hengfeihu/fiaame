package com.assetman.fiaame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hengfeihu on 2017/8/1.
 */
@Entity
@Table(name = "s_resource_role")
public class SysResourceRole extends BaseModel {
    @Column(name = "roleId", length = 50)
    private String roleId; //角色ID

    @Column(name = "resourceId", length = 50)
    private String resourceId;//资源ID
}
