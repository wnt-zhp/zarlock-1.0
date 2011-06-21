package zzQuery;

import zzMyDate.MyDate;
import zzProduct.Product;

public class BookingDateFilter extends DateFilter {
	
	public BookingDateFilter() {
		super("Data Ksi�gowania", "Filtorwanie wed�ug daty ksi�gowania, podaj j� w foracie DD/MM/YY");
	}
	

	@Override
	protected MyDate getQuerriedDate(Product p) throws Exception{
		return p.getDateOfBooking();
	}

}
