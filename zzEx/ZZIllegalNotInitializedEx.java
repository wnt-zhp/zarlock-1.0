/*
 * Created on 2006-02-27
 * Author jb
 *
 */
package zzEx;

/**
 * @author jb
 * Rzucane kiedy ktoœ próbuje odczytaæ nie zainicjalizowane pole. (takei którego referencja == null)
 */
public class ZZIllegalNotInitializedEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ZZIllegalNotInitializedEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZIllegalNotInitializedEx(String arg0) {
    super("NotInitialized" +arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZIllegalNotInitializedEx(String arg0, Throwable arg1) {
    super("NotInitialized" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZIllegalNotInitializedEx(Throwable arg0) {
    super(arg0);
  }

}
