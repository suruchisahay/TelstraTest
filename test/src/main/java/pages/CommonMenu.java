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

public class CommonMenu extends Base{
	
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart_image")
	private AndroidElement shoppingCartIcon;
	
	public CommonMenu() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Click on left side menu
	 */
	public void clickMenu() {
		
	}
	
	
	/*
	 * Click on shopping cart icon on right top corner of the screen
	 */
	public ProceedToCheckoutPage clickOnShoppingCart() {
		wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon));
		//click on shopping cart icon
		shoppingCartIcon.click();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on shopping cart icon");
		return new ProceedToCheckoutPage();
	}
	

}
