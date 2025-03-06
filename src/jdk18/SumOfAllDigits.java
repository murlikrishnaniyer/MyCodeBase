package jdk18;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumOfAllDigits {

	public static void main(String[] args) {
		int i = 15623;
		
		int j= Stream.of(String.valueOf(i).split("")).collect(Collectors.summingInt(Integer::parseInt));
		System.out.println("SumOfAllDigits.main()"+j);

	}

}
