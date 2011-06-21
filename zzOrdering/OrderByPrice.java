/*
 * Created on 2006-04-18
 * Author jb
 *
 */
package zzOrdering;


import zzEx.ZZIllegalEx;
import zzProduct.Product;

public class OrderByPrice extends FieldOrdering {

  public int compareMe(Product arg0, Product arg1) throws ZZIllegalEx {
    
      if(arg0.getPrice() < arg1.getPrice() ) return -1;
      if(arg0.getPrice() > arg1.getPrice() ) return 1;
    
    return 0;
  }

}
