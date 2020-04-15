package pages;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import reports.ExtentTestManager;
import util.WaitLibrary;

public class CommonMenu extends Base{
	
	
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart_image")
	private AndroidElement shoppingCartIcon;
	
	public CommonMenu() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/*
	 * Click on shopping cart icon on right top corner of the screen
	 */
	public ProceedToCheckoutPage clickOnShoppingCart() {
		WaitLibrary.waitTillElementVisible(shoppingCartIcon);
		//click on shopping cart icon
		shoppingCartIcon.click();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on shopping cart icon");
		return new ProceedToCheckoutPage();
	}
	
	
	/*
	 * Click on left side menu
	 */
	public void clickMenu() {
		
	}
	
	
	
	

}
