package jdk18;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicateStrings {
	public static void main(String[] args) {
		
		List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
		listOfStrings.stream().distinct().collect(Collectors.toList()).forEach(p->System.out.println("RemoveDuplicateStrings.main()"+p));
		
		listOfStrings.stream().collect(Collectors.toSet()).forEach(p->System.out.println("RemoveDuplicateStrings222.main()"+p));

	}
}
