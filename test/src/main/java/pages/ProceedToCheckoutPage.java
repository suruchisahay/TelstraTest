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

public class ProceedToCheckoutPage extends Base{
	
	WebDriverWait wait = new WebDriverWait(driver, 90);
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Proceed to checkout\")")
	private AndroidElement proceedToCheckoutButton;
	
	@AndroidFindBy(xpath = "//android.widget.ListView/preceding-sibling::android.view.View[1]")
	private List<AndroidElement> checkoutItemList;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private List<AndroidElement> textBox;
	
	public ProceedToCheckoutPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/*
	 * Click on proceed to checkout button
	 */
	public Object clickOnProceedToCheckOutButon() {
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
		proceedToCheckoutButton.click();
		ExtentTestManager.getTest().log(Status.INFO, "CLicked on Proceed to Checkout button");
		if (textBox.size() == 0)
			return new SelectShippingAddressPage();
		else
			return new EnterShippingAddressPage();
	}
	
	/*
	 * Returns the actual Item name visible in CheckOut Page to verify it with the item name that was added
	 */
	public String confirmItemForCheckout() {
		System.out.println(checkoutItemList.size());
		String actualItemName = checkoutItemList.get(0).getAttribute("text").trim().substring(0, 20);
		System.out.println("actual name = " + actualItemName);
		return actualItemName;
	}
}
