package com.aconex.test;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.aconex.coding.challenge.Dictionary;
import com.aconex.coding.challenge.NumberToAlphaConverter;

public class TestNumberToAlphaConverter{

	Dictionary dictionary = Dictionary.getInstance();
	
	@Before
	public void init(){
		dictionary.init("dictionary"+File.separator+"dict2.txt");
	}
	
	/*Done Working*/
	@Test
	public void checkForSimpleConversionWithSingleResult(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("9333");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("ZEDD")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	

	/*Done Working*/
	@Test
	public void checkForSimpleConversionWithMultipleResult(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("626474");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("MANISH") && !str.equals("MAMISH") && !str.equals("MAOISH")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	
	/*Done Working*/
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWord(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("72##24@465673*(9");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("SACHIN-LOPEZ")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}

	
	/*Done Working */
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndSingleNumbersInBetween(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("7^^22446.35673%9");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("SACHIN3LOPEZ")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	
	/*Done Working */
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndMultipleNumbersInBetween(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("7224465.76626356739");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("SACHIN5SONAM3LOPEZ")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	
	/*Done Working*/
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndSingleNumbersAtLeftEnd(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("52!742.676626");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("5BRIAN-SONAM")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	/*Done Working*/
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndSingleNumbersAtRightEnd(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("2!7426.7662.69");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("BRIAN-SONAM9")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	/*Done Working*/
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndNumbersAtBothEnds(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("5!!27426.76626%9");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("5BRIAN-SONAM9")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	/*Done Working*/
	@Test
	public void testsSingleStringResultWithMultipleDictionaryWordAndNumbersAtEndsAndInBetween(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("52742.64.7662!!69567$39$$9");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("5BRIAN4SONAM9LOPEZ9")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	/*Done Working*/
	@Test
	public void testsResultWithMultipleDictionaryWordAndNumbersConsecutiveInResult(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("5274264.27662.69.5673!!99");
		boolean flag = true;
		if(result.size()>0){
			for(String str:result){
				if(str.equals("5BRIAN42SONAM9LOPEZ9")){
					flag = false;
				}
			}
		}
		
		if(flag){
			Set<String> result1 = NumberToAlphaConverter.convertToAlpha("5!!274264.2766269%%956739^9");
			if(result1.size()>0){
				for(String str:result){
					if(str.equals("5BRIAN2SONAM99LOPEZ9")){
						flag = false;
					}
				}
			}
		}
		assertFalse(!flag);
	}
	
	
	/**
	 * Done Working
	 * In Dictionary Added - NIS,HA,HHA,AN,NISHHAN
	 */
	@Test
	public void testPhoneNumberForAmbiguity(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("$$647%%44^^2!6");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("NIS4HA6") && !str.equals("NISHHAN") && !str.equals("NIS-HHA6")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	
	/**
	 * Done Working
	 * In Dictionary Added - SSW,SA
	 */
	@Test
	public void testPhoneNumberForAlphaToDigitConversionAmbiguity(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("6264747%%79");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("MANISH7SW") && !str.equals("MAMISH7SW") && !str.equals("MAOISH7SW")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
	
	/**
	 * Done Working
	 * In Dictionary Added - SSW,SA
	 */
	@Test
	public void testPhoneNumberWith1(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("62647417%%72");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				if(!str.equals("MANISH1SSA") && !str.equals("MAMISH1SSA") && !str.equals("MAOISH1SSA")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		if(!flag){
			Set<String> result1 = NumberToAlphaConverter.convertToAlpha("1$$647^4426162647417%%72#1");
			if(result1.size()>0){
				for(String str:result1){
					if(!str.equals("1NISHHAN1MANISH1SSA1") && !str.equals("1NISHHAN1MAMISH1SSA1") 
							&& !str.equals("1NISHHAN1MAOISH1SSA1")){
						flag = true;
					}
				}
			}else{
				flag = true;
			}
		}
		assertFalse(flag);
	}
	
	@Test
	public void testThreeWordsInOneResult(){
		Set<String> result = NumberToAlphaConverter.convertToAlpha("32999368");
		boolean flag = false;
		if(result.size()>0){
			for(String str:result){
				System.out.println(str);
				if(!str.equals("DAZZ-ZEN8") && !str.equals("DAZZ9ENT")){
					flag = true;
				}
			}
		}else{
			flag = true;
		}
		assertFalse(flag);
	}
	
}
