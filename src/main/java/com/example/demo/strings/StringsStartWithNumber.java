package com.example.demo.strings;

import java.util.Arrays;
import java.util.List;

public class StringsStartWithNumber {
    public static void main(String[] args) {
        String str = "2abc";
        String str1 = "abc";
        List<String> list = List.of(str, str1);
        list.stream().filter(strr->Character.isDigit(strr.charAt(0))).forEach(System.out::println);
    }
}
