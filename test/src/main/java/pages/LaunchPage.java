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

public class LaunchPage extends Base{
	
	WebDriverWait wait = new WebDriverWait(driver, 90);
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Already a customer? Sign in']")
	private AndroidElement signInLink;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'New to Amazon.com? Create an account']")
	private AndroidElement createAccount;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'Skip sign in']")
	private AndroidElement skipSignIn;
		
	public LaunchPage() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Click on sign In Button
	 */
	public EnterEmailPage clickOnSignInLink() {
		wait.until(ExpectedConditions.visibilityOf(signInLink));
		signInLink.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on sign-in Link");
		return new EnterEmailPage();
	}
	
	/*
	 * Click on Skip sign in button
	 */
	public void clickOnSkipSignIn() {
		wait.until(ExpectedConditions.visibilityOf(skipSignIn));
		skipSignIn.click();	
	}
	 
	/*
	 * Click on create account button
	 */
	public void clickOnCreateAccount() {
		wait.until(ExpectedConditions.visibilityOf(createAccount));
		createAccount.click();	
	}

}
