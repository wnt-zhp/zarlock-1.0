package zzTests;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable;
import zzProductBase.SimpleProductBase;
import zzQuery.FilterPanel;
import zzQuery.NameFilter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class FilterPanelTests extends JFrame {

	private JPanel jContentPane = null;
	private FilterPanel fp =null ;
	private JButton jButton = null;
	private JTextPane jTextPane = null;
	private ProductBaseImmutable base;
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public FilterPanelTests() throws Exception{
		super();
		zzTestMain.setUpDataBase(10);
		for(Product p: StaticContent.sc.getDataBase().getSorted()){
			getJTextPane().setText(getJTextPane().getText() + "\n" + p.getName());
		}
		base =StaticContent.sc.getDataBase();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;  // Generated
			gridBagConstraints2.gridy = 1;  // Generated
			gridBagConstraints2.weightx = 1.0;  // Generated
			gridBagConstraints2.weighty = 1.0;  // Generated
			gridBagConstraints2.gridwidth = 3;  // Generated
			gridBagConstraints2.gridx = 0;  // Generated
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 2;  // Generated
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;  // Generated
			gridBagConstraints1.gridy = 0;  // Generated
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridy = 0;  // Generated
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;  // Generated
			gridBagConstraints.weightx = 1.0;  // Generated
			gridBagConstraints.gridx = 0;  // Generated
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());  // Generated
			jContentPane.add(getFP(), gridBagConstraints);  // Generated
			jContentPane.add(getJButton(), gridBagConstraints1);  // Generated
			jContentPane.add(getJScrollPane(), gridBagConstraints2);  // Generated
		}
		return jContentPane;
	}
	
	private FilterPanel getFP(){
		if(fp == null) fp = new FilterPanel(new NameFilter(), this);
		return fp;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("add");
			jButton.setText("Add");
			jButton.setMaximumSize(jButton.getPreferredSize());
			jButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(getFP().isSelected()){
						try {
							getJTextPane().setText(getJTextPane().getText() + "\n" + "...:::(((000)))):::...");
							base = base.performQuery(getFP().getQuery());
							for(Product p: base.getSorted()){
								getJTextPane().setText(getJTextPane().getText() + "\n" + p.getName());
							}
						} catch (ZZIllegalEx e) {
							getJTextPane().setText(getJTextPane().getText() + "\n" + "B£¥D");
							e.printStackTrace();
						}
					}
					
				}
				
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			
			
		}
		return jTextPane;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextPane());  // Generated
		}
		return jScrollPane;
	}

	public static void main(String args[])throws Exception{
		new FilterPanelTests().setVisible(true);
	}
}
