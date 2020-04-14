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
import util.CommonUtil;
import util.WaitLibrary;

public class EnterShippingAddressPage extends Base{ 
	
	CommonUtil util = new CommonUtil();
	
	WebDriverWait wait = new WebDriverWait(driver, 90);
	
	@AndroidFindBy(className = "android.widget.EditText") //[@id = 'enterAddressAddressLine1']
	private List<AndroidElement> streetAddress;
	
	@AndroidFindBy(className = "android.widget.Spinner")
	private AndroidElement country;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Enter a shipping address\")")
	private AndroidElement header;
	
	@AndroidFindBy(className = "android.webkit.WebView")
	private AndroidElement webView;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Continue\")")
	private AndroidElement continueBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Deliver to this address\")")
	private AndroidElement submitAddressBtn;
	
	@AndroidFindBy(xpath = "//android.widget.ListView/parent::android.view.View[1]")
	private AndroidElement countryList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Close\")")
	private AndroidElement closeBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"address-ui-widgets-countryCode_101\")")
	private AndroidElement countryIndia;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"address-ui-widgets-countryCode_231\")")
	private AndroidElement countryUSA;
	
	public EnterShippingAddressPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	/*
	 * Checks if the page is displayed
	 */
	public boolean isShippingPageVisible() {
		wait.until(ExpectedConditions.visibilityOf(header));
		return header.isDisplayed();
	}
	
	
	/*
	 * Enters the address details on the address page
	 */
	public void enterAddress(String addressLine, String city, String state, int zipCode, int phone, String countryName) { 
		System.out.println(streetAddress.size());
		streetAddress.get(1).sendKeys(addressLine); //enters the address line 1
		streetAddress.get(3).sendKeys(city); //enters the city name
		streetAddress.get(4).sendKeys(state); //enter the state name
		streetAddress.get(5).sendKeys(zipCode + ""); //enter the zip code
		streetAddress.get(6).sendKeys(phone + ""); //enter the phone 
		country.click(); //click on country 
		wait.until(ExpectedConditions.visibilityOf(closeBtn)); //wait till the country dialogue is displayed
		selectCountry(countryName);
		wait.until(ExpectedConditions.visibilityOf(header)); //wait till country is selected and dialogue is closed
		ExtentTestManager.getTest().log(Status.INFO, "Entered address details for " + countryName);
	}
	
	
	/*
	 * Click on Continue button
	 */
	public void clickContinueButton() {
		util.scrollTillText("android.webkit.WebView", "Continue");
		//util.scrollBackward("Continue");
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
		System.out.println("clicked on continue button");
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on Continue button");
	}
	
	/*
	 * Click on Deliver to this address button
	 */
	public void clickDeliverToThisAddress() {
		util.scrollTillText("android.webkit.WebView", "Deliver to this address");
		wait.until(ExpectedConditions.visibilityOf(submitAddressBtn));
		submitAddressBtn.click();
	}
	
	/*
	 * scrolls and selects the country from the app dialogue
	 */
	public void selectCountry(String country) {
		System.out.println("select " + country);
		String firstLetter = Character.toString(country.charAt(0));
		util.pressKey(firstLetter);
		util.scrollTillText(country);
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + country + "\")").click();
		WaitLibrary.staticWait(200);
	}

}
