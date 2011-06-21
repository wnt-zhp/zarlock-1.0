/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;

public class ExpenditureDisplayLabel extends JPanel {
  Product owner;
  Component matka;
  ExpenditureComboBox box;
  ExpenditureComboBoxButton button;
  public ExpenditureDisplayLabel(Product o, Component c) {
    super();
    owner = o;
    matka = c;
    box = new ExpenditureComboBox (o);
    button = new ExpenditureComboBoxButton(box,c); 
    setPreferredSize(new Dimension(
        box.getPreferredSize().width + button.getPreferredSize().width+10,
        box.getPreferredSize().height + button.getPreferredSize().height));
    add(box);
    add(button);
    setVisible(true);
  }

}
