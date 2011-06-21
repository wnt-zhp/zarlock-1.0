/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzTests;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;

import zzDataBase.Expenditure;
import zzGUIElements.zzTableElements.ExpenditureDisplayLabel;
import zzGUIElements.zzTableElements.ExpenditureDsplayPanelv2;
import zzId.ExpenditureId;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;

public class zzExpComboAndButtonTests extends JFrame {
  Product owner;

  ExpenditureDsplayPanelv2 lab;
  public zzExpComboAndButtonTests() throws Exception {
    super("Nazwa");
    User.setName("UUUU");
    User.setPriveleges(Priveleges.ROOTUSER);
    SimpleBaseTests.setUpProduct(); 
    owner = SimpleBaseTests.returnSimpleProduct();
    owner.changeSQuantity(100);
    owner.changeExpiryDate(new MyDate(new Date(new Date().getTime()+24*3600*1000*100)));
    owner.performExpenditure(10, new MyDate(), "AAA");
    owner.performExpenditure(10, new MyDate(), "foo");
    owner.performExpenditure(10, new MyDate(), "bar");
    owner.performExpenditure(10, new MyDate(), "baz");
    owner.performExpenditure(10, new MyDate(), "uaz");
    Vector<Map.Entry<ExpenditureId,Expenditure> >foo = owner.getExpenditures();
    for(Map.Entry<ExpenditureId,Expenditure>  e :foo){
      System.out.println(e.getValue());
    }
    lab = new ExpenditureDsplayPanelv2 (owner, this);
    add(lab);
    pack();
    setVisible(true);    
  }
  
  public static void main (String [] a) throws Exception{
    new zzExpComboAndButtonTests();
  }
}
