package zzGUIElements.PopupMenu.Day;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.MealElements.DayGeneratingDialog;

public class AddDay extends AbstractPopupMenuItem {
	
	public AddDay(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}

	static private DayGeneratingDialog dialog = new DayGeneratingDialog();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		dialog.display();
		
	}
	

	public Integer priority() {
		return -10;
	}

	public boolean userCanPerformAction() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String toString(){
		return "Dodaj Dzien";
	}

}
