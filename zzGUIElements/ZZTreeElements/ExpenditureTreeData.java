package zzGUIElements.ZZTreeElements;

import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzId.ExpenditureId;

public class ExpenditureTreeData extends AbstractTreeElement {

	private ExpenditureId eid;
	private Expenditure e;
	private AbstractPopupMenu menu;		
	
	public String toString(){
		return super.toString() + e.date;
	}

	public ExpenditureTreeData(String name, JEditorPane pane, JTree tree, ExpenditureId eid, Expenditure e, AbstractPopupMenu menu) {
		super(name, pane, tree);
		// TODO Auto-generated constructor stub
		this.eid = eid;
		this.e = e;
		this.menu = menu;
	}

	public ExpenditureTreeData(String name, JEditorPane pane,JTree tree,Map.Entry<ExpenditureId, Expenditure> entry) {
		super(name, pane,tree);
		this.eid = entry.getKey();
		this.e = entry.getValue();
	}
	
	@Override
	public ExpenditureId getEcxpenditureId() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		return eid;
	}

	@Override
	public Expenditure getExpenditure() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {
		return e;
	}

	@Override
	public boolean representsExpenditure() {
		return true;
	}

	@Override
	public AbstractPopupMenu getAssociatedPopUpMenu() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx {				
		return menu;
	}

	@Override
	public boolean hasAssociatedPopUpMenu() {
		return true;
	}
	

}
