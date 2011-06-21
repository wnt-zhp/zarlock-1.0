/*
 * Created on 2006-03-16
 * Author jb
 *
 */
package zzEx;

public class ZZIoFileNotFoundEx extends ZZIoEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ZZIoFileNotFoundEx(String arg0) {
    super("FileNotFound: " + arg0);
   }

  public ZZIoFileNotFoundEx(String arg0, Throwable arg1) {
    super("FileNotFound: " + arg0, arg1);
  }

}
