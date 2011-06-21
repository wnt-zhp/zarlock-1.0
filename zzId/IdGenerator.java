/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzId;


public class IdGenerator<T extends AbstractId> {

    T lastId = null;
    
    public IdGenerator(T firstId){
      lastId = firstId;
    }
    
    @SuppressWarnings("unchecked")
    public T getLastId(){
      return (T) lastId.copy();
    }
    
    @SuppressWarnings("unchecked")
    public synchronized T getNextId(){
      if(lastId==null){
        //throw new ZZIllegalNotInitializedEx("Nie zainicjowano generatora id");
      }
      lastId.increment();
      return (T) lastId.copy();
    };
    
    @SuppressWarnings("unchecked")
    public synchronized void setLastId(AbstractId rhs){
      lastId = (T) rhs.copy();
   }   
 

}
