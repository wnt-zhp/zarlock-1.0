/*
 * Created on 2006-02-28
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 */
public class ZZErrorEx extends ZZEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZErrorEx() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public ZZErrorEx(String arg0) {
    super("Error" + arg0);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZErrorEx(String arg0, Throwable arg1) {
    super("Error" + arg0, arg1);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public ZZErrorEx(Throwable arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

}
