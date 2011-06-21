package zzQuery;

import java.util.Vector;

import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public abstract class AbstractFilter extends Object implements Filter {
	
	private String name;
	private String description;
	
	public AbstractFilter(String name, String description) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.description = description;
	}
	
	
	public static final Vector<Filter> allFilters = new Vector<Filter>();
	static{
		allFilters.add(new NameFilter());
		allFilters.add(new DateValidFilter());
        allFilters.add(new FacturaNoFilter());
		allFilters.add(new HasMoreThanZero());
		allFilters.add(new BookingDateFilter());
		allFilters.add(new ExpiryDateFilter());
		allFilters.add(new DescriptionFilter());
		allFilters.add(new CQuantityFilter());
		allFilters.add(new PriceFilter());
	}
	
	
	public Query getFilter(String pattern, Operand oper) throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}

	public Query getFilter(String pattern, boolean not) throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}

	public String[] getDescriptionOfNot() throws ZZInternalUnsupportedOperationExceptionEx{
		throw new ZZInternalUnsupportedOperationExceptionEx(this.getClass().getName());
	}

	public Query getFilter(Query query) {
		return query;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Query getFilter(boolean not) throws ZZInternalUnsupportedOperationExceptionEx {
		throw new ZZInternalUnsupportedOperationExceptionEx();
	}

	public boolean isPreSetted() {
		return false;
	}

	public boolean usesOperand() {
		return false;
	}

	

	
}
