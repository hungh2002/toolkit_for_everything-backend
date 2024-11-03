package com.ryu.toolkit_for_everything.dto.webSocket.message;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketMessage {

    private MessageHeader header;

    private Map<String, Object> content;

}
