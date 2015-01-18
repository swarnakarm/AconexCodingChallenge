package com.aconex.coding.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phone {

	static Map<String,ArrayList<String>> numberEncodingMap = new HashMap<String,ArrayList<String>>();
	
	static {
		ArrayList<String> decimalEncoding = new ArrayList<String>();
		decimalEncoding.add("-");
		numberEncodingMap.put(".",decimalEncoding);
		ArrayList<String> digit2Encoding = new ArrayList<String>();
		digit2Encoding.add("A");
		digit2Encoding.add("B");
		digit2Encoding.add("C");
		numberEncodingMap.put("2",digit2Encoding);
		ArrayList<String> digit3Encoding = new ArrayList<String>();
		digit3Encoding.add("D");
		digit3Encoding.add("E");
		digit3Encoding.add("F");
		numberEncodingMap.put("3",digit3Encoding);
		ArrayList<String> digit4Encoding = new ArrayList<String>();
		digit4Encoding.add("G");
		digit4Encoding.add("H");
		digit4Encoding.add("I");
		numberEncodingMap.put("4",digit4Encoding);
		ArrayList<String> digit5Encoding = new ArrayList<String>();
		digit5Encoding.add("J");
		digit5Encoding.add("K");
		digit5Encoding.add("L");
		numberEncodingMap.put("5",digit5Encoding);
		ArrayList<String> digit6Encoding = new ArrayList<String>();
		digit6Encoding.add("M");
		digit6Encoding.add("N");
		digit6Encoding.add("O");
		numberEncodingMap.put("6",digit6Encoding);
		ArrayList<String> digit7Encoding = new ArrayList<String>();
		digit7Encoding.add("P");
		digit7Encoding.add("Q");
		digit7Encoding.add("R");
		digit7Encoding.add("S");
		numberEncodingMap.put("7",digit7Encoding);
		ArrayList<String> digit8Encoding = new ArrayList<String>();
		digit8Encoding.add("T");
		digit8Encoding.add("U");
		digit8Encoding.add("V");
		numberEncodingMap.put("8",digit8Encoding);
		ArrayList<String> digit9Encoding = new ArrayList<String>();
		digit2Encoding.add("W");
		digit2Encoding.add("X");
		digit2Encoding.add("Y");
		digit2Encoding.add("Z");
		numberEncodingMap.put("9",digit9Encoding);
	}
	
	
	private float number = 0.0f;
	
	public void initNumber(float number){
		this.number = number;
	}
	
	
	private int findLastDigitPosition(String convertedString){
		int pos = -1;
		char digit0 = '0';
		char digit9 = '9';
		for(int index = 0; index<convertedString.length();index++){
			char charAtPos = convertedString.charAt(index);
			if(charAtPos>=digit0 && charAtPos<=digit9){
				pos = index;
			}
		}
		return pos;
		
	}
	
	
	
	
	private Set<String> addToSet(Set<String> convertedSet,ArrayList<String> arr, String digit){
		Set<String> newConvertedSet = new HashSet<String>();
		for(String convertedStr : convertedSet){
			int lastDigitPos = findLastDigitPosition(convertedStr);
			String str = convertedStr.substring(lastDigitPos+1);
			for(String encodedStr : arr){
				String temp = str.concat(encodedStr);
				if(Dictionary.isStringPatternExistInDictionary(temp)){
					newConvertedSet.add(convertedStr.concat(encodedStr));
				}else{
					if(Dictionary.isStringExistInDictionary(str)){
						newConvertedSet.add(convertedStr.concat(digit));
					}
				}
			}
		}
		return newConvertedSet;
	}
	
	
	
	public Set<String> convertToAlpha(){
		Set<String> convertedSet = new HashSet<String>();
		String numberInString = String.valueOf(number);
		for(int index = 0;index<numberInString.length();index++){
			char digit = numberInString.charAt(index);
			if(numberEncodingMap.containsKey(String.valueOf(digit))){
				ArrayList<String> arr = numberEncodingMap.get(String.valueOf(digit));
				convertedSet = addToSet(convertedSet,arr,String.valueOf(digit));
			}
		}
		return convertedSet;
	}
	
	
	
}
