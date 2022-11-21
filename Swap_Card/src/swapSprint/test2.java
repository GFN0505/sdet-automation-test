package swapSprint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTests;


	public class test2 extends BaseTests {
		
		public WebDriver driver; 
		
		@Parameters({"URL"})//Parameters defined in testng xml file
		@BeforeTest 
		public void initialActions(String urlname) 
		{

			WebDriverManager.chromedriver().setup();
			//paramterize to run in headless mode
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get(urlname);
	
					
		}
		
		@Test 
		public void testCase2() 
		{
			Actions a = new Actions(driver);
			WebElement move = driver.findElement(By.id("nav-hamburger-menu"));
			a.moveToElement(move).click().build().perform();
			
			driver.findElement(By.xpath("//div[text()= 'Arts & Crafts']")).click();
			driver.findElement(By.xpath("//a[text()= 'Beading & Jewelry Making']")).click();
			driver.findElement(By.xpath("//span[text()= 'Engraving Machines & Tools']")).click();
			
			//sort on the page from highest to lowest
			driver.findElement(By.xpath("//span[text()= 'Sort by:']")).click();
			driver.findElement(By.xpath("//a[@id= 's-result-sort-select_2']")).click();
			
			// get price elements
			List<WebElement> list_of_products = driver.findElements(By.xpath("//span[@class = 'a-size-base-plus a-color-base a-text-normal']"));
			List<WebElement> list_of_products_price = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
			
			//Use of HashMap to store Products and Their prices(after conversion to Integer)
			
			String product_price;
			int int_product_price;
			HashMap<Integer, WebElement> map_final_products = new HashMap<Integer,WebElement>();
			for(int i=0;i<list_of_products.size();i++) {
				WebElement product = list_of_products.get(i);
				product_price = list_of_products_price.get(i).getText();//Iterate and fetch product price
				product_price = product_price.replaceAll("[^0-9]", "");//Replace anything wil space other than numbers
				int_product_price = Integer.parseInt(product_price);//Convert to Integer
				map_final_products.put(int_product_price,product);//Add product and price in HashMap
			}
			
			//One way is to fetch all values of the hashMap and then save it in the ArrayList
			//Then using Collections class in java, sort it in reverse order to get prices from highest to lowest
			
			//Get all the keys from Hash Map
			Set<Integer> allkeys = map_final_products.keySet();
			ArrayList<Integer> array_list_values_product_prices = new ArrayList<Integer>(allkeys);
					
			//Get the third available product and open it
			int third_product = array_list_values_product_prices.get(2);
			map_final_products.get(third_product).click(); 
			
			//Get rating of product
			String ratingText = driver.findElement(By.xpath("(//span[@class= 'a-icon-alt'])[1]")).getText();// gets the full text or raring
			String ratingString = ratingText.split(" ")[0];//trims to the actual rating figure as a text
			int actualRating = Integer.parseInt(ratingString);//Convert rating string to an Integer
			
			//Check if rating is less tban or more than 4
			Assert.assertFalse(actualRating < 4); // To determine if the producting rating is 4 or greater
			
		}
		
		@AfterClass 
		public void closeOut() 
		{
			driver.quit();
		}
		
		

		
		
		
	}


