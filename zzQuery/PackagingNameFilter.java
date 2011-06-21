package zzQuery;

import zzProduct.Product;

public class PackagingNameFilter extends StringFilter {

	public PackagingNameFilter() {
		super("Opakowanie", "Filtruje produkty zawieraj¹ce/nie zawieraj¹ce o nazwie opakowania" +
				" danego ci¹gu znków");
	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getPackaging();
	}

}
