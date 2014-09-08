package com.example.testingfragment.staticdata;

import java.util.ArrayList;
import java.util.List;

public class StaticManager {
	private static List<String> listOfStrings = new ArrayList<String>();
	
	public static List<String> getStrings() {
		for(int i=0;i<20;i++) {
			listOfStrings.add("String " + (i+1));
		}
		return listOfStrings;
	}
}
