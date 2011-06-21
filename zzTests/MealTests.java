package zzTests;

import zzDataBase.Day;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;

public class MealTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		zzTestMain.setUpDataBase(500);
		zzTestMain.setUpDays(100);
		Mealv3 m;
		Day d ;
		while (true){			
			try{
			d = StaticContent.sc.getDays().get(zzTestMain.rand.nextInt(5));
			m = d.getMeals().get(zzTestMain.rand.nextInt(6));
			break;
			}catch(Throwable t){
				
			};
		}
		String s = m.stringify();
		System.out.println(s);
		Mealv3 m2 = new Mealv3(s);
		System.out.println(m2.stringify());
		
		StaticContent.sc.setDays(new java.util.Vector<Day>());
		s = d.toString();
		System.out.println(s);
		Day d2 = new Day(s);
		System.out.println(d2);
		
		
			

	}

}
