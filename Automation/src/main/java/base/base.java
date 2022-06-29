package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class base {
	

public WebDriver driver;
public Properties prop;
public WebDriverWait wait;

public WebDriver initializeDriver() throws IOException
{
	
prop= new Properties();
FileInputStream fis=new FileInputStream("src/test/resources/config.properties");

prop.load(fis);
String browserName=prop.getProperty("browser");
System.out.println("Browser selected from config file is  "+browserName);

if(browserName.equals("chrome"))
{
	 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//execute in chrome
	 driver= new ChromeDriver();
    	
}


else if (browserName.equals("firefox"))
{
	// execute in firefox
	 driver= new FirefoxDriver();
	
}


else if (browserName.equals("IE"))
{
//	execute in  IE
}

//Maximize the browser window
driver.manage().window().maximize();

//setting implicit wait
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

return driver;

}

public void launchUrl() throws IOException{
	
	prop= new Properties();
	FileInputStream fis=new FileInputStream("src/test/resources/config.properties");

	prop.load(fis);
	String url=prop.getProperty("url");
	System.out.println("url selected from config file is  "+url);

	driver.get(url);
}

public void waitForVisbilityOfElement(WebElement element, String text) {
	wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.textToBePresentInElement(element , text));
	
	
}
}
