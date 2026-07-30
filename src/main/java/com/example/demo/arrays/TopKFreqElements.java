package com.example.demo.arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopKFreqElements {
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};
        int n = arr.length;
        int k=2;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }
            else
            {
                map.put(arr[i],1);
            }
        }
        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream().limit(k).collect(Collectors.toList());
        System.out.println(collect);
    }
}
