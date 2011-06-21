package zzGUIElements.ZZTreeElements;

import java.text.ParseException;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZIoBadFormatEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzId.ExpenditureId;
import zzMyDate.MyDate;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;

public class KiedyTreeData extends PartOfAnExpenditureTreeData {

	
	public String toString(){
		return super.toString() + ovner.date;
	}

	
	public KiedyTreeData(String name, JEditorPane pane, JTree tree, Expenditure ovner, ExpenditureId id, ExpenditureHolder ovnerProduct, AbstractPopupMenu menu) {
		super(name, pane, tree, ovner, id, ovnerProduct, menu);
		// TODO Auto-generated constructor stub
	}

	public void setValue(String str) throws ZZIoBadFormatEx, ZZIllegalEx, 
	ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx{
		MyDate date;
		try {
			date = new MyDate(str);
		} catch (ParseException e) {
			throw new ZZIoBadFormatEx();
		}
		ovnerProduct.changeExpenditure(id, new Expenditure ( ovner.ile, date, ovner.tytulem));  

	}
}
