package zzGUIElements.zzProdDialElements;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

public class PanelWithTips extends JPanel {

  private JLabel leftTip = null;
  private JTextField Field = null;
  private JLabel rightTip = null;

  /**
   * This is the default constructor
   */
  public PanelWithTips() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
    gridBagConstraints2.gridx = 2;  // Generated
    gridBagConstraints2.gridy = 0;  // Generated
    rightTip = new JLabel();
    rightTip.setFont(new Font(leftTip.getFont().getName(), Font.ITALIC, getFont().getSize()));
    rightTip.setText("JLabel");  // Generated
    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
    gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
    gridBagConstraints1.gridy = 0;  // Generated
    gridBagConstraints1.weightx = 1.0;  // Generated
    gridBagConstraints1.gridx = 1;  // Generated
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;  // Generated
    gridBagConstraints.gridy = 0;  // Generated
    leftTip = new JLabel();
    leftTip.setFont(new Font(leftTip.getFont().getName(), Font.ITALIC, getFont().getSize()));
    leftTip.setText("JLabel");  // Generated
    this.setLayout(new GridBagLayout());  // Generated
    this.setSize(300, 200);
    this.add(leftTip, gridBagConstraints);  // Generated
    this.add(getField(), gridBagConstraints1);  // Generated
    this.add(rightTip, gridBagConstraints2);  // Generated
  }

  /**
   * This method initializes Field	
   * 	
   * @return javax.swing.JTextField	
   */
  public JTextField getField() {
    if (Field == null) {
      Field = new JTextField();
    }
    return Field;
  }
  
  
}
