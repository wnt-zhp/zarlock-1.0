package zzGUIElements.zzExpPaneElements;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class minMaxPanev2
    extends JPanel {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final String labelAction = "789751313213246798747564879";

  /**
   * This is the default constructor
   */
  public minMaxPanev2() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
    gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
    gridBagConstraints11.gridy = 0;  // Generated
    gridBagConstraints11.weightx = 1.0;  // Generated
    gridBagConstraints11.gridheight = 1;  // Generated
    gridBagConstraints11.gridx = 1;  // Generated
    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
    gridBagConstraints1.gridx = 2;  // Generated
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.CENTER;  // Generated
    gridBagConstraints1.fill = java.awt.GridBagConstraints.NONE;  // Generated
    gridBagConstraints1.gridy = 0;  // Generated
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(0,0,0,0);  // Generated
    gridBagConstraints.gridy = 0;  // Generated
    gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;  // Generated
    gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;  // Generated
    gridBagConstraints.gridx = 0;  // Generated
    lewa = new JLabel();
    lewa.setFont(new Font(lewa.getFont().getName(), Font.ITALIC,lewa.getFont().getSize()));
    ////lewa.setText("<I>(min )");
    prawa = new JLabel();
    //prawa.setText("(max)");  
    prawa.setFont(new Font(prawa.getFont().getName(), Font.ITALIC,prawa.getFont().getSize()));
    
    this.setLayout(new GridBagLayout());  // Generated
    //this.setSize(397, 135);
    this.setSize(this.getPreferredSize());
    this.add(lewa, gridBagConstraints);  // Generated
    this.add(prawa, gridBagConstraints1);  // Generated
    this.setBorder(BorderFactory.createTitledBorder(""));
    this.add(getZawartosc(), gridBagConstraints11);  // Generated
  }
  
  public void setMinl( String minl) {
    this.minl = minl;
    reintiailzeMin();
  }


  public String getMinl() {
    return minl;
  }


  public void setMinp( String minp) {
    this.minp = minp;
    reintiailzeMin();
  }


  public String getMinp() {
    return minp;
  }


  void setMaxl(
      String maxl) {
    this.maxl = maxl;
    reinitializeMax();
  }

 
  public  String getMaxl() {
    return maxl;
  }


  public void setMaxp(
      String maxp) {
    this.maxp = maxp;
    reinitializeMax();
  }


  public  String getMaxp() {
    return maxp;
  }


  public void setMax(String max) {
    this.max = max;
    reinitializeMax();
  }


  public String getMax() {
    return max;
  }


  public void setMin(String min) {
    this.min = min;
    reintiailzeMin();
  }


  public String getMin() {
    return min;
  }
  
  public void  setColumns(int i){
    zawartosc.setColumns(i);
  }
  public int getColumns(int i){
    return zawartosc.getColumns();
  }

  public void setText(String text){
    zawartosc.setText(text);
  }
  
  public String getText(){
    return zawartosc.getText();
  }
  
  private String max, min;
  
  private String maxp = ")";
  private String maxl= "(max. ";
  private String minp =")" ;
  private String minl = "(min. ";

  private JLabel lewa = null;

  private JLabel prawa = null;

  private JTextField zawartosc = null;
  
  

  /**
   * This method initializes zawartosc	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getZawartosc() {
    if (zawartosc == null) {
      zawartosc = new JTextField();
      zawartosc.setActionCommand(labelAction);
      zawartosc.setColumns(10);
      
    }
    return zawartosc;
  }
  
  
  private void reintiailzeMin(){
    if(min!=null){
      lewa.setText(minl + min + minp);
      lewa.setVisible(true);
    }
    else{
      lewa.setText("");
      lewa.setVisible(false);
    }
  }
  private void reinitializeMax(){
    if(max!=null){
      prawa.setText(maxl+ max + maxp);
      prawa.setVisible(true);
    }
    else{
      prawa.setText("");
      prawa.setVisible(true);
    }
  }

  public void setName(String name) {
    super.setName(name);
    this.setBorder(BorderFactory.createTitledBorder(name));
    //this.name = name;
  }

  public String getName() {
    return super.getName();
  }

  public void setPanelBackgroundColor(Color c){
    zawartosc.setBackground(c);
  }
  
  public void addActionListener(ActionListener a){
    zawartosc.addActionListener(a);
  }
  
  public void setActionCommand(String Command){
    zawartosc.setActionCommand(Command);
  }
  public void removeActionListener(ActionListener a){
    zawartosc.removeActionListener(a);
  }
}  //  @jve:decl-index=0:visual-constraint="38,32"
