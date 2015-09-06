package concordance.main;

import concordance.concord.Concord;
import concordance.factory.FileLoaderFactory;
import concordance.fileLoader.FileLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

/**
 * Created by ganeshwani on 9/4/15.
 */
//@Slf4j
public class ConcordanceMain {
    public static void main (String [] args) {
        if (args.length != 1) {
//            log.error("Please enter the filename, Format program name <filename>");
            System.out.println("Please enter the filename, Format program name <filename>");
            System.exit(1);
        }

        String fileName = args[0];
        FileLoader fileLoader = null;
        try {
            fileLoader = FileLoaderFactory.getParser(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not found");
            System.exit(1);
        }

        if (fileLoader == null) {
//            log.error("Currently Concord only supports Text, PDF and Word file format");
            System.out.println("Currently Concord only supports Text, PDF and Word file format");
            System.exit(1);
        }

        Concord concord = new Concord(fileLoader);
        try {
            System.out.println(concord.getConcordance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
