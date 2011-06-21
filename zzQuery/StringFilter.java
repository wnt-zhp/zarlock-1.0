package zzQuery;

import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzProduct.Product;

public abstract class StringFilter extends AbstractFilter {
	
	public static final String[] opcje  = new String[]{
		"Zawiera",
		"Nie zawiera"				
	}; 

	public StringFilter(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public boolean usesOperand() {
		return false;
	}
	

	@Override
	public String[] getDescriptionOfNot() 
		throws ZZInternalUnsupportedOperationExceptionEx {
		return opcje;
	}

	@Override
	public Query getFilter(String pattern, boolean not) 
		throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx {
		if(not){
			return new NoQuery(new StringQuery(getName(), getDescriptionOfNot()[1],pattern));
		}else{
			return new StringQuery(getName(), getDescriptionOfNot()[0],pattern);
		}
	}
	
	abstract protected String getQuerriedString(Product p) throws Exception;
	
	private class StringQuery extends AbstractQuery{
		
		@Override
		protected int cmp(Query q) {
			StringQuery q2 = (StringQuery) q;
			return String.CASE_INSENSITIVE_ORDER.compare(q2.pattern,pattern);
		}

		public StringQuery(String name, Object oper, String pattern) {
			super(name, oper, pattern);
		}

		@Override
		public boolean ask(Product p) {
			
			try {
				//System.err.println(getQuerriedString(p) + " " + pattern);
				return getQuerriedString(p).contains(pattern);				
			} catch (Exception e) {
				//System.err.println("dasda");
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	
	

}
