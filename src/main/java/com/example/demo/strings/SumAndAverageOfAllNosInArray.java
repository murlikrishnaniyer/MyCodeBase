package com.example.demo.strings;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class SumAndAverageOfAllNosInArray {
    public static void main(String[] args) {
        int[] arr= new int[]{4, 3, 2, 7, 8, 2, 3, 1};

        int sum = Arrays.stream(arr).sum();
        Double average = Arrays.stream(arr).average().getAsDouble();

        System.out.println(average+" "+ sum);
    }
}
