package com.aconex.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aconex.coding.challenge.ResultFormatter;

public class TestResultFormatter {

	@Test
	public void testWith2WordsAndNoNumber(){
		String result = ResultFormatter.formatOutput("MANSIH-DAZZ");
		assertEquals("MANSIH-DAZZ",result);
	}
	
	
	@Test
	public void testWithMultipleWordsAndSingleNumber(){
		String result = ResultFormatter.formatOutput("MANSIH3DAZZ");
		assertEquals("MANSIH-3-DAZZ",result);
	}
	
	@Test
	public void testWithMultipleWordsAndMultipleNumberInBetween(){
		String result = ResultFormatter.formatOutput("MANSIH3DAZZ2AA");
		assertEquals("MANSIH-3-DAZZ-2-AA",result);
	}
	
	
	@Test
	public void testWithMultipleWordsAndMultipleNumberAtEnds(){
		String result = ResultFormatter.formatOutput("3MANSIH-DAZZ-AA2");
		assertEquals("3-MANSIH-DAZZ-AA-2",result);
	}
	
	@Test
	public void testWithMultipleWordsAndMultipleNumber(){
		String result = ResultFormatter.formatOutput("3MANSIH6DAZZ-AA2");
		assertEquals("3-MANSIH-6-DAZZ-AA-2",result);
	}
	
	@Test
	public void testPhoneNumberFormat(){
		String result = ResultFormatter.formatPhoneNumber("798809%%$$87%66&&9");
		assertEquals("79880987669",result);
	}
	
}
