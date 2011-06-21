package zzQuery;

import zzProduct.Product;

public final class NoQuery implements Query { 
	Query query;
	
	public int compareTo(Query arg0) {
		return query.compareTo(arg0);
	}

	public NoQuery(Query query) {
		super();
		this.query = query;
	}

	public boolean ask(Product p) {
		//System.out.print("No query");
		return ! query.ask(p);
	}

	public int getExcluded() {
		return query.getExcluded();
	}

	public String getName() {
		return query.getName();
	}

	public String getOtherInfo() {
		return query.getOtherInfo();
	}

	public void resetCounter() {
		query.resetCounter();
	}
	
	
	
	
	
}
