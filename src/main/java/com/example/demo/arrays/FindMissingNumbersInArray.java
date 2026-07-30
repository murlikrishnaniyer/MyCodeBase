package com.example.demo.arrays;

import java.util.Arrays;

public class FindMissingNumbersInArray {
    public static void main(String[] args) {
        int[] arr= new int[]{4, 3, 2, 7, 8, 2, 3, 1};

        for(int i=0;i<arr.length;i++){
            int index = Math.abs(arr[i]) - 1;

            if(arr[index]>0){
                //adding - to index valuess
                arr[index]= -arr[index];

            }
        }            System.out.println(Arrays.toString(arr));

        // find the positive nos from the indexes
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0){
                System.out.println(i+1);
            }
        }

    }
}
