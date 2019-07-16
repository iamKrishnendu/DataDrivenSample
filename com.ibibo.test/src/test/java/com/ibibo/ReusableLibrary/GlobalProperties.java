package com.ibibo.ReusableLibrary;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalProperties {
	
	public Properties readFile(){
		
		Properties prop = new Properties();
		try{
				String propertyFilePath = System.getProperty("user.dir")+"/testData/globalProperty.properties";
				FileInputStream fin = new FileInputStream(propertyFilePath);
				prop.load(fin);
		}catch(IOException e){
			
		}
		return prop;
	}

}
