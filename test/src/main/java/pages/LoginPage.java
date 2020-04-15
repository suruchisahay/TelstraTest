package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.CommonUtil;
import util.WaitLibrary;

public class LoginPage extends Base{
	
	CommonUtil util = new CommonUtil();
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Already a customer? Sign in']")
	private AndroidElement signInLink;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'New to Amazon.com? Create an account']")
	private AndroidElement createAccount;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Skip sign in']")
	private AndroidElement skipSignIn;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement passwordTextBox;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private AndroidElement checkBoxShowPassword;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Sign-In']")
	private AndroidElement signInButton;
	
	@AndroidFindBy(xpath = "//android.view.View[@text = 'Sign-In']")
	private AndroidElement SignInheader;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement emailTextBox;
	
	@AndroidFindBy(className = "android.widget.Button")
	private AndroidElement continueButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Welcome\")")
	private AndroidElement header;
	
	public LoginPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Click on sign In Button
	 */
	public void clickOnAlreadyCustomerSignInLink() {
		WaitLibrary.waitTillElementVisible(signInLink);
		signInLink.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on Already a customer? sign-in Button");
	}
	
	/*
	 * Click on Skip sign in button
	 */
	public void clickOnSkipSignIn() {
		WaitLibrary.waitTillElementVisible(skipSignIn);
		skipSignIn.click();	
	}
	 
	/*
	 * Click on create account button
	 */
	public void clickOnCreateAccount() {
		WaitLibrary.waitTillElementVisible(createAccount);
		createAccount.click();	
	}

	
	/*
	 * Checks if the login page is visible
	 */
	public boolean isPasswordTextBoxVisible() {
		try {
			WaitLibrary.waitTillElementVisible(passwordTextBox);
			ExtentTestManager.getTest().log(Status.PASS, "Password TextBox is visible");
			return passwordTextBox.isDisplayed();
		}catch(Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Password Text box is not visible");
			return false;
		}
		
	}
	
	/*
	 * Checks if the email id is as expected
	 */
	public boolean validateExpectedEmailId(String expectedEmailId) {
		if (driver.findElementByAndroidUIAutomator("text(\""+ expectedEmailId + "\")").isDisplayed())
			return true;
		else
			return false;
	}
	
	/*
	 * Enter the password
	 */
	public void enterPassword(String password) {
		WaitLibrary.waitTillElementVisible(passwordTextBox);
		passwordTextBox.sendKeys(password);
		ExtentTestManager.getTest().log(Status.PASS, "Entered Password successfully");
	}
	
	/*
	 * Check / Uncheck the show password checkBox
	 */
	public void checkUncheckShowPasswordCheckBox(String expectedValue) {
		ExtentTestManager.getTest().log(Status.INFO, "Clicking on checkbox to " + expectedValue);
		WebElement checkBox = (driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Show password\")");
		WaitLibrary.waitTillElementVisible(checkBox);
		String actualChecked =  checkBox.getAttribute("checked");
		System.out.println(actualChecked);
		if (expectedValue.equalsIgnoreCase("uncheck")) {
			if (actualChecked.equalsIgnoreCase("true")) {
				checkBox.click();
			}
			else
				ExtentTestManager.getTest().log(Status.INFO, "Check box already unchecked");
		} else if (expectedValue.equalsIgnoreCase("check")) {
			if (actualChecked.equalsIgnoreCase("true"))
				ExtentTestManager.getTest().log(Status.INFO, "Check box already Checked");
			else
				checkBox.click();
		}
	}
	
	/*
	 * Click on Sign -In Button
	 */
	public HomePage tapOnSignInButton() {
		WaitLibrary.waitTillElementVisible(signInButton);
		util.tap(signInButton);
		//signInButton.click();
		ExtentTestManager.getTest().log(Status.PASS, "clicked on sign-in button");
		return new HomePage();
	}
	
	/*
	 * Checks if the page is displayed in screen by verifying the header of the page
	 */
	public boolean isEnterEmailTextBoxVisible() {
		try {
			System.out.println(header.isDisplayed());
			WaitLibrary.waitTillElementVisible(header);
			ExtentTestManager.getTest().log(Status.INFO, "Enter Email Text box is visible" + header.isDisplayed());
			return header.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/*
	 * enter email id on the email id text box
	 */
	public void enterEmailId(String emailId) {
		WaitLibrary.waitTillElementVisible(emailTextBox);
		emailTextBox.sendKeys(emailId);
		WaitLibrary.waitTillTextToBePresentInElement(emailTextBox, emailId);
		ExtentTestManager.getTest().log(Status.INFO, "Entered Email id " + emailId);
	}
	
	
	/*
	 * Click on continue button
	 */
	public void clickContinueButton() {
		WaitLibrary.waitTillElementVisible(continueButton);
		continueButton.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on Continue Button");
	}

	

}
