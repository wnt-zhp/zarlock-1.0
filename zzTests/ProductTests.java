/*
 * Created on 2006-03-06
 * Author jb
 *
 */
package zzTests;

import zzProduct.*;
import zzUser.Priveleges;
import zzUser.User;
import zzMyDate.*;
import StringIntTwoWayMap.*;
import zzEx.*;
import zzId.*;



public class ProductTests {
  
  static void makeSimpleMap(){
    User.setPriveleges(Priveleges.USER);
    User.setName("Butz");
    StringIntTwoWayMap packMap = new StringIntTwoWayMap();
    packMap.put(1, "Puszka stalowa");
    packMap.put(2, "Puszka glinowa");
    packMap.put(3, "Folia poliproetylenowa");
    
    StringIntTwoWayMap  unitMap = new StringIntTwoWayMap  ();
    unitMap.put(1, "Sztuki");
    unitMap.put(2, "Kilogramy");
    unitMap.put(3, "miligramy");
    SimpleProduct.setPackagingMap(packMap);
    SimpleProduct.setUnitMap(unitMap);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    //Testowanie zwyk³ego u¿ytkownika
    try{
      SimpleProduct.setBeginId(new ProdId(1000));
    }
    catch(Exception dupa){
      System.out.println("Nie uda³o siê ustawiæ pocz¹tkowego id");
    }
    makeSimpleMap();

    {
      //    TODO Marker 0
      Product foo = new SimpleProduct();
      try{
        foo.setName("Bulwy");
      }
      catch(ZZEx dupa){
        System.out.println( "setName" + 
            dupa.getMessage());
      }
      
      try{
        foo.setDateOfBooking(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println( "setDateofBooking" + 
            dupa.getMessage());
      }
      
      try{
        foo.setDescription("Nie jadalne - dawaæ niegrzecznym £owcom");
      }
      catch(ZZEx dupa){
        System.out.println("setDescription" + 
            dupa.getMessage());
      }
      try{
        foo.setExpiryDate(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println("setExpiryDate" + 
            dupa.getMessage());
      }
      try{
        foo.setFacturaNo("FX-007");
      }
      catch(ZZEx dupa){
        System.out.println("setFacturaNo" + 
            dupa.getMessage());
      }
      try{
        foo.setPackaging("Folia poliproetylenowa");
      }
      catch(ZZEx dupa){
        System.out.println("setPackaging" + 
            dupa.getMessage());
      }
      try{
        foo.setPrice(1000f);
      }
      catch(ZZEx dupa){
        System.out.println("setPrice" + 
            dupa.getMessage());
      }
      try{
        foo.setSQuantity(10000f);
      }
      catch(ZZEx dupa){
        System.out.println("setsQuantity" + 
            dupa.getMessage());
      }
      try{
        foo.setUnit("Sztuki");
      }
      catch(ZZEx dupa){
        System.out.println("setUnit" + 
            dupa.getMessage());
      }
      System.out.println(foo);
    }
    {
      Product foo = new SimpleProduct();
      //    TODO Marker 1
      try{
        foo.setName("Bulwy");
      }
      catch(ZZEx dupa){
        System.out.println( "setName" + 
            dupa.getMessage());
      }
      
      try{
        foo.setDateOfBooking(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println( "setDateofBooking" + 
            dupa.getMessage());
      }
      
      try{
        foo.setDescription("Nie jadalne - dawaæ niegrzecznym £owcom");
      }
      catch(ZZEx dupa){
        System.out.println("setDescription" + 
            dupa.getMessage());
      }
      try{
        foo.setExpiryDate(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println("setExpiryDate" + 
            dupa.getMessage());
      }
      try{
        foo.setFacturaNo("FX-007");
      }
      catch(ZZEx dupa){
        System.out.println("setFacturaNo" + 
            dupa.getMessage());
      }
      try{
        foo.setPackagingId(3);
      }
      catch(ZZEx dupa){
        System.out.println("setPackaging" + 
            dupa.getMessage());
      }
      try{
        foo.setPrice(1000f);
      }
      catch(ZZEx dupa){
        System.out.println("setPrice" + 
            dupa.getMessage());
      }
      try{
        foo.setSQuantity(10000f);
      }
      catch(ZZEx dupa){
        System.out.println("setsQuantity" + 
            dupa.getMessage());
      }
      try{
        foo.setUnitId(1);
      }
      catch(ZZEx dupa){
        System.out.println("setUnit" + 
            dupa.getMessage());
      }
      System.out.println(foo);
      System.out.println("Teraz (powinien) rzucaæ wyj¹tki");
      //    TODO Marker 2
      
      try{
        foo.setName("Bulwy");
      }
      catch(ZZEx dupa){
        System.out.println( "setName" + 
            dupa.getMessage());
      }
      try{
        foo.setDateOfBooking(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println( "setDateofBooking" + 
            dupa.getMessage());
      }
      
      try{
        foo.setDescription("Nie jadalne - dawaæ niegrzecznym £owcom");
      }
      catch(ZZEx dupa){
        System.out.println("setDescription" + 
            dupa.getMessage());
      }
      try{
        foo.setExpiryDate(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println("setExpiryDate" + 
            dupa.getMessage());
      }
      try{
        foo.setFacturaNo("FX-007");
      }
      catch(ZZEx dupa){
        System.out.println("setFacturaNo" + 
            dupa.getMessage());
      }
      try{
        foo.setPackagingId(3);
      }
      catch(ZZEx dupa){
        System.out.println("setPackaging" + 
            dupa.getMessage());
      }
      try{
        foo.setPrice(1000f);
      }
      catch(ZZEx dupa){
        System.out.println("setPrice" + 
            dupa.getMessage());
      }
      try{
        foo.setSQuantity(10000f);
      }
      catch(ZZEx dupa){
        System.out.println("setsQuantity" + 
            dupa.getMessage());
      }
      try{
        foo.setUnitId(1);
      }
      catch(ZZEx dupa){
        System.out.println("setUnit" + 
            dupa.getMessage());
      }
      // TODO Marker 3
 System.out.println("Teraz (powinien) rzucaæ wyj¹tki");
      
      try{
        foo.changeName("Bulwy ch");
      }
      catch(ZZEx dupa){
        System.out.println( "changeName" + 
            dupa.getMessage());
      }
      try{
        foo.changeDateOfBooking(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println( "changeDateofBooking" + 
            dupa.getMessage());
      }
      
      try{
        foo.changeDescription("Jadalne - dawaæ niegrzecznym £owcom");
      }
      catch(ZZEx dupa){
        System.out.println("changeDescription" + 
            dupa.getMessage());
      }
      try{
        foo.changeExpiryDate(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println("changeExpiryDate" + 
            dupa.getMessage());
      }
      try{
        foo.changeFacturaNo("FX-0078");
      }
      catch(ZZEx dupa){
        System.out.println("changeFacturaNo" + 
            dupa.getMessage());
      }
      try{
        foo.changePackagingId(2);
      }
      catch(ZZEx dupa){
        System.out.println("changePackaging" + 
            dupa.getMessage());
      }
      try{
        foo.changePrice(100f);
      }
      catch(ZZEx dupa){
        System.out.println("changePrice" + 
            dupa.getMessage());
      }
      try{
        foo.changeSQuantity(100f);
      }
      catch(ZZEx dupa){
        System.out.println("changesQuantity" + 
            dupa.getMessage());
      }
      try{
        foo.changeUnitId(2);
      }
      catch(ZZEx dupa){
        System.out.println("changeUnit" + 
            dupa.getMessage());
      }
      // TODO Marker 4
      User.setPriveleges(Priveleges.ROOTUSER);
      System.out.println("Teraz znowó¿ nie");
      
      try{
        foo.changeName("Bulwy ch");
      }
      catch(ZZEx dupa){
        System.out.println( "changeName" + 
            dupa.getMessage());
      }
      try{
        foo.changeDateOfBooking(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println( "changeDateofBooking" + 
            dupa.getMessage());
      }
      
      try{
        foo.changeDescription("Jadalne - dawaæ niegrzecznym £owcom");
      }
      catch(ZZEx dupa){
        System.out.println("changeDescription" + 
            dupa.getMessage());
      }
      try{
        foo.changeExpiryDate(new MyDate());
      }
      catch(ZZEx dupa){
        System.out.println("changeExpiryDate" + 
            dupa.getMessage());
      }
      try{
        foo.changeFacturaNo("FX-0078");
      }
      catch(ZZEx dupa){
        System.out.println("changeFacturaNo" + 
            dupa.getMessage());
      }
      try{
        foo.changePackagingId(2);
      }
      catch(ZZEx dupa){
        System.out.println("changePackaging" + 
            dupa.getMessage());
      }
      try{
        foo.changePrice(100f);
      }
      catch(ZZEx dupa){
        System.out.println("changePrice" + 
            dupa.getMessage());
      }
      try{
        foo.changeSQuantity(100f);
      }
      catch(ZZEx dupa){
        System.out.println("changesQuantity" + 
            dupa.getMessage());
      }
      try{
        foo.changeUnitId(2);
      }
      catch(ZZEx dupa){
        System.out.println("changeUnit" + 
            dupa.getMessage());
      }
      System.out.println(foo);      
      //TODO Marker 5
      try{
        SimpleProduct.setBeginId(new ProdId(1000));
        //System.out.println("Tego tu nie powinno byæ");
      }
      catch(Exception dupa){        
      }
      // TODO Marker 6 - próby odczytu
      
      System.out.print("\n TERAZ PRÓBA ODCZYTU Z STRINGA\n\n");
      String text = new String (foo.toString());
      
      try{
        System.out.println(foo);
        Product foo2 = new SimpleProduct(text);        
        System.out.println(foo2);
      }
      catch(ZZEx dupa){
        System.out.println("Excepion:" + 
            dupa.getClass() +
            "\nMessage: " + dupa.getMessage());
      }
      

      
    }
  }
}
