/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzUser;

public class User {
  static String Name;
  static Priveleges privs;
  public static String getName(){
    return Name;
  }
  
  public static Priveleges getPriveleges(){
    return privs;
  }
  
  public static void setName(String _Name){
    Name = _Name;
  }
  
  public static void setPriveleges(Priveleges _privs){
    privs= _privs;
  }
  
  public static boolean canAddExpenditureInProduct(){
	  return (privs.compareTo(Priveleges.USER)>=0);
  }
  
  public static boolean canModifyExpenditureInProduct(){
	  return (privs.compareTo(Priveleges.USER)>0);
  }
  
  public static boolean canRemoveExpenditureInProduct(){
	  return (privs.compareTo(Priveleges.USER)>0);
  }
  
  public static boolean canModifyPartOfAnExpenditure(){
	  return (privs.compareTo(Priveleges.USER)>0);
  }
  
  public static boolean canAddExpenditureInMeal(){
	  return (privs.compareTo(Priveleges.USER)>=0);
  }
  
  public static boolean canRemoveExpenditureInMeal(){
	  return (privs.compareTo(Priveleges.USER)>0);
  }
  
  public static boolean canChangeExpenditureInMeal(){
	  return (privs.compareTo(Priveleges.USER)>0);
  }
  

}
