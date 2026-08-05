package com.example.demo.strings;

import java.util.Arrays;
import java.util.Comparator;

public class SortStringInIncreasingOrder {
    public static void main(String[] args) {
    String s="The inner loop runs n times for every iteration of the outer loop";
    Arrays.stream(s.split(" ")).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
    }
}
