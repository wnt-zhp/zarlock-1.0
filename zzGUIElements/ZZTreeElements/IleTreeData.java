package zzGUIElements.ZZTreeElements;



import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZIoBadFormatEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzId.ExpenditureId;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;

public class IleTreeData extends PartOfAnExpenditureTreeData {

	

	public IleTreeData(String name, JEditorPane pane, JTree tree, Expenditure ovner, ExpenditureId id, ExpenditureHolder ovnerProduct, AbstractPopupMenu menu) {
		super(name, pane, tree, ovner, id, ovnerProduct, menu);
	}
	
	public String toString(){
		return super.toString() + ovner.ile;
	}

	public void setValue(String str) throws ZZIllegalEx, ZZIoBadFormatEx, 
	ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx{
		float ile;
		try{
   		ile = Float.parseFloat(str);
   	}catch(NumberFormatException e){
   		throw new ZZIoBadFormatEx();
   	}
   	ovnerProduct.changeExpenditure(id, new Expenditure(ile, ovner.date, ovner.tytulem));   	
   }
	
}
