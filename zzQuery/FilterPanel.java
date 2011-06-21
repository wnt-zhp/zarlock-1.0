package zzQuery;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.crypto.provider.JceKeyStore;

import zzDataBase.StaticContent;
import zzEx.PrimaryError;
import zzEx.ZZIllegalWrongPatternEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;

public class FilterPanel extends JPanel {

	private JCheckBox jCheckBox = null;
	private JTextField jTextField = null;
	private Filter filter;
	private Component component;

	private FlavourHolder flav;
		
	public FilterPanel(Filter filter, Component component) {
		super();
		this.filter = filter;
		this.component = component;
		initialize();
	}
	

	/**
	 * This is the default constructor
	 * @deprecated for VE ONLY
	 */
	public FilterPanel() {
		super();
		initialize();
	}
	
	public Filter getFilter(){
		return filter;
	}
	
	public boolean  isSelected(){
		return getJCheckBox().isSelected();
	}
	
	public void setEnabled(boolean b){
		getJCheckBox().setEnabled(b);
	}

	public Query  getQuery(){
		return flav.getCurrentQuery();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		if(filter.isPreSetted()){
			flav = new PreSettedFlavour();
		}else{
			if(!filter.usesOperand()){
				flav = new NonOperandFlavour();
			}else{
				flav = new OperandFlavour();
			}
		}
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
		gridBagConstraints2.gridy = 0;  // Generated
		gridBagConstraints2.weightx = 1.0;  // Generated
		gridBagConstraints2.gridwidth = 2;  // Generated
		gridBagConstraints2.gridx = 0;  // Generated
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
		gridBagConstraints1.gridy = 1;  // Generated
		gridBagConstraints1.weightx = 1.0;  // Generated
		gridBagConstraints1.gridx = 1;  // Generated
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;  // Generated
		gridBagConstraints.gridy = 1;  // Generated
		this.setLayout(new GridBagLayout());  // Generated
		this.setSize(300, 200);
		this.add(getJCheckBox(), gridBagConstraints);  // Generated
		this.add(getJComboBox(), gridBagConstraints1);  // Generated
		this.add(getJTextField(), gridBagConstraints2);  // Generated
		this.setBorder(BorderFactory.createTitledBorder(filter.getName()));
	}

	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		return flav.getComboBox();
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setColumns(8);
		}
		return jTextField;
	}

	
	private interface FlavourHolder{
		Query getCurrentQuery();
		JComboBox getComboBox();
	}
	
   private class PreSettedFlavour implements FlavourHolder{		
		public JComboBox getComboBox() {
			try {
				return new JComboBox(filter.getDescriptionOfNot());
			} catch (ZZInternalUnsupportedOperationExceptionEx e) {
				e.printStackTrace();
				throw new PrimaryError(e.getMessage(),e);
			}
		}

		public Query getCurrentQuery() {
			try {
				String not = (String) getJComboBox().getSelectedItem();
				boolean bool = false;
				if(not.equals(filter.getDescriptionOfNot()[1])){
					bool = true;
				}else{
					if(!not.equals(filter.getDescriptionOfNot()[0]))
						throw new PrimaryError("foo");
				}
				return filter.getFilter(bool);
			} catch (ZZInternalUnsupportedOperationExceptionEx e) {
				e.printStackTrace();
				throw new PrimaryError(e.getMessage(),e);
			}
		}

		public PreSettedFlavour() {
			super();
			getJTextField().setEditable(false);
			getJTextField().setText(filter.getName());
		}
		
	}
	
	private abstract class AbstractFlavour implements FlavourHolder{
		
	}
	
	private class NonOperandFlavour extends AbstractFlavour{

		private JComboBox combo;
		
		public JComboBox getComboBox() {
			try {
				if(combo==null) combo = new JComboBox(filter.getDescriptionOfNot());
				return combo;
			} catch (ZZInternalUnsupportedOperationExceptionEx e) {
				e.printStackTrace();
				throw new PrimaryError("NonOperandFlavour");
			}
		}

		public Query getCurrentQuery() {
			try {
				String not = (String) getJComboBox().getSelectedItem();
				System.out.println(not);
				boolean bool = false;
				if(not.equals(filter.getDescriptionOfNot()[1])){
					bool = true;
				}else{
					if(!not.equals(filter.getDescriptionOfNot()[0]))
						throw new PrimaryError("foo");
				}
				return filter.getFilter(FilterPanel.this.getJTextField().getText(), bool);
			} catch (ZZInternalUnsupportedOperationExceptionEx e) {
				e.printStackTrace();
				throw new PrimaryError(e.getMessage(),e);
			} catch (ZZIllegalWrongPatternEx e){
				Component owner;
				if(component==null){
					owner = StaticContent.sc.getUppmostFrame();
				}else{
					owner = component;
				}
				JOptionPane.showMessageDialog(owner,
						"Z³y format zapytania\n"+
						e.getMessage(),
						"B³¹d",
						JOptionPane.WARNING_MESSAGE
						);
				return null;
			}
		}		
	}
	
	private class OperandFlavour extends AbstractFlavour{
		
		private JComboBox combo;
		
		private Operand[] oper = new Operand[]{
				Operand.EQUAL,
				Operand.NOT_EQUAL,
				Operand.GREATER,
				Operand.GREATER_OR_EQUAL,
				Operand.SMALLER,
				Operand.SMALLER_OR_EQUAL
			};
		
		public JComboBox getComboBox() {
			if(combo==null) combo = new JComboBox(oper);
			return combo;
			
		}

		public Query getCurrentQuery() {
			try {
				Operand oper1 =(Operand) FilterPanel.this.getJComboBox().getSelectedItem();				
				return filter.getFilter(FilterPanel.this.getJTextField().getText(),oper1);
			} catch (ZZInternalUnsupportedOperationExceptionEx e) {
				e.printStackTrace();
				throw new PrimaryError(e.getMessage(),e);
			} catch (ZZIllegalWrongPatternEx e){
				Component owner;
				if(component==null){
					owner = StaticContent.sc.getUppmostFrame();
				}else{
					owner = component;
				}
				JOptionPane.showMessageDialog(owner,
						"Z³y format zapytania\n"+
						e.getMessage(),
						"B³¹d",
						JOptionPane.WARNING_MESSAGE
						);
				return null;
			}
		}
	}
	

}
