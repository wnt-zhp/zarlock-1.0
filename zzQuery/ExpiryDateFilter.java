package zzQuery;

import zzMyDate.MyDate;
import zzProduct.Product;

public class ExpiryDateFilter extends DateFilter { 

	public ExpiryDateFilter() {
		super("Data przydatno�ci do spo�.", "Filtorwanie wed�ug przydtatno�ci do spo�., podaj j� w foracie DD/MM/YY");
	}
	
	@Override
	protected MyDate getQuerriedDate(Product p) throws Exception {
		return p.getExpiryDate();
	}
	

}
