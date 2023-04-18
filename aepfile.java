import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveAEPFiles {
    public static void main(String[] args) {
        // Define the source and destination directories
        File sourceDir = new File("/");
        File destinationDir = new File("/Users/your_username/Documents/Video Work/");

        // Find all .aep files on the computer and move them to the destination folder
        moveAEPFiles(sourceDir, destinationDir);

        System.out.println("All .aep files have been moved to the Video Work folder.");
    }

    private static void moveAEPFiles(File sourceDir, File destinationDir) {
        // Get a list of all files in the source directory
        File[] files = sourceDir.listFiles();

        // Iterate through each file and move any .aep files to the destination directory
        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively search for .aep files in subdirectories
                moveAEPFiles(file, destinationDir);
            } else if (file.getName().endsWith(".aep")) {
                Path sourcePath = Paths.get(file.getAbsolutePath());
                Path destinationPath = Paths.get(destinationDir.getAbsolutePath() + File.separator + file.getName());

                try {
                    Files.move(sourcePath, destinationPath);
                } catch (IOException e) {
                    System.err.println("Error moving file: " + e.getMessage());
                }
            }
        }
    }
}
