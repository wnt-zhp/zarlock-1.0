/*
 * Created on 2006-04-18
 * Author jb
 *
 */
package zzProduct;

import java.util.Comparator;


public class ProductComparator implements Comparator<Product> {

  public int compare(Product arg0, Product arg1) {
    try{
    if(arg0.getId().getVal() < arg0.getId().getVal()) return -1;
    if(arg0.getId().getVal() > arg0.getId().getVal()) return 1;
    return 0;
    }
    catch(Exception foo){
      return -1;
    }
  }
}
