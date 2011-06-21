package zzTests;

import java.awt.geom.CubicCurve2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import zzDataBase.StaticContent;
import zzProduct.Product;
import zzQuery.Query;

import zzQuery.CQuantityFilter;
import zzQuery.DescriptionFilter;
import zzQuery.Filter;
import zzQuery.NameFilter;
import zzQuery.Operand;

public class FilterTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Filter f1 = new NameFilter();
		Query q1 = f1.getFilter("Osa", true);
		Filter f2 = new DescriptionFilter();
		Query q2 = f2.getFilter("Osa", true);
		System.out.print(q1.compareTo(q2));
		zzTestMain.setUpDataBase(20);
		Product p = StaticContent.sc.getDataBase().getSorted().get(1);		
		BufferedReader br = new BufferedReader ( new InputStreamReader (System.in));
		Filter f3 =  new NameFilter();
		/*System.out.println(p.getName());
		String str = br.readLine();
		Query q3 = f3.getFilter(str,false), q4 = f3.getFilter(str,true);
		System.out.println(q3.ask(p));
		System.out.println(q4.ask(p));*/
		
		/*Filter f4 = new CQuantityFilter();
		System.out.println(p.getCurrQ());
		String str = br.readLine();
		Query q3 = f4.getFilter(str,Operand.GREATER), q4 = f4.getFilter(str,Operand.SMALLER_OR_EQUAL);
		System.out.println(q3.ask(p));
		System.out.println(q4.ask(p));*/
		
		for(Product p2: StaticContent.sc.getDataBase().getSorted()){
			System.out.println(p2.getName());
		}
		
		String str = br.readLine();
		Query q3 = f3.getFilter(str,false), q4 = f3.getFilter(str,true);
		
		for(Product p2: StaticContent.sc.getDataBase().performQuery(q3).getSorted()){
			System.out.println("ZZ0" + p2.getName());
		}
		
		for(Product p2: StaticContent.sc.getDataBase().performQuery(q4).getSorted()){
			System.out.println("ZZ1" + p2.getName());
		}
		
		for(Product p2: StaticContent.sc.getDataBase().performQuery(new Query[]{q3}).getSorted()){
			System.out.println("ZZ0" + p2.getName());
		}
		
		for(Product p2: StaticContent.sc.getDataBase().performQuery(new Query[]{q4}).getSorted()){
			System.out.println("ZZ1" + p2.getName());
		}
	}

}
