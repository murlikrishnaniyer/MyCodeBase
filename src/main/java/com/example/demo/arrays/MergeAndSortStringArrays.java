package com.example.demo.arrays;

import java.util.Arrays;
import java.util.stream.Stream;

public class MergeAndSortStringArrays {

    public static void main(String[] args) {

        String [] str={"hfj","hgg","abc","lkj"};
        String [] str1={"bnm","cvb","sdf","poi"};
        Stream<String> stream = Arrays.stream(str);
        Stream<String> stream1 = Arrays.stream(str1);

        String [] resultString=Stream.concat(stream,stream1).sorted().toArray(String[]::new);
        System.out.println("Array after sorting all : "+Arrays.toString(resultString));
    }
}
