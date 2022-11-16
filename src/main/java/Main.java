import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int newWidth = 300;
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static final Queue<File> filesQueue = new ConcurrentLinkedQueue<>();
    private static final long start = System.currentTimeMillis();
    private static final String SRC_FOLDER = "C:/src";
    private static final String DST_FOLDER = "C:/dst";

    public static void main(String[] args) {
        File srcDir = new File(SRC_FOLDER);
        filesQueue.addAll(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        for (int i = 0; i < processorCoreCount; i++) {
            new Thread(new ImageResizer(filesQueue, newWidth, DST_FOLDER, start)).start();
        }
    }
}
