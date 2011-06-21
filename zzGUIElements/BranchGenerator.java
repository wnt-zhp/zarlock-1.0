package zzGUIElements;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import zzDataBase.Day;
import zzDataBase.Expenditure;
import zzDataBase.MealExpenditure;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;
import zzEx.PrimaryError;
import zzEx.ZZIllegalEx;
import zzEx.ZZIoEx;
import zzGUIElements.AbstractGuiElements.AbstractEmptyTreeElement;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenu;
import zzGUIElements.AbstractGuiElements.AbstractTreeElement;
import zzGUIElements.PopupMenu.PopupMenuGenerator;
import zzGUIElements.ZZTreeElements.DayTreeData;
import zzGUIElements.ZZTreeElements.ExpenditureTreeData;
import zzGUIElements.ZZTreeElements.IleTreeData;
import zzGUIElements.ZZTreeElements.KiedyTreeData;
import zzGUIElements.ZZTreeElements.MealTreeBranch;
import zzGUIElements.ZZTreeElements.ProductTreeBranch;
import zzGUIElements.ZZTreeElements.Tytu³emTreeData;
import zzId.ExpenditureId;
import zzId.MealExpenditureId;
import zzProduct.ExpenditureHolder;
import zzProduct.Product;

public abstract class BranchGenerator {
  public static JEditorPane getEmptyPane(){
    if(emptyPanel == null){
      String message = new String();
      message += encloseInTag("B", "Null") + newLine + "Nie ma nic do wyswietlenia";
      
      try {
        emptyPanel = getReadyPane(message);
      } catch (ZZIllegalEx e) {
        emptyPanel = new JEditorPane();
        e.printStackTrace(System.err);
      } catch (ZZIoEx e) {
        emptyPanel = new JEditorPane();
        e.printStackTrace(System.err);
      }
      emptyPanel.setEditable(false);
    }
    return emptyPanel;
  }
  private static JEditorPane emptyPanel;
  
  private static  JEditorPane getMealPane(){
		    if(mealPane == null){
		      String message = new String();
		      message += encloseInTag("B", "Wy³¹czono") + newLine + "Z przyczyn wydajnoœciowych wy³¹czono";
		      
		      try {
		      	mealPane = getReadyPane(message);
		      } catch (ZZIllegalEx e) {
		      	mealPane = new JEditorPane();
		        e.printStackTrace(System.err);
		      } catch (ZZIoEx e) {
		      	mealPane = new JEditorPane();
		        e.printStackTrace(System.err);
		      }
		      mealPane.setEditable(false);
		    }
		    return mealPane;
  }
  
  private static JEditorPane mealPane;
  
  public static final String newLine = "<br>";
  
  public static DefaultMutableTreeNode dayBranch(Day d, JTree tree) throws ZZIllegalEx, ZZIoEx{
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode(
			  new DayTreeData(createDayDocument(d),
					  tree,
					  d,
					  PopupMenuGenerator.getDayPopupMenu(tree))
	  		);
	  for(Mealv3 m: d.getMeals()){
		  root.add(expenditureBranch(m,tree));
	  }
	  return root;
  }
  
  public static DefaultMutableTreeNode allDaysBranch(List<Day> days, JTree tree)throws ZZIllegalEx, ZZIoEx{
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode(new AbstractEmptyTreeElement());
	  for(Day d: days){
		  root.add(dayBranch(d,tree));
	  }
	  return root;
  }
    
  public static DefaultMutableTreeNode expendituresBranch(Product p, JTree tree) throws ZZIllegalEx, ZZIoEx{
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode(
			  new ProductTreeBranch(p.getName(), 
					  createProductDocument(p), 
					  tree,
					  PopupMenuGenerator.getProductPopupMenu(tree),
					  p)
	  		);
	  
	  DefaultMutableTreeNode branch;
	  
	  for(Map.Entry<ExpenditureId, Expenditure> me : p.getExpenditures()){
		  branch = getExpenditureNode(me.getValue(), me.getKey(),p,tree,
				  PopupMenuGenerator.getExpenditureInProductMenu(tree),
				  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree),
				  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree),
				  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree));
		  root.add(branch);
	  };
	 
	  return root;
  }
  
  public static DefaultMutableTreeNode expenditureBranch(Mealv3 m, JTree tree) throws ZZIllegalEx, ZZIoEx{
	   DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				  new MealTreeBranch(
						  m.getName(), 
						  createMealDocument(m), 
						  tree,
						  m,
						  PopupMenuGenerator.getMealPopupMenu(tree)
						  )
		  		);
	   
	   DefaultMutableTreeNode branch;	   
	   for(Map.Entry<MealExpenditureId, MealExpenditure> me : m.getContents().entrySet()){
			  branch = getExpenditureNode(me.getValue(),m,tree);
			  root.add(branch);
		  };
		  return root;
		  /**return expendturesBranch(m.getExpendtures(), 
	       new MealTreeBranch( m.getName(), createMealDocument(m), m));*/
	  }
  /**
   * DOES NOT CONTAIN TreeData
   * @param p
   * @return
   */
  public static DefaultMutableTreeNode getMealProduct(Product p){
	  DefaultMutableTreeNode root;
	try {
		root = new DefaultMutableTreeNode("Produkt: " + p.getName());
	} catch (ZZIllegalEx e) {
		root = new DefaultMutableTreeNode("Nieznany produkt");
		e.printStackTrace();
	}
	  try {
		root.add(new DefaultMutableTreeNode("Ilosc na skladzie: " + + p.getCurrQ()  ));
	} catch (ZZIllegalEx e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		root.add(new DefaultMutableTreeNode("Data Ksiêgowania: " +  p.getDateOfBooking()  ));
	} catch (ZZIllegalEx e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		root.add(new DefaultMutableTreeNode("Data przydatnoœci: " + p.getExpiryDate()));
	} catch (ZZIllegalEx e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		root.add(new DefaultMutableTreeNode("Faktura: " +  p.getFacturaNo()  ));
	} catch (ZZIllegalEx e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return root;
  }
  
  public static DefaultMutableTreeNode getExpenditureNode(MealExpenditure e, Mealv3 m, JTree tree) throws ZZIllegalEx, ZZIoEx{

	  ExpenditureTreeData data;
	  IleTreeData ileData;
	  KiedyTreeData kiedyData;
	  Tytu³emTreeData tytulemTreeData;
	  data= new ExpenditureTreeData(
			  "Wyjœcie z dnia: ",
			  createExpenditureDocument(e),
			  tree,
			  e.getId(),
			  e,
			  PopupMenuGenerator.getExpenditureInMealMenu(tree));
	  DefaultMutableTreeNode  branch = new DefaultMutableTreeNode(data);

	  
	  Product p =  StaticContent.sc.getDataBase().getById(e.getPId());
	  branch.add(getMealProduct(p));

	  ileData = new IleTreeData(
			  "Wydano: ", 
			  getEmptyPane(),
			  tree,
			  e, 
			  e.getId(),
			  m,
			  new AbstractPopupMenu());
	  branch.add(new DefaultMutableTreeNode(ileData));
	  kiedyData = new KiedyTreeData(
			  "Dnia: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  e.getId(),
			  m,
			  new AbstractPopupMenu());
	  branch.add(new DefaultMutableTreeNode(kiedyData));
	  tytulemTreeData = new Tytu³emTreeData(
			  "Tytulem: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  e.getId(), 
			  m,
			  new AbstractPopupMenu());
	  branch.add(new DefaultMutableTreeNode(tytulemTreeData));
	  return branch;
  }
  
  public static DefaultMutableTreeNode getExpenditureNode(Expenditure e, 
		  ExpenditureId id, 
		  ExpenditureHolder p,
		  JTree tree,
		  AbstractPopupMenu expenditureMenu,
		  AbstractPopupMenu ileMenu,
		  AbstractPopupMenu kiedyMenu,
		  AbstractPopupMenu tytulemlem) 
  throws ZZIllegalEx, ZZIoEx
  {
	  DefaultMutableTreeNode branch;
	  ExpenditureTreeData data;
	  IleTreeData ileData;
	  KiedyTreeData kiedyData;
	  Tytu³emTreeData tytulemTreeData;
	  data= new ExpenditureTreeData(
			  "Wyjœcie z dnia: ",
			  createExpenditureDocument(e),
			  tree,
			  id,
			  e,
			  expenditureMenu);
	  branch = new DefaultMutableTreeNode(data);

	  ileData = new IleTreeData(
			  "Wydano: ", 
			  getEmptyPane(),
			  tree,
			  e, 
			  id, 
			  p,
			  ileMenu);
	  branch.add(new DefaultMutableTreeNode(ileData));
	  kiedyData = new KiedyTreeData(
			  "Dnia: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  id, 
			  p,
			  kiedyMenu);
	  branch.add(new DefaultMutableTreeNode(kiedyData));
	  tytulemTreeData = new Tytu³emTreeData(
			  "Tytulem: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  id, 
			  p,
			  tytulemlem);
	  branch.add(new DefaultMutableTreeNode(tytulemTreeData));
	  return branch;
  }
  
  public static DefaultMutableTreeNode getExpenditureNode(Expenditure e, 
		  ExpenditureId id, 
		  ExpenditureHolder p,
		  JTree tree)
  throws ZZIllegalEx, ZZIoEx
  {
	  ExpenditureTreeData data;
	  IleTreeData ileData;
	  KiedyTreeData kiedyData;
	  Tytu³emTreeData tytulemTreeData;
	  data= new ExpenditureTreeData(
			  "Wyjœcie z dnia: ",
			  createExpenditureDocument(e),
			  tree,
			  id,
			  e,
			  PopupMenuGenerator.getExpenditureInProductMenu(tree));
	  DefaultMutableTreeNode  branch = new DefaultMutableTreeNode(data);

	  ileData = new IleTreeData(
			  "Wydano: ", 
			  getEmptyPane(),
			  tree,
			  e, 
			  id, 
			  p,
			  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree));
	  branch.add(new DefaultMutableTreeNode(ileData));
	  kiedyData = new KiedyTreeData(
			  "Dnia: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  id, 
			  p,
			  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree));
	  branch.add(new DefaultMutableTreeNode(kiedyData));
	  tytulemTreeData = new Tytu³emTreeData(
			  "Tytulem: " , 
			  getEmptyPane(), 
			  tree,
			  e, 
			  id, 
			  p,
			  PopupMenuGenerator.getPartOfAnExpenditureInProductMenu(tree));
	  branch.add(new DefaultMutableTreeNode(tytulemTreeData));
	  return branch;
  }
  
  public static JEditorPane createMealDocument(Mealv3 meal) throws ZZIllegalEx, ZZIoEx{
    /*String HTML = new String();
    HTML += encloseInTag("B", "Nazwa: ") + meal.getName() + newLine;
    HTML += encloseInTag("B", "Data: ") + meal.getDate() + newLine;
    HTML += encloseInTag("B", "Opis: ") + meal.getDescription() + newLine;
    HTML = encloseInTag("HTML", encloseInTag("BODY", HTML) );*/
    return getMealPane();
    }
  
  public static JEditorPane createExpenditureDocument (Expenditure e) throws ZZIllegalEx, ZZIoEx{
	    String HTML = new String();
	    HTML += encloseInTag("B", "Ile: ") + e.ile + newLine;
	    HTML += encloseInTag("B", "Data: ") + e.date + newLine;
	    HTML += encloseInTag("B", "Tytulem: ") + e.tytulem + newLine;
	    HTML = encloseInTag("HTML", encloseInTag("BODY", HTML) );
	    return getReadyPane(HTML);
  }
  
  public static JEditorPane createProductDocument(Product p) throws ZZIllegalEx, ZZIoEx{
    String HTML = new String();
    HTML += encloseInTag("B", "Nazwa: ") + p.getName()  + newLine;
    HTML += encloseInTag("B", "Ilosc na skladzie: ") + p.getCurrQ() + newLine;
    HTML += encloseInTag("B", "Data Ksiêgowania: ") + p.getDateOfBooking() + newLine;
    HTML += encloseInTag("B", "Data przydatnoœci: ") + p.getExpiryDate() + newLine;
    HTML += encloseInTag("B", "Opis: ") + p.getDescription() + newLine;    
    /* ... */
    HTML = encloseInTag("HTML", encloseInTag("BODY", HTML) );
    return getReadyPane(HTML);    
  }
  
  public static JEditorPane createDayDocument(Day d) throws ZZIllegalEx, ZZIoEx{
	  /*String HTML = new String();
	    HTML += encloseInTag("B", "Nazwa: ") + d.getName()  + newLine;
	    HTML += encloseInTag("B", "Dzien") + d.getDate() + newLine;
	    HTML += encloseInTag("B", "Opis: ") + d.getDescription() + newLine;    
	    HTML = encloseInTag("HTML", encloseInTag("BODY", HTML) );*/
	    return getMealPane(); 
  }
  
  protected static JEditorPane getReadyPane(String contents) throws ZZIllegalEx, ZZIoEx{
    JEditorPane okienko = new JEditorPane();
    okienko.setBackground(UIManager.getColor("Window.background"));
    okienko.setContentType("text/html");
    HTMLDocument doc = (HTMLDocument) okienko.getDocument();
    HTMLEditorKit kit = (HTMLEditorKit) okienko.getEditorKit();
    try{
      StringReader stringReader = new StringReader(contents);
      kit.read(stringReader, doc, 0);
      stringReader.close();
    }catch(IOException ioe){
      throw new ZZIoEx("Nie uda³o siê stwo¿yæ obiektu klasy String Reader/n klasa:BranchGenertaotr");
      //Zak³adam ¿e siê nie zdarzy
    }catch(BadLocationException e){
      throw new PrimaryError("Noe powinno siê zdarzyæ \nklasa:BranchGenertaotr");
    }    
    okienko.setEditable(false);
    return okienko;    
  }
  
  protected static String encloseInTag(String tag, String Message){
    String foo = new String ();
    foo = "<" + tag + ">" + Message + "</" + tag + ">"; 
    return foo;
  }
}
