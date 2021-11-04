package kanzi.serializer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Log {
    private static long startTime;
    private static long stopTime;
    private static long elapsedTime;

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    private static BufferedWriter writer;
    private static String logFile = "./log/log-output.txt";
    
    static {
        try {
            writer = new BufferedWriter(new FileWriter(logFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void startExperiment() {
        startExperiment("Data Structure", "Type of Experiment ");
    }

    public static void startExperiment(String dataStructure, String typeOfExperiment) {
        try {
            writer.write("-- " + dataStructure + ", " + typeOfExperiment + " --");
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();
    }

    public static void endExperiment() {
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;

        try {
            writer.write( String.format("Elapsed time: %.2f", elapsedTime / 1000.0) + " seconds");
            writer.newLine();

            writer.write("-- END --");
            writer.newLine();
            writer.newLine();

            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long memory = runtime.totalMemory() - runtime.freeMemory();
        try {
            writer.write("Memory used: " + bytesToMegabytes(memory) + " MB");
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reset() {

    }
}
