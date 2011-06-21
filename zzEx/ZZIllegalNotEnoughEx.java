/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Thrown when someone is trying to give out more items of product than he posseses
 */
public class ZZIllegalNotEnoughEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalNotEnoughEx() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public ZZIllegalNotEnoughEx(String arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalNotEnoughEx(String arg0, Throwable arg1) {
    super(arg0, arg1);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public ZZIllegalNotEnoughEx(Throwable arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

}
