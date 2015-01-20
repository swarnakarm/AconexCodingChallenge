package com.aconex.coding.challenge;

public class ResultFormatter {

	
	private static int findIndexOfDigit(String word){
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(c>='0' && c<='9'){
				return i;
			}
		}
		return -1;
	}
	
	public static String formatOutput(final String result){
		StringBuffer formattedResult = new StringBuffer();
		int digitPos = findIndexOfDigit(result);
		String remainingResultString =  result;
		while(digitPos != -1)
		{
			if(digitPos != -1){
				if(digitPos != 0){
					formattedResult.append(remainingResultString.substring(0,digitPos).concat("-")
							.concat(String.valueOf(remainingResultString.charAt(digitPos))).concat("-"));
				}else{
					formattedResult.append(String.valueOf(remainingResultString.charAt(digitPos)).concat("-"));
				}
			}
			remainingResultString = remainingResultString.substring(digitPos+1);
			digitPos = findIndexOfDigit(remainingResultString);
		}
		formattedResult.append(remainingResultString);
		if(formattedResult.toString().charAt(formattedResult.length()-1) == '-'){
			return formattedResult.toString().substring(0,formattedResult.length()-1);
		}
		return formattedResult.toString();
	}
	
	
	public static String formatPhoneNumber(final String number){
		StringBuffer sbuf = new StringBuffer();
		for(int i=0;i<number.length();i++){
			char c = number.charAt(i);
			if(c>='0'&&c<='9'){
				sbuf.append(c);
			}
		}
		return sbuf.toString();
	}
	
}
