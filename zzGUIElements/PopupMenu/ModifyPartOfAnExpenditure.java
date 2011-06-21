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
			
			String message = "Podaj now¹ wartoœæ \n w przysz³oœci bêdziesz wiedzia³ co zmieniasz \n " +
					"i zna³ zakres wartoœci ;-)";		
			
			String result;
			try{			
				result= (String) JOptionPane.showInputDialog(StaticContent.sc.getUppmostFrame(),message, "Podaj now¹ wartoœæ",
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			}catch(ClassCastException e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Powa¿ny b³¹d JOptionPane nie dzia³a jak powinno", 
						"B³¹d",JOptionPane.WARNING_MESSAGE);
				
				return;
			}
			
			try{
				te.setValue(result, p);
				getTreeModel().nodeChanged(resultExpen);
			}catch(ZZIllegalEx e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Poda³eœ z³¹ wartoœæ", 
						"B³¹d",JOptionPane.WARNING_MESSAGE);
			}catch(ZZIoBadFormatEx e){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Z³y format np. dziesiêæ zamiast 10 ;P", 
						"B³¹d",JOptionPane.WARNING_MESSAGE);
			}
			
		}catch(ZZInternalClassCastExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Drzewo nie zawiera tylko DefaultMutableTreeNode, zawieraj¹cych TreeElement", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			
			return;
		}catch(ZZInternalNoSuchEntryEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
				"Nei znaleziono produktu do którego trzeba dodaæ wyjœcie\n" +
				"Wo³aj jacka. Albo pracuj dalej - jeœli mo¿esz poza tym ¿e operacja" +
				"zosta³a wykonana nic wielkiego siê nie sta³o", "B³¹d", JOptionPane.INFORMATION_MESSAGE);
			
			return;
			
		}catch(ZZInternalUnsupportedOperationExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa¿ny b³¹d funkcja searchForNode nie dzia³a", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			
			return;
		}catch(ClassCastException e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa¿ny b³¹d funkcja searchForNode nie dzia³a", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			
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
