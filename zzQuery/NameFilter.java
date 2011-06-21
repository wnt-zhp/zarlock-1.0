package zzQuery;

import zzProduct.Product;

public class NameFilter extends StringFilter {
	

	public NameFilter() {
		super("Nazwa: " , "Filtruje produkty zawierajace/nie zawieraj¹ce" +
				" danego ci¹gu znaków w nazwie");

	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getName();
	}

}
