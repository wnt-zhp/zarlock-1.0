package zzTests;

import zzDataBase.Day;
import zzDataBase.StaticContent;
import zzGUIElements.PopupMenu.Day.Print11ADialog;

public class print13Atests {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		zzTestMain.setUpDataBase(10);
		zzTestMain.setUpDays(1);
		Day d = StaticContent.sc.getDays().get(0);
		new Print11ADialog().display(d);

	}

}
