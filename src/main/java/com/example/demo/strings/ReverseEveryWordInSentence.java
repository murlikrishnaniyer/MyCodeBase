package com.example.demo.strings;

import java.util.Arrays;

import java.util.stream.Collectors;

public class ReverseEveryWordInSentence {
    public static void main(String[] args) {
        String s="Array after sorting all";
        String collect = Arrays.stream(s.split(" ")).map(x -> new StringBuffer(x).reverse()).collect(Collectors.joining(" "));
        System.out.println(collect);

    }
}
