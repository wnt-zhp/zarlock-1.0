/*
 * Created on 2006-03-27
 * Author jb
 *
 */
package zzOrdering;

import zzEx.ZZIllegalEx;
import zzProduct.Product;

public final class OrederByBookingDate extends FieldOrdering  {
  @SuppressWarnings("deprecation")
  public int compareMe(Product arg0, Product arg1) throws ZZIllegalEx {
    return arg0.getExpiryDate().compareTo(arg1.getExpiryDate());
  }
}
