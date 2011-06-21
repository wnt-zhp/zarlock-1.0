/*
 * Created on 2006-03-04
 * Author jb
 *
 */
package zzProduct;


import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import zzDataBase.Expenditure;
import zzEx.ZZEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalExpiredEx;
import zzEx.ZZIllegalNoCathegoryEx;
import zzEx.ZZIllegalNotEnoughEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZIllegalAlreadyInitializedEx;
import zzEx.ZZIoBadFormatEx;
import zzEx.ZZIoEx;
import zzEx.ZZIllegalNotInitializedEx;
import zzId.ExpenditureId;
import zzId.IdComparator;
import zzId.IdGenerator;
import zzId.ProdId;
import zzMyDate.MyDate;
import zzUser.Priveleges;
import zzUser.User;

public class SimpleProduct extends AbstractProduct {
	
	private static final long serialVersionUID = 4567929599996369821L;
	
	public SimpleProduct() {
		super();
		id = idGen.getNextId();
	}
	/**
	 * @param text Sting with comma separated fields: <br>
	 * name (String), currentQuantity (float), unit name (String), price (float) 
	 * packaging name(String), bookingDate (Date), expiryDate (Date),
	 * description (String), FacturaNo (String), sQuantity (String),
	 * id (Int), unitId (Int), packagingId(Int)  
	 * <br> Note that dates are in polish (SIMPLE) locale DD.MM.YY
	 * @throws ZZIoEx
	 */
	public SimpleProduct(String text) throws ZZIoBadFormatEx{
		this.parseProduct(text);
	}
	
	public String toString(){   
		String result = new String ();
			   result+= name.replace(',',';') + ", " ;
			   System.out.println(result);
			   result += String.valueOf(getCurrQ()).replace(',',';') + ", " ;
			   result += getUnitMap().getTwo(unitId).toString().replace(',',';') + ", ";
			   result += price + ", ";
			   result += getPackagingMap().getTwo(packagingId).toString().replace(',',';') + ", " ;
			   result += bookingDate.toString().replace(',',';') + ", " ;
			   result += expiryDate.toString().replace(',',';') + ", " ;
			   result +=description.toString().replace(',',';') + ", ";
			   result += facturaNo.toString().replace(',',';') + ", " ;
			   result += String.valueOf(sQuantity).replace(',',';') + ", " ;
			   result += id.toString().replace(',',';') +", ";
			   result += String.valueOf(unitId).replace(',',';') + ", " ;
			   result += String.valueOf(packagingId).replace(',',';')+ ", " ;
			   result += expIdGen.getLastId().toString().replace(',',';')+ ", ";
			   result += String.valueOf(expenditures.entrySet().size()).replace(',',';');
		for(ExpenditureId i : expenditures.keySet()){
			Expenditure e = expenditures.get(i);
			result += ", ";
			result += String.valueOf(e.ile).replace(',',';');
			result += ", ";
			result += e.date.toString().replace(',',';');
			result += ", ";
			result += e.tytulem.toString().replace(',',';');
			result += ", ";
			result += i;
		}
		return result;  
	}
	
	public void parseProduct(String line)throws ZZIoBadFormatEx{
		StringTokenizer tokens = new StringTokenizer(line,",");
		name = tokens.nextToken();
		float currQ = Float.parseFloat(tokens.nextToken().replace(';',',')); //ignore current quantity - it is calculated from formula sQuantity - all expenditures
		String unitName = tokens.nextToken().replace(';',','); //Ignore unit name - it is supposed to be stored elsewhere
		price = Float.parseFloat(tokens.nextToken().replace(';',','));
		String packagingName = tokens.nextToken().replace(';',','); //Ignore packaging name - as above
		try{
			bookingDate = new MyDate( tokens.nextToken().replace(';',','));
			expiryDate = new MyDate( tokens.nextToken().replace(';',','));
		}
		catch(ParseException foo){
			throw new ZZIoBadFormatEx("Z³y format daty w pliku w "
					+"zasadzie u¿ytkownik tego b³êdu nei powinien widzieæ ("
					+ foo.getMessage() + ")");
		}
		description = tokens.nextToken().trim().replace(';',',');
		facturaNo = tokens.nextToken().trim().replace(';',',');
		sQuantity = Float.parseFloat(tokens.nextToken().replace(';',','));
		id = new ProdId();
		id.parse(tokens.nextToken().replace(';',','));
        System.out.println("id:" + id + ", " + AbstractProduct.idGen.getLastId());
        if(id.compareTo(AbstractProduct.idGen.getLastId())>0){
           AbstractProduct.setBeginId(id);
        }
		unitId = Integer.parseInt(tokens.nextToken().trim().replace(';',','));
		packagingId = Integer.parseInt(tokens.nextToken().trim().replace(';',','));
		
		if(!getUnitMap().containsOne(unitId)){
			getUnitMap().put(unitId,unitName);
		}
		
		if(!getPackagingMap().containsOne(packagingId)){
			getPackagingMap().put(packagingId, packagingName);
		}
		
		ExpenditureId lastExpenditureID = new ExpenditureId();
		lastExpenditureID.parse(tokens.nextToken().trim().replace(';',','));
		expIdGen = new IdGenerator<ExpenditureId>(lastExpenditureID);
		
		int repetitions = Integer.parseInt(tokens.nextToken().trim().replace(';',','));
		
		try{
			for(int ii = 0; ii < repetitions; ii++){
				
				float ile = Float.parseFloat(tokens.nextToken().replace(';',','));
				MyDate date = new MyDate(tokens.nextToken().replace(';',','));
				String tytulem = tokens.nextToken().trim().replace(';',',');
				ExpenditureId id = new ExpenditureId();
				id.parse(tokens.nextToken().trim().replace(';',','));
				expenditures.put(id, 
						new Expenditure(ile, date, tytulem));  
				System.out.println(ii);
			}
		}
		catch(ParseException foo){
			throw new ZZIoBadFormatEx("Z³y format daty odczytanej z pliku."
					+" Uzytkownik nie powinien zobaczyc tego b³êdu");
		}
		catch(NumberFormatException foo){
			throw new ZZIoBadFormatEx("Z³y format liczby odczytanej z pliku."
					+" Uzytkownik nie powinien zobaczyc tego b³êdu",foo);
		}

		/*
		 if((currQ + Expenditure.sumItUp(expenditures) - sQuantity)!=0){
		 try{
		 throw new ZZIoBadProductParseEx("Nielogicznoœæ wychodzi ¿e: iloœæ produktów "
		 +" w magazynie + to co z niego wysz³o nie jest równe iloœci produktów"
		 +"które s¹ na pó³kach", new SimpleProduct(this));
		 }catch(ZZEx foo){
		 throw new ZZIoBadFormatEx("B³¹d odczytu pliku");
		 } 
		 } */
	}
	
	public SimpleProduct(String _name, int _sQuantity, String _unit, float _price, 
			String _packaging, Date _bookingDate, Date _expiryDate, String _description){
		id = idGen.getNextId();
		name= new String (_name);
		sQuantity = _sQuantity;
		unitId = getUnitMap().getOne(_unit.trim());
		price = _price;
		packagingId = getPackagingMap().getOne(_packaging.trim());
		bookingDate = new MyDate(_bookingDate);
		expiryDate = new MyDate (_expiryDate);
		description = _description;
		facturaNo = new String();
	}
	
	public SimpleProduct(String _name, int _sQuantity, int _unit, float _price, 
			int _packaging, Date _bookingDate, Date _expiryDate, String _description){
		id = idGen.getNextId();
		name= new String (_name);
		sQuantity = _sQuantity;
		unitId = _unit;
		price = _price;
		packagingId = _packaging;
		bookingDate = new MyDate (_bookingDate);
		expiryDate = new MyDate (_expiryDate);
		description = _description;
		facturaNo = new String();
		
	}  
	
	public boolean equals(Product foo) throws ZZEx{
		return id.equals(foo.getId());
	}
	
	public SimpleProduct(SimpleProduct foo){		
		id.setTo(foo.id);
		name = new String (foo.name);
		sQuantity = foo.sQuantity;
		unitId = foo.unitId;
		price = foo.price;
		packagingId =foo.packagingId;
		bookingDate = new MyDate(foo.bookingDate);
		expiryDate = new MyDate (foo.expiryDate);
		expenditures = new TreeMap<ExpenditureId,Expenditure> (foo.expenditures);    
		description = new String (foo.description);
	};
	
	private void checkSet(Object foo) throws ZZIllegalEx {
		if(User.getPriveleges().equals(Priveleges.BROWSE)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
					"Próba dostêpu do zmiany danych przez u¿ytkownika, który" + 
			" nie ma wystarczaj¹cych uprawnien");
		}  
		if(foo != null){
			throw new ZZIllegalNotInitializedEx("Próba powtónego zainicjalizowania pola");
		}
	}
	
	private void checkChange(Object foo) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx{
		if(! User.getPriveleges().equals(Priveleges.ROOTUSER)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
			"Próba zmiany danych przez u¿ytkownika bez wystarczaj¹cych uprawnien");
		};
		if(foo == null){
			throw new ZZIllegalNotInitializedEx(
			"Próba zmiany nie zainicjalizowanego pola");
		}
	}
	
	private void checkSet(int foo) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		if(User.getPriveleges().equals(Priveleges.BROWSE)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
					"Próba dostêpu do zmiany danych przez u¿ytkownika, który" + 
			" nie ma wystarczaj¹cych uprawnien");
		}  
		if(foo != -1){
			throw new ZZIllegalNotInitializedEx("Próba powtónego zainicjalizowania pola");
		}
	}
	
	private void checkChange(int foo) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx{
		if(! User.getPriveleges().equals(Priveleges.ROOTUSER)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
			"Próba zmiany danych przez u¿ytkownika bez wystarczaj¹cych uprawnien");
		};
		if(foo == -1){
			throw new ZZIllegalNotInitializedEx(
			"Próba zmiany nie zainicjalizowanego pola");
		}
	}
	
	private void checkSet(float foo) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		if(User.getPriveleges().equals(Priveleges.BROWSE)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
					"Próba dostêpu do zmiany danych przez u¿ytkownika, który" + 
			" nie ma wystarczaj¹cych uprawnien");
		}  
		if(foo != -1.0f){
			throw new ZZIllegalNotInitializedEx("Próba powtónego zainicjalizowania pola");
		}
	}
	
	private void checkChange(float foo) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx{
		if(! User.getPriveleges().equals(Priveleges.ROOTUSER)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
			"Próba zmiany danych przez u¿ytkownika bez wystarczaj¹cych uprawnien");
		};
		if(foo == -1.0f){
			throw new ZZIllegalNotInitializedEx(
			"Próba zmiany nie zainicjalizowanego pola");
		}
	}
	public void setName(String _name) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkSet(name);
		name = new String(_name);
	}
	
	
	public void setId(ProdId _id) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(id);
		id = new ProdId(_id);
	}
	
	/*public void changeId(ProdId _id) throws ZZIllegalEx,
	 ZZIllegalNotEnoughPrivelagesEx {
	 checkChange(id);
	 id.setTo(_id);
	 }*/
	
	public ProdId getId(){
		return id;
	}
	
	public void setSQuantity(float _quant) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkSet(sQuantity);
		sQuantity = _quant;
		
	}
	
	public void setUnitId(int _id) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(unitId);
		if(!getUnitMap().containsOne(new Integer(_id))){
			throw new ZZIllegalNoCathegoryEx("Nie istnieje taki identyfikator"
					+" jednostki towaru");
		}
		unitId = _id;
		
	}
	
	public void setUnit(String _Unit) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkSet(unitId);
		Integer foo = getUnitMap().getOne(_Unit);
		if(foo==null){
			throw new ZZIllegalNoCathegoryEx ("Nie istnieje taka nazwa jednostki");
		}
		unitId = foo.intValue();
	}
	
	public void setDescription(String desc) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(description);
		description = new String (desc);
	}
	
	public void setFacturaNo(String _No) throws  ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(facturaNo);
		facturaNo = new String (_No);   
		
	}
	
	public void setPackagingId(int _id) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkSet(packagingId);
		if(!getPackagingMap().containsOne( new Integer (_id))){
			throw new ZZIllegalNoCathegoryEx("Nie ma takiego indentyfikatora opakowania");
		}
		packagingId = _id;
	}
	
	public void setPackaging(String _Unit) throws ZZIllegalEx{
		checkSet(packagingId);
		Integer foo = getPackagingMap().getOne(_Unit);
		if(foo==null){
			throw new ZZIllegalNoCathegoryEx ("Nie istnieje taka nazwa opakowania");
		}
		packagingId = foo.intValue();
	}
	
	public void setDateOfBooking(MyDate _bookingBate) throws ZZIllegalEx{
		checkSet(bookingDate);
		bookingDate = new MyDate ( _bookingBate);
	}
	
	public void changeName(String _name) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkChange(name);
		name = new String (_name);
	}
	
	public void changeSQuantity(float _quant) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx, ZZIllegalNotEnoughEx {
		checkChange(sQuantity);
		if((_quant - Expenditure.sumItUp(expenditures.values())) < 0.0f ){
			throw new ZZIllegalNotEnoughEx ("Próbujesz zmieniæ pocz¹tkow¹ iloœæ"
					+ " produktu " + name + " w ten sposób ze na stanie magazynu" 
					+ " by³aby ujemna ilosc");
		}
		sQuantity = _quant;    
	}
	
	public void changeUnitId(int _id) throws ZZIllegalEx{
		checkChange(id);
		if(!getUnitMap().containsOne(new Integer (_id))){
			throw new ZZIllegalNoCathegoryEx ("Nie ma takiego identyfikatora jednostki");
		}
		unitId = _id;
	}
	
	public void changeUnit(String _Unit) throws ZZIllegalEx {
		checkChange(unitId);
		if(!getUnitMap().containsTwo(_Unit.trim())){
			throw new ZZIllegalNoCathegoryEx ("Nie ma takiej jendostki towaru");
		}
		unitId = getUnitMap().getOne(_Unit).intValue();
	}
	
	public void changeDescription(String desc) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkChange(description);
		description = new String (desc);
	}
	
	public void changeFacturaNo(String _No) throws ZZIllegalEx,
	ZZIllegalNotEnoughPrivelagesEx {
		checkChange(facturaNo);
		facturaNo = new String (_No);
	}
	
	public void changePackagingId(int _id) throws ZZIllegalEx {
		checkChange(packagingId);
		if(!getPackagingMap().containsOne( new Integer (_id))){
			throw new ZZIllegalNoCathegoryEx ("Nie ma takiego identyfikatora opakowania");
		}
		packagingId = _id;
	}
	
	public void changePackaging(String _Unit) throws ZZIllegalEx{
		checkChange(packagingId);
		if(!getPackagingMap().containsTwo(_Unit)){
			throw new ZZIllegalNoCathegoryEx ("Nie ma takiego typu opakowania");
		}
		packagingId = getPackagingMap().getOne(_Unit).intValue();
	}
	
	public void changeDateOfBooking(MyDate _BookingDate) throws ZZIllegalEx {
		checkChange(bookingDate);
		for(int i = 0; i < expenditures.size(); i++){
			for(Expenditure e :expenditures.values()){
				if(e.date.before(_BookingDate))
				throw new ZZIllegalEx ("Towar nie moze zostaæ ywdany zanim wejdzie do magazynu");
			}
		}
		bookingDate = _BookingDate;
		
	}
	
	private String CopyStr(String foo){
		if(foo!=null){
			return new String(foo);
		}
		return null;
	}
	
	public String getName() {
		return CopyStr(name);
	}
	
	public float getSQuantity() {
		return sQuantity; 
	}
	
	public int getUnitId() {
		return unitId;
	}
	
	public String getUnit() {
		return CopyStr(getUnitMap().getTwo(unitId));
	}
	
	public String getDescription() {
		return CopyStr(description);
	}
	
	public String getFacturaNo() {
		return CopyStr(facturaNo);
	}
	
	public int getPackagingId() {
		return packagingId;
	}
	
	public String getPackaging() {
		return CopyStr(getPackagingMap().getTwo(packagingId));
	}
	
	public MyDate getDateOfBooking() {
		return new MyDate(bookingDate);
	}
	
	public float getCurrQ() {
		return sQuantity - Expenditure.sumItUp(expenditures.values());
	}
	
	public Vector<Map.Entry<ExpenditureId,Expenditure> > getExpenditures() {
		return new Vector<Map.Entry<ExpenditureId,Expenditure> > (expenditures.entrySet());
	}
	
	public Vector<Expenditure> getExpendituresNotTheirIds() {
		return new Vector<Expenditure> (expenditures.values());
	}
	
	public Iterable<Map.Entry<ExpenditureId,Expenditure> > getUncheckedExpenditures() {
		return expenditures.entrySet();
	}
	
	public ExpenditureId performExpenditure( float _number, MyDate _date, String tytulem) throws ZZIllegalEx,
	ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		try{
			if(_date.before(bookingDate)){
				throw new ZZIllegalEx("Towar nie mo¿e wyjœæ z magazynu zanim do niego wejdzie");
			}
			if(_date.after(expiryDate)){
				throw new ZZIllegalEx ("Nie mo¿na wydaæ nieœwierzego produktu");
			}
			if((sQuantity - _number - Expenditure.sumItUp(expenditures.values())) < 0){
				throw new ZZIllegalNotEnoughEx("Nie mo¿es wydaæ wiêcej niz masz na" + 
				"magazynie" );
			}
		}catch(NullPointerException e){
			throw new ZZIllegalNotInitializedEx("Nie zainiclalizowano pola", e);
		}
		if(tytulem==null || tytulem.trim().length()==0)
			throw new ZZIllegalEx ("Musisz podaæ dlaczego wydajesz produkt");
		Expenditure temp = new Expenditure(_number,_date, tytulem);
		ExpenditureId x786 = expIdGen.getNextId();
		expenditures.put(x786,temp);
		return x786;
	}
	
	public boolean isExpired(MyDate _today) {
		return _today.after(expiryDate);
	}
	
	public long toExpiry(MyDate _today) {    
		return (expiryDate.distanceInDays(_today));
	}
	
	public void setExpiryDate(MyDate _date) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(expiryDate);
		expiryDate= new MyDate (_date);    
	}
	
	public void changeExpiryDate(MyDate _date) throws ZZIllegalEx, ZZIllegalEx {
		checkChange(expiryDate);
		for(Expenditure e : expenditures.values()){
			if(_date.before(e.date)){
				System.out.println("Ecpenditure:" + e.toString() + "\ndate1" + _date + "\ndate2" + e.date);
				throw new ZZIllegalEx ("Nie mo¿na zmienyæ daty przydatnoœci do spo¿ycia"
						+" tak by wydaæ coœ nieœwierzego");
			}
		}    
		expiryDate = _date;
	}
	
	public MyDate getExpiryDate(){
		return new MyDate (expiryDate); 
	}
	
	public void setPrice(float _price) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkSet(price);
		price = _price;
		
	}
	
	public void changePrice(float _price) throws ZZIllegalEx, ZZIllegalNotEnoughPrivelagesEx {
		checkChange(price);
		price = _price;
		
	}
	
	public float getPrice() {
		return price;
	}
	
	
	
	public void removePackagingType(String Name) throws ZZIllegalNotEnoughPrivelagesEx{
		if(!getPackagingMap().containsTwo(Name)){
			System.err.println("Próba usuniêcia"
					+" rodzaju opakowania, którego ju¿ nie ma (" + Name + ")");
			return;
		}
		getPackagingMap().removePairTwo(Name);
	}
	
	public void removePackagingType(int numer ) throws ZZIllegalNotEnoughPrivelagesEx{
		if(!getPackagingMap().containsOne(numer)){
			System.err.println("Próba usuniêcia"
					+" rodzaju opakowania, którego ju¿ nie ma (" + numer + ")");
			return;
		}
		getPackagingMap().removePairOne(numer);
	} 
	
	public void removeUnitType(String Name) throws ZZIllegalNotEnoughPrivelagesEx{
		if(!getUnitMap().containsTwo(Name)){
			System.err.println("Próba usuniêcia"
					+" rodzaju opakowania, którego ju¿ nie ma (" + Name + ")");
			return;
		}
		getUnitMap().removePairTwo(Name);
	}
	
	public void removeUnitType(int numer ) throws ZZIllegalNotEnoughPrivelagesEx{
		if(!getUnitMap().containsOne(numer)){
			System.err.println("Próba usuniêcia"
					+" rodzaju opakowania, którego ju¿ nie ma (" + numer + ")");
			return;
		}
		getUnitMap().removePairOne(numer);
	}
	
	public Expenditure getExpenditure(ExpenditureId i) throws ZZIllegalNotInitializedEx{
		Expenditure foo = expenditures.get(i);
		if(foo==null) 
			throw new ZZIllegalNotInitializedEx ("Nie zainicjalizowano wyjœcia z magazynu" + 
					" W produkcie " + this.name);
		return new Expenditure (foo);
	}
	
	public void changeExpenditure(ExpenditureId ktore, float newQuant,  MyDate newDate,String _tytulem)
	throws ZZIllegalEx{
		Expenditure temp = expenditures.get(ktore);
		if(User.getPriveleges()!=Priveleges.ROOTUSER){
			throw new ZZIllegalNotEnoughPrivelagesEx();
		}
		if((sQuantity - Expenditure.sumItUp(expenditures.values()) 
				+ temp.ile - newQuant)< 0){
			throw new ZZIllegalNotEnoughEx("Zmiana spwowodowa³a by nielogicznoœæ w " +
			"baze danych");
		}
		if(newDate.after(expiryDate)){
			throw new ZZIllegalExpiredEx ("Zmiana spwowdowa³a by sytuacjê w której" 
					+ " wydanoby nieswierze produkty");
		}
		temp.ile = newQuant;
		temp.date = new MyDate (newDate);
	}
	
	public void cancelExpenditure(ExpenditureId id){
		expenditures.remove(id);
	}
	
	public void changeUncheckedExpenditure(ExpenditureId ktore, float newQuant, MyDate newDate, String _tytulem){
		Expenditure temp = expenditures.get(ktore);
		temp.tytulem = new String(_tytulem);
		temp.ile = newQuant;
		temp.date = new MyDate (newDate);
	}
	
	public Expenditure getUncheckedExpenditure(ExpenditureId ktore){
		Expenditure foo = expenditures.get(ktore);
		if(foo==null) 
			return null;
		return new Expenditure (foo);
	}
	
	
	
	public ExpenditureId performUncheckedExpenditure(float _quant, MyDate _date, String _tytulem){
		ExpenditureId foo = expIdGen.getNextId();
		expenditures.put(foo,
				new Expenditure(_quant, _date, _tytulem));
		return (ExpenditureId) foo.copy();
	}
	
	public void cancelUncheckedExpenditure(ExpenditureId id){
		expenditures.remove(id);
	}  
	
	
	public int compareTo(Product foo){
		try{
			return (int) (this.getId().getVal() - ((Product) foo).getId().getVal());
		}catch(ZZIllegalEx baz){
			return 0;
		}
	} 
	
	public void eraseExpenditure(int ktore){
		expenditures.remove(ktore);
	}
	
	
	public static void setBeginId(ProdId id){
		idGen.setLastId(id);
	}
	
	
	
	private ProdId id = null;
	// public static DateFormat df;
	
	//static{
	//df = DateFormat.getDateInstance(DateFormat.SHORT, new Locale ("pol", "PL"));
	//}
	
	private String name, facturaNo, description;
	private float sQuantity=-1.0f, price=-1.0f;
	private MyDate bookingDate, expiryDate;
	private int packagingId=-1, unitId=-1; 
	
	
	private IdGenerator<ExpenditureId> expIdGen  = 
		new IdGenerator<ExpenditureId>(new ExpenditureId(0));
	
	Map<ExpenditureId,Expenditure> expenditures = 
		new TreeMap<ExpenditureId,Expenditure> (new IdComparator<ExpenditureId>());
	
	
	
}
