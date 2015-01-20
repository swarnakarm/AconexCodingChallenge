package com.aconex.coding.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Manish S
 * This class NumberToAlphaConverter is used to convert the
 * phone number with the combination of multiple word available in dictionary.
 * The result for the number can be multiple set of words where each word can be comprised of multiple word from dictionary
 * e.g 6264743299 is the phone number provided and in Dictionary we have [MANISH, MAMISH, MAOISH, DAZZ]
 * result is MANISH-DAZZ, MAMISH-DAZZ, MAOISH-DAZZ
 */
public class NumberToAlphaConverter {

static Map<String,ArrayList<String>> numberEncodingMap = new HashMap<String,ArrayList<String>>();
	
	static {
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
	
	/**
	 * Method is used to find the last digit position from the converString.
	 * @param convertedString
	 * @return
	 */
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
	
	/**
	 * Method is used to convert alpha character to its corresponding digit.
	 * @param alpha
	 * @return
	 */
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
	
	/**
	 * Method is used to convert each character of the string at a time and check the prefix and postfix string 
	 * available in dictionary. If the prefix and postfix matches the string is 1 of the result word and will be added in Set.
	 * @param str
	 * @return
	 */
	private static Set<String> replaceWithDigitAndCheckInDictionary(String str){
		Set<String> newResultSet = new HashSet<String>();
		for(int i=0;i<str.length()-1;i++){
			if(i==0){
				String stringAfterFirstCharacterAsDigit = str.substring(i+1);
				if(Dictionary.isStringPatternExistInDictionary(stringAfterFirstCharacterAsDigit)){
					newResultSet.add(alphaToDigit(str.charAt(i)).concat(stringAfterFirstCharacterAsDigit));
				}
			}
			else{
				String stringBeforeDigit = str.substring(0,i);
				if(Dictionary.isStringExistInDictionary(stringBeforeDigit)){
					String stringAfterDigit = str.substring(i+1);
					if(Dictionary.isStringPatternExistInDictionary(stringAfterDigit)){
						newResultSet.add(stringBeforeDigit.concat(alphaToDigit(str.charAt(i))).concat(stringAfterDigit));
					}
				}
			}
		}
		return newResultSet;
	}
	
	
	/**
	 * Every encodedString from encodeStringArr is to be appended with resultWord available in resultSet.
	 * If the newEncodedString or its pattern matches with the words available in dictionary 
	 * then the newEncodedString is added to newResultSet which returned. 
	 * @param resultSet
	 * @param encodedStringArr
	 * @param digit
	 * @param isFirstIndex
	 * @return
	 */
	private static Set<String> getResultSetAfterAddingEncodedString(Set<String> resultSet,ArrayList<String> encodedStringArr, String digit,boolean isFirstIndex){
		Set<String> newResultSet = new HashSet<String>();
		if(resultSet.size()>0){
			for(String resultString : resultSet){
				final int lastDigitPos = findLastDigitPosition(resultString);
				String stringAfterLastDigit = resultString.substring(lastDigitPos+1);
				for(String encodedString : encodedStringArr){
					final String newResultString = stringAfterLastDigit.concat(encodedString);
					if(Dictionary.isStringPatternExistInDictionary(newResultString)){
						newResultSet.add(resultString.concat(encodedString));
					}
					else{
						if(Dictionary.isStringExistInDictionary(stringAfterLastDigit) && (lastDigitPos!=resultString.length()-1))
						{
							newResultSet.add(resultString.concat(digit));
						}
						Set<String> tempResultSet = replaceWithDigitAndCheckInDictionary(newResultString);
						if(tempResultSet.size()>0){
							for(String tempStr:tempResultSet){
								char firstLetter = tempStr.charAt(0);
								if(!(firstLetter >= '0' && firstLetter<='9') || lastDigitPos == -1){
									newResultSet.add(tempStr);
								}
							}
						}
					}
					if(lastDigitPos!=-1){
						ArrayList<String> lastDigitEncodedStringArr = numberEncodingMap.get(String.valueOf(resultString.charAt(lastDigitPos)));
						if(lastDigitEncodedStringArr!=null){
							for(String lastDigitEncodedString : lastDigitEncodedStringArr){
								String resultStringAfterReplacingLastDigitWithString = lastDigitEncodedString.concat(newResultString);
								if(Dictionary.isStringPatternExistInDictionary(resultStringAfterReplacingLastDigitWithString)){
									if(lastDigitPos != 0){
										newResultSet.add(resultString.substring(0,lastDigitPos).concat("-").concat(resultStringAfterReplacingLastDigitWithString));
									}else{
										newResultSet.add(resultStringAfterReplacingLastDigitWithString);
									}
								}
							}
						}
					}
				}
			}
		}else if(isFirstIndex){
			for(String encodedString : encodedStringArr){
				newResultSet.add(encodedString);
			}
		}
		return newResultSet;
	}
	
	/**
	 * Method is used to convert the Number with all possible combination of word available in dictionary.
	 * This is the actual method used to convert number to set of combination of available dictionary word.
	 * And is called for conversion from outside. 
	 * @param number
	 * @return
	 */
	public static Set<String> convertToAlpha(final String number){
		Set<String> resultSet = new HashSet<String>();
		boolean isFirst = true;
		for(int index = 0;index<number.length();index++){
			char digit = number.charAt(index);
			if(numberEncodingMap.containsKey(String.valueOf(digit)) || digit == '1'){
				if(digit == '1'){
					Set<String> newConvertedSet = new HashSet<String>();
					if(isFirst){
						newConvertedSet.add("1");
					}else{
						for(String convertedStr:resultSet){
							final int lastDigitPos = findLastDigitPosition(convertedStr);
							if(lastDigitPos != convertedStr.length()-1){
								String str = convertedStr.substring(lastDigitPos+1);
								if(Dictionary.isStringExistInDictionary(str)){
									newConvertedSet.add(convertedStr.concat("1"));
								}
							}
						}
					}
					resultSet.clear();
					for(String str : newConvertedSet){
						resultSet.add(str);
					}
				}else{
					ArrayList<String> encodedStringArr = numberEncodingMap.get(String.valueOf(digit));
					resultSet = getResultSetAfterAddingEncodedString(resultSet,encodedStringArr,String.valueOf(digit),isFirst);
				}
				if(resultSet.size()==0){
					break;
				}
				isFirst = false;
			}
		}
		return resultSet;
	}
	
}
