package concordance.concord;

import concordance.fileLoader.FileLoader;
import concordance.tokenise.Tokeniser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ganeshwani on 9/4/15.
 */

public class Concord {
    FileLoader fileLoader;
    Map<String, List<Long>> concordMap = new TreeMap<String, List<Long>>();

    public Concord (FileLoader fileLoaderIn) {
        this.fileLoader = fileLoaderIn;
    }

    public String getConcordance () throws Exception {
        String sentence;
        long sentenceCount = 1;

        while (true) {
            sentence = fileLoader.getLine();
            if (sentence == null) {
                break;
            }
            String [] tokens = Tokeniser.getTokens(sentence);
            for (String token : tokens) {
                List<Long> sentenceList = concordMap.get(token);
                sentenceList = sentenceList == null ? new ArrayList<Long>() : sentenceList;
                sentenceList.add(sentenceCount);
            }
            ++sentenceCount;
        }
        return createAwesomeConcordance();
    }

    private String createAwesomeConcordance() {
        StringBuilder sb = new StringBuilder();
        for (String key : concordMap.keySet()) {
            sb.append(key);
            sb.append("\t\t");
            sb.append("{");
            for (long sentenceNumber : concordMap.get(key)) {
                sb.append(sentenceNumber);
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append("}\n");
        }
        return sb.toString();
    }
}
