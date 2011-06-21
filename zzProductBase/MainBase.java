package zzProductBase;

import java.util.Vector;

import zzOrdering.Ordering;
import zzQuery.Query;

public class MainBase extends SimpleProductBaseMutable {

	public MainBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainBase(SimpleProductBase foo) {
		super(foo);
		// TODO Auto-generated constructor stub
	}

	public MainBase(SimpleProductBaseMutable foo) {
		super(foo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected synchronized void fireFiltered(ProductBaseImmutable filtered, Vector<Query> query) {
	}
	

	@Override
	protected synchronized void fireSorted(Vector<Ordering> orderings) {
	}

	
}
