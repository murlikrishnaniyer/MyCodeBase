package com.example.demo.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeparateOddAndEvenNos {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = List.of(0, 0, 1, 1, 1, 2, 2, 3, 3, 4);
        Map<Boolean, List<Integer>> collect = listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(collect);
    }
}
