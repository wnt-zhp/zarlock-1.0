package zzGUIElements.AbstractGuiElements;

import java.util.Comparator;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import zzDataBase.Day;
import zzDataBase.Expenditure;
import zzDataBase.Mealv3;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzId.ExpenditureId;
import zzProduct.Product;

/**
 * @author jb
 *
 */

public class AbstractEmptyTreeElement  implements TreeElement {
	
	public void setPanel(JEditorPane pane) throws ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
		
	}

	public void setValue(String stringRepresentation, Object modifiedObject) 
	throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx, ZZInternalClassCastExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
		
	}

	public void setValue(String stringRepresentation, Object[] modifiedObjects) 
	throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx, ZZInternalClassCastExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();		
	}

	public boolean representsPartOfExpenditure() {
		return false;
	}
	
	public void setValue(String stringRepresentation)
	throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx, 
	ZZIoBadFormatEx, ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean representsExpenditure() {
		return false;
	}
	
	public Expenditure getExpenditure() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean representsMeal() {
		return false;
	}
	
	public Mealv3 getMeal() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean RepresentsDay() {
		return false;
	}
	
	public Day getDay() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx{
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean hasAdditionalData() {
		return false;
	}
	
	public JEditorPane getAdditionalData()throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean representsProduct() {
		return false;
	}
	
	public Product getProduct() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}
	
	public boolean hasAssociatedPopUpMenu() {
		return false;
	}
	
	public AbstractPopupMenu getAssociatedPopUpMenu() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
		
	}
	
	public ExpenditureId getEcxpenditureId() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}

		
	public void reload(){	
	}

	public JTree getTreeReference() throws ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}

	public boolean hasTreeReference() {
		return false;
	}

	public static DefaultMutableTreeNode searchForNode
	(DefaultMutableTreeNode beginnode, Comparable<TreeElement> cmp)
	throws ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx{
		TreeNode node = beginnode;
		DefaultMutableTreeNode castedNode;
		while(true){
			if(!DefaultMutableTreeNode.class.isInstance(node)) 
				throw new ZZInternalClassCastExceptionEx(DefaultMutableTreeNode.class);
			castedNode = (DefaultMutableTreeNode) node;
			
			if(!TreeElement.class.isInstance(castedNode.getUserObject()))
				throw new ZZInternalClassCastExceptionEx(TreeElement.class);
			
			TreeElement elem = (TreeElement) castedNode.getUserObject();
			
			if(cmp.compareTo(elem)==0){
				return castedNode;
			}
			
			if(castedNode.getParent()== null)
				throw new ZZInternalNoSuchEntryEx();
			else node = castedNode.getParent();
			
		}
	}


}
