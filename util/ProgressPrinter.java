package util;

public class ProgressPrinter {
    public static void printStatus(int current, int total, String image, String filter, boolean success) {
        String status = success ? "Done." : "Failed.";
        System.out.printf("[%d/%d] Applying %-10s to %-20s... %s%n", current, total, filter, image, status);
    }
}
