package org.apache.hadoop.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ComputerQuickSort {
	public ArrayList<Map.Entry<String, Double>> QuickSort(Map<String,Double> oldMap){
	
		    ArrayList<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String,Double>>(oldMap.entrySet());  
	        Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {  
	  
	            @Override  
	            public int compare(Entry<java.lang.String, Double> arg0,  
	                    Entry<java.lang.String,Double> arg1) {  
	            	double result = arg1.getValue() - arg0.getValue();
	                if(result > 0){
	                	return 1;
	                }
	                else if(result == 0){
	                	return 0;
	                }
	                else
	                	return -1;
	            }  
	        });  
//	        Map<String,Double> newMap =  new HashMap<String,Double>(); 
//	        for (int i = 0; i < list.size(); i++) {  
//	            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
//	            System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
//	        }  
	        return list;  
	}
}
