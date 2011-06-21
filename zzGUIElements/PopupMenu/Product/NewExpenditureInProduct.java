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
import zzGUIElements.AbstractGuiElements.PopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzId.ExpenditureId;
import zzProduct.Product;
import zzUser.User;

public class NewExpenditureInProduct extends AbstractPopupMenuItem{
	
	static ExpenditureGeneratingDialog dialog = new ExpenditureGeneratingDialog();
	

	public synchronized void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx{
		DefaultMutableTreeNode result;
		Product p;
		TreeElement te;
		try{
			result = AbstractEmptyTreeElement.searchForNode(node, new cmp());
			te = (TreeElement) result.getUserObject();
			p = te.getProduct();
		}catch(ZZInternalClassCastExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Drzewo nie zawiera tylko DefaultMutableTreeNode, zawieraj¹cych TreeElement", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			
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
		
		Expenditure e = dialog.display(p);
		if(!dialog.hasFinishedSuccesfully()) return;
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
	
	private class cmp implements Comparable<TreeElement>{
		public int compareTo(TreeElement arg0) {
			if( arg0.representsProduct() )return 0;
			return 1;
		}
		
	}

	public boolean userCanPerformAction() {
		return User.canAddExpenditureInProduct();
	}

	public NewExpenditureInProduct(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}

}
