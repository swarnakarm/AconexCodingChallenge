package com.aconex.coding.challenge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberToAlphaConverter {

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
		digit9Encoding.add("W");
		digit9Encoding.add("X");
		digit9Encoding.add("Y");
		digit9Encoding.add("Z");
		numberEncodingMap.put("9",digit9Encoding);
	}
	
	
	private static int findLastDigitPosition(String convertedString){
		int pos = -1;
		char digit0 = '0';
		char digit9 = '9';
		for(int index = 0; index<convertedString.length();index++){
			char charAtPos = convertedString.charAt(index);
			if(charAtPos>=digit0 && charAtPos<=digit9){
				pos = index;
			}else if(charAtPos == '-'){
				pos = index;
			}
		}
		return pos;
		
	}
	
	private static String alphaToDigit(char alpha){
		if(alpha>='A'&&alpha<='C'){
			return "2";
		}else if(alpha>='D'&&alpha<='F'){
			return "3";
		}else if(alpha>='G'&&alpha<='I'){
			return "4";
		}else if(alpha>='J'&&alpha<='L'){
			return "5";
		}else if(alpha>='M'&&alpha<='O'){
			return "6";
		}else if(alpha>='P'&&alpha<='S'){
			return "7";
		}else if(alpha>='T'&&alpha<='V'){
			return "8";
		}else{
			return "9";
		}
	}
	
	
	private static Set<String> replaceStringWithDigit(String str){
		Set<String> newSet = new HashSet<String>();
		for(int i=0;i<str.length()-1;i++){
			if(i==0){
				String temp = str.substring(i+1);
				if(Dictionary.isStringPatternExistInDictionary(temp)){
					newSet.add(alphaToDigit(str.charAt(i))+temp);
				}
			}
			else{
				String temp = str.substring(0,i);
				if(Dictionary.isStringExistInDictionary(temp)){
					String temp2 = str.substring(i+1);
					if(Dictionary.isStringPatternExistInDictionary(temp2)){
						newSet.add(temp+alphaToDigit(str.charAt(i))+temp2);
					}
				}
			}
		}
		return newSet;
	}
	
	private static Set<String> addToSet(Set<String> convertedSet,ArrayList<String> arr, String digit,boolean isFirstIndex){
		Set<String> newConvertedSet = new HashSet<String>();
		if(digit.equals(".")){
			for(String encodedStr : convertedSet){
				int lastDigitPos = findLastDigitPosition(encodedStr);
				String str = encodedStr.substring(lastDigitPos+1);
				if(Dictionary.isStringExistInDictionary(str)){
					newConvertedSet.add(encodedStr.concat("-"));
				}
			}
		}
		else if(convertedSet.size()>0){
			for(String convertedStr : convertedSet){
				int lastDigitPos = findLastDigitPosition(convertedStr);
				String str = convertedStr.substring(lastDigitPos+1);
				for(String encodedStr : arr){
					String temp = str.concat(encodedStr);
					if(Dictionary.isStringPatternExistInDictionary(temp)){
						newConvertedSet.add(convertedStr.concat(encodedStr));
					}else{
						if(Dictionary.isStringExistInDictionary(str)&&lastDigitPos!=convertedStr.length()-1){
							newConvertedSet.add(convertedStr.concat(digit));
						}
						Set<String> tempSet = replaceStringWithDigit(temp);
						if(tempSet.size()>0){
							for(String tempStr:tempSet){
								char firstLetter = tempStr.charAt(0);
								if(!(firstLetter >= '0' && firstLetter<='9') || lastDigitPos == -1){
									if(lastDigitPos == -1){
										newConvertedSet.add(tempStr);
									}else{
										newConvertedSet.add(convertedStr.substring(0,lastDigitPos).concat(tempStr));
									}
								}
							}
						}
					}
				}
			}
		}else if(isFirstIndex){
			for(String encodedStr:arr){
				newConvertedSet.add(encodedStr);
			}
		}
		return newConvertedSet;
	}
	
	
	public static Set<String> convertToAlpha(Phone phone){
		Set<String> convertedSet = new HashSet<String>();
		try{ 
			DecimalFormat df = new DecimalFormat("#");
	        df.setMaximumFractionDigits(128);
			String numberInString = df.format(phone.getNumber());
			System.out.println("Number Sent For Conversion: "+numberInString);
			for(int index = 0;index<numberInString.length();index++){
				char digit = numberInString.charAt(index);
				if(numberEncodingMap.containsKey(String.valueOf(digit))){
					ArrayList<String> arr = numberEncodingMap.get(String.valueOf(digit));
					convertedSet = addToSet(convertedSet,arr,String.valueOf(digit),index==0);
					if(convertedSet.size()==0){
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return convertedSet;
	}
	
}
