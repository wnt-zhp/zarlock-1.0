package zzQuery;

import zzProduct.Product;

public class PriceFilter extends IntFilter {

	public PriceFilter() {
		super("Cena: ", "Filtrowanie wed�ug ceny");
	}

	@Override
	protected Integer getQuerriedInt(Product p) throws Exception {
		return new Integer(Math.round(p.getPrice()));
	}

}
