package org.pinelab.liveliness.services.processor;

import org.pinelab.liveliness.model.Frame;

public class PixelChangeProcessor implements ImageProcessor{

    @Override
    public Boolean process(Frame previousFrame, Frame currentFrame) {
        Integer[][] previousPixels = previousFrame.getPixels();
        Integer[][] currentPixels = currentFrame.getPixels();
        int width = previousPixels.length;
        int height = previousPixels[0].length;
        int changeCount = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!previousPixels[x][y].equals(currentPixels[x][y])) {
                    changeCount++;
                }
            }
        }

        return changeCount > (width * height * 0.01); // threshold: 1% of pixels changed
    }
}
