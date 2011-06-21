package zzDataBase;

import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNosuchEntryEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzId.AbstractId;
import zzId.ExpenditureId;
import zzId.IdComparator;
import zzId.IdGenerator;
import zzId.MealExpenditureId;
import zzId.OwnerId;
import zzId.ProdId;
import zzMyDate.MyDate;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;
import zzUser.User;

public class Mealv3 extends AbstractOwner
implements ExpenditureHolder,
OwnerOfAnExpenditurev2 {
	
	
	// Constructors	
	/**
	 * @param date date of meal. Cannot be changed
	 */
	public Mealv3(MyDate date) {
		super();
		this.setDate(date);
	}
	
	/**
	 * @param date  of meal. Cannot be changed
	 * @param name of meal. can be changed
	 * @param description of meal. can be changed
	 */
	public Mealv3(MyDate date, String name, String description) {
		super();
		this.setDate(date);
		this.setName(name);
		this.description = description;
	}
	
	public Mealv3(String str) throws ZZIoBadFormatEx {
		//super(); - we want to omit creating of Id
		this.parseString(str);		
	}
	
	
	
	
	//Native functions;
	
	public MealExpenditure addExpenditure(Product p, float f) throws ZZIllegalEx, ZZInternalDuplicateEntryEx{
		if(!User.canAddExpenditureInMeal()) throw new ZZIllegalNotEnoughPrivelagesEx();
		Expenditure e = new Expenditure(f, getDate(), "Posilek: " + getName());
		p.isExpenditureOK(e,true);
		ExpenditureId eid = p.performExpenditure(e);
        MealExpenditureId meid = gen.getNextId();
        System.out.println("id" + meid);
		MealExpenditure me = new MealExpenditure(e,meid,p.getId(),eid);
        System.out.println("Exid" + me.getId());
        if(contents.containsKey(me.getId())){
           for(MealExpenditure me2 : contents.values()){
              System.out.print(me2.getId() + ", " + StaticContent.sc.getDataBase().getById(me2.getPId()).getName() + "; ");
           }
           System.out.println();
           for(MealExpenditureId id : contents.keySet()){
              System.out.print(id + "; ");
           } 
           throw new ZZInternalDuplicateEntryEx("" +me.getId());
        }
        /*for(MealExpenditure me2 : contents.values()){
           System.out.println(me.getId() + ", " + StaticContent.sc.getDataBase().getById(me2.getPId()).getName());
        }*/
		contents.put(me.getId(),me);
		return me;
	}
	
	public void removeExpenditure(MealExpenditureId meid)
	throws ZZIllegalEx{
       System.out.println(getName());
       if(meid==null) throw new ZZIllegalNosuchEntryEx();
		if(!User.canRemoveExpenditureInMeal()) throw new ZZIllegalNotEnoughPrivelagesEx();
        System.out.println("meid: " + meid);
        for(MealExpenditureId me2: contents.keySet()){           
           System.out.print(me2 + ", ");
        }
		if(!contents.containsKey(meid)) throw new ZZIllegalNosuchEntryEx();
        System.out.println(getName());
        MealExpenditure m = contents.get(meid);
        if(m==null)throw new ZZIllegalNosuchEntryEx();
		StaticContent.sc.getDataBase().getById(m.getPId()).cancelExpenditure(m.getEId());
		contents.remove(meid);
	}
	
	public void removeExpenditure(Product p, ExpenditureId id)
	throws ZZIllegalEx{
		removeExpenditure(getMealExpId(p.getId(), id));
	}
	
	public void changeExpenditure(MealExpenditureId meid, float newQuant)
	throws ZZIllegalEx{
		if(!User.canChangeExpenditureInMeal()) 
			throw new ZZIllegalNotEnoughPrivelagesEx();
		MealExpenditure me = contents.get(meid);
		Expenditure e = me.getExp();
		Product p = StaticContent.sc.getDataBase().getById(me.getPId());
		
		p.isExpenditureChangeOk(e, newQuant, true);
		
		p.changeExpenditure(me.getEId(), newQuant, me.date,me.tytulem);
		me.ile = newQuant;
	}
	
	public void changeExpenditure(Product p, ExpenditureId id, float newQuant)
	throws ZZIllegalEx{
		MealExpenditure me = contents.get(getMealExpId(p.getId(),id));
		Expenditure e = me.getExp();
		
		p.isExpenditureChangeOk(e, newQuant, true);
		
		p.changeExpenditure(me.getEId(), newQuant, me.date,me.tytulem);
		me.ile = newQuant;
	}
	
	private synchronized MealExpenditureId getMealExpId 
	(ProdId pId, ExpenditureId eId){
		Iterator<Map.Entry<MealExpenditureId,MealExpenditure> > i = 
			contents.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry<MealExpenditureId,MealExpenditure> e = i.next();
			if(e.getValue().getPId()== pId && e.getValue().getEId() == eId) 
				return e.getKey() ;
		}
		return null;
	}
	
	public synchronized String stringify() throws ZZIllegalEx{
		String result = "";
		result += name.replace(',',';') + " ,";
		result += getDate().toString().replace(',',';') + " ,";
		result += getDescription().replace(',',';') + " ,";
		result += this.getOwnerId().toString().replace(',',';') + " ,";
		result += String.valueOf(contents.size()).toString().replace(',',';')  + " ,";
		for(Map.Entry<MealExpenditureId,MealExpenditure> e : contents.entrySet()){
			result +=  e.getKey().toString().replace(',',';')  + " , " + e.getValue().toString();
		}
		return result;
	}
	
	public Map<MealExpenditureId, MealExpenditure> getContents(){
		return Collections.unmodifiableMap(contents);
	}
	
	public void parseString(String s) throws ZZIoBadFormatEx{
		try{
			StringTokenizer tok = new StringTokenizer(s,",");
			setName(tok.nextToken().trim().toString().replace(';',','));
			setDate(new MyDate (tok.nextToken().toString().replace(';',',')) );
			setDescription(tok.nextToken().trim().toString().replace(';',','));
			OwnerId id = new OwnerId();
			id.parse(tok.nextToken().toString().replace(';',','));
			setOwnerId(new OwnerId (id));
			int size = (int) Float.parseFloat(tok.nextToken().toString().replace(';',','));
			MealExpenditure me;
			MealExpenditureId meid;
			MealExpenditureId maxId = new MealExpenditureId(0);
			for(int i = 0 ; i < size; i++){
				meid = new MealExpenditureId();
				meid.parse(tok.nextToken().toString().replace(';',','));
                System.out.println(maxId + " compareTo " + meid + ", " + meid.compareTo(maxId) );
				if(meid.compareTo(maxId)>0){                   
                   maxId = new MealExpenditureId(meid);
                }
				me = new MealExpenditure();
				tok = me.parseString(tok);
                me.setId(meid);
				contents.put(meid,me);
               
			}
            System.out.println(maxId);
			//maxId.increment();
            
            System.out.println(maxId);
			gen.setLastId(maxId);
            System.out.println(gen.getNextId());
            
		}catch(ParseException e){
			throw new ZZIoBadFormatEx("B³¹d w odczycie posi³ki", e); 
		}
	}
	
	
	// Functions needed by OvnerOfnExpenditurev2	
	/**
	 * @deprecated to be ued via OvnerOfnExpenditurev2 interface
	 */
	public void cancel(AbstractId id) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		removeExpenditure(id);
	}
	/**
	 * @deprecated to be ued via OvnerOfnExpenditurev2 interface
	 */
	public void cancel(ExpenditureId toBeCancelled, ProdId product) throws ZZIllegalEx {
		removeExpenditure(
				StaticContent.sc.getDataBase().getById(product), toBeCancelled
		);		
	}
	
	//Functions needed by Expenditure Holder
	/**
	 * @deprecated to be ued via ExpenditureHolder interface
	 */
	public void changeExpenditure(AbstractId changedId, Expenditure newOne) 
	throws ZZIllegalEx, ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx {
		if(!AbstractId.class.isInstance(changedId))
			throw new ZZInternalClassCastExceptionEx();
		
		MealExpenditure me = contents.get(changedId);
		
		if(me==null)
			throw new ZZInternalNoSuchEntryEx();
		
		Product p = StaticContent.sc.getDataBase().getById(me.getPId());
		if(p==null)
			throw new ZZInternalNoSuchEntryEx();
		
		
		Expenditure e = p.getExpenditure(me.getEId());
		if(e==null)
			throw new ZZInternalNoSuchEntryEx();
		
		p.isExpenditureChangeOk(e,newOne.ile, true);
		p.changeExpenditure(me.getEId(),e.ile,e.date,e.tytulem);
		me.ile = newOne.ile;		
	}
	
	/**
	 *  @throws ZZInternalUnsupportedOperationExceptionEx 
	 * 
	 *  @deprecated to be ued via ExpenditureHolder interface
	 *  @see zzProduct.ExpenditureHolder#performExpenditure(zzDataBase.Expenditure)
	 */
	public AbstractId performExpenditure(Expenditure exp) 
	throws ZZIllegalEx, ZZInternalClassCastExceptionEx, ZZInternalUnsupportedOperationExceptionEx {
		if(!MealExpenditure.class.isInstance(exp))
			throw new ZZInternalClassCastExceptionEx();
		throw new ZZInternalUnsupportedOperationExceptionEx();
		/*MealExpenditure me = (MealExpenditure) exp;
		 
		 Product p = StaticContent.sc.getDataBase().getById(me.getPId());
		 if(me.getEId()==null){
		 p.isExpenditureOK(me,true);
		 p.performExpenditure(me);
		 me.setId(gen.getNextId());
		 contents.add
		 }
		 for(Map.Entry<ExpenditureId, Expenditure> mapE : p.getExpenditures()){
		 if (mapE.getKey().equals(me.getEId())){
		 if(!mapE.getValue().equals(me.getExp())){
		 throw new ZZInternalUnsupportedOperationExceptionEx();
		 }
		 break;
		 }
		 }*/
	}
	
	/**
	 * @deprecated to be ued via ExpenditureHolder interface
	 */
	public void removeExpenditure(AbstractId id) 
	throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		if(!MealExpenditureId.class.isInstance(id)) 
			throw new ZZInternalClassCastExceptionEx();
		
		removeExpenditure((MealExpenditureId)id);
	}	
	
	
	//Containers
	
	private final Map<MealExpenditureId, MealExpenditure > contents 
	= new TreeMap<MealExpenditureId, MealExpenditure > (new IdComparator<MealExpenditureId>());
	
	private IdGenerator<MealExpenditureId> gen =
		new IdGenerator<MealExpenditureId>(new MealExpenditureId(0)); 
	
	// Descriptive fields, and their accesor methods
	/**
	 * Used to determine the date of expednitures
	 */
	private MyDate date;
	private String name, description;
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param date The date to set.
	 */
	private void setDate(MyDate date) {
		this.date = date;
	}
	
	/**
	 * @return Returns the date.
	 */
	public MyDate getDate() {
		return date;
	}
}