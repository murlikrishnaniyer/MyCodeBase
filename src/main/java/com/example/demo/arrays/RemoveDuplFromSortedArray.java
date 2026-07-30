package com.example.demo.arrays;

import java.util.Arrays;
import java.util.stream.Stream;

public class RemoveDuplFromSortedArray {

    public static void main(String[] args) {

        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int[] distinctnums = Arrays.stream(nums).sorted().distinct().toArray();

        int paddedNums=nums.length-distinctnums.length;

        Stream<String> modArray = Arrays.stream(distinctnums).mapToObj(String::valueOf);

        Stream<String> paddingStream=Stream.generate(()->"_").limit(paddedNums);

        String [] result= Stream.concat(modArray,paddingStream).toArray(String[]::new);

        System.out.println("Array after padding all : "+Arrays.toString(result));


    }
}
