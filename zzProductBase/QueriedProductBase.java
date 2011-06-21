package zzProductBase;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import zzEx.ZZIllegalDateExceptionEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIoEx;
import zzId.ProdId;
import zzOrdering.Ordering;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable.FilteredListener;
import zzProductBase.ProductBaseImmutable.LoadedLisntener;
import zzProductBase.ProductBaseImmutable.ProductsChangedListener;
import zzProductBase.ProductBaseImmutable.SortedListener;
import zzQuery.Query;

public class QueriedProductBase extends SimpleProductBase implements ProductBaseImmutable {

	private SimpleProductBase base;

	private Vector<Query> querries;
	
	private boolean  sorted = false;
	
	private Vector<Ordering> orderings = new Vector<Ordering>();
	

	public QueriedProductBase(SimpleProductBase foo) {
		super();
		base = foo;
	}

	
	public QueriedProductBase(SimpleProductBase base, Vector<Query> querries) {
		super();
		this.base = base;
		this.querries = new Vector( querries);
	}


	public Product getById(ProdId nr) {
		Product p =  base.getById(nr);
		if(p== null || !ask(p)){
			return null;
		}
		else return p;
	}

	public ProductBaseImmutable performQuery(Query query) {
		if(querries.contains(query))
		return  new QueriedProductBase(base, querries);
		else{
			Vector<Query> q2 = new Vector<Query>(querries);
			q2.add(query);
			return new QueriedProductBase(base, q2);
		}
	}

	public ProductBaseImmutable performQuery(Query[] query) {
		Vector<Query> q2 = new Vector<Query>(querries);
		for(Query q: query){
			if(!q2.contains(q)){
				q2.add(q);
			}
		}
		afterAsk(true);
		return new QueriedProductBase(base, q2);
			
	}

	public ProductBaseImmutable performQuery() {
		return new QueriedProductBase(base, querries);
	}



	public void sort(Ordering ordering) {
		orderings.clear();
		orderings.add(ordering);	
		sorted=false;
	}

	public void sort(Ordering[] ordering) {
		orderings.clear();
		for(Ordering o: ordering){
			orderings.add(o);
		}
		sorted=false;
	}

	public void sort(Collection<Ordering> foo) {
		orderings.clear();
		orderings.addAll(foo);	
		sorted=false;
		
	}

	public long getNumberOfElems() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Product> getSorted() {
		for(Ordering o: orderings){

		}
		return null;
	}

	public List<Product> getSorted(Ordering ordering) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save() throws ZZIoEx, ZZIllegalEx {
		base.save();
		
	}

	public void load() throws ZZIoEx, ZZIllegalEx {
			base.load();	
	}
	
	private int licznik=10;
	
	private void afterAsk(boolean b){
		if(b ||(licznik++) % 10==0){
			Collections.sort(querries,new cmp());
			for(Query q: querries){
				q.resetCounter();
			}
		}
	}
	
	private boolean ask(Product p){
		boolean returnMe=true;
		for(Query q: querries){
			if(!q.ask(p)){
				returnMe=false;
				break;
			}
		}
		afterAsk(false);
		return returnMe;
	}

	
	private class cmp implements Comparator<Query>{

		public int compare(Query arg0, Query arg1) {			
			return arg1.getExcluded() - arg0.getExcluded();
		}
		
	}

}

/*
 * 	public Map<ProdId, Product> getDatabase() {
		return new Map<ProdId, Product>(){

			public int size() {
				return SIZE();
			}
			
			private int SIZE(){
				int i=0;
				for(Product p : base.contents.values())
					if(ask(p))
						i++;
				return i;
				
			}

			public boolean isEmpty() {
				return SIZE()==0;
			}

			public boolean containsKey(Object arg0) {

				return ask(base.contents.get(arg0));
			}

			public boolean containsValue(Object arg0) {
				try{
					return base.contents.containsValue(arg0) && ask((Product)arg0);
				}catch(ClassCastExceptione){
					return false;
				}
			}

			public Product get(Object arg0) {
				Product p = base.contents.get(arg0);
				if(ask(p)){
					return p;
				}
				return null;
			}

			public Product put(ProdId arg0, Product arg1) {
				throw new UnsupportedOperationException();
			}

			public Product remove(Object arg0) {
				throw new UnsupportedOperationException();
			}

			public void putAll(Map<? extends ProdId, ? extends Product> arg0) {
				throw new UnsupportedOperationException();
				
			}

			public void clear() {
				throw new UnsupportedOperationException();
				
			}

			public Set<ProdId> keySet() {
				return new Set<ProdId>(){

					public int size() {
						// TODO Auto-generated method stub
						return 0;
					}

					public boolean isEmpty() {
						return false;
					}

					public boolean contains(Object arg0) {
						try{
							return containsKey((ProdId)arg0);
						}catch(ClassCastException c){
							return false;
						}
						
					}

					public Iterator<ProdId> iterator() {
						return new Iterator<ProdId>(){
							Iterator<Product> iter = base.contents.values().iterator();
							ProdId next;

							private ProdId getNext(){
								Product temp;
								do{
									temp= iter.next();
								}while(temp!=null && ask(temp));
								if(temp==null){
									return null;
								}
								else{
									try{
										return temp.getId();
									}catch(ZZIllegalEx e){
										return getNext();
									}
								}
								
							}
							public boolean hasNext() {
								next = getNext();
								return getNext()!=null;
							}

							public ProdId next() {
								if(next!=null){
									ProdId temp = next;
									next=null;
									return temp;
								}								
								return getNext();
							}

							public void remove() {
								throw new UnsupportedOperationException();								
							}
							
						};
					}

					public Object[] toArray() {						
						return toArray(new ProdId[]{});
					}

					public <T> T[] toArray(T[] arg0) {
						Vector<ProdId> vec = new Vector();
						for(Product p: base.contents.values())
							try{
								if(ask(p))
									vec.add(p.getId());
							}catch(ZZIllegalEx e){};
						
						return vec.toArray(arg0);
						return null;
					}

					public boolean add(ProdId arg0) {
						throw new UnsupportedOperationException();
					}

					public boolean remove(Object arg0) {
						throw new UnsupportedOperationException();
					}

					public boolean containsAll(Collection<?> arg0) {
						throw new UnsupportedOperationException();
					}

					public boolean addAll(Collection<? extends ProdId> arg0) {
						throw new UnsupportedOperationException();
					}

					public boolean retainAll(Collection<?> arg0) {
						throw new UnsupportedOperationException();
					}

					public boolean removeAll(Collection<?> arg0) {
						throw new UnsupportedOperationException();
					}

					public void clear() {
						throw new UnsupportedOperationException();
					}

					
				};
			}

			public Collection<Product> values() {
				return new Collection<Product>(){
					
				};
			}

			public Set<Entry<ProdId, Product>> entrySet() {
				// TODO Auto-generated method stub
				return null;
			}


		};*/
