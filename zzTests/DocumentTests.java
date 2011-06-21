package zzTests;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import zzDataBase.StaticContent;
import zzGUIElements.BranchGenerator;
import zzProduct.Product;


public class DocumentTests extends JFrame{

  public DocumentTests(JEditorPane e) throws Exception{
    super();
    add(e);
    pack();
    setVisible(true);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    zzTestMain.setUpDataBase(10);
    Product p = StaticContent.sc.getDataBaseMutable().getSorted().get(1);
    new DocumentTests(BranchGenerator.createProductDocument(p));

  }

}
