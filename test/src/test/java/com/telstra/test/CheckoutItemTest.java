package com.telstra.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import pages.EnterShippingAddressPage;
import pages.HomePage;
import pages.ItemBuyingOptionPage;
import pages.ItemDetailPage;
import pages.ProceedToCheckoutPage;
import pages.SelectShippingAddressPage;
import reports.ExtentTestManager;
import util.WaitLibrary;

@Listeners(reports.TestListener.class)
public class CheckoutItemTest extends Base{
	
	HomePage homePage;
	ItemDetailPage itemDetailPage;
	ItemBuyingOptionPage itemBuyingOptionPage;
	ProceedToCheckoutPage proceedToCheckoutPage;
	EnterShippingAddressPage enterShippingAddressPage;
	SelectShippingAddressPage selectShippingAddressPage;
	
	String item = "Sceptre 65";
	
	@BeforeMethod
	public void setup() {
		homePage = new HomePage();
	}
	
	@Test
	public void checkoutItem() {
		Assert.assertTrue(homePage.isHomePageVisible(), "Home Page is not visible");
		WaitLibrary.staticWait(3000);
		Assert.assertTrue(homePage.searchItem("65-Inch tv"), "Search result for item is not displayed");
		itemDetailPage = homePage.scrollTillItemAndClick(item);
		Assert.assertTrue(itemDetailPage.isItemDetailPageVisible(), "Item Page is not visible after clicking on the item");
		Object itemPage = itemDetailPage.addItem();
		
		if (itemPage instanceof ItemBuyingOptionPage) {
			itemBuyingOptionPage = (ItemBuyingOptionPage) itemPage;
			Assert.assertTrue(itemBuyingOptionPage.isPageVisible(item), "Item Buying options are not visible");
			itemBuyingOptionPage.buyOption(1);
		}
		
		/*Checkout */
		proceedToCheckoutPage = getMenu().clickOnShoppingCart();
		Assert.assertEquals(proceedToCheckoutPage.confirmItemForCheckout(), homePage.getExpectedItemNameAddedToCart(), "Actual and Expected Item name are not same");
		ExtentTestManager.getTest().log(Status.PASS, "Item in Checkout page and item page are same " + homePage.getExpectedItemNameAddedToCart());
		
		//Object as proceed to checkout can lead to either already entered address page or if no address is present then to enter shipping address page
		Object addressObject = proceedToCheckoutPage.clickOnProceedToCheckOutButon();
		
		if (addressObject instanceof SelectShippingAddressPage) {
			//select shipping address from list of address
			selectShippingAddressPage = (SelectShippingAddressPage) addressObject;
			Assert.assertTrue(selectShippingAddressPage.isShippingPageVisible(), "select shipping address page is not visible");
			Assert.assertTrue(selectShippingAddressPage.selectAddress("Pune"), "Address not available in address list");
			selectShippingAddressPage.clickDeliverToThisAddress();
		} else if (addressObject instanceof EnterShippingAddressPage) {
			//add new address
			WaitLibrary.staticWait(5000);
			enterShippingAddressPage = (EnterShippingAddressPage) addressObject;
			Assert.assertTrue(enterShippingAddressPage.isShippingPageVisible(), "Enter Shipping Address is not visible");
			enterShippingAddressPage.enterAddress("Pune", "Pune", "Maharashtra", 411027, 1234567890, "India");
			enterShippingAddressPage.clickContinueButton();
		}
		
	}

}
