package zzQuery;

import java.text.ParseException;

import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzMyDate.MyDate;
import zzProduct.Product;

public class DateValidFilter extends AbstractFilter {


	public final static String[] opis = new String[] {
			"Produkty o dobrej dacie", "Produkty o z³ej dacie" };

	public DateValidFilter() {
		super(
				"Odpowiednia data",
				"Zwraca produkty ksiêgowane przed dan¹ dat¹ i maj¹ce termin przydatnoœci po niej");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getDescriptionOfNot()
			throws ZZInternalUnsupportedOperationExceptionEx {
		return opis;
	}

	@Override
	public Query getFilter(String pattern, boolean not)
			throws ZZIllegalWrongPatternEx,
			ZZInternalUnsupportedOperationExceptionEx {

		MyDate date;
		try {
			date = new MyDate(pattern);
		} catch (ParseException e) {
			throw new ZZIllegalWrongPatternEx();
		}
		Query q = new MyQuery(date);
		if (not) {
			return new NoQuery(q);
		} else {
			return q;
		}

	}

	@Override
	public boolean usesOperand() {
		return false;
	}

	@Override
	public boolean isPreSetted() {
		return false;
	}

	private class MyQuery extends AbstractQuery {
		MyDate date;

		public MyQuery(MyDate validDate) {
			super("Odpowiednia data", "", validDate.toString());
			date = validDate;
			

		}

		@Override
		protected int cmp(Query q) {
			MyQuery q2 = (MyQuery) q;
			return q2.date.compareTo(date);
		}

		@Override
		public boolean ask(Product p) {
			try{
				System.out.println(date +" " + p.getDateOfBooking() );
				System.out.println(date +" " + p.getExpiryDate() );
				System.out.println();
			return date.after(p.getDateOfBooking()) && date.before(p.getExpiryDate());
			}catch(Exception e){
				return false;
			}
		}

	}

}