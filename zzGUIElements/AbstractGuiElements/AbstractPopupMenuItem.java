package zzGUIElements.AbstractGuiElements;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import zzEx.ZZInternalClassCastExceptionEx;

public abstract class AbstractPopupMenuItem implements PopupMenuItem {

	private JTree tree;

	public AbstractPopupMenuItem(JTree tree) {
		super();
		// TODO Auto-generated constructor stub
		this.tree = tree;
	}

	protected void setTree(JTree tree) {
		this.tree = tree;
	}

	protected JTree getTree() {
		return tree;
	}
	
	protected DefaultTreeModel getTreeModel() throws ZZInternalClassCastExceptionEx{
		try{
			return (DefaultTreeModel) getTree().getModel();
		}catch(ClassCastException e){
			throw new ZZInternalClassCastExceptionEx();
		}
	}
	
}
