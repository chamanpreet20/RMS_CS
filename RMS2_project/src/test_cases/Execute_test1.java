package test_cases;

	import java.util.Properties;

import operation.Read_object;
import operation.UI_operation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import FileIO.Read_excelfile;
	
	public class Execute_test1 {
	
	
			@Test
	    public void testLogin() throws Exception {
			//	Object[][] object2d = null;
				String filepath="C:\\Users\\chaman.preet\\Documents\\C data\\git\\RMS2_workspace\\RMS2_project";
				String fileName="RMS_testcases.xlsx";
		Read_excelfile file = new Read_excelfile();
		Read_object object = new Read_object();
		Properties allObjects = object.getobjectrepository();
		
	        // TODO Auto-generated method stub
	//	FirefoxProfile fp = new FirefoxProfile();
	//	fp.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		System.setProperty("webdriver.gecko.driver", "C://Users//chaman.preet//Downloads//geckodriver.exe");
	

	//Read keyword sheet
	Sheet sheet = file.readExcel(filepath,fileName, "Test_cases");
	//Find number of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 1; i < rowCount+1; i++) {
	        //Loop over all the rows
	        Row row = sheet.getRow(i);
	      //  System.out.println("Row count in case sheet " +row.getPhysicalNumberOfCells());
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
	        				if(row1.getCell(0).toString().length()!=0 && testcasename1.equalsIgnoreCase(testcasename2))
	        					{
	        					WebDriver webdriver = new FirefoxDriver();
	        					webdriver.manage().window().maximize();
	        					UI_operation operation = new UI_operation(webdriver);
	        				//	System.out.println(row.getRowNum());
	    						int x=row1.getRowNum()+1;
	    						System.out.println("Executing row " +x + " of test step sheet");
	    						System.out.println("Testcase name matches " +testcasename2);
	    			//			do{			
	    						while(sheet1.getRow(x).getCell(0).toString().isEmpty()||sheet1.getRow(x).getCell(0).getCellType()==Cell.CELL_TYPE_BLANK)
	    							//System.out.println("same test case");
	    						{		Row row3=sheet1.getRow(x);
	    						//	System.out.println(row3.getCell(1).toString());
	        			 // System.out.println(row3.getCell(0).toString()+"----"+ row3.getCell(2).toString()+"----"+
	        			       //     row3.getCell(3).toString()+"----"+ row3.getCell(4).toString());
	        			        //Call perform function to perform operation on UI
	        			         //   operation.perform(allObjects, row1.getCell(1).toString(), row1.getCell(2).toString(),
	        			        //        row1.getCell(3).toString(), row1.getCell(4).toString());
	        			//     for (int z = 0; z < row3.getLastCellNum(); z++) 
	 					//	{
	 							//Print excel data in console
	 						//	Cell cell=row3.getCell(z);
	 							//object2d=new Object[rowCount1+1][5];
	 							//System.out.println(row3.getCell(z).toString());
	 							//object2d[x][z] = cell.toString();
	 							//String c = cell.toString();
	 							//System.out.println("values are "  +object2d[x][z]);				
	 					//	}
	    							x++;
	 						System.out.println(row3.getCell(1).toString()+"----"+ row3.getCell(2).toString()+"----"+
	 	        			           row3.getCell(3).toString()+"----"+ row3.getCell(4).toString());
	 					//	Call perform function to perform operation on UI
       			            operation.perform(allObjects, row3.getCell(1).toString(), row3.getCell(2).toString(),
       			               row3.getCell(3).toString(), row3.getCell(4).toString());
	 						}
	    						
	    						//while(sheet.getRow(x).getCell(0).getStringCellValue().isEmpty() || sheet.getRow(x).getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || sheet.getRow(x).getCell(0).toString()==null);
	    					//	while(sheet1.getRow(x).getCell(0).toString().length()==0);
	    						//System.out.println(sheet1.getRow(x).getCell(0).toString());
	    						//System.out.println(sheet1.getRow(x).getCell(1).toString());
	    						System.out.println("value of x is " +x);
	        					}
	        		  else if(!(testcasename1.equalsIgnoreCase(testcasename2))&&row1.getCell(0).toString().length()!=0)
	        				  {
	      	            //Print the new testcase name when it started
	      	                System.out.println("New Testcase in teststep sheet-> "+row1.getCell(0).toString() +" Started");
	      	            }
	        		}	
	        		   }
	        	
	        	else if(row.getCell(2).toString().equals("N"))
	        	{
	        		System.out.println("Skip testcase");
	        		//throw new SkipException("test cases skipped ");
	        	}
	    
	        	}	          
	    }
	}
