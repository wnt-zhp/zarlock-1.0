/*
 * Created on 2006-04-15
 * Author jb
 *
 */
package zzTests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.swing.JFileChooser;

import StringIntTwoWayMap.StringIntTwoWayMap;



import zzProductBase.SimpleProductBaseMutable;
import zzProductBase.SimpleProductBase;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzProduct.*;



public class SimpleBaseTests extends javax.swing.JFrame{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  static public SimpleProductBaseMutable returnNewBase(int numberOfProfucts) 
    throws ZZIllegalEx, ZZInternalDuplicateEntryEx{
    SimpleProductBaseMutable foo = new SimpleProductBaseMutable();
    for(int i = 0; i < numberOfProfucts; i++){
      foo.addProduct(returnSimpleProduct());
    }
    return foo;
  }

  SimpleProductBaseMutable RandomB=new SimpleProductBaseMutable();
  SimpleProductBaseMutable Readed =new SimpleProductBaseMutable();
  Date Start = new Date() , Stop = new Date();
  

  
  
  static public void setUpProduct()throws ZZIllegalNotEnoughPrivelagesEx{
    for(int i = 0; i<jednostka.length; i++){
      AbstractProduct.addUnitType(jednostka[i]);
    } 
    for(int i = 0; i<opakowania.length; i++){
      AbstractProduct.addPackagingType(opakowania[i]);
    } 
  }
  
  File lastSave = null;
  SimpleBaseTests(){
    lastSave = new File("D:" + File.separator + "DOKUMENTY" + 
        File.separator + "ZZTests");
    lastSave.mkdir();
    
  }
  
  private void Start(){
    Start = new Date();
  }
  
  private void Stop(){
    Stop = new Date();
  }
  

  private long  diff(){
    return (Stop.getTime() - Start.getTime());
  }
  
  public static void main(String args[])throws Exception {
    SimpleBaseTests foo=new SimpleBaseTests();
    System.out.println("Adding default units, and kinds of packaging");
    setUpProduct();
    System.out.println("Trying to create new database");
    foo.setVisible(true);
    System.out.println("Fiklling with random products");
    foo.Start();
    foo.fillWithRandom(100);
    foo.Stop();
    System.out.println("Fiklling with random products took: " + foo.diff() + "ms" );
    System.out.println("Saving");
    
    foo.Start();
    /*for(Product p: foo.RandomB){
      System.out.println(p);
    }*/
    foo.SaveToDefFile(foo.RandomB);  
    foo.Stop();
    System.out.println("Saving took: " + foo.diff() + "ms." );
    foo.RandomB=null;
    System.out.println("Loading");
    SimpleProduct.setPackagingMap(new StringIntTwoWayMap());
    SimpleProduct.setUnitMap(new StringIntTwoWayMap());
    foo.Start();
    foo.Readed = foo.LoadDef();
    foo.Stop();
    System.out.println("Loading took: " + foo.diff() + "ms.");
    System.out.println("Displaying");
   /* for(Product p: foo.Readed){
      System.out.println(p);
    }*/
    //System.out.println("Saving 2");
    //foo.SaveToFile(foo.Readed);
    //System.out.println("SuckCes");
    System.out.println("Performing some sorting");
    foo.Start();
    /*Ordering[] ord = {        
        new OrderByPrice(),
        new OrderByName()
    };*/
    //foo.Readed.sort(ord);
    //Stack<Product> Result = foo.Readed.performQuery(foo.Readed.performQuery(new OrderByPrice()), new OrderByName());
    Collection<Product> Result = foo.Readed.getSorted();
    foo.Stop();
    System.out.println("Performing some sorting took: " + foo.diff() +"ms");

    File sorted = new File(foo.lastSave, "Sortowane.txt");
    sorted.createNewFile();
    BufferedWriter buffwr = new BufferedWriter(new FileWriter (sorted));
    for(Product p: Result){
      buffwr.write(p.toString() + "\n");
      System.out.println(p);
    }
    buffwr.flush();
    buffwr.close();
    System.out.println("SuckCes");
    System.exit(0);
  }
  
  static Random rand = new Random(new Date().hashCode());
  
  static public String[] nazwy = {
      "Bu³ka zwyk³a",
      "Bu³ka luksusowa",
      "Chleb wiejski pe³noziarnisty",
      "Konserwa Tyrolska,",
      "Tuszonka wiejska - konserwa t³uszczowa",
      "Mleko 3,2%",
      "Mleko bezt³uszczowe",
      "Ziemniak",
      "Burak",
      "P³atki kukurydziane",
      "P³atki Zborzowe",
      "Woda mineralna ¯ywiec",
      "£ódka, Bols"
  };
  
  static public String opakowania[]={
      "Luzem",
      "Puszka stalowa,",
      "Tetrapak",
      "Trójwarstwowy p³aszcz, z dilithium",
      "Szklane",
      "SupraPack"
  };
  
  static public String jednostka[]={
      "Litr,",
      "Megatona",
      "Sztuka",
      "Kilogram"
  };
  
  static public String description[]={
    "Dawaæ nielubianym harcerzom",
    "Tylko Druid prze¿yje zjedenie tego",
    "Nawet Druit, tego nie zje",
    "Pyszota",
    "Jadalne",
    "Pzrechowywaæ w miejscu niedostêpnym dla dzieci"
  };
  
  static private int r(int foo){
    return rand.nextInt(foo);
  }
  
  static private float f(){
    return rand.nextFloat()*100;
  }
  
   public void fillWithRandom(int ile)throws ZZInternalDuplicateEntryEx, ZZIllegalEx{
     RandomB = returnNewBase(ile);
  }
  
   public void SaveToFile(SimpleProductBase foo)throws Exception{
    JFileChooser choose = new JFileChooser();
    choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    choose.showSaveDialog(this);
    System.out.println(choose.getSelectedFile().getAbsolutePath());
    File bar = new File(lastSave=choose.getSelectedFile(), "foo.txt");
    foo.setSaveToFile(bar);
    bar.createNewFile();
    foo.save();
    }
   
   public void SaveToDefFile(SimpleProductBase foo)throws Exception{
     if(lastSave==null){
       SaveToFile(foo);
     }
     File bar = new File(lastSave, "foo.txt");
     bar.createNewFile();
     foo.setSaveToFile(bar);
     foo.save();
    }
   
   static public SimpleProduct returnSimpleProduct(){
    Calendar today = new java.util.GregorianCalendar();
    today.add(Calendar.DAY_OF_MONTH,r(15));
    return new SimpleProduct(    
        nazwy[r(nazwy.length)],r(100),jednostka[r(jednostka.length)],f()
        ,opakowania[r(opakowania.length)], new Date(), today.getTime(),
        description[r(description.length)]
      );
  }

  public SimpleProductBaseMutable Load() throws Exception{
     JFileChooser choose = new JFileChooser();
     choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     choose.showOpenDialog(this);
     SimpleProductBaseMutable foo = new SimpleProductBaseMutable();
     File bar = new File(lastSave=choose.getSelectedFile(), "foo.txt");
     System.out.println(bar);
     foo.setloadFromFile(bar);
     foo.load();
     return foo;
   }
   
   public SimpleProductBaseMutable LoadDef() throws Exception{
     if(lastSave==null){
       return Load();
     }
     SimpleProductBaseMutable foo = new SimpleProductBaseMutable();
     File bar = new File(lastSave, "foo.txt");
     System.out.println(bar);
     foo.setloadFromFile(bar);
     foo.load();
     return foo;
   }
   
   
   
}
