package jdk18;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumOfFirst10Nos {

	public static void main(String[] args) {

		
		int sum=IntStream.rangeClosed(0, 10).sum();
		System.out.println("SumOfFirst10Nos.main()"+sum);
		
		
		
	}

}
