package zzGUIElements.ZZTreeElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzId.ExpenditureId;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;


public abstract class PartOfAnExpenditureTreeData extends AbstractTreeElement {
	
	protected Expenditure ovner;
	protected ExpenditureId id;
	protected ExpenditureHolder ovnerProduct;
	protected AbstractPopupMenu menu;
	

	

	public PartOfAnExpenditureTreeData(String name, JEditorPane pane, JTree tree, Expenditure ovner, ExpenditureId id, ExpenditureHolder ovnerProduct, AbstractPopupMenu menu) {
		super(name, pane, tree);
		// TODO Auto-generated constructor stub
		this.ovner = ovner;
		this.id = id;
		this.ovnerProduct = ovnerProduct;
		this.menu = menu;
	}

	@Override
	public AbstractPopupMenu getAssociatedPopUpMenu() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		return menu;
	}

	@Override
	public boolean representsPartOfExpenditure() {
		return true;
	}

}
