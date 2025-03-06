package jdk18;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostRepeatedElement {

	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");

		Map<String, Long> mp=listOfStrings.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		Entry<String,Long> mostrep=mp.entrySet().stream().max(Map.Entry.comparingByValue()).get();
		System.out.println("MostRepeatedElement.main()"+mostrep.getKey()+" "+ mostrep.getValue());
		
	}

}
