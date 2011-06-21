package zzQuery;

import java.text.ParseException;

import zzEx.PrimaryError;
import zzEx.ZZIllegalWrongPatternEx;
import zzMyDate.MyDate;
import zzProduct.Product;

public abstract class DateFilter extends AbstractFilterv2 {
	
	public DateFilter(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}


	public boolean usesOperand() {
		return true;
	}

	@Override
	public Query getFilter(String pattern, SimpleOperand oper) 
	throws ZZIllegalWrongPatternEx{
		MyDate date;
		try{
			date = new MyDate(pattern);
		}catch(ParseException ex){
			throw new ZZIllegalWrongPatternEx();
		}
		if(oper==SimpleOperand.EQUAL){
			return new Equal(date);
		}
		if(oper==SimpleOperand.GREATER){
			return new Greater(date);
		}
		if(oper==SimpleOperand.SMALLER){
			return new Less(date);
		}
		throw new PrimaryError("DateFilter");
	}
	
	
	abstract protected MyDate getQuerriedDate(Product p) throws Exception;
	
	private abstract class foo extends AbstractQuery{				
		protected MyDate date;
		
		@Override
		protected int cmp(Query q) {
			foo q2 = (foo) q;
			return q2.date.compareTo(date);
		}

		public foo(String name, Object oper, String pattern) {
			super(name, oper, pattern);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	private class Equal extends foo{
		public Equal(MyDate date) {
			super(DateFilter.this.getName(), Operand.EQUAL, date.toString());
			// TODO Auto-generated constructor stub
			this.date = date;
		}

		@Override
		public boolean ask(Product p) {
			try {
				return getQuerriedDate(p).compareTo(date)==0;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
	}
	
	private class Greater extends foo{
		public Greater(MyDate date) {
			super(DateFilter.this.getName(), Operand.GREATER, date.toString());
			this.date = date;
		}

		@Override
		public boolean ask(Product p) {
			try {
				return getQuerriedDate(p).compareTo(date)>0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	private class Less extends foo{
		public Less(MyDate date) {
			super(DateFilter.this.getName(), Operand.SMALLER, date.toString());
			this.date = date;
		}

		@Override
		public boolean ask(Product p) {
			try{
				return getQuerriedDate(p).compareTo(date)<0;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}
	


}
