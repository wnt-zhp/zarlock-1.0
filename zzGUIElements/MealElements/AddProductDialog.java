package zzGUIElements.MealElements;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import zzDataBase.Result;
import zzDataBase.StaticContent;
import zzEx.PrimaryError;
import zzGUIElements.ZZTreeElements.IleTreeData;
import zzId.ProdId;
import zzMyDate.MyDate;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable;
import zzQuery.AbstractFilter;
import zzQuery.DateValidFilter;
import zzQuery.DateValidFilterv2;
import zzQuery.Filter;
import zzQuery.FilterPanel;
import zzQuery.Query;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddProductDialog extends JDialog {

	private JPanel ileWydacPanel = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel FiltryPanel = null;

	private JComboBox jComboBox = null;

	private JButton remove = null;

	private JButton add = null;

	private Vector<FilterPanel> panele;

	private Vector<String> nazwy;

	private MealProductTable mealProductTable = null;

	private MealProductTableModel model = null;

	private ProductBaseImmutable base = null;

	private ProductBaseImmutable baseFiltered = null;

	private Result result = Result.USER_ABORTED;

	private Product resultProduct = null;

	private float resultQuantity = -1;

	private JLabel jLabel = null;

	private JTextField ileField = null;

	private JPanel jContentPane = null;

	private JPanel jPanel2 = null;
	
	private Filter dateFilter = null;
	
	private Filter dateValidFilter = null;
	
	private DefaultComboBoxModel comboModel;
	
	private CardLayout layout = null;

	public AddProductDialog(ProductBaseImmutable base) throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		this.base = base;
		initialize();
	}

	public Result display(Component ovner) {
		result = Result.USER_ABORTED;
		resultProduct = null;
		resultQuantity = -1;
		getIleField().setBackground(Color.WHITE);
		this.setModal(true);
		this.pack();
		this.setVisible(true);
        getIleField().setText("");

		return result;
	}
	
	public Result display(Component ovner, MyDate date) {
		result = Result.USER_ABORTED;
		resultProduct = null;
		resultQuantity = -1;
		getIleField().setBackground(Color.WHITE);
		FilterPanel fp = new FilterPanel(new DateValidFilterv2(date.toString()),this);
		
		getNazwy();
			
		getComboModel().addElement(fp.getFilter().getName());
		//getNazwy().add(fp.getFilter().getName());
		FiltryPanel.add(fp,fp.getFilter().getName());	
		getPanele().add(fp);
		
		this.setModal(true);
		this.pack();
		this.setVisible(true);
		getIleField().setText("");
		FiltryPanel.remove(fp);		
		getComboModel().removeElement(fp.getName());
		getNazwy().remove(fp.getFilter().getName());
		getPanele().remove(fp);
		return result;
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	public Product getSelectedProduct() {
		return resultProduct;
	}

	/**
	 * This is the default constructor
	 */
	public AddProductDialog() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());  // Generated
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getIleWydacPanel() {
		if (ileWydacPanel == null) {
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
			gridBagConstraints41.gridy = 4; // Generated
			gridBagConstraints41.weightx = 1.0; // Generated
			gridBagConstraints41.gridx = 3; // Generated
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 3; // Generated
			gridBagConstraints31.gridheight = 1; // Generated
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints31.gridy = 2; // Generated
			jLabel = new JLabel();
			jLabel.setText("Ile wydac?"); // Generated
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridwidth = 4; // Generated
			gridBagConstraints11.gridy = 0; // Generated
			gridBagConstraints11.gridx = 0; // Generated
			ileWydacPanel = new JPanel();
			ileWydacPanel.setLayout(new GridBagLayout());
			ileWydacPanel.add(jLabel, gridBagConstraints31);  // Generated
			ileWydacPanel.add(getIleField(), gridBagConstraints41); // Generated
		}
		return ileWydacPanel;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridLayout(1, 0));
			//jPanel.setBackground(java.awt.Color.green);  // Generated
			//jPanel.setBorder(BorderFactory.createTitledBorder("Foo"));
			jPanel.add(getMealProductTable(), null);  // Generated
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;  // Generated
			gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
			gridBagConstraints7.gridwidth = 3;  // Generated
			gridBagConstraints7.gridy = 0;  // Generated
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;  // Generated
			gridBagConstraints2.gridx = -1;  // Generated
			gridBagConstraints2.gridy = -1;  // Generated
			gridBagConstraints2.gridheight = 4;  // Generated
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2; // Generated
			gridBagConstraints5.gridy = 1; // Generated
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3; // Generated
			gridBagConstraints4.gridy = 1; // Generated
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL; // Generated
			gridBagConstraints3.gridy = 2; // Generated
			gridBagConstraints3.gridx = 2; // Generated
			gridBagConstraints3.gridwidth = 2; // Generated
			gridBagConstraints3.gridheight = 1; // Generated
			gridBagConstraints3.weightx = 1.0; // Generated
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout()); // Generated
			jPanel1.add(getJComboBox(), gridBagConstraints3); // Generated
			jPanel1.add(getRemove(), gridBagConstraints4); // Generated
			jPanel1.add(getAdd(), gridBagConstraints5); // Generated
			jPanel1.add(getIleWydacPanel(), gridBagConstraints7);  // Generated
		}
		return jPanel1;
	}

	/**
	 * This method initializes FiltryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFiltryPanel() {
		if (FiltryPanel == null) {
			FiltryPanel = new JPanel();
			//FiltryPanel.setMinimumSize(new Dimension(100,100));
			//FiltryPanel.setBorder(BorderFactory.createTitledBorder("Foo3"));
			FiltryPanel.setLayout(new CardLayout()); // Generated
			for (int i = 0; i < getPanele().size(); i++) {
				FiltryPanel.add(getPanele().get(i), getNazwy().get(i));

			}
		}
		return FiltryPanel;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox(getComboModel());
			jComboBox.setName("jComboBox"); // Generated
			jComboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					CardLayout deck = (CardLayout) getFiltryPanel().getLayout();
					String name = (String) arg0.getItem();
					deck.show(getFiltryPanel(), name);
				}

			});
			jComboBox.setSelectedIndex(0);
		}
		return jComboBox;
	}

	/**
	 * This method initializes remove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemove() {
		if (remove == null) {
			remove = new JButton();
			remove.setText("Usun filtry"); // Generated
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setBaseFiltered(getBase());
				}

			});
		}
		return remove;
	}

	/**
	 * This method initializes add	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd() {
		if (add == null) {
			add = new JButton();
			add.setText("Dodaj filtr"); // Generated
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String selected = (String) getJComboBox().getSelectedItem();
					FilterPanel fp = getPanele().get(
							getNazwy().indexOf(selected));
					Query q = fp.getQuery();
					setBaseFiltered(getBaseFiltered().performQuery(q));
				}
			});
		}
		return add;
	}

	public Vector<String> getNazwy() {
		if (nazwy == null) {
			nazwy = new Vector<String>(AbstractFilter.allFilters.size(), 1);
			for (Filter f : AbstractFilter.allFilters) {
				nazwy.add(f.getName());
			}
		}
		return nazwy;
	}

	public Vector<FilterPanel> getPanele() {
		if (panele == null) {
			panele = new Vector<FilterPanel>(AbstractFilter.allFilters.size(), 1);
			
			for (Filter f : AbstractFilter.allFilters) {
				panele.add(new FilterPanel(f, this));
			}
		}
		return panele;
	}

	/**
	 * This method initializes mealProductTable	
	 * 	
	 * @return zzGUIElements.MealElements.MealProductTable	
	 */
	private MealProductTable getMealProductTable() {
		if (mealProductTable == null) {
			mealProductTable = new MealProductTable(getModel());
			mealProductTable.setList(new MealProductTable.Listener() {
				public void addProduct(ProdId id) {
					boolean ok = false;
					float ile = -1;
					try {
						ile = Float.parseFloat(getIleField().getText().replace(",", "."));
						if (ile >= 0) {
							ok = true;
						}
					} catch (NumberFormatException e) {

					} finally {
						if (ok) {
							resultProduct = StaticContent.sc.getDataBase().getById(id);
							resultQuantity = ile;
							getIleField().setText("" + resultQuantity);
							result = Result.OK;
							setVisible(false);
						} else {
							getIleField().setBackground(Color.PINK);
						}
					}

				}
			});
		}
		return mealProductTable;
	}

	public MealProductTableModel getModel() {
		if (model == null) {
			model = new MealProductTableModel(getBaseFiltered(), this);
		}
		return model;
	}

	public ProductBaseImmutable getBase() {
		if (base == null)
			throw new PrimaryError("B³¹d", new NullPointerException());
		return base;
	}

	public ProductBaseImmutable getBaseFiltered() {
		if (baseFiltered == null)
			baseFiltered = getBase();
		return baseFiltered;
	}

	public void setBaseFiltered(ProductBaseImmutable baseFiltered) {
		this.baseFiltered = baseFiltered;
		model.setBase(baseFiltered);
	}

	/**
	 * This method initializes ileField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIleField() {
		if (ileField == null) {
			ileField = new JTextField();
			ileField.setColumns(4); // Generated
		}
		return ileField;
	}

	public float getResultQuantity() {
		return resultQuantity;
	}

	public Result getResult() {
		return result;
	}

	/**
	 * This method initializes contentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;  // Generated
			gridBagConstraints1.gridy = 1;  // Generated
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;  // Generated
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());  // Generated
			jContentPane.add(getJPanel(), gridBagConstraints);  // Generated
			jContentPane.add(getJPanel2(), gridBagConstraints1);  // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = -1;  // Generated
			gridBagConstraints6.gridy = -1;  // Generated
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());  // Generated
			jPanel2.add(getFiltryPanel(), gridBagConstraints6);  // Generated
			jPanel2.add(getJPanel1(), new GridBagConstraints());  // Generated
			//jPanel.setBackground(java.awt.Color.red);  // Generated
			//jPanel.setBorder(BorderFactory.createTitledBorder("Foo"));
		}
		return jPanel2;
	}

	private DefaultComboBoxModel getComboModel() {
		if(comboModel==null){
			comboModel = new DefaultComboBoxModel(getNazwy());
		}
		return comboModel;
	}


	

}
