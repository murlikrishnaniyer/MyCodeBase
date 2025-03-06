package jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FreqOfEachElementInList {
	public static void main(String[] args) 
	{
		List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
		Map<String,Long>stationCountMap=stationeryList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println("FreqOfEachElementInList.main()"+stationCountMap);
		List<String> ls=stationeryList.stream().filter(i->i.startsWith("P")).collect(Collectors.toList());
		System.out.println(""+ls);
		
		  // Creating a Stream of Strings 
        Stream<String> stream = Stream.of("GFG", "Geeks", 
                                          "for", "GeeksforGeeks"); 
  
        // Using forEach(Consumer action) to print 
        // Character at index 1 in reverse order 
        stream.sorted(Comparator.reverseOrder()) 
            .flatMap(str -> Stream.of(str.charAt(1))) 
            .forEach(System.out::println); 
        
        String[] arr = { "Geeks", "for", "Gfor" }; 
        
        // Given String 
        String str = "GeeksforGeeks"; 
  
        // Convert the Prefixes into Stream using Stream.of() 
        // and check if any prefix matches using Predicate 
        // str::startsWith 
        if (Stream.of(arr) 
                .anyMatch(str::startsWith)) 
            System.out.println("Given String "
                               + "starts with one of the prefixes."); 
        else
            System.out.println("Given String do not "
                               + "starts with one of the prefixes."); 
    
	}

}
