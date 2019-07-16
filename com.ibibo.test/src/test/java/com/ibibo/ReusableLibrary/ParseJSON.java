package com.ibibo.ReusableLibrary;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseJSON {
	public static String scriptName=null;
	@DataProvider(name="testDataInventory",parallel = false)
	public Object[][] readJsonDataSet(){
		Object[][] dataCollection=null;
		try{

			Map<Integer, Map> dataSetsMapping = new HashMap<Integer, Map>();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = new JSONObject ();
			FileReader filereader = new FileReader(new File(ParseJSON.class.getClassLoader().getResource("TestDataInventory.json").getFile()));
			jsonObject = (JSONObject) jsonParser.parse(filereader);

			JSONArray jsonDataSetsArrayList = (JSONArray) jsonObject.get("dataSet");
			int inScopeCount = 0;
			int j=0;
			for (int i = 0; i < jsonDataSetsArrayList.size(); i++)
			{
				JSONObject jsonDataSet = (JSONObject) jsonDataSetsArrayList.get(i);
				boolean isInScope = (Boolean)jsonDataSet.get("inScope");
				String strDataSetScriptId = (String)jsonDataSet.get("scriptId");

				if(strDataSetScriptId.contentEquals(scriptName)&&(isInScope==true))
				{	

					Map<String,String> testDataTemp = new HashMap<String, String>();
					inScopeCount++;
					String browserName = (String)jsonDataSet.get("browserName");
					String browserVersion = (String)jsonDataSet.get("browserVersion");
					String osVersion = (String)jsonDataSet.get("osVersion");
					JSONObject jsonInputParams = (JSONObject)jsonDataSet.get("inputParameters");
					Set<Map.Entry<String, String>> keyValueSet = jsonInputParams.entrySet();

					String name = "";
					String value = "";

					for (Map.Entry<String, String> keyValue: keyValueSet)
					{
						name = keyValue.getKey();
						value = keyValue.getValue();
						testDataTemp.put(name, value);
					}

					testDataTemp.put("browserName", browserName);
					testDataTemp.put("browserVersion", browserVersion);
					testDataTemp.put("osVersion", osVersion);

					dataSetsMapping.put(inScopeCount, testDataTemp);
				}
			}

			System.out.println("inScopeCount = " + inScopeCount);

			dataCollection = new Object[inScopeCount][1];

			for (int i=1;i<=inScopeCount;i++)
			{
				Map<String,String> testData = new HashMap<String, String>();
				testData = dataSetsMapping.get(i);
				dataCollection[i-1][0] = testData;
			}

		}catch(Exception e){

		}
		return dataCollection;

	}
	
	
}
