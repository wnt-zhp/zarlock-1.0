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


public class RemoveMealInDay extends AbstractPopupMenuItem {

	public RemoveMealInDay(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}

	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try{
			TreeElement te = (TreeElement) node.getUserObject();
			DefaultMutableTreeNode result;
			if(!te.representsMeal()) throw new IllegalArgumentException();
			
			result = AbstractTreeElement.searchForNode(node, new cmp());

			TreeElement rTe = (TreeElement) result.getUserObject();
			String resultS = (String) JOptionPane.showInputDialog(
					StaticContent.sc.getUppmostFrame(),
					"Chcesz skasowa� POSI�EK!. Zak�adam �e do�c du�o roboty " +
					"w�o�y�e� w jego wykonanie. I pewnie nie chcesz przypadkowo go traci�" +
					"\n Co wi�cej na dzis jest tak �e wyj�cie w produktach nie s� kasowane" +
					"Wiec z �aski swoejej najpierw je usun \n  A potem wpisz tu \"rozumiem\"",
					"Pytanie",
					JOptionPane.WARNING_MESSAGE,
					null,
					null,
					null
			);
			if(String.CASE_INSENSITIVE_ORDER.compare(resultS, "rozumiem")!=0)
				return;
			rTe.getDay().removeMeal(te.getMeal().getName());
			result.remove(node);
			getTreeModel().nodeStructureChanged(result);
		}catch(Exception e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B��d, powa�ny b��d\n" +
					e.getMessage() + "\n" + e.getClass().getName(),
					"B��d",
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

	public String toString(){
		return "Usu�n posi�ek";
	}
	public Integer priority() {

		return 10;
	}

	public boolean userCanPerformAction() {
		return true;
	}


}
