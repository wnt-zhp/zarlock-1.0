package zzOrdering;
import zzEx.ZZIllegalEx;
import zzProduct.Product;
/*
 * Created on 2006-03-26
 * Author jb
 *
 */

public class OrderByUnit extends FieldOrdering  {
  public int compareMe(Product arg0, Product arg1) throws ZZIllegalEx {
    return String.CASE_INSENSITIVE_ORDER.compare(arg0.getUnit(),arg1.getUnit());
  }

}
