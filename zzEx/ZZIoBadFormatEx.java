/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Rzucane gdy jest jakiœ b³¹d w formacie informacji wpisywanej informacji
 */
public class ZZIoBadFormatEx extends ZZIoEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIoBadFormatEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIoBadFormatEx(String arg0) {
    super("BadFormat" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIoBadFormatEx(String arg0, Throwable arg1) {
    super("BadFormat" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIoBadFormatEx(Throwable arg0) {
    super(arg0);
  }

}

