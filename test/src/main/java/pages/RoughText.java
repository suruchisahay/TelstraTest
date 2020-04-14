package pages;

public class RoughText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JavascriptExecutor js = (JavascriptExecutor) driver; 
		//driver.executeScript("mobile:performEditorAction",ImmutableMap.of("action","search"));
		//WaitLibrary.staticWait(60000);
		//List<MobileElement> scrollableList = driver.findElementsByAndroidUIAutomator("new UiSelector().scrollable(true)");
		//System.out.println(scrollableList.size());
	}
	
	public void selectItemFromList(int itemNo) {
		//System.out.println(searchId.size());
		//make list of item (with text $ in it) and get the text and then scroll till the itemNo and click on the item
		//List<AndroidElement> itemList = driver.findElementsByAndroidUIAutomator("new UiSelector().id(\"search\")");
		//List<AndroidElement> itemList = driver.findElementsById("search"); ////android.view.View[@, //android.view.View[contains(text(),'$')]
		/*System.out.println(itemList.size());
		String expectedItemText = itemList.get(itemNo - 1).getAttribute("text");
		System.out.println(itemList.get(itemNo - 1).getAttribute("text"));
		//scroll to the item with text
		util.scrollTillText("android.webkit.WebView", expectedItemText);
		itemList.get(itemNo - 1).click();*/
	}

	
	public void selectItemExceptFirstAndLastFromList() {
		
	}
}
