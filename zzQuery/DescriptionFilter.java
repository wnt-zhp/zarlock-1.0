package zzQuery;

import zzProduct.Product;

public class DescriptionFilter extends StringFilter {

	public DescriptionFilter() {
		super("Opis", "Filtruje produkty zawieraj�ce/nie " +
				"zawieraj�ce w opisie danegoci�gu znak�w");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getDescription();
	}

}
