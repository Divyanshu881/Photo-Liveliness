package org.pinelab.liveliness;


import org.pinelab.liveliness.services.LivelinessDetectionService;
import org.pinelab.liveliness.services.LivelinessDetectionServiceImpl;
import org.pinelab.liveliness.services.processor.ImageProcessor;
import org.pinelab.liveliness.services.processor.PixelChangeProcessor;
import org.pinelab.liveliness.utils.ImageLoader;

public class LivelinessDetectionApp {

    public static void main(String[] args) {
        // Test directories
        String frameDirectoryTrue = "src/main/resources/sampleFramesTrue";
        String frameDirectoryFalse = "src/main/resources/sampleFramesFalse";

        // Run tests for both directories
        runLivelinessTest(frameDirectoryTrue, "Expected to detect liveliness");
        runLivelinessTest(frameDirectoryFalse, "Expected NOT to detect liveliness");
    }

    private static void runLivelinessTest(String frameDirectory, String expectation) {
        try {
            System.out.println("Testing directory: " + frameDirectory);
            System.out.println("Expectation: " + expectation);

            // Instantiate dependencies
            ImageLoader imageLoader = new ImageLoader();
            ImageProcessor imageProcessor = new PixelChangeProcessor();
            LivelinessDetectionService livelinessDetectionService = new LivelinessDetectionServiceImpl(imageProcessor, imageLoader);

            // Detect liveliness based on the provided directory of frames
            boolean isLive = livelinessDetectionService.detectLiveliness(frameDirectory);
            System.out.println(isLive ? "Liveliness confirmed!" : "No liveliness detected.");
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            System.err.println("An error occurred while testing directory: " + frameDirectory);
            e.printStackTrace();
        }
    }
}

