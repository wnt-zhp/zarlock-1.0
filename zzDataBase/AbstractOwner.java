/*
 * Created on 2006-05-02
 * Author jb
 *
 */
package zzDataBase;

import zzEx.ZZIllegalEx;
import zzId.DumbIdGen;
import zzId.IdComparator;
import zzId.IdGenerator;
import zzId.OwnerId;

public abstract class AbstractOwner implements OwnerOfAnExpenditurev2 {
  private OwnerId ownerId;
  private static IdGenerator<OwnerId> OwnerIdGenerator = 
    new IdGenerator<OwnerId>(new OwnerId(0)); 
  
  public static DumbIdGen getIdGenerator(){
    return new DumbIdGen(OwnerIdGenerator);
  }
  
  public void  setFirstOwnerIdGeneratrd(OwnerId id){
    OwnerIdGenerator =
      new IdGenerator<OwnerId>(id); 
  }
  
  protected AbstractOwner(){
    ownerId = OwnerIdGenerator.getNextId(); 
    StaticContent.sc.getOwners().put(ownerId,this);
  }
  
  public void setOwnerId(OwnerId _id){
    StaticContent.sc.getOwners().remove(ownerId);
    ownerId = _id;
    StaticContent.sc.getOwners().put(ownerId,this);
  }
  
  public OwnerId getOwnerId()throws ZZIllegalEx{
    return new OwnerId(ownerId);
  }
  
  public boolean checkOwnerId(OwnerId id)throws ZZIllegalEx{
    return new IdComparator<OwnerId>().compare(this.ownerId, id)==0;
  }
}
