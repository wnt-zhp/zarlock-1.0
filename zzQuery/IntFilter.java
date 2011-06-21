package zzQuery;

import zzEx.PrimaryError;
import zzEx.ZZIllegalWrongPatternEx;
import zzMyDate.MyDate;
import zzProduct.Product;

public abstract class IntFilter extends AbstractFilterv2 {
	public IntFilter(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}




	protected Query getFilter(String pattern, SimpleOperand oper)
			throws Exception {
		Integer i;
		try{
			i= new Integer(Math.round(Float.parseFloat(pattern)));
		}catch(NumberFormatException e){
			throw new ZZIllegalWrongPatternEx();
		}
		if(oper.equals(SimpleOperand.SMALLER)){
			return new LessQuery(i);
		}
		if(oper.equals(SimpleOperand.EQUAL)){
			return new EqualQuery(i);
		}
		if(oper.equals(SimpleOperand.GREATER)){
			return new MoreQuery(i);
		}
		throw new PrimaryError("IntFilter");
	}
	
	abstract protected Integer getQuerriedInt(Product p) throws Exception;
	
	private abstract class foo extends AbstractQuery{				
		protected Integer guts;
		
		@Override
		protected int cmp(Query q) {
			foo q2 = (foo) q;
			return q2.guts.compareTo(guts);
		}

		public foo(String name, Object oper, String pattern) {
			super(name, oper, pattern);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	private class LessQuery extends foo{
			public LessQuery(Integer guts) {
			super(IntFilter.this.getName(), Operand.SMALLER, guts.toString());
			// TODO Auto-generated constructor stub
			this.guts = guts;
		}

		@Override
		public boolean ask(Product p) {
			try {
				return getQuerriedInt(p).compareTo(guts)<0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}		
	}
	
	private class MoreQuery extends foo{
		public MoreQuery(Integer guts) {
			super(IntFilter.this.getName(), Operand.GREATER, guts.toString());
			// TODO Auto-generated constructor stub
			this.guts = guts;
		}

		@Override
		public boolean ask(Product p) {
			try {
				return getQuerriedInt(p).compareTo(guts)>0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}		
	}
	
	private class EqualQuery extends foo{
	
		public EqualQuery(Integer guts) {
			super(IntFilter.this.getName(), Operand.EQUAL, guts.toString());
			// TODO Auto-generated constructor stub
			this.guts = guts;
		}

		@Override
		public boolean ask(Product p) {
			try {
				return getQuerriedInt(p).compareTo(guts)==0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}		
	}

}
