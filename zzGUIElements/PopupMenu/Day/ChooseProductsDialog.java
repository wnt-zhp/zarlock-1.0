package zzGUIElements.PopupMenu.Day;

import java.awt.HeadlessException;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import zzEx.PrimaryError;
import zzEx.ZZIllegalEx;
import zzProduct.Product;

public class ChooseProductsDialog extends JDialog {
   
   public Vector<Product> prods;
   public Vector<Boolean> bools;
   public Vector<Product> choosenProds;
   public int printedNameOffset = 0;

   public ChooseProductsDialog(Vector<Product> products) throws HeadlessException {
      super();
      prods = products;
      bools = new Vector();
      for(int i = 0; i <bools.size();i++){
         bools.add(new Boolean (true));
      }
      this.setTitle("Wybierz produkty które maj¹ tê sam¹ nazwê.");
      this.setSize(640,480);
      
   }
   
   private class myTableModel extends AbstractTableModel{

      public int getRowCount() {
         return prods.size();
      }

      public int getColumnCount() {
         // TODO Auto-generated method stub
         return 3;
      }

      public Object getValueAt(int row, int col) {
         // TODO Auto-generated method stub
         switch(col){
         case 0 :
            try {
                  return prods.get(row).getName();
               } catch (ZZIllegalEx e) {
                  e.printStackTrace();
                  return "No³name";
                  
               }
         case 1 :
            return bools.get(row);
         case 2 :
            if(row == printedNameOffset)
               return Boolean.TRUE;
               return Boolean.FALSE;
            
         default : throw new PrimaryError("DupaDupaDupa");
             
         }
      }

      @Override
      public Class<?> getColumnClass(int col) {
         switch(col){
         case 0 :
            return String.class;
         case 1 :
            return Boolean.class;
         case 2 :
            return Boolean.class;
            
         default : throw new PrimaryError("DupaDupaDupa");
             
         }
      }

      @Override
      public String getColumnName(int col) {
         switch(col){
         case 0 :
            return "Nazwa produktu";
         case 1 :
            return "Czy dodaæ go do kartoteki";
         case 2 :
            return "Podaj nazwê która ma pojawiæ siê w kartotece";
            
         default :
            throw new PrimaryError("DupaDupaDupa");
         }
             
      }

      @Override
      public boolean isCellEditable(int row, int col) {
         if(col==0) return false;
         return true;
      }

      @Override
      public void setValueAt(Object arg0, int row, int col) {
         switch(col){
         case 0 :
         throw new PrimaryError("DupaDupaDupa");
         case 1 :
           bools.set(row,(Boolean) arg0);
         case 2 :
            if(bools.get(row).booleanValue()){
               printedNameOffset = row;
               this.fireTableDataChanged();
            }
            
         default : throw new PrimaryError("DupaDupaDupa");
             
         }
      }
      
      
      
   }

}
