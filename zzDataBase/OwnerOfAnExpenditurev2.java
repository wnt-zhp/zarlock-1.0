package zzDataBase;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzId.AbstractId;
import zzId.ExpenditureId;
import zzId.MealExpenditureId;
import zzId.OwnerId;
import zzId.ProdId;

public interface OwnerOfAnExpenditurev2 {

  void cancel(ExpenditureId toBeCancelled, ProdId product) throws ZZIllegalEx;
  
  void cancel (AbstractId  id) throws ZZIllegalEx, ZZInternalClassCastExceptionEx;
  
  void setOwnerId(OwnerId id )throws ZZIllegalEx;
  
  OwnerId getOwnerId()throws ZZIllegalEx;
  
  boolean checkOwnerId(OwnerId id)throws ZZIllegalEx;
  
  String stringify() throws ZZIllegalEx;
   
}
