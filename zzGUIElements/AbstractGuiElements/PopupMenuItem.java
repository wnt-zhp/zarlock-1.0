package zzGUIElements.AbstractGuiElements;


import javax.swing.tree.DefaultMutableTreeNode;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;

public interface PopupMenuItem{
	/**
	 *
	 * @param node Node wchih launched popup menu. Usefull for finding parents etc. Can be null
	 * @param ObjectChanged Object to be changed. Can be null, 
	 */
	public void performAction(DefaultMutableTreeNode node)
		throws ZZIllegalEx, ZZInternalClassCastExceptionEx;
	/**
	 * Should return name of that object, so AbstractPopupMenu
	 * could display it. No mojePopupMenu@48754 is not OK. 
	 * @return
	 */
	public String toString();
	
	/**
	 * Priority controls the sequence in wchih objects are put in the popup
	 * @return
	 */
	public Integer priority();
	
	public boolean userCanPerformAction();
	
}
