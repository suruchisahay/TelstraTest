package pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;

public class EnterEmailPage extends Base{
	
	WebDriverWait wait = new WebDriverWait(driver, 90);
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement emailTextBox;
	
	@AndroidFindBy(className = "android.widget.Button")
	private AndroidElement continueButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Welcome\")")
	private AndroidElement header;
	
	
	public EnterEmailPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	/*
	 * Checks if the page is displayed in screen by verifying the header of the page
	 */
	public boolean isPageVisible() {
		System.out.println(header.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(header));
		ExtentTestManager.getTest().log(Status.PASS, "Enter Email Page is visible" + header.isDisplayed());
		return header.isDisplayed();
	}
	
	
	/*
	 * enter email id on the email id text box
	 */
	public void enterEmailId(String emailId) {
		wait.until(ExpectedConditions.visibilityOf(emailTextBox));
		emailTextBox.sendKeys(emailId);
		wait.until(ExpectedConditions.textToBePresentInElement(emailTextBox, emailId));
		ExtentTestManager.getTest().log(Status.INFO, "Entered Email id " + emailId);
	}
	
	
	/*
	 * Click on continue button
	 */
	public LoginPage clickContinueButton() {
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		continueButton.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on Continue Button");
		return new LoginPage();
	}

}
