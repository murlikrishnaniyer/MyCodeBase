package com.example.demo.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ReverseString {
    public static void main(String[] args) {
    String s="the sky is blue";
    String[] words = s.split(" ");
    Collections.reverse(Arrays.asList(words));
    String reversed = Arrays.stream(words).collect(Collectors.joining(" "));
    System.out.println(reversed);
    }
}
