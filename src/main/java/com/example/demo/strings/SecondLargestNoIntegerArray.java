package com.example.demo.strings;

import java.util.List;

public class SecondLargestNoIntegerArray {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = List.of( 4, 3, 5, 7, 8, 2, 3, 1);
        listOfIntegers.stream().sorted().skip(1).findFirst().ifPresent(System.out::println);
    }
}
