package com.goldenburguer.app.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @MessageMapping("/kitchen")
  @SendTo("/topic/kitchen")
  public String broadcastNews(@Payload String message) {
    return message;
  }
}
