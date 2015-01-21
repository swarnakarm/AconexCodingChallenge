package com.aconex.coding.challenge;

public class ResultFormatter {

	/**
	 * finds the first index position of Digit in word. 
	 * @param word
	 * @return
	 */
	private static int findIndexOfDigit(String word){
		for(int i=0;i<word.length();i++){
			final char c = word.charAt(i);
			if(c>='0' && c<='9'){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * The result word will be formatted as required.
	 * @param result
	 * @return
	 */
	public static String formatOutput(final String result){
		StringBuffer formattedResult = new StringBuffer();
		int digitPos = findIndexOfDigit(result);
		String remainingString =  result;
		while(digitPos != -1)
		{
			if(digitPos != -1){
				if(digitPos != 0){
					formattedResult.append(remainingString.substring(0,digitPos).concat("-")
							.concat(String.valueOf(remainingString.charAt(digitPos))).concat("-"));
				}else{
					formattedResult.append(String.valueOf(remainingString.charAt(digitPos)).concat("-"));
				}
			}
			remainingString = remainingString.substring(digitPos+1);
			digitPos = findIndexOfDigit(remainingString);
		}
		formattedResult.append(remainingString);
		if(formattedResult.toString().charAt(formattedResult.length()-1) == '-'){
			return formattedResult.toString().substring(0,formattedResult.length()-1);
		}
		return formattedResult.toString();
	}
	
	/**
	 * number is formatted by removing all punctuation or special characters.
	 * @param number
	 * @return
	 */
	public static String formatPhoneNumber(final String number){
		StringBuffer sbuf = new StringBuffer();
		for(int i=0;i<number.length();i++){
			final char c = number.charAt(i);
			if(c>='0'&&c<='9'){
				sbuf.append(c);
			}
		}
		return sbuf.toString();
	}
	
}
