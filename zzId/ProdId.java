/*
 * Created on 2006-04-28
 * Author jb
 *
 */
package zzId;

public class ProdId extends AbstractId  {

  public ProdId(ProdId rhs) {
    super(rhs);

  }

  public ProdId(int rhs) {
    super(rhs);

  }

  public ProdId() {
    super();

  }

  public AbstractId copy() {
    return new ProdId(this);
  }

  public int compareTo(ProdId arg0) {
    return comId(arg0);
  }
  
  

}
