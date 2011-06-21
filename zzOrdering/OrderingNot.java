/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzOrdering;

import zzProduct.Product;

public class OrderingNot implements Ordering {
  Ordering zawartosc;
  public OrderingNot(Ordering foo) {
   foo = zawartosc;
  }
  public int compare(Product arg0, Product arg1) {
    return -zawartosc.compare(arg0, arg1);
  }

}
