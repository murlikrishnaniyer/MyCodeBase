package com.example.demo.arrays;

public class FirstAndLastPositionBinarySearchAlgo {

    public static void main(String[] args) {
        FirstAndLastPositionBinarySearchAlgo sol = new FirstAndLastPositionBinarySearchAlgo();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int[] bounds = sol.searchRange(nums, target);
        System.out.println("First and Last Position: [" + bounds[0] + ", " + bounds[1] + "]");
        // Output: [3, 4]
    }

    private int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findLast(int[] nums, int target) {

        int start = 0; int end = nums.length - 1;
        while (start <= end) {
            int mid=start+(end-start)/2;




            // right sorted

            if(nums[mid] <= target )
            {
                start =mid+1;
            }
            else
            {
                end =mid-1;
            }

            if(nums[mid] == target) {
                return mid;
            }

        }
        return -1;
    }

    private int findFirst(int[] nums, int target) {
        int lo=0;
        int hi=nums.length-1;
        while (lo<=hi) {
            int mid=lo+ (hi - lo)/2;


            //Left sorted
            if( nums[mid]>= target )
            {
                hi=mid-1;
            }
            else
            {
                lo=mid+1;
            }
            if(nums[mid]==target)
            {
                return mid;
            }
        }
        return -1;
    }


}
