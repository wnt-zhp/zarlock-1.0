package zzGUIElements.MealElements;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;

import zzDataBase.Day;
import zzEx.ZZInternalDuplicateEntryEx;
import zzMyDate.MyDate;

public class DayGeneratingDialog extends JDialog {

	private JPanel jContentPane = null;
	private JTextField nazwa = null;
	private JTextField data = null;
	private JTextField Opis = null;
	private JButton wykonaj = null;
	
	private String[] nazwy = new String[]{
			"Brand new day",
			"Nowy dzieñ wstaje",
			"Chyli siê dzien do kresu",
			"Dzien jak codzien"	};
	private Random rand = new Random(new Date().hashCode());
	
	private Day day;
	
	public static int RESULT_OK = 0;
	public static int RESULT_ERROR= 1;
	
	private int result =1;
	

	/**
	 * This is the default constructor
	 */
	public DayGeneratingDialog() {
		super();
		initialize();
	}
	
	public int display(){
		this.setTitle(nazwy[rand.nextInt(nazwy.length)]);
		result = RESULT_ERROR;
		day=null;
		getData().setEnabled(true);
		getData().setToolTipText("Podaj datê");
		getData().setText("");
		setVisible(true);
		return result;
	}
	
	public int display(MyDate date){
		this.setTitle(nazwy[rand.nextInt(nazwy.length)]);
		result = RESULT_ERROR;
		day=null;
		getData().setEnabled(false);
		getData().setToolTipText("A ja juz znam date :P");
		getData().setText(date.toString());
		setVisible(true);
		return result;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Nowy dzien wstaje ;-)");  // Generated
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;  // Generated
			gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints3.gridy = 3;  // Generated
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints2.gridy = 2;  // Generated
			gridBagConstraints2.weightx = 1.0;  // Generated
			gridBagConstraints2.gridx = 0;  // Generated
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints1.gridy = 1;  // Generated
			gridBagConstraints1.weightx = 1.0;  // Generated
			gridBagConstraints1.gridx = 0;  // Generated
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints.gridy = 0;  // Generated
			gridBagConstraints.weightx = 1.0;  // Generated
			gridBagConstraints.gridx = 0;  // Generated
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getNazwa(), gridBagConstraints);  // Generated
			jContentPane.add(getData(), gridBagConstraints1);  // Generated
			jContentPane.add(getOpis(), gridBagConstraints2);  // Generated
			jContentPane.add(getWykonaj(), gridBagConstraints3);  // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes nazwa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNazwa() {
		if (nazwa == null) {
			nazwa = new JTextField();
			nazwa.setBorder(BorderFactory.createTitledBorder("Nazwa"));
			nazwa.setToolTipText("Podaj nazwe");  // Generated
		}
		return nazwa;
	}

	/**
	 * This method initializes data	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getData() {
		if (data == null) {
			data = new JTextField();
			data.setBorder(BorderFactory.createTitledBorder("Data"));
		}
		return data;
	}

	/**
	 * This method initializes Opis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOpis() {
		if (Opis == null) {
			Opis = new JTextField();
			Opis.setBorder(BorderFactory.createTitledBorder("Opis"));
		}
		return Opis;
	}

	/**
	 * This method initializes wykonaj	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getWykonaj() {
		if (wykonaj == null) {
			wykonaj = new JButton();
			wykonaj.setText("Wykonaj");  // Generated
			wykonaj.addActionListener(new ActionListener(){			
				public void actionPerformed(ActionEvent arg0) {
						String name = getNazwa().getText();
						MyDate date;
						try {
							 date = new MyDate (getData().getText().replace(",","."));
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(DayGeneratingDialog.this,
									"Z³y format daty" + e.getMessage(),
									"B³¹d",
									JOptionPane.ERROR_MESSAGE,
									null);
							e.printStackTrace();
							return;
						}
						String opis = getOpis().getText();
						if(name.length()==0){
							JOptionPane.showMessageDialog(DayGeneratingDialog.this,
									"Jednak dzien powinien mieæ jak¹c nazwê" +
									"Wpisz no np. Poniedzia³ek",
									"B³¹d",
									JOptionPane.ERROR_MESSAGE,
									null);
							return;
						}
						try {
							day = new Day(name, date,opis);
						} catch (ZZInternalDuplicateEntryEx e) {
							JOptionPane.showMessageDialog(DayGeneratingDialog.this,
									"Juz istnieje dzien " + date.toString() +
									"Ja rozumiem ¿e ka¿dy dzieñ na obozie, liczy siê jakby podwójnie...," +
									"Ale prawda czasu - prawda ekranu :). Mo¿e byæ jeden obiekt klasy dzien, " +
									"Maj¹cy konkretn¹ date!!",
									"B³¹d",
									JOptionPane.ERROR_MESSAGE,
									null);
							e.printStackTrace();
							return;
						}
						result = RESULT_OK;
						setVisible(false);				
				}
				
			});
		}
		return wykonaj;
	}

	public Day getDay() {
		return day;
	}

	
}
