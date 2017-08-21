package test_cases;

	import java.util.Properties;

import operation.Read_object;
import operation.UI_operation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import FileIO.Read_excelfile;
	
public class Execute_test2 {
	
	
			//@Test
	    public void testLogin() throws Exception {
				String filepath="C:\\Users\\chaman.preet\\Documents\\C data\\git\\RMS2_workspace\\RMS2_project";
				String fileName="RMS_testcases.xlsx";
		Read_excelfile file = new Read_excelfile();
		Read_object object = new Read_object();
		Properties allObjects = object.getobjectrepository();
		System.setProperty("webdriver.gecko.driver", "C://Users//chaman.preet//Downloads//geckodriver.exe");
	//Read keyword sheet
	Sheet sheet = file.readExcel(filepath,fileName, "Test_cases");
	//Find number of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 1; i < rowCount+1; i++) {
	        //Loop over all the rows
	        Row row = sheet.getRow(i);
	        System.out.println("Get runmode value " +row.getCell(2));
	        	if(row.getCell(2).toString().equals("Y"))
	        	{
	        		String testcasename1=row.getCell(0).toString();
	        		//System.out.println("testcase name in case sheet is " +testcasename1);
	        		Sheet sheet1=file.readExcel(filepath, fileName, "Test_steps");
	        		int rowCount1 = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
	        		   for (int a = 1; a < rowCount1; a++) {
	        			   Row row1 = sheet1.getRow(a);
	        			   String testcasename2=row1.getCell(0).toString();        			
	        				if(testcasename1.equalsIgnoreCase(testcasename2))
	        					{
	        					WebDriver webdriver = new FirefoxDriver();
	        					webdriver.manage().window().maximize();
	        					UI_operation operation = new UI_operation(webdriver);
	        				//	System.out.println(row.getRowNum());	
	    						int x=row1.getRowNum()+1;
	        					System.out.println("Executing row " +x + " of test_steps sheet");
	    						System.out.println("Testcase name matches-- " +testcasename2);
	    						for(x=row1.getRowNum()+1;x<=rowCount1;x++)
	    						{
	    							Row row3=sheet1.getRow(x);
	    							if(row3.getCell(0).toString().length()==0)
	    					//	do{
	    					//		Row row3=sheet1.getRow(x);
	    					//		x++;
	    							{
	 						System.out.println(row3.getCell(1).toString()+"----"+ row3.getCell(2).toString()+"----"+
	 	        			           row3.getCell(3).toString()+"----"+ row3.getCell(4).toString());
	 					//	Call perform function to perform operation on UI
       			            operation.perform(allObjects, row3.getCell(1).toString(), row3.getCell(2).toString(),
       			               row3.getCell(3).toString(), row3.getCell(4).toString());
	 						}
	    							else
	    								continue;
	    						}
	        					}
	    						
	 						//while(row.getCell(0).getStringCellValue().isEmpty() || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(0).toString()==null);
	 					//while(sheet1.getRow(x).getCell(0).getStringCellValue().isEmpty());
	    					//	System.out.println("value of x is " +x);
	        					
	        		  else{
	      	            //Print the new testcase name when it started
	      	                System.out.println("New Testcase in teststep sheet->"+row1.getCell(0).toString() +" Started");
	      	            }
	        		}	
	        		   }
	        	
	        	else if(row.getCell(2).toString().equals("N"))
	        	{
	        		System.out.println("Skip testcase");
	        	}
	    
	        	}	          
	    }
	}
