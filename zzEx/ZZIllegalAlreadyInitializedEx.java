/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 *
 */
public class ZZIllegalAlreadyInitializedEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalAlreadyInitializedEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalAlreadyInitializedEx(String arg0) {
    super("AlreadyInitialized" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalAlreadyInitializedEx(String arg0, Throwable arg1) {
    super("AlreadyInitilaized" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalAlreadyInitializedEx(Throwable arg0) {
    super(arg0);
  }

}
