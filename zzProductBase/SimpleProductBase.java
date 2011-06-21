/*
 * Created on 2006-04-24
 * Author jb
 *
 */
package zzProductBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import zzEx.ZZIllegalEx;
import zzEx.ZZIoEx;
import zzEx.ZZIoFileNotFoundEx;
import zzId.ProdId;
import zzOrdering.Ordering;
import zzProduct.Product;
import zzProduct.SimpleProduct;
import zzQuery.Query;

public class SimpleProductBase extends
AbstractProductBaseImmutable {
	
	protected SortedMap<ProdId, Product> contents = Collections.synchronizedSortedMap( new TreeMap <ProdId, Product>());
	protected Vector <Product> lastSort = new Vector<Product>(); 
	
	public SimpleProductBase() {
		super();
		initialize();
	}
	
	public SimpleProductBase(SimpleProductBase foo){
		contents = Collections.synchronizedSortedMap( new TreeMap<ProdId, Product>(foo.contents));
		System.out.println("MapSize" + contents.size());
		lastSort = new Vector<Product>(contents.values());
		initialize();
	}
	
	
	private synchronized void initialize(){
		ProductBaseImmutable.ProductsChangedListener foo = 
			new ProductBaseImmutable.ProductsChangedListener(){
			public void actionPerformed(Vector<ProdId> ids) {
				lastSort = new Vector<Product> (contents.values());
			}     
		};
		addProductAddedListener(foo);
		addProductRemovedListener(foo);
	}
	
	public synchronized Product getById(ProdId nr){
		return contents.get(nr);
	}
	
	
	public  synchronized ProductBaseImmutable performQuery(Query query){
		SimpleProductBase result = new SimpleProductBase();
		for(Product p: contents.values()){
			if(query.ask(p)){
				try {
					result.contents.put(p.getId(), (SimpleProduct)p);
				} catch (ZZIllegalEx e) {
					e.printStackTrace();
				}
			}
		}
		Vector<Query> foo = new Vector<Query> ();
		foo.add(query);
		fireFiltered(result,foo);
		result.lastSort = new Vector(result.contents.values());
		return result;
	}
	
	public synchronized ProductBaseImmutable performQuery(Query[] query)  {
		/*SimpleProductBase result = new SimpleProductBase();
		 int i = 0;
		 System.out.println(i++ + "i" + query.length);
		 boolean OK = true;
		 for(Product p: contents.values()){ 
		 
		 for(Query q : query){
		 System.out.println(q.ask(p) + q.getClass().getName());
		 if(!q.ask(p)){
		 OK=false;
		 break;
		 }
		 } 
		 System.out.println(i++ + ""+ OK);   	 
		 if(OK) result.contents.put(p.getId(), p);
		 }
		 Vector<Query> foo = new Vector<Query> ();
		 foo.copyInto(query);
		 fireFiltered(result,foo);
		 for(Product p: result.contents.values()){
		 System.out.println(p.getName());
		 }
		 result.lastSort = new Vector(result.contents.values());
		 return result;*/
		SimpleProductBase result = new SimpleProductBase(this);
		for(Query q: query){
			result = (SimpleProductBase)result.performQuery(q);
		}
		result.lastSort = new Vector(result.contents.values());
		return result;
		
	}
	
	public synchronized List<Product> getSorted() {
		
		return Collections.unmodifiableList(lastSort);
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public synchronized List<Product> getSortedMod() {
		
		return lastSort;
	}
	
	/*public ProductBaseImmutable performQuery(Ordering ordering){
	 Collections.sort(lastSort = new Vector<Product>(contents.keySet()), ordering);
	 
	 }*/
	
	public Iterator<Product> iterator() {
		return new iter (contents);
	}
	
	
	private class iter implements Iterator<Product>{
		Iterator<ProdId> Keys;
		
		public iter(SortedMap<ProdId,Product> foo){
			Keys = foo.keySet().iterator();
		}
		
		public boolean hasNext() {
			return Keys.hasNext();
		}
		
		public Product next() {
			return contents.get(Keys.next());
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public synchronized void sort(Ordering ordering){
		Collections.sort(lastSort);
		Vector<Ordering> foo = new Vector<Ordering> ();
		foo.add(ordering);
		fireSorted(foo);
	}
	
	public synchronized void sort(Collection<Ordering> orderings){
		lastSort = new Vector<Product>(contents.values());
		for(Ordering o:orderings){
			Collections.sort(lastSort,o);
		}
		Vector<Ordering> foo = new Vector<Ordering> (orderings);
		fireSorted(foo);
	}
	
	public synchronized void sort(Ordering[] orderings){
		lastSort = new Vector<Product>(contents.values());
		for(Ordering o:orderings){
			Collections.sort(lastSort,o);
		}
		Vector<Ordering> foo = new Vector<Ordering> ();
		foo.copyInto(orderings);
		fireSorted(foo);
	}
	
	public synchronized ProductBaseImmutable performQuery() {
		return new SimpleProductBase(this);
	}
	
	public synchronized List<Product> getSorted(Ordering ordering) {
		sort(ordering);
		return Collections.unmodifiableList(lastSort);
	}
	
	public void reset(){
		contents.clear();
		lastSort.clear();
		this.fireProductRemoved(null);
	}
	
	public synchronized void setSaveToFile(File _saveHere){
		saveHere= _saveHere;
	}
	
	public synchronized void setloadFromFile(File _loadFromHere){
		loadFromHere= _loadFromHere;
	}
	
	private File saveHere;
	private File loadFromHere;
	
	public void save() throws ZZIoEx, ZZIllegalEx {
		try{
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(saveHere, false));
			
			for(Product p: contents.values() ){
				writer.write(p.toString());
				writer.write("\n");
				
			}
			writer.flush();
			writer.close();
		}catch(IOException foo){
			throw new ZZIoEx (foo.getMessage());
		}
	}
	
	public void load() throws ZZIoEx, ZZIllegalEx {
		try{  
			BufferedReader reader = new BufferedReader(
					new FileReader(loadFromHere));
			String buffer;
			while((buffer = reader.readLine()) != null){
				Product p = new SimpleProduct(buffer);
				contents.put(p.getId(),p);
			}
			SimpleProduct.setBeginId(contents.lastKey());
			lastSort=new Vector<Product> (contents.values());
		}catch(FileNotFoundException foo){
			throw new ZZIoFileNotFoundEx(foo.getMessage());
		}catch(IOException foo){
			throw new ZZIoEx(foo.getMessage());
		}finally{
			fireLoaded();
		}
	}
	
	public long getNumberOfElems() {
		return contents.size();
	}

	public Map<ProdId, Product> getDatabase() {
		return Collections.unmodifiableMap(contents);
	}
	

}


