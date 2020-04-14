package pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.Constants;

public class ItemBuyingOptionPage extends Base{
	 
	WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_TIME);
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\")")
	private List<AndroidElement> buyingoptions;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"$\")")
	private List<AndroidElement> priceList;
	
	
	public ItemBuyingOptionPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	 /*
	  * Checks if the page is displayed
	  */
	 
	public boolean isPageVisible(String itemName) {
		AndroidElement item = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + itemName + "\")");
		wait.until(ExpectedConditions.visibilityOf(item));
		return item.isDisplayed();
		
	}
	
	/*
	  * Clicks on the buy option displayed to buy from various seller
	  */
	public void buyOption(int optionNo) {
		System.out.println(buyingoptions.size());
		System.out.println("price list size" + priceList.size());
		buyingoptions.get(optionNo - 1).click();
	}
	
	/*
	 * Clicks on the cheapest price option from different sellers
	 */
	public void buyCheapestOption() {
		String price = priceList.get(0).getAttribute("text").replaceAll("$", "");
		int minValue = Integer.parseInt(price);
		int minIndex = 0;
		for(int i = 0; i < priceList.size(); i++) {
			System.out.println("min value " + minValue );
			if(! (priceList.get(i).getAttribute("text").contains("shipping"))) { //excluding all the shipping price and comparing the price value.
				int currentPrice = Integer.parseInt(priceList.get(i).getAttribute("text").replaceAll("$", ""));
				if (currentPrice < minValue) {
					minValue = currentPrice;
					minIndex = i / 2;
				}
			}
		}
		System.out.println("cheapest option is " + minIndex);
		buyingoptions.get(minIndex).click();
		ExtentTestManager.getTest().log(Status.PASS, "Buying cheapest option " + minIndex);
	}
	

}
