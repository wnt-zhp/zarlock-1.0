/*
 * Created on 2006-04-28
 * Author jb
 *
 */
package zzId;

public class OwnerId extends AbstractId {

  public OwnerId(OwnerId rhs) {
    super(rhs);
  }

  public OwnerId(int rhs) {
    super(rhs);
  }

  public OwnerId() {
    super();
  }

  public AbstractId copy() {
    return new OwnerId(this);
  }

}
