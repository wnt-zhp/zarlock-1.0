/*
 * Created on 2006-03-06
 * Author jb
 *
 */
package StringIntTwoWayMap;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JLabel;

import zzEx.ZZIoEx;

public class StringIntTwoWayMap extends TwoWayMap<Integer, String>{
   
   
   
   @Override
   public synchronized void put(Integer one, String two) {
      super.put(one, two.trim());
   }
   
   @Override
   public synchronized boolean containsTwo(String two) {
      return super.containsTwo(two.trim());
   }
   
   @Override
   public synchronized Integer getOne(String two) {
      return super.getOne(two);
   }
   
   public StringIntTwoWayMap(){
      super.put(0, "n. dot.");
   }
   public void save(File nazwa) throws ZZIoEx{
      try{
         save(new java.io.FileWriter(nazwa));
      }
      catch(IOException foo){
         throw new ZZIoEx("TwoWayMap:save" + foo.getMessage());
      };    
   }
   
   public synchronized int getSize(){
      return OneTwo.size();
   }
   
   public synchronized void save(Writer writer) throws ZZIoEx{
      BufferedWriter buffwr = new BufferedWriter(writer);
      try{
         Set<Entry<Integer,String> > set = OneTwo.entrySet();
         Iterator<Entry<Integer,String> > iter = set.iterator();
         //int i=0;
         while(iter.hasNext()){
            //System.out.print(++i);
            Entry<Integer,String> temp=iter.next();
            buffwr.write(temp.getKey().toString() + "," + temp.getValue()+ ",\n");
         }
         
         /*for(; klucze.hasNext();){
          System.out.print(":");
          Object temp = klucze.next();
          buffwr.write(temp.toString() + "," + OneTwo.get(temp)+ ",\n");
          }*/
      }
      catch(IOException foo){
         throw new ZZIoEx("TwoWayMap:save");
      }finally{
         try{
            buffwr.flush();
            buffwr.close();
         }catch(Exception sa){};
      };
   };
   
   public synchronized void load (Reader read) throws ZZIoEx{
      BufferedReader burr = new BufferedReader(read);
      String line;
      try{
         //int i =0;
         while((line =burr.readLine())!=null){
            StringTokenizer foo = new StringTokenizer(line,",");
            //System.out.print(":"+ i++);
            Integer integer= (Integer) new Integer( Integer.parseInt(foo.nextToken().trim()));
            String string = (String) foo.nextToken();
            put(integer,string);
         }
      }
      catch(IOException foo){
         throw new ZZIoEx ("TwoWayMap:load" + foo.getMessage());
      }      
   };
   
   public synchronized void load (File name) throws ZZIoEx{
      try{
         load(new java.io.FileReader(name));
      }catch(IOException foo){
         throw new ZZIoEx ("TwoWayMap:load" + foo.getMessage());
      }
   }  
}



class TwoWayMap<One, Two> { //Bzdura, ale na pocz¹tku da³em za ma³e za³ozenia a teraz nei chce mi siê tego od szablonowywaæ
   TreeMap<One, Two> OneTwo = new TreeMap<One, Two>();
   TreeMap<Two, One> TwoOne = new TreeMap<Two, One>();
   public Vector<Two> values;
   public JLabel foo = new JLabel(); 
   
   public static final String PropertyName="zz85674854478951133254";
   
   public TwoWayMap(){
   }
   
   public synchronized void put(One one, Two two) /*throws ZZInternalDuplicateEntryEx*/{
      /*if(containsOne(one) && !getTwo(one).equals(two))
       throw new ZZInternalDuplicateEntryEx ("Ju¿ jest klucz o numerze" + one +
       "i jego wartoœæ nie równa siê" + two);*/
      OneTwo.put(one,two);
      TwoOne.put(two,one);
      values = new Vector<Two> (OneTwo.values());
      foo.firePropertyChange(PropertyName, 10,11);
   }
   
   public synchronized void removePairOne(One one){
      TwoOne.remove(OneTwo.get(one));
      OneTwo.remove(one); 
      values = new Vector<Two> (OneTwo.values());
      foo.firePropertyChange(PropertyName, 10,11);
   }
   
   public synchronized void removePairTwo(Two two){
      OneTwo.remove(TwoOne.get(two));
      TwoOne.remove(two);
      values = new Vector<Two> (OneTwo.values());
      foo.firePropertyChange(PropertyName, 10,11);
   }
   
   public synchronized boolean containsTwo(Two two){
      return OneTwo.containsValue(two);
   }
   
   public synchronized boolean containsOne(One one){
      return TwoOne.containsValue(one);
   }
   
   public synchronized One getOne(Two two){
      return TwoOne.get(two);
   }
   
   public synchronized Two getTwo(One one){
      return OneTwo.get(one);
   }
   
   public synchronized One maxOne(){
      return OneTwo.lastKey();
   }
   
   public synchronized One minOne(){
      return OneTwo.firstKey();
   }
   
   public synchronized Two maxTwo(){
      return TwoOne.lastKey();
   }
   
   public synchronized Two minTwo(){
      return TwoOne.firstKey();
   }
   
   public synchronized Map<One,Two> getOneTwo(){
      return new TreeMap<One,Two> (OneTwo);
   }
   
   public synchronized Map<Two, One> getTwoOne(){
      return new TreeMap<Two, One> (TwoOne);
   }
}
