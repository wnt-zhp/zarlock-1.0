/*
 * Created on 2006-05-20
 * Author jb
 *
 */
package zzTests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;;

public class JButtonTests extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  JButton b= new JButton("PushMe");
  public JButtonTests() {
    super();
    b.addActionListener(new lst());
    b.setActionCommand("bardzo dluga nazwa maj¹ca na celu zminimalizowanie zderzen");
    add(b);
    pack();
    setVisible(true);
    
  }
  
  class lst implements ActionListener{

    public void actionPerformed(ActionEvent arg0) {
      System.out.println(arg0.getActionCommand() + ", " + arg0.getSource() + 
          ", " + arg0.getID());
      
    }
    
  }
  
  public static void main(String[] a){
    new JButtonTests();
  }

}
