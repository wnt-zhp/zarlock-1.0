/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzEx;


public class ZZIllegalNoCathegoryEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalNoCathegoryEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalNoCathegoryEx(String arg0) {
    super("Illegal" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalNoCathegoryEx(String arg0, Throwable arg1) {
    super("Illegal" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalNoCathegoryEx(Throwable arg0) {
    super(arg0);
  }
}
