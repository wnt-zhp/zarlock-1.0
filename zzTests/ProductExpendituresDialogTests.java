package zzTests;

import javax.swing.JFrame;
import javax.swing.text.AbstractDocument.BranchElement;

import zzDataBase.StaticContent;
import zzGUIElements.BranchGenerator;
import zzGUIElements.ExpenditureGeneratingDialog;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzGUIElements.zzGuiProductElements.ProductExpendituresDialogv2;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;

public class ProductExpendituresDialogTests extends JFrame {

	public ProductExpendituresDialogTests() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		zzTestMain.setUpDataBase(10);
		ProductExpendituresDialogTests foo = new ProductExpendituresDialogTests();
		foo.setVisible(true);
		User.setPriveleges(Priveleges.ROOTUSER);		
		StaticContent.sc.setUppmostFrame(foo);
		Product p = StaticContent.sc.getDataBaseMutable().getSorted().get(1);
		MyDate date1 = new MyDate("01.07.2006");
		//System.out.print(date1);
		p.changeDateOfBooking(date1);
		p.changeExpiryDate(new MyDate("10.07.2006"));
		p.changeSQuantity(100f);
		p.performExpenditure(10, date1, "Ala");
		p.performExpenditure(10, new MyDate ("03.07.2006"), "Ma kota");
		p.performExpenditure(10, new MyDate("04.07.2006"), "A sierotka");
		p.performExpenditure(10, new MyDate("07.07.2006"), "Ma rysia");
		ProductExpendituresDialogv2 dialog = new ProductExpendituresDialogv2(foo);
		//foo.add(((TreeElement)BranchGenerator.expendituresBranch(p).getUserObject()).getAdditionalData());
		dialog.dispaly(p);
		
	}
	

}
