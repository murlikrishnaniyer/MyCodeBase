package com.example.demo.arrays;

public class longestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        // Handle edge cases for empty or null array
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Initialize the prefix with the first string
        String prefix = strs[0];

        // Scan through the remaining strings in the array
        for (int i = 1; i < strs.length; i++) {
            // Shorten the prefix until it is found at index 0 of the current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // If the prefix becomes empty, there is no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] words1 = {"flower", "flow", "flight"};
        System.out.println("Prefix: " + longestCommonPrefix(words1)); // Output: "fl"

        String[] words2 = {"dog", "racecar", "car"};
        System.out.println("Prefix: " + longestCommonPrefix(words2)); // Output: ""
    }
}
