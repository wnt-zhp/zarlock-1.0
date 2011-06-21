/**
 * 
 */
package zzDataBase;

import java.text.ParseException;
import java.util.StringTokenizer;

import zzEx.ZZIoBadFormatEx;
import zzId.ExpenditureId;
import zzId.MealExpenditureId;
import zzId.ProdId;
import zzMyDate.MyDate;

public class MealExpenditure extends Expenditure{
	MealExpenditureId id;
	ProdId pId;
	ExpenditureId eId;
	
	public void setId(MealExpenditureId id) {
		this.id = id;
	}
	
	public MealExpenditureId getId() {
		return id;
	}
	
	
	
	public MealExpenditure(Expenditure foo, MealExpenditureId id, ProdId id2, ExpenditureId id3) {
		super(foo);
		this.id = id;
		pId = id2;
		eId = id3;
	}
	
	@Deprecated 
	public MealExpenditure(){};
	
	public Expenditure getExp(){
		return new Expenditure (this.ile, this.date, this.tytulem);
	}
	
	public void setPId(ProdId pId) {
		this.pId = pId;
	}
	
	public ProdId getPId() {
		return pId;
	}
	
	public void setEId(ExpenditureId eId) {
		this.eId = eId;
	}
	
	public ExpenditureId getEId() {
		return eId;
	}
	
	 public String toString(){
		String result = new String();
		result += tytulem.toString().replace(',',';') + ",";
		result += String.valueOf(ile).toString().replace(',',';') +",";
		result += date.toString().replace(',',';') + ",";
		result += getId().toString().replace(',',';') + ",";   
		if(getEId()==null) result += StaticContent.nullString.toString().replace(',',';')+ ",";
		else               result += getEId().toString().replace(',',';') + ",";
		result += getPId().toString().replace(',',';')+ ",";
		return result;
	}
	
	@SuppressWarnings("hiding") 
	public StringTokenizer parseString(StringTokenizer tok) 
	throws ParseException, ZZIoBadFormatEx 
	{
		tytulem = tok.nextToken().trim().toString().replace(';',',');
		ile = Float.parseFloat(tok.nextToken().replace(';',',') );
		date = new MyDate(tok.nextToken().replace(';',',') );
		MealExpenditureId id = new MealExpenditureId();
		id.parse(tok.nextToken().replace(';',',') );
		setId(id);
		String token = tok.nextToken().replace(';',',') ;
		if (token.equals(StaticContent.nullString.replace(';',',') )){
			setEId(null);
		}else{
			ExpenditureId eId = new ExpenditureId();
			eId.parse(token.replace(';',',') );
			setEId(eId);
		}
		ProdId pId= new ProdId();
		pId.parse(tok.nextToken().replace(';',',') );
		setPId(pId);
		return tok;
	}
	
}