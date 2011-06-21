/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Thrown when some internal inconsistency occurs
 */
public class ZZInternalEx extends ZZEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZInternalEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZInternalEx(String arg0) {
    super("Internal" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZInternalEx(String arg0, Throwable arg1) {
    super("Internal" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZInternalEx(Throwable arg0) {
    super(arg0);
  }

}
