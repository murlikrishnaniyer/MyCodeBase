package com.example.demo.strings;

import java.util.stream.IntStream;

public class SumFirstNaturalNos {
    public static void main(String[] args) {
        System.out.println(IntStream.rangeClosed(1,11).sum());
    }
}
