package util;

import com.telstra.base.Base;

public class WaitLibrary extends Base{
	
	public static void staticWait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
