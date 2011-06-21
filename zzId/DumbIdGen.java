/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzId;

public class DumbIdGen<T extends AbstractId> {
  private IdGenerator idGen;

  public DumbIdGen(IdGenerator foo) {
    super();
  }
  
  @SuppressWarnings("unchecked")
  public T getNextId(){
    return (T) idGen.getNextId();
  }

}
