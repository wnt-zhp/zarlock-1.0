package zzQuery;

import zzEx.PrimaryError;
import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzProduct.Product;

public abstract class AbstractFilterv2 extends AbstractFilter {
 
	public AbstractFilterv2(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public boolean usesOperand() {
		return true;
	}
	
	protected abstract Query getFilter(String pattern, SimpleOperand oper)throws Exception;
	
	@Override
	public Query getFilter(String pattern, Operand oper) throws ZZIllegalWrongPatternEx, ZZInternalUnsupportedOperationExceptionEx {
		try{
			if(oper.equals(Operand.EQUAL)){
				return getFilter(pattern, SimpleOperand.EQUAL);
			}
			if(oper.equals(Operand.GREATER)){
				return getFilter(pattern, SimpleOperand.GREATER);
			}
			if(oper.equals(Operand.SMALLER)){
				return getFilter(pattern, SimpleOperand.SMALLER);
			}
			if(oper.equals(Operand.SMALLER_OR_EQUAL)){
				return new QQuery(
						getName(),
						oper,
						pattern,
						getFilter(pattern, SimpleOperand.SMALLER),
						getFilter(pattern, SimpleOperand.EQUAL));
			}
			if(oper.equals(Operand.GREATER_OR_EQUAL)){
				return new QQuery(
						getName(),
						oper,
						pattern,
						getFilter(pattern, SimpleOperand.GREATER),
						getFilter(pattern, SimpleOperand.EQUAL));
			}
			if(oper.equals(Operand.NOT_EQUAL)){
				return new NoQuery(getFilter(pattern,SimpleOperand.EQUAL));
			}
		}catch(PrimaryError e){
			throw e;
		}catch(ZZIllegalWrongPatternEx e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			return new AbstractQuery("B³¹d", "B³¹d", "B³¹d"){
				public int cmp(Query q){
					return 0;
				}
				@Override
				public boolean ask(Product p) {
					super.ask(p);
					return false;
				}
				
				
			};
		}
		
		throw new PrimaryError("getFilter");
	}
	
	
	private class QQuery extends AbstractQuery{
		Query one, two;
		
		@Override
		protected int cmp(Query q) {
			QQuery q2 = (QQuery) q;
			return Math.abs(one.compareTo(q2.one)) + Math.abs(two.compareTo(two));
		}


		public QQuery(String name, Object oper, String pattern, Query one, Query two) {
			super(name, oper, pattern);
			this.one = one;
			this.two = two;
		}


		@Override
		public boolean ask(Product p) {
			super.ask(p);
			return one.ask(p) || two.ask(p);
		}		
	}




}
