package zzTests;

import ZZLaunchers.MainFrameLoader;
import zzGUIElements.MainFrame;

public class MainFrameTests extends MainFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		zzTestMain.setUpDataBase(20);
		zzTestMain.setUpDays(10);
		new MainFrameLoader();
	}

}
