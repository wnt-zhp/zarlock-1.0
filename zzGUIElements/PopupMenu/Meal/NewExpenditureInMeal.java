package zzGUIElements.PopupMenu.Meal;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.MealExpenditure;
import zzDataBase.Result;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoEx;
import zzGUIElements.BranchGenerator;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzGUIElements.MealElements.AddProductDialog;
import zzGUIElements.PopupMenu.PopupMenuGenerator;
import zzUser.User;

public class NewExpenditureInMeal extends AbstractPopupMenuItem {

	private static AddProductDialog dialog = new AddProductDialog(StaticContent.sc.getDataBase());
	
	public NewExpenditureInMeal(JTree tree) {
		super(tree);
	}

	public String toString(){
		return "Dodaj produkt";
	}

	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try{
			TreeElement te = (TreeElement) node.getUserObject();
			DefaultMutableTreeNode result;
			if(te.representsExpenditure()){				
				result  = AbstractEmptyTreeElement.searchForNode(node, new com());
			}else{
				if(te.representsMeal()){
					result = node;
				}else{
					throw new IllegalArgumentException();
				}
			}
			TreeElement ter = (TreeElement) result.getUserObject();
			Result r = dialog.display(StaticContent.sc.getUppmostFrame(),ter.getMeal().getDate());
			
			if(r==Result.OK){
				System.out.println("OK");
				MealExpenditure e = te.getMeal().addExpenditure(dialog.getSelectedProduct(),dialog.getResultQuantity());
				result.add(BranchGenerator.getExpenditureNode(e, e.getId(),te.getMeal(),getTree(),
						  PopupMenuGenerator.getExpenditureInMealMenu(getTree()),
						  PopupMenuGenerator.getIleInMealMenuItem(getTree()),
						  PopupMenuGenerator.getOtherItemsInMealMenuItem(),
						  PopupMenuGenerator.getOtherItemsInMealMenuItem())
					);
			}else{
				System.out.println("NIE-OK");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B³¹d, powa¿ny b³¹d\n" +
					e.getMessage() ,
					"B³¹d",
					JOptionPane.WARNING_MESSAGE,
					null);
		}
		
	}

	public Integer priority() {
		return 1;
	}

	public boolean userCanPerformAction() {
		return User.canAddExpenditureInMeal();
	}
	
	private class com implements Comparable<TreeElement>{

		public int compareTo(TreeElement arg0) {
			if(arg0.representsMeal()) return 0;
			return 1;
		}
		
	}

}
