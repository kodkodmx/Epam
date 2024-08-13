package com.epam.rd.autotasks;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        // Map to store the frequency of each word
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Iterate through each line
        for (String line : lines) {
            // Split the line into words using a regex pattern that includes letters, digits, and marks
            String[] words = line.split("[^\\p{L}\\p{M}\\d]+");
            
            for (String word : words) {
                // Convert to lowercase to handle case insensitivity
                word = word.toLowerCase();
                
                // Filter words with fewer than 4 characters
                if (word.length() >= 4) {
                    // Increase the word count in the map
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }
        
        // Create a list of entries from the map for sorting
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCount.entrySet());
        
        // Sort by frequency (descending) and then by word (alphabetical order)
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int freqComparison = e2.getValue().compareTo(e1.getValue());
                if (freqComparison == 0) {
                    return e1.getKey().compareTo(e2.getKey());
                }
                return freqComparison;
            }
        });
        
        // Create a StringBuilder for the result
        StringBuilder result = new StringBuilder();
        
        // Append each word and its frequency to the result
        for (Map.Entry<String, Integer> entry : entryList) {
            if (entry.getValue() >= 10) {
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(entry.getKey()).append(" - ").append(entry.getValue());
            }
        }
        
        // Return the result as a string
        return result.toString();
    }
}
