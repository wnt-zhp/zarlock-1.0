package zzProductBase;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import zzId.ProdId;
import zzOrdering.Ordering;
import zzQuery.Query;


public abstract class AbstractProductBaseImmutable 
  implements ProductBaseImmutable  {
  
  /// Lists ///
  private List<ProductBaseImmutable.ProductsChangedListener> prodAddedListeners 
    = new Vector<ProductBaseImmutable.ProductsChangedListener> ();
  
  private List<ProductBaseImmutable.ProductsChangedListener> prodRemovedListeners
    = new LinkedList<ProductBaseImmutable.ProductsChangedListener> ();
  
  private List<ProductBaseImmutable.SortedListener> sortedListeners 
    = new LinkedList<ProductBaseImmutable.SortedListener> ();
  
  private List<ProductBaseImmutable.FilteredListener> filteredListeners 
    = new LinkedList<ProductBaseImmutable.FilteredListener>();
  
  private List<ProductBaseImmutable.LoadedLisntener> loadedLisnteners 
  = new LinkedList<ProductBaseImmutable.LoadedLisntener>();
  
  public void addProductAddedListener(ProductsChangedListener l) {
    prodAddedListeners.add(l);    
  }

  public void addProductRemovedListener(ProductsChangedListener l) {
    prodRemovedListeners.add(l);    
  }

  public void addSortedListener(SortedListener l) {
    sortedListeners.add(l);    
  }

  public void addFilteredListener(FilteredListener l) {
    filteredListeners.add(l);    
  }
  
  public void addLoadedListener(LoadedLisntener l){
    loadedLisnteners.add(l);
  }
  
  public void removeProductAddedListener(ProductsChangedListener l) {
    prodAddedListeners.remove(l);    
  }

  public void removeProductRemovedListener(ProductsChangedListener l) {
    prodRemovedListeners.remove(l);    
  }

  public void removeSortedListener(SortedListener l) {
    sortedListeners.remove(l);    
  }

  public void removeFilteredListener(FilteredListener l) {
    filteredListeners.remove(l);    
  }
  
  public void removeLoadedListener(LoadedLisntener l){
    loadedLisnteners.remove(l);
  }
  
  protected synchronized void fireProductAdded(Vector<ProdId> ids){
    for(ProductBaseImmutable.ProductsChangedListener l : prodAddedListeners){
      l.actionPerformed(ids);
    }
  }
  
  protected synchronized void fireProductRemoved(Vector<ProdId> ids){
    for(ProductBaseImmutable.ProductsChangedListener l : prodRemovedListeners){
      l.actionPerformed(ids);
    }
  }
  
  protected synchronized void fireSorted (Vector<Ordering> orderings){
    for(ProductBaseImmutable.SortedListener l : sortedListeners){
      //l.actionPerformed(orderings);
    }
  }
  
  protected synchronized void fireFiltered (ProductBaseImmutable filtered, Vector<Query> query){
    for(ProductBaseImmutable.FilteredListener l : filteredListeners){
      //l.actionPerformed(filtered,query);
    }
  }
  
  protected void fireLoaded(){
    for(LoadedLisntener l:loadedLisnteners){
      //l.actionPerformed();
    }
  }


}
