/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzId.ExpenditureId;
import zzMyDate.MyDate;
import zzProduct.Product;

public class ExpenditureComboBox extends JComboBox {
  Product owner;
  mod model;
  //Vector<Expenditure> contents;
  public ExpenditureComboBox(Product _owner) {
    super();
    owner = _owner;
    super.setModel(model = new mod(this));
    super.setRenderer(new rend (this));
    model.fire(this,1, getItemCount());
    setPreferredSize(new Dimension(250,20));
  }

  public int getItemCount(){
    return model.getItemCount();
  }
  
  public ExpenditureId getSelectedItemExpenditureId(){
    try{
      return owner.getExpenditures().get(getSelectedIndex()).getKey();
    }catch(IndexOutOfBoundsException foo){
      String napis = "Skontaktuj sie z jb";
      model.fire(this,0, getItemCount());
      try{
        return owner.getExpenditures().get(getSelectedIndex()).getKey();
      }catch(Exception e){
        return null;
      }      
    }catch(ZZIllegalEx e){
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
      return null;
      //String napis = "Skontaktuj sie z 99::WHW\"DRUIDZI\"::Jacek i powiedz ExpenditureComboBox::Error :P";
    }
  }
  
  class mod extends AbstractListModel 
  implements ComboBoxModel{
    
    mod(ExpenditureComboBox e){
      parent = e;
    }
    
    int selected;
    
    ExpenditureComboBox parent;
    
    public int getItemCount(){
      try{
        return owner.getExpenditures().size();
      }catch(ZZIllegalEx e){
        System.err.println(e.getMessage());
        e.printStackTrace(System.err);
        return 0;
      }
    }
    
    public void fire(Object source,
        int index0,
        int index1){
      super.fireContentsChanged(source, index0,index1);
    }

    public void setSelectedItem(Object arg0) {
      selected = ((Integer) arg0).intValue();
      
    }

    public Object getSelectedItem() {
      return new Integer(selected);
    }

    public int getSize() {
      return getItemCount();
    }
    
    public Object getElementAt(int arg0){
      return new Integer(arg0);
    }
    
    /*public Object getElementAt(int arg0) {
      try{
      return owner.getExpenditures().get(arg0).getValue();
      }
      catch(ZZIllegalEx e){
        return new Expenditure(0, new MyDate(), "Opieprz Jacka" );
      }catch(IndexOutOfBoundsException e){
        fire(parent, 0, getItemCount());
        try{
          return owner.getExpenditures().get(arg0);
        }catch(ZZIllegalEx ee){
          return new Expenditure(0, new MyDate(), "Opieprz Jacka" );
        }catch(IndexOutOfBoundsException ee){
          return new Expenditure(0, new MyDate(), "Opieprz Jacka i to tak ¿eby popamniêta³" );
        }
      }
    }*/
  }
  
  class rend extends JLabel implements ListCellRenderer {
    
    ExpenditureComboBox parent;
    
    public rend(ExpenditureComboBox c){
      parent = c;
    }    

    public Component getListCellRendererComponent(
        JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus) {
        
        if(getItemCount() == 0){
          setText("Na razie ¿adnych");
          return this;
        }
        int selectedIndex;
        //Systint selectedIndexem.err.print(value.getClass().getName() + ((ExpenditureId) value ).toString());
        //System.exit(1);
        /*try{
          ExpenditureId id = (ExpenditureId) value;
          selectedIndex = owner.getExpenditures().i;
          
        }*/
        
        selectedIndex = ((Integer)value).intValue();
        
        
        if (isSelected) {
          setBackground(list.getSelectionBackground());
          setForeground(list.getSelectionForeground());
        } else {
          setBackground(list.getBackground());
          setForeground(list.getForeground());
        }
        String napis = "";
        try{
          napis = owner.getExpenditures().get(selectedIndex).getValue().toString();
          
        }catch(IndexOutOfBoundsException foo){
          napis = "Skontaktuj sie z jb";
          model.fire(parent,0, getItemCount());
        }catch(ZZIllegalEx e){
          System.err.println(e.getMessage());
          e.printStackTrace(System.err);
          napis = "Skontaktuj sie z 99::WHW\"DRUIDZI\"::Jacek i powiedz ExpenditureComboBox::Error :P";
        }finally{
          setText(napis);
        }
        
        return this;
        }
    
  }
  

}
