package zzTests;

import java.util.List;
import java.util.Date;
import java.util.Random;

import field.stringParser;

import zzDataBase.Day;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalDateExceptionEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzProduct.SimpleProduct;
import zzProductBase.SimpleProductBase;
import zzUser.Priveleges;
import zzUser.User;

public abstract class zzTestMain {
	
	static Random rand = new Random(new Date().getTime());
	
	static String nazwy[] = new String[] {
			"Barszcz, z uszkami",
			"Barszcz, bez uszek",
			"Czarna, polewka",
			"Kompletna, polewka",
			"Kotlet, z kota",
			"Kotlet, sojowy",
			"Zupa, z gwoŸdzia",
			"Kluszk,i z dziurk¹",
			"Kluski, bez dziurki"
	};
	
	static String opisy[] = new String[]{
		"Radioactive student inside",
		"Ambrozja",
		"Jak z Wod¹ ¯ycia, - piæ po przegotowaniu",
		"Smaczne",
		"Nektar i Ambrozja",
		"Thats life"
	};
	
	static String dniNazwy[] = new String[]{
		"Poniedzia³ek",
		"Wtorek",
		"Oktodzieñ",
		"Pentadzieñ",
		"Nie - dziela",
	};
	
	static String dniOpisy[] = new String[]{
		"Nie wiem co tu wpisaæ",
		"null",
		"void",
		"null, and void",
	};

  static public void setUpDataBase(int ile) throws Exception{
    SimpleBaseTests.setUpProduct();
    User.setName("Foo Name");
    User.setPriveleges(Priveleges.ROOTUSER);
    StaticContent.sc.setDataBase(SimpleBaseTests.returnNewBase(ile));
    StaticContent.sc.setToday(new MyDate());
   }
  
	static public void setUpDays(int ile){
		int ile2=0;
		List<Product> vector = StaticContent.sc.getDataBase().getSorted();
		List<Product> mut = ((SimpleProductBase)(StaticContent.sc.getDataBase())).getSortedMod();
		for(Product p : mut){
			Day d;
			try{
				d = new  Day(dniNazwy[rand.nextInt(dniNazwy.length)]
				                      ,p.getExpiryDate(),dniOpisy[rand.nextInt(dniOpisy.length)]);
			}catch(ZZInternalDuplicateEntryEx e){
				continue;
			} catch (ZZIllegalEx e) {
				continue;
			}
			for(int i = 0; i <4; i++){
				Mealv3 m;
				try {
					m = d.addMeal(nazwy[rand.nextInt(nazwy.length)],
							opisy[rand.nextInt(opisy.length)]);
				} catch (ZZInternalDuplicateEntryEx e1) {
					continue;
				}
				//System.out.println("i " + i );
				int ile3 =0;
				for(Product p2: mut){
					try {
						m.addExpenditure(
								p2,
								rand.nextInt((int) Math.floor(p.getCurrQ()))
						);
						if(ile3++==10){
							break;
						}
					} catch (ZZIllegalEx e) {
						//System.out.println(e);
						continue;
					} catch( Throwable t){
						continue;
					}
				}
				
			}			
			ile2++;
			if(ile<ile2){ 
				System.out.println("OK " + ile + ", " +ile2);
				break;						
			}
		}
	}

}
