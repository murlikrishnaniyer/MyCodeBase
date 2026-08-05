package com.example.demo.strings;

import java.util.Arrays;
import java.util.List;

public class CommonNumbersin2Arrays
{
    public static void main(String[] args)
    {
        int[] array1 = {1, 3, 5, 7,8};
        int[] array2 = {2, 4, 6, 8};

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> list2 = Arrays.asList(1,9,10,11,12,13,14,15,16,17);
        list1.stream().filter(list2::contains).forEach(System.out::println);
    }
}
