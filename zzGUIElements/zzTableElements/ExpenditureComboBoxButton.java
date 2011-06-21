/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzGUIElements.zzTableElements;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import zzMyDate.MyDate;
import zzProduct.Product;
import zzUser.Priveleges;
import zzUser.User;
;

public class ExpenditureComboBoxButton extends JButton 
  implements ActionListener{
  public static final String command = "ExpenditureComboBoxButton46878912348";
  public static final String popUpMenuNewCommand  =
    "ExpenditureComboBoxButtonpopUpMenuNew789797891798";
  public static final String popUpMenuChangeCommand  =
    "ExpenditureComboBoxButtonMenuChange7289797891798"; 
  public static final String popUpMenuDeleteCommand  =
    "ExpenditureComboBoxButtonMenuDelete7896797891798"; 
  JMenu menu;
  JPopupMenu popUpMenu;
  ExpenditureComboBox parent;
  Component componentMatka;
  Product owner;
  public ExpenditureComboBoxButton(
      ExpenditureComboBox e, Component c) {
    super("+");

    //setPreferredSize(new Dimension(22,18));
    setVisible(true);
    setActionCommand(command);
    parent = e;
    componentMatka = c;
    owner = e.owner;
    popUpMenu = makePopUpMenu();
  }
  public void actionPerformed(ActionEvent arg0) {
    if(!command.equals(arg0.getActionCommand())) return;
    // TODO Auto-generated method stub  
  }
  
  class menuSluchacz implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
      // TODO Auto-generated method stub
    }   
  }
  
  class popUpMenuSluchacz implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
      if(arg0.getActionCommand().equals(popUpMenuNewCommand)){
        System.out.println("New");
        JInternalFrame newFrame = new  JInternalFrame("Dodawanie wyjœcia z magazynu",
            true, true, false, false);   
        //TODO finish
      }
      if(arg0.getActionCommand().equals(popUpMenuChangeCommand)){
        System.out.println("Change");
      }
      if(arg0.getActionCommand().equals(popUpMenuDeleteCommand)){
        System.out.println("Delete");
      }
    }   
  }
  
  final JPopupMenu makePopUpMenu(){
    JPopupMenu menu = new JPopupMenu();
    
    JMenuItem item = new JMenuItem("Nowy");
    item.setActionCommand(popUpMenuNewCommand);
    item.addActionListener(new popUpMenuSluchacz());
    menu.add(item);
    if(User.getPriveleges().equals(Priveleges.ROOTUSER)){
      item = new JMenuItem("Edytuj");
      item.setActionCommand(popUpMenuChangeCommand);
      item.addActionListener(new popUpMenuSluchacz());
      menu.add(item);
     
      item = new JMenuItem("Usun");
      item.setActionCommand(popUpMenuDeleteCommand);
      item.addActionListener(new popUpMenuSluchacz());
      menu.add(item);
    }
    
    this.addMouseListener(new PopupListener(menu));    
    return menu;
  }
  
  class PopupListener extends MouseAdapter {
    JPopupMenu popup;

    PopupListener(JPopupMenu popupMenu) {
        popup = popupMenu;
    }

    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
  }
}
