package jdk18;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NonRepetable3Number {
	
	public static void main(String[] args) {
	
		List<Integer> abc= Arrays.asList(1,2,2,1,5,5,7,9,25,9);
		
		Set<Integer>uni=abc.stream().collect(Collectors.toSet());
		System.out.println("NonRepetable3Number.main()"+uni);
		
		System.out.println("NonRepetable3Number.main()"+uni.stream().skip(2).findFirst().get());
		
	}

}
