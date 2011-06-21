/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzId;

public class ExpenditureId extends AbstractId {

  public ExpenditureId(ExpenditureId rhs) {
    super(rhs);

  }

  public ExpenditureId(int rhs) {
    super(rhs);

  }

  public ExpenditureId() {
    super();

  }

  public AbstractId copy(){
    return new ExpenditureId(this);
  }

}
