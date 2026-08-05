package com.example.demo.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddingAllElementsExceptSelf
{
    public static void main(String[] args)
    {
        int[] numbers = {10, 20, 30, 40, 50};
        int totalArrayLength = numbers.length;
        List<Integer> list = new ArrayList<Integer>();
        int j=0;
        for (int i = 0; i < numbers.length; i++)
        {
            System.out.println("Except index " + i + " (" + numbers[i] + "): [ ");

            for(j=0;j<numbers.length;j++)
            {
                if (i == j) {
                    continue;
                }
                System.out.print(numbers[j] + " ");
            }
        }
        System.out.println("]");

        for (int i = 0; i < numbers.length; i++) {
            final int targetIndex = i;
            System.out.print("Except index " + i + ": ");

            // Filter out the target index and print the remaining elements
            IntStream.range(0, numbers.length)
                    .filter(index -> index != targetIndex)
                    .map(index -> numbers[index])
                    .forEach(val -> System.out.print(val + " "));

            System.out.println();
    }



}

}
