/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzId;

import zzEx.*;

public abstract class AbstractId implements Comparable<AbstractId> {
  private int id=0;
  
  public AbstractId(AbstractId rhs){
    id = rhs.id;
  }
  
  public AbstractId(int rhs){
    id = rhs;
  }
  
  
  public String toString (){
    return String.valueOf(id);
  }
  
  public void parse(String foo) throws ZZIoBadFormatEx{
    try{
      id = Integer.parseInt(foo.trim());
    }
    catch(Exception grt){
      throw new ZZIoBadFormatEx("Z³y format w klasie " + this.getClass().getName()+ " (" + grt.getMessage() + ")" );
    }
  }
  
  public abstract AbstractId copy();
  
  public void setTo(AbstractId rhs){
    id=rhs.id;
  }
  
  public AbstractId(){
    id=0;
  }
  
  public AbstractId increment(){
    id++;
    return this;
  }
  
  public AbstractId decrement(){
    id--;
    return this;
  }
  
  public int getVal(){
    return id;
  }
  
  protected int comId(AbstractId aId){
    if(id>aId.id){
       return 1;
    }
    if(id<aId.id){
       return -1;
    }
     return 0;
  }
  
  public int compareTo(AbstractId aId){
     
    return comId(aId);
  }

  @Override
	public boolean equals(Object arg0) {
    System.out.println(this.getClass().getName() + ", " + arg0.getClass().getName() + ": " + this.getClass().isInstance(arg0)  );
	if(!this.getClass().isInstance(arg0))
		return false;
	AbstractId aid = (AbstractId) arg0;
	return comId(aid)==0;
  }
  
  
  
  
}
