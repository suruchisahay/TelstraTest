package pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.CommonUtil;
import util.WaitLibrary;

public class HomePage extends Base{
	
	CommonUtil util = new CommonUtil();
	
	private String expectedItemName;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text = 'What are you looking for?']")
	private AndroidElement searchBox;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement search;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(text() = 'Results')]")
	private AndroidElement resultText;
	
	@AndroidFindBy(className = "android.webkit.WebView")
	private AndroidElement webView;
	
	@AndroidFindBy(id = "search")
	private List<AndroidElement> searchId;
	
	public HomePage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Checks if the home page is visible
	 */
	public boolean isHomePageVisible() {
		try {
			WaitLibrary.waitTillElementVisible(searchBox);
			ExtentTestManager.getTest().log(Status.PASS, "Login successful");
			return searchBox.isDisplayed();
		}catch(Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Login failed");
			return false;
		}
		
	}
	
	/* searches item by scrolling and checking for the required item */
	
	public boolean searchItem(String itemName) {
		/* Click on search and enter the itemName in the search box */
		WaitLibrary.waitTillElementVisible(search);
		ExtentTestManager.getTest().log(Status.INFO, "searching for item " + itemName);
		searchBox.click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText")));
		WaitLibrary.waitTillPresenceOfElementLocated("class", "android.widget.EditText");
		search.sendKeys(itemName);
		ExtentTestManager.getTest().log(Status.INFO, "Entered in search box item " + itemName);
		util.pressKey("search");
		//driver.pressKey(new KeyEvent(AndroidKey.SEARCH));
		getItemFromListById("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions", 1); //clicks on the 1st auto suggestion from the ajax suggestion in the search box
		WaitLibrary.staticWait(3000);
		searchBox.click();
		WaitLibrary.staticWait(3000);
		getItemFromListById("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions", 1);
		AndroidElement result = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"Results\")");
		WaitLibrary.waitTillElementVisible(result);
		ExtentTestManager.getTest().log(Status.PASS, "Search results are visible for item " + itemName);
		System.out.println(result.isDisplayed());
		return result.isDisplayed();
	}
	
	/* Generic function to get the item from the list by passing id and no */
	public void getItemFromListById(String id, int listNo) {
		List<AndroidElement> list = driver.findElementsById(id);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (i ==  listNo - 1) {
				list.get(i).click();
				System.out.println("clicked on item " + (i + 1));
				break;
			}
					
		}
	}
	
	/* scrolls in the home Page and clicks on the desired item*/
	
	public ItemDetailPage scrollTillItemAndClick(String itemName) {
		WaitLibrary.waitTillElementVisible(webView);
		util.scrollTillText("android.webkit.WebView", itemName);
		AndroidElement item = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + itemName + "\")");
		expectedItemName = item.getAttribute("text").trim();
		System.out.println("item name = " + expectedItemName);
		item.click();
		System.out.println("Clicked on item ");
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on item -  " + expectedItemName);
		return new ItemDetailPage();
	}
	
	/*Returns the expected item name that is added to the cart, this will be used later to compare the item name in check-out page */
	public String getExpectedItemNameAddedToCart() {
		expectedItemName = this.expectedItemName.substring(0, 20);
		return expectedItemName;
	}
	
	

}
