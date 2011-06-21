/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzEx;

public class ZZIllegalNosuchEntryEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalNosuchEntryEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalNosuchEntryEx(String arg0) {
    super("NosuchEntry" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalNosuchEntryEx(String arg0, Throwable arg1) {
    super("NosuchEntry" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalNosuchEntryEx(Throwable arg0) {
    super(arg0);
  }
}
