package zzDataBase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZIoBadFormatEx;
import zzMyDate.MyDate;
import zzProduct.Product;

public class Day implements Comparable<Day> {
	
	private MyDate date;

	private String description;

	private Vector<Mealv3> meals = new Vector<Mealv3>();

	private String name;

	public Day(String str) throws ZZInternalDuplicateEntryEx {
		super();
		this.parseString(str);
		initialize();

	}

	public Day(String name, MyDate date, String description) throws ZZInternalDuplicateEntryEx {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.date = date;
		this.description = description;
		initialize();
	}

	public Mealv3 addMeal(String name, String desc)throws ZZInternalDuplicateEntryEx{
		Mealv3 m =  new Mealv3(date, name, desc);		
		if(find(new cmp(name))!=null) 
			throw new ZZInternalDuplicateEntryEx("Posi³ki s¹ identyfikowane po nazwie..." +
					" Niestety \njest ju¿ posi³ek o teh nazwie");
		meals.add(m);
		return m;
	}
	
	public void removeMeal(String name){
		Mealv3 m;
		if((m=find(new cmp(name)))!=null)
			meals.remove(m);
	}
	
	public Mealv3 getMeal(String name){
		return find(new cmp(name));
	}
		
	
	public int compareTo(Day arg0) {
		return arg0.date.compareTo(date);
	}

	public String getDescription() {
		return description;
	}

	public List<Mealv3> getMeals() {
		return Collections.unmodifiableList(meals);
	}

	public String getName() {
		return name;
	}

	private void initialize() throws ZZInternalDuplicateEntryEx {
		if (!StaticContent.sc.getDays().contains(this)) {
			StaticContent.sc.getDaysMod().add(this);
			System.out.println("foo");
		}else{
			throw new ZZInternalDuplicateEntryEx("Duplicate day");
		}
	}

	public void parseString(String str) {
		StringTokenizer tok = new StringTokenizer(str, "\n");
		setName(tok.nextToken().replace(";",",") );
		try {
			date = new MyDate(tok.nextToken().replace(";",",") );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setDescription(tok.nextToken().replace(";",",") );
		int i = (int) Float.parseFloat(tok.nextToken());
		for (int j = 0; j < i; j++) {
			try {
				meals.add(new Mealv3(tok.nextToken()));
			} catch (ZZIoBadFormatEx e) {
				e.printStackTrace();
			}
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		String result = new String();
		result += name.replace(",",";")  + " \n";
		result += date.toString().replace(",",";") + " \n";
        if(description==null || description.trim().length()==0){
           result  += "IMPLEMENTATION DEFINED" + "\n";
        }else{        
		result += description.replace(",",";") + " \n";
        }
		int i = meals.size();
		result += i + " \n";
		for (Mealv3 m : meals) {
			try {
				result += m.stringify() + " \n";
			} catch (ZZIllegalEx e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public MyDate getDate() {
		return date;
	}

	@Override
	public boolean equals(Object arg0) {
		if(!this.getClass().isInstance(arg0)) return false;
		return this.compareTo((Day)arg0)==0;
	}

	public void print11A(BufferedWriter buffwr, Vector<Boolean> printmod, Vector<Integer> ilosciOsob) throws IOException, ZZIllegalEx{
		buffwr.write("ZWI¥ZEK HARCERSTWA POLSKIEGO\n");
		buffwr.write("Zgrupowanie obozów w " + StaticContent.miejsceDope³niacz + "\n");
		buffwr.write("Organizator: " + StaticContent.organizator+ "\n\n");
		buffwr.write("ZAPOTRZEBOWANIE ¯YWNOŒCIOWE\n");
		buffwr.write("Dnia:" + getDate() + "\n");
		buffwr.write("Zatwierdzona w planie finansowym dzienna stawka ¿ywnoœciowa: " + StaticContent.stawka + "z³\n\n");
		Vector<Mealv3> posi³kiDodatkowe = new Vector();
		Vector<Mealv3> posi³kiZwyk³e = new Vector();
		if(printmod.size() != meals.size()) throw new ZZIllegalEx();
		for(int i = 0; i< printmod.size(); i++){
			if(printmod.get(i).booleanValue()){
				posi³kiDodatkowe.add(meals.get(i));
			}else{
				posi³kiZwyk³e.add(meals.get(i));
			}
		}
		for(Mealv3 m: posi³kiZwyk³e){
			buffwr.write("\n" + m.getName().toUpperCase() + "\n" + "Wyszczególnienie: \n");
			for(MealExpenditure me: m.getContents().values()){
				Product p = StaticContent.sc.getDataBase().getById(me.getPId());
				buffwr.write(p.getName() + " - " + (float) Math.round(me.ile*100)/100f + " " + p.getUnit() + "\n");
			}
		}
		buffwr.write("\n\n");
		if(posi³kiDodatkowe.size()!=0){
			buffwr.write("Posi³ki dodatkowe - wyszczególnienie:\n");
			for(Mealv3 m: posi³kiDodatkowe){
				buffwr.write(m.getName().toUpperCase() + "\n"  + m.getDescription()+ "\n");
				for(MealExpenditure me: m.getContents().values()){
					Product p = StaticContent.sc.getDataBase().getById(me.getPId());
					buffwr.write(p.getName() + " - " + Math.round(me.ile*100)/100 + " " + p.getUnit() +"\n");
				}
			}
		}
		buffwr.write("\n\n\n");
		buffwr.write("Dane o stanie ¿ywionych:\n");
		buffwr.write("Liczba uczestników obozu: " + ilosciOsob.get(0) + "\n");
		buffwr.write("Liczba kadry obozu: " + ilosciOsob.get(1) + "\n");
		buffwr.write("Liczba innych osób: " + ilosciOsob.get(2) + "\n");
		int ile = ilosciOsob.get(0) +  ilosciOsob.get(1)+ilosciOsob.get(2);
		buffwr.write("Stan ¿ywionych razem: " + ile + "\n" );
		
		float koszt=0;
		for(Mealv3 m: meals){
			for(MealExpenditure me: m.getContents().values()){
				Product p = StaticContent.sc.getDataBase().getById(me.getPId());
				koszt += p.getPrice() * me.ile;
			}
		}

		
		float kosztOsoby = (float) Math.round((koszt/ile)*100)/100f;
		System.out.println("k " + koszt + "i " + ile + "k/i " + kosztOsoby);
		buffwr.write("Przeciêtny koszt wy¿ywienia jednej osoby:" + kosztOsoby + "z³.\n");
		buffwr.flush();
		
	}
	
	public void print13A(BufferedWriter buffwr) throws IOException{
		buffwr.write("\"POBRANE ARTYKU£Y ¯YWNOŒCIOWE DO OBOZOWEJ KUCHNI W DNIU:" + getDate() +"\",,,,,\n");
		buffwr.write("\"lp\",\"nazwa artyku³u\",\"jednostka miary\",\"Iloœæ\",\"Cena\",\"Wartoœæ\",\n");
		int i = 0;
		float razem=0;
		for(Mealv3 m: meals){
			for(MealExpenditure me: m.getContents().values()){
				Product p = StaticContent.sc.getDataBase().getById(me.getPId());
				try {
					razem += me.ile * p.getPrice();
					buffwr.write("" + 
							++i + ","+
							"\"" + p.getName() + "\","+
							"\"" + p.getUnit() + "\"," + 
							"\"" + (float) Math.round(me.ile*100f)/100f + "\"," + 
							"\"" + (float) Math.round(p.getPrice()*100)/100f + "\"," +
							"\"" + (float) Math.round(p.getPrice() * me.ile*100)/100f   +"\",\n");
				} catch (ZZIllegalEx e) {
					buffwr.write("B³ad zapisu nietety :(,,,,,,\n");
					e.printStackTrace();
				} 
			}
		}
	

		buffwr.write(",,,," + "\"" + "Razem: " + "\"," + Math.round(100*razem)/100f + "\n" );
		buffwr.flush();
	}
	
	/*public TableModel getTableModelFor13A(){
		//DefaultTableModel model = new DefaultTableModel();
		Vector result = new Vector();
		Vector names = new Vector();
		names.add("lp");
		names.add("prod_name");
		names.add("prod_unit");
		names.add("prod_quantity");
		names.add("prod_val");
		names.add("prod_val_round");
		
		for(Mealv3 m : meals){
			for
		}
	}*/
	
	private class cmp implements Comparable<Mealv3>{
		String name;

		public cmp(String name) {
			super();
			// TODO Auto-generated constructor stub
			this.name = name;
		}

		public int compareTo(Mealv3 arg0) {
			return arg0.getName().compareTo(name);
		}		
	}
	
	private Mealv3 find(cmp c){
		for(Mealv3 m :meals){
			if(c.compareTo(m)==0) return m;
		}
		return null;
	}
	
	
}
