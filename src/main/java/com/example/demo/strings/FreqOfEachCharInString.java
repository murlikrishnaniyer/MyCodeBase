package com.example.demo.strings;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FreqOfEachCharInString {
    public static void main(String[] args) {
        String s= "abshfkjsfhksfchaggfqwebhxjcfqejd";
        Map<Character, Long> collect = s.chars().mapToObj(k -> (char) k).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }
}
