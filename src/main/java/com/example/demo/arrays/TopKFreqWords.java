package com.example.demo.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFreqWords {
    public static void main(String[] args) {

        String [] str={"i","love","leetcode","i","love","coding"};
        Map<String, Long> collect = Arrays.stream(str).
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }
}
