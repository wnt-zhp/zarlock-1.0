/*
 * Created on 2006-03-07
 * Author jb
 *
 */
package zzTests;

import zzProduct.*;

public class ProductTestsExtended2 {
  public static void main(String[] args) throws Throwable {
    ProductTests.makeSimpleMap();
    for(int i=0; i <10000; i++){
      new SimpleProduct ();    
    }
    System.out.println(new SimpleProduct ().getId());
  }

}
