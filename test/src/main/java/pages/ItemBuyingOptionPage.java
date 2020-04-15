package pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.WaitLibrary;

public class ItemBuyingOptionPage extends Base{
	
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
		WaitLibrary.waitTillElementVisible(item);
		ExtentTestManager.getTest().log(Status.PASS, "Item detail page is visible for item " + itemName);
		return item.isDisplayed();
		
	}
	
	/*
	  * Clicks on the buy option displayed to buy from various seller
	  */
	public void buyOption(int optionNo) {
		System.out.println(buyingoptions.size());
		System.out.println("price list size" + priceList.size());
		buyingoptions.get(optionNo - 1).click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on item " + optionNo);
	}
	
	/*
	 * Clicks on the cheapest price option from different sellers
	 */
	public void buyCheapestOption() {
		try {
			String price = priceList.get(0).getAttribute("text").replaceAll("\\$", "").trim();
			System.out.println(price);
			int minValue = (int) Double.parseDouble(price);
			System.out.println(minValue);
			
			int minIndex = 0;
			for(int i = 0; i < priceList.size(); i++) {
				System.out.println("min value " + minValue );
				if(! (priceList.get(i).getAttribute("text").contains("shipping"))) { //excluding all the shipping price and comparing the price value.
					System.out.println(priceList.get(i).getAttribute("text").replaceAll("[^\\d\\ ]|\\.|\\.$", "").trim());
					int currentPrice = (int) Double.parseDouble(priceList.get(i).getAttribute("text").replaceAll("[^\\d\\ ]|\\.|\\.$", "").trim());
					System.out.println("current price" + currentPrice);
					if (currentPrice < minValue) {
						minValue = currentPrice;
						minIndex = i / 2;
					}
				}
			}
			System.out.println("cheapest option is " + minIndex);
			buyingoptions.get(minIndex).click();
			ExtentTestManager.getTest().log(Status.PASS, "Buying cheapest option " + minIndex);
		}catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Sellers are not available to sell the product");
		}
		
	}
	

}
