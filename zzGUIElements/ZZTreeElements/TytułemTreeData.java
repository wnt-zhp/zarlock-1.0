package zzGUIElements.ZZTreeElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzId.ExpenditureId;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;

public class Tytu³emTreeData extends PartOfAnExpenditureTreeData {

	
	
	public String toString(){
		return super.toString() + ovner.tytulem;
	}

	
	public Tytu³emTreeData(String name, JEditorPane pane, JTree tree, Expenditure ovner, ExpenditureId id, ExpenditureHolder ovnerProduct, AbstractPopupMenu menu) {
		super(name, pane, tree, ovner, id, ovnerProduct, menu);
		// TODO Auto-generated constructor stub
	}

	public void setValue(String str) throws ZZIllegalEx,
		ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx{
		ovnerProduct.changeExpenditure(id, new Expenditure(ovner.ile, ovner.date, str));
	}
}
