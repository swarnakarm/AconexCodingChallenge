package com.aconex.coding.challenge;
/**
 * PhoneDirectory keeps track of all the phone Number
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneDirectory {

	private String directoryPath = null;
	ArrayList<Phone> phoneList = null;
	public PhoneDirectory(){}
	
	
	public PhoneDirectory(String directoryPath){
		this.directoryPath = directoryPath;
		initializePhoneNumbers();
	}
	
	public void init(String directoryPath){
		this.directoryPath = directoryPath;
		initializePhoneNumbers();
	}
	
	/**
	 * initialize phoneList from PHONE_DIRECTORY_FILE_PATH
	 */
	public final void initializePhoneNumbers(){
		phoneList = new ArrayList<Phone>();
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
				phone.initNumber(ResultFormatter.formatPhoneNumber(line));
				phoneList.add(phone);
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
	 * Converts all the phone numbers to set of combination of dictionary word.
	 * @return
	 */
	public Map<String,Set<String>> readDirectoryAndCovertAllPhoneNumbertoAlpha(){
		Map<String,Set<String>> resultDir = new HashMap<String,Set<String>>();
		for(Phone phone:phoneList){
			Set<String> set = NumberToAlphaConverter.convertToAlpha(phone.getNumber());
			resultDir.put(phone.getNumber(), set);
		}
		return resultDir;
	}
	
	
}
