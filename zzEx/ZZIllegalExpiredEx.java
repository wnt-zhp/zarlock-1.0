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
public class ZZIllegalExpiredEx extends ZZIllegalDateExceptionEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalExpiredEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalExpiredEx(String arg0) {
    super("Expired" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalExpiredEx(String arg0, Throwable arg1) {
    super("Expierd" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalExpiredEx(Throwable arg0) {
    super(arg0);
  }

}
