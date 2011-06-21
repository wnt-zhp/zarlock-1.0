package zzQuery;

import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public interface Filter {
	
	String getName();
	String getDescription();
	
	void setName(String name); 
	void setDescription(String desc);
	
	/**
	 * Used to get query created using pattern 
	 * @param pattern
	 * @return
	 */
	Query getFilter(String pattern, Operand oper) 
		throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx;
	
	/**
	 * 
	 * @param pattern
	 * @param not if true Query.ask should yeild opposite result
	 * @return
	 * @throws ZZIllegalWrongPatternEx
	 * @throws ZZInternalUnsupportedOperationExceptionEx
	 */
	Query getFilter(String pattern, boolean not) 
		throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx;

	Query getFilter(boolean not) throws ZZInternalUnsupportedOperationExceptionEx; 
	
	boolean usesOperand();
	boolean isPreSetted();
	
	
	public String[] getDescriptionOfNot() throws ZZInternalUnsupportedOperationExceptionEx;
	
}
