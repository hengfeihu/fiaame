package com.assetman.fiaame.models;

import java.util.Date;

/**
 * Created by hengfeihu on 2017/8/7.
 */
public class SocketMessage {
    public String username;
    public String message;
    public Date date;

    public SocketMessage(String message) {
        this.message = message;
        this.date = new Date();
    }
}
