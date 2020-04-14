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
	
	@BeforeMethod
	public void setup() {
		homePage = new HomePage();
	}
	
	@Test
	public void checkoutItem() {
		Assert.assertTrue(homePage.isHomePageVisible());
		WaitLibrary.staticWait(3000);
		Assert.assertTrue(homePage.searchItem("65-Inch tv"));
		itemDetailPage = homePage.scrollItemListAndSelectItem("Toshiba");
		Assert.assertTrue(itemDetailPage.isItemDetailPageVisible());
		Object itemPage = itemDetailPage.addItemDynamically();
		
		if (itemPage instanceof ItemBuyingOptionPage) {
			itemBuyingOptionPage = (ItemBuyingOptionPage) itemPage;
			Assert.assertTrue(itemBuyingOptionPage.isPageVisible("Toshiba"));
			itemBuyingOptionPage.buyCheapestOption();
	
		}
		
		/*Checkout */
		proceedToCheckoutPage = getMenu().clickOnShoppingCart();
		Assert.assertEquals(proceedToCheckoutPage.confirmItemForCheckout(), homePage.getExpectedItemNameAddedToCart(), "Actual and Expected Item name are not same");
		ExtentTestManager.getTest().log(Status.PASS, "Item in Checkout page and item page are same " + homePage.getExpectedItemNameAddedToCart());
		Object addressObject = proceedToCheckoutPage.clickOnProceedToCheckOutButon();
		
		if (addressObject instanceof SelectShippingAddressPage) {
			selectShippingAddressPage = (SelectShippingAddressPage) addressObject;
			Assert.assertTrue(selectShippingAddressPage.isShippingPageVisible());
			Assert.assertTrue(selectShippingAddressPage.selectAddress("Pune"), "Address not available in address list");
			selectShippingAddressPage.clickDeliverToThisAddress();
		} else if (addressObject instanceof EnterShippingAddressPage) {
			WaitLibrary.staticWait(5000);
			enterShippingAddressPage = (EnterShippingAddressPage) addressObject;
			Assert.assertTrue(enterShippingAddressPage.isShippingPageVisible());
			enterShippingAddressPage.enterAddress("Pune", "Pune", "Maharashtra", 411027, 1234567890, "India");
			enterShippingAddressPage.clickContinueButton();
		}
		
	}

}
