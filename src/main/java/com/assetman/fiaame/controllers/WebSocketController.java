package com.assetman.fiaame.controllers;

import com.assetman.fiaame.models.SecurityUser;
import com.assetman.fiaame.models.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

/**
 * Created by hengfeihu on 2017/8/4.
 */
@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    public SocketMessage handleChat(String message, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        SocketMessage socketMessage = new SocketMessage(message);
        if (securityUser != null) {
            socketMessage.username = securityUser.name;
        }
        return socketMessage;
    }
}
