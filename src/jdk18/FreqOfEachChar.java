package jdk18;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FreqOfEachChar {

	public static void main(String[] args) {
		
		String inputString = "Java Concept Of The Day";
		
		
		Map<Character,Long> count=inputString.chars().mapToObj(p-> (char)p).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		String inputStringgg = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
		System.out.println("FreqOfEachChar.main()"+count);
	}

}
