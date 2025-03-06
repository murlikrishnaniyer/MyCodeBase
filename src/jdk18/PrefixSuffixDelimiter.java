package jdk18;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrefixSuffixDelimiter {

	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
		
		String joinedString=listOfStrings.stream().collect(Collectors.joining(",","[","]"));
		System.out.println("PrefixSuffixDelimiter.main()"+joinedString);

	}

}
