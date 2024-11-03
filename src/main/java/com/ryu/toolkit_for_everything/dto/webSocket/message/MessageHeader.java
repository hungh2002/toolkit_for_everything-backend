package com.ryu.toolkit_for_everything.dto.webSocket.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageHeader {
    private MessageType type;

    // private Long senderId;
}
