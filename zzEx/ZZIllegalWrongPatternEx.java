/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzEx;

public final class ZZIllegalWrongPatternEx extends ZZIllegalEx {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ZZIllegalWrongPatternEx() {
    super("WrongPattern: ");
  }

  public ZZIllegalWrongPatternEx(String arg0) {
    super("WrongPattern: " + arg0);

  }

  public ZZIllegalWrongPatternEx(String arg0, Throwable arg1) {
    super("WrongPattern: " + arg0, arg1);
  }

  public ZZIllegalWrongPatternEx(Throwable arg0) {
    super(arg0);
  }

}
