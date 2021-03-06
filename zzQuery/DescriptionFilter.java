package zzQuery;

import zzProduct.Product;

public class DescriptionFilter extends StringFilter {

	public DescriptionFilter() {
		super("Opis", "Filtruje produkty zawierające/nie " +
				"zawierające w opisie danegociągu znaków");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getQuerriedString(Product p) throws Exception {
		return p.getDescription();
	}

}
