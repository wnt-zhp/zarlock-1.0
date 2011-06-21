/*
 * Created on 2006-04-26
 * Author jb
 *
 */
package zzProductBase;

import java.util.Vector;

import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZIoBadFormatEx;
import zzId.ProdId;
import zzProduct.Product;
import zzProduct.SimpleProduct;
import zzUser.Priveleges;
import zzUser.User;

public class SimpleProductBaseMutable extends SimpleProductBase
    implements
      ProductBaseMutable {

  public SimpleProductBaseMutable() {
    super();
  }

  public SimpleProductBaseMutable(SimpleProductBase foo) {
    super(foo);
  }
  
  public SimpleProductBaseMutable(SimpleProductBaseMutable foo){
    super(foo);
  }

  public void addProduct(Product _prod) throws ZZIllegalEx,ZZInternalDuplicateEntryEx{
    if(User.getPriveleges()==Priveleges.BROWSE) 
       throw new ZZIllegalNotEnoughPrivelagesEx("U¿ytkownik " + User.getName()+
           " nie ma wystarczaj¹cych uprawnieñ by modyfikowaæ baze danych");
    if(contents.containsKey(_prod.getId()))
      throw new ZZInternalDuplicateEntryEx("W bazie danych ju¿ jest produkt o id" +
          _prod.getId() + "ma on nazwê " + contents.get(_prod.getId()) + "Produkt" +
          " który mia³ zostaæ dodany ma nazwê" + _prod.getId()+ "\n Debug Info" +
          "Produkt Oryginalny:" + contents.get(_prod.getId()) + "\nProdukt nie dodany"+
          _prod);    
    contents.put(_prod.getId(), _prod);
    Vector<ProdId> foo = new Vector<ProdId> ();
    foo.add(_prod.getId());
    fireProductAdded(foo);
  }

  public void addProduct(String _product) throws 
  ZZIllegalEx, ZZInternalDuplicateEntryEx, ZZIoBadFormatEx {
    addProduct(new SimpleProduct(_product));
  }

  public void removeProduct(ProdId id) throws ZZIllegalNotEnoughPrivelagesEx {
    if(User.getPriveleges()==Priveleges.BROWSE) 
      throw new ZZIllegalNotEnoughPrivelagesEx("U¿ytkownik " + User.getName()+
          " nie ma wystarczaj¹cych uprawnieñ by modyfikowaæ baze danych");    
    contents.remove(id);
    Vector<ProdId> foo = new Vector<ProdId> ();
    foo.add(id);
    fireProductRemoved(foo);
 }

 /* public void removeProducts(ProductBaseMutable foo) 
  throws ZZIllegalNotEnoughPrivelagesEx, ZZIllegalEx {
    for(Product p: foo){
      removeProduct(p.getId());
    }
  }*/

  /*public int Expenditure(Product _product, int quantity, MyDate date, String tytulem)
  throws ZZInternalNoSuchEntryEx, ZZIllegalEx {
    return Expenditure(_product.getId(), quantity,date,tytulem);
  }

  public int Expenditure(ProdId _productNo, int quantity, MyDate date, String tytulem) 
  throws ZZInternalNoSuchEntryEx, ZZIllegalEx {
    if(User.getPriveleges()==Priveleges.BROWSE) 
      throw new ZZIllegalNotEnoughPrivelagesEx("U¿ytkownik " + User.getName()+
          " nie ma wystarczaj¹cych uprawnieñ by modyfikowaæ baze danych");
    
    if(!contents.containsKey(_productNo))
      throw new ZZInternalNoSuchEntryEx("W bazie danych nie ma obiektu o id"
          + _productNo);
    return Expenditure.performExpenditure(contents.get(_productNo), quantity,date, tytulem);    
  }*/
}
