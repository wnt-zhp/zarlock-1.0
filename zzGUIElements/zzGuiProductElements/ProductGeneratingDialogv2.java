/**
 * 
 */
package zzGUIElements.zzGuiProductElements;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;

import zzMyDate.MyDate;
import zzProduct.AbstractProduct;
import zzProduct.Product;
import zzProduct.SimpleProduct;

import java.awt.GridLayout;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JComboBox;

import zzEx.ZZEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNoCathegoryEx;
import zzGUIElements.zzTableElements.SimpleCombo;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.sun.crypto.provider.JceKeyStore;

/**
 * @author jb
 *
 */
public class ProductGeneratingDialogv2 extends JDialog {

  /**
   * This is the default constructor
   */
  public ProductGeneratingDialogv2() {
    super();
    initialize();
  }
  
  public ProductGeneratingDialogv2(JFrame owner) {
    super(owner, true);
    initialize();
    this.owner = owner;
  }
  
  public void display(){
    this.setLocationRelativeTo(owner);
    this.setLocation(owner.getWidth()/2,owner.getHeight()/2);
    this.setVisible(true);
   
  }
  
  
  public Product getWynik() {
    return wynik;
  }
  
  public void setModified(Product modified) {
    this.modified = modified;
    reinitialize();
  }

  public Product getModified() {
    return modified;
  }

  private void setWynikOk(boolean isWynikOk) {
    this.isWynikOk = isWynikOk;
  }

  public boolean isWynikOk() {
    return isWynikOk;
  }

  private boolean isWynikOk=false;
  private Product wynik;
  private Product modified;
  
  private void reinitialize(){
    if(getModified()!=null){
      try{
        getNazwa().setText(getModified().getName());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getIloscPoczatkowa().setText("" + getModified().getSQuantity());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getJednostkaCombo().setSelectedItem(getModified().getUnit());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getCena().setText("" + getModified().getPrice());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getOpakowanie().setSelectedItem(getModified().getPackaging());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }                  
      
      try{
        getIloscPoczatkowa().setText("" + getModified().getSQuantity());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getOpis().setText(getModified().getDescription());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getDataKsiegowania().setText("" + getModified().getDateOfBooking());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }
      
      try{
        getDataWaznosci().setText("" + getModified().getExpiryDate());
      }catch(ZZIllegalEx e){
        System.err.println("ProductGeneratingDialogv2");
      }      
    } 
  }
  
  /////GUI////
    
  private JPanel jContentPane = null;
  
  //FIXME isOK is to be erased
  
  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    //this.setSize(576, 329);
    this.setContentPane(getJContentPane());
    this.pack();
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(
        new WindowListener(){

          public void windowOpened(WindowEvent arg0) {
                        
          }

          public void windowClosing(WindowEvent arg0) {
            (new wykonajListener()).actionPerformed(null);            
          }

          public void windowClosed(WindowEvent arg0) {
            
          }

          public void windowIconified(WindowEvent arg0) {
            
            
          }

          public void windowDeiconified(WindowEvent arg0) {
            
            
          }

          public void windowActivated(WindowEvent arg0) {
            
            
          }

          public void windowDeactivated(WindowEvent arg0) {
            
            
          }
          
        }
      );
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
      gridBagConstraints4.gridx = 0;  // Generated
      gridBagConstraints4.gridy = 8;  // Generated
      GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
      gridBagConstraints31.fill = java.awt.GridBagConstraints.NONE;  // Generated
      gridBagConstraints31.gridy = 5;  // Generated
      gridBagConstraints31.weightx = 1.0;  // Generated
      gridBagConstraints31.gridx = 1;  // Generated
      GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
      gridBagConstraints22.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints22.gridy = 5;  // Generated
      gridBagConstraints22.weightx = 1.0;  // Generated
      gridBagConstraints22.gridx = 0;  // Generated
      GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
      gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints11.gridy = 7;  // Generated
      gridBagConstraints11.weightx = 1.0;  // Generated
      gridBagConstraints11.weighty = 1.0;  // Generated
      gridBagConstraints11.gridwidth = 2;  // Generated
      gridBagConstraints11.gridx = 0;  // Generated
      GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
      gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints5.gridy = 2;  // Generated
      gridBagConstraints5.weightx = 1.0;  // Generated
      gridBagConstraints5.gridx = 1;  // Generated
      GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
      gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints3.gridy = 1;  // Generated
      gridBagConstraints3.weightx = 1.0;  // Generated
      gridBagConstraints3.gridx = 0;  // Generated
      GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
      gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints21.gridy = 2;  // Generated
      gridBagConstraints21.weightx = 1.0;  // Generated
      gridBagConstraints21.gridx = 0;  // Generated
      GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
      gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints2.gridy = 2;  // Generated
      gridBagConstraints2.weightx = 1.0;  // Generated
      gridBagConstraints2.gridx = 0;  // Generated
      GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints1.gridy = 1;  // Generated
      gridBagConstraints1.weightx = 1.0;  // Generated
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints1.gridx = 1;  // Generated
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridy = 0;  // Generated
      gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;  // Generated
      gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
      gridBagConstraints.gridwidth = 1;  // Generated
      gridBagConstraints.gridx = 0;  // Generated
      jContentPane = new JPanel();
      jContentPane.setLayout(new GridBagLayout());  // Generated
      jContentPane.add(getNazwa(), gridBagConstraints);  // Generated
      jContentPane.add(getIloscPoczatkowa(), gridBagConstraints1);  // Generated
      jContentPane.add(getJednostkaCombo(), gridBagConstraints21);  // Generated
      jContentPane.add(getCena(), gridBagConstraints3);  // Generated
      jContentPane.add(getOpakowanie(), gridBagConstraints5);  // Generated
      jContentPane.add(getOpis(), gridBagConstraints11);  // Generated
      jContentPane.add(getDataKsiegowania(), gridBagConstraints22);  // Generated
      jContentPane.add(getDataWaznosci(), gridBagConstraints31);  // Generated
      jContentPane.add(getButtonPanel(), gridBagConstraints4);  // Generated
      jContentPane.setFocusTraversalPolicy(new policy());
    }
    return jContentPane;
  }

  private JFrame owner = null;

  private JTextField Nazwa = null;
  private JTextField iloscPoczatkowa = null;
  private SimpleCombo jednostkaCombo = null;
  private JTextField cena = null;
  private SimpleCombo opakowanie = null;
  private JTextArea opis = null;
  private JTextField dataKsiegowania = null;
  private JTextField dataWaznosci = null;
  private JPanel ButtonPanel = null;
  private JButton isOK = null;
  private JButton Wykonaj = null;

  /**
   * This method initializes Nazwa	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getNazwa() {
    if (Nazwa == null) {
      Nazwa = new JTextField();
      Nazwa.setColumns(10);
      Nazwa.setBorder(BorderFactory.createTitledBorder("Nazwa"));
      Nazwa.setToolTipText("Podaj nazwê. Nazwa nie musi byæ unikalna.");  // Generated
    }
    return Nazwa;
  }

  /**
   * This method initializes iloscPoczatkowa	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getIloscPoczatkowa() {
    if (iloscPoczatkowa == null) {
      iloscPoczatkowa = new JTextField();
      iloscPoczatkowa.setToolTipText("Podaj ilosc pocz¹tkow¹. Musi byæ dodatnia");  // Generated
      iloscPoczatkowa.setColumns(10);
      iloscPoczatkowa.setBorder(BorderFactory.createTitledBorder("Iloœæ pocz¹tkowa"));
      iloscPoczatkowa.setInputVerifier(new intVerifier ());
      
    }
    return iloscPoczatkowa;
  }

  /**
   * This method initializes jednostkaCombo	
   * 	
   * @return javax.swing.JComboBox	
   */
  private SimpleCombo getJednostkaCombo() {
    if (jednostkaCombo == null) {
      try{
        opakowanie = new SimpleCombo(AbstractProduct.getUnitMap());
        }catch(ZZIllegalEx e){
          System.out.println("No teraz to poieprz JAcka B. i powiedz mu" +
                  "ProductGeneratingPane");
        }//Ten blok cath sie nie pojawi.
        
      jednostkaCombo.setBorder(BorderFactory.createTitledBorder("Jednostka"));
    }
    return jednostkaCombo;
  }

  /**
   * This method initializes cena	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getCena() {
    if (cena == null) {
      cena = new JTextField();
      cena.setColumns(5);
      cena.setBorder(BorderFactory.createTitledBorder("Cena"));
      cena.setInputVerifier(new intVerifier());
    }
    return cena;
  }

  /**
   * This method initializes opakowanie	
   * 	
   * @return javax.swing.JComboBox	
   */
  private SimpleCombo getOpakowanie() {
    if (opakowanie == null) {
      try{
      opakowanie = new SimpleCombo(AbstractProduct.getPackagingMap());
      }catch(ZZIllegalEx e){
        System.out.println("No teraz to poieprz JAcka B. i powiedz mu" +
                "ProductGeneratingPane");
      }//Ten blok cath sie nie pojawi.
      //opakowanie = new JComboBox(new String[] {"Puszka", "Celofan", "luz"});
      opakowanie.setBorder(BorderFactory.createTitledBorder("Opakowanie"));
    }
    return opakowanie;
  }

  /**
   * This method initializes jTextArea	
   * 	
   * @return javax.swing.JTextArea	
   */
  private JTextArea getOpis() {
    if (opis == null) {
      opis = new JTextArea();
      opis.setBorder(BorderFactory.createTitledBorder("Opis"));
      opis.setColumns(20);
      opis.setRows(2);
      
    }
    return opis;
  }

  /**
   * This method initializes dataKsiegowania	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getDataKsiegowania() {
    if (dataKsiegowania == null) {
      dataKsiegowania = new JTextField();
      dataKsiegowania.setBorder(BorderFactory.createTitledBorder("Data ksiêgowania"));
      dataKsiegowania.setColumns(15);  // Generated
      dataKsiegowania.setInputVerifier(new datyInputVerifier());
    }
    return dataKsiegowania;
  }

  /**
   * This method initializes dataWaznosci	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getDataWaznosci() {
    if (dataWaznosci == null) {
      dataWaznosci = new JTextField();
      dataWaznosci.setBorder(BorderFactory.createTitledBorder("Data Wa¿noœci"));
      dataWaznosci.setColumns(15);  // Generated
      dataWaznosci.setInputVerifier(new datyInputVerifier());
    }
    return dataWaznosci;
  }

  /**
   * This method initializes ButtonPanel	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getButtonPanel() {
    if (ButtonPanel == null) {
      ButtonPanel = new JPanel();
      ButtonPanel.add(getIsOK(), null);  // Generated
      ButtonPanel.add(getWykonaj(), null);  // Generated
    }
    return ButtonPanel;
  }

  /**
   * This method initializes isOK	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getIsOK() {
    if (isOK == null) {
      isOK = new JButton("Ok?");
    }
    return isOK;
  }

  /**
   * This method initializes Wykonaj	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getWykonaj() {
    if (Wykonaj == null) {
      Wykonaj = new JButton("Wykonaj");
      Wykonaj.addActionListener(
          new wykonajListener()
          );
    }
    return Wykonaj;
  }

  private void setWynik(Product wynik) {
    this.wynik = wynik;
  }


  private JComponent[] components = new JComponent []{
    getNazwa(),
    getCena(),
    getIloscPoczatkowa(),
    getJednostkaCombo(),
    getOpakowanie(),
    getDataKsiegowania(),
    getDataWaznosci(),
    getOpis(),
    //getIsOK(),
    getWykonaj()
  };  
    
  private final class wykonajListener implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {
      setWynikOk(true);
      for(JComponent c :components){
        if(!c.getInputVerifier().verify(c)){
          setWynikOk(false);
          break;
        }
      }
      wynik = new SimpleProduct();
      
      try{
        wynik.setName(getNazwa().getText().trim().replace(",", " "));
      }catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      
      try{
        wynik.setDescription(getOpis().getText().trim().replace(",", " "));
      }catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      
      try{
        wynik.setPrice(Float.parseFloat(getCena().getText()));
      }catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }catch(NumberFormatException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }
      
      try{
        wynik.setSQuantity(Float.parseFloat(getIloscPoczatkowa().getText()));
      }catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }catch(NumberFormatException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }
      
      try{
        wynik.setPackaging(getOpakowanie().getSelectedItem().toString());
      }catch(ZZIllegalNoCathegoryEx e){
        System.err.println("ProductGenmeratingPaneError nie ma takiej kategorii");
      }
      catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      catch(NumberFormatException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }
      
      try{
        wynik.setUnit(getJednostkaCombo().getSelectedItem().toString());
      }catch(ZZIllegalNoCathegoryEx e){
        System.err.println("ProductGenmeratingPaneError nie ma takiej kategorii");
      }
      catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      catch(NumberFormatException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }
      
      try{
        wynik.setDateOfBooking(new MyDate(getDataKsiegowania().getText().trim()));
      }
      catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      catch(ParseException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }
      
      try{
        wynik.setExpiryDate(new MyDate(getDataWaznosci().getText().trim()));
      }
      catch(ZZEx e){
        System.err.println("ProductGenmeratingPaneError");
      }
      catch(ParseException e){
        System.err.println("ProductGenmeratingPaneError v2");
      }      
    }
  }

  private class policy extends FocusTraversalPolicy{

    @Override
    public Component getComponentAfter(Container arg0, Component arg1) {
      for(int i = 0 ; i < components.length; i++){
        if(components[i]==arg1){
          if(i+1 == components.length){
            return components[0];
          }
          return components[i+1];
        }
      }
      return getNazwa();
    }

    @Override
    public Component getComponentBefore(Container arg0, Component arg1) {
      for(int i = 0 ; i < components.length; i++){
        if(components[i]==arg1){
          if(i == 0){
            return components[components.length - 1];
          }
          return components[i-1];
        }
      }
      return getNazwa();
    }

    @Override
    public Component getFirstComponent(Container arg0) {
      // TODO Auto-generated method stub
      return getNazwa();
    }

    @Override
    public Component getLastComponent(Container arg0) {
      // TODO Auto-generated method stub
      return getWykonaj();
    }

    @Override
    public Component getDefaultComponent(Container arg0) {
      // TODO Auto-generated method stub
      return getNazwa();
    }
    
  }
  
  private class datyInputVerifier extends InputVerifier{
    @Override
    public boolean verify(JComponent arg0) {
      boolean ksiegEmpty=false, waznoscEmpty=false;
      if(getDataKsiegowania().getText().trim().equals(""))
        ksiegEmpty=true;
         
      if(getDataWaznosci().getText().trim().equals(""))
        waznoscEmpty=true;
      
      MyDate ksieg = null, waznosc=null; 
      try{
        if(!ksiegEmpty){
          ksieg = new MyDate(getDataKsiegowania().getText().trim());
        }
        if(!waznoscEmpty){
          waznosc = new MyDate(getDataWaznosci().getText().trim());
        }             
      }catch(ParseException e){
        return false;
      }
      if(! (ksiegEmpty || waznoscEmpty)){
        if(waznosc.after(ksieg))
          return true;
        else return false;
      }
      return true;
    }
    
    public boolean shouldYieldFocus(JComponent arg0){
      if(!verify(arg0))
        arg0.setBackground(Color.RED);
      try{
      if(!getDataWaznosci().getText().trim().equals(""))
        getDataWaznosci().setText(new MyDate (getDataWaznosci().getText()).toString());
      }catch(ParseException e){
        //I tak pewnie siê nie zdarzy
      }
      try{
      if(!getDataKsiegowania().getText().trim().equals(""))
        getDataKsiegowania().setText(new MyDate (getDataKsiegowania().getText()).toString());
      }catch(ParseException e){
        //I tak pewnie siê nie zdarzy
      }
      return true;
    }
  }
  
  private class intVerifier extends InputVerifier{
    @Override
    public boolean verify(JComponent arg0) {
      float i=0;
      try{
        i = Float.parseFloat(((JTextField)arg0).getText());
      }catch(NumberFormatException e){
        return false;
      }
      if(i>=0)
        return true;
      return false;
    }
    
    public boolean shouldYieldFocus(JComponent arg0){
      if(!verify(arg0))
        arg0.setBackground(Color.RED);
      else{
        JTextField foo =(JTextField) arg0;
        try{
          foo.setText("" + Float.parseFloat(foo.getText()));
        }catch(NumberFormatException e){
          //I tak siê nie zdarzy
        }
      }
        
      return true;
    }    
  }
  
}  //  @jve:decl-index=0:visual-constraint="305,7"
