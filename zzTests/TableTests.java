package zzTests;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import zzDataBase.StaticContent;
import zzGUIElements.zzGuiProductElements.ProductTable;

public class TableTests extends JFrame {

  public TableTests() throws Exception {
    super();
    zzTestMain.setUpDataBase(100);
    StaticContent.sc.setUppmostFrame(this);
    ProductTable tab = new ProductTable();
    tab.setPreferredSize(new Dimension(640,480));
    add(tab);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String [] args)throws Exception{
    new TableTests();
  }
}
