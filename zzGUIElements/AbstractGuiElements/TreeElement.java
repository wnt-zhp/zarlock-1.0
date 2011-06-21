package zzGUIElements.AbstractGuiElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzDataBase.Day;
import zzDataBase.Expenditure;
import zzDataBase.Mealv3;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzEx.ZZIoEx;
import zzId.ExpenditureId;
import zzProduct.Product;

public interface TreeElement {
	
	
	boolean representsPartOfExpenditure();
	/**
	 * @deprecated
	 * @param stringRepresentation
	 * @throws ZZIllegalEx
	 */
	void setValue(String stringRepresentation) 
		throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx, 
		ZZIoBadFormatEx, ZZIoBadFormatEx,ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx;
	
	/**
	 * @deprecated
	 * @param stringRepresentation
	 * @throws ZZIllegalEx
	 */
	void setValue(String stringRepresentation, Object modifiedObject) 
		throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx, 
		ZZInternalClassCastExceptionEx, ZZIoBadFormatEx, ZZIoBadFormatEx,
		ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx;
	
	/**
	 * @deprecated
	 * @param stringRepresentation
	 * @throws ZZIllegalEx
	 */
	void setValue(String stringRepresentation, Object[] modifiedObjects) 
		throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx,
		ZZIoBadFormatEx, ZZInternalClassCastExceptionEx, ZZIoBadFormatEx,
		ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx;


	boolean representsExpenditure();
	/**
	 * @deprecated All database editig functionality should be done via
	 * getAssociatedPopUpMenu() 
	 * @see TreeElement.getAssociatedPopUpMenu();
	 */
	Expenditure getExpenditure() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx ;
	ExpenditureId getEcxpenditureId() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	boolean representsMeal();
	
	/** 
	 * @deprecated All database editin functionality should be done via
	 * getAssociatedPopUpMenu() 
	 * @see TreeElement.getAssociatedPopUpMenu();
	 * 
	 */
	Mealv3 getMeal() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	
	boolean RepresentsDay();
	
	/**
	 * @depracated All database editin functionality should be done via
	 * getAssociatedPopUpMenu() 
	 * @see TreeElement.getAssociatedPopUpMenu();
	 */
	Day getDay() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	
	boolean hasAdditionalData();
	JEditorPane getAdditionalData() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	void setPanel(JEditorPane pane) throws ZZInternalUnsupportedOperationExceptionEx;
	
	boolean representsProduct();
	
	/**
	 * @depracated All database editin functionality should be done via
	 * getAssociatedPopUpMenu() 
	 * @see TreeElement.getAssociatedPopUpMenu();
	 */
	Product getProduct() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	
	
	boolean hasAssociatedPopUpMenu(); 
	AbstractPopupMenu getAssociatedPopUpMenu() throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;
	
	boolean hasTreeReference();
	JTree getTreeReference() throws ZZInternalUnsupportedOperationExceptionEx;
	
	void reload();
}
