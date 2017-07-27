package com.assetman.fiaame.models;

import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * Created by hengfeihu on 2017/7/25.
 */
@MappedSuperclass
public class BaseModel extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @CreatedTimestamp
    public Timestamp createTime;
    @UpdatedTimestamp
    public Timestamp updateTime;
}
