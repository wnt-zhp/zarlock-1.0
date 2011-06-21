package zzGUIElements.ZZTreeElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzGUIElements.AbstractGuiElements.AbstractTreeElementWMenu;
import zzProduct.Product;

public class ProductTreeBranch extends AbstractTreeElementWMenu {
  Product prod; 
 
  

public ProductTreeBranch(String name, JEditorPane pane, JTree tree, AbstractPopupMenu menu, Product prod) {
	super(name, pane, tree, menu);
	// TODO Auto-generated constructor stub
	this.prod = prod;
}

public boolean representsProduct(){
    return true;
  }
  
  public Product getProduct(){
    return prod;
  }
 

}
