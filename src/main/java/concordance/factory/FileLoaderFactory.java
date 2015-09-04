package concordance.factory;

import concordance.fileLoader.FileLoader;
import concordance.fileLoader.TextFileLoader;

import java.io.FileNotFoundException;

/**
 * Created by ganeshwani on 9/4/15.
 */
public class FileLoaderFactory {
    public static FileLoader getParser(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.equals("")) {
            // File Format is null or empty
            return null;
        }

        if (fileName.endsWith(".txt")) {
            // File is text
            return new TextFileLoader(fileName);
        } else if (fileName.endsWith(".pdf")) {
            // File is PDF
            return null;
        } else if (fileName.endsWith(".word")) {
            // File is WORD
            return null;
        }
        return null;
    }
}
