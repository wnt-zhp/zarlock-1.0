package zzQuery;

import zzProduct.Product;

public abstract class AbstractQuery extends Object implements Query {
	
	protected String name;
	protected Object oper;
	protected String pattern;

	protected abstract int cmp(Query q);
	
	int counter;	
	
	

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	public boolean ask(Product p) {
		counter++;
		System.err.println("sdadasda" + pattern);
		return true;
	}

	public int getExcluded() {
		return counter;
	}

	public void resetCounter() {
		counter = 0;

	}

	public String getName() {
		return name;
	}

	public String getOtherInfo() {
		return oper.toString() + "  " + pattern;
	}

	public AbstractQuery(String name, Object oper, String pattern) {
		super();
		this.name = name;
		this.oper = oper;
		this.pattern = pattern;
	}

	public int compareTo(Query q) {
		if(this.getClass()!=q.getClass()) return 1;
		else return cmp(q);
	}

}
