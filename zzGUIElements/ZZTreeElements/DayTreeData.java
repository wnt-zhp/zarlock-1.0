package zzGUIElements.ZZTreeElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Day;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;

public class DayTreeData extends AbstractTreeElement {

	private Day day;
	private AbstractPopupMenu menu;

	public DayTreeData(JEditorPane pane, JTree tree, Day day,AbstractPopupMenu menu) {
		super(day.getDate().toString(), pane, tree);
		// TODO Auto-generated constructor stub
		this.day = day;
		this.menu= menu;
	}

	@Override
	public Day getDay() throws ZZIllegalEx,
			ZZInternalUnsupportedOperationExceptionEx {
		return day;
	}

	@Override
	public boolean RepresentsDay() {
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
