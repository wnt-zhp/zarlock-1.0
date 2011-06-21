/*
 * Author jb
 *
 */
package zzMyDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
@SuppressWarnings("deprecation")
public class MyDate implements Comparable<MyDate> {
  public static Locale _defLocale = new Locale("pol", "PL");
  public static TimeZone _defTimeZone = TimeZone.getDefault();
  
  private Locale _locale;
  private TimeZone _timeZone;;
  
   @Override
   public boolean equals(Object arg0) {
	if(! this.getClass().isInstance(arg0)) return false;
	MyDate md = (MyDate) arg0;
	return md.date.equals(date);
}

private void setLocale(){
    synchronized(_defLocale){
      _locale = (Locale) _defLocale.clone();
    }
    synchronized(_defTimeZone){
      _timeZone = (TimeZone)_defTimeZone.clone();
    }    
  }
  
  private void ResetHrs(){
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
  }
  
  public MyDate(){
    setLocale();
    date = new GregorianCalendar(_timeZone,_locale );
    date.setTime(new Date());
    ResetHrs();
  }
  
  public MyDate(Date _date){
    setLocale();
    date = new GregorianCalendar(_timeZone,_locale );
    date.setTime(_date);
    ResetHrs();
  }
  
  public MyDate(MyDate _date){
    setLocale();
    date = new GregorianCalendar(_timeZone,_locale );
    date = (GregorianCalendar) _date.date.clone();
    ResetHrs();
  }
  
  public MyDate(String _date) throws ParseException{
    setLocale();
    date = new GregorianCalendar(_timeZone,_locale );
    date.setTime(DateFormat.getDateInstance(DateFormat.SHORT, _locale).parse(_date));
    //System.out.println(_date +", " + this.toString());
    ResetHrs();
  }
  
  public String toString(){
    return DateFormat.getDateInstance(DateFormat.SHORT, _locale).format(date.getTime());
  }
  
  public boolean before (MyDate _date){
    long wynik = (date.getTimeInMillis() - _date.date.getTimeInMillis());
    //System.out.println("before: " + this.toString() + " - "+ _date.toString() + "= " +wynik);
    return date.before(_date.date);
  }
  
  public boolean after (MyDate _date){	  
	  long wynik = (date.getTimeInMillis() - _date.date.getTimeInMillis());
	  //System.out.println("after: " + this.toString() + " - "+ _date.toString() + "= " +wynik);
	  return date.after(_date.date);
	  //return (date.getTimeInMillis() - _date.date.getTimeInMillis()) > -3600000;
  }
  
  /**
   * @param foo
   * @return distance in days, computed as: (this - foo), so if foo is tater than 
   * this return val is > 0.
   */
  public long distanceInDays(MyDate foo){
    long distance=0;
    GregorianCalendar less, more;
    int minus;
    if(foo.date.before(this.date)){
      more = (GregorianCalendar) this.date.clone();
      less = (GregorianCalendar) foo.date.clone();
      minus = +1;
    }
    else{
      more = (GregorianCalendar) foo.date.clone();
      less = (GregorianCalendar) this.date.clone();
      minus = -1;
    }
    while(
          more.get(Calendar.DAY_OF_MONTH)!=less.get(Calendar.DAY_OF_MONTH) ||
          more.get(Calendar.MONTH)!=less.get(Calendar.MONTH) ||
          more.get(Calendar.YEAR)!=less.get(Calendar.YEAR)
        ) 
    {
      less.add(Calendar.DAY_OF_MONTH,1);
      distance ++;
    }
    return distance*minus;
  };
  
  public int compareTo(MyDate foo){
     if(distanceInDays(foo)>0){
       return 1;
     }
     if(distanceInDays(foo)<0){
       return -1;
     }
     return 0;
   }
  
  public GregorianCalendar date;

  
  //static private GregorianCalendar dstBegin, dstEnd;
  
  /*static{
    dstBegin = new GregorianCalendar(new Locale("pol", "PL"));
    dstBegin.setTime(new Date());
    dstBegin.set(Calendar.MONTH, Calendar.MARCH);
    dstBegin.set(Calendar.DAY_OF_MONTH, 1);
    GregorianCalendar temp = new GregorianCalendar(new Locale("pol", "PL"));
    while(dstBegin.get(GregorianCalendar.MONTH)==Calendar.MARCH){
      if(dstBegin.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
        temp=dstBegin;
      }
      dstBegin.add(Calendar.DAY_OF_MONTH,1);
    }
    dstBegin = temp;
    
    dstEnd = new GregorianCalendar(new Locale("pol", "PL"));
    dstEnd.setTime(new Date());
    dstEnd.set(Calendar.MONTH, Calendar.OCTOBER);
    dstEnd.set(Calendar.DAY_OF_MONTH, 1);
    temp.clear();
    while(dstEnd.get(GregorianCalendar.MONTH)==Calendar.OCTOBER){
      if(dstEnd.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
        temp=dstEnd;
      }
      dstEnd.add(Calendar.DAY_OF_MONTH,1);
    }   
    dstEnd = temp;
  }*/
  /*public MyDate() {
    date = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay()).
    getG();
  }
  
  public MyDate(Date _date){
    date = (Date) _date.clone();
    date = new Date(date.getYear(), date.getMonth(), date.getDay());
  }
  
  public MyDate(String foo)throws ParseException{
    date = DF.df.parse(foo);
    date = new Date(date.getYear(), date.getMonth(), date.getDay());
  }
  
  public String toString(){
    return DF.format(this);
  }
  
  public MyDate(MyDate _date){
    date = (Date) _date.date.clone();
  }
  
  public boolean before (MyDate _date){
    return date.before(_date.date);
  }
  
  public boolean after (MyDate _date){
    return date.after(_date.date);
  }
  
  @SuppressWarnings("deprecation")
  public int compareTo(MyDate arg0) {
    if(date.getYear() > arg0.date.getYear()){
      return 1; 
    }
    if(date.getYear() < arg0.date.getYear()){
      return -1; 
    }
    if(date.getMonth() > arg0.date.getMonth()){
      return 1; 
    }
    if(date.getMonth() < arg0.date.getMonth()){
      return -1; 
    }
    if(date.getDay() > arg0.date.getDay()){
      return 1; 
    }
    if(date.getDay() < arg0.date.getDay()){
      return -1; 
    }
    return 0;
  }
  
  public long distanceDays(MyDate foo){
    return (date.getTime() - foo.date.getTime())/(24*3600*100);
    //FIXME Sprawdz czy dzia³a (MyDate.distanceDays())
  }*/


}
