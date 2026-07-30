package com.example.demo.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class TwoSumExample {

    public static int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store the array value as key and its index as value
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if the required complement already exists in the map
            if (map.containsKey(complement)) {
                // If found, return the index of the complement and the current index
                return new int[] { map.get(complement), i };
            }

            // If not found, store the current number and its index in the map
            map.put(nums[i], i);
        }

        // Return an empty array if no solution is found (per requirements, a solution always exists)
        return new int[] {};
    }

    public static void main(String[] args) {
        // Test case
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        // Print the output indices
        System.out.println("Indices: " + Arrays.toString(result));
    }
}
