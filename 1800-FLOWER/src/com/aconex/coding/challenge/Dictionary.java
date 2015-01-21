package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
/**
 * Dictionary is used to store the words.
 * Kepth this class singleton as only 1 Dictionary to be used throughout application.
 * @author Manish S
 *
 */
public final class Dictionary {

	private static Set<String> words = new HashSet<String>();
	
	
	private static Dictionary object = null;
	
	private Dictionary(){
		
	}

	
	public static Dictionary getInstance(){
		if(object==null){
			object = new Dictionary();
		}
		return object;
	}
	
	/**
	 * Initializes all the word from dictionary.
	 * @param filePath
	 */
	public void init(String filePath){
		File file = null;
		FileReader fread = null;
		BufferedReader bfread = null;
		try{
			file = new File(filePath);
			fread = new FileReader(file);
			bfread = new BufferedReader(fread);
			String line = null;
			while((line = bfread.readLine())!=null){
				StringBuffer sbuf = new StringBuffer();
				String str = line.toUpperCase();
				for(int i=0;i<str.length();i++){
					char c = str.charAt(i);
					if(c >= 'A' && c <= 'Z'){
						sbuf.append(c);
					}
				}
				words.add(sbuf.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bfread!=null){
				try{
					bfread.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(fread!=null){
				try{
					fread.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/** 
	 * Check if the String exactly matches with one of the word available in dictionary.
	 * @param str
	 * @return
	 */
	public static boolean isStringExistInDictionary(String str){
		boolean flag = false;
		if(words.contains(str)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Check if the String pattern matches with one of the word available in dictionary.
	 * @param str
	 * @return
	 */
	public static boolean isStringPatternExistInDictionary(String str){
		boolean flag = false;
		for(String word:words){
			if(word.startsWith(str)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
}
