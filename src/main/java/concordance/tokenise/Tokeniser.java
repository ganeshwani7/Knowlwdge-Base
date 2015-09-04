package concordance.tokenise;

/**
 * Created by ganeshwani on 9/4/15.
 */
public class Tokeniser {
    private static final String[] EMPTY_ARRAY = new String[0];
    private static final String SPLITTER = " ";

    public static String [] getTokens (String sentence) {
        if (sentence == null || sentence.equals("")) {
            return EMPTY_ARRAY;
        }
        return filterTokens(sentence);
    }

    private static String [] filterTokens (final String anyString) {
        String filteredString;
//        filteredString = anyString.toLowerCase().replaceAll("'", "");
        filteredString = anyString.trim().toLowerCase().replaceAll(",", "");
        filteredString = filteredString.replaceAll("\\)", "");
        filteredString = filteredString.replaceAll("\\(", "");
        filteredString = filteredString.replaceAll("\"", "");

        return filteredString.split(SPLITTER);
    }
}
