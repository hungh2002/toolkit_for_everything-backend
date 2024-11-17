package com.ryu.toolkit_for_everything.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ryu.toolkit_for_everything.dto.webSocket.message.PaintMessage;
import com.ryu.toolkit_for_everything.dto.webSocket.message.PaintStyleMessage;

@Controller
public class PaintController {

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/room/{roomId}/paint-style")
    public void paintStyle(@DestinationVariable long roomId, PaintStyleMessage message) {
        template.convertAndSend("/topic/room" + "/" + roomId + "/" + "paint-style", message);
    }

    @MessageMapping("/room/{roomId}/paint")
    public void paint(@DestinationVariable long roomId, PaintMessage message) {
        template.convertAndSend("/topic/room" + "/" + roomId + "/" + "paint", message);
    }
}
