package com.ryu.toolkit_for_everything.dto.canvasDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanvasDTO {
    private boolean isDraw;

    private PositionDTO startPosition;

    private PositionDTO currentPosition;
}
