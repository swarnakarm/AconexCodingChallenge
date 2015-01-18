package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

	private static Set<String> words = new HashSet<String>();
	
	
	private static Dictionary object = null;
	
	private Dictionary(){
		
	}

	
	public static Dictionary getInstance(){
		if(object!=null){
			object = new Dictionary();
		}
		return object;
	}
	
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
				words.add(line.toUpperCase());
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
	
	
	public static boolean isStringExistInDictionary(String str){
		boolean flag = false;
		if(words.contains(str)){
			flag = true;
		}
		return flag;
	}
	
	
	public static boolean isStringPatternExistInDictionary(String str){
		boolean flag = false;
		words.
		return flag;
	}
	
}
