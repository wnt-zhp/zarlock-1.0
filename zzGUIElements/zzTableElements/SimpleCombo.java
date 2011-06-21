package zzGUIElements.zzTableElements;

import StringIntTwoWayMap.StringIntTwoWayMap;
import zzEx.ZZIllegalDateExceptionEx;
import zzEx.ZZIllegalEx;
import zzProduct.AbstractProduct;

public class SimpleCombo extends StringIntComboBox {
  StringIntTwoWayMap x = null;;
  
  public SimpleCombo(StringIntTwoWayMap foo) throws ZZIllegalEx{
    super(foo);
    x=foo;
  }
  

  
  @Override
  protected void setProperty(String item) throws ZZIllegalEx {
    
  }

  @Override
  protected String getProperty() throws ZZIllegalEx {
    return x.maxTwo();
  }

}
