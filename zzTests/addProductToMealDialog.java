package zzTests;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import zzDataBase.StaticContent;
import zzGUIElements.MealElements.AddProductDialog;
import zzGUIElements.PopupMenu.Meal.NewExpenditureInMeal;

public class addProductToMealDialog extends JFrame{

	
	
	public addProductToMealDialog() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public addProductToMealDialog(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public addProductToMealDialog(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public addProductToMealDialog(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		zzTestMain.setUpDataBase(100);
		AddProductDialog dialog = new AddProductDialog (StaticContent.sc.getDataBase());
		addProductToMealDialog test = new addProductToMealDialog();
		test.setVisible(true);
		dialog.display(test);
		System.out.println(dialog.getSelectedProduct() + ", " + dialog.getResultQuantity());
		System.exit(0);
	}

}
