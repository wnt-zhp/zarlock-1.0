/*
 * Created on 2006-03-07
 * Author jb
 *
 */
package zzTests;

import zzMyDate.*;
import zzProduct.*;
import zzEx.*;
public class ProductTestsExtended1 {
  
  public static void main (String[] x758255887) throws Exception {
    ProductTests.makeSimpleMap();
    //try{
    Product foo = new SimpleProduct("Bu³ka, 10, xx1, 0.1, xx2, 10.07.67, 10.08.67," +
        "Wojskowa racja; ¿ywnoœciowa, FN-MILITARY-BNO-FNOLL-9885;, 10, 1,8,1,2,0");
    System.out.print("orig:" +foo + "\n");
    foo.performExpenditure(1, new MyDate("11.07.67"), "foo");
    foo.performExpenditure(4, new MyDate("12.07.67"), "foo2");
    System.out.print("Wydano 5 sztuk dnia 11.07.67\n");
    String msg= new String(foo.toString());
    System.out.println("orig; to string: " + msg);
    Product foo2 = new SimpleProduct (msg);
    System.out.println(foo2);
    System.out.println("Ciekawe czy oba produkty s¹ takie same: " + foo2.equals(foo));
    try{
      foo.performExpenditure(10, new MyDate("11.07.67"),"foo");
      System.out.println("W³aœnie wydano wiêcej ni¿ jest na sk³adzie i nic"
          + "nie zosta³o rzucone");
    }
    catch(ZZIllegalNotEnoughEx fdasd){
      System.out.println("Ta informacja pwoinna siê tu pojawiæ \n" + 
          "Nie pozwolono na wydanie wiecej ni¿ jest na sk³adzie - " +
          "Zobacz ze produkt siê nie zmieni³");
      System.out.println(foo);
    }
    try{
      foo.performExpenditure( 1,new MyDate("11.07.68"), "foo");   
    }
    catch (ZZIllegalEx ada){
      System.out.println(ada.getMessage());
    }
    
    
    System.out.println("Sprawdzamy czy w³aœciwie jest" +
        " sprawdzana wa¿noœæ Produktu \n Powinno byæ \"true\", jest: " 
        + foo.isExpired(new MyDate("11.09.99")) + 
        ", powinno byæ \"false\" a jest: " + 
        foo.isExpired(new MyDate("09.08.67") ));
    
    System.out.println("Sprawdzamy czy dobrze jest liczony czas to chwili "+
        "utraty œwierzoœci. \n Powinno byc 9, jest: " +
        foo.toExpiry(new MyDate("01.08.67")));
       
    //}catch(ZZEx foo2){
    //  System.out.print(foo2.getMessage()); 
    //}catch(Exception foo2){
    //  System.out.print(foo2.getMessage());     
    //}
  }
}
