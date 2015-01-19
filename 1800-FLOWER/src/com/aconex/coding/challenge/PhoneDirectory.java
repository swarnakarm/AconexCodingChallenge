package com.aconex.coding.challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;

public class PhoneDirectory {

	private String directoryPath = null;
	
	public PhoneDirectory(){
		
	}
	
	public PhoneDirectory(String directoryPath){
		this.directoryPath = directoryPath;
	}
	
	public void init(String directoryPath){
		this.directoryPath = directoryPath;
	}
	
	public void readDirectoryAndCovertNumbertoAlpha(){
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
					System.out.println("Phone Number: "+phone.getNumber());
					Set<String> set = NumberToAlphaConverter.convertToAlpha(phone.getNumber());
					if(set.size()>0){
						System.out.println("Possible Word Conversion-");
						for(String alpha:set){
							System.out.println(alpha);
						}
					}else{
						System.out.println("No Possible Word Conversion!!!");
					}
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
	}
	
	
}
