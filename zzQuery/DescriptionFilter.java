package zzQuery;

import zzProduct.Product;

public class DescriptionFilter extends StringFilter {

	public DescriptionFilter() {
		super("Opis", "Filtruje produkty zawieraj¹ce/nie " +
				"zawieraj¹ce w opisie danegoci¹gu znaków");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getDescription();
	}

}
