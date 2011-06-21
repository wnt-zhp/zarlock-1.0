/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzTests;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.Date;
import java.util.Vector;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzGUIElements.zzTableElements.ExpenditureComboBox;
import zzGUIElements.zzTableElements.UnitComboBox;
import zzId.ExpenditureId;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzTests.zzStringIntComboBoxTesrs.al;
import zzUser.Priveleges;
import zzUser.User;

public class zzExpComboBoxTests extends JFrame {
  Product owner;
  
  ExpenditureComboBox combo;
  JLabel label;
  
  public zzExpComboBoxTests(String name) 
  throws HeadlessException, ZZIllegalEx {
    super(name);
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
    combo = new ExpenditureComboBox(owner);
    combo.setVisible(true);
    //combo.setPreferredSize(new Dimension(100,20));
    System.out.println(combo.getItemCount());
    this.getContentPane().add(combo);
    setLayout(new FlowLayout());
    pack();
    super.setVisible(true);
   
    /* label = new JLabel();
    label.setText((String)combo.getSelectedItem());
    label.setVisible(true);
    combo.addActionListener(new al());
    add(label);
    pack();
    setVisible(true);*/
    
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    new zzExpComboBoxTests("Szalenie powa¿na nazwa");

  }

}
