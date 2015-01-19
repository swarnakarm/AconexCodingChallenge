package com.aconex.coding.challenge;

public class ResultFormatter {

	
	private static int findIndexOfDigit(String word){
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(c>='0'&&c<='9'){
				return i;
			}
		}
		return -1;
	}
	
	public static String format(final String resultWord){
		StringBuffer newResultString = new StringBuffer();
		int digitPos = findIndexOfDigit(resultWord);
		do{
			if(digitPos != -1){
				resultWord.substring(0,digitPos+1);
			}
			for(int i=0;i<resultWord.length();i++){
				
			}
		}while(digitPos != -1);
		return newResultString.toString();
	}
	
}
