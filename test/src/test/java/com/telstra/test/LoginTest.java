package com.telstra.test;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telstra.base.Base;

import pages.HomePage;
import pages.LoginPage;

@Listeners(reports.TestListener.class)
public class LoginTest extends Base{

	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup() { 
		loginPage = new LoginPage();
	}
	
	@Test
	public void loginTest1() {
		loginPage.clickOnAlreadyCustomerSignInLink();
		Assert.assertTrue(loginPage.isEnterEmailTextBoxVisible(), "Enter email text box is not visible on screen");
		
		loginPage.enterEmailId(prop.getProperty("emailId"));
		loginPage.clickContinueButton();
		
		Assert.assertTrue(loginPage.isPasswordTextBoxVisible(), "Password Text Box is not visible");
		Assert.assertTrue(loginPage.validateExpectedEmailId(prop.getProperty("emailId")), "actual email id is not matching expected email id");
		loginPage.checkUncheckShowPasswordCheckBox("uncheck");
		loginPage.enterPassword(prop.getProperty("password"));
		
		homePage = loginPage.tapOnSignInButton();
		Assert.assertTrue(homePage.isHomePageVisible(), "Sign-in failed, Home Page is not visible"); // sign in is successful
	}
	
	@AfterMethod
	public void cleanUp() {
		//quit driver if needed
	}
}
