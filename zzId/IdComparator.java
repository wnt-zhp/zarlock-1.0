/*
 * Created on 2006-05-02
 * Author jb
 *
 */
package zzId;

public class IdComparator<T extends AbstractId> implements java.util.Comparator<T>{
  public int compare(T arg0, T arg1) {
     System.out.println("Compare: " + arg0 + ", " + arg1);
    return arg0.comId(arg1);
  }
}
