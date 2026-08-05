package com.example.demo.arrays;

class SearchInsertionPositionBinarySearchAlgo {

    static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Prevents integer overflow compared to (left + right) / 2
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,5,6};
        int key = 7;
        System.out.println(search(arr, key));
    }
}