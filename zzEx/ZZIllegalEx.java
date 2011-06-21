/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Thrown when user is performing action that is legal in terms of programming,
 * but unwanted in terms of life - eg: tries to give out food that expiey date has 
 * passed.
 */
public class ZZIllegalEx extends ZZEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalEx(String arg0) {
    super("Illegal" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalEx(String arg0, Throwable arg1) {
    super("Illegal" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalEx(Throwable arg0) {
    super(arg0);
  }

}
