package zzGUIElements;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.sun.corba.se.impl.logging.UtilSystemException;
import com.sun.media.sound.JDK13Services;

import zzDataBase.Day;
import zzDataBase.DirLoadSaver;
import zzDataBase.Expenditure;
import zzDataBase.LoadSaver;
import zzDataBase.StaticContent;
import zzEx.Exit;
import zzEx.ZZIllegalEx;
import zzEx.ZZIllegalNotEnoughPrivelagesEx;
import zzEx.ZZInternalDuplicateEntryEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoBadFormatEx;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzGUIElements.MealElements.MealGeneatingDialog;
import zzGUIElements.PopupMenu.Day.ChoosProdsInDialogv2;
import zzGUIElements.zzGuiProductElements.DateChooserTableModel;
import zzGUIElements.zzGuiProductElements.FilterPanelLong;
import zzGUIElements.zzGuiProductElements.ProductGeneratingDialogv3;
import zzGUIElements.zzGuiProductElements.ProductTable;
import zzMyDate.MyDate;
import zzOrdering.OrderByName;
import zzOrdering.OrderByPrice;
import zzProduct.AbstractProduct;
import zzProduct.Product;
import zzProduct.SimpleProduct;

public class MainFrame extends JFrame {
   
   private MealGeneatingDialog dialog;
   
   private static File rootFile;
   static {
      if (!getRootFile().exists())
         getRootFile().mkdirs();
   }
   
   
   private JFileChooser fileChooser;
   
   private JFileChooser dirChooser;
   
   private ProductGeneratingDialogv3 productGeneratingDialog;
   
   private JPanel jContentPane = null;
   
   private JSplitPane mainSplitPane = null;
   
   private FilterPanelLong Filters = null;
   
   private JSplitPane secondSplitPane = null;
   
   private JSplitPane lowestSplitPane = null;
   
   private JScrollPane TextScroll = null;
   
   private JTextPane text = null;
   
   private ProductTable productTable = null;
   
   private JPanel fooPanel = null;
   
   private JMenuBar jJMenuBar = null;
   
   private JMenu Baza = null;
   
   private JMenuItem nowa = null;
   
   private JMenuItem zapiszDomyslne = null;
   
   private JMenuItem zapisz = null;
   
   private JMenuItem odczytajOstatnie = null;
   
   private JMenuItem odczytaj = null;
   
   private JMenuItem wyjdz = null;
   
   private JMenu Produkt = null;
   
   private JMenuItem nowyProd = null;
   
   private JMenuItem wyszukaj = null;
   
   private JMenu Posilek = null;
   
   private JMenuItem nowyPosilek = null;
   
   private JMenu advanced = null;
   
   private JMenuItem zmienDzis = null;
   
   private JPanel treePanel = null;
   
   private JButton resetTree = null;
   
   private JScrollPane treeScrollPane = null;
   
   private JTree DaysTree = null;
   
   private DefaultTreeModel model = null;
   
   private JMenu addFromTemplate = null;
   
   private JMenuItem imporTZExela = null;
   
   private JMenuItem ZapiszStanMagazynu = null;
   
   private JMenuItem OdczytajZkat = null;
   
   private JMenuItem zapiszStanyMagazynu = null;
   
   private JMenu inne = null;
   
   private JMenuItem genKartoteki= null;
   
   //private JMenuItem genZZ = null;
   
   /**
    * This is the default constructor
    */
   public MainFrame() {
      super();
      initialize();
   }
   
   /**
    * This method initializes this
    * 
    * @return void
    */
   private void initialize() {
      StaticContent.sc.setUppmostFrame(this);
      this.setSize(300, 200);
      this.setJMenuBar(getJJMenuBar()); // Generated
      this.setTitle("Magazyn");
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setContentPane(getJContentPane());
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.addWindowListener(new WindowListener() {
         
         public void windowOpened(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void windowClosing(WindowEvent arg0) {
            if( JOptionPane.YES_OPTION ==JOptionPane.showConfirmDialog(MainFrame.this,
                  "Czy chcesz wyjœæ nie zapisawszy bazy danych",
                  "Pytanie",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE,
                  null
            )){
               zapiszDomyslne(MainFrame.this);
               System.exit(0);
            }
            
            
         }
         
         public void windowClosed(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void windowIconified(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void windowDeiconified(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void windowActivated(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         public void windowDeactivated(WindowEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
      });
   }
   
   /**
    * This method initializes jContentPane
    * 
    * @return javax.swing.JPanel
    */
   private JPanel getJContentPane() {
      if (jContentPane == null) {
         GridLayout gridLayout = new GridLayout();
         gridLayout.setRows(1); // Generated
         jContentPane = new JPanel();
         jContentPane.setLayout(gridLayout); // Generated
         jContentPane.add(getMainSplitPane(), null); // Generated
         
         lowestSplitPane.setDividerLocation(0.95d);
         lowestSplitPane.setResizeWeight(0.95d);
         secondSplitPane.setDividerLocation(0.9d);
         secondSplitPane.setResizeWeight(0.9d);
         mainSplitPane.setDividerLocation((double) 0.1d);
         mainSplitPane.setResizeWeight(0.1d);
      }
      return jContentPane;
   }
   
   /**
    * This method initializes jSplitPane	
    * 	
    * @return javax.swing.JSplitPane	
    */
   private JSplitPane getMainSplitPane() {
      if (mainSplitPane == null) {
         mainSplitPane = new JSplitPane();
         mainSplitPane.setDividerLocation((double) 0.1d);
         mainSplitPane.setResizeWeight(0.1d);
         mainSplitPane.setOneTouchExpandable(true);
         mainSplitPane.setRightComponent(getSecondSplitPane()); // Generated
         mainSplitPane.setLeftComponent(getFilters()); // Generated
      }
      return mainSplitPane;
   }
   
   /**
    * This method initializes Filters	
    * 	
    * @return zzGUIElements.zzGuiProductElements.FilterPanelLong	
    */
   private FilterPanelLong getFilters() {
      if (Filters == null) {
         Filters = new FilterPanelLong(this, getProductTable().getModel());
      }
      return Filters; 
   }
   
   /**
    * This method initializes jSplitPane	
    * 	
    * @return javax.swing.JSplitPane	
    */
   private JSplitPane getSecondSplitPane() {
      if (secondSplitPane == null) {
         secondSplitPane = new JSplitPane();
         secondSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT); // Generated
         secondSplitPane.setOneTouchExpandable(true); // Generated
         secondSplitPane.setBottomComponent(getTextScroll()); // Generated			
         secondSplitPane.setTopComponent(getLowestSplitPane()); // Generated
         secondSplitPane.setDividerLocation(0.9d);
         secondSplitPane.setResizeWeight(0.9d);
      }
      return secondSplitPane;
   }
   
   /**
    * This method initializes jSplitPane	
    * 	
    * @return javax.swing.JSplitPane	
    */
   private JSplitPane getLowestSplitPane() {
      if (lowestSplitPane == null) {
         lowestSplitPane = new JSplitPane();
         lowestSplitPane.setLeftComponent(getProductTable()); // Generated
         lowestSplitPane.setRightComponent(getFooPanel()); // Generated
         lowestSplitPane.setOneTouchExpandable(true);
         lowestSplitPane.setDividerLocation(0.1);
         lowestSplitPane.setResizeWeight(0.1d);
      }
      return lowestSplitPane;
   }
   
   /**
    * This method initializes TestScroll	
    * 	
    * @return javax.swing.JScrollPane	
    */
   private JScrollPane getTextScroll() {
      if (TextScroll == null) {
         TextScroll = new JScrollPane();
         TextScroll
         .setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Generated
         TextScroll.setViewportView(getText()); // Generated
      }
      return TextScroll;
   }
   
   /**
    * This method initializes Test	
    * 	
    * @return javax.swing.JTextPane	
    */
   private JTextPane getText() {
      if (text == null) {
         text = new JTextPane();
      }
      return text;
   }
   
   /**
    * This method initializes productTable	
    * 	
    * @return zzGUIElements.zzGuiProductElements.ProductTable	
    */
   public ProductTable getProductTable() {
      if (productTable == null) {
         productTable = new ProductTable(StaticContent.sc.getDataBase(), this);
      }
      return productTable;
   }
   
   /**
    * This method initializes fooPanel	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getFooPanel() {
      if (fooPanel == null) {
         GridLayout gridLayout1 = new GridLayout();
         gridLayout1.setRows(1); // Generated
         fooPanel = new JPanel();
         fooPanel.setLayout(gridLayout1); // Generated
         fooPanel.add(getTreePanel(), null); // Generated
      }
      return fooPanel;
   }
   
   /**
    * This method initializes jJMenuBar	
    * 	
    * @return javax.swing.JMenuBar	
    */
   private JMenuBar getJJMenuBar() {
      if (jJMenuBar == null) {
         jJMenuBar = new JMenuBar();
         jJMenuBar.add(getBaza()); // Generated
         jJMenuBar.add(getProdukt()); // Generated
         jJMenuBar.add(getPosilek()); // Generated
         jJMenuBar.add(getAdvanced()); // Generated
      }
      return jJMenuBar;
   }
   
   /**
    * This method initializes Baza	
    * 	
    * @return javax.swing.JMenu	
    */
   private JMenu getBaza() {
      if (Baza == null) {
         Baza = new JMenu();
         Baza.setText("Baza"); // Generated
         Baza.add(getNowa()); // Generated
         Baza.add(getZapiszDomyslne()); // Generated
         Baza.add(getInne());
         Baza.add(getOdczytajOstatnie()); // Generated
         Baza.add(getWyjdz()); // Generated
         
      }
      return Baza;
   }
   
   private JMenu getInne(){
      if(inne == null){
         inne= new JMenu();
         inne.setText("Inne opcje");
         inne.add(getImporTZExela());
         inne.add(getOdczytaj());
         inne.add(getZapisz());
         //inne.add(getZapiszStanMagazynu());
         inne.add(getZapiszStanyMagazynu());
         inne.add(getGenKartoteki());
         //inne.add(getGenZZ());
         //inne.add(getOdczytajZkat());
      }
      return inne;
   }
   
   /**
    * This method initializes nowa	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getNowa() {
      if (nowa == null) {
         nowa = new JMenuItem();
         nowa.setName("Nowa baza danych"); // Generated
         nowa.setText("Nowa"); // Generated
         nowa.setToolTipText("Spowoduje zamkniêcie bierz¹cej bazy danych stwozenie nowej"); // Generated
         nowa.addActionListener(new performDadabaseDestroyingAction() {
            
            @Override
            protected int performAction() {
               StaticContent.sc.reset();
               getProductTable().fireDataChanged();
               return 0;
            }
            
         });
         
      }
      return nowa;
   }
   
   /**
    * This method initializes ZapiszDomyslne
    * 
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getZapiszDomyslne() {
      if (zapiszDomyslne == null) {
         zapiszDomyslne = new JMenuItem();
         zapiszDomyslne.setName("Zapisz do domyslnego pliku"); // Generated
         zapiszDomyslne.setText("Zapisz"); // Generated
         zapiszDomyslne
         .setToolTipText("Domyslny plik ma nazwê HH-MM-SS-DD-MM-YY.zip"); // Generated
         zapiszDomyslne.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
               int result = zapiszDomyslne(MainFrame.this);
               if (result == 0) {
                  JOptionPane.showMessageDialog(MainFrame.this,
                        "Zapis zakonczony powodzeniem", "Sukces",
                        JOptionPane.INFORMATION_MESSAGE, null);
               }
               
            }
            
         });
      }
      return zapiszDomyslne;
   }
   
   /**
    * This method initializes Zapisz
    * 
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getZapisz() {
      if (zapisz == null) {
         zapisz = new JMenuItem();
         zapisz.setToolTipText("Program pozwoli wybrac nazwe pliku"); // Generated
         zapisz.setText("Zapisz do..."); // Generated
         zapisz.setName("Zapisz"); // Generated
         zapisz.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
               int result = getFileChooser().showSaveDialog(MainFrame.this);
               if (result == JFileChooser.APPROVE_OPTION) {
                  try {
                     LoadSaver.save(StaticContent.sc, fileChooser
                           .getSelectedFile());
                     return;
                  } catch (IOException e) {
                     JOptionPane.showMessageDialog(MainFrame.this,
                           "Nie odczytano!\b b³¹d odczytu" + e.getMessage()
                           + "\n" + e.getClass().getName(), "b³¹d",
                           JOptionPane.WARNING_MESSAGE, null);
                     e.printStackTrace();
                  }
               } else {
                  JOptionPane.showMessageDialog(MainFrame.this,
                        "Anulowa³eœ b¹dŸ wyszed³eœ\n nie dokonano zmian",
                        "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
               }
               
            }
            
         });
      }
      return zapisz;
   }
   
   private int zapiszDomyslne(Component cmp) {
      if (!getRootFile().exists())
         getRootFile().mkdir();
      System.out.println("katalog?" + getRootFile().isDirectory());
      DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, new Locale(
            "pol", "PL"));
      String nazwa = df.format(new Date());
      nazwa = nazwa.replace('.', '-');
      String nazwaTemp = new String(nazwa);
      File newFile;
      int i = 0;
      do {
         newFile = new File(getRootFile(), nazwaTemp + ".zip");
         nazwaTemp = nazwa + "_" + i++;
      } while (newFile.exists());
      try {
         LoadSaver.save(StaticContent.sc, newFile);
         return 0;
      } catch (IOException e) {
         JOptionPane.showMessageDialog(cmp, "B³¹d!!n"
               + "Pradopodobnie nie uda³o sie zapisaæ pliku" + "Detale b³êdu:"
               + e.getLocalizedMessage(), "B³¹d!", JOptionPane.WARNING_MESSAGE,
               null);
         e.printStackTrace();
      }
      return 1;
   }
   
   /**
    * This method initializes odczytajOstatnie
    * 
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getOdczytajOstatnie() {
      if (odczytajOstatnie == null) {
         odczytajOstatnie = new JMenuItem();
         odczytajOstatnie
         .setToolTipText("Odczyta ostatnio zapisany plik w domyslnym katalogu"); // Generated
         odczytajOstatnie.setText("Odczytaj ostatni"); // Generated
         odczytajOstatnie.setName("Odczytaj domyslne"); // Generated
         odczytajOstatnie
         .addActionListener(new performDadabaseDestroyingAction() {
            
            @Override
            protected int performAction() {
               return odczytajOstatnie(MainFrame.this);
            }
            
         });
      }
      return odczytajOstatnie;
   }
   
   private int odczytajOstatnie(Component cmp) {
      Comparator<File> compare = new Comparator<File>() {
         public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
               return 1;
            }
            if (arg0.lastModified() < arg1.lastModified()) {
               return -1;
            }
            return 0;
         }
      };
      File[] files = getRootFile().listFiles();
      Arrays.sort(files, compare);
      File lastModified = (files[0].lastModified() > files[files.length - 1]
                                                           .lastModified()) ? files[0] : files[files.length - 1];
                                                           
                                                           System.out.println(lastModified.getName());
                                                           
                                                           String message;
                                                           try {
                                                              LoadSaver.load(StaticContent.sc, lastModified);
                                                              return 0;
                                                           } catch (ZZInternalDuplicateEntryEx e) {
                                                              message = e.getMessage();
                                                              e.printStackTrace();
                                                           } catch (ZZIoBadFormatEx e) {
                                                              message = e.getMessage();
                                                              e.printStackTrace();
                                                           } catch (ZZIllegalNotEnoughPrivelagesEx e) {
                                                              message = e.getMessage();
                                                              e.printStackTrace();
                                                           } catch (ZZIllegalEx e) {
                                                              message = e.getMessage();
                                                              e.printStackTrace();
                                                           } catch (IOException e) {
                                                              message = e.getMessage();
                                                              e.printStackTrace();
                                                           }
                                                           
                                                           JOptionPane.showMessageDialog(cmp, message, "B³¹d",
                                                                 JOptionPane.WARNING_MESSAGE, null);
                                                           return 1;
   }
   
   /**
    * This method initializes odczytaj	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getOdczytaj() {
      if (odczytaj == null) {
         odczytaj = new JMenuItem();
         odczytaj.setName("Odczytaj"); // Generated
         odczytaj.setText("Odczytaj z..."); // Generated
         odczytaj.setToolTipText("Odczytaj z dowolnie wybranego pliku"); // Generated
         odczytaj.addActionListener(new performDadabaseDestroyingAction() {
            
            @SuppressWarnings("finally")
            @Override
            protected int performAction() {
               int result = getFileChooser().showOpenDialog(MainFrame.this);
               if (result == JFileChooser.APPROVE_OPTION) {
                  try {
                     LoadSaver.load(StaticContent.sc, fileChooser
                           .getSelectedFile());
                     return 0;
                  } catch (Exception e) {
                     e.printStackTrace();
                     JOptionPane.showMessageDialog(MainFrame.this,
                           "Nie odczytano! \nb³¹d odczytu: " + e.getMessage()
                           +"debug: " + e.getClass().getName(),
                           "b³¹d", JOptionPane.WARNING_MESSAGE, null);
                     
                  }
               } else {
                  JOptionPane.showMessageDialog(MainFrame.this,
                        "Anulowa³eœ b¹dŸ wyszed³eœ\n nie dokonano zmian",
                        "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
               }
               return 0;
            }
            
         });
      }
      return odczytaj;
   }
   
   private void odczytaj(Component cmp) {
      int result = fileChooser.showOpenDialog(cmp);
      if (result == JFileChooser.APPROVE_OPTION) {
         File file = fileChooser.getSelectedFile();
         try {
            LoadSaver.load(StaticContent.sc, file);
         } catch (Exception e) {
            JOptionPane.showMessageDialog(cmp, "B³¹d odczytu "
                  + e.getLocalizedMessage(), "B³¹d",
                  JOptionPane.WARNING_MESSAGE, null);
            e.printStackTrace();
         }
      }
   }
   
   /**
    * This method initializes wyjdz
    * 
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getWyjdz() {
      if (wyjdz == null) {
         wyjdz = new JMenuItem();
         wyjdz.setName("Wyl¹cz"); // Generated
         wyjdz.setText("Wyjdz"); // Generated
         wyjdz.setToolTipText("Wy³¹cza program"); // Generated
         wyjdz.addActionListener(new performDadabaseDestroyingAction() {
            
            @Override
            protected int performAction() {
               System.exit(0);
               return 0;
            }
            
         });
      }
      return wyjdz;
   }
   
   /**
    * This method initializes Produkt	
    * 	
    * @return javax.swing.JMenu	
    */
   private JMenu getProdukt() {
      if (Produkt == null) {
         Produkt = new JMenu();
         Produkt.setText("Produkt"); // Generated
         Produkt.add(getNowyProd()); // Generated
         Produkt.add(getAddFromTemplate()); // Generated
         Produkt.add(getWyszukaj()); // Generated
         
      }
      return Produkt;
   }
   
   /**
    * This method initializes nowyProd	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getNowyProd() {
      if (nowyProd == null) {
         nowyProd = new JMenuItem();
         nowyProd.setText("Nowy"); // Generated
         nowyProd.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
               System.out.println("Action");
               if (getProductGeneratingDialog().display() == ProductGeneratingDialogv3.USER_CLICKED_OK) {
                  
                  try {
                     System.err.println("Product: " + getProductGeneratingDialog().getProduct());
                     StaticContent.sc.getDataBaseMutable().addProduct(
                           productGeneratingDialog.getProduct());
                     StaticContent.sc.fireProductAdded();
                     
                  } catch (ZZInternalDuplicateEntryEx e) {
                     JOptionPane.showMessageDialog(MainFrame.this,
                           "B³¹d programisty - czyli mnie", "B³ad",
                           JOptionPane.WARNING_MESSAGE, null);
                     e.printStackTrace();
                  } catch (ZZIllegalEx e) {
                     JOptionPane.showMessageDialog(MainFrame.this, "B³¹d: "
                           + e.getMessage(), "B³ad",
                           JOptionPane.WARNING_MESSAGE, null);
                     e.printStackTrace();
                  }
                  
                  
               }
               System.out.println("Action2");
            }
            
         });
      }
      return nowyProd;
   }
   
   /**
    * This method initializes wyszukaj
    * 
    * @return javax.swing.JMenuItem
    */
   private JMenuItem getWyszukaj() {
      if (wyszukaj == null) {
         wyszukaj = new JMenuItem();
         wyszukaj.setEnabled(false); // Generated
         wyszukaj.setToolTipText("Bêdzie w nastêpnej wersji..."); // Generated
         wyszukaj.setText("Wyszukaj"); // Generated
      }
      return wyszukaj;
   }
   
   /**
    * This method initializes Posilek	
    * 	
    * @return javax.swing.JMenu	
    */
   private JMenu getPosilek() {
      if (Posilek == null) {
         Posilek = new JMenu();
         Posilek.add(getNowyPosilek()); // Generated
         Posilek.setText("Posilek");
         
      }
      return Posilek;
   }
   
   /**
    * This method initializes nowyPosilek	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getNowyPosilek() {
      if (nowyPosilek == null) {
         nowyPosilek = new JMenuItem();
         nowyPosilek.setText(" Nowy"); // Generated
         nowyPosilek.setToolTipText("Doda do dzis nowy posilek"); // Generated'
         nowyPosilek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               getDialog().display();
            }
            
         });
      }
      return nowyPosilek;
   }
   
   /**
    * This method initializes advanced	
    * 	
    * @return javax.swing.JMenu	
    */
   private JMenu getAdvanced() {
      if (advanced == null) {
         advanced = new JMenu();
         advanced.add(getZmienDzis()); // Generated
         advanced.setText("Zaawansowane");
      }
      return advanced;
   }
   
   /**
    * This method initializes zmienDzis	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getZmienDzis() {
      if (zmienDzis == null) {
         zmienDzis = new JMenuItem();
         zmienDzis
         .setToolTipText("Zmienia dzisiejsz¹ datê - tzn nie globalnie a w programie. Raczej siê nie przyda. \n bêdzie za czas jakiœ"); // Generated
         zmienDzis.setText("Zmieñ datê."); // Generated
         zmienDzis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
               String data = (String) JOptionPane.showInputDialog(StaticContent.sc.getUppmostFrame(),
                     "Podaj dzisiejsz¹ datê",
                     "pytanie",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     null,
                     null);
               
               try{
                  MyDate data2 = new MyDate(data.replace(",","."));
                  StaticContent.sc.setToday(data2);
               }catch(ParseException e){
                  JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
                        "Z³y format daty",
                        "Problem",
                        JOptionPane.WARNING_MESSAGE,
                        null);
               }                 
            }
         });
      }
      return zmienDzis;
   }
   
   private abstract class performDadabaseDestroyingAction implements
   ActionListener {
      protected String cosutmOption = "Anuluj";
      
      protected abstract int performAction();
      
      public void actionPerformed(ActionEvent e) {
         if (StaticContent.sc.getDataBase().getSorted().size() != 0) {
            String[] opcje = new String[] { "Tak, zapisz", "Nie, nie zapisuj",
                  cosutmOption };
            int result = JOptionPane.showOptionDialog(MainFrame.this,
                  "Wydawaæ by sie mog³o ¿e posiadasz\n niezapisan¹ "
                  + "bazê danych. Czy chcesz ja zapisaæ?", "Pytanie",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.WARNING_MESSAGE, null, opcje, opcje[0]);
            if (result == JOptionPane.CANCEL_OPTION
                  || result == JOptionPane.CLOSED_OPTION)
               return;
            if (result == JOptionPane.YES_OPTION) {
               int wynikZapisu = zapiszDomyslne(MainFrame.this);
               if (wynikZapisu != 0) {
                  JOptionPane.showMessageDialog(MainFrame.this,
                        "B³¹d zapisu \nOperacja przerwana", "Informacja",
                        JOptionPane.INFORMATION_MESSAGE, null);
                  return;
               }
               performAction();
            }
            if (result == JOptionPane.NO_OPTION) {
               int result2 = JOptionPane.showOptionDialog(MainFrame.this,
                     "Czy jesteœcie pewni?", "Pytanie",
                     JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                     null, new String[] { "Tak", "Nie" }, "Nie");
               if (result2 == JOptionPane.NO_OPTION
                     || result == JOptionPane.CLOSED_OPTION) {
                  return;
               } else {
                  performAction();
               }
            }
         } else { // Rozmiar wynosi zero...
            performAction();
         }
      }
   }
   
   private MealGeneatingDialog getDialog() {
      if (dialog == null) {
         dialog = new MealGeneatingDialog();
      }
      
      return dialog;
   }
   
   private JFileChooser getFileChooser() {
      if (fileChooser == null) {
         fileChooser = new JFileChooser(getRootFile());
      }
      return fileChooser;
   }
   
   private static void setRootFile(File rootFile) {
      MainFrame.rootFile = rootFile;
   }
   
   private static File getRootFile() {
      if (rootFile == null) {
         rootFile = new File((File) null, "Database");
      }
      return rootFile;
   }
   
   /**
    * This method initializes treePanel	
    * 	
    * @return javax.swing.JPanel	
    */
   private JPanel getTreePanel() {
      if (treePanel == null) {
         GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
         gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH; // Generated
         gridBagConstraints3.gridy = 1; // Generated
         gridBagConstraints3.weightx = 1.0; // Generated
         gridBagConstraints3.weighty = 1.0; // Generated
         gridBagConstraints3.gridx = 0; // Generated
         GridBagConstraints gridBagConstraints = new GridBagConstraints();
         gridBagConstraints.gridx = 0; // Generated
         gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST; // Generated
         gridBagConstraints.gridy = 0; // Generated
         treePanel = new JPanel();
         treePanel.setLayout(new GridBagLayout()); // Generated
         treePanel.add(getResetTree(), gridBagConstraints); // Generated
         treePanel.add(getTreeScrollPane(), gridBagConstraints3); // Generated
         treePanel.addMouseListener(new mouseListener());
      }
      return treePanel;
   }
   
   /**
    * This method initializes resetTree	
    * 	
    * @return javax.swing.JButton	
    */
   private JButton getResetTree() {
      if (resetTree == null) {
         resetTree = new JButton();
         resetTree.setText("Reset "); // Generated
         resetTree
         .setToolTipText("Niestety mo¿e byæ konieczne u¿ycie tego guzika. Odbudowuje drzewo od zera."); // Generated
         resetTree.setMinimumSize(resetTree.getMinimumSize());
         resetTree.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
               resetTree();
               
            }
            
         });
      }
      return resetTree;
   }
   
   /**
    * This method initializes treeScrollPane	
    * 	
    * @return javax.swing.JScrollPane	
    */
   private JScrollPane getTreeScrollPane() {
      if (treeScrollPane == null) {
         treeScrollPane = new JScrollPane();
         treeScrollPane.setViewportView(getDaysTree()); // Generated
      }
      return treeScrollPane;
   }
   
   /**
    * This method initializes jTree	
    * 	
    * @return javax.swing.JTree	
    */
   private JTree getDaysTree() {
      if (DaysTree == null) {
         DaysTree = new JTree(getModel());
         DaysTree.setRootVisible(false);
         DaysTree.addMouseListener(new mouseListener());
      }
      return DaysTree;
   }
   
   private DefaultTreeModel getModel() {
      if (model == null) {
         model = new DefaultTreeModel(new DefaultMutableTreeNode());
      }
      return model;
   }
   
   /*default*/void resetTree() {
      try {
         getModel().setRoot(
               BranchGenerator.allDaysBranch(StaticContent.sc.getDays(),
                     getDaysTree()));
      } catch (Exception e) {
         JOptionPane.showMessageDialog(this,
               "B³¹d! Jakiœ b³¹d przy two¿eniu drzewa" + e.getMessage(),
               "B³¹d", JOptionPane.WARNING_MESSAGE, null);
      }
   }
   
   private class mouseListener implements MouseListener {
      
      public void mouseClicked(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mousePressed(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseReleased(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseEntered(MouseEvent arg0) {
         maybeShowPopup(arg0);
         
      }
      
      public void mouseExited(MouseEvent arg0) {
         maybeShowPopup(arg0);
      }
      
      private void maybeShowPopup(MouseEvent arg0) {
         
         if (!arg0.isPopupTrigger())
            return;
         System.out.println("Menu");
         TreeElement element;
         DefaultMutableTreeNode node;
         try {
            node = (DefaultMutableTreeNode) getDaysTree()
            .getLastSelectedPathComponent();
            element = (TreeElement) node.getUserObject();
            
         } catch (ClassCastException e) {
            //Mo¿e zawieraæ inne typy danych - te bie bêd¹ mia³y popupa ;-) Czyli git :P
            return;
         } catch (NullPointerException e) {
            return; //getJTree().getLastSelectedPathComponent(); mo¿e zwróciæ null
            //Jest to przejaw normalnej dzia³alnoœci programu
         }
         
         if (element.hasAssociatedPopUpMenu()) {
            try {
               element.getAssociatedPopUpMenu().show(
                     getDaysTree(),
                     arg0.getX(),
                     arg0.getY(),
                     (DefaultMutableTreeNode) getDaysTree()
                     .getLastSelectedPathComponent(), model);
               
            } catch (ZZInternalUnsupportedOperationExceptionEx e) {
               
               e.printStackTrace();
            } catch (ZZIllegalEx e) {
               
               e.printStackTrace();
            }
         }
         
      }
   }
   
   public ProductGeneratingDialogv3 getProductGeneratingDialog() {
      if (productGeneratingDialog == null)
         productGeneratingDialog = new ProductGeneratingDialogv3();
      return productGeneratingDialog;
   }
   
   /**
    * This method initializes addFromTemplate	
    * 	
    * @return javax.swing.JMenu	
    */
   private JMenu getAddFromTemplate() {
      if (addFromTemplate == null) {
         addFromTemplate = new JMenu();
         addFromTemplate.setText("Dodaj z szablonu...");
         StaticContent.myActionListener list = new StaticContent.myActionListener() {
            public void actionPerformed(String command) {
               if (command.equals(StaticContent.templatesChanged)) {
                  getAddFromTemplate().removeAll();
                  for (Vector<String> vec : StaticContent.sc.getTemplates()) {
                     JMenuItem foo = new JMenuItem();
                     foo.setText(vec.get(0));
                     foo.addActionListener(new TemplateActionListener(vec));
                     addFromTemplate.add(foo);
                  }
               }
            }
         };
         
         StaticContent.sc.addListener(list);
         
         for (Vector<String> vec : StaticContent.sc.getTemplates()) {
            JMenuItem foo = new JMenuItem();
            foo.setText(vec.get(0));
            foo.addActionListener(new TemplateActionListener(vec));
            addFromTemplate.add(foo);
         }
      }
      return addFromTemplate;
   }
   
   private class TemplateActionListener implements ActionListener {
      
      ProductGeneratingDialogv3.templateI temp;
      
      public TemplateActionListener(Vector<String> vec) {
         super();
         temp = new ProductGeneratingDialogv3.templateI(vec);
      }
      
      public void actionPerformed(ActionEvent arg0) {
         if (getProductGeneratingDialog().display(temp) == ProductGeneratingDialogv3.USER_CLICKED_OK) {
            
            try {
               System.err.println("Product: " + getProductGeneratingDialog().getProduct());
               StaticContent.sc.getDataBaseMutable().addProduct(
                     productGeneratingDialog.getProduct());
               StaticContent.sc.fireProductAdded();
               
            } catch (ZZInternalDuplicateEntryEx e) {
               JOptionPane.showMessageDialog(MainFrame.this,
                     "B³¹d programisty - czyli mnie", "B³ad",
                     JOptionPane.WARNING_MESSAGE, null);
               e.printStackTrace();
            } catch (ZZIllegalEx e) {
               JOptionPane.showMessageDialog(MainFrame.this, "B³¹d: "
                     + e.getMessage(), "B³ad",
                     JOptionPane.WARNING_MESSAGE, null);
               e.printStackTrace();
            }
            
            
         }
      }
      
   }
   
   /**
    * This method initializes imporTZExela	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getImporTZExela() {
      if (imporTZExela == null) {
         imporTZExela = new JMenuItem();
         imporTZExela.setText("Import z Exela");
         imporTZExela.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
               int result = getFileChooser().showDialog(MainFrame.this, "Import");
               if(result == JFileChooser.APPROVE_OPTION){
                  File file = getFileChooser().getSelectedFile();
                  try {
                     BufferedReader read = new BufferedReader(new FileReader(file));
                     String line;
                     read.readLine();
                     int i =0;
                     while((line = read.readLine())!=null){
                        StringTokenizer tokener = new StringTokenizer (line,";");
                        Product p = new SimpleProduct();
                        if(tokener.nextToken().trim().length()==0){
                           break;
                        }
                        p.setName(tokener.nextToken().replace("\"",""));
                        System.out.println(++i);
                        String cena = tokener.nextToken();
                        p.setSQuantity(Float.parseFloat(cena.replace(",",".").replace("\"","")));
                        System.out.println("Cena" + cena + ", " + cena.replace(",",".").replace("\"","") );
                        p.setPrice(Float.parseFloat(tokener.nextToken().replace(",",".").replace("\"","")));
                        p.setDateOfBooking(new MyDate("16.07.06"));
                        p.setExpiryDate(new MyDate("01.01.07"));
                        p.setFacturaNo("Nota ksiêgowa - przejête z magazynu");
                        p.setDescription("Przejête z magazynu");
                        if(!AbstractProduct.getUnitMap().containsTwo("opak.")){
                           AbstractProduct.getUnitMap().put(AbstractProduct.getUnitMap().maxOne()+1,"opak.");
                        }
                        if(!AbstractProduct.getPackagingMap().containsTwo("n.dot. - przejête")){
                           AbstractProduct.getPackagingMap().put(AbstractProduct.getPackagingMap().maxOne()+1,"n.dot. - przejête");
                        }
                        p.setPackaging("n.dot. - przejête");
                        p.setUnit("opak.");
                        StaticContent.sc.getDataBaseMutable().addProduct(p);
                     }
                  } catch (Exception e) {
                     e.printStackTrace();
                     JOptionPane.showMessageDialog(
                           StaticContent.sc.getUppmostFrame(),
                           "B³¹d",
                           "B³¹d:\n" + e.getClass().getName() + "\n" + e.getMessage(),
                           JOptionPane.WARNING_MESSAGE,
                           null);
                  }
                  
               }
               
            }});
      }
      return imporTZExela;
   }
   
   /**
    * This method initializes ZapiszStanMagazynu	
    * 	
    * @return javax.swing.JMenuItem	
    */
   private JMenuItem getZapiszStanMagazynu() {
      if (ZapiszStanMagazynu == null) {
         ZapiszStanMagazynu = new JMenuItem();
         ZapiszStanMagazynu.setText("Zapisz stan magazynu do...");
         ZapiszStanMagazynu.setToolTipText("Zapisuje stan magazynu do pliku csv");
         ZapiszStanMagazynu.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent arg0) {
               int result = getFileChooser().showDialog(StaticContent.sc.getUppmostFrame(),"Export");
               Vector<String> errors = new Vector<String>();
               if(result == JFileChooser.APPROVE_OPTION){
                  File file = getFileChooser().getSelectedFile();
                  BufferedWriter wrr = null;
                  try {
                     wrr = new BufferedWriter (new FileWriter(file));
                     for (Product p : StaticContent.sc.getDataBase().getSorted()){
                        try{
                           String line = new String();
                           line += p.getName() + ",";
                           line += p.getCurrQ() + ",";
                           line += p.getPrice() + ",";
                           line += p.getExpiryDate() + ",";
                           line +="\n";
                           wrr.write(line);
                        }catch(Exception e){
                           errors.add("B³¹d: " + e.getClass().getName() + " msg: " + e.getMessage() );
                        }
                     }
                  } catch (Exception e) {
                     errors.add("B³ad przy otwieraniu pliku: "+ e.getClass().getName() + " msg: " + e.getMessage() );
                     e.printStackTrace();
                  }finally{
                     if(wrr!=null){
                        try {
                           wrr.flush();
                           wrr.close();
                        } catch (IOException e) {
                           e.printStackTrace();
                        }                        
                     }
                  }
                  if(errors.size()!=0){
                     JOptionPane.showMessageDialog(MainFrame.this,
                           new JList(errors),
                           "B³êdy:",
                           JOptionPane.WARNING_MESSAGE,
                           null);
                  }
                  
               }
               
            }
            
         });
      }
      return ZapiszStanMagazynu;
   }
   
   public JMenuItem getOdczytajZkat() {
      if(OdczytajZkat == null){
         OdczytajZkat = new JMenuItem();
         OdczytajZkat.setText("Odczytaj bazê z katalogu");
         OdczytajZkat.addActionListener(new performDadabaseDestroyingAction(){
            
            @Override
            protected int performAction() {
               int result = getDirChooser().showOpenDialog(MainFrame.this);
               if(result == JFileChooser.APPROVE_OPTION){
                  StaticContent.sc.reset();
                  try {
                     DirLoadSaver.load(StaticContent.sc, getDirChooser().getSelectedFile());
                  } catch (Exception e){
                     JOptionPane.showMessageDialog(
                           MainFrame.this,
                           "B³¹d:\n" + e.getClass().getName() + "\n" + e.getMessage(), 
                           "Bl¹d",
                           JOptionPane.ERROR_MESSAGE,
                           null
                     );
                     e.printStackTrace();
                     return 1;
                  }   
                  
                  
               }
               return 0;
               
            }
         });
      }
      
      return OdczytajZkat;
   }
   
   
   
   public JFileChooser getDirChooser() { 
      if(dirChooser==null){
         dirChooser= new JFileChooser();
         FileView view = new FileView(){
            @Override
            public Boolean isTraversable(File arg0) {
               File[] files = arg0.listFiles();
               for(File f : files){
                  if (f.isDirectory()) return false;
               }               
               return true;
            }
         };
         
         FileFilter  filter = new FileFilter(){
            static final String nazwa = "Katalogi, csv i txt";
            
            @Override
            public boolean accept(File f) {
               if(f.getName().endsWith(".csv")) return true;
               if(f.getName().endsWith(".txt")) return true;
               if(f.isDirectory()) return true;
               return false;
            }
            
            @Override
            public String getDescription() {
               return nazwa;
            }
            
         };
         
         dirChooser.setFileView(view);
         dirChooser.addChoosableFileFilter(filter);
         dirChooser.setAcceptAllFileFilterUsed(false);
         
         
         
         
      }
      return dirChooser;
   }
   
   public JMenuItem getZapiszStanyMagazynu() {
      if(zapiszStanyMagazynu == null){
         zapiszStanyMagazynu = new JMenuItem ();
         zapiszStanyMagazynu.setText("Zapisz stan magazynu z dni");
         zapiszStanyMagazynu.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent arg0) {
               TreeMap<MyDate, Product> map = new TreeMap<MyDate,Product>();
               
               for(Product p: StaticContent.sc.getDataBase().getSorted()){
                  try {
                     map.put(p.getDateOfBooking(), null);
                     for(Expenditure e: p.getExpendituresNotTheirIds()){
                        map.put(e.date,null);
                     }
                  } catch (ZZIllegalEx e) {
                     e.printStackTrace();
                  }            
               }
               
               Vector<MyDate> daty = new Vector( map.keySet());
               
               //JTable table = new JTable (new DateChooserTableModel (new Vector (daty)));
               
               /*class myDialog extends JDialog{
                myDialog(JTable table){
                table.setPreferredSize(new Dimension(640,480));
                this.add(table);
                this.setModal(true);
                JButton button = new JButton();
                button.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent arg0) {
                myDialog.this.setVisible(false);
                
                }
                
                });
                this.add(button);                     
                }*
                }*/
               
               //myDialog dialog = new myDialog(table);
               
               //dialog.setVisible(true);
               
               // DateChooserTableModel ch= (DateChooserTableModel) table.getModel();
               Vector<Product> prods = new Vector(StaticContent.sc.getDataBase().getSorted());
               Collections.sort(prods,new OrderByName());
               File file = StaticContent.sc.getDocumentsFolder();
               for(int i = 0; i<daty.size(); i++){
                  //if(!ch.bool.get(i)) continue;
                  File f = new File(file, "StanMagNa" + daty.get(i).toString() + ".csv");
                  BufferedWriter buffwr = null;
                  try{
                     buffwr = new BufferedWriter (new FileWriter(f));
                     buffwr.write("Stan magazynu na:" + daty.get(i).toString() + "\n\n");
                     buffwr.write("Nazwa, iloœæ, jedn mian, opis\n");
                     for(Product p : prods){
                        if(p.getDateOfBooking().after(daty.get(i))) continue;
                        float quant = p.getSQuantity();
                        for(Expenditure e : p.getExpendituresNotTheirIds()){
                           if(!e.date.after(daty.get(i))) quant -= e.ile;
                        }
                        if(quant>0){
                           buffwr.write(p.getName() + ", " + quant + "," + p .getUnit() + ", " + p.getDescription().replace(",","").replace(";","") + "\n");
                        }
                     }
                  }catch(Exception e){
                     JOptionPane.showMessageDialog(MainFrame.this,"B³¹d wejœcia-wyjœia, lub nie ustawiono pól" +
                           "\n" + e.getClass().getName() + "\n" + e.getMessage());
                  }finally{
                     if(buffwr != null){
                        try {
                           buffwr.close();
                        } catch (IOException e) {
                           //Tego nie ³apie p.
                           e.printStackTrace();
                        }
                     }
                  }                 
               }
            }           
         });
         
      }
      return zapiszStanyMagazynu;
   }
   
   
   public JMenuItem getGenKartoteki()  {
      if (genKartoteki == null) {
         genKartoteki = new JMenuItem();
         genKartoteki.setText("Generuje kartotrki produktów");
         genKartoteki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               Vector<Product> allProducts = new Vector (StaticContent.sc.getDataBase().getSorted());
               Collections.sort(allProducts, new OrderByName());
               Collections.sort(allProducts, new OrderByPrice());
               int iter = 0;
               File k = new File(StaticContent.sc.getDocumentsFolder(), "Kartoteki");
               k.mkdirs();
               for(File f: k.listFiles()){
                  f.delete();
               }
               try{
                  
                  while(allProducts.size()!=0){
                     Vector<Product> products = new Vector();
                     Float price;
                     try {
                        price = allProducts.lastElement().getPrice();
                     } catch (ZZIllegalEx e2) {
                        price = 0f;
                        e2.printStackTrace();
                     }
                     Product p1;
                     try {
                        while ((p1 = allProducts.lastElement()) != null) {
                           if (p1.getPrice() == price) {
                              products.add(p1);
                              allProducts.remove(p1);
                           }else{
                              break;
                           }
                        }
                     }
                     catch(NoSuchElementException e){
                        //Starczy przerwaæ pêtlê.
                     } catch (ZZIllegalEx e) {
                        //Starczy przerwaæ pêtlê.
                        e.printStackTrace();
                     }
                     Vector<Product> store = new Vector(products); 
                     String name;
                     float priceF=666f;
                     
                     System.out.println("Products size" + products.size());
                     if(products.size()>1){
                        ChoosProdsInDialogv2 dialog = new ChoosProdsInDialogv2(products);
                        dialog.setVisible(true);
                        if(dialog.wantsWxit){
                           throw new Exit();
                        }                     
                        try {
                           name = products.get(dialog.printedNameOffset).getName();
                           
                        } catch (ZZIllegalEx e) {
                           name = "bezNazwy";
                           e.printStackTrace();
                        }
                        products = dialog.getChoosenProds();
                     }else{
                        try {
                           name = products.get(0).getName();
                           priceF = products.get(0).getPrice();
                        } catch (ZZIllegalEx e) {
                           name = "bezNazwy";
                           e.printStackTrace();
                        }
                     }
                     System.out.println("Products size" + products.size());
                     
                     TreeSet<Expenditure> set = new TreeSet<Expenditure>(new Comparator<Expenditure> (){
                        
                        public int compare(Expenditure arg0, Expenditure arg1) {
                           return arg0.date.compareTo(arg1.date); 
                        }
                     });
                     
                     for(Product p : products){
                        try {
                           set.add(new Expenditure(- p.getSQuantity(), p.getDateOfBooking(), p.getFacturaNo() ));
                        } catch (ZZIllegalEx e1) {
                           // TODO Auto-generated catch block
                           e1.printStackTrace();
                        }
                        for(Expenditure e : p.getExpendituresNotTheirIds()){
                           set.add(e);
                        }
                     }
                     
                     
                     File f;
                     f = new File(k,name + ".csv");
                     int i = 0;
                     while(f.exists()){
                        f = new File(k, name.replace("\"", "").replace(",", "").replace("/", "") +"_" + i++ + ".csv");
                     }
                     
                     
                     boolean result = true;
                     BufferedWriter wr = null;
                     while(result){       
                        try {
                           float currQuant=0;
                           
                           int lp=0;
                           f.createNewFile();
                           wr = new BufferedWriter (new FileWriter(f));
                           System.out.println(f.getCanonicalPath());
                           wr.write(",KARTOTEKA MAGAZYNOWA" + "\n");
                           wr.write(",ILOŒCIOWO-WARTOŒCIOWA" + "\n");
                           wr.write("," + "\n");
                           wr.write(",Nazwa towaru: " + name + ", cena:" + priceF);
                           wr.write("," + "\n");
                           wr.write("Lp," + "Data," + "Symbol i nr. dowodu," + "Przychód, ," + "Rozchód, ," + "Stan, ,"+ "\n"  );
                           wr.write(", " + ", " + ", " +  "iloœæ, wartoœæ," + "iloœæ, wartoœæ," +"iloœæ, wartoœæ,"  + "\n" );
                           wr.write("1, 2, 3, 4, 5, 6, 7, 8, 9\n" );
                           for(Expenditure e: set){
                              currQuant -= e.ile;
                              if(e.ile>0){
                                 wr.write("" + ++lp + ", " + e.date.toString() + ", " + "Wydano dn: " + e.date.toString() + "na " + e.tytulem.replace(",", "").replace(";", ",") + ", " + "---" + ", " + "---" + "," + e.ile  + ", " + e.ile * priceF + ", " + "" + currQuant + " ," + currQuant*priceF  + "\n");
                              }else{
                                 if(e.ile<0){
                                    wr.write("" + ++lp + ", " + e.date.toString() + ", " + e.tytulem.replace(",", "").replace(";", ",") + ", "  + - e.ile  + ", " + - e.ile * priceF + ", "  + "---" + ", " + "---" + ", " + currQuant + " ," + currQuant*priceF  + "\n");
                                 }
                              }
                           }                     
                        } catch (IOException e) {
                           
                           e.printStackTrace();
                           String name1 = (String) JOptionPane.showInputDialog(MainFrame.this,
                                 "B³¹d w nazwie pliku\nPodaj now¹ nazwê pliku",
                                 "B³¹d",
                                 JOptionPane.QUESTION_MESSAGE,
                                 null,
                                 null,
                                 null
                           );
                           
                           f = new File(k, name.replace("\"", "").replace(",", "").replace("/", "") +"_" + i++ + ".csv");
                           while(f.exists()){
                              f = new File(k, name.replace("\"", "").replace(",", "").replace("/", "") +"_" + i++ + ".csv");
                           }
                           try {
                              f.createNewFile();
                           } catch (IOException e1) {
                              e1.printStackTrace();
                              continue;
                              
                           }
                           
                           
                        }
                        result = false;
                     }
                     
                     if(wr!=null){
                        try {
                           wr.close();
                        } catch (IOException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                        }
                     }
                     
                     
                     for(Product p :store){
                        if(!products.contains(p)){
                           allProducts.add(p);
                           
                        }
                     }
                     Collections.sort(allProducts, new OrderByName());
                     Collections.sort(allProducts, new OrderByPrice());
                     System.out.println(iter++ + ", " + allProducts.size());
                     
                  }
                  
               }
               catch(Exit e){
                  
               }
            }
         });
         
         
         
         
      }
      
      return genKartoteki;
   }
   
   
   /*JMenuItem getGenZZ(){
    if(genZZ == null){
    genZZ = new JMenuItem();
    genZZ.setText("Generuj ZZ");
    genZZ.addActionListener(
    new ActionListener(){
    
    public void actionPerformed(ActionEvent arg0) {
    File ZZFolder = new File(StaticContent.sc.getDocumentsFolder(), "ZZ");
    ZZFolder.mkdir();
    for(Day d: StaticContent.sc.getDays()){
    File zz = new File(ZZFolder, "ZZ" + d.getDate().toString() + ".txt");
    BufferedWriter buffWr = new BufferedWriter (new FileWriter(zz));
    Vector<Boolean> bool = new Vector(d.getMeals().size());
    for(int i = 0; i < d.getMeal().size(); i++){
    bool.add(Boolean.TRUE);
    }
    d.print11A(buffWr, bool);                        
    }
    
    }
    
    });
    }
    }*/
   
   
   
   
   
}//  @jve:decl-index=0:visual-constraint="1,10"
