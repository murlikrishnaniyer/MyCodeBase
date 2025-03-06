package jdk18;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Palindrome {

	public static void main(String[] args) {
		String str = "ROTATOR";
		
		boolean flag= IntStream.range(0, str.length()/2).noneMatch(i->str.charAt(i)!=str.charAt(str.length()-i-1));
		
	}

}
