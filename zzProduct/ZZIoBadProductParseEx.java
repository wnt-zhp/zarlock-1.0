/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzProduct;

import zzEx.ZZIoEx;

public class ZZIoBadProductParseEx extends ZZIoEx {

  private static final long serialVersionUID = 1L;
  Product contents;
  public ZZIoBadProductParseEx(String arg0, Product Save ) {
    super("BadProductParse" + arg0);
    contents = Save;
  }


}
