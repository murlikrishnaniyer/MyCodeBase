package jdk18;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericClass {

	/**
	 * @param <T>
	 * @param args
	 */
	/**
	 * @param <T>
	 * @param args
	 */
	/**
	 * @param <T>
	 * @param args
	 */
	public static <T> void main(String[] args) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Set<Integer> set = new HashSet();
        
        //Duplicate Elements
        myList.stream().filter(p->!set.add(p)).forEach(System.out::println);
        
             
        //FindFirst
        myList.stream().findFirst().ifPresent(System.out::println);
        
        //Total No of Elements in the List
       long count= myList.stream().count();
       System.out.println("Total No of Elements in the List "+count);
       
       //MaxNo in List
       Integer maxNo=myList.stream().max(Comparator.naturalOrder()).get();
       System.out.println("MaxNo in List() "+maxNo);
       
       //Get the first Non Repetative Char from ther String.
       
       String input="Java articles are Awesome";
       
       Character result=input.chars()           // IntStream
               .mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i)))  // convert to lowercase & then to Character object Stream
               .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a LinkedHashMap with the count
               .entrySet().stream()                       // EntrySet stream
               .filter(entry -> entry.getValue() == 1L)   // extracts characters with a count of 1
               .map(entry -> entry.getKey())              // get the keys of EntrySet
               .findFirst().get();                        // get the first entry from the keys
       System.out.println("resultresultresult "+result);        
       
       String s = "AToday is the happiest day of my life";
       String longest=Arrays.stream(s.split(" ")).max(Comparator.comparingInt(String::length)).orElse(null);
       System.out.println("L"+longest);
       
       //Sort all the vlaues of Integers
       
       myList.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
       
       myList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
       
       
       List<Integer> list=myList.stream().collect(Collectors.toList());
       
       Set<Integer> hSet=new HashSet<Integer>();
       
       //myList.stream().collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.joining()));
       Set<Integer> setData = new HashSet<>();

       myList.stream().anyMatch(num->(!setData.add(num)));
       
       int arr[] = { 99, 55, 203, 99, 4, 91 };
       Arrays.sort(arr);
       Arrays.stream(arr).forEach(System.out::println);
       
      List<String> names=Arrays.asList("aa","aa","bb","bb","cc","dd");
       //String [] names= {"aa","bb","cc","dd"};
       names.stream().map(p-> p.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
       
		      
       
       
       //find only duplicate elements with its count from the String ArrayList
       
       
       
       String namesCount = names .stream() 
    		   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) 
    		   .entrySet()
    		   .stream()
    		   .sorted(Map.Entry.comparingByValue())
    		   .filter(entry->entry.getValue()>1L) .map(entry->entry.getKey())
    		   .findFirst()
    		   .get(); 
       System.out.println("namesCount "+namesCount);
       
       Map<String,Long>names1=names.stream()
       .filter(x->Collections.frequency(names, x)>1)
       .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
       System.out.println("names1 "+names1);
       
       
       
       // arrange the keys from hashmap in ascending order &  values in descending order
       Map<String,Long>names2=names.stream()
    		   .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
    		   .entrySet()
    		   .stream()
    		   .sorted(Map.Entry.comparingByKey())
    		   .filter(entry->entry.getValue()>1)
    		   .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    		   
    		   
    	       System.out.println("names2 "+names2);
    	       
    	       Map<String, Integer> unsortedMap = Map.of("f", 6, "c", 1, "b", 2, "e", 5, "d", 4);
    	       
    	       Map mp=unsortedMap.entrySet().stream()
    	    		   .sorted(Map.Entry.comparingByKey()) 
    	    		   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
    	    		            (newValue,oldValue) -> oldValue, LinkedHashMap::new));
    	       System.out.println("mp "+mp);
    	       
    	       Map mp1=unsortedMap.entrySet().stream()
    	    		   .sorted(Map.Entry.comparingByValue()) 
    	    		   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
    	    		            (newValue,oldValue) -> oldValue, LinkedHashMap::new));
    	       System.out.println("mp1 "+mp1);
    	       
    	       Map mp2=unsortedMap.entrySet().stream()
    	    		   .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
    	    		   //.thenComparing(Map.Entry.comparingByKey())
    	    		   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
    	    		            (newValue,oldValue) -> oldValue, LinkedHashMap::new));
    	       System.out.println("mp1 "+mp1);
    	       
    	       Map mp3 = unsortedMap.entrySet().stream()
    	               .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
    	               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
    	               (newValue,oldValue) -> oldValue, LinkedHashMap::new));
    	       System.out.println("mp3 "+mp3);
    	       
    	       // count the no of characters from a string
    	       
    	       String str = "string data to count each character";
    	       
    	       Map<String,Long> charCount=Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.counting()));
    	       System.out.println("charCount "+charCount);
    	       
    	       String nonRepeatedChar=Arrays.stream(str.split(""))
    	    		   .collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.counting()))
    	    		   .entrySet()
    	    		   .stream()
    	    		   .filter(entry->entry.getValue()==1L)
    	    		   .map(entry->entry.getKey())
    	    		   .findFirst()
    	    		   .get();
    	       
    	       Map mp4=Arrays.stream(str.split(""))
    	    		   .collect(Collectors.groupingBy(Function.identity(),HashMap::new,Collectors.counting()));
    	       System.out.println("mp4 "+mp4);
    	       
    	       System.out.println("nonRepeatedChar "+nonRepeatedChar);
    	       
    	       
    	       
    	       //map integers to their squares and print results
    	       
    	        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    	        
    	       Map<Integer,Integer> squares=numbers.stream()
    	    		   						.collect(Collectors.toMap(p->p,p->p*p));
    	       
    	       System.out.println("squares "+squares);
    	       
    	       
    	       //find and print the maximum value from a list
    	       
    	       System.out.println("Max Value "+numbers.stream().max(Comparator.naturalOrder()));
    	       
    	       
    	       //reduce a list of integers to their sum
    	       
    	       Optional<Integer> total=numbers.stream().reduce(Integer::sum);
    	       
    	       System.out.println("Sum Using Reduce method "+total);

    	       //group strings by their lengths and print the groups
    	       
    	        List<String> words = Arrays.asList("a", "bb", "ccc", "dd");
    	        Map<Integer,List<String>>grouped =words.stream().collect(Collectors.groupingBy(String::length));
     	       	System.out.println("grouped  "+grouped );
     	       	
     	       	//program to limit and skip elements in a list, then print.
     	       	System.out.println("limit&skip  "+numbers.stream().limit(4).skip(3).findFirst().get() );
     	       
     	       	
     	       // check if a list contains a specific element
     	       	System.out.println("Specific Element Check "+ numbers.stream().anyMatch(t -> t==7 ));
     	       
     	       	// average length of strings in a list of strings
     	       List<String> arrStr = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig", "grape");

     	       	//average length of strings in a list of strings
     			
     			OptionalDouble avgLength=arrStr.stream().mapToInt(String::length).average();
     	     
     	       	System.out.println("avgLength "+ avgLength);

     	   // to count the occurrences of a given character in a list of strings
     	       	
     	       	Long givenCharCount=arrStr.stream()
     	       			.flatMapToInt(String::chars)
     	       			.mapToObj(value ->(char)value)
     	       			.filter(c->c=='e')
     	       			.count();
     	       			System.out.println("givenCharCount "+ givenCharCount);
     	       			
     	   //check if all elements in a list are greater than a given value
     	       			
     	       		System.out.println("all numbers matching "+ numbers.stream()
     	       		.allMatch(p->p > -1));
     	       		
     	       		
     	   // avg no of even numbers
     	       		
     	       		
     	       	System.out.println("avg no of even numbers "+ numbers.stream().filter(p->p%2==0).mapToInt(n->n).average());
					
     	  //convert a list of integers to a comma-separated string
     	       System.out.println("list of integers to a comma-separated string  "+	numbers.stream().map(t->t.toString() )
     	       	.collect(Collectors.joining(",")));
     	       
     	       //program to find the sum of digits of a list of integers
     	      List<Integer> digitNumbers = List.of(123, 456, 789);
     	      
     	     int digitSum=digitNumbers.stream()
     	      .mapToInt(value -> String.valueOf(value).chars().map(Character::getNumericValue).sum())
     	      .sum();
     		
     	     System.out.println("digitSum "+ digitSum);
     	     
     	     //program to find the distinct characters in a list of strings 
     	    List<String> strings = List.of("apple", "banana", "cherry");
     	     Set<Character> distinct=strings.stream()
     	     .flatMapToInt(String::chars)
     	     .mapToObj(p->(char)p)
			 .collect(Collectors.toSet());
     	      
     	    System.out.println("distinct "+ distinct);
     	    
     	    //find all the numbers starting with 2 in given list
     	    
     	   List<Integer> numbers1 = Arrays.asList(223, 234, 145, 367, 289, 2001, 2289);
     	   List<Integer> numberWithTwoAsStart=numbers1.stream()
     	   .filter(number->String.valueOf(number).startsWith("2"))
     	   .collect(Collectors.toList());
     			  
    	    System.out.println("numberWithTwoAsStart "+ numberWithTwoAsStart);

     	   
     	   //check if given integer array contains duplicate or not. Return true if it contains duplicate character.
    	    boolean dupCheck=myList.stream().distinct().count()!=myList.size();
    	    System.out.println("Duplicate Numbers Check in a List "+dupCheck );
    	    	
    	    // sort given list of strings according to decreasing order of their length
    	    System.out.println("sort given list of strings according to decreasing order of their length "+arrStr.stream().sorted((o1, o2) -> Integer.compare(o2.length(), o1.length())).toList() ) ;

    	    
}

       
	

}
