package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Main class - starting point.
 * @author Manish S
 *
 */
public class Main {

	/**
	 * Reads dictionary path.
	 * Dictionary path to be provided as argument
	 * Main -d DICTION_FILE_PATH
	 * Its mandatory to provided dictionary path
	 * @param args
	 * @return
	 */
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
	
	/**
	 * Method reads the Phone Directory Path either from stdin or Command line argument.
	 * @param args
	 * @param dictionaryPath
	 * @return
	 */
	public static String readPhoneDirPath(String args[],String dictionaryPath){
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
	
	/**
	 * Starting Point - Initializes Dictionary and Phone Directory
	 * Runs the converter to convert phone number to corresponding 
	 * set of combination of multiple words available in dictionary.
	 * @param args
	 */
	public static void main(String args[]){
		String dictionaryPath = readDictionaryPath(args);
		if(dictionaryPath==null){
			System.out.println("Dcitionary Not Provided!!!");
			System.out.println("Usage -");
			System.out.println("Main -d DICTIONARY_FILE_PATH PHONE_DIRECTORY_FILE_PATH");
			System.exit(1);
		}
		
		String phoneDirectoryPath = readPhoneDirPath(args, dictionaryPath);
		
		Dictionary dictionary = Dictionary.getInstance();
		dictionary.init(dictionaryPath);
		PhoneDirectory dir = new PhoneDirectory();
		dir.init(phoneDirectoryPath);
		Map<String,Set<String>> resultDir = dir.readDirectoryAndCovertAllPhoneNumbertoAlpha();
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
