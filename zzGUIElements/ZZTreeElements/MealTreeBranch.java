package zzGUIElements.ZZTreeElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Mealv3;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;

public class MealTreeBranch extends AbstractTreeElement {

	private Mealv3 posilek;

	private AbstractPopupMenu menu;

	public MealTreeBranch(String name, JEditorPane pane, JTree tree,
			Mealv3 posilek, AbstractPopupMenu menu) {
		super(name, pane, tree);
		// TODO Auto-generated constructor stub
		this.posilek = posilek;
		this.menu = menu;
	}

	public boolean representsMeal() {
		return true;
	}

	public Mealv3 getMeal() {
		return posilek;
	}

	@Override
	public AbstractPopupMenu getAssociatedPopUpMenu() throws ZZIllegalEx,
			ZZInternalUnsupportedOperationExceptionEx {
		// TODO Auto-generated method stub
		return menu;
	}

	@Override
	public boolean hasAssociatedPopUpMenu() {
		// TODO Auto-generated method stub
		return true;
	}

}
