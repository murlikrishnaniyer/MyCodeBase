package jdk18;

import java.util.stream.Stream;

public class FibonnaciSeries 
{
    public static void main(String[] args) 
    {
    	Stream.iterate(new int[] {0,1}, n-> new int[] {n[1],n[0]+n[1]}).limit(20).forEach(n->System.out.println(n[0]));
    	
    	int sum= Stream.iterate(new int[] {0,1}, n-> new int[] {n[1],n[0]+n[1]}).limit(20).map(n->n[0]).mapToInt(n->n).sum();
    			
    		System.out.println("sum"+sum);
    }
}
