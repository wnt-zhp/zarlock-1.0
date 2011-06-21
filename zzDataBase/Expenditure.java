/*
 * Created on 2006-02-27
 * Author jb
 *
 */


package zzDataBase;
import zzMyDate.MyDate;

/**
 * @author jb
 *
 */
public class Expenditure {
  public static float sumItUp(Iterable<Expenditure> arr){
    float result=0;
    if(arr==null){
      return 0f;
    }
    for(Expenditure e: arr){
      result+= e.ile;
    }
    return result;    
  }
  
  public String tytulem = null;
  public MyDate date=null;
  public float ile=0; 
  
  public Expenditure(Expenditure foo){
    date=new MyDate(foo.date);
    ile=foo.ile;
    tytulem=new String (foo.tytulem);
  }
  
  public Expenditure(float _ile, MyDate _date, String _tytulem){
    date=new MyDate(_date);
    ile=_ile;
    tytulem=new String (_tytulem);
  }
  
  public String toString(){
    return date.toString() + ";" + ile + "; " + tytulem +","; 
  }
  
  protected Expenditure(){}

  @Override
	public boolean equals(Object arg0) {
	  if(Expenditure.class.isInstance(arg0))
		  return false;
	  Expenditure e = (Expenditure) arg0;
	  
	return ile==e.ile && date.equals(e.date);
};
 
  
}
