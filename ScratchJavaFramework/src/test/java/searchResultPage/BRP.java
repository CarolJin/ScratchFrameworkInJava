package searchResultPage;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import elements.SRPElements;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BRP {
	private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        //https://github.com/bonigarcia/webdrivermanager
    //  WebDriverManager.chromedriver().version("2.36").setup();
    }

    @Before
    public void setupTest() { 
    	//driver = new ChromeDriver();
    	
    	//Start headless chrome
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1280,800");

		driver = new ChromeDriver(options);
    }

    @After
    public void teardown() {
        if (driver != null) {
        	driver.close();
         // driver.quit(); //Will quit all the browser pages
        }
    }
    
	@Test
	public void BrowerResultPage() throws InterruptedException, IOException {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
	/*	System.setProperty("webdriver.gecko.driver", projectPath + "/divers/geckodriver");
		
	  //WebDriver driver = new FirefoxDriver();
		WebDriver driver = new SafariDriver();
	*/
		driver.get("https://www.amazon.com/");
		Assert.assertTrue("The title of the window is incorrect.", driver.getTitle().equals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"));
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.MICROSECONDS);
		
		//Explicitly wait
		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement AccountMenu1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-line-2' and text()='Account & Lists']")));
		
		//Fluent wait
		@SuppressWarnings("deprecation")
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(21, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement AccountMenu2 = wait.until(new Function<WebDriver,WebElement> () {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//span[@class='nav-line-2' and text()='Account & Lists']"));
			
				if(element.isDisplayed()) System.out.println("The menu is displayed.");
				
				return element;
			}
		});
		
		
		Actions actions = new Actions(driver);
		WebElement AccountMenu = driver.findElement(By.xpath("//span[@class='nav-line-2' and text()='Account & Lists']"));
	//	WebElement signIn = driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']"));
		
		//Hover on the dropdown list
		actions.moveToElement(AccountMenu2).perform();
		SRPElements.SignIn(driver).click(); //Using Page object model
		
		Thread.sleep(3000);
		
		//Get screenshot
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(projectPath + "/output/newScreenshot"));
				
	}
}
