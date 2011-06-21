/*
 * Created on 2006-03-26
 * Author jb
 *
 */
package zzOrdering;

import zzEx.ZZIllegalEx;
import zzProduct.Product;

public class OrderByPackaging extends FieldOrdering  {
  public int compareMe(Product arg0, Product arg1) throws ZZIllegalEx {
    return String.CASE_INSENSITIVE_ORDER.compare(arg0.getPackaging(),arg1.getPackaging());
  }

}
