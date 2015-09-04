package concordance;

/**
 * Created by ganeshwani on 9/4/15.
 */
public class FileLoaderFactory {
    public static void getParser(String fileName) {
        if (fileName == null || fileName.equals("")) {
            // File Format is null or empty
        }

        if (fileName.endsWith(".txt")) {
            // File is text
        } else if (fileName.endsWith(".pdf")) {
            // File is PDF
        } else if (fileName.endsWith(".word")) {
            // File is WORD
        }
    }
}
