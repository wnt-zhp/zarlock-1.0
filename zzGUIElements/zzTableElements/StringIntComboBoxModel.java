/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import StringIntTwoWayMap.StringIntTwoWayMap;

import zzProduct.Product;

public abstract class StringIntComboBoxModel 
  extends AbstractListModel
  implements ComboBoxModel, PropertyChangeListener{
  
  private Product owner; 
  private Integer selected;
  
  private final static String ZMIEN =  "Zmien";
  
  private  StringIntTwoWayMap Map;
  public StringIntComboBoxModel(Product _owner) {
    super();
    owner = _owner;
  }

  public void setSelectedItem(Object arg0) {
    selected = Map.getOne((String) arg0);    
  }

  public Object getSelectedItem() {
    return Map.getTwo(selected);
  }

  public int getSize() {
    return Map.getSize()+1;
  }

  public Object getElementAt(int arg0) {
    synchronized(Map){
      if(arg0 <= Map.values.size()){
        return Map.values.get(arg0);
      }
      else return ZMIEN;
    }
  }
  

  

}
