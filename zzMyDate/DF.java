/*
 * Created on 2006-03-08
 * Author jb
 *
 */
package zzMyDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

abstract class DF {  
  public static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, new Locale ("pol", "PL"));
  public static MyDate parse(String s) throws ParseException{
    return new MyDate(df.parse(s));
  }
  
  //public static String format(MyDate foo){
    //return df.format(foo.date);
  //}
}
