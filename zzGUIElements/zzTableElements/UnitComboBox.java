/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import zzEx.ZZIllegalDateExceptionEx;
import zzEx.ZZIllegalEx;
import zzProduct.AbstractProduct;
import zzProduct.Product;
import StringIntTwoWayMap.StringIntTwoWayMap;

public class UnitComboBox extends StringIntComboBox {

  public UnitComboBox(Product _owner) throws ZZIllegalEx {
    super(_owner, AbstractProduct.getUnitMap());
  }

  @Override
  protected void setProperty(String item) throws ZZIllegalEx {
    owner.changeUnit(item);
  }

  @Override
  protected String getProperty() throws ZZIllegalEx {
    return owner.getUnit();
  }



}
