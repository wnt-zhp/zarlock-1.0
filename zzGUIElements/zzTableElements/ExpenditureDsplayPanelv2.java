package zzGUIElements.zzTableElements;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import zzDataBase.StaticContent;
import zzProduct.Product;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ExpenditureDsplayPanelv2 extends JPanel {

  private ExpenditureComboBox expComboBox = null;
  private ExpenditureComboBoxButton opcje = null;

  /**
   * This is the default constructor
   */
  public ExpenditureDsplayPanelv2(Product ownerProduct, Component upperFrame) {
    super();
    setOwnerProduct(ownerProduct);
    setUpperFrame(upperFrame);
    initialize();
  }
  

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
    gridBagConstraints1.gridy = 0;  // Generated
    gridBagConstraints1.weightx = 0.05;  // Generated
    gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;  // Generated
    gridBagConstraints1.gridx = 1;  // Generated
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;  // Generated
    gridBagConstraints.weightx = 1.0;  // Generated
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
    gridBagConstraints.gridy = 0;  // Generated
    this.setLayout(new GridBagLayout());  // Generated
    this.add(getExpComboBox(), gridBagConstraints);  // Generated
    this.add(getOpcje(), gridBagConstraints1);  // Generated
    this.setMinimumSize(this.getPreferredSize());
    }

  /**
   * This method initializes expComboBox	
   * 	
   * @return javax.swing.JComboBox	
   */
  private ExpenditureComboBox getExpComboBox() {
    if (expComboBox == null) {
      expComboBox = new ExpenditureComboBox(getOwnerProduct());
    }
    return expComboBox;
  }

  /**
   * This method initializes opcje	
   * 	
   * @return javax.swing.JButton	
   */
  private ExpenditureComboBoxButton getOpcje() {
    if (opcje == null) {
      opcje = new ExpenditureComboBoxButton( getExpComboBox(),getUpperFrame());
    }
    return opcje;
  }
  
  public void setOwnerProduct(Product ownerProduct) {
    this.ownerProduct = ownerProduct;
  }

  public Product getOwnerProduct() {
    return ownerProduct;
  }

  public void setUpperFrame(Component upperFrame) {
    this.upperFrame = upperFrame;
  }


  public Component getUpperFrame() {
    return upperFrame;
  }

  private Product ownerProduct;
  private Component upperFrame;

}
