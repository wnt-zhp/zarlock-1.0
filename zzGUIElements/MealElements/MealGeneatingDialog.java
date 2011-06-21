package zzGUIElements.MealElements;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;

import zzDataBase.Day;
import zzDataBase.Mealv3;
import zzDataBase.StaticContent;
import zzEx.ZZInternalDuplicateEntryEx;
import zzMyDate.MyDate;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.border.Border;

import sun.misc.Compare;

public class MealGeneatingDialog extends JDialog {

	private static DayGeneratingDialog dialog = new DayGeneratingDialog();
	
	private Mealv3 meal;
	
	public static int RESULT_OK = 0;
	public static int RESULT_ERROR= 1;
	
	private int result =1;
	
	
	private JPanel jContentPane = null;
	private JTextField nazwa = null;
	private JTextField dataField = null;
	private JTextField Opis = null;
	private JButton wykonaj = null;
	/**
	 * This is the default constructor
	 */
	public MealGeneatingDialog() {
		super();
		initialize();
	}
	
	public int display(){
		result = RESULT_ERROR;
		meal=null;
		getDataField().setEnabled(true);
		getDataField().setToolTipText("Podaj datê");
		getDataField().setText("");
		setVisible(true);
		return result;
	}
	
	public int display(MyDate date){
		result = RESULT_ERROR;
		meal=null;
		getDataField().setEnabled(false);
		getDataField().setToolTipText("A ja juz znam date :P");
		getDataField().setText(date.toString());
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
		this.setTitle("Nowy posilek");  // Generated
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
			jContentPane.add(getDataField(), gridBagConstraints1);  // Generated
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
			nazwa.setColumns(10);  // Generated
			nazwa.setToolTipText("Podaj nazwê posilku");  // Generated
			nazwa.setBorder(BorderFactory.createTitledBorder("Nazwa"));
		}
		return nazwa;
	}

	/**
	 * This method initializes dataField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDataField() {
		if (dataField == null) {
			dataField = new JTextField();
			dataField.setToolTipText("Podaj datê");  // Generated
			dataField.setBorder(BorderFactory.createTitledBorder("Data posi³ku"));
		}
		return dataField;
	}

	/**
	 * This method initializes Opis	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOpis() {
		if (Opis == null) {
			Opis = new JTextField();
			Opis.setText("");  // Generated
			Opis.setToolTipText("Podaj opis");  // Generated
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
			wykonaj.setText("Wykonaj");
			wykonaj.addActionListener(new ActionListener(){			
				public void actionPerformed(ActionEvent arg0) {
						String name = getNazwa().getText();
						MyDate date;
						try {
							 date = new MyDate (getDataField().getText().replace(",","."));
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(MealGeneatingDialog.this,
									"Z³y format daty" + e.getMessage(),
									"B³¹d",
									JOptionPane.ERROR_MESSAGE,
									null);
							e.printStackTrace();
							return;
						}
						String opis = getOpis().getText();
						if(name.length()==0){
							JOptionPane.showMessageDialog(MealGeneatingDialog.this,
									"Jednak dzien powinien mieæ jak¹c nazwê" +
									"Wpisz no np. Poniedzia³ek",
									"B³¹d",
									JOptionPane.ERROR_MESSAGE,
									null);
							return;
						}
						Day day = StaticContent.sc.getDay(date);
						try{
						if(day!=null){
							day.addMeal(name,opis);
						}else{
							int result = dialog.display(date);
							if(result == dialog.RESULT_OK){
								dialog.getDay().addMeal(name,opis);
							}
						}
						}catch(ZZInternalDuplicateEntryEx e){
							setVisible(false);
							JOptionPane.showMessageDialog(
									StaticContent.sc.getUppmostFrame(),
									"B³¹d posi³ki s¹ identyfikowane po nazwie\n" +
									"tj. mo¿e byæ jeden posi³ek o danej nazwie danego dnia",
									"B³ad",
									JOptionPane.WARNING_MESSAGE,
									null
									);
						}
						

							
							
							
	
						result = RESULT_OK;
						setVisible(false);				
				}
				
			});
		}
		return wykonaj;
	}

}
