package com.telstra.base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import pages.CommonMenu;
import reports.ExtentManager;
import util.Constants;

public class Base {
	
	public Properties prop;
	public static AndroidDriver<AndroidElement> driver;
	DesiredCapabilities cap ;
	public static ExtentReports extent;
	public String testName;
	public FileInputStream fis;
	
	public Base() {
		/*
		 * initialize the property file located in src/test/resources/config.property
		 * property file contains the environment variable for test and other login details
		 */
		prop = new Properties();
		if (driver == null) {
			System.out.println("Inside base , driver is getting initialized");
			ClassLoader classLoader = Base.class.getClassLoader();
			File propFile = new File(classLoader.getResource("config.properties").getFile());
			File fileName = new File("src//test//resources");	
			
			try {
				fis = new FileInputStream(propFile);
				prop.load(fis);
				System.out.println("prop file initialized" + prop.getProperty("emailId"));
			}catch(Exception e) {
				System.out.println("file not present in path");
				e.printStackTrace();
			}
			
			/*
			 * Desired Capabilities are set to start the test and connect to the appium server.
			 */
			cap = new DesiredCapabilities();
			File apkName = new File(fileName, "Amazon_shopping.apk");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName")); //Simulator Name
			cap.setCapability(MobileCapabilityType.APP, apkName.getAbsolutePath()); //apk name to run the app
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty("automationName"));
			try {
				driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			/* implicit wait to wait for element to be visible  */
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICITTIME, TimeUnit.SECONDS);
			
		}
		
		
	}
	
	@BeforeSuite
	public void beforeSuit() {
		//initialize Extent Report
		//System.out.println("before suite");
		extent = ExtentManager.getInstance(System.getProperty("user.dir")+ "/", "ExtentReports");
	}
	
	public void setDriver(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	public AndroidDriver<AndroidElement> getDriver() {
		return this.driver;
	}
	
	public CommonMenu getMenu() {
		return new CommonMenu();
	}
	
	@AfterSuite
	public void cleanUp() {
		//extent.flush(); //flush report
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
/*	@DataProvider
	public Object[][] getData(Method method) {
		System.out.println("inside data provider of Test " + method);
		testName = method.getName();
		System.out.println(testName);
		Object[][] myData = data.getTestData(testName, xls);
		return myData;
	}*/
	
}
