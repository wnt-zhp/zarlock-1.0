package zzGUIElements.zzGuiProductElements;

import java.awt.BorderLayout;

import javax.naming.NoPermissionException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JDialog;

import zzDataBase.StaticContent;
import zzEx.ZZEx;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotInitializedEx;
import zzMyDate.MyDate;
import zzProduct.AbstractProduct;
import zzProduct.Product;
import zzProduct.SimpleProduct;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

public class ProductGeneratingDialogv3 extends JDialog {

   private JPanel jContentPane = null;

   private JTextField nameTextField = null;

   private JTextField sQuantityTextField = null;

   private JTextField iloœæTeraz = null;

   private JComboBox jednostkaCombo = null;

   private JComboBox opakowanieCombo = null;

   private DefaultComboBoxModel jednostkaModel = null;

   private DefaultComboBoxModel opakowanieModel = null;

   private JTextField cena = null;

   private JTextField fakturaNoTextField = null;

   private JPanel jPanel = null;

   private JTextField bookingDateTextField = null;

   private JTextField expirtDateTextField = null;

   private JScrollPane jScrollPane = null;

   private JTextPane opisTextPane = null;

   private JPanel jPanel1 = null;

   private JButton OK = null;

   private JButton anuluj = null;

   private Flavour flav = null;

   private DefaultListModel listModel = null;

   private int result = USER_CLICKED_ABORD;

   private Product resultProduct = null;

   public static final int USER_CLICKED_OK = 0;

   public static final int USER_CLICKED_ABORD = 1;

   public static final int USER_EXITED = 2;

   private JList bledyList = null;

   private JButton utwozSzablon = null;

   private JComboBox vatBox = null;

   private JPanel jPanel2 = null;

   private JLabel procent = null;

   private JPanel jPanel3 = null;

   private JPanel jPanel4 = null;

   private JTextField zVatTextFirld = null;

   public int display() {
      result = USER_CLICKED_ABORD;
      resultProduct = null;
      flav = new noProductFlavour();
      setVisible(true);
      flav.dispose();
      return result;
   }

   public int display(Product p) {
      result = USER_CLICKED_ABORD;
      resultProduct = null;
      flav = new ProductFlavour(p);
      setVisible(true);
      flav.dispose();
      return result;
   }

   public int display(template t) {
      result = USER_CLICKED_ABORD;
      resultProduct = null;
      flav = new templateFlav(t);
      setVisible(true);
      flav.dispose();
      return result;
   }

   public Product getProduct() throws ZZIllegalEx {
      if (resultProduct == null) {
         throw new ZZIllegalEx("foo");
      }
      return resultProduct;
   }

   public Vector<String> getors() {
      return flav.getErrors();
   }

   /**
    * This is the default constructor
    */
   public ProductGeneratingDialogv3() {
      super();
      this.setModal(true);
      initialize();
   }

   /**
    * This method initializes this
    * 
    * @return void
    */
   private void initialize() {
      this.setSize(300, 400);
      this.setContentPane(getJContentPane());
   }

   /**
    * This method initializes jContentPane
    * 
    * @return javax.swing.JPanel
    */
   private JPanel getJContentPane() {
      if (jContentPane == null) {
         GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
         gridBagConstraints71.gridx = 1;
         gridBagConstraints71.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints71.gridy = 4;
         GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
         gridBagConstraints61.gridx = 0;
         gridBagConstraints61.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints61.gridwidth = 2;
         gridBagConstraints61.gridy = 1;
         GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
         gridBagConstraints12.fill = java.awt.GridBagConstraints.BOTH; // Generated
         gridBagConstraints12.gridy = 10; // Generated
         gridBagConstraints12.weightx = 1.0; // Generated
         gridBagConstraints12.weighty = 1.0; // Generated
         gridBagConstraints12.gridwidth = 2; // Generated
         gridBagConstraints12.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
         gridBagConstraints14.gridx = 0; // Generated
         gridBagConstraints14.anchor = java.awt.GridBagConstraints.WEST; // Generated
         gridBagConstraints14.gridwidth = 2; // Generated
         gridBagConstraints14.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints14.gridy = 11; // Generated
         GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
         gridBagConstraints111.fill = java.awt.GridBagConstraints.BOTH; // Generated
         gridBagConstraints111.gridy = 9; // Generated
         gridBagConstraints111.weightx = 1.0; // Generated
         gridBagConstraints111.weighty = 1.0; // Generated
         gridBagConstraints111.gridwidth = 2; // Generated
         gridBagConstraints111.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
         gridBagConstraints9.gridx = 0; // Generated
         gridBagConstraints9.gridwidth = 2; // Generated
         gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints9.gridy = 7; // Generated
         GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
         gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints7.gridy = 5; // Generated
         gridBagConstraints7.weightx = 1.0; // Generated
         gridBagConstraints7.gridwidth = 2;
         gridBagConstraints7.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
         gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints4.gridy = 3; // Generated
         gridBagConstraints4.weightx = 1.0; // Generated
         gridBagConstraints4.gridwidth = 2; // Generated
         gridBagConstraints4.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
         gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints3.gridy = 2; // Generated
         gridBagConstraints3.weightx = 1.0; // Generated
         gridBagConstraints3.gridwidth = 2; // Generated
         gridBagConstraints3.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints.gridy = 0; // Generated
         gridBagConstraints.weightx = 1.0; // Generated
         gridBagConstraints.gridwidth = 2; // Generated
         gridBagConstraints.gridx = 0; // Generated
         jContentPane = new JPanel();
         jContentPane.setLayout(new GridBagLayout());
         jContentPane.setName("Nowy produkt"); // Generated
         jContentPane.add(getNameTextField(), gridBagConstraints); // Generated
         jContentPane.add(getJednostkaCombo(), gridBagConstraints3); // Generated
         jContentPane.add(getOpakowanieCombo(), gridBagConstraints4); // Generated
         jContentPane.add(getFakturaNoTextField(), gridBagConstraints7);
         jContentPane.add(getJPanel(), gridBagConstraints9);
         jContentPane.add(getJScrollPane(), gridBagConstraints111);
         jContentPane.add(getJPanel1(), gridBagConstraints14);
         jContentPane.add(getBledyList(), gridBagConstraints12);
         jContentPane.add(getJPanel3(), gridBagConstraints61);
         jContentPane.add(getJPanel4(), gridBagConstraints71);
      }
      return jContentPane;
   }

   /**
    * This method initializes nameTextField
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getNameTextField() {
      if (nameTextField == null) {
         nameTextField = new JTextField();
         nameTextField.setBorder(BorderFactory.createTitledBorder("Nazwa: "));
         nameTextField.setColumns(30); // Generated
      }
      return nameTextField;
   }

   /**
    * This method initializes sQuantityTextField
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getSQuantityTextField() {
      if (sQuantityTextField == null) {
         sQuantityTextField = new JTextField();
         sQuantityTextField.setBorder(BorderFactory
               .createTitledBorder("Iloœæ pocz¹tkowa: "));
         sQuantityTextField.setColumns(22); // Generated
      }
      return sQuantityTextField;
   }

   /**
    * This method initializes iloœæTeraz
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getIloœæTeraz() {
      if (iloœæTeraz == null) {
         iloœæTeraz = new JTextField();
         iloœæTeraz.setBorder(BorderFactory.createTitledBorder("Teraz w mag:"));
         iloœæTeraz.setColumns(8); // Generated
         iloœæTeraz.setEditable(false);
      }
      return iloœæTeraz;
   }

   /**
    * This method initializes jednostkaCombo
    * 
    * @return javax.swing.JComboBox
    */
   private JComboBox getJednostkaCombo() {
      if (jednostkaCombo == null) {
         jednostkaCombo = new JComboBox(getJednostkaModel());
         jednostkaCombo.setBorder(BorderFactory
               .createTitledBorder("Jednostka: "));
         jednostkaCombo.setEditable(true);
         jednostkaCombo.addActionListener(new UnitListener());
      }
      return jednostkaCombo;
   }

   /**
    * This method initializes opakowanieCombo
    * 
    * @return javax.swing.JComboBox
    */
   private JComboBox getOpakowanieCombo() {
      if (opakowanieCombo == null) {
         opakowanieCombo = new JComboBox(getOpakowanieModel());
         opakowanieCombo.setBorder(BorderFactory
               .createTitledBorder("Opakowanie: "));
         opakowanieCombo.setEditable(true);
         opakowanieCombo.addActionListener(new PackListener());
      }
      return opakowanieCombo;
   }

   /**
    * This method initializes cena
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getCena() {
      if (cena == null) {
         cena = new JTextField();
         cena.setBorder(BorderFactory.createTitledBorder("Cena: "));
         cena.setColumns(8); // Generated
         cena.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
               float cenaBezWat = Float.parseFloat(getCena().getText().replace(",", "."));
               float cenaZWat = cenaBezWat * (1+(Float) getVatBox().getSelectedItem()/100);
               getZVatTextFirld().setText(""+ cenaZWat);
               
            }});
      }
      return cena;
   }

   /**
    * This method initializes fakturaNoTextField
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getFakturaNoTextField() {
      if (fakturaNoTextField == null) {
         fakturaNoTextField = new JTextField();
         fakturaNoTextField.setBorder(BorderFactory
               .createTitledBorder("Numer faktury: "));
         fakturaNoTextField.setColumns(22); // Generated
      }
      return fakturaNoTextField;
   }

   /**
    * This method initializes jPanel
    * 
    * @return javax.swing.JPanel
    */
   private JPanel getJPanel() {
      if (jPanel == null) {
         GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
         gridBagConstraints11.gridx = 0; // Generated
         gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints11.gridwidth = 2; // Generated
         gridBagConstraints11.gridy = 6; // Generated
         GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
         gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
         gridBagConstraints8.gridy = 0; // Generated
         gridBagConstraints8.weightx = 1.0; // Generated
         gridBagConstraints8.gridx = 1; // Generated
         GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
         gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH; // Generated
         gridBagConstraints5.weightx = 1.0; // Generated
         jPanel = new JPanel();
         jPanel.setLayout(new GridBagLayout()); // Generated
         jPanel.add(getBookingDateTextField(), gridBagConstraints5); // Generated
         jPanel.add(getExpirtDateTextField(), gridBagConstraints8); // Generated
      }
      return jPanel;
   }

   /**
    * This method initializes bookingDateTextField
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getBookingDateTextField() {
      if (bookingDateTextField == null) {
         bookingDateTextField = new JTextField();
         bookingDateTextField.setBorder(BorderFactory
               .createTitledBorder("Data przyjœcia do mag.: "));
         bookingDateTextField.setColumns(15); // Generated
      }
      return bookingDateTextField;
   }

   /**
    * This method initializes expirtDateTextField
    * 
    * @return javax.swing.JTextField
    */
   private JTextField getExpirtDateTextField() {
      if (expirtDateTextField == null) {
         expirtDateTextField = new JTextField();
         expirtDateTextField.setBorder(BorderFactory
               .createTitledBorder("Data przydatnoœci do spo¿: "));
         expirtDateTextField.setColumns(15); // Generated
      }
      return expirtDateTextField;
   }

   /**
    * This method initializes jScrollPane
    * 
    * @return javax.swing.JScrollPane
    */
   private JScrollPane getJScrollPane() {
      if (jScrollPane == null) {
         jScrollPane = new JScrollPane();
         jScrollPane.setViewportView(getOpisTextPane()); // Generate
      }
      return jScrollPane;
   }

   /**
    * This method initializes jTextPane
    * 
    * @return javax.swing.JTextPane
    */
   private JTextPane getOpisTextPane() {
      if (opisTextPane == null) {
         opisTextPane = new JTextPane();
         opisTextPane.setBorder(BorderFactory.createTitledBorder("Opis: "));
         opisTextPane.setPreferredSize(getOK().getPreferredSize());
      }
      return opisTextPane;
   }

   /**
    * This method initializes jPanel1
    * 
    * @return javax.swing.JPanel
    */
   private JPanel getJPanel1() {
      if (jPanel1 == null) {
         jPanel1 = new JPanel();
         jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS)); // Generated
         jPanel1.add(getOK(), null); // Generated
         jPanel1.add(getAnuluj(), null); // Generated
         jPanel1.add(Box.createGlue());
         jPanel1.add(getUtwozSzablon(), null); // Generated
      }
      return jPanel1;
   }

   /**
    * This method initializes OK
    * 
    * @return javax.swing.JButton
    */
   private JButton getOK() {
      if (OK == null) {
         OK = new JButton();
         OK.setText("OK"); // Generated
         OK.setToolTipText("Dodaj produkt do magazynu"); // Generated
         OK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
               resultProduct = flav.getProduct();
               getListModel().removeAllElements();
               for (String s : flav.getErrors())
                  getListModel().addElement(s);
               result = USER_CLICKED_OK;
               pack();
               if (resultProduct != null)
                  setVisible(false);

            }

         });
      }
      return OK;
   }

   /**
    * This method initializes anuluj
    * 
    * @return javax.swing.JButton
    */
   private JButton getAnuluj() {
      if (anuluj == null) {
         anuluj = new JButton();
         anuluj.setText("Anuluj"); // Generated
         anuluj.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
               result = USER_CLICKED_ABORD;
               setVisible((false));
            }

         });
      }
      return anuluj;
   }

   private interface Flavour {
      /* Zmiany dotycz¹ce stanu ramki wykonaæ w konstruktoerze */
      Product getProduct();

      Vector<String> getErrors();

      void dispose();
   }

   public interface template {
      /**
       * 
       * @param i
       *           will be between 1 and 9
       * @return field value
       */
      String get(int i);
   }

   public static class templateI extends Vector<String> implements template {

      public templateI(Collection<? extends String> arg0) {
         super(arg0);
      }

      @Override
      public synchronized String get(int i) {

         return super.get(i).replace("dzis",
               StaticContent.sc.getToday().toString());
      }

   }

   private class noProductFlavour implements Flavour {

      Vector<String> errors = new Vector<String>();

      public noProductFlavour() {
         super();
         getNameTextField().setText("");
         getSQuantityTextField().setText("");
         getIloœæTeraz().setVisible(false);
         getJednostkaModel().removeAllElements();
         getOpakowanieModel().removeAllElements();
         for (String u : AbstractProduct.getUnitMap().getTwoOne().keySet()) {
            getJednostkaModel().addElement(u);
         }
         for (String u : AbstractProduct.getPackagingMap().getTwoOne().keySet()) {
            getOpakowanieModel().addElement(u);
         }
         getCena().setText("");
         //getFakturaNoTextField().setText("");
         //getBookingDateTextField().setText("");
         getExpirtDateTextField().setText("");
         getOpisTextPane().setText("");
         getListModel().removeAllElements();

      }

      public Product getProduct() {
         errors.clear();
         Product p = new SimpleProduct();
         try {
            if (getNameTextField().getText().trim().length() != 0) {
               p.setName(getNameTextField().getText().trim());
            } else {
               errors.add("Pole nazwa nie mo¿e byæ puste");
            }
         } catch (Exception e) {
            e.printStackTrace();
            errors.add("Wyjatek przy ustawianiu nazwy: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            if (getSQuantityTextField().getText().replace(",",".").trim().length() != 0) {
               p.setSQuantity(Float.parseFloat(getSQuantityTextField()
                     .getText().replace(",",".").trim()));
            } else {
               errors.add("Pole iloœc pocz¹tkowa nie mo¿e byæ puste");
            }
         } catch (Exception e) {
            e.printStackTrace();
            errors.add("Wyjatek przy ustawianiu iloœci pocz¹tkowej: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            p.setUnitId(AbstractProduct.getUnitMap()
                  .getOne((String) getJednostkaCombo().getSelectedItem()));

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu jednostki: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            p.setPackagingId(AbstractProduct.getPackagingMap()
                  .getOne((String) getOpakowanieCombo().getSelectedItem()));

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu iloœci pocz¹tkowej: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            //float vat = (Float) vatBox.getSelectedItem();
               p.setPrice(Float.parseFloat(getZVatTextFirld().getText().trim()));
      
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu ceny: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            if (getFakturaNoTextField().getText().trim().length() != 0) {
               p.setFacturaNo(getFakturaNoTextField().getText().trim());
            } else {
               errors.add("Pole numer faktury nie mo¿e byæ puste");
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu numer faktury: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            if (getBookingDateTextField().getText().replace(",",".").trim().length() != 0) {
               p.setDateOfBooking(new MyDate(getBookingDateTextField().getText().replace(",",".")));
            } else {
               errors.add("Pole data ksiêgowania nie mo¿e byæ puste");
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu data ksiêgowania: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            if (getExpirtDateTextField().getText().trim().length() != 0) {
               p.setExpiryDate(new MyDate(getExpirtDateTextField().getText().replace(",",".")));
            } else {
               errors.add("Pole data wa¿noœci nie mo¿e byæ puste");
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu data wa¿noœci: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {

            p.setDescription(getOpisTextPane().getText().trim());

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu opis: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         if (errors.size() == 0)
            return p;

         return null;
      }

      public void dispose() {
         getIloœæTeraz().setVisible(true);
      }

      public Vector<String> getErrors() {
         return errors;
      }

   }

   private class ProductFlavour extends noProductFlavour implements Flavour {
      Product p;

      ActionListener lst = new ActionListener() {

         public void actionPerformed(ActionEvent arg0) {
            try {
               getIloœæTeraz().setText(
                     String.valueOf(p.getCurrQ()
                                 - p.getSQuantity()
                                 + Float.parseFloat(getSQuantityTextField()
                                       .getText().replace(",","."))));
            } catch (Exception e) {
               getIloœæTeraz().setText("");
            }

         }

      };

      public ProductFlavour(Product p) {
         super();
         super.dispose();
         this.p = p;

         getSQuantityTextField().addActionListener(lst);

         try {
            getNameTextField().setText(p.getName());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu nazwy: "
                  + e.getClass().getName() + ", b³¹d: " + e.getMessage());
         }

         try {
            getSQuantityTextField().setText("" + p.getSQuantity());
            getIloœæTeraz().setText("" + p.getCurrQ());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu iloœci pocz¹tkowej: "
                  + e.getClass().getName() + ", b³¹d:" + e.getClass().getName()
                  + ", b³¹d:" + e.getMessage());
         }

         try {
            getJednostkaCombo().setSelectedItem(p.getUnit());

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu jednostki: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            getOpakowanieCombo().setSelectedItem(p.getPackaging());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu iloœci pocz¹tkowej: "
                  + e.getClass().getName() + ", b³¹d:" + e.getClass().getName()
                  + ", b³¹d:" + e.getMessage());
         }

         try {
            getZVatTextFirld().setText("" + p.getPrice());

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu ceny: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            getFakturaNoTextField().setText(p.getFacturaNo());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu numer faktury: "
                  + e.getClass().getName() + ", b³¹d:" + e.getClass().getName()
                  + ", b³¹d:" + e.getMessage());
         }

         try {
            getBookingDateTextField().setText(p.getDateOfBooking().toString());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu data ksiêgowania: "
                  + e.getClass().getName() + ", b³¹d:" + e.getClass().getName()
                  + ", b³¹d:" + e.getMessage());
         }

         try {
            getExpirtDateTextField().setText(p.getExpiryDate().toString());
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu data wa¿noœci: "
                  + e.getClass().getName() + ", b³¹d:" + e.getClass().getName()
                  + ", b³¹d:" + e.getMessage());
         }

         try {

            getOpisTextPane().setText(p.getDescription());

         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu opis: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

      }

      @Override
      public void dispose() {
         getSQuantityTextField().removeActionListener(lst);
      }

      public Product getProduct() {
         errors.clear();

         try {
            try {
               if (getNameTextField().getText().trim().length() != 0) {
                  p.changeName(getNameTextField().getText());
               } else {
                  errors.add("Pole nazwa nie mo¿e byæ puste");
               }
            } catch (ZZIllegalNotInitializedEx e) {
               p.setName(getNameTextField().getText());
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu nazwy: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {
               if (getSQuantityTextField().getText().trim().length() != 0) {
                  p.changeSQuantity(Float.parseFloat(getSQuantityTextField()
                        .getText().replace(",",".")));
               } else {
                  errors.add("Pole iloœæ pocz¹tkowa nie mo¿e byæ puste");
               }
            } catch (ZZIllegalNotInitializedEx e) {
               p.setSQuantity(Float.parseFloat(getNameTextField().getText()));
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu iloœci pocz¹tkowej: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {

               p.changeUnitId(AbstractProduct.getUnitMap()
                     .getOne((String) getJednostkaCombo().getSelectedItem()));

            } catch (ZZIllegalNotInitializedEx e) {
               System.out.println((String) getJednostkaCombo().getSelectedItem());
               p.setUnitId(AbstractProduct.getUnitMap()
                     .getOne((String) getJednostkaCombo().getSelectedItem()));
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu Jednostki: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {
               System.out.println( getOpakowanieCombo(). getSelectedItem());
               AbstractProduct.getUnitMap();
               p.changePackagingId(AbstractProduct.getPackagingMap().getOne((String)getOpakowanieCombo().getSelectedItem()));

            } catch (ZZIllegalNotInitializedEx e) {
               p.setPackagingId(AbstractProduct.getUnitMap()
                     .getOne((String) getOpakowanieCombo().getSelectedItem()));
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu opakowania: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
            e.printStackTrace();
         }

         try {
            try {
                  p.changePrice(Float.parseFloat(getZVatTextFirld().getText().trim()));

            } catch (ZZIllegalNotInitializedEx e) {
               p.setPrice(Float.parseFloat(getZVatTextFirld().getText().trim()));
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu ceny: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {
               p.changeFacturaNo(getFakturaNoTextField().getText().trim());
            } catch (ZZIllegalNotInitializedEx e) {
               p.setFacturaNo(getFakturaNoTextField().getText().trim());
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu numeru faktury: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {
               if (getBookingDateTextField().getText().replace(",",".").trim().length() != 0) {
                  p.changeDateOfBooking(new MyDate(getBookingDateTextField()
                        .getText()));
               } else {
                  errors.add("Pole data ksiêgowania nie mo¿e byæ puste");
               }
            } catch (ZZIllegalNotInitializedEx e) {
               p.setDateOfBooking(new MyDate(getBookingDateTextField().getText().replace(",",".")));
            }
         } catch (Exception e) {
            e.printStackTrace();
            errors.add("Wyjatek przy ustawianiu daty ksiêgowania: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {
               if (getExpirtDateTextField().getText().trim().length() != 0) {
                  p.changeExpiryDate(new MyDate(getExpirtDateTextField()
                        .getText().replace(",",".")));
               } else {
                  errors
                        .add("Pole data przudatkoœci do spo¿ nie mo¿e byæ puste");
               }
            } catch (ZZIllegalNotInitializedEx e) {
               p.setExpiryDate(new MyDate(getExpirtDateTextField().getText().replace(",",".")));
            }
         } catch (Exception e) {
            e.printStackTrace();
            errors.add("Wyjatek przy ustawianiu daty przydatnoœci: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         try {
            try {

               p.changeDescription(getOpisTextPane().getText().trim());

            } catch (ZZIllegalNotInitializedEx e) {
               p.setDescription(getOpisTextPane().getText().trim());
            }
         } catch (Exception e) {
            errors.add("Wyjatek przy ustawianiu opisu: "
                  + e.getClass().getName() + ", b³¹d:" + e.getMessage());
         }

         if (errors.size() == 0)
            return p;

         return null;
      }

      public Vector<String> getErrors() {
         return errors;
      }

   }

   private class templateFlav extends noProductFlavour implements Flavour {

      public templateFlav(template t) {
         super();
         getNameTextField().setText(t.get(1).trim());
         getSQuantityTextField().setText(t.get(2).trim());
         getIloœæTeraz().setVisible(false);
         getJednostkaModel().removeAllElements();
         for (String u : AbstractProduct.getUnitMap().getTwoOne().keySet()) {
            getJednostkaModel().addElement(u);
         }
         for (String u : AbstractProduct.getPackagingMap().getTwoOne().keySet()) {
            getOpakowanieModel().addElement(u);
         }
         getJednostkaCombo().setSelectedItem(t.get(3).trim());
         getOpakowanieCombo().setSelectedItem(t.get(4).trim());
         getCena().setText(t.get(5).trim());
         getFakturaNoTextField().setText(t.get(6).trim());
         getBookingDateTextField().setText(t.get(7).trim());
         getExpirtDateTextField().setText(t.get(8).trim());
         getOpisTextPane().setText(t.get(9).trim());
      }
   }

   private DefaultComboBoxModel getJednostkaModel() {
      if (jednostkaModel == null)
         jednostkaModel = new DefaultComboBoxModel();
      return jednostkaModel;
   }

   private DefaultComboBoxModel getOpakowanieModel() {
      if (opakowanieModel == null)
         opakowanieModel = new DefaultComboBoxModel();
      return opakowanieModel;
   }

   /**
    * This method initializes bledyList	
    * 	
    * @return javax.swing.JList	
    */
   private JList getBledyList() {
      if (bledyList == null) {
         bledyList = new JList(getListModel());
         bledyList.setBorder(BorderFactory.createTitledBorder("B³êdy: "));
      }
      return bledyList;
   }

   private DefaultListModel getListModel() {
      if (listModel == null)
         listModel = new DefaultListModel();
      return listModel;
   }

   /**
    * This method initializes utwozSzablon	
    * 	
    * @return javax.swing.JButton	
    */
   private JButton getUtwozSzablon() {
      if (utwozSzablon == null) {
         utwozSzablon = new JButton();
         utwozSzablon.setText("utwó¿ szablon"); // Generated
         utwozSzablon
               .setToolTipText("Utwo¿y szablon z zawartosci tego diialoga - potem bedzie mo¿na two¿yæ produkty wedel tego szablonu"); // Generated
         utwozSzablon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
               Vector<String> result = new Vector();

               String nazwa = (String) JOptionPane.showInputDialog(
                     StaticContent.sc.getUppmostFrame(),
                     "Podaj nazwê dla szablonu", "Pytanie",
                     JOptionPane.QUESTION_MESSAGE, null, null, null);
               if (nazwa.trim().length() == 0) {
                  JOptionPane
                        .showMessageDialog(
                              StaticContent.sc.getUppmostFrame(),
                              "Nazwa powinna mieæ przynajmniej jeden znak d³ugoœci - no less!!",
                              "Ostrze¿enie", JOptionPane.WARNING_MESSAGE, null);
                  return;
               }
               ;

               result.add(nazwa); //Liczoym 1 ;
               result.add(getNameTextField().getText());
               result.add(getSQuantityTextField().getText().replace(",","."));
               result.add((String) getJednostkaCombo().getSelectedItem());
               result.add((String) getOpakowanieCombo().getSelectedItem());
               result.add(getCena().getText());
               result.add(getFakturaNoTextField().getText());
               result.add(getBookingDateTextField().getText().replace(",","."));
               result.add(getExpirtDateTextField().getText().replace(",","."));
               result.add(getOpisTextPane().getText());

               StaticContent.sc.getTemplates().add(result);
               StaticContent.sc.fireTemplateChanged();
            }

         });
      }
      return utwozSzablon;
   }

   private abstract class ComboListensr implements ActionListener {
      protected abstract boolean add(String s);

      public void actionPerformed(ActionEvent arg0) {
         JComboBox box = (JComboBox) arg0.getSource();
         DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
         String s = (String) box.getSelectedItem();
         if (s == null)
            return;
         if (add(s)) {
            model.addElement(box.getSelectedItem());
         }
      }
   }

   private class UnitListener extends ComboListensr {

      @Override
      protected boolean add(String s) {
         if (AbstractProduct.getUnitMap().containsTwo(s.trim()))
            return false;
         else {
            AbstractProduct.getUnitMap()
                  .put(AbstractProduct.getUnitMap().maxOne() + 1, s);
            return true;
         }
      }
   }

   private class PackListener extends ComboListensr {

      @Override
      protected boolean add(String s) {
         if (AbstractProduct.getPackagingMap().containsTwo(s.trim()))
            return false;
         else {
            AbstractProduct.getPackagingMap().put(AbstractProduct.getPackagingMap()
                  .maxOne() + 1, s);
            return true;
         }
      }

   }

   /**
    * This method initializes vatBox	
    * 	
    * @return javax.swing.JComboBox	
    */
   private JComboBox getVatBox() {
      if (vatBox == null) {
         vatBox = new JComboBox();
        
         DefaultComboBoxModel model = (DefaultComboBoxModel) vatBox.getModel();
         Float seven = new Float  (7);
         model.addElement(new Float(0));
         model.addElement(new Float(3));
         model.addElement(seven);
         model.addElement(new Float(22));
         model.setSelectedItem(seven);
         vatBox.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
               float cenaBezWat = Float.parseFloat(getCena().getText().replace(",", "."));
               float cenaZWat = cenaBezWat * (1+(Float) vatBox.getSelectedItem()/100);
               getZVatTextFirld().setText(""+ cenaZWat);
               
            }});
      }
      return vatBox;
   }

   /**
    * This method initializes jPanel2	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getJPanel2() {
      if (jPanel2 == null) {
         GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
         gridBagConstraints13.gridx = 1;
         gridBagConstraints13.gridy = 0;
         procent = new JLabel();
         procent.setText("%");
         GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
         gridBagConstraints10.gridx = 0;
         gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints10.weightx = 1.0;
         gridBagConstraints10.gridy = 0;
         jPanel2 = new JPanel();
         jPanel2.setLayout(new GridBagLayout());
         jPanel2.add(getVatBox(), gridBagConstraints10);
         jPanel2.add(procent, gridBagConstraints13);
         vatBox.setBorder(BorderFactory.createTitledBorder("Podaj stawkê vat"));
      }
      return jPanel2;
   }

   /**
    * This method initializes jPanel3	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getJPanel3() {
      if (jPanel3 == null) {
         GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
         gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints2.gridy = 0;
         gridBagConstraints2.weightx = 0.4;
         gridBagConstraints2.gridx = 1;
         GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
         gridBagConstraints1.gridx = 0;
         gridBagConstraints1.weightx = 1.0;
         gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints1.gridy = 0;
         jPanel3 = new JPanel();
         jPanel3.setLayout(new GridBagLayout());
         jPanel3.add(getSQuantityTextField(), gridBagConstraints1);
         jPanel3.add(getIloœæTeraz(), gridBagConstraints2);
      }
      return jPanel3;
   }

   /**
    * This method initializes jPanel4	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getJPanel4() {
      if (jPanel4 == null) {
         GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
         gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints16.gridy = 0;
         gridBagConstraints16.weightx = 0.4;
         gridBagConstraints16.gridx = 2;
         GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
         gridBagConstraints15.gridx = 1;
         gridBagConstraints15.weightx = 10.0;
         gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints15.gridy = 0;
         GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
         gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
         gridBagConstraints6.gridy = 0;
         gridBagConstraints6.weightx = 0.4;
         gridBagConstraints6.gridx = 0;
         jPanel4 = new JPanel();
         jPanel4.setLayout(new GridBagLayout());
         jPanel4.add(getCena(), gridBagConstraints6);
         jPanel4.add(getJPanel2(), gridBagConstraints15);
         jPanel4.add(getZVatTextFirld(), gridBagConstraints16);
      }
      return jPanel4;
   }

   /**
    * This method initializes zVatTextFirld	
    * 	
    * @return javax.swing.JTextField	
    */
   private JTextField getZVatTextFirld() {
      if (zVatTextFirld == null) {
         zVatTextFirld = new JTextField();
         zVatTextFirld.setColumns(8);
         zVatTextFirld.setBorder(BorderFactory.createTitledBorder("Z vat:"));
      }
      return zVatTextFirld;
   }

}
