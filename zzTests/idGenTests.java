/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzTests;

import java.util.Vector;

import zzId.IdGenerator;
import zzId.ProdId;

public class idGenTests extends Thread {
  IdGenerator<ProdId> gen;
  String nazwa;
  double foo= 232;
  double foo2 = 5868.5587;
  Vector<String> dane;
  
  public void run(){
    for(int i = 0; i<40; i++){
      System.out.println(nazwa + gen.getNextId());
      for(int j = 0; j<1000;j++){
        foo *= StrictMath.exp(++foo2) / StrictMath.exp(foo);
      }
      Thread.yield();
    }
  }
  
  public static void main(String Args[]) throws Exception{
    IdGenerator<ProdId> gen = new IdGenerator<ProdId>(new ProdId(12));
    for(int i = 0; i<11; i++){
      new idGenTests("" + i + ":", gen);
    }
  }

  public idGenTests(String _nazwa, IdGenerator<ProdId> _gen) {
    super();
    nazwa = _nazwa;
    gen = _gen;
    start();
  }

}
