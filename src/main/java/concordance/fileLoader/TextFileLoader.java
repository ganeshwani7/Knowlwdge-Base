package concordance.fileLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ganeshwani on 9/4/15.
 */
public class TextFileLoader implements FileLoader {
    private static final String[] EMPTY_ARRAY = new String[0];
    private final char [] SENTENCE_TERMINATION = {'.', '?', '!'};
    private final int SPLIT_LIMIT = 2;

    private BufferedReader in;
    private StringBuilder previousLineSentence = new StringBuilder();

    public TextFileLoader (String fileNameIn) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(fileNameIn));
    }

    public String getLine() throws Exception {
        String regex = new String(SENTENCE_TERMINATION);
        regex = "[" + regex + "]";
        StringBuilder sentenceToReturn = new StringBuilder();

        if (previousLineSentence.length() != 0) {
           return returnFromPreviousRead();
        }
        else {
            try {
                String line = in.readLine();
                if (line == null) {
                    sentenceToReturn.append(returnFromPreviousRead());
                    return sentenceToReturn.toString().equals("") ? null : sentenceToReturn.toString();
                }
                if (line.equals("")) {
                    return "";
                }
                String [] sentenceList = splitLineAroundLineEnd(line);

                // Read from the stream till we get complete line
                if (sentenceList.length == 1) {
                    while (sentenceList.length == 1) {
                        for (String sentence : sentenceList) {
                            sentenceToReturn.append(sentence + " ");
                        }

                        line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        sentenceList = splitLineAroundLineEnd(line);
                        if (line.contains(regex)) {
                            break;
                        }
                    }
                } else {
                    for (int i = 1; i < sentenceList.length; i++) {
                        previousLineSentence.append(sentenceList[i] + " ");
                    }
                    sentenceToReturn.append(sentenceList[0]);
                }

//                System.out.println(sentenceToReturn.toString());
                return sentenceToReturn.toString();
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }

    private String returnFromPreviousRead () {
        String [] sentenceList = splitLineAroundLineEnd(previousLineSentence.toString());
        if (sentenceList.length == 0)
            return "";

        previousLineSentence.setLength(0);

        for (int i = 1; i < sentenceList.length; i++) {
            previousLineSentence.append(sentenceList[i].trim());
        }

        return sentenceList[0];
    }

    private String [] splitLineAroundLineEnd(String sentence) {
        if (sentence == null || sentence.equals("")) {
            return EMPTY_ARRAY;
        }
        String regex = new String(SENTENCE_TERMINATION);
        regex = "[" + regex + "]";
        return sentence.split(regex, SPLIT_LIMIT);
    }
}
