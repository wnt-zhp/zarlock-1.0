package zzQuery;

import zzMyDate.MyDate;
import zzProduct.Product;

public class BookingDateFilter extends DateFilter {
	
	public BookingDateFilter() {
		super("Data Ksiêgowania", "Filtorwanie wed³ug daty ksiêgowania, podaj j¹ w foracie DD/MM/YY");
	}
	

	@Override
	protected MyDate getQuerriedDate(Product p) throws Exception{
		return p.getDateOfBooking();
	}

}
