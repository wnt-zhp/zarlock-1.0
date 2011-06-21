/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzTests;
import java.util.TreeMap;
import java.util.Vector;

import zzId.*;

public class IdTests {
  public static void main(String[] args) throws Exception{
    IdGenerator<ExpenditureId> foo = new IdGenerator<ExpenditureId>(new ExpenditureId(0));
    Vector<ExpenditureId> foo2 = new Vector<ExpenditureId> ();
    for(int i = 0; i < 100; i++){
      foo2.add(foo.getNextId());
    }
    for(ExpenditureId p: foo2){
      System.out.println(p);
    }
    TreeMap<ExpenditureId, String> foo3 = new TreeMap<ExpenditureId, String>(/*new IdComparator<ExpenditureId>()*/);
    for(int i = 0; i < 100; i++){
      foo3.put(foo.getNextId(), "foo" + foo.getLastId());
    }
    for(ExpenditureId p: foo3.keySet()){
      System.out.println(p + ", " +foo3.get(p));
    }
    ExpenditureId meid = foo.getNextId();
    ExpenditureId meid2 = new ExpenditureId (meid);
    foo3.put(meid, "foo");
    System.out.println(foo3.containsKey(meid2));

  }

  public IdTests() {
    super();
    // TODO Auto-generated constructor stub
  }

}
