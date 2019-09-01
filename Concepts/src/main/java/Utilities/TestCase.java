package Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase extends ConceptNew{
	
	String testName = this.getClass().getSimpleName();
	String sheetname="";
	@BeforeClass
	public void startUp(){
		System.out.println("Test Execution is Started");
		ConceptNew.testCaseName=testName;
	}

	@BeforeTest
	public void launchApp(){
		
		System.out.println("Launch browser");
	}
	
	@Test
	
	public void test(){
		sheetname="Employee";
		String name = getData("TestCase",sheetname);
		String Email = getData("Email",sheetname);
		
		System.out.println("Name:"+name);
		System.out.println("Email:"+Email);
	}
}
