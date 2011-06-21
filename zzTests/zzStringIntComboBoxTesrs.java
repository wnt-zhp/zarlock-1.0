/*
 * Created on 2006-05-19
 * Author jb
 *
 */
package zzTests;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import zzEx.ZZIllegalEx;
import zzGUIElements.zzTableElements.UnitComboBox;
import zzProduct.AbstractProduct;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;

import StringIntTwoWayMap.StringIntTwoWayMap;

public class zzStringIntComboBoxTesrs extends JFrame {

  public zzStringIntComboBoxTesrs(String name) 
  throws HeadlessException, ZZIllegalEx {
    super(name);
    User.setName("UUUU");
    User.setPriveleges(Priveleges.ROOTUSER);
    SimpleBaseTests.setUpProduct(); 
    owner = SimpleBaseTests.returnSimpleProduct();
    combo = new UnitComboBox(owner);
    combo.setVisible(true);
    combo.setPreferredSize(new Dimension(100,20));
    this.getContentPane().add(combo);
    setLayout(new FlowLayout());
    label = new JLabel();
    label.setText((String)combo.getSelectedItem());
    label.setVisible(true);
    combo.addActionListener(new al());
    add(label);
    pack();
    setVisible(true);
    
  }
  
  Product owner;
  UnitComboBox combo;
  JLabel label;
  
  class al implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
      label.setText((String)combo.getSelectedItem());
      System.err.println("al:" + (String)combo.getSelectedItem());
    }    
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws ZZIllegalEx {
    new zzStringIntComboBoxTesrs("Powa¿na nazwa").setVisible(true);

  }

}
