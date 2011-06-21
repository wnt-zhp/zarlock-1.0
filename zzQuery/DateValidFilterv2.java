package zzQuery;

import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public class DateValidFilterv2 extends DateValidFilter {
	String pattern;

	public DateValidFilterv2(String pattern) {
		super();
		// TODO Auto-generated constructor stub
		this.pattern = pattern;
		System.out.println(pattern);
	
	}

	@Override
	public Query getFilter(boolean not)throws ZZInternalUnsupportedOperationExceptionEx {
		try {
			return super.getFilter(pattern, not);
		} catch (ZZIllegalWrongPatternEx e) {
			throw new ZZInternalUnsupportedOperationExceptionEx();

		} 
	}

	@Override
	public boolean isPreSetted() {
		return true;
	}

	@Override
	public String getName() {
		return "Ustawiona odpowiednia data";
	}

	
	
}
