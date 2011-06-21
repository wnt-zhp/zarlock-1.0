/*
 * Created on 2006-05-02
 * Author jb
 *
 */
package zzTests;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import zzMyDate.*;

public class MyDateTests {
  public static void main(String Args[]) throws ParseException{
    MyDate data1 = new MyDate(new Date());
    MyDate data2 = new MyDate(new Date());
    MyDate data3 = new MyDate("02.05.2006");
    MyDate data5 = new MyDate("02.05.2006");
    MyDate data4 = new MyDate("10.05.2006");
    System.out.println("data3 = 02.05.2006: " + data3);
    System.out.println("data4 = 10.05.2006: " + data4);
    System.out.println("data4 = 10.05.2006: " +
   		 DateFormat.getDateInstance
          (DateFormat.SHORT, new Locale ("pol", "PL")).parse("10.05.2006"));
    check(data1, data2);
    check(data1, data1);

        
    check(data3, data4);
    check(data3, data3);
    check(data3, data5);
    check(data1, data4);
    
   }
  private static void check(MyDate d1, MyDate d2){
    System.out.println("SprawdŸmy daty: " + d1 + ", " + d2);
    System.out.println("Ró¿nica w dniach pomiêdzy nimi:\n"
        + d1.distanceInDays(d2));
    System.out.println("Czy s¹ sobie równe?:"
        + ( d1.compareTo(d2) == 0 ));
    System.out.println("compareTo\n" 
   		 +d1.compareTo(d2) + "\n"+
   		 d2.compareTo(d1));
    System.out.println("Czy pierwsza jest .after druga?: \n" +
   		 d1.after(d2));
    System.out.println("Czy pierwsza jest .before druga?: \n" +
   		 d1.before(d2));
    System.out.println("Czy druga jest .after pierwsza?: \n" +
   		 d2.after(d1));
    System.out.println("Czy druga jest .before pierwsza?: \n" +
   		 d2.before(d1));
    System.out.println("Sprawdzamy zwrotnoœæ operacji:\n" 
        + ( d1.distanceInDays(d2) == - d2.distanceInDays(d1) ) +"\n" +
        ( ( d1.compareTo(d2) ) ==  ( - d2.compareTo(d1) ) ) );  
    System.out.print("\n    ... --- ooo OOO ooo --- ...    \n");
  }
}
