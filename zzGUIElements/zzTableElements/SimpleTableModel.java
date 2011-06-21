/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import javax.swing.table.AbstractTableModel;

import zzProductBase.SimpleProductBase;
import zzUser.User;
import zzUser.Priveleges;

/**
 * @author jb
 *
 */
public class SimpleTableModel extends AbstractTableModel {

  private SimpleProductBase contents = new SimpleProductBase();
  
  /**
   * Does absolutelly nothing
   */
  public SimpleTableModel() {}
  

  boolean isEditable(int rowIndex, int columnIndex){
    if(User.getPriveleges()==Priveleges.ROOTUSER) return true;
    return false;
  }

  /** (non-Javadoc)
   * @see javax.swing.table.TableModel#getRowCount()
   */
  public int getRowCount() {
    return (int) contents.getNumberOfElems();
  }

  /**
   * @return  returns 11;
   * @see javax.swing.table.TableModel#getColumnCount()
   */
  public int getColumnCount() {
    return 11;
  }

  /**
   * 
   *  (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int arg0, int arg1) {
    // TODO Auto-generated method stub
    return null;
  }

}
