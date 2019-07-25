package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SRPElements {
	
	private static WebElement login = null;
	
	public static WebElement SignIn(WebDriver driver) {
		
		login = driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']"));
		return login;
		
	}
	
}
