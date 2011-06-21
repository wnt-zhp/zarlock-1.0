package zzQuery;

import zzProduct.Product;

public class NameFilter extends StringFilter {
	

	public NameFilter() {
		super("Nazwa: " , "Filtruje produkty zawierajace/nie zawieraj�ce" +
				" danego ci�gu znak�w w nazwie");

	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getName();
	}

}
