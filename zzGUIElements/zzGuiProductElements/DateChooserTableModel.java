package zzGUIElements.zzGuiProductElements;


import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import zzMyDate.MyDate;
;

public class DateChooserTableModel extends AbstractTableModel {

      public Vector<MyDate> daty = null;
      public Vector<Boolean> bool = null;
      
      
      
        public DateChooserTableModel(List<MyDate> daty) {
         super();
         this.daty = new Vector ( daty );
         bool = new Vector<Boolean>(daty.size());
         for(int i = 0 ; i < daty.size() ; i++){
            bool.add(new Boolean(true));
         }
      }

      public int getRowCount() {
            return daty.size();
        }
        
        public int getColumnCount() {
            return 2;
        }
        
        public Object getValueAt(int row, int col) {
            if(col==0){
                return bool.get(row);
            }else{
                return daty.get(row);
            }
        }
        
        @Override
        public Class<?> getColumnClass(int col) {
            if(col==0){
                return Boolean.class;
            }
            else{
                return String.class;
            } 
        }
        
        @Override
        public String getColumnName(int col) {
            if(col==0){
                return "Czy uwzglêdniæ";
            }
            else{
                return "Dzieñ";
            } 
        }
        
        @Override
        public boolean isCellEditable(int row, int col) {
            if(col==0){
                return true;
            }
            else{
                return false;
            } 
        }
        
        @Override
        public void setValueAt(Object arg0, int row , int col) {
            bool.setElementAt((Boolean) arg0,row);
        }
}
