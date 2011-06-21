package zzGUIElements.PopupMenu.Day;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzGUIElements.MealElements.MealGeneatingDialog;

public class NewMealInDay extends AbstractPopupMenuItem {

	public NewMealInDay(JTree tree) {
		super(tree);
	}

	private static MealGeneatingDialog dialog = new MealGeneatingDialog();	

	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try{
			TreeElement te = (TreeElement) node.getUserObject();
			DefaultMutableTreeNode result;
			if(te.RepresentsDay()){
				result = node;
			}else{
				result = AbstractTreeElement.searchForNode(node, new cmp());
			}
			TreeElement rTe = (TreeElement) result.getUserObject();
			int dialogResult = dialog.display(rTe.getDay().getDate());
			getTreeModel().nodeStructureChanged(result);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B³¹d, powa¿ny b³¹d\n" +
					e.getMessage() + "\n" + e.getClass().getName(),
					"B³¹d",
					JOptionPane.WARNING_MESSAGE,
					null);
		}

	}

	private class cmp implements Comparable<TreeElement>{

		public int compareTo(TreeElement arg0) {
			if(arg0.RepresentsDay()){
				return 0; 
			}
			return 1;
		}
		
	}
	public Integer priority() {
		return 5;
	}

	public boolean userCanPerformAction() {
		return true;
	}
	
	public String toString(){
		return "Nowy posilek";
	}

}
