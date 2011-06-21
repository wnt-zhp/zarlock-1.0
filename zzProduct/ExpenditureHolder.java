package zzProduct;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzId.AbstractId;

public interface ExpenditureHolder {
	AbstractId performExpenditure(Expenditure exp) 
		throws 
		ZZIllegalEx, 
		ZZInternalUnsupportedOperationExceptionEx,
		ZZInternalClassCastExceptionEx;
	void changeExpenditure(AbstractId changedId, Expenditure newOne) 
		throws ZZIllegalEx, ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx;
	void removeExpenditure(AbstractId id) throws ZZIllegalEx, ZZInternalClassCastExceptionEx;
}
