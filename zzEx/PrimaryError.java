/*
 * Created on 2006-04-02
 * Author jb
 *
 */
package zzEx;

public class PrimaryError extends Error {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public PrimaryError(String arg0){
    super(arg0);
  }
  
  public PrimaryError(String arg0, Throwable arg1){
    super(arg0, arg1);
  }

}
