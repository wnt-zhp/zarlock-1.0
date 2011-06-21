package zzGUIElements.zzGuiProductElements;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import sun.misc.FpUtils;

import zzQuery.AbstractFilter;
import zzQuery.Filter;
import zzQuery.FilterPanel;
import zzQuery.Query;

public class FilterPanelSquare extends JPanel {

	private JPanel ButtonPanel = null;
	private JScrollPane Scroll = null;
	private JPanel allFilterPanels = null;
	private JButton Wykonaj = null;
	private JButton reset = null;
	private Component owner;
	private Vector<FilterPanel> panels;
	private ProductTableModel model;

	/**
	 * This is the default constructor
	 */
	public FilterPanelSquare() {
		super();
		initialize();
	}

	
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(
				getWykonaj().getWidth()+getReset().getWidth() + 20, 
				Math.max(getWykonaj().getHeight(),getReset().getHeight()) +50
		);
	}



	public FilterPanelSquare(Component owner, ProductTableModel model) {
		this.owner = owner;
		this.model = model;
		initialize();
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;  // Generated
		gridBagConstraints4.gridy = 0;  // Generated
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;  // Generated
		gridBagConstraints3.gridx = 0;  // Generated
		gridBagConstraints3.gridy = 1;  // Generated
		gridBagConstraints3.fill = java.awt.GridBagConstraints.NONE;  // Generated
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;  // Generated
		gridBagConstraints1.gridy = 0;  // Generated
		gridBagConstraints1.weightx = 1.0;  // Generated
		gridBagConstraints1.weighty = 1.0;  // Generated
		gridBagConstraints1.gridx = 0;  // Generated
		this.setLayout(new GridBagLayout());  // Generated
		this.setSize(300, 200);
		this.add(getScroll(), gridBagConstraints1);  // Generated
		this.add(getButtonPanel(), gridBagConstraints3);  // Generated
		this.add(getAllFilterPanels(), gridBagConstraints4);  // Generated
	}

	/**
	 * This method initializes ButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonPanel() {
		if (ButtonPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;  // Generated
			gridBagConstraints2.gridy = 0;  // Generated
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;  // Generated
			gridBagConstraints.gridy = 0;  // Generated
			ButtonPanel = new JPanel();
			ButtonPanel.setLayout(new GridBagLayout());  // Generated
			ButtonPanel.add(getReset(), gridBagConstraints2);  // Generated
			ButtonPanel.setMinimumSize(
					new Dimension(
							getWykonaj().getWidth()+getReset().getWidth(), 
							Math.max(getWykonaj().getHeight(),getReset().getHeight())
					)			
			);
			ButtonPanel.add(getWykonaj(), gridBagConstraints);  // Generated
		}
		return ButtonPanel ;
	}

	/**
	 * This method initializes Scroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScroll() {
		if (Scroll == null) {
			Scroll = new JScrollPane();
			Scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  // Generated
			Scroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  // Generated
		}
		return Scroll;
	}

	/**
	 * This method initializes allFilterPanels	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAllFilterPanels() {
		if (allFilterPanels == null) {
			GridLayout gridLayout = new GridLayout(1,1);
			int elems = getPanels().size();
			int cols = (int) Math.ceil(elems / 2.0f);
			int rows = (int) Math.ceil(elems/(float)cols);
			gridLayout.setRows(rows);
			gridLayout.setColumns(cols);  // Generated
			allFilterPanels = new JPanel();
			allFilterPanels.setLayout(gridLayout);  // Generated
			for(FilterPanel fp : getPanels()){
				allFilterPanels.add(fp);
			}
		}
		return allFilterPanels;
	}

	/**
	 * This method initializes Wykonaj	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getWykonaj() {
		if (Wykonaj == null) {
			Wykonaj = new JButton();
			Wykonaj.setText("Wykonaj");
			Wykonaj.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					Vector<Query> querries = new Vector (getPanels().size());
					for(FilterPanel fp :getPanels()){
						if(fp.isSelected()){
							Query q = fp.getQuery();
							if(q!=null)
								querries.add(q);
						}
					}
					System.out.println("Size" + querries.size());
					model.performQuerries(querries);			 		
				}				
			});
		}
		return Wykonaj;
	}

	/**
	 * This method initializes reset	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset() {
		if (reset == null) {
			reset = new JButton();
			reset.setText("Reset");
			reset.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					model.resetQuerries();					
				}
				
			});
		}
		return reset;
	}

	private Vector<FilterPanel> getPanels(){
		if(panels == null){
			panels = new Vector(10,1);
			for(Filter f : AbstractFilter.allFilters){
				panels.add(new FilterPanel(f, getOwner()));
				if(f.usesOperand()){
					panels.add(new FilterPanel(f, getOwner()));
				}				
			}
		}
		return panels;
	}

	public Component getOwner() {
		//if (owner == null) owner = this;
		return owner;
	}

	public void setOwner(Component owner) {
		this.owner = owner;
	}
	
	
}

