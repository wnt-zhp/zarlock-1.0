package zzGUIElements.PopupMenu.Product;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.Expenditure;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoEx;
import zzGUIElements.BranchGenerator;
import zzGUIElements.ExpenditureGeneratingDialog;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzId.ExpenditureId;
import zzProduct.Product;
import zzUser.User;

public class OnlyProductMenuItem extends AbstractPopupMenuItem {
static ExpenditureGeneratingDialog dialog = new ExpenditureGeneratingDialog();
	


	public OnlyProductMenuItem(JTree tree) {
	super(tree);
	// TODO Auto-generated constructor stub
}

	public synchronized void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx{
		DefaultMutableTreeNode result;
		Product p;
		TreeElement te;
		try{
			result = node;
			te = (TreeElement) result.getUserObject();
			p = te.getProduct();
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
		
		Expenditure e = dialog.display(p);
		if(e==null) return;
		try{
			ExpenditureId id = p.performExpenditure(e);
			//getTreeModel().nodeChanged(result);			
			te.setPanel(BranchGenerator.createProductDocument(p));
			result.add(BranchGenerator.getExpenditureNode(e,id,p,getTree()));
			//getTreeModel().nodeChanged(result);
			getTreeModel().nodeStructureChanged(result);
		}catch(ZZIllegalEx exp){
			try{
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						new JList(p.isExpenditureOKStr(e)),
						"Lista b³êdów przy wydawaniu z magazynu",
						JOptionPane.WARNING_MESSAGE);
			}catch(ZZIllegalEx easas){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Produkt nie zosta³ prawid³owo zainicjalizowany" +
						easas.getMessage(),
						"Lista b³êdów przy wydawaniu z magazynu",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (ZZInternalClassCastExceptionEx es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		} catch (ZZIoEx es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		} catch (ZZInternalUnsupportedOperationExceptionEx ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public Integer priority() {
		return 10;
	}
	
	public String toString(){
		return "Nowe wyjœcie";
	}
	

	public boolean userCanPerformAction() {
		return User.canAddExpenditureInProduct();
	}


}
	

