package zzDataBase;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzId.AbstractId;
import zzId.ExpenditureId;
import zzId.MealExpenditureId;
import zzId.ProdId;
import zzMyDate.MyDate;
import zzProduct.Product;

public interface Foo {

	public abstract void setName(String name);

	public abstract String getName();

	public abstract void setDate(MyDate date);

	public abstract MyDate getDate();

	public abstract void setOpis(String opis);

	public abstract String getOpis();

	public abstract MealExpenditureId addProduct(Product p, float q)
			throws ZZIllegalEx;

	public abstract void removeExpenditure(MealExpenditureId id)
			throws ZZIllegalEx;

	public abstract void removeExpenditure(ProdId pId, ExpenditureId eId)
			throws ZZIllegalEx;

	public abstract void changeExpenditure(Product p, ExpenditureId foo,
			float newQuant) throws ZZIllegalEx;

	////
	public abstract String toString();

	public abstract void parseString(String s) throws ZZIoBadFormatEx;

	public abstract void cancel(ExpenditureId toBeCancelled, ProdId product)
			throws ZZIllegalEx;

	public abstract void cancel(MealExpenditureId id) throws ZZIllegalEx;

	public abstract Iterator<Entry<MealExpenditureId, Expenditure>> iterator();

	public abstract Collection<? extends Expenditure> getExpendtures();

	public abstract void changeExpenditure(AbstractId changedId,
			Expenditure newOne) throws ZZIllegalEx,
			ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx;

	public abstract AbstractId performExpenditure(Expenditure exp)
			throws ZZIllegalEx, ZZInternalUnsupportedOperationExceptionEx;

	/**
	 * @deprecated
	 * Use only via ExpenditureHolder interface
	 */
	public abstract void removeExpenditure(AbstractId removedId)
			throws ZZIllegalEx, ZZInternalClassCastExceptionEx;

}