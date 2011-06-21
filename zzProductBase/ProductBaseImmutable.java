/*
 * Created on 2006-04-22
 * Author jb
 *
 */
package zzProductBase;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import zzEx.ZZIllegalEx;
import zzEx.ZZIoEx;
import zzId.ProdId;
import zzOrdering.Ordering;
import zzProduct.Product;
import zzQuery.Query;

public interface ProductBaseImmutable {
  
  public Product getById(ProdId nr);
  
  ProductBaseImmutable performQuery(Query query);
  
  ProductBaseImmutable performQuery(Query query[]);  

  ProductBaseImmutable performQuery();
  
  Map<ProdId, Product> getDatabase();
  
  void sort(Ordering ordering);
  void sort(Ordering[] ordering);
  void sort(Collection<Ordering> foo);
  
  long getNumberOfElems();
  
  List<Product> getSorted ();
  List<Product> getSorted (Ordering ordering);
  
  void save() throws ZZIoEx, ZZIllegalEx;
  void load() throws ZZIoEx, ZZIllegalEx;
  
  public void addProductAddedListener(ProductsChangedListener l);
  public void addProductRemovedListener(ProductsChangedListener l);
  public void addSortedListener(SortedListener l);
  public void addFilteredListener(FilteredListener l);
  public void addLoadedListener(LoadedLisntener l);
  
  public void removeProductAddedListener(ProductsChangedListener l);
  public void removeProductRemovedListener(ProductsChangedListener l);
  public void removeSortedListener(SortedListener l);
  public void removeFilteredListener(FilteredListener l);
  public void removeLoadedListener(LoadedLisntener l);
  
  public interface ProductsChangedListener{
    /**
     * Note: that that database is immutable doesn't imply it wont change - it implies YOU wont change it ;-).
     * @param ids these are the id's of product that are added. ids.size() should be >= 1;
     * 
     */
    void actionPerformed(Vector<ProdId> ids);
  }
  
  public interface SortedListener{
    /**
     * Informs that database has beeb sorder, and if so what orderrigns had been user <br>
     * Also informs whether sorting is from scrath or you just re sort data. <br>
     * Because I use stable sorting it makes a difference
     * @param orderings.size() should be > 1
     */
    void actionPerformed(Vector<Ordering> orderings);
  }
  
  public interface FilteredListener{
    /**
     * Inform that database had been filtered.
     * 
     * @param filtered filtered database
     * @param query query used
     */
    void actionPerformed(ProductBaseImmutable filtered, Vector<Query> query);
  }
  
  public interface LoadedLisntener{
    void actionPerformed();
  }
  
}
