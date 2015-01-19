package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

//	private  static final String DICTIONARY_FILE_PATH = "DictionaryFilePath";
	
	
	public static void main(String args[]){
		String dictionaryPath = null;
		
		InputStreamReader isr = null;
		BufferedReader bfread = null;
		
		String phoneDirectoryPath = null;
		int dictionaryPathArgIndex = -1;
		for(int index=0;index<args.length;index++){
			String arg = args[index];
			if(arg.equals("-d")){
				try{
					dictionaryPath = args[index+1];
					dictionaryPathArgIndex = index;
					break;
				}catch(ArrayIndexOutOfBoundsException e){
					//Expected
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		if(dictionaryPath==null){
			System.out.println("Dcitionary Path Not Provided!!!");
			System.exit(1);
		}
		
		if(dictionaryPath!=null && args.length == 3){
			if(dictionaryPathArgIndex == 1){
				phoneDirectoryPath = args[0];
			}else{
				phoneDirectoryPath = args[2];
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
		Dictionary dictionary = Dictionary.getInstance();
		dictionary.init(dictionaryPath);
		PhoneDirectory dir = new PhoneDirectory();
		dir.init(phoneDirectoryPath);
		dir.readDirectoryAndCovertNumbertoAlpha();
	}
	
}
