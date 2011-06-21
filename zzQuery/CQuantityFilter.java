package zzQuery;

import zzProduct.Product;

public class CQuantityFilter extends IntFilter {

	public CQuantityFilter() {
		super("Ilo�� pocz�tkowa", "Filtrowanie wed�ug ilo�ci w magazynie");
		
	}

	@Override
	protected Integer getQuerriedInt(Product p) throws Exception {
		return new Integer(Math.round(p.getCurrQ()));
	}

}
