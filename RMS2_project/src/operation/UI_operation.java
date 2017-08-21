package operation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import operation.GetObjectclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UI_operation extends GetObjectclass{
WebDriver driver;
public UI_operation(WebDriver driver)
{
	this.driver=driver;
}

public void perform(Properties p,String operation, String objectname, String objectType, String value) throws Exception
{
	switch(operation.toUpperCase())
	{
	case "GOTOURL":
		driver.get(p.getProperty(value));
		break;
	
	case "CLICK":
		driver.findElement(this.getObject(p, objectname, objectType)).click();
		break;
		
	case "SETTEXT":
		driver.findElement(this.getObject(p, objectname, objectType)).sendKeys(value);
	break;
	
	case "CLOSE":
	driver.close();
		break;

		//all types of wait
	case "IMPLICITWAIT":
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		break;
	case "EXPLICITWAIT":
		WebDriverWait wait= new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(p, objectname, objectType))));
		break;
	case "DRIVERWAIT":
		try
		{
			  long time = (long)Double.parseDouble(value);
		        Thread.sleep(time*1000L);}
		catch (NumberFormatException e){
		       System.out.println("not a number"); }
		break;		

	case "VERIFY":
		String element = driver.findElement(this.getObject(p, objectname, objectType)).getAttribute("innerHTML");
		System.out.println(element);
		try
		{
		Assert.assertEquals(element, value);
		System.out.println("data match, Proceed !!!");
		}
		catch(Exception e)
		{
			System.out.println("Data do not match and give exception- " +e.getMessage());
			//APP_LOGS.error("Unable to match data" +e.getMessage());
		}
		break;
		
		default:
			break;
	}
	
	}
}
