/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import zzEx.ZZIllegalEx;
import zzProduct.Product;

import StringIntTwoWayMap.StringIntTwoWayMap;

public abstract class StringIntComboBox extends JComboBox 
  implements 
  ActionListener,
  PropertyChangeListener{
  
  protected abstract void setProperty(String item) throws ZZIllegalEx;
  protected abstract String getProperty() throws ZZIllegalEx;
  StringIntTwoWayMap map;
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  Product owner;

  public StringIntComboBox(StringIntTwoWayMap _map) throws ZZIllegalEx{
    super(_map.values);
    setEditable(true);
    //addActionListener(this); 
    setEditor(new StringEditor());
    map=_map;
    map.foo.addPropertyChangeListener(this);
    setMaximumRowCount(5);    
    super.setSelectedItem(getProperty());    
  }
  
  public StringIntComboBox(Product _owner,StringIntTwoWayMap _map) throws ZZIllegalEx{
    super(_map.values);
    setEditable(true);
    //addActionListener(this); 
    setEditor(new StringEditor());
    owner = _owner;
    map=_map;
    map.foo.addPropertyChangeListener(this);
    setMaximumRowCount(5);    
    super.setSelectedItem(getProperty());         
   }
  
  public void setSelectedItem(Object item){
    try{
      String foo = (String) item;
      boolean b = true; 
      for(String s:map.values){
        if(String.CASE_INSENSITIVE_ORDER.compare(s,foo)==0){
          b=false;
          break;
        }
      }
      if(b) map.put(new Integer(map.maxOne().intValue()+1), foo);
      else System.out.println("action perf: " + foo );
      setProperty((String)item);
      super.setSelectedItem(item);
    }catch(ZZIllegalEx e){
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
  }
  
  
  
  public void setSelectedIndex(int index){
    try{
    setProperty((String) getItemAt(index));
    super.setSelectedIndex(index);
    }catch(ZZIllegalEx e){
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
  }  
  
  public void actionPerformed(ActionEvent a ){
    String foo = (String) this.getSelectedItem();
    boolean b = true; 
    for(String s:map.values){
      if(String.CASE_INSENSITIVE_ORDER.compare(s,foo)==0){
        b=false;
        break;
      }
    }
    if(b) map.put(new Integer(map.maxOne().intValue()+1), foo);
    else System.out.println("action perf: " + foo );
  }

  public void propertyChange(PropertyChangeEvent arg0) {
    if(arg0.getPropertyName().equals(StringIntTwoWayMap.PropertyName)){
     fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, null, ItemEvent.ITEM_STATE_CHANGED)); 
    this.removeAllItems();
    for(String s:map.values){
      addItem(s);
    }
    }    
  }
  
  class StringEditor extends BasicComboBoxEditor{
    

   public void setItem(Object arg0) {
      editor.setText((String)arg0);
    }
   
   public String getItem(){
     setSelectedItem(editor.getText());
     return editor.getText();
   }
    
  }

}
