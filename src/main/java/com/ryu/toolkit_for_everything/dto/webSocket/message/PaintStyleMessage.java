package com.ryu.toolkit_for_everything.dto.webSocket.message;

import com.ryu.toolkit_for_everything.dto.canvasDTO.Brush;
import com.ryu.toolkit_for_everything.dto.canvasDTO.SizeBoard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaintStyleMessage extends WebSocketMessage {
    private SizeBoard sizeBoard;

    private Brush brush;
}
