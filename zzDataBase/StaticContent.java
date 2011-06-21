package zzDataBase;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JFrame;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import zzEx.ZZIllegalEx;
import zzId.OwnerId;
import zzMyDate.MyDate;
import zzProductBase.MainBase;
import zzProductBase.ProductBaseImmutable;
import zzProductBase.ProductBaseMutable;
import zzProductBase.SimpleProductBase;
import zzProductBase.SimpleProductBaseMutable;
import zzQuery.Query;

public class StaticContent {
	
	public static final String nullString = "null";
	
	///Static part///
	
	public static StaticContent sc = new StaticContent();
	
	///Variables///
	
	private MyDate today = new MyDate();  
	private MainBase dataBase = new MainBase();  
	private JFrame uppmostFrame;
	private Map<OwnerId, OwnerOfAnExpenditurev2> allOwners = 
		Collections.synchronizedMap( new TreeMap<OwnerId, OwnerOfAnExpenditurev2>());
	private Vector<Query> querriesEnforced = new Vector<Query>();
	private Vector<Day> days = new Vector<Day>();
	private Vector<Vector<String>> templates = new Vector();
	
	/// ListenerInterface ///
	private List<myActionListener> listeners = new LinkedList<myActionListener>();
	private ProductBaseImmutable querriedBase = new SimpleProductBase();
	private boolean hasBeenQuerried = false;
    private File documentsDir;
	public void addListener(myActionListener listener){
		listeners.add(listener);
	};
	
	boolean iffireListeners = true;
	
	/**
	 * Currently method relies on Object.compareTy(). So it performs check 
	 * of internal adresses of variables.
	 * @param listener
	 */
	public void removeListener(myActionListener listener){
		listeners.remove(listener);
	}
	
	protected void fireChange(String command){
		if(!iffireListeners) return;
		for(myActionListener l : listeners){
			l.actionPerformed(command);
		}
	}
	
	/// Available commands 
	
	static public final String dateChanged = "The date has changed";
	static public final String frameChanged = "The uppmost frame has changed";
	static public final String databaseChanged = "Products had been added into database";
	static public final String ownersChanged = "Some info has changed";
	static public final String filteringPerformed = "Some filtering had been performed";
	static public final String templatesChanged = "Some templates had been added";
	
	static public final String miejsceDope³niacz = "Mikaszówce";
	static public final String organizator = "Zwi¹zek Harcerstwa Polskiego.\nKomenda Chor¹gwi Sto³ecznej Hufiec Warszawa Wola\nPiaskowa 4 01-067 Warszawa"  ;
	public static final float stawka = 12.0f; 
	
	///Stupid accesor functions ///
	
	public void setToday(MyDate today) {
		this.today = today;
		fireChange(dateChanged);
	}
	
	public MyDate getToday() {
		return today;
	}
	
	public void setDataBase(SimpleProductBaseMutable dataBase) {
		this.dataBase = new MainBase(dataBase);
		this.querriedBase = dataBase;
		fireChange(databaseChanged);
	}
	
	public ProductBaseMutable getDataBaseMutable() {
		return dataBase;
	}
	
	public ProductBaseImmutable getDataBase(){
		return dataBase;
	}
	
	public void setUppmostFrame(JFrame uppmostFrame) {
		this.uppmostFrame = uppmostFrame;
		fireChange(frameChanged);
	}
	
	public  JFrame getUppmostFrame() {
		return uppmostFrame;
	}
	
	public void addDay(Day d){
		days.add(d);
	}
	
	public List<Day> getDays(){
		return Collections.unmodifiableList(days);
	
	}
	
	public Day getDay(MyDate date){
		MyDate date2 = date;
		class cmp implements Comparable<Day>{
			private MyDate date2;
			
			public cmp(MyDate date2) {
				super();

				this.date2 = date2;
			}

			public int compareTo(Day arg0) {
				return arg0.getDate().compareTo(date2);
			}
			
		}
		cmp cmp2 = new cmp(date); //Wow :P
		for(Day d : getDays()){
			if(cmp2.compareTo(d)==0){
				return d;
			}
		}
		return null;

	}
	
	public  void setfireListeners(boolean iffireListeners){
		this.iffireListeners = iffireListeners;
	}
	
	
	public void setOwners(Map<OwnerId, OwnerOfAnExpenditurev2> allOwners) {
		this.allOwners = allOwners;
	}
	
	public Map<OwnerId, OwnerOfAnExpenditurev2> getOwners() {
		return allOwners;
	}
	
	public ProductBaseImmutable enforceQuerries() throws ZZIllegalEx{
		if(hasBeenQuerried == true){			
			querriedBase = getDataBaseMutable().performQuery(querriesEnforced.toArray(new Query[]{}));		
		}
		hasBeenQuerried = false;
		return querriedBase;
	}
    
    
	
	public StaticContent() {
      super();
      documentsDir = new File((File)null, "Documents");
      documentsDir.mkdirs();
   }

   public void fireQueryPerformed(){
		fireChange(filteringPerformed);
	}
	
	public void addQuery(Query q){
		hasBeenQuerried = true;
		querriesEnforced.add(q);
		fireQueryPerformed();
	}
	
	public void addQuery(Vector<Query> q){
		hasBeenQuerried = true;
		querriesEnforced.addAll(q);
		fireQueryPerformed();
	}
	
	public void removeQuery(Query q){
		hasBeenQuerried = true;
		querriesEnforced.remove(q);
		fireQueryPerformed();
	}
	
	public void removeQuery(Vector<Query> q){
		hasBeenQuerried = true;
		querriesEnforced.removeAll(q);
		fireQueryPerformed();
	}
	
	public void fireProductAdded(){
		fireChange(databaseChanged);
	}
	
	public void addQuerySilent(Query q){
		hasBeenQuerried = true;
		querriesEnforced.add(q);
	}
	
	public void addQuerySilent(Vector<Query> q){
		hasBeenQuerried = true;
		querriesEnforced.addAll(q);
	}
	
	public void removeQuerySilent(Query q){
		hasBeenQuerried = true;
		querriesEnforced.remove(q);
	}
	
	public void removeQuerySilent(Vector<Query> q){
		hasBeenQuerried = true;
		querriesEnforced.removeAll(q);
	}	
	
	public void fireTemplateChanged(){
		fireChange(StaticContent.templatesChanged);
	}
	public void reset(){
		allOwners.clear();
		querriesEnforced.clear();
		days.clear();
		dataBase.reset();
		templates.clear();
		fireChange(databaseChanged);
		fireChange(templatesChanged);
	}
	
	public interface myActionListener{
		void actionPerformed(String command);
	}

	public void setDays(Vector<Day> days) {
		this.days = days;
	}
	/**
	 * @deprecated
	 * @return
	 */
	public List<Day> getDaysMod(){
		return days;
	}

	public void setTemplates(Vector<Vector<String>> templates) {
		this.templates = templates;
	}

	public Vector<Vector<String>> getTemplates() {
		return templates;
	}
    
    public void setDocumentsFolder(File file){
       documentsDir = file;
       documentsDir.mkdirs();
    }
    
    public File getDocumentsFolder(){
       return documentsDir;
    }
	
}
