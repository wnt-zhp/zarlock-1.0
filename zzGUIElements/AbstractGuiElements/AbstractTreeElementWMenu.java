package zzGUIElements.AbstractGuiElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public class AbstractTreeElementWMenu extends AbstractTreeElement {

	private AbstractPopupMenu menu;
	
	public AbstractTreeElementWMenu(String name, 
			JEditorPane pane, 
			JTree tree,
			AbstractPopupMenu menu) {
		super(name, pane, tree);
		this.menu = menu;
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
