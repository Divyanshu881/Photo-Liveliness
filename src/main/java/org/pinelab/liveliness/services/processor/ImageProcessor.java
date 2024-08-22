package org.pinelab.liveliness.services.processor;

import org.pinelab.liveliness.model.Frame;

public interface ImageProcessor {
    Boolean process(Frame previousFrame, Frame currentFrame);
}
