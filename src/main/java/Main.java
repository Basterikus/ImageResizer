import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int NEW_WIDTH = 300;
    private static final int PROCESSOR_CORE_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Queue<File> FILES_QUEUE = new ConcurrentLinkedQueue<>();
    private static final long START = System.currentTimeMillis();
    private static final String SRC_FOLDER = "C:/src";
    private static final String DST_FOLDER = "C:/dst";

    public static void main(String[] args) {
        File srcDir = new File(SRC_FOLDER);
        FILES_QUEUE.addAll(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        for (int i = 0; i < PROCESSOR_CORE_COUNT; i++) {
            new Thread(new ImageResizer(FILES_QUEUE, NEW_WIDTH, DST_FOLDER, START)).start();
        }
    }
}
