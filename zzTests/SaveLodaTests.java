package zzTests;

import javax.swing.JFileChooser;

import zzDataBase.LoadSaver;
import zzDataBase.StaticContent;
import zzEx.PrimaryError;

public class SaveLodaTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		zzTestMain.setUpDataBase(100);
		zzTestMain.setUpDays(10);
		JFileChooser fc = new JFileChooser ();
		int result = fc.showSaveDialog(null);
		if(!(result == JFileChooser.APPROVE_OPTION))
			throw new PrimaryError("OOO");
		System.out.println(StaticContent.sc.getDays().size());
		LoadSaver.save(StaticContent.sc,fc.getSelectedFile());

		
	}

}
