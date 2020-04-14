package com.telstra.test;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telstra.base.Base;

import junit.framework.Assert;
import pages.EnterEmailPage;
import pages.HomePage;
import pages.LaunchPage;
import pages.LoginPage;

@Listeners(reports.TestListener.class)
public class LoginTest extends Base{

	LaunchPage launchPage ;//= new LaunchPage();
	EnterEmailPage email;
	LoginPage loginPage;
	HomePage homePage;
	//WaitLibrary waitLibrary = new WaitLibrary();
	
	@BeforeMethod
	public void setup() { //Method method
		launchPage = new LaunchPage();
	}
	
	@Test
	public void loginTest1() {
		email = launchPage.clickOnSignInLink();
		Assert.assertTrue("page is not visible", email.isPageVisible());
		//System.out.println(prop.getProperty("emailId"));
		email.enterEmailId(prop.getProperty("emailId"));
		loginPage = email.clickContinueButton();
		Assert.assertTrue("Login page is not visible", loginPage.isLoginPageVisible());
		Assert.assertTrue("email-id is not matching", loginPage.isEmailIdCorrect(prop.getProperty("emailId")));
		loginPage.checkUncheckShowPassword("uncheck");
		loginPage.enterPassword(prop.getProperty("password"));
		homePage = loginPage.clickSignInButton();
		Assert.assertTrue("Sign-in failed", homePage.isHomePageVisible()); // sign in is successful
	}
	
	@AfterMethod
	public void cleanUp() {
		//System.out.println("quitting driver");
	}
}
