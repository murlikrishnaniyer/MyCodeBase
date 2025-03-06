package jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxMinNos {

	public static void main(String[] args) {
		List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
		int max = listOfIntegers.stream().max(Comparator.naturalOrder()).get();
		int min =listOfIntegers.stream().min(Comparator.naturalOrder()).get();
		System.out.println("MaxMinNos.main()"+ max +" "+ min );

	}

}
