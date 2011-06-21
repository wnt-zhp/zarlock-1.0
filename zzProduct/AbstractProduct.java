/*
 * Created on 2006-04-29
 * Author jb
 *
 */
package zzProduct;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;


import zzDataBase.Expenditure;
import zzEx.ZZIllegalAlreadyInitializedEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZIllegalTytulemIsEmptyEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzEx.ZZInternalNoSuchEntryEx;
import zzId.AbstractId;
import zzId.ExpenditureId;
import zzId.IdGenerator;
import zzId.ProdId;
import zzId.DumbIdGen;
import zzMyDate.MyDate;
import zzUser.Priveleges;
import zzUser.User;
import StringIntTwoWayMap.StringIntTwoWayMap;


public abstract class AbstractProduct implements Product {
	
	
	
	public void changeExpenditure(AbstractId changedId, Expenditure newOne) throws ZZIllegalEx, ZZInternalClassCastExceptionEx, ZZInternalNoSuchEntryEx {
		if(!ExpenditureId.class.isInstance(changedId)) throw new ZZInternalClassCastExceptionEx();
		ExpenditureId id = (ExpenditureId) changedId;
		changeExpenditure(id, newOne.ile, newOne.date, newOne.tytulem);
	}
	public void removeExpenditure(AbstractId removedId) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		if(!ExpenditureId.class.isInstance(removedId)) throw new ZZInternalClassCastExceptionEx();
		ExpenditureId id = (ExpenditureId) removedId;
		cancelExpenditure(id);
	}
	public String[] isExpenditureOKStr(Expenditure foo) throws ZZIllegalEx{
		Vector<String> vec = new Vector<String>(5);
		if(foo.date==null || foo.date.before(getDateOfBooking()))
			vec.add("Przed dat¹ ksiêgowania");
		if(foo.date==null || foo.date.after(getExpiryDate()))
			vec.add("Przeterminowany");
		if( (getCurrQ() - foo.ile)  < 0)
			vec.add("Chcesz wydaæ wiêcej ni¿ masz na sk³adzie");
		if(foo.tytulem == null ||foo.tytulem.trim().length()==0)
			vec.add("Pole tytu³em nie powinno byæ puste");
		return vec.toArray(new String[1]);
	}
	public String[] isExpenditureChangeOkStr(Expenditure foo, float newQuant)  throws ZZIllegalEx{
		Vector<String> vec = new Vector<String>(5);
		if(foo.date==null || foo.date.before(getDateOfBooking()))
			vec.add("Przed dat¹ ksiêgowania");
		if(foo.date==null || foo.date.after(getExpiryDate()))
			vec.add("Przeterminowany");
		if( (getCurrQ() + foo.ile - newQuant)  < 0)
			vec.add("Chcesz wydaæ wiêcej ni¿ masz na sk³adzie");
		if(foo.tytulem == null ||foo.tytulem.trim().length()==0)
			vec.add("Pole tytu³em nie powinno byæ puste");
		return vec.toArray(new String[1]);
	}
	
	public int isExpenditureOK(Expenditure foo) throws ZZIllegalEx{
		int ret = 0;
		if(foo.date==null || foo.date.before(getDateOfBooking()))
			ret+=1;
		if(foo.date==null || foo.date.after(getExpiryDate()))
			ret += 2;
		if( (getCurrQ() - foo.ile)  < 0)
			ret +=4;
		if(foo.tytulem == null ||foo.tytulem.trim().length()==0)
			ret+=8;
		return ret;
	}
	
	public int isExpenditureOK(Expenditure foo,boolean b) throws ZZIllegalEx{
		int wynik = isExpenditureOK(foo);
		if(b && wynik!=0){
			if(wynik%8==0){
				wynik-=8;
				throw new ZZIllegalTytulemIsEmptyEx("Pote tytu³em nie mo¿e byc puste");        
			}
			if(wynik%4 == 0){
				wynik -=4;
				throw new ZZIllegalNotEnoughEx("Za ma³o jednostek produktu");
			}
			if(wynik%2==0){
				wynik -= 2;
				throw new ZZIllegalEx("Produkt przeterminowany");
			}
			if(wynik%1==0){
				throw new ZZIllegalEx("Produkt nie zaksiêgowny");
			}      
		}
		return wynik;
	}
	
	public int isExpenditureChangeOk(Expenditure foo, float newQuant, boolean b) throws ZZIllegalEx{
		int ret = 0;
		if(foo.date==null || foo.date.before(getDateOfBooking()))
			ret+=1;
		if(foo.date==null || foo.date.after(getExpiryDate()))
			ret += 2;
		if( (getCurrQ() + foo.ile - newQuant) < 0)
			ret +=4;
		if(foo.tytulem == null ||foo.tytulem.trim().length()==0)
			ret+=8;  
		int wynik = ret;
		if(b && wynik!=0){
			if(wynik%8==0){
				wynik-=8;
				throw new ZZIllegalTytulemIsEmptyEx("Pote tytu³em nie mo¿e byc puste");        
			}
			if(wynik%4 == 0){
				wynik -=4;
				throw new ZZIllegalNotEnoughEx("Za ma³o jednostek produktu");
			}
			if(wynik%2==0){
				wynik -= 2;
				throw new ZZIllegalEx("Produkt przeterminowany");
			}
			if(wynik%1==0){
				throw new ZZIllegalEx("Produkt nie zaksiêgowny");
			}
		}
		return wynik;
	}
	
	public ExpenditureId performExpenditure(Expenditure e ) throws ZZIllegalEx{
		return performExpenditure(e.ile,new MyDate(e.date),e.tytulem);
	}
	
	private static StringIntTwoWayMap unitMap=new StringIntTwoWayMap();
	
	private static StringIntTwoWayMap packagingMap = new StringIntTwoWayMap();
	
	public static void setBeginId(ProdId id){
		idGen.setLastId(id);
	}
	
	protected static IdGenerator<ProdId> idGen = 
		new IdGenerator<ProdId> (new ProdId(0));
	
	public static void addPackagingType(String Name) throws ZZIllegalNotEnoughPrivelagesEx{
		if(User.getPriveleges()==Priveleges.BROWSE)
			throw new ZZIllegalNotEnoughPrivelagesEx("Za ma³e uprawnienia");
		if(getPackagingMap().containsTwo(Name)){
			return;
		}
		getPackagingMap().put(getPackagingMap().maxOne()+1, Name);
	}
	
	public static void addUnitType(String Name) throws ZZIllegalNotEnoughPrivelagesEx{
		if(User.getPriveleges()==Priveleges.BROWSE)
			throw new ZZIllegalNotEnoughPrivelagesEx("Za ma³e uprawnienia");
		if(getUnitMap().containsTwo(Name)){
			return;
		}
		getUnitMap().put(getUnitMap().maxOne()+1, Name);
	}
	
	public static Collection<Map.Entry<Integer, String> > getPackagingMappings(){
		return Collections.unmodifiableCollection(
				getPackagingMap().getOneTwo().entrySet()
		);
	}
	
	public static Collection<Map.Entry<Integer, String> >  getUnitMappings(){
		return Collections.unmodifiableCollection(
				getUnitMap().getOneTwo().entrySet()
		);
	}
	
	public static void setFirstGeneratedId (ProdId _id) throws ZZIllegalNotEnoughPrivelagesEx{
		if(User.getPriveleges().equals(Priveleges.BROWSE)){
			throw new ZZIllegalNotEnoughPrivelagesEx(
					"Próba dostêpu do zmiany danych przez u¿ytkownika, który" + 
			" nie ma wystarczaj¹cych uprawnien");
		}
		idGen = new IdGenerator<ProdId> ( new ProdId(_id));
	}
	
	public static DumbIdGen<ProdId> getIdGenerator(){
		return new DumbIdGen<ProdId> (idGen);
	}
   public static void setUnitMap(StringIntTwoWayMap unitMap) {
      AbstractProduct.unitMap = unitMap;
   }
   public static StringIntTwoWayMap getUnitMap() {
      if(unitMap==null){
         unitMap = new StringIntTwoWayMap();
      }
      return unitMap;
   }
   public static void setPackagingMap(StringIntTwoWayMap packagingMap) {
      AbstractProduct.packagingMap = packagingMap;
   }
   public static StringIntTwoWayMap getPackagingMap() {
      if(packagingMap==null){
         packagingMap = new StringIntTwoWayMap();
      }
      return packagingMap;
   }
}
