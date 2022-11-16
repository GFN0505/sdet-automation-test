package testComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	public WebDriver driver;
	
	
	
	public String getScreenShot(String testCaseName, WebDriver driver ) throws IOException //method for screenshot
	{
		
		 File source = (File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 File file =  new File(System.getProperty("user.dir") +"//screenshot//" + testCaseName + ".png");
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir") +"//screenshot//" + testCaseName + ".png";
	}
}
