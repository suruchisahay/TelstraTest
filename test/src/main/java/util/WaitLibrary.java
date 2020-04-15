package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.telstra.base.Base;

import io.appium.java_client.MobileElement;

public class WaitLibrary extends Base{
	
	static WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_TIME);
	
	public static void staticWait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void waitTillElementVisible(MobileElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waitTillElementVisible(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waitTillTextToBePresentInElement(MobileElement ele, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
	}
	
	public static void waitTillPresenceOfElementLocated(String locatorName, String locatorValue) {
		switch (locatorName) {
			case "class" :
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				break;
				
			case "id" :
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				break;
				
			default:
				System.out.println("locator type s not defined in the method");
				break;
			}
		
	}

}
