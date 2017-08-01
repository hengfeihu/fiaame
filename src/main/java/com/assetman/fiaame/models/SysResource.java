package com.assetman.fiaame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hengfeihu on 2017/8/1.
 */
@Entity
@Table(name = "s_resource")
public class SysResource extends BaseModel {
    @Column(name = "resourceString", length = 1000)
    public String resourceString;//url

    @Column(name = "resourceId", length = 50)
    public String resourceId;//资源ID

    @Column(name = "remark", length = 200)
    public String remark;//备注

    @Column(name = "resourceName", length = 400)
    public String resourceName;//资源名称

    @Column(name = "methodName", length = 400)
    public String methodName;//资源所对应的方法名

    @Column(name = "methodPath", length = 1000)
    public String methodPath;//资源所对应的包路径
}
