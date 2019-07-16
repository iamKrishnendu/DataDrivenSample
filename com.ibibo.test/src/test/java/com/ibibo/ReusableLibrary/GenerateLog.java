package com.ibibo.ReusableLibrary;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class GenerateLog {
	
	private String instanceID = "id_001";
	private Logger log = null;
	GlobalProperties properties = new GlobalProperties();
	private String testFolderPath =properties. readFile().getProperty(System.getProperty("user.dir")+"RESULT_FOLDER_PATH");
	public void setLogger(String testName,String testDescription)
	{
		try{
			instanceID = GenericUtilities.generateRandomNumber();
			Properties logProperties = new Properties();
			logProperties.put("log4j.logger." + testName, "DEBUG, " + instanceID );
			logProperties.put("log4j.appender." + instanceID, "org.apache.log4j.FileAppender");
			logProperties.put("log4j.appender." + instanceID + ".layout", "org.apache.log4j.PatternLayout");
			logProperties.put("log4j.appender." + instanceID + ".File", testFolderPath + "\\" + instanceID +".log");
			logProperties.put("log4j.appender." + instanceID + ".Append", "false");
			
			PropertyConfigurator.configure(logProperties);
			log = Logger.getLogger(instanceID);
			log.info("log");
		}catch(Exception e){
			
		}
		
	}
	public Logger getLog(){
		return log;
	}
	
	public static void main(String arg[]){
		GenerateLog	log = new GenerateLog();
		log.setLogger("test", "testDes");
		log.getLog().info("in logger");
		
	}
}
