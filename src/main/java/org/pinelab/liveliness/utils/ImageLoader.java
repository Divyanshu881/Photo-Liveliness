package org.pinelab.liveliness.utils;

import org.pinelab.liveliness.model.Frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageLoader {

    public List<Frame> loadFramesFromDirectory(String directoryPath) throws IOException {
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException("Directory does not exist or is not a directory: " + directoryPath);
        }

        File[] files = Optional.ofNullable(dir.listFiles((d, name) -> name.toLowerCase().endsWith(".jpg")))
                .orElseThrow(() -> new IOException("No JPG files found in directory: " + directoryPath));

        return Stream.of(files)
                .sorted()
                .map(this::loadImage)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::convertToImageVector)
                .map(Frame::new)
                .collect(Collectors.toList());
    }

    private Optional<BufferedImage> loadImage(File file) {
        try {
            return Optional.ofNullable(ImageIO.read(file));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + file.getAbsolutePath());
            return Optional.empty();
        }
    }

    private Integer[][] convertToImageVector(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Integer[][] imageVector = new Integer[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                imageVector[x][y] = image.getRGB(x, y);
            }
        }
        return imageVector;
    }
}
