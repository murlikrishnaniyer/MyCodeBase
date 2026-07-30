package com.example.demo.arrays;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class BinarySearchUsingStreams {
    public static void main(String[] args) {
        int[] arr = {1,3,5,6,7,8,9,10};
        int target =8;
        OptionalInt first = IntStream.of(target).map(t -> Arrays.binarySearch(arr, t))
                .filter(t -> t >= 0)
                .findFirst();
        int value =-1;
        if (first.isPresent()) {
            value= first.getAsInt();
        }
        System.out.println(value);

    }
}
