package zzGUIElements.PopupMenu;

import javax.swing.JTree;

import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.PopupMenu.Day.AddDay;
import zzGUIElements.PopupMenu.Day.NewMealInDay;
import zzGUIElements.PopupMenu.Day.Print11A;
import zzGUIElements.PopupMenu.Day.Print13A;
import zzGUIElements.PopupMenu.Day.RemoveMealInDay;
import zzGUIElements.PopupMenu.Meal.ModifyExpenditureInMeal;
import zzGUIElements.PopupMenu.Meal.NewExpenditureInMeal;
import zzGUIElements.PopupMenu.Meal.RemoveExpenditureInMeal;
import zzGUIElements.PopupMenu.Product.DeleteExpenditureInProductMenuItem;
import zzGUIElements.PopupMenu.Product.ModifyExpenditurePopupMenuItem;
import zzGUIElements.PopupMenu.Product.NewExpenditureInProduct;
import zzGUIElements.PopupMenu.Product.OnlyProductMenuItem;
import zzTests.print13Atests;

public abstract class PopupMenuGenerator {
	
	public static AbstractPopupMenu getDayPopupMenu(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new AddDay (tree));
		menu.addComponent(new NewMealInDay(tree));
		menu.addComponent(new Print11A(tree));
		menu.addComponent(new Print13A(tree));
		return menu;
	}
	
	public static AbstractPopupMenu getMealPopupMenu(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new NewExpenditureInMeal(tree));
		menu.addComponent(new NewMealInDay(tree));
		menu.addComponent(new RemoveMealInDay(tree));
		return menu;
	}
	
	public static AbstractPopupMenu getExpenditureInProductMenu(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new NewExpenditureInProduct(tree));
		menu.addComponent(new ModifyExpenditurePopupMenuItem(tree));
		menu.addComponent(new DeleteExpenditureInProductMenuItem(tree));
		return menu;
	}
	
	public static AbstractPopupMenu getExpenditureInMealMenu(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new NewExpenditureInMeal(tree));
		menu.addComponent(new ModifyExpenditureInMeal(tree));
		menu.addComponent(new RemoveExpenditureInMeal(tree));
		return menu;
	}
	
	public static AbstractPopupMenu getPartOfAnExpenditureInProductMenu(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new ModifyPartOfAnExpenditure(tree));
		return menu;
	}
	
	public static AbstractPopupMenu getIleInMealMenuItem(JTree tree){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		menu.addComponent(new ModifyPartOfAnExpenditure(tree));
		return menu;
	}

	public static AbstractPopupMenu getOtherItemsInMealMenuItem(){
		AbstractPopupMenu menu = new AbstractPopupMenu();
		return menu;
	}

	public static AbstractPopupMenu getProductPopupMenu(JTree tree){
		AbstractPopupMenu result = new AbstractPopupMenu();
		result.addComponent(new OnlyProductMenuItem(tree));
		return result;
	}
}
