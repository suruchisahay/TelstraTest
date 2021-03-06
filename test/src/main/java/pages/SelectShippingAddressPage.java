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

public class SelectShippingAddressPage extends Base{
	CommonUtil util = new CommonUtil();
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"a-autoid-0-announce\")")
	private AndroidElement submitAddressBtn;
	
	@AndroidFindBy(className = "android.widget.RadioButton")
	private List<AndroidElement> addressRadioBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Select a shipping address\")")
	private AndroidElement header;
	
	public SelectShippingAddressPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Checks if the Select Shipping address page is visible
	 */
	public boolean isShippingPageVisible() {
		WaitLibrary.waitTillElementVisible(header);
		ExtentTestManager.getTest().log(Status.PASS, "Shipping page is visible");
		return header.isDisplayed();
	}
	
	/*
	 * Clicks on button "Deliver to this address"
	 */
	public void clickDeliverToThisAddress() {
		util.scrollTillText("android.webkit.WebView", "Deliver to this address");
		WaitLibrary.waitTillElementVisible(submitAddressBtn);
		submitAddressBtn.click();
		ExtentTestManager.getTest().log(Status.INFO, "CLicked on Deliver to this address button");
	} 
	
	/*
	 * Clicks on radio button with the passed address in parameter(city Name / locality should be passed)
	 */
	public boolean selectAddress(String address) {
		try {
			for (int i = 0; i < addressRadioBtn.size(); i++) {
				if (addressRadioBtn.get(i).getAttribute("text").contains(address)) {
					addressRadioBtn.get(i).click();
					ExtentTestManager.getTest().log(Status.INFO, "Selecting delivery address " + address);
					return true;
				}
			}
			return false;
		}catch(Exception e) {
			return false;
		}
		
	}
}
