package zzQuery;

import zzProduct.Product;

public interface Query extends Comparable<Query>{
	boolean ask(Product p);
	int getExcluded();
	void resetCounter();
	String getName();
	String getOtherInfo();
}
