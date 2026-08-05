package com.example.demo.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeAndSortIntArrays {
    public static void main(String[] args)
    {
        int[] array1 = {1, 3, 5, 7,8};
        int[] array2 = {2, 4, 6, 8};

        IntStream intStream=Arrays.stream(array1);
        IntStream intStream1=Arrays.stream(array2);
        int [] newArray=IntStream.concat(intStream1,intStream).sorted().distinct().toArray();
        System.out.println("Array after sorting all : "+Arrays.toString(newArray));
    }
}

