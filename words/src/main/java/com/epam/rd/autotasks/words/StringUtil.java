package com.epam.rd.autotasks.words;

import java.util.Arrays;

public class StringUtil {
    
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (words == null || sample == null || words.length == 0) {
            return 0;
        }
        
        int count = 0;
        for (String word : words) {
            if (word.trim().equalsIgnoreCase(sample.trim())) {
                count++;
            }
        }
        
        return count;
    }
    
    public static String[] splitWords(String text) {
        if (text == null || text.isEmpty() || text.matches("[,.:;!? ]+")) {
            return null;
        }
        
        String[] words = text.split("[,.:;!? ]+");
        return Arrays.stream(words)
                     .filter(word -> !word.isEmpty())
                     .toArray(String[]::new);
    }

    public static String convertPath(String path, boolean toWin) {
        if (path == null || path.isEmpty() || path.contains("/") && path.contains("\\") ||
        path.matches(".*//.*") ||                      // Unix path with double slash
        //path.matches(".*\\\\\\\\.*") ||                // Windows path with double backslash
        (path.contains("~") && !path.startsWith("~")) || // '~' not at the beginning
        path.matches(".*[^~]/~.*") ||                  // Mixed ~ with /
        path.matches(".*~.*~.*") ||                    // More than one ~
        path.matches(".*[^C:]C:.*") ||                 // Mixed C: with /
        path.matches(".*\\*/.*")                  // Illegal characters in Unix path
        // ||path.matches(".*\\\\[^\\\\].*")               // Illegal characters in Windows path
        || path.contains("~\\")
        ){           
        return null;
    }
    
        // Convert Unix path to Windows path
        if (toWin) {
            if (path.equals("~")) {
                return "C:\\User";
            } else if (path.startsWith("~/")) {
                return "C:\\User\\" + path.substring(2).replace("/", "\\");
            } else if (path.startsWith("/")) {
                return "C:\\" + path.substring(1).replace("/", "\\");
            } else {
                return path.replace("/", "\\");
            }
        } else {
            // Convert Windows path to Unix path
            if (path.equals("C:\\User")) {
                return "~";
            } else if (path.startsWith("C:\\User\\")) {
                return "~/" + path.substring(8).replace("\\", "/");
            } else if (path.startsWith("C:\\")) {
                return "/" + path.substring(3).replace("\\", "/");
            } else {
                return path.replace("\\", "/");
            }
        }
    }
    
    
   
    public static String joinWords(String[] words) {
        if (words == null || words.length == 0 || allEmpty(words)) {
            return null;
        }
        
        StringBuilder result = new StringBuilder("[");
        boolean first = true;
        
        for (String word : words) {
            if (!word.isEmpty()) {
                if (!first) {
                    result.append(", ");
                }
                result.append(word);
                first = false;
            }
        }
        
        result.append("]");
        return result.toString();
    }
    
    private static boolean allEmpty(String[] words) {
        for (String word : words) {
            if (!word.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words1 = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS"};
        String sample1 = "words   ";
        int countResult1 = countEqualIgnoreCaseAndSpaces(words1, sample1);
        System.out.println("Result: " + countResult1);
        int expectedCount1 = 2;
        System.out.println("Must be: " + expectedCount1);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);

        System.out.println("Test 5: PERSONAL TEST");
        String unixPath2 = "~\\\\folder";
        String convertResult2 = convertPath(unixPath2, false);
        System.out.println("Result: " + convertResult2);
       
    }
}
