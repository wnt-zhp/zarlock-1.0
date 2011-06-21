package zzGUIElements.AbstractGuiElements;

import javax.swing.JEditorPane;
import javax.swing.JTree;

import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public class AbstractTreeElement 
  extends AbstractEmptyTreeElement{
    JEditorPane pane;
    String name;
    JTree tree;
    public AbstractTreeElement( String name,JEditorPane pane, JTree tree) {
      this.pane = pane;
      this.name = name;
      this.tree = tree;
    }
    
    public String toString(){
      return name;
    }
    
    public boolean hasAdditionalData(){
      return true;
    }
    
    public JEditorPane getAdditionalData(){
      return pane;
    }

	@Override
	public void setPanel(JEditorPane pane) throws ZZInternalUnsupportedOperationExceptionEx {
		this.pane = pane;
	}
    
  
  }