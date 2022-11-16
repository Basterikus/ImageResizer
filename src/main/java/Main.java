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

    public static void main(String[] args) {
        String srcFolder = "C:/Users/andre/Desktop/src";
        String dstFolder = "C:/Users/andre/Desktop/dst";

        File srcDir = new File(srcFolder);
        filesQueue.addAll(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        for (int i = 0; i < processorCoreCount; i++) {
            new Thread(new ImageResizer(filesQueue, newWidth, dstFolder, start)).start();
        }
    }
}
