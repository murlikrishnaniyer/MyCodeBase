package com.example.demo.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveElementFromArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int [] distintNumbers=Arrays.stream(nums).sorted().filter(i->i!=2).toArray();
        int paddedNums=nums.length-distintNumbers.length;
        System.out.println(nums.length);
        //Arrays.stream(distintNumbers).forEach(System.out::println);

        Stream<String> modArray = Arrays.stream(distintNumbers).mapToObj(String::valueOf);
        Stream<String> limit = Stream.generate(() -> "_").limit(paddedNums);

        Stream.concat(modArray,limit).forEach(System.out::println);
    }
}
