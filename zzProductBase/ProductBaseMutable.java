/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzProductBase;

import zzEx.*;
import zzId.*;
import zzProduct.Product;


public interface ProductBaseMutable extends ProductBaseImmutable {
  
  /**
   * All these functions are supposed to make a new entry to the database
   * @param _prod Product that is to be added
   * @throws ZZInternalDuplicateEntryEx thrown if uppon adding this product a
   * dupliate entry would occour
   */
  void addProduct(Product _prod) throws ZZIllegalEx, ZZInternalDuplicateEntryEx;
  
  /**
   * All these functions are supposed to make a new entry to the database
   * @param _product String that is supposed to contain data of product
   * in certain formatting.
   */
  void addProduct(String _product) throws ZZIllegalEx, ZZInternalDuplicateEntryEx, ZZIoBadFormatEx, ZZIllegalNotEnoughPrivelagesEx;
  
  /**
   * Removes product having id specified by argument
   * @param id
   */
  void removeProduct(ProdId id) throws ZZIllegalEx;
  
  /**
   * Removes from this all product form foo.
   * 
   * @param foo Base consisting of products to be removed from data base
   */
  //void removeProducts(ProductBaseMutable foo) throws ZZIllegalNotEnoughPrivelagesEx, ZZIllegalEx ;
  
  /*int Expenditure(Product _product, int quantity, MyDate date, String tytulem)
  throws ZZInternalNoSuchEntryEx, ZZIllegalEx;*/
  
  /*int Expenditure(ProdId _productNo, int quantity, MyDate date, String Tytulem)
  throws ZZInternalNoSuchEntryEx, ZZIllegalEx;*/
}
