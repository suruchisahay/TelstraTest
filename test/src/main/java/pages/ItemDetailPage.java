package pages;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.CommonUtil;
import util.WaitLibrary;

public class ItemDetailPage extends Base{
	
	CommonUtil util = new CommonUtil();
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"See All Buying Options\")")
	private AndroidElement seeAllBuyingOptionButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Add to Cart\")")
	private AndroidElement addToCartButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"image-block-row\")")
	private AndroidElement header;
	
	public ItemDetailPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public boolean isItemDetailPageVisible() {
		try {
			WaitLibrary.staticWait(30000);
			System.out.println(header.isDisplayed());
			WaitLibrary.waitTillElementVisible(header);
			ExtentTestManager.getTest().log(Status.INFO, "Item detail page visible ");
			return header.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * click on see All Buying options to choose price from various vendors
	 */
	public ItemBuyingOptionPage clickSeeAllBuyingOptionButton() {
		try {
			WaitLibrary.waitTillElementVisible(seeAllBuyingOptionButton);
			seeAllBuyingOptionButton.click();
			ExtentTestManager.getTest().log(Status.INFO, "Clicked on See All Buying Option button");
			return new ItemBuyingOptionPage();
		}catch(Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "No seller is selling this item currently");
			return null;
		}
		
	}
	
	/*
	 * click on Add to cart
	 */
	public void clickAddToCart() {
		util.scroll("down");
		WaitLibrary.waitTillElementVisible(addToCartButton);
		addToCartButton.click();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on Add to Cart Button successfully");
		WaitLibrary.staticWait(5000);
		WaitLibrary.waitTillElementVisible(addToCartButton);
	}
	
	/*
	 * check if add to cart is visible , if not then check buy all options is visible and use it.
	 */
	public Object addItem() {
		//check if add to cart is visible , if not then click on buy all options.
		try {
			//add Item to cart by checking if add to cart button is visible
				clickAddToCart(); //add Item to cart
		
		}catch(Exception e) {
			//In catch and add item by clicking see all buying option
			ExtentTestManager.getTest().log(Status.INFO, "Add to cart is not displayed so checking see all buying option");
			System.out.println("add to cart is not displayed so checking see all buying option");
			return clickSeeAllBuyingOptionButton();
			
		}
		return null;
	}
}
