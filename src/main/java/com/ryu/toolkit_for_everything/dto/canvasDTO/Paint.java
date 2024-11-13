package com.ryu.toolkit_for_everything.dto.canvasDTO;

import lombok.Getter;
import lombok.Setter;

enum PaintAction {START,MOVE,END}

@Getter
@Setter
public class Paint {

    private PaintAction action;

    private Position position;

    
}
