/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import zzEx.ZZIllegalEx;
import zzProduct.Product;
import StringIntTwoWayMap.StringIntTwoWayMap;

public class PackComboBox extends StringIntComboBox {

  public PackComboBox(Product _owner, StringIntTwoWayMap _map)
      throws ZZIllegalEx {
    super(_owner, _map);
  }

  @Override
  protected void setProperty(String item) throws ZZIllegalEx {
    owner.changePackaging(item);

  }

  @Override
  protected String getProperty() throws ZZIllegalEx {
    return owner.getPackaging();
  }

}
