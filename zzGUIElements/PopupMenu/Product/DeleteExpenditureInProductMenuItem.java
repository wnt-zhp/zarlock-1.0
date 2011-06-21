package zzGUIElements.PopupMenu.Product;

import java.util.Map;

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
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.PopupMenuItem;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzId.ExpenditureId;
import zzProduct.Product;
import zzUser.User;

public class DeleteExpenditureInProductMenuItem extends AbstractPopupMenuItem {

	
	
	
	public DeleteExpenditureInProductMenuItem(JTree tree) {
		super(tree);
		// TODO Auto-generated constructor stub
	}



	public synchronized void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx{
		DefaultMutableTreeNode result;
		Product p;
		Expenditure expen;
		ExpenditureId id;
		try{
			TreeElement data = (TreeElement) node.getUserObject();
			result = AbstractEmptyTreeElement.searchForNode(node, new cmp());
			TreeElement te = (TreeElement) result.getUserObject();
			p = te.getProduct();
			expen = data.getExpenditure();
			id = data.getEcxpenditureId();
			
			String[] opcje = new String[]{
					"Tak, usuñ",
					"Nie, nie usuwaj"
			};
			String pytanie = "Czy rzeczywiœcie chcesz usun¹c to wyjœcie?" + 
			"\nDane wyjscia\n" + "Produkt: " + p.getName() + "\nIle: " + expen.ile +
			"\nKiedy: " + expen.date + "\nTytu³em: " + expen.tytulem;
			
			int wynik;
			wynik = JOptionPane.showOptionDialog(StaticContent.sc.getUppmostFrame(), 
				   "Pytanie",
					pytanie,
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					opcje,
					opcje[1]
				);
			if(wynik == JOptionPane.YES_OPTION) p.cancelExpenditure(id);
			getTreeModel().removeNodeFromParent(node);
			te.setPanel(BranchGenerator.createProductDocument(p));
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
			e.printStackTrace();
			return;
			
		}catch(ZZInternalUnsupportedOperationExceptionEx e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa¿ny b³¹d funkcja searchForNode nie dzia³a", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return;
		}catch(ClassCastException e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"Powa¿ny b³¹d funkcja searchForNode nie dzia³a", 
					"B³¹d",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return;
		} catch (ZZIllegalEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZZIoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
		
	

	public Integer priority() {
		return 200;
	}
	
	public final String toString(){
		return "Usun wyjœcie";
	}
	
	private class cmp implements Comparable<TreeElement>{
		public int compareTo(TreeElement arg0) {
			if( arg0.representsProduct() )return 0;
			return 1;
		}	
	}

	public boolean userCanPerformAction() {
		return User.canRemoveExpenditureInProduct();
	}


}
