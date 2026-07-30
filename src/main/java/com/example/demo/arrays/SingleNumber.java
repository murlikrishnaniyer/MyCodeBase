package com.example.demo.arrays;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {4,1,2,1,2};
        int n = arr.length;
        int target =4;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (freq.containsKey(arr[i])) {freq.put(arr[i], freq.get(arr[i]) + 1);}
            else {freq.put(arr[i], 1);}
        }
        for (int i = 0; i < freq.size(); i++) {
            if (freq.get(arr[i])==1) {
                System.out.println(arr[i]);

            }
        }

    }
}
