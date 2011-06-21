package zzGUIElements.PopupMenu.Day;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import zzEx.PrimaryError;
import zzEx.ZZIllegalEx;
import zzProduct.Product;
import java.awt.GridLayout;

public class ChoosProdsInDialogv2 extends JDialog {
   
   public Vector<Product> prods;
   public Vector<Boolean> bools;
   private Vector<Product> choosenProds;
   public int printedNameOffset = 0;
  public boolean wantsWxit = false;
   private JPanel jContentPane = null;
   private JScrollPane jScrollPane = null;
   private JTable jTable = null;
   private JButton jButton = null;
   private myTableModel model = null;
   private JPanel jPanel = null;
   private JButton jButton1 = null;
   

   /**
    * This is the default constructor
    */
   public ChoosProdsInDialogv2(Vector<Product> products) throws HeadlessException {
      super();
      prods = products;
      bools = new Vector();
      for(int i = 0; i <products.size();i++){
         
         bools.add(new Boolean (true));
      }
      this.setTitle("Wybierz produkty które maj¹ tê sam¹ nazwê.");
      //this.setSize(640,480);
      initialize();
   }

   /**
    * This method initializes this
    * 
    * @return void
    */
   private void initialize() {
      this.setSize(300, 200);
      this.setContentPane(getJContentPane());
      this.setModal(true);
   }

   /**
    * This method initializes jContentPane
    * 
    * @return javax.swing.JPanel
    */
   private JPanel getJContentPane() {
      if (jContentPane == null) {
         GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
         gridBagConstraints5.gridx = 0;
         gridBagConstraints5.gridy = 1;
         GridBagConstraints gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.gridy = 0;
         gridBagConstraints.weightx = 1.0;
         gridBagConstraints.weighty = 1.0;
         gridBagConstraints.gridx = 0;
         jContentPane = new JPanel();
         jContentPane.setLayout(new GridBagLayout());
         jContentPane.add(getJScrollPane(), gridBagConstraints);
         jContentPane.add(getJPanel(), gridBagConstraints5);
      }
      return jContentPane;
   }

   /**
    * This method initializes jScrollPane	
    * 	
    * @return javax.swing.JScrollPane	
    */
   private JScrollPane getJScrollPane() {
      if (jScrollPane == null) {
         jScrollPane = new JScrollPane();
         jScrollPane.setViewportView(getJTable());
      }
      return jScrollPane;
   }

   /**
    * This method initializes jTable	
    * 	
    * @return javax.swing.JTable	
    */
   private JTable getJTable() {
      if (jTable == null) {
         jTable = new JTable(getModel());
      }
      return jTable;
   }

   /**
    * This method initializes jButton	
    * 	
    * @return javax.swing.JButton	
    */
   private JButton getJButton() {
      if (jButton == null) {
         jButton = new JButton();
         jButton.setText("OK");
         jButton.setMinimumSize(jButton.getPreferredSize());
         jButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
               
               ChoosProdsInDialogv2.this.setVisible((false));
               
            }
            
         });
      }
      return jButton;
   }

   public Vector<Product> getChoosenProds() {
      if(choosenProds == null) choosenProds=new Vector();
      choosenProds.clear();
      for(int i = 0; i < prods.size();i++){
         if(bools.get(i).booleanValue())
            choosenProds.add(prods.get(i));
      }
      return choosenProds;
   }
   
   private myTableModel getModel(){
      if(model == null) model = new myTableModel();
      return model;
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
            Boolean B = (Boolean) arg0;
           bools.set(row,B);
           if(row == printedNameOffset && !B.booleanValue()){
              for(int i = 0; i < bools.size();i++){
                 if(bools.get(i).booleanValue()){
                    printedNameOffset=i;
                    this.fireTableDataChanged();
                 }
              }
           }
           if(B.booleanValue() && ! bools.get(printedNameOffset)){
              printedNameOffset=row;
              this.fireTableDataChanged();
           }
           
         case 2 :
            if(bools.get(row).booleanValue()){
               printedNameOffset = row;
               this.fireTableDataChanged();
            }
            
         //default : throw new PrimaryError("DupaDupaDupa " + col);
             
         }
      }
      
      
      
   }

   /**
    * This method initializes jPanel	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getJPanel() {
      if (jPanel == null) {
         GridLayout gridLayout = new GridLayout();
         gridLayout.setRows(1);
         gridLayout.setHgap(10);
         gridLayout.setVgap(10);
         gridLayout.setColumns(2);
         jPanel = new JPanel();
         jPanel.setLayout(gridLayout);
         jPanel.add(getJButton(), null);
         jPanel.add(getJButton1(), null);
      }
      return jPanel;
   }

   /**
    * This method initializes jButton1	
    * 	
    * @return javax.swing.JButton	
    */
   private JButton getJButton1() {
      if (jButton1 == null) {
         jButton1 = new JButton();
         jButton1.setText("WyjdŸ");
         jButton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
               wantsWxit=true;
               setVisible(false);
            }});;
      }
      return jButton1;
   }
   


}
