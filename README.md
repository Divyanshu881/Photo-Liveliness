# Liveliness Detection

## Overview

This project is a Java-based implementation for detecting liveliness from a sequence of image frames. The main goal is to analyze consecutive frames to determine whether there is significant movement or change that suggests the subject in the images is "alive" (i.e., not a static image or a video loop). The system uses 30 frames extracted from a video and 30 static images for testing, all of which are stored in the `resources` folder.

## Algorithm Overview

The core algorithm used in this project is a pixel change detection method. The algorithm compares consecutive frames, pixel by pixel, to detect any significant changes between them. If enough pixels change between two consecutive frames, the algorithm concludes that liveliness is detected.

### Steps:
1. **Frame Loading**: Load images from the resources directory.
2. **Frame Comparison**: For each pair of consecutive frames, compare pixel values.
3. **Liveliness Detection**: If a significant change is detected between any pair of frames, liveliness is confirmed.

### High-Level Design (HLD)

#### Component Diagram

+-------------------------------------------------------------+
|                 Liveliness Detection System                 |
|                                                             |
|  +---------------------+         +----------------------+   |
|  |  LivelinessDetectionApp        | LivelinessDetectionServiceImpl |
|  +---------------------+         +----------------------+   |
|             |                              |                 |
|  +---------------------+         +----------------------+   |
|  |  ImageLoader         |         | ImageProcessor           |
|  +---------------------+         +----------------------+   |
|                                         |                   |
|  +---------------------+         +----------------------+   |
|  |  Frame (Model)       |         | PixelChangeProcessor      |
|  +---------------------+         +----------------------+   |
+-------------------------------------------------------------+



#### Sequence Diagram

+-------------------------+        +----------------------------+        +--------------------------+        +-----------------+
| LivelinessDetectionApp   |        | LivelinessDetectionServiceImpl |        | ImageProcessor            |        | Frame           |
+-------------------------+        +----------------------------+        +--------------------------+        +-----------------+
|                         |        |                            |        |                          |        |
|                         |        |                            |        |                          |        |
|--- main() -------------->|        |                            |        |                          |        |
|                         |        |--- detectLiveliness() ---->|        |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |--- loadFramesFromDirectory() -->    |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |<--- list of Frame objects ---|      |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |--- (loop over frames) ---->|        |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |--- process() -------------->|        |                          |        |
|                         |        |   (passing two Frame objects)   |   |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |<--- true/false ------------|        |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |--- continue loop if false -->      |                          |        |
|                         |        |                            |        |                          |        |
|                         |        |<--- return final result ---|        |                          |        |
|                         |        |                            |        |                          |        |
|<--- output result ------|        |                            |        |                          |        |
+-------------------------+        +----------------------------+        +--------------------------+        +-----------------+


### File Structure

LivelinessDetection/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org/
│   │   │   │   ├── pinelab/
│   │   │   │   │   ├── liveliness/
│   │   │   │   │   │   ├── model/
│   │   │   │   │   │   │   ├── Frame.java
│   │   │   │   │   │   ├── services/
│   │   │   │   │   │   │   ├── LivelinessDetectionService.java
│   │   │   │   │   │   │   ├── LivelinessDetectionServiceImpl.java
│   │   │   │   │   │   ├── processor/
│   │   │   │   │   │   │   ├── ImageProcessor.java
│   │   │   │   │   │   │   ├── PixelChangeProcessor.java
│   │   │   │   │   │   ├── utils/
│   │   │   │   │   │   │   ├── ImageLoader.java
│   │   │   │   │   │   ├── LivelinessDetectionApp.java
│   ├── resources/
│   │   ├── sampleFramesTrue/
│   │   ├── sampleFramesFalse/
│   └── pom.xml
│
└── README.md



### How to Run

1. Clone or download the repository.
2. Navigate to the project root.
3. Compile the project using Maven or your preferred IDE.
4. Run the `LivelinessDetectionApp` class.

### Example Command to Run:

If using an IDE:
- Run the `LivelinessDetectionApp` directly.

If using the command line:
```bash
mvn clean install
java -cp target/LivelinessDetection-1.0-SNAPSHOT.jar org.pinelab.liveliness.LivelinessDetectionApp




