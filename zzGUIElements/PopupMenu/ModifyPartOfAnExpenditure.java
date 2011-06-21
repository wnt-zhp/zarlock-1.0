      package zzGUIElements.PopupMenu;


import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.Expenditure;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.PopupMenuItem;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzId.ExpenditureId;
import zzProduct.Product;
import zzUser.User;

public class ModifyPartOfAnExpenditure extends AbstractPopupMenuItem {

	public ModifyPartOfAnExpenditure(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		DefaultMutableTreeNode resultExpen;
		DefaultMutableTreeNode resultProduct;
		Expenditure expen;
		ExpenditureId id;
		Product p;
		try{
			resultExpen = AbstractEmptyTreeElement.searchForNode(node, new cmpExpen());
			TreeElement te = (TreeElement) resultExpen.getUserObject();
			expen = te.getExpenditure();
			id = te.getEcxpenditureId();
			
			resultProduct = AbstractEmptyTreeElement.searchForNode(resultExpen, new cmpProduct());
			TreeElement tep = (TreeElement) resultProduct.getUserObject();
			p = tep.getProduct();
			
			String message = "Podaj now� warto�� \n w przysz�o�ci b�dziesz wiedzia� co zmieniasz \n " +
					"i zna� zakres warto�ci ;-)";		
			
			String result;
			try{			
				result= (String) JOptionPane.showInputDialog(StaticContent.sc.getUppmostFrame(),message, "Podaj now� warto��",
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			}catch(ClassCastException e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Powa�ny b��d JOptionPane nie dzia�a jak powinno", 
						"B��d",JOptionPane.WARNING_MESSAGE);
				
				return;
			}
			
			try{
				te.setValue(result, p);
				getTreeModel().nodeChanged(resultExpen);
			}catch(ZZIllegalEx e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Poda�e� z�� warto��", 
						"B��d",JOptionPane.WARNING_MESSAGE);
			}catch(ZZIoBadFormatEx e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Z�y format np. dziesi�� zamiast 10 ;P", 
						"B��d",JOptionPane.WARNING_MESSAGE);
			}
			
		}catch(ZZInternalClassCastExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Drzewo nie zawiera tylko DefaultMutableTreeNode, zawieraj�cych TreeElement", 
					"B��d",JOptionPane.WARNING_MESSAGE);
			
			return;
		}catch(ZZInternalNoSuchEntryEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
				"Nei znaleziono produktu do kt�rego trzeba doda� wyj�cie\n" +
				"Wo�aj jacka. Albo pracuj dalej - je�li mo�esz poza tym �e operacja" +
				"zosta�a wykonana nic wielkiego si� nie sta�o", "B��d", JOptionPane.INFORMATION_MESSAGE);
			
			return;
			
		}catch(ZZInternalUnsupportedOperationExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa�ny b��d funkcja searchForNode nie dzia�a", 
					"B��d",JOptionPane.WARNING_MESSAGE);
			
			return;
		}catch(ClassCastException e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa�ny b��d funkcja searchForNode nie dzia�a", 
					"B��d",JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
	}

	public Integer priority() {
		return 10;
	}

	public boolean userCanPerformAction() {
		return User.canModifyPartOfAnExpenditure();
	}

	private class cmpExpen implements Comparable<TreeElement>{
		public int compareTo(TreeElement arg0) {
			if(arg0.representsExpenditure()) return 0;
			return 1;
		}		
	} 
	
	private class cmpProduct implements Comparable<TreeElement>{
		public int compareTo(TreeElement arg0) {
			if(arg0.representsExpenditure()) return 0;
			return 1;
		}		
	}
	

}
