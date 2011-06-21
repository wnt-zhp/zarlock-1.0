/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzOrdering;

import zzProduct.Product;

public abstract class FieldOrdering implements Ordering {

  final public int compare(Product arg0, Product arg1) {
    try{
      return compareMe(arg0, arg1);
    }catch(Exception e){}
    return -1;    
  }
  
  abstract int compareMe(Product arg0, Product arg1) throws Exception;

}
