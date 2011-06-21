/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzOrdering;

import java.util.Comparator;

import zzProduct.Product;

public interface Ordering extends Comparator<Product> {

  /**
   * Ordering interface
   * @param p1 
   * @param p2
   * @return 0 if equal. Positive if p1 >p2, negative otherwise.
   */
  public int compare(Product arg0, Product arg1);
  
  //int ask (Product p1, Product p2);

}
