package com.ryu.toolkit_for_everything.dto.webSocket.message;

import com.ryu.toolkit_for_everything.dto.canvasDTO.Paint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaintMessage extends WebSocketMessage  {

    private Paint paint;
}
