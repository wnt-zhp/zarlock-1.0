package zzGUIElements.zzGuiProductElements; 

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import zzDataBase.Expenditure;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzGUIElements.MainFrame;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable;

public class ProductTable extends JPanel{
   private JTable table;
   JScrollPane pane;
   public ProductTableModel model;
   private JFileChooser chooser = null;
   
   public void setBase(ProductBaseImmutable pb, Component ovner){
      setModel (new ProductTableModel(pb, ovner));
      setTable(model);
   }
   
   private void initialize(){
      TableColumn expenditureColumn = getTable().getColumnModel().getColumn(ProductTableModel.EXPENDITURESCOLUMNNUMBER);
      expenditureColumn.setCellRenderer(new simpleRenderer());
      expenditureColumn.setCellEditor(new simpleRenderer());
      
      //expenditureColumn.setPreferredWidth(400);
      //table.setRowHeight(25);
      add(pane = new JScrollPane (getTable()));
      addMouseListener(new mouseListener());
      
   }
   
   public ProductTable() {
      super();
      
   }
   
   public ProductTable(ProductBaseImmutable base, Component owner) {
      super(new GridLayout(1,1));
      setModel(new ProductTableModel(
            base,
            owner
      ));
      setTable(getModel() );  
      initialize();
   }
   
   public void fireDataChanged(){
      model.fireTableDataChanged();
   }
   
   public void setPreferredSize(Dimension dim){
      getTable().setPreferredScrollableViewportSize(dim);
   }
   
   public Dimension getPreferredSize(){
      return getTable().getPreferredScrollableViewportSize();
   }
   
   private void setModel(ProductTableModel model) {
      //TableSorter sorter = new TableSorter(model);
      //sorter.setTableHeader(table.getTableHeader());
      this.model = model;
   }
   
   public ProductTableModel getModel() {
      if(model==null) throw new NullPointerException();
      return model;
   }
   
   private class simpleRenderer extends AbstractCellEditor implements 
   TableCellRenderer, 
   TableCellEditor{
      
      JButton button;
      Product currentProduct;
      
      simpleRenderer(){
         super();
         button = new JButton("Wyjscia z mag.");   
         button.addActionListener(
               new ActionListener(){
                  ProductExpendituresDialogv2 dialog = 
                     new ProductExpendituresDialogv2 (StaticContent.sc.getUppmostFrame());
                  public void actionPerformed(ActionEvent arg0) {
                     dialog.dispaly(currentProduct);					
                  }      		
               }
         );
      }
      
      public Component getTableCellRendererComponent(JTable arg0, Object arg1, 
            boolean arg2, boolean arg3, int arg4, int arg5) {
         return button;
      }
      
      public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
         currentProduct = (Product)arg1;
         return button;
      }
      
      public Object getCellEditorValue() {
         return currentProduct;
      }
      
   }
   private JPopupMenu menu = null;
   
   private JPopupMenu getMenu() {
      if(menu == null){
         menu = new JPopupMenu();
         menu.add(getZmienProdukt());
         menu.add(getUsunProduct());
         menu.add(getZapiszStan());
         menu.add(getDrukujRaport());
         
      }
      return menu;
   }
   
   JMenuItem getZmienProdukt(){
      JMenuItem item = new JMenuItem();
      item.setText("Zmien wybrany produkt");
      item.addActionListener(
            new ActionListener(){
               
               public void actionPerformed(ActionEvent arg0) {
                  if(getTable() == null) return;
                  ListSelectionModel listModel = getTable().getSelectionModel();
                  int rowMin = listModel.getMinSelectionIndex();
                  int rowMax = listModel.getMaxSelectionIndex();
                  int row=-1;
                  if(rowMax != rowMin){
                     JOptionPane.showMessageDialog(
                           StaticContent.sc.getUppmostFrame(),
                           "Wybierz jden¹ kolumne",
                           "Bl¹d",
                           JOptionPane.WARNING_MESSAGE,
                           null);
                     return;
                  }else{
                     row = rowMax;
                  }
                  Product p = (Product) getModel().getValueAt(row,ProductTableModel.EXPENDITURESCOLUMNNUMBER);
                  try{
                     MainFrame frame = (MainFrame) StaticContent.sc.getUppmostFrame(); //Oh holy god it the heavens its blastphemy :P
                     frame.getProductGeneratingDialog().display(p);
                  }catch(Exception e){
                     e.printStackTrace();
                     JOptionPane.showMessageDialog(
                           ProductTable.this,
                           "A jednak nie wysz³o :( Wo³aj Jacka "+
                           "\nDebugInfo:ProductTable popupmenu\n"+
                           e.getClass().getName() + "\n" + 
                           e.getMessage(),
                           "B³¹d",
                           JOptionPane.WARNING_MESSAGE,
                           null);
                  };
                  
               }
            });
      return item;
   }
   
   JMenuItem getUsunProduct(){
      JMenuItem item = new JMenuItem();
      item.setText("Usuñ wybrany Produkt");
      item.addActionListener(new ActionListener(){
         
         public void actionPerformed(ActionEvent arg0) {
            if(getTable() == null) return;
            ListSelectionModel listModel = getTable().getSelectionModel();
            int rowMin = listModel.getMinSelectionIndex();
            int rowMax = listModel.getMaxSelectionIndex();
            int row=-1;
            if(rowMax != rowMin){
               JOptionPane.showMessageDialog(
                     StaticContent.sc.getUppmostFrame(),
                     "Wybierz jden¹ kolumne",
                     "Bl¹d",
                     JOptionPane.WARNING_MESSAGE,
                     null);
               return;
            }else{
               row = rowMax;
            }
            Product p = (Product) getModel().getValueAt(row,ProductTableModel.EXPENDITURESCOLUMNNUMBER);
            String options[] = new String[]{
                  "Tak, chcê",
                  "Nie, nie chcê"
            };
            int result;
            try {
               result = JOptionPane.showOptionDialog(
                     StaticContent.sc.getUppmostFrame(),
                     "Czy jesteœ pewien ¿e chcesz usun¹æ produkt o nazweie:\n"+
                     p.getName(),
                     "Pytanie",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     options,
                     options[1]            
               );
            }catch (ZZIllegalEx e1) {
               result = JOptionPane.showOptionDialog(
                     StaticContent.sc.getUppmostFrame(),
                     "Czy jesteœ pewien ¿e chcesz usun¹æ produkt o nazweie:\n"+
                     "Brak nazwy",
                     "Pytanie",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     options,
                     options[1]
               );
               e1.printStackTrace();
            }
            if (result == JOptionPane.YES_OPTION){
               try {
                  StaticContent.sc.getDataBaseMutable().removeProduct(p.getId());
               } catch (ZZIllegalEx e) {
                  JOptionPane.showMessageDialog(
                        StaticContent.sc.getUppmostFrame(),
                        "Bl¹d\n" + e.getClass().getName() + 
                        "\n message" + e.getMessage(),
                        "B³¹d",
                        JOptionPane.ERROR_MESSAGE,
                        null
                  );
                  e.printStackTrace();
               }
               getModel().fireTableRowsUpdated(row,row);
            }
         }
         
      });
      return item;
   }
   
   JMenuItem getDrukujRaport(){
      JMenuItem item = new JMenuItem();
      item.setText("Zapisz raport");
      item.setToolTipText("Mo¿esz zaznaczyæ wiêcej ni¿ jeden produkt");
      
      item.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent arg0) {
            if(getTable() == null){
               System.out.println("OooOOoooOOOooooooOOOO");
               return;
            }
            ListSelectionModel listModel = getTable().getSelectionModel();
            int rowMin = listModel.getMinSelectionIndex();
            int rowMax = listModel.getMaxSelectionIndex();
            int lp = 0;
            for(int row = rowMin; row<=rowMax;++row){
               Product p = (Product) getModel().getValueAt(row,ProductTableModel.EXPENDITURESCOLUMNNUMBER);
               String name;
               try {
                  name = p.getName();
               } catch (ZZIllegalEx e) {
                  name = "unnamedProduct";
               }
               String id;
               try {
                  id = p.getId().toString();
               } catch (ZZIllegalEx e) {
                  id = "nizenanyId";
               }
               
               String price;
               float priceF=0;
               try {
                  price = String.valueOf(p.getPrice());
                  priceF=0;
               } catch (ZZIllegalEx e) {
                  price = "NieznanaCena";
               }
               String faktura;
               try {
                  faktura = p.getFacturaNo();
               } catch (ZZIllegalEx e) {
                  faktura = "Nieznany numer";
               }
               
               String dataK;
               try {
                  dataK = p.getDateOfBooking().toString();
               } catch (ZZIllegalEx e) {
                  dataK = "Nieznany data ksiêgowania";
               }
               
               String sQuant;
               float sQuantF=0;
               try {
                  sQuantF = p.getPrice();
                  sQuant = String.valueOf(p.getSQuantity());                  
               } catch (ZZIllegalEx e) {
                  sQuant = "nie wiadomo ile";
               }
               
               String wartosc; 
               try{
                  wartosc = String.valueOf(p.getSQuantity() * p.getPrice());
               }catch(ZZIllegalEx e){
                  wartosc = "??";
               }
               
               File saveTo = new File(StaticContent.sc.getDocumentsFolder(), name + "_" + id +  "_" + price + ".csv");
               int foo=0;
               BufferedWriter wr=null;
               
               while(saveTo.exists()){ 
                  saveTo = new File(StaticContent.sc.getDocumentsFolder(), name + "_" + id + "_" +  price + "_" + foo++  + ".csv" );
               }
               
               try {
                  wr = new BufferedWriter( new FileWriter(saveTo));
               } catch (IOException e1) {
                  JOptionPane.showMessageDialog(
                        StaticContent.sc.getUppmostFrame(),
                        "B³¹d zapisu:\n" + e1.getClass().getName() + "\n" + e1.getMessage() ,
                        "Bl¹d",
                        JOptionPane.WARNING_MESSAGE,
                        null);
                  e1.printStackTrace();
               }
               
               try {
                  wr.write(",KARTOTEKA MAGAZYNOWA" + "\n");
                  wr.write(",ILOŒCIOWO-WARTOŒCIOWA" + "\n");
                  wr.write("," + "\n");
                  wr.write("Lp," + "Data," + "Symbol i nr. dowodu," + "Przychód, ," + "Rozchód, ," + "Stan, ,"+ "\n"  );
                  wr.write(", " + ", " + ", " +  "iloœæ, wartoœæ," + "iloœæ, wartoœæ," +"iloœæ, wartoœæ,"  + "\n" );
                  wr.write("1, " + "2, " + "3, " +  "4,5," + "6,7," +"8, 9,"  + "\n" );
                  wr.write("" + ++lp + ", " + dataK + ", " + faktura +", " + sQuant + ",  " + wartosc + ", " + "---," + "---, " + sQuant + ",  " + wartosc + "\n");
                  float cQuant = sQuantF;
                  for(Expenditure e: p.getExpendituresNotTheirIds()){
                     cQuant-=e.ile;
                     wr.write("" + ++lp + ", " + e.date.toString() + ", " + ", " + "---" + ", " + "---" + "," + e.ile  + ", " + e.ile * priceF + ", " + "" + cQuant + " ," + cQuant*priceF  + "\n");
                  }
                  
               } catch (IOException e) {                  
                  
                  JOptionPane.showMessageDialog(
                        StaticContent.sc.getUppmostFrame(),
                        "B³¹d zapisu:\n" + e.getClass().getName() + "\n" + e.getMessage() ,
                        "Bl¹d",
                        JOptionPane.WARNING_MESSAGE,
                        null);
                  e.printStackTrace();
                  
               }
               try {
                  wr.flush();
                  wr.close();
               } catch (IOException e) {
                  JOptionPane.showMessageDialog(
                        StaticContent.sc.getUppmostFrame(),
                        "B³¹d zapisu:\n" + e.getClass().getName() + "\n" + e.getMessage() ,
                        "Bl¹d",
                        JOptionPane.WARNING_MESSAGE,
                        null);
                  e.printStackTrace();
               }
               
            }
            
         }}); 
      
      return item;
   }
   
   JMenuItem getZapiszStan(){
      JMenuItem item = new JMenuItem();
      item.setText("Zapisz stan mag.");
      item.addActionListener(new ActionListener(){

         public void actionPerformed(ActionEvent arg0) {
            int result = getChooser().showDialog(ProductTable.this, "Export");
            
            if(result!=JFileChooser.APPROVE_OPTION) return;
            if(getTable() == null){
               System.out.println("OooOOoooOOOooooooOOOO");
               return;
            }
            ListSelectionModel listModel = getTable().getSelectionModel();
            int rowMin = listModel.getMinSelectionIndex();
            int rowMax = listModel.getMaxSelectionIndex();
            int lp = 0;
            BufferedWriter wr=null;
            try {
               wr = new BufferedWriter(new FileWriter(getChooser().getSelectedFile()));
               wr.write("Nazwa, jest w mag, jednostka, opis, cena\n");
               for(int row = rowMin; row<=rowMax; row++){
                  Product p = (Product) getModel().getValueAt(row,ProductTableModel.EXPENDITURESCOLUMNNUMBER);
                 
                  try {
                     wr.write(p.getName() +", " + p.getCurrQ() + ", " + p.getUnit() + ", " +  p.getDescription().replace(",", "").replace(";", "") +", "+ p.getPrice() + "\n");
                  } catch (ZZIllegalEx e) {
                     wr.write("B³¹d,,,");
                     e.printStackTrace();
                  }
               }
            } catch (IOException e) {
               e.printStackTrace();
            }finally{
               if(wr!=null){
                  try{
                  wr.flush();
                  wr.close();
                  }catch(Exception e){
                     
                  }
               }
            }
         }
      });
     return item;
   }
   
   
   
   void setTable(TableModel model) {
      
//    JTable table = new JTable(new MyTableModel());          //OLD
      JTable table = new JTable(getModel()){
         
         @Override
         public String getToolTipText(MouseEvent e) {
            String tip = null;
            java.awt.Point p = e.getPoint();
            int rowIndex = rowAtPoint(p);
            int colIndex = columnAtPoint(p);
            int realColumnIndex = convertColumnIndexToModel(colIndex);
            
            tip = this.getModel().getValueAt(rowIndex,realColumnIndex).toString();
            
            if(realColumnIndex==ProductTableModel.EXPENDITURESCOLUMNNUMBER){
               return super.getToolTipText(e);
            }
            
            return tip;
         }
         
      };             //NEW
      
      
      ListSelectionModel listModel = table.getSelectionModel();
      listModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      table.addMouseListener(new mouseListener());
      this.table = table;
   }
   
   JTable getTable() {
      return table;
   }
   
   private class mouseListener implements MouseListener {
      
      public void mouseClicked(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mousePressed(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseReleased(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseEntered(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseExited(MouseEvent arg0) {
         maybeShowPopup(arg0);
      }
      
      private void maybeShowPopup(MouseEvent arg0) {
         System.out.println("Menu1");
         if (!arg0.isPopupTrigger())
            return;
         System.out.println("Menu2");
         getMenu().show(
               ProductTable.this,
               arg0.getX(),
               arg0.getY())
               ;
         
      }
   }

   public JFileChooser getChooser() {
      if(chooser == null){
         chooser = new JFileChooser (StaticContent.sc.getDocumentsFolder());
        FileFilter filter = new FileFilter(){

         @Override
         public boolean accept(File arg0) {
            if(arg0.isDirectory()) return true;
            if(arg0.getName().endsWith(",csv")) return true;
            return false;
         }

         @Override
         public String getDescription() {
            return "Pliki .csv";
         }
           
        };
        
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
      }
      return chooser;
   }
   
   
}
