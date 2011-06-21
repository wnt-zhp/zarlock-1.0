package zzQuery;

import zzMyDate.MyDate;
import zzProduct.Product;

public class ExpiryDateFilter extends DateFilter { 

	public ExpiryDateFilter() {
		super("Data przydatnoœci do spo¿.", "Filtorwanie wed³ug przydtatnoœci do spo¿., podaj j¹ w foracie DD/MM/YY");
	}
	
	@Override
	protected MyDate getQuerriedDate(Product p) throws Exception {
		return p.getExpiryDate();
	}
	

}
