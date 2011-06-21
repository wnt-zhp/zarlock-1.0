package zzGUIElements.PopupMenu.Product;

import java.util.Map;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;

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

public class ModifyExpenditurePopupMenuItem  
	extends AbstractPopupMenuItem{

static ExpenditureGeneratingDialog dialog = new ExpenditureGeneratingDialog();
	

	public synchronized void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx{
		DefaultMutableTreeNode result;
		Product p;
		Expenditure expen;
		ExpenditureId id;
		TreeElement te;
		TreeElement data;
		try{
			data = (TreeElement) node.getUserObject();
			result = AbstractEmptyTreeElement.searchForNode(node, new CmpProduct());
			te = (TreeElement) result.getUserObject();
			p = te.getProduct();
			expen = data.getExpenditure();
			id = data.getEcxpenditureId();
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
		
		Expenditure e = dialog.display(p,expen);
		try{
			p.changeExpenditure(id,e.ile,e.date,e.tytulem);
			getTreeModel().nodeChanged(result);
			te.setPanel(BranchGenerator.createProductDocument(p));
			data.setPanel(BranchGenerator.createExpenditureDocument(e));
		}catch(ZZIllegalEx exp){
			try{
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						new JList(p.isExpenditureOKStr(e)),
						"Lista b��d�w przy wydawaniu z magazynu",
						JOptionPane.WARNING_MESSAGE);
			}catch(ZZIllegalEx easas){
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Produkt nie zosta� prawid�owo zainicjalizowany" +
						easas.getMessage(),
						"Lista b��d�w przy wydawaniu z magazynu",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (ZZInternalClassCastExceptionEx ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ZZInternalUnsupportedOperationExceptionEx ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ZZIoEx ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public Integer priority() {
		return 100;
	}
	
	
	public boolean userCanPerformAction() {
		return User.canModifyExpenditureInProduct();
	}

	public String toString(){
		return "Zmie� wyj�cie";
	}
	
	private class CmpProduct implements Comparable<TreeElement>{
		public int compareTo(TreeElement arg0) {
			if( arg0.representsProduct() )return 0;
			return 1;
		}		
	}

	public ModifyExpenditurePopupMenuItem(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}
	

}
