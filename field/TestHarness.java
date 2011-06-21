package field;

import java.io.*;
import java.util.regex.*;

public final class TestHarness {

    private static String REGEX;
    private static String INPUT;
    private static BufferedReader br;
    private static Pattern pattern;
    private static Matcher matcher;
    private static boolean found;

    public static void main(String[] argv) {
        initResources();
        processTest();
        closeResources();
    }

    private static void initResources() {
        INPUT = " a\\ \\a  \\\\";
        REGEX = field.slash;

        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);

        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);
    }

    private static void processTest() {
        while(matcher.find()) {
            System.out.println("I found the text \"" + matcher.group() +
                               "\" starting at index " + matcher.start() +
                               " and ending at index " + matcher.end() + ".");
            found = true;
        }

        if(!found){
            System.out.println("No match found.");
        }
    }

    private static void closeResources() {

    }
}
