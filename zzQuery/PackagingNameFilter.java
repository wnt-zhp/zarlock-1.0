package zzQuery;

import zzProduct.Product;

public class PackagingNameFilter extends StringFilter {

	public PackagingNameFilter() {
		super("Opakowanie", "Filtruje produkty zawieraj�ce/nie zawieraj�ce o nazwie opakowania" +
				" danego ci�gu znk�w");
	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getPackaging();
	}

}
