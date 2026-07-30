package com.example.demo.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveMaxOccuranceOfDucplicatesUsingHMaps {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 5};
        int n = arr.length;

        List<Integer> l = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (freq.containsKey(arr[i])) {
                freq.put(arr[i], freq.get(arr[i]) + 1);
            } else {
                freq.put(arr[i], 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (freq.get(arr[i]) >= 2) {
                // Making frequency to 1
                freq.put(arr[i], 1);
                l.add(arr[i]);
            } else if (freq.get(arr[i]) == 1) {
                // Making frequency to 0
                // and appending to list
                l.add(arr[i]);
                freq.put(arr[i], 0);
            }
        }

        // Printing the list
        for (int i : l) {
            System.out.print(i + " ");
        }
    }
}

