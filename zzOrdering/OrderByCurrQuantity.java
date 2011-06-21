/*
 * Created on 2006-03-26
 * Author jb
 *
 */
package zzOrdering;

import zzEx.ZZIllegalEx;
import zzProduct.Product;

public class OrderByCurrQuantity extends FieldOrdering {
  public int compareMe(Product arg0, Product arg1) throws ZZIllegalEx {
    if (arg0.getCurrQ() == arg1.getCurrQ()){
      return 0;
    }
    if (arg0.getCurrQ() < arg1.getCurrQ()){
      return 1;
    }
    return -1;
  }
}
