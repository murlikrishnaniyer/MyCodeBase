package com.example.demo.arrays;

import java.util.HashMap;

public class UniqueCharacterGeneral {
    public static void main(String[] args) {

        String s="leetcode";
        String a="loveleetcode";
        String b="aabb";
    int j=-1;
        HashMap<Character, Integer> countsMap = new HashMap<>();

        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            countsMap.put(c,countsMap.getOrDefault(c,0)+1);
        }
         for(int i=0;i<s.length();i++){
             if(countsMap.get(s.charAt(i))==1){
              j=i;

             }
         }
        System.out.println("value found"+j);
    }
}
