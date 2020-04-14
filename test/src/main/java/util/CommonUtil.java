package util;


import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;

import com.aventstack.extentreports.Status;
import com.telstra.base.Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import reports.ExtentTestManager;

public class CommonUtil extends Base{
	
	public void scrollTillText(String classValue, String text) {
		try {
			System.out.println(text);
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"" + classValue + "\")).scrollIntoView(new UiSelector().textContains(\"" + text + "\"));");
			System.out.println("scrolling successful");
			ExtentTestManager.getTest().log(Status.PASS, "Scrolled succesfully to get itemName " + text);
		}catch(Exception e) {
			System.out.println("scrolling failed");
			ExtentTestManager.getTest().log(Status.FAIL, "Scrolled failed for itemName " + text);
			e.printStackTrace();
		}
			
	}
	
	public void scrollTillText(String text) {
		try {
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())" + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))");
			//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))").click(); //.click()
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void scrollUsingTouchActions_ByElements(MobileElement startElement, MobileElement endElement) {
		try {
			TouchAction actions = new TouchAction(driver);
			Point startPoint = startElement.getLocation();
			Point endPoint = endElement.getLocation();
			System.out.println(startPoint.getX() + " " +  startPoint.getY());
			System.out.println(endPoint.getX() + " " +  endPoint.getY());
			actions.press(PointOption.point(startPoint.getX(), startPoint.getY())).waitAction().moveTo(PointOption.point(endPoint.getX(), endPoint.getY())).release().perform();
			System.out.println("scrolling successfull");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void tap(MobileElement ele) {
		Point pt = ele.getLocation();
		new TouchAction(driver).tap(PointOption.point(pt.getX(), pt.getY())).release().perform();
	}
	
	public void tapOutsideElement(String direction , MobileElement ele) {
		try {
			Point pt = ele.getLocation();
			TouchAction action = new TouchAction(driver);
			switch(direction) {
			case "right":
				action.tap(PointOption.point(pt.getX() - 100, pt.getY())).release().perform();
				break;
			case "left":
				action.tap(PointOption.point(pt.getX() + 500, pt.getY())).release().perform();
				break;
			case "up":
				action.tap(PointOption.point(pt.getX(), pt.getY() - 100)).release().perform();
				break;
			case "down":
				action.tap(PointOption.point(pt.getX() - 100, pt.getY() + 500)).release().perform();
				break;
			default:
				System.out.println("option not available");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tapAtCoordinate(int x, int y) {
		try {
			new TouchAction(driver).tap(PointOption.point(x, y)).release().perform();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void horizontalScrollTillText(String className, String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"" + className + "\")).setAsHorizontalList().scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"))");
	}
	
	public void rotateScreen(String orientation) {
		try {
			if (orientation.equalsIgnoreCase("landscape"))
				driver.rotate(ScreenOrientation.LANDSCAPE);
			else if (orientation.equalsIgnoreCase("portrait"))
				driver.rotate(ScreenOrientation.PORTRAIT);
		}catch(Exception e) {
			System.out.println("screen rotation failed");
			e.printStackTrace();
		}
	}
	
	public void scroll(String direction) {
		try {
			Dimension size = driver.manage().window().getSize();
			switch(direction) {
			
				case "up" :
					int startY = (int) (size.height * 0.20);
					int endY = (int) (size.height * 0.80);
					int startX = size.width / 2;
					new TouchAction(driver).press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(PointOption.point(startX, endY)).release().perform();
					break;
				case "down" :
					startY = (int) (size.height * 0.80);
					endY = (int) (size.height * 0.20);
					startX = size.width / 2;
					new TouchAction(driver).press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(PointOption.point(startX, endY)).release().perform();
					break;
				default:
					System.out.println("option is not correct");
			}
			ExtentTestManager.getTest().log(Status.PASS, "Scrolled " + direction + " successfully");
		} catch(Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.INFO, "Scroll  " + direction + " failed");
		}
		
	}
	
	public void pressKey(String e) {
		e = e.toLowerCase();
		switch (e) {
		case "a":
			driver.pressKey(new KeyEvent(AndroidKey.A));
			break;
		case "b":
			driver.pressKey(new KeyEvent(AndroidKey.B));
			break;
		case "c":
			driver.pressKey(new KeyEvent(AndroidKey.C));
			break;
		case "d":
			driver.pressKey(new KeyEvent(AndroidKey.D));
			break;
		case "e":
			driver.pressKey(new KeyEvent(AndroidKey.E));
			break;
		case "f":
			driver.pressKey(new KeyEvent(AndroidKey.F));
			break;
		case "g":
			driver.pressKey(new KeyEvent(AndroidKey.G));
			break;
		case "h":
			driver.pressKey(new KeyEvent(AndroidKey.H));
			break;
		case "i":
			driver.pressKey(new KeyEvent(AndroidKey.I));
			break;
		case "j":
			driver.pressKey(new KeyEvent(AndroidKey.J));
			break;
		case "k":
			driver.pressKey(new KeyEvent(AndroidKey.K));
			break;
		case "l":
			driver.pressKey(new KeyEvent(AndroidKey.L));
			break;
		case "m":
			driver.pressKey(new KeyEvent(AndroidKey.M));
			break;
		case "n":
			driver.pressKey(new KeyEvent(AndroidKey.N));
			break;
		case "o":
			driver.pressKey(new KeyEvent(AndroidKey.O));
			break;
		case "p":
			driver.pressKey(new KeyEvent(AndroidKey.P));
			break;
		case "q":
			driver.pressKey(new KeyEvent(AndroidKey.Q));
			break;
		case "r":
			driver.pressKey(new KeyEvent(AndroidKey.R));
			break;
		case "s":
			driver.pressKey(new KeyEvent(AndroidKey.S));
			break;
		case "t":
			driver.pressKey(new KeyEvent(AndroidKey.T));
			break;
		case "u":
			driver.pressKey(new KeyEvent(AndroidKey.U));
			break;
		case "v":
			driver.pressKey(new KeyEvent(AndroidKey.V));
			break;
		case "w":
			driver.pressKey(new KeyEvent(AndroidKey.W));
			break;
		case "x":
			driver.pressKey(new KeyEvent(AndroidKey.X));
			break;
		case "y":
			driver.pressKey(new KeyEvent(AndroidKey.Y));
			break;
		case "z":
			driver.pressKey(new KeyEvent(AndroidKey.Z));
			break;
		case "back":
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			break;
		case "space":
			driver.pressKey(new KeyEvent(AndroidKey.SPACE));
			break;
		default:
			System.out.println("Not presnt in the case");
			
		}
	}
	
	/* IOS Methods as it does not operate with UiAutomator methods*/
	public void scrollFullScreen_ios(String direction) {
		try {
			 System.out.println("Inside Scroll " + direction + " function for full screen");
			 
			 JavascriptExecutor js = (JavascriptExecutor) driver; 
			 HashMap<String, String> scrollObject = new HashMap<String, String>(); 
			 scrollObject.put("direction", direction);
			 js.executeScript("mobile:scroll", scrollObject);
			// ExtentTestManager.getTest().log(Status.INFO, "scrolling successful");
		 }catch (Exception e) {
			 System.out.println("scrolling failed");
			 e.printStackTrace();
		}
	}

}
