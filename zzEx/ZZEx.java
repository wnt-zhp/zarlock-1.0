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
public class ZZEx extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public String getDefaultMessage(){
	  return "";
  }
  
  /**
   * 
   */
  public ZZEx() {
    super();
  }

  /**
   * @param arg0
   */
  public ZZEx(String arg0) {
    super("ZZEx" + arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public ZZEx(String arg0, Throwable arg1) {
    super("ZZEx" + arg0, arg1);
  }

  /**
   * @param arg0
   */
  public ZZEx(Throwable arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

}
