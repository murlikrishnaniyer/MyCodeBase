package com.example.demo.strings;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumAllDigitsOfNumber {
    public static void main(String[] args) {
        int number=12345;
        IntSummaryStatistics collect = Stream.of(String.valueOf(number).split("")).collect(Collectors.summarizingInt(Integer::parseInt));
        System.out.println(collect);
    }
}
