package zzGUIElements.PopupMenu.Meal;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.MealExpenditure;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzGUIElements.MainFrame;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;

public class RemoveExpenditureInMeal extends AbstractPopupMenuItem{

	public RemoveExpenditureInMeal(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return "Usuñ";
	}


	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try {
			String[] opcje = new String[] { "Tak wyk",
					"Nie, nie zapisuj" };
			String pytanie = "Czy jesteœ absosmerfnie pewnien ¿e chcesz usun¹c to wyjœcie?";
			int result2 = JOptionPane.showOptionDialog(StaticContent.sc.getUppmostFrame(),
					pytanie,
					"Pytanie", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null, opcje, opcje[0]);
			
			if(result2 == JOptionPane.NO_OPTION) return;
			
			DefaultMutableTreeNode result = AbstractTreeElement.searchForNode(node,new com());
			TreeElement resultElement = (TreeElement) result.getUserObject();
			Mealv3 m = resultElement.getMeal();
            System.out.println("Meal: " + m.getName());
			TreeElement element = (TreeElement) node.getUserObject();
			MealExpenditure me = (MealExpenditure) element.getExpenditure();
            System.out.println("ID: " + me.getId() );
            System.out.println(me.tytulem );
            System.out.println("Lista id" );
            for(MealExpenditure me2: m.getContents().values()){
               System.out.print(me2.getId() + ", ");
            }
            System.out.println("Contains " +m.getContents().containsKey(me.getId()) );
			m.removeExpenditure(me.getId());
			result.remove(node);
			getTreeModel().nodeStructureChanged(result);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B³¹d\n" + e.getClass().getName() + "\n"+ e.getMessage(),
					"B³¹d",
					JOptionPane.WARNING_MESSAGE);
		}

		
	}

	public Integer priority() {
		return 10;
	}

	public boolean userCanPerformAction() {
		return true;
	}
	
	private class com implements Comparable<TreeElement>{

		public int compareTo(TreeElement arg0) {
			if(arg0.representsMeal())	return 0;
			return 1;
		}
		
	}

}
