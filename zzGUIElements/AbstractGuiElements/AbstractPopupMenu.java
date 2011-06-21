package zzGUIElements.AbstractGuiElements;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;




public class AbstractPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6271579322085229976L;

	SortedMap<PopupMenuItem, JMenuItem> contents 
		= new TreeMap<PopupMenuItem, JMenuItem>(new comp());
	
	private DefaultMutableTreeNode node;
	
	private DefaultTreeModel model;
	
	boolean hasHanged = true;
	
	public void addComponent(PopupMenuItem i){
		JMenuItem item = new JMenuItem(i.toString());
		item.addActionListener(new Listener(i));
		contents.put(i,item);
		hasHanged = true;		
	}
	
	public void removeComponent(AbstractPopupMenu i){
		contents.remove(i);
		hasHanged = true;
	}
	
	//public void removeComponent
	
	/**
	 * @deprecated U¿yj raczej show z obiektem co??
	 */
	public void show(Component c, int x, int y){
		if(contents.size()==0) return;
		this.removeAll();
		for(JMenuItem i : contents.values()){
			this.add(i);
		}
		super.show(c,x,y);
	}
	
	
	public void show (Component c, int x, int y, DefaultMutableTreeNode node, DefaultTreeModel model){
		if(contents.size()==0) return;
		this.removeAll();
		for(Map.Entry<PopupMenuItem, JMenuItem> i : contents.entrySet()){
			if(i.getKey().userCanPerformAction()) this.add(i.getValue());
		}		
		this.node = node;
		this.model = model;
		super.show(c,x,y);
		
	}
	



	/**
	 * @deprecated
	 * @param node
	 */
	public void setNode(DefaultMutableTreeNode node) {
		this.node = node;
	}

	public DefaultMutableTreeNode getNode() {
		return node;
	}


	private class Listener implements ActionListener{
		private PopupMenuItem item;
		
		public Listener(PopupMenuItem item) {
			super();
			this.item = item;
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				item.performAction(node);
			} catch (ZZInternalClassCastExceptionEx e) {
				StringWriter strwr = new StringWriter();
				PrintWriter prwr = new PrintWriter(strwr);
				e.printStackTrace(prwr);
				prwr.flush();
				JOptionPane.showMessageDialog(
						StaticContent.sc.getUppmostFrame(),
						"Powa¿ny b³¹d programisty. Wezwij go." +
						"Zapisz pracê i podaj mu debug info\n" +
						"DebugInfo:\n" +
						strwr, 
						"Powa¿ny b³¹d", JOptionPane.WARNING_MESSAGE);
			} catch (ZZIllegalEx e) {
				JOptionPane.showMessageDialog(
						StaticContent.sc.getUppmostFrame(),
						e.getDefaultMessage() + "\n"
						+ e.getMessage(),
						"B³¹d",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class comp implements Comparator<PopupMenuItem>{

		public int compare(PopupMenuItem arg0, PopupMenuItem arg1) {
			if(arg0.priority() > arg1.priority())return 1;
			if(arg0.priority() < arg1.priority())return -1;
			return 0;
		}
		
	}



}
