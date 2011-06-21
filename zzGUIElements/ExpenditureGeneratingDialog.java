package zzGUIElements;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.JButton;

import zzDataBase.Expenditure;
import zzEx.ZZIllegalEx;
import zzGUIElements.zzExpPaneElements.minMaxPanev2;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BoxLayout;


public class ExpenditureGeneratingDialog extends JDialog {

  //private ExpenditureGeneratingDialog THIS = this;
  /**
   * This is the default constructor
   */
  public ExpenditureGeneratingDialog() {
    super();
    initialize();
  }
  
  public ExpenditureGeneratingDialog(JFrame owner) {
    super(owner,true);
    initialize();
    setLocationRelativeTo(owner);
    setLocation(owner.getWidth()/2,owner.getHeight()/2);
  }
  
  public Product getProductOwner(){
    return owner;

  }
  
  public void setModifiedExpenditure(Expenditure e){
    modified = e;
    reinitialize();
  }
  
  public Expenditure getModifiedExpenditure(){
     return modified;
  }
  
  public Expenditure display(){
	  setProductOwner(null);
	  setModifiedExpenditure(null);
	  setVisible(true);
	  return getCreatedExpenditure();
  }
  
  public Expenditure display(Product owner){
	  setProductOwner(owner);
	  setModifiedExpenditure(null);
	  setVisible(true);
	  return getCreatedExpenditure();
  }
  
  public Expenditure display(Product owner, Expenditure modified){
	  setProductOwner(owner);
	  setModifiedExpenditure(modified);
	  setVisible(true);
	  return getCreatedExpenditure();
  }
  
  public void setTytylemPusty(boolean b){
    tytulemPusty=b;    
    reinitialize();
  }
  public boolean hasFinishedSuccesfully(){
    return finishedSucccesfully;
  }
  
  /**
   * It is possible that created expenditure is not-valid, i.e. adding it to the 
   * product will resuli in throwing an exception. It happens when you add 
   * Expenditure to modiy, and that expenditure is a part ow owner product. 
   * Or if product is modified while displaying thid dialog.
   * @return
   */
  public Expenditure getCreatedExpenditure(){
    return wynik;
  }
  
  public void setProductOwner(Product p){
    owner=p;
    reinitialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setSize(300, 200);
    this.setContentPane(getJContentPane());  // Generated
    this.setTitle("Podaj dane dot. wyjscia z magazynu");  // Generated
    this.setModal(true);
    this.pack();
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
      gridBagConstraints21.gridx = 1;  // Generated
      gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints21.gridy = 3;  // Generated
      GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
      gridBagConstraints2.gridx = 0;  // Generated
      gridBagConstraints2.gridwidth = 2;  // Generated
      gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints2.gridy = 2;  // Generated
      GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
      gridBagConstraints1.gridx = 0;  // Generated
      gridBagConstraints1.gridwidth = 2;  // Generated
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints1.gridy = 1;  // Generated
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.insets = new java.awt.Insets(0,0,0,0);  // Generated
      gridBagConstraints.gridy = 0;  // Generated
      gridBagConstraints.gridwidth = 2;  // Generated
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints.gridx = 0;  // Generated
      jContentPane = new JPanel();
      jContentPane.setLayout(new GridBagLayout());
      jContentPane.add(getIle(), gridBagConstraints);  // Generated
      jContentPane.add(getKiedy(), gridBagConstraints1);  // Generated
      jContentPane.add(getTytulem(), gridBagConstraints2);  // Generated
      jContentPane.add(getButtonPanel(), gridBagConstraints21);  // Generated
      
      getOK().addActionListener(
        new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {
            checkExpenditureOK();
          }
         
        }
      );
      
      getWykonaj().addActionListener(
          new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
               if(checkExpenditureOK()){
                 wynik = new Expenditure(ileWynik,kiedyWynik,tytulemWynik);
                 finishedSucccesfully=true;                 
               }
               setVisible(false);
            }            
          }
      );
      
      reinitialize();
    }
    return jContentPane;
  }

  /**
   * This method initializes ile	
   * 	
   * @return zzGUIElements.zzExpPaneElements.minMaxPanev2	
   */
  private minMaxPanev2 getIle() {
    if (ile == null) {
      ile = new minMaxPanev2();
      ile.setName("Ile:  ");
      ile.setMin("" + 0);
      ile.setColumns(5);
      ile.addActionListener(new reinitializeActiolListener());
    }
    return ile;
  }

  /**
   * This method initializes kiedy	
   * 	
   * @return zzGUIElements.zzExpPaneElements.minMaxPanev2	
   */
  private minMaxPanev2 getKiedy() {
    if (kiedy == null) {
      kiedy = new minMaxPanev2();
      kiedy.setName("Kiedy: ");
      kiedy.setColumns(12);
      kiedy.addActionListener(new reinitializeActiolListener());
    }
    return kiedy;
  }

  /**
   * This method initializes tytulem	
   * 	
   * @return zzGUIElements.zzExpPaneElements.minMaxPanev2	
   */
  private minMaxPanev2 getTytulem() {
    if (tytulem == null) {
      tytulem = new minMaxPanev2();
      tytulem.setName("Tytu³em ");
      tytulem.setMin("1 znak");
      tytulem.setColumns(15);
      tytulem.addActionListener(new reinitializeActiolListener());
    }
    return tytulem;
  }

  /**
   * This method initializes OK	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getOK() {
    if (OK == null) {
      OK = new JButton("Ok?");
      OK.setBorder(BorderFactory.createLineBorder(UIManager.getColor(OK),3));
      OK.setAlignmentX(JComponent.LEFT_ALIGNMENT);
    }
    return OK;
  }

  /**
   * This method initializes wykonaj	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getWykonaj() {
    if (wykonaj == null) {
      wykonaj = new JButton("wykonaj");
      wykonaj.setBorder(BorderFactory.createLineBorder(UIManager.getColor(wykonaj),3));
    }
    return wykonaj;
  }
  
  private void reinitialize(){
    intMax=0;
    intMin=0.0001f;
    BookingDate=null;
    ExpiryDate=null;
    if(tytulemPusty) tytulem.setMin(null);
    else tytulem.setMin("1 znak");
    reSetIle();
    reSetKiedy();
    reSetTytulem();
    if(owner!=null){
      try{
        intMax+=owner.getCurrQ();
        BookingDate=owner.getDateOfBooking();
        ExpiryDate = owner.getExpiryDate();
        if(modified != null){
          intMax+=modified.ile;
          ile.setText("" + modified.ile);
          kiedy.setText(modified.date.toString());
          tytulem.setText(modified.tytulem);
        }
      }catch(ZZIllegalEx e){    
      }
      if(intMax!=0) ile.setMax("" + intMax);
      ile.setMin("" + 0.0001);
      if(BookingDate!=null) kiedy.setMin(BookingDate.toString());
      if(ExpiryDate!=null) kiedy.setMax(ExpiryDate.toString());
      this.pack();
    }    
  }

  
  private void setOKRed(){
    OK.setBorder(BorderFactory.createLineBorder(Color.RED,3));
  }
  private void setOKGreen(){
    OK.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
  }
  
  private void setIleRed(){
    ile.setPanelBackgroundColor(Color.RED);
  }
  
  private void reSetIle(){
    ile.setPanelBackgroundColor(Color.WHITE);
  }
  
  private void setKiedyRed(){
    kiedy.setPanelBackgroundColor(Color.RED);
  }
  
  private void reSetKiedy(){
    kiedy.setPanelBackgroundColor(Color.WHITE);
  }
  
  @SuppressWarnings("unused")
  private void setTytulemRed(){
    tytulem.setPanelBackgroundColor(Color.RED);
  }
  
  private void reSetTytulem(){
    tytulem.setPanelBackgroundColor(Color.WHITE);
  }
  
  private boolean checkExpenditureOK(){
    sukcess=true;
    MyDate data=null; 
    System.out.println(sukcess);
    float ilosc=0;
    try{
      ilosc = Float.parseFloat(ile.getText().replace(",", "."));
      if(intMax!=0 && ilosc > intMax){
        ile.setText("" + intMax);
        sukcess=false;
      }
      if(ilosc < intMin){
        ile.setText("" + intMin);
        sukcess=false;
      }
      reSetIle();
    }catch(NumberFormatException e){
      sukcess=false;
      setIleRed();
    }
    System.out.println(sukcess);
    try{
      data = new MyDate(kiedy.getText().replace(",", "."));
      if(ExpiryDate!= null && ExpiryDate.before(data)){
        kiedy.setText(ExpiryDate.toString());
        sukcess=false;
        System.out.println("Za wczeœni");
      }
      if(BookingDate!= null && BookingDate.after(data)){
        kiedy.setText(BookingDate.toString());
        sukcess=false;
        System.out.print("Za po¿no");
      }
      kiedy.setText(data.toString());
      reSetKiedy();
    }catch(ParseException e){
      sukcess=false;
      kiedy.setText("ParseError");
      setKiedyRed();
      //e.printStackTrace();
    }
    System.out.println(sukcess);
    if(!tytulemPusty && tytulem.getText().trim().equals("")){
      sukcess=false;
      tytulem.setText(User.getName());
    }

    System.out.println(sukcess);
    if(sukcess){
      ileWynik=ilosc;
      kiedyWynik = data;
      tytulemWynik = tytulem.getText().trim();   
      reSetIle();
      reSetKiedy();
      reSetTytulem();
      setOKGreen();
      System.out.println("OK");
    } else setOKRed(); 
    return sukcess;
  }

  
  private JPanel jContentPane = null;
  private minMaxPanev2 ile = null;
  private minMaxPanev2 kiedy = null;
  private minMaxPanev2 tytulem = null;
  private JButton OK = null;
  private JButton wykonaj = null;
  private Product owner = null;
  private Expenditure modified = null; 
  private float intMax=0, intMin=0.0001f;
  private MyDate BookingDate=null, ExpiryDate=null;
  private boolean sukcess = true, tytulemPusty=false;
  private boolean finishedSucccesfully = false;
  private float ileWynik;
  private MyDate kiedyWynik;
  private String tytulemWynik;
  private Expenditure wynik;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel ButtonPanel = null;
  /**
   * This method initializes ButtonPanel	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getButtonPanel() {
    if (ButtonPanel == null) {
      ButtonPanel = new JPanel();
      ButtonPanel.setLayout(new BoxLayout(getButtonPanel(), BoxLayout.X_AXIS));  // Generated
      ButtonPanel.add(getOK(), null);  // Generated
      ButtonPanel.add(Box.createRigidArea(new Dimension(10,0)));
      ButtonPanel.add(getWykonaj(), null);  // Generated
    }
    return ButtonPanel;
  }
  
  private class reinitializeActiolListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
      finishedSucccesfully = checkExpenditureOK();     
    }    
  }
}  //  @jve:decl-index=0:visual-constraint="198,47"
