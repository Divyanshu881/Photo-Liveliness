package org.pinelab.liveliness.services;

import org.pinelab.liveliness.model.Frame;
import org.pinelab.liveliness.services.processor.ImageProcessor;
import org.pinelab.liveliness.utils.ImageLoader;

import java.util.List;

import java.util.stream.IntStream;

public class LivelinessDetectionServiceImpl implements LivelinessDetectionService {

    private final ImageProcessor imageProcessor;
    private final ImageLoader imageLoader;

    public LivelinessDetectionServiceImpl(ImageProcessor imageProcessor, ImageLoader imageLoader) {
        this.imageProcessor = imageProcessor;
        this.imageLoader = imageLoader;
    }

    @Override
    public boolean detectLiveliness(String frameDirectory) {
        try {
            List<Frame> frames = imageLoader.loadFramesFromDirectory(frameDirectory);

            return IntStream.range(1, frames.size())
                    .mapToObj(i -> imageProcessor.process(frames.get(i - 1), frames.get(i)))
                    .filter(result -> result)
                    .findFirst()
                    .orElse(false);

        } catch (Exception e) {
            System.err.println("Error during liveliness detection: " + e.getMessage());
            return false;
        }
    }
}
