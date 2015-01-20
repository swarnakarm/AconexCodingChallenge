package com.aconex.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(TestNumberToAlphaConverter.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	      
	      Result resultFormatter = JUnitCore.runClasses(TestResultFormatter.class);
	      for (Failure failure : resultFormatter.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(resultFormatter.wasSuccessful());
	   }
}
 