package zzQuery;

import zzProduct.Product;

public class FacturaNoFilter extends StringFilter {

   public FacturaNoFilter() {
      super("Numer faktury","Wyszukuje w polu mumer faktury");
      // TODO Auto-generated constructor stub
   }

   @Override
   protected String getQuerriedString(Product p) throws Exception {
      return p.getFacturaNo();
   }

}
