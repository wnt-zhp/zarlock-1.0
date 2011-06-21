/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Thrown when user tries to assign product to non existing cathegory
 */
public class ZZInternalNoCathegoryEx extends ZZInternalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZInternalNoCathegoryEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZInternalNoCathegoryEx(String arg0) {
    super("NoCathegory" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZInternalNoCathegoryEx(String arg0, Throwable arg1) {
    super("No cathegory" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZInternalNoCathegoryEx(Throwable arg0) {
    super(arg0);
  }

}
