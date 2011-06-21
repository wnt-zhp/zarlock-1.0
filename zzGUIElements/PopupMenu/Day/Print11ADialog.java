package zzGUIElements.PopupMenu.Day;

import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import zzDataBase.Day;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.File;

public class Print11ADialog extends JDialog {
	
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel jLabel = null;
	private TableModel model = null;
	private JButton OK = null;

	private int chooserResult;
	private JPanel UczesticyPanel = null;
	private JLabel UczestnnicyLabel = null;
	private JTextField dataTestFirld = null;
	private JTextField NaglowekTextField = null;
	private JTextField IloscUczestnikowTextFiels = null;
	private JPanel kadraPanel = null;
	private JLabel kadraLabel = null;
	private JTextField kadraField = null;
	private JPanel inntPanel = null;
	private JLabel inniLabel = null;
	private JTextField inniField = null;
	private JPanel jPanel = null;
	private JPanel plik = null;
	private JLabel plikLabel = null;
	private JTextField plikField = null;
	private JButton plikButton = null;
	private JPanel jPanel1 = null;
	private JButton anuluj = null;
	
	private Vector<Boolean> boolVect = new Vector<Boolean>();
	private Vector<String> namesVect = new Vector<String>();
	private Vector<Integer> intVect = new Vector<Integer>(3);
	private int result;
	private JFileChooser chooser = new JFileChooser();
	
	public static final int OK_RESULT = 0;
	public static final int WRONG_RESULT = 0;
	
	public int display(Day day){
		namesVect.clear();
		boolVect.clear();
		intVect.clear();
		chooserResult = JFileChooser.CANCEL_OPTION;
		getPlikField().setText("");
		result = WRONG_RESULT;
		getNaglowekTextField().setText("Podaj dane dla daty: " + day.getDate());
		
		for(Mealv3 m: day.getMeals()){
			boolVect.add(false);
			namesVect.add(m.getName());
		}
		pack();
		
		setVisible(true);
		return result;
		
		
	}
	
	public File getFile(){
		return chooser.getSelectedFile();
	}
	
	/**
	 * This is the default constructor
	 */
	public Print11ADialog() {
		super();
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new FileFilter(){
			

			@Override
			public boolean accept(File f) {
				if(!f.exists())return false;
				if(f.isDirectory())return true;
				if(!f.getName().endsWith(".txt")) return false;
				return true;
			}

			@Override
			public String getDescription() {
				return "Plik tekstowy";
			}
			
		});
		setModal(true);
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
		this.setModal(true);
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.gridx = 0;  // Generated
			gridBagConstraints81.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints81.gridy = 4;  // Generated
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.gridx = 0;  // Generated
			gridBagConstraints71.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints71.gridy = 3;  // Generated
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints4.gridy = 2;  // Generated
			gridBagConstraints4.weightx = 1.0;  // Generated
			gridBagConstraints4.gridx = 0;  // Generated
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;  // Generated
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints1.gridy = 0;  // Generated
			jLabel = new JLabel();
			jLabel.setText("Wybierz te \"dodatkowe\" posi³ki");// Generated
			jLabel.setToolTipText("Tak jak we wzorze 13A");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;  // Generated
			gridBagConstraints.gridy = 1;  // Generated
			gridBagConstraints.weightx = 1.0;  // Generated
			gridBagConstraints.weighty = 1.0;  // Generated
			gridBagConstraints.gridx = 0;  // Generated
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints);  // Generated
			jContentPane.add(jLabel, gridBagConstraints1);  // Generated
			jContentPane.add(getNaglowekTextField(), gridBagConstraints4);  // Generated
			jContentPane.add(getJPanel(), gridBagConstraints71);  // Generated
			jContentPane.add(getJPanel1(), gridBagConstraints81);  // Generated
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());  // Generated
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(getModel());
			TableColumn firstColumn = jTable.getColumnModel().getColumn(0);
			firstColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
			
		}
		return jTable;
	}
	
	private TableModel getModel() {
		if(model == null){
			model = new AbstractTableModel(){
				
				public int getRowCount() {
					return namesVect.size();
				}
				
				public int getColumnCount() {
					return 2;
				}
				
				public Object getValueAt(int row, int col) {
					if(col==0){
						return boolVect.get(row);
					}else{
						return namesVect.get(row);
					}
				}
				
				@Override
				public Class<?> getColumnClass(int col) {
					if(col==0){
						return Boolean.class;
					}
					else{
						return String.class;
					} 
				}
				
				@Override
				public String getColumnName(int col) {
					if(col==0){
						return "Czy dodatkowy?";
					}
					else{
						return "nazwa";
					} 
				}
				
				@Override
				public boolean isCellEditable(int row, int col) {
					if(col==0){
						return true;
					}
					else{
						return false;
					} 
				}
				
				@Override
				public void setValueAt(Object arg0, int row , int col) {
					boolVect.setElementAt((Boolean) arg0,row);
				}
				
				
				
			};
		}
		
		
		return model;
	}
	
	/**
	 * This method initializes OK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOK() {
		if (OK == null) {
			OK = new JButton();
			OK.setText("OK");
			OK.addActionListener(
					new ActionListener(){
						
						public void actionPerformed(ActionEvent arg0) {
							try{
								Integer u = new Integer((int) Float.parseFloat(getIloscUczestnikowTextFiels().getText()));
								Integer k = new Integer((int) Float.parseFloat(getKadraField().getText()));
								Integer i = new Integer((int) Float.parseFloat(getInniField().getText()));
								intVect.add(u);
								intVect.add(k);
								intVect.add(i);
							}catch(NumberFormatException e){
								JOptionPane.showMessageDialog(
										StaticContent.sc.getUppmostFrame(),
										"Jedna z liczb jest Ÿle wprowadzona",
										"B³¹d",
										JOptionPane.WARNING_MESSAGE,
										null);
								return;
							}
							if(chooserResult == JFileChooser.APPROVE_OPTION){
								result = OK_RESULT;
								setVisible(false);
							}else{
								JOptionPane.showMessageDialog(
										StaticContent.sc.getUppmostFrame(),
										"Wybierz plik",
										"B³¹d",
										JOptionPane.WARNING_MESSAGE,
										null);
							}
							
						}
						
					});
		}
		return OK;
		}
		
		/**
		 * This method initializes UczesticyPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getUczesticyPanel() {
			if (UczesticyPanel == null) {
				GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
				gridBagConstraints9.gridx = 0;  // Generated
				gridBagConstraints9.gridy = 0;  // Generated
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
				gridBagConstraints5.gridy = 0;  // Generated
				gridBagConstraints5.weightx = 1.0;  // Generated
				gridBagConstraints5.gridx = 1;  // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
				gridBagConstraints2.gridx = 2;  // Generated
				gridBagConstraints2.gridy = 0;  // Generated
				gridBagConstraints2.weightx = 1.0;  // Generated
				UczestnnicyLabel = new JLabel();
				UczestnnicyLabel.setText("Ilosc Uczestników");  // Generated
				UczesticyPanel = new JPanel();
				UczesticyPanel.setLayout(new GridBagLayout());  // Generated
				UczesticyPanel.add(UczestnnicyLabel, gridBagConstraints9);  // Generated
				UczesticyPanel.add(getIloscUczestnikowTextFiels(), gridBagConstraints5);  // Generated
				
			}
			return UczesticyPanel;
		}
		
		
		/**
		 * This method initializes NaglowekTextField	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getNaglowekTextField() {
			if (NaglowekTextField == null) {
				NaglowekTextField = new JTextField();
				NaglowekTextField.setEditable(false);
			}
			return NaglowekTextField;
		}
		
		/**
		 * This method initializes IloscUczestnikowTextFiels	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getIloscUczestnikowTextFiels() {
			if (IloscUczestnikowTextFiels == null) {
				IloscUczestnikowTextFiels = new JTextField();
			}
			return IloscUczestnikowTextFiels;
		}
		
		/**
		 * This method initializes kadraPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getKadraPanel() {
			if (kadraPanel == null) {
				GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
				gridBagConstraints6.gridx = 1;  // Generated
				gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
				gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;  // Generated
				gridBagConstraints6.weightx = 1.0;  // Generated
				gridBagConstraints6.gridy = 0;  // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridy = 0;  // Generated
				gridBagConstraints3.gridx = 0;  // Generated
				kadraLabel = new JLabel();
				kadraLabel.setText("Liczba kadry obozowej");  // Generated
				kadraPanel = new JPanel();
				kadraPanel.setLayout(new GridBagLayout());  // Generated
				kadraPanel.add(kadraLabel, gridBagConstraints3);  // Generated
				kadraPanel.add(getKadraField(), gridBagConstraints6);  // Generated
			}
			return kadraPanel;
		}
		
		/**
		 * This method initializes kadraField	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getKadraField() {
			if (kadraField == null) {
				kadraField = new JTextField();
			}
			return kadraField;
		}
		
		/**
		 * This method initializes inntPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getInntPanel() {
			if (inntPanel == null) {
				GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
				gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
				gridBagConstraints8.gridy = 0;  // Generated
				gridBagConstraints8.weightx = 1.0;  // Generated
				gridBagConstraints8.gridx = 1;  // Generated
				GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
				gridBagConstraints7.gridy = 0;  // Generated
				gridBagConstraints7.gridx = 0;  // Generated
				inniLabel = new JLabel();
				inniLabel.setText("Inne osoby");  // Generated
				inntPanel = new JPanel();
				inntPanel.setLayout(new GridBagLayout());  // Generated
				inntPanel.add(inniLabel, gridBagConstraints7);  // Generated
				inntPanel.add(getInniField(), gridBagConstraints8);  // Generated
			}
			return inntPanel;
		}
		
		/**
		 * This method initializes inniField	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getInniField() {
			if (inniField == null) {
				inniField = new JTextField();
			}
			return inniField;
		}
		
		/**
		 * This method initializes jPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanel() {
			if (jPanel == null) {
				GridLayout gridLayout = new GridLayout();
				gridLayout.setRows(4);  // Generated
				jPanel = new JPanel();
				jPanel.setLayout(gridLayout);  // Generated
				jPanel.add(getUczesticyPanel(), null);  // Generated
				jPanel.add(getInntPanel(), null);  // Generated
				jPanel.add(getKadraPanel(), null);  // Generated
				jPanel.add(getPlik(), null);  // Generated
			}
			return jPanel;
		}

		/**
		 * This method initializes plik	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getPlik() {
			if (plik == null) {
				GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
				gridBagConstraints13.gridx = 2;  // Generated
				gridBagConstraints13.gridy = 0;  // Generated
				GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
				gridBagConstraints12.gridx = 1;  // Generated
				gridBagConstraints12.weightx = 1.0;  // Generated
				gridBagConstraints12.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
				gridBagConstraints12.gridy = 0;  // Generated
				GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
				gridBagConstraints10.gridy = 0;  // Generated
				gridBagConstraints10.gridx = 0;  // Generated
				plikLabel = new JLabel();
				plikLabel.setText("Zapisz do:");  // Generated
				plik = new JPanel();
				plik.setLayout(new GridBagLayout());  // Generated
				plik.add(plikLabel, gridBagConstraints10);  // Generated
				plik.add(getPlikField(), gridBagConstraints12);  // Generated
				plik.add(getPlikButton(), gridBagConstraints13);  // Generated
			}
			return plik;
		}

		/**
		 * This method initializes plikField	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getPlikField() {
			if (plikField == null) {
				plikField = new JTextField();
				plikField.setEditable(false);
			}
			return plikField;
		}

		/**
		 * This method initializes plikButton	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getPlikButton() {
			if (plikButton == null) {
				plikButton = new JButton();
				plikButton.setText("Przegl¹daj");  // Generated
				plikButton.addActionListener(
						new ActionListener(){
							
							public void actionPerformed(ActionEvent arg0) {
								chooserResult = chooser.showSaveDialog(StaticContent.sc.getUppmostFrame());
								plikField.setText(chooser.getSelectedFile()!=null ? chooser.getSelectedFile().getAbsolutePath() : "blad");
								plikField.setEditable(false);
								
							}
							
						});
			}
			return plikButton;
		}

		/**
		 * This method initializes jPanel1	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanel1() {
			if (jPanel1 == null) {
				jPanel1 = new JPanel();
				jPanel1.add(getOK(), null);  // Generated
				jPanel1.add(getAnuluj(), null);  // Generated
			}
			return jPanel1;
		}

		/**
		 * This method initializes anuluj	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getAnuluj() {
			if (anuluj == null) {
				anuluj = new JButton();
				anuluj.setText("Anuluj");  // Generated
				anuluj.addActionListener(
						new ActionListener(){

							public void actionPerformed(ActionEvent arg0) {
								result = WRONG_RESULT;
								setVisible(false);
								
								
							}
							
						});
			
				
				
			}
			return anuluj;
		}

		public Vector<Boolean> getBoolVect() {
			return boolVect;
		}

		public Vector<Integer> getIntVect() {
			return intVect;
		}
		
	}
