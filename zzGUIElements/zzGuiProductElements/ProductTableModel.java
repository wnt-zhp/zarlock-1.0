package zzGUIElements.zzGuiProductElements;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


import zzDataBase.StaticContent;
import zzDataBase.StaticContent.myActionListener;
import zzEx.ZZIllegalEx;
import zzId.ProdId;
import zzOrdering.OrderByName;
import zzOrdering.Ordering;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable;
import zzProductBase.ProductBaseImmutable.ProductsChangedListener;
import zzQuery.Query;
import zzQuery.QueryListener;
import zzUser.Priveleges;
import zzUser.User;

public class ProductTableModel 
	extends AbstractTableModel{
  
  private ProductBaseImmutable base = null;
  private ProductBaseImmutable baseFiltered = null;

  private List<Product> productVect = null;
  private Component owner = null;
  private ProductTableModel THIS = this;
  
  
  
  public ProductTableModel() {
    super();
    this.setBase(StaticContent.sc.getDataBaseMutable());
    this.setOwner(StaticContent.sc.getUppmostFrame());     
    addStaticContentsListeners();
    fireTableDataChanged();
  }

  
  public ProductTableModel(ProductBaseImmutable base, Component owner) {
    super();
    this.setBase(base);
    this.setOwner(owner);
    fireTableDataChanged();
  }

  public int getRowCount() {
    return (int) productVect.size();
  }

  public int getColumnCount() {
    return colNames.size();
  }
  
  public String getColumnName(int col) {
    return colNames.get(new Integer(col));
}
  
  public final void refresh(){
    fireTableDataChanged();
  }

  public Object getValueAt(int row, int column) {
    switch(column){
    case NAMECOLUMNNUMBER: 
      try{
        return productVect.get(row).getName();
      }
      catch(ZZIllegalEx e){
        return notSetMessage;
      }
    case CURRENTQUANTITYCOLUMNNUMBER:
      try{
        return String.valueOf(productVect.get(row).getCurrQ());
      }
      catch(ZZIllegalEx e){
        return notSetMessage;
      }
     case UNITCOLUMNNUMBER:
       try{
         return productVect.get(row).getUnit();
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case PRICECOLUMNNUMBER:
       try{
         return  String.valueOf((float)(StrictMath.round(productVect.get(row).getPrice()*100))/100);
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case PACKAGINGCOLUMNNUMBER:
       try{
         return productVect.get(row).getPackaging();
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case EXPENDITURESCOLUMNNUMBER:
      return productVect.get(row);
     case IFEXPIREDCOLUMNNUMBER:
         try{
           if ( productVect.get(row).isExpired(StaticContent.sc.getToday()))
             return "TAK!!";
           return "NIE";
         }
         catch(ZZIllegalEx e){
           return notSetMessage;
         }
     case DESCRIPTIONCOLUMNNUMBER:
       try{
         return productVect.get(row).getDescription();
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case BOOKINGDATECOLUMNNUMBER:
       try{
         return productVect.get(row).getDateOfBooking().toString();
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case EXPIRYDATECOLUMNNUMBER:
       try{
         return productVect.get(row).getExpiryDate().toString();
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }
     case STARTINGQUANTITYCOLUMNNUMBER:
       try{
         return String.valueOf(productVect.get(row).getSQuantity());
       }
       catch(ZZIllegalEx e){
         return notSetMessage;
       }     
    }
    return null;
  }
  
  public Class<?> getColumnClass(int c) {
    return getValueAt(0, c).getClass();
}

  public void setBase(ProductBaseImmutable base) {
    if(this.base!=null) removeListeners();
    removeStaticContentsListeners();
    this.base = base;
    addListeners();
    setBaseFiltered(base);
    fireTableDataChanged();
  }
  
  private void setBaseFiltered(ProductBaseImmutable baseFiltered) {
	  this.baseFiltered = baseFiltered;
	  productVect = new Vector<Product>( baseFiltered.getSorted());
	  System.out.println("baseFilyered size " + getBaseFiltered().getNumberOfElems() + "base " + getBase().getNumberOfElems());
	  fireTableDataChanged();
  }

  public boolean isCellEditable(int row, int col){
	  if(col==EXPENDITURESCOLUMNNUMBER) return true;
    return false;/*
    if(User.getPriveleges()==Priveleges.BROWSE) return false;
    if(User.getPriveleges()==Priveleges.USER 
        && col == EXPENDITURESCOLUMNNUMBER) return true;
    if(User.getPriveleges()==Priveleges.ROOTUSER 
        && col == EXPENDITURESCOLUMNNUMBER) return true;
    return true;*/
  }

  public ProductBaseImmutable getBase() {
    return base;
  }


  public void setOwner(Component owner) {
    this.owner = owner;
  }


  public Component getOwner() {
    return owner;
  }

  
  public final static int NAMECOLUMNNUMBER = 0;
  public final static int CURRENTQUANTITYCOLUMNNUMBER =1;
  public final static int UNITCOLUMNNUMBER = 2;
  public final static int PRICECOLUMNNUMBER = 3;
  public final static int PACKAGINGCOLUMNNUMBER = 4;
  public final static int EXPENDITURESCOLUMNNUMBER = 5;
  public final static int IFEXPIREDCOLUMNNUMBER = 6;
  public final static int DESCRIPTIONCOLUMNNUMBER = 7;
  public final static int BOOKINGDATECOLUMNNUMBER = 8;
  public final static int EXPIRYDATECOLUMNNUMBER = 9;
  public final static int STARTINGQUANTITYCOLUMNNUMBER = 10;

  public static String notSetMessage = "Nie ustawiono";
  public static Map<Integer,String> allColNames;
  static{
	  allColNames = new TreeMap<Integer,String>();
	  allColNames.put(new Integer(NAMECOLUMNNUMBER),"Nazwa");
	  allColNames.put(new Integer(CURRENTQUANTITYCOLUMNNUMBER),"Ilosc");
	  allColNames.put(new Integer(UNITCOLUMNNUMBER),"Jednostka");
	  allColNames.put(new Integer(PRICECOLUMNNUMBER),"Cena");
	  allColNames.put(new Integer(PACKAGINGCOLUMNNUMBER),"Opakowanie");
	  allColNames.put(new Integer(EXPENDITURESCOLUMNNUMBER),"Ksiêga wyjœæ");
	  allColNames.put(new Integer(IFEXPIREDCOLUMNNUMBER),"Przeterminoany");
	  allColNames.put(new Integer(DESCRIPTIONCOLUMNNUMBER),"Opis");
	  allColNames.put(new Integer(BOOKINGDATECOLUMNNUMBER),"Data Ksiêgowania");
	  allColNames.put(new Integer(EXPIRYDATECOLUMNNUMBER),"Data przydatnoœci do spo¿ycia");
     allColNames.put(new Integer(STARTINGQUANTITYCOLUMNNUMBER),"Iloœæ pocz¹tkowa");    
  };

  public Map<Integer,String> colNames = new TreeMap<Integer,String>(allColNames);
  

  
  private void addStaticContentsListeners(){
    StaticContent.sc.addListener(dateChanged);  
    StaticContent.sc.addListener(dataBaseChanged);
  }
  
  private void removeStaticContentsListeners(){
    StaticContent.sc.removeListener(dateChanged);  
    StaticContent.sc.removeListener(dataBaseChanged);
  }
  
  /// G³upie listenery ///
  
  
  private ProductBaseImmutable.ProductsChangedListener removedListener =
    new ProductsChangedListener(){
      public void actionPerformed(Vector<ProdId> ids) {
        productVect = base.getSorted();
        fireTableDataChanged();
      }    
  };
   
  private ProductBaseImmutable.ProductsChangedListener addedListener
    = new ProductBaseImmutable.ProductsChangedListener(){
      public void actionPerformed(Vector<ProdId> ids) {
        productVect = base.getSorted();
        fireTableDataChanged();
      }
  };
  
  private ProductBaseImmutable.SortedListener sortedListener 
    = new ProductBaseImmutable.SortedListener(){
      public void actionPerformed(Vector<Ordering> orderings) {
        productVect = base.getSorted();
        fireTableDataChanged();
      }   
  };
  
  private ProductBaseImmutable.LoadedLisntener loadedListener
    = new ProductBaseImmutable.LoadedLisntener(){
      public void actionPerformed() {
        productVect = base.getSorted();
        fireTableDataChanged();        
      }      
  };
  
  private ProductBaseImmutable.FilteredListener filteredListener 
    = new ProductBaseImmutable.FilteredListener(){
      public void actionPerformed(ProductBaseImmutable filtered, Vector<Query> query) {
       setBaseFiltered(filtered);        
      }       
  };
  
  
  private StaticContent.myActionListener dateChanged = new myActionListener(){
    public void actionPerformed(String command) {
      if(command.equals(StaticContent.dateChanged)){
        fireTableChanged(new TableModelEvent(
            THIS,
            0,
            getRowCount(),
            IFEXPIREDCOLUMNNUMBER,
            TableModelEvent.UPDATE
          )
        );
      }            
    }
  };
  private StaticContent.myActionListener dataBaseChanged = new myActionListener(){
    public void actionPerformed(String command) {
      if(command.equals(StaticContent.databaseChanged)){
        base = StaticContent.sc.getDataBaseMutable();
        productVect= new Vector<Product> (base.getSorted(new OrderByName()));
        fireTableDataChanged();
      }            
    }          
  };        
  
  private void addListeners(){
    base.addProductAddedListener(addedListener);
    base.addProductRemovedListener(removedListener);
    base.addSortedListener(sortedListener);
    base.addLoadedListener(loadedListener);
    base.addFilteredListener(filteredListener);
  }
  
  private void removeListeners(){
    //assert false : "Dzia³a!";
    // (addedListener!= null) : "Tu cie mam";
    base.removeProductAddedListener(addedListener);
    base.removeProductRemovedListener(removedListener);
    base.removeSortedListener(sortedListener);
    base.removeLoadedListener(loadedListener);
    base.removeFilteredListener(filteredListener);
  }

  public void resetQuerries(){
	  setBaseFiltered(getBase());
  }
  
  public void performQuerries(Vector<Query> querries){
	  setBaseFiltered(getBase().performQuery(querries.toArray(new Query[]{})));
  }
  

  
  
  private ProductBaseImmutable getBaseFiltered() {
	  return baseFiltered;
  }  
  
  
   
}
