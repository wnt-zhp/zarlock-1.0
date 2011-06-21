/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzProduct;

import java.io.Serializable;
import java.util.Map;
import java.util.Vector;

import zzDataBase.Expenditure;
import zzEx.ZZEx;
import zzEx.ZZIllegalDateExceptionEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZIoEx;
import zzId.ExpenditureId;
import zzId.ProdId;
import zzMyDate.MyDate;

/**
 * Interface of product <br>
 * all setXXX functions set proper variable. They all throw
 * ZZAlreadyInitializedEx to assert that set functions wont change value of
 * already initialized field <br>
 * all change functions change proper variables. They all throw
 * ZZIllegalNotInitializedEx to assert that they wont be used to set variables.
 * They Also throw ZZIllegalEx when changing this variables would cause some
 * internal inconsistences fe. change would cause you to have -1 peaches. <br>
 * get functions return a copy proper variable. They do not throw anything. So
 * it is not possiblr to acces contents of class via getXXX
 * 
 * @author jb
 * 
 */
public interface Product extends Serializable, Comparable<Product>, ExpenditureHolder {
  void setName(String _name) throws ZZEx;
  
  public int isExpenditureChangeOk(Expenditure foo, float newQuant, boolean b) throws ZZIllegalEx;
  
  public String[] isExpenditureChangeOkStr(Expenditure foo, float newQuant) throws ZZIllegalEx;
  
  public Vector<Expenditure> getExpendituresNotTheirIds();
  
  int isExpenditureOK(Expenditure check) throws ZZIllegalEx;
  public int isExpenditureOK(Expenditure foo,boolean b) throws ZZIllegalEx;
  public String[] isExpenditureOKStr(Expenditure foo) throws ZZIllegalEx;

  void setId(ProdId _id) throws ZZIllegalEx;
  //void changeId(ProdId _id) throws ZZIllegalEx;
  ProdId getId() throws ZZIllegalEx;

  void setExpiryDate(MyDate _date) throws ZZIllegalEx;
  void changeExpiryDate(MyDate _date) throws ZZIllegalEx;
  MyDate getExpiryDate() throws ZZIllegalEx;

  void setPrice(float price) throws ZZIllegalEx;
  void changePrice(float price) throws ZZIllegalEx;
  float getPrice() throws ZZIllegalEx;

  /**
   * toString and parseProduct are supposed to be coherent - product.equals((new
   * Product).parseProduct(product.toString())); is supposed to be true
   * 
   * @return
   */
  String toString();
  
  /**
   * Shuld check whereas operation is proper, i.e.:
   * if date >= BookingDate
   * if date < ExpiryDate
   * if _quantity <= CurrentQuantity
   * @param _quantity
   * @param _date
   * @param _tytulem
   * @return
   * @throws ZZIllegalEx
   */
  
  ExpenditureId performExpenditure(float _quantity, MyDate _date, String _tytulem ) throws ZZIllegalEx;
  ExpenditureId performExpenditure(Expenditure e) throws ZZIllegalEx;
  void cancelExpenditure(ExpenditureId whichOne) throws ZZIllegalEx/*, ZZInternalNoSuchEntryEx*/;  
  Expenditure getExpenditure(ExpenditureId i)  throws ZZIllegalEx ;
  Vector<Map.Entry<ExpenditureId,Expenditure> > 
    getExpenditures() throws ZZIllegalEx;
  
  void changeExpenditure(ExpenditureId id, float _quantity, 
      MyDate _date, String _tytulem ) throws ZZIllegalEx;
  
  void changeUncheckedExpenditure(ExpenditureId id, float _quantity, 
      MyDate _date, String _tytulem );
  
  ExpenditureId performUncheckedExpenditure(float _quantity, MyDate _date, String _tytulem );
  void cancelUncheckedExpenditure(ExpenditureId whichOne);  
  Expenditure getUncheckedExpenditure(ExpenditureId i);
  Iterable<Map.Entry<ExpenditureId,Expenditure> >
    getUncheckedExpenditures();
  

  //void addPackagingType(String newPackaging);

  void removePackagingType(String Packaging)
      throws ZZIllegalNotEnoughPrivelagesEx;

  //void addUnitType(String newUnit);

  void removeUnitType(String Unit) throws ZZIllegalNotEnoughPrivelagesEx;

  // Much work - no effect
  // void addNewPackagingMappings(Map<Integer, String> Packagings);

  // void addNewUnitMappings(Map<Integer, String> Packagings);

  //Map<Integer, String> getPackagingMappings();

  //Map<Integer, String> getUnitMappings();

  /**
   * toString and parseProduct are supposed to be coherent - product.equals((new
   * Product).parseProduct(product.toString())); is supposed to be true
   * 
   * @return
   */
  void parseProduct(String _line) throws ZZIoEx;

  //void setFirstGeneratedId(ProdId id) throws ZZIllegalNotEnoughPrivelagesEx;

  void setSQuantity(float _quant) throws ZZIllegalEx;

  /**
   * This function should chceck whereas this is a proper if
   * 
   * @param _id
   * @throws ZZIoEx
   */
  void setUnitId(int _id) throws ZZIllegalEx;

  /**
   * Finds an id of this Packaging if not found it throws an Exception
   * 
   * @param _Packaging
   * @throws ZZIoEx
   */
  void setUnit(String _Unit) throws ZZIllegalEx;

  void setDescription(String desc) throws ZZIllegalEx;

  /**
   * Note that thus it is an Factura No in fact this fun gets a String
   * 
   */
  void setFacturaNo(String _No) throws ZZIllegalEx;

  /**
   * This function should chceck whereas this is a proper if
   * 
   * @param _id
   * @throws ZZIoEx
   */
  void setPackagingId(int _id) throws ZZIllegalEx;

  /**
   * Finds an id of this Packaging if not found it throws an Exception
   * 
   * @param _Packaging
   * @throws ZZIoEx
   */
  void setPackaging(String _Unit) throws ZZIllegalEx;

  void setDateOfBooking(MyDate _bookingBate) throws ZZIllegalEx;

  void changeName(String _name) throws ZZIllegalEx;

  void changeSQuantity(float _quant) throws ZZIllegalEx;

  /**
   * This function should chceck whereas this is a proper if
   * 
   * @param _id
   * @throws ZZIoEx
   */
  void changeUnitId(int _id) throws ZZIllegalEx;

  /**
   * Finds an id of this Packaging if not found it throws an Exception
   * 
   * @param _Packaging
   * @throws ZZIoEx
   */
  void changeUnit(String _Unit) throws ZZIllegalEx;

  void changeDescription(String desc) throws ZZIllegalEx;

  /**
   * Note that thus it is an Factura No in fact this fun gets a String
   * 
   */
  void changeFacturaNo(String _No) throws ZZIllegalEx;

  /**
   * This function should chceck whereas this is a proper if
   * 
   * @param _id
   * @throws ZZIoEx
   */
  void changePackagingId(int _id) throws ZZIllegalEx;

  /**
   * Finds an id of this Packaging if not found it throws an Exception
   * 
   * @param _Packaging
   * @throws ZZIoEx
   */
  void changePackaging(String _Unit) throws ZZIllegalEx;

  void changeDateOfBooking(MyDate _BookingDate) throws ZZIllegalEx;

  
  
  String getName() throws ZZIllegalEx;

  float getSQuantity() throws ZZIllegalEx;

  int getUnitId() throws ZZIllegalEx;

  String getUnit() throws ZZIllegalEx;

  String getDescription() throws ZZIllegalEx;

  String getFacturaNo() throws ZZIllegalEx;

  int getPackagingId() throws ZZIllegalEx;

  String getPackaging() throws ZZIllegalEx;

  MyDate getDateOfBooking() throws ZZIllegalEx;

  float getCurrQ() throws ZZIllegalEx;

  

  // This function is protected in class SimpleProduct

  boolean isExpired(MyDate _today) throws ZZIllegalEx;

  long toExpiry(MyDate _today) throws ZZIllegalEx;

  /**
   * This function is supposed to compare only id's of Products. Good
   * implementation would be: return this.getId()- toBeCompared.getId(); If one
   * of the products is not has not initialized id. Should return 0. This is
   * because in some cases it will prevent putting uninitialized object
   * somewhere
   * 
   * @param toBeCompared
   * @return
   */
  int compareTo(Product toBeCompared);

};
