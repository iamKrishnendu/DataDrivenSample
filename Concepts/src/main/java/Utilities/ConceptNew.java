package Utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConceptNew {
	
	
	 public static String testCaseName="";
	private static	XSSFWorkbook workbook ;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static FileInputStream fin ;
 
	public static void loadExcel(String SheetName){
		try{
			fin	= new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx");
			workbook= new XSSFWorkbook(fin);
			sheet= workbook.getSheet(SheetName);
			fin.close();
		}catch(Exception e){

		}

	}
	
	
	@DataProvider(name="testData",parallel = false)
	public Object[][] dataProviderMethod(){
		
		String SheetName = "Controller";
		if(sheet == null){
			loadExcel(SheetName);
		}

		int rowCount = sheet.getLastRowNum();
		int colCount =  sheet.getRow(0).getLastCellNum();
		Object[][]obj = new Object[rowCount][1];

		for(int i=0;i<=rowCount;i++){
			Map<Object,Object>dataMap = new HashMap<Object,Object>();
			String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			if(testCaseName.equalsIgnoreCase("TC-023")){
				for(int j=0;j<colCount;j++){
					
					
					dataMap.put(sheet.getRow(0).getCell(j).getStringCellValue().trim(), sheet.getRow(i).getCell(j).getStringCellValue().trim());
		}
			
		
					obj[i][0]=dataMap;
				
			}
			
					
					
					
		}
		return obj;
		
	}
	//Working method
	public static Map<String,Map<String,String>>storedata(String sheetName){
		
	//[][] collection = null;
			loadExcel(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int colCount =  sheet.getRow(0).getLastCellNum();
		//Object[][]obj = new Object[rowCount][1];
		Map<String,String>dataMap = new HashMap<String,String>();
		Map<String, Map<String, String>>storeData = new HashMap<String, Map<String, String>>();
		for(int i=0;i<=rowCount;i++){
			
			String testName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			
			if(testName.equalsIgnoreCase(testCaseName)){
				for(int j=0;j<colCount;j++){
					
					dataMap.put(sheet.getRow(0).getCell(j).getStringCellValue().trim(), sheet.getRow(i).getCell(j).getStringCellValue().trim());
					}
			}
			
			
				
			storeData.put("Data", dataMap)	;
		}
		return storeData;
	}
	
	public static String getData(String key,String sheetName){
		Map<String,String>data = storedata(sheetName).get("Data");
		String value = data.get(key);
		return value;
	}
	
	@Test
		public void test(){

		
	System.out.println(getData("Name","Employee"));
		//System.out.println(Testdata.get("TestCase").toString());
//	Map<String,String>data = getdata("Employee").get("Data");
//		
//		System.out.println(data.get("Name").toString());
//		System.out.println(data.get("Email").toString());
//		System.out.println(data.get("Department").toString());
		
		
		
		
	}
	

}
