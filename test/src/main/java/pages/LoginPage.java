package pages;

import org.openqa.selenium.WebElement;
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

public class LoginPage extends Base{
	
	WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_TIME);
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement passwordTextBox;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private AndroidElement checkBoxShowPassword;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Sign-In']")
	private AndroidElement signInButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@text = 'Sign-In']")
	private AndroidElement header;
	
	public LoginPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Checks if the login page is visible
	 */
	public boolean isLoginPageVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOf(header));
			ExtentTestManager.getTest().log(Status.PASS, "Login Page visible");
			return header.isDisplayed();
		}catch(Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Login Page is not visible");
			return false;
		}
		
	}
	
	/*
	 * Checks if the email id is as expected
	 */
	public boolean isEmailIdCorrect(String expectedEmailId) {
		//List<MobileElement> list1 = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(value)");
		if (driver.findElementByAndroidUIAutomator("text(\""+ expectedEmailId + "\")").isDisplayed())
			return true;
		else
			return false;
	}
	
	/*
	 * Enters the password
	 */
	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
		passwordTextBox.sendKeys(password);
		ExtentTestManager.getTest().log(Status.PASS, "Entered Password successfully");
	}
	
	/*
	 * Check / Uncheck the show password checkBox
	 */
	public void checkUncheckShowPassword(String expectedValue) {
		WebElement checkBox = (driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Show password\")");
		wait.until(ExpectedConditions.visibilityOf(checkBox));
		String actualChecked =  checkBox.getAttribute("checked");
		System.out.println(actualChecked);
		if (expectedValue.equalsIgnoreCase("uncheck")) {
			if (actualChecked.equalsIgnoreCase("true"))
				checkBox.click();
			else
				System.out.println("Check box already unchecked");
		} else if (expectedValue.equalsIgnoreCase("check")) {
			if (actualChecked.equalsIgnoreCase("true"))
				System.out.println("Check box already Checked");
			else
				checkBox.click();
		}
	}
	
	/*
	 * Click on Sign -In Button
	 */
	public HomePage clickSignInButton() {
		wait.until(ExpectedConditions.visibilityOf(signInButton));
		signInButton.click();
		ExtentTestManager.getTest().log(Status.PASS, "clicked on sign-in button");
		return new HomePage();
	}

}
