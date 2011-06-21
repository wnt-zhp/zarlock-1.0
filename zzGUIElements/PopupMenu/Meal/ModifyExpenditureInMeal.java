package zzGUIElements.PopupMenu.Meal;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.MealExpenditure;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;

public class ModifyExpenditureInMeal extends AbstractPopupMenuItem{

	public ModifyExpenditureInMeal(JTree tree) {
		super(tree);
	}

	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try {
			
			DefaultMutableTreeNode result = AbstractTreeElement.searchForNode(node,new com());
			TreeElement resultElement = (TreeElement) result.getUserObject();
			Mealv3 m = resultElement.getMeal();
			TreeElement element = (TreeElement) node.getUserObject();
			MealExpenditure me = (MealExpenditure) element.getExpenditure();
			
			String answer = (String) JOptionPane.showInputDialog(
					StaticContent.sc.getUppmostFrame(),
					"Tu mo¿esz zmodyfikowaæ tylko iloœæ - podaj j¹\n" +
					"By anulowac podaj iloœc ujemn¹, b¹dŸ cokolwiek np. Bzdzi¹gwa, czy cos",
					"Pytanie",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					null);
			
			float f = Float.parseFloat(answer);
			if(f<0) return;
			m.changeExpenditure(me.getId(),f);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B³¹d" + e.getMessage(),
					"B³¹d",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public Integer priority() {
		return 5;
	}

	public String toString(){
		return "Zmien";
	}
	
	public boolean userCanPerformAction() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private class com implements Comparable<TreeElement>{

		public int compareTo(TreeElement arg0) {
			if(arg0.representsMeal())	return 0;
			return 1;
		}
		
	}

	

}
