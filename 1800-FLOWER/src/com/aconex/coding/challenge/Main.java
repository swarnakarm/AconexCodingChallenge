package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

//	private  static final String DICTIONARY_FILE_PATH = "DictionaryFilePath";
	
	public static String readDictionaryPath(String args[]){
		String dictionaryPath = null;
		for(int index=0;index<args.length;index++){
			String arg = args[index];
			if(arg.equals("-d")){
				try{
					dictionaryPath = args[index+1];
					break;
				}catch(ArrayIndexOutOfBoundsException e){
					//Expected
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return dictionaryPath;
	}
	
	
	public static String readPhonePath(String args[],String dictionaryPath){
		String phoneDirectoryPath = null;
		InputStreamReader isr = null;
		BufferedReader bfread = null;
		
		if(dictionaryPath!=null && args.length == 3){
			if(args[1].equals(dictionaryPath)){
				phoneDirectoryPath = args[2];
			}else{
				phoneDirectoryPath = args[0];
			}
		}else if(dictionaryPath!=null && args.length == 2){
			System.out.println("Phone Directory Path:");
			try{
				isr = new InputStreamReader(System.in);
				bfread = new BufferedReader(isr);
				phoneDirectoryPath = bfread.readLine();
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
				if(isr!=null){
					try{
						isr.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		return phoneDirectoryPath;
		
	}
	
	public static void main(String args[]){
		String dictionaryPath = readDictionaryPath(args);
		if(dictionaryPath==null){
			System.out.println("Dcitionary Not Provided!!!");
			System.out.println("Usage -");
			System.out.println("Main -d DICTIONARY_FILE_PATH PHONE_DIRECTORY_FILE_PATH");
			System.exit(1);
		}
		
		String phoneDirectoryPath = readPhonePath(args, dictionaryPath);
		
		Dictionary dictionary = Dictionary.getInstance();
		dictionary.init(dictionaryPath);
		PhoneDirectory dir = new PhoneDirectory();
		dir.init(phoneDirectoryPath);
		Map<String,Set<String>> resultDir = dir.readDirectoryAndCovertNumbertoAlpha();
		Set<Entry<String,Set<String>>> entrySet = resultDir.entrySet();
		for(Entry<String,Set<String>> e : entrySet){
			String phoneNumber = e.getKey();
			System.out.println("Phone Number :"+ResultFormatter.formatPhoneNumber(phoneNumber));
			Set<String> set = e.getValue();
			if(set.size()>0){
				System.out.println("Possible Word Conversion-");
				for(String alpha:set){
					System.out.println(ResultFormatter.formatOutput(alpha));
				}
			}else{
				System.out.println("No Possible Word Conversion!!!");
			}
		}
	}
	
}
