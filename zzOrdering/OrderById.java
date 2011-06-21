/*
 * Created on 2006-03-26
 * Author jb
 *
 */
package zzOrdering;

import zzProduct.Product;

public final class OrderById implements Ordering {
  public int compare(Product arg0, Product arg1) {
    return arg0.compareTo(arg1);
  }

}
