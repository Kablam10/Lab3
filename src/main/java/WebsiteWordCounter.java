import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * This class will count the number of words on a website and return it as a printed statement.
 * @author mikep
 *
 */

public class WebsiteWordCounter {

    /**
     * Count the words on a given URL.
     * @param url the URL
     * @return the number of words in the URL
     */
    public static int wordsInUrl(final String url) {
        String contents = urlToString(url);
        int words = 0;

        boolean isAWord = false;
        int lastChar = contents.length() - 1;

        for (int i = 0; i <= lastChar; i++) {
            if (Character.isLetter(contents.charAt(i)) && i != lastChar) {
                isAWord = true;
            } else if (!Character.isLetter(contents.charAt(i)) && isAWord) {
                words++;
                isAWord = false;
            } else if (Character.isLetter(contents.charAt(i)) && i == lastChar) {
                words++;
            }
        }
        return words;
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * Counts and prints the number of words on certain sites.
     * @param args unused
     */
    public static void main(final String[] args) {
        System.out.println("Hamlet contains "
            + wordsInUrl("http://erdani.com/tdpl/hamlet.txt") + " words");
        System.out.println("The CS @ Illinois page contains "
            + wordsInUrl("https://cs.illinois.edu") + " words");
    }
}
