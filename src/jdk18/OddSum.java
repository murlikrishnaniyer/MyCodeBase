package jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class OddSum {

	public static void main(String[] args) {
		int[] arr= {123, 145, 1677, 156, 234, 345, 456, 567, 678, 189, 11, 234};
		
		
		Integer summation=Arrays.stream(arr)
		.filter(p->String.valueOf(p).startsWith("1"))
		.map(p->Integer.valueOf(p))
		.filter(p-> !(p%2 == 0)).sum();
		
		System.out.println("summation "+summation);
		

		
		String[] arrStr= {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
		
		
		Optional<String> longestString=Arrays.stream(arrStr)
		.max(Comparator.comparingInt(String::length));
		
		System.out.println("longestString "+longestString);
		
		
		Optional<String> longestString1=Arrays.stream(arrStr).reduce((a,b)->a.length()>b.length()?a:b);
		System.out.println("longestString1 "+longestString1);


	}

}

