package com.ryu.toolkit_for_everything.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.ryu.toolkit_for_everything.dto.canvasDTO.CanvasDTO;

@Controller
public class DrawingController {
    @MessageMapping("/painter")
    @SendTo("/topic/drawing")
    public CanvasDTO greeting(CanvasDTO canvasDTO) {
        return canvasDTO;
    }
}
