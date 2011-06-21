package zzTests;

import javax.swing.JButton;
import javax.swing.JFrame;

import zzDataBase.Expenditure;
import zzGUIElements.ExpenditureGeneratingDialog;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;

public class zzExpGenDialogTests extends JFrame {
  static Product owner;
  ExpenditureGeneratingDialog dial;
  Expenditure mod = new Expenditure (10, new MyDate(), "foo");
  public zzExpGenDialogTests()throws Exception{
    super();
    User.setName("UUUU");
    User.setPriveleges(Priveleges.ROOTUSER);
    SimpleBaseTests.setUpProduct(); 
    getContentPane().add(new JButton("foo"));
    owner = SimpleBaseTests.returnSimpleProduct();
    owner.changeSQuantity(100);
    mod = new Expenditure (10, new MyDate(), "foo");
    setSize(640,320);
    dial = new ExpenditureGeneratingDialog (this);
    setVisible(true);
    basic();
    intermediate();
    advanced();
  }
  
  public static void main(String[] args) throws Exception{
    new zzExpGenDialogTests();    
  }
  
   void basic(){
    dial.setTytylemPusty(false);
    dial.setVisible(true);
    if(dial.hasFinishedSuccesfully()) System.out.println(dial.getCreatedExpenditure().toString());
    else System.out.println("Badass");
   }
   
   private void intermediate() throws Exception{
    dial.setProductOwner(owner);
    dial.setVisible(true);
    if(dial.hasFinishedSuccesfully()) System.out.println(dial.getCreatedExpenditure().toString());
    else System.out.println("Badass");
    owner.performExpenditure(mod);
    dial.setProductOwner(owner);
    dial.setVisible(true);
    if(dial.hasFinishedSuccesfully()) System.out.println(dial.getCreatedExpenditure().toString());
    else System.out.println("Badass");
  }
   private void advanced() throws Exception{
     dial.setProductOwner(owner);
     dial.setModifiedExpenditure(mod);
     dial.setVisible(true);
     if(dial.hasFinishedSuccesfully()) System.out.println(dial.getCreatedExpenditure().toString());
     else System.out.println("Badass");
   }
}
