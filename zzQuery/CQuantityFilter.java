package zzQuery;

import zzProduct.Product;

public class CQuantityFilter extends IntFilter {

	public CQuantityFilter() {
		super("Iloœæ pocz¹tkowa", "Filtrowanie wed³ug iloœci w magazynie");
		
	}

	@Override
	protected Integer getQuerriedInt(Product p) throws Exception {
		return new Integer(Math.round(p.getCurrQ()));
	}

}
