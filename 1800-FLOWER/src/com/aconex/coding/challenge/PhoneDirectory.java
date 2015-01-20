package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneDirectory {

	private String directoryPath = null;
	
	public PhoneDirectory(){}
	
	
	public PhoneDirectory(String directoryPath){
		this.directoryPath = directoryPath;
	}
	
	public void init(String directoryPath){
		this.directoryPath = directoryPath;
	}
	
	public Map<String,Set<String>> readDirectoryAndCovertNumbertoAlpha(){
		Map<String,Set<String>> resultDir = new HashMap<String,Set<String>>();
		File file = new File(directoryPath);
		FileReader fread = null;
		BufferedReader bfread = null;
		try{
			if(!file.exists()){
				System.err.print("Phone Directory File Not Found!!!");
				System.exit(1);
			}
			fread = new FileReader(file);
			bfread = new BufferedReader(fread);
			String line = null;
			while((line = bfread.readLine())!=null){
				Phone phone = new Phone();
				try{
					StringBuffer sbuf = new StringBuffer();
					String str = line.toUpperCase();
					for(int i=0;i<str.length();i++){
						char c = str.charAt(i);
						if(c >= '0' && c <= '9'){
							sbuf.append(c);
						}
					}
					phone.initNumber(sbuf.toString());
					Set<String> set = NumberToAlphaConverter.convertToAlpha(phone.getNumber());
					resultDir.put(phone.getNumber(), set);
				}catch(Exception e){
					//Expected
				}
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
		return resultDir;
	}
	
	
}
