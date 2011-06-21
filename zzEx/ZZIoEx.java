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
public class ZZIoEx extends ZZEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIoEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIoEx(String arg0) {
    super("Io" +arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIoEx(String arg0, Throwable arg1) {
    super("Io" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIoEx(Throwable arg0) {
    super(arg0);
  }

}
