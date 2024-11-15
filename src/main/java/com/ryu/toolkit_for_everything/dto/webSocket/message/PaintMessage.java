package com.ryu.toolkit_for_everything.dto.webSocket.message;

import com.ryu.toolkit_for_everything.dto.canvasDTO.Position;
import lombok.Getter;
import lombok.Setter;

enum PaintAction {START,MOVE,END}

@Getter
@Setter
public class PaintMessage extends WebSocketMessage  {

    private PaintAction action;

    private Position position;
}
