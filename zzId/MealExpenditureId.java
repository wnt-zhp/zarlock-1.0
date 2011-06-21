/*
 * Created on 2006-05-03
 * Author jb
 *
 */
package zzId;

public class MealExpenditureId extends ExpenditureId {

  public MealExpenditureId(MealExpenditureId rhs) {
    super(rhs);
  }

  public MealExpenditureId(int rhs) {
    super(rhs);
  }

  public MealExpenditureId() {
    super();
  }

  public AbstractId copy() {
    return new MealExpenditureId(this);
  }

}
