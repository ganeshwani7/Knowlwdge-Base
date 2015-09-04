package concordance.tokenise;

/**
 * Created by ganeshwani on 9/4/15.
 */
public class Tokeniser {
    private static final String[] EMPTY_ARRAY = new String[0];
    private static final String SPLIT = " ";

    public static String [] getTokens (String sentence) {
        if (sentence == null || sentence.equals("")) {
            return EMPTY_ARRAY;
        } else {
            return sentence.split(SPLIT);
        }
    }
}
