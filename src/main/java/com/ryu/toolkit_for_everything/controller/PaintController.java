package com.ryu.toolkit_for_everything.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.ryu.toolkit_for_everything.dto.canvasDTO.CanvasDTO;

@RestController
@CrossOrigin
public class PaintController {
    @MessageMapping("/painter")
    @SendTo("/topic/paint")
    public CanvasDTO greeting(CanvasDTO canvasDTO) {
        return canvasDTO;
    }
}
