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
import javax.swing.JSplitPane;

public class FilterPanelLong extends JPanel {

	private JPanel ButtonPanel = null;
	private JScrollPane Scroll = null;
	private JPanel allFilterPanels = null;
	private JButton Wykonaj = null;
	private JButton reset = null;
	private Component owner;
	private Vector<FilterPanel> panels;
	private ProductTableModel model;
	private JSplitPane jSplitPane = null;
	private JPanel jPanel = null;

	/**
	 * This is the default constructor
	 */
	public FilterPanelLong() {
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



	public FilterPanelLong(Component owner, ProductTableModel model) {
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
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;  // Generated
		gridBagConstraints3.gridy = 0;  // Generated
		gridBagConstraints3.weightx = 1.0;  // Generated
		gridBagConstraints3.weighty = 1.0;  // Generated
		gridBagConstraints3.gridx = 0;  // Generated
		this.setLayout(new GridBagLayout());  // Generated
		this.setSize(300, 200);
		this.add(getJSplitPane(), gridBagConstraints3);  // Generated
	}

	/**
	 * This method initializes ButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonPanel() {
		if (ButtonPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;  // Generated
			gridBagConstraints1.gridy = -1;  // Generated
			gridBagConstraints1.weightx = 1.0;  // Generated
			gridBagConstraints1.weighty = 1.0;  // Generated
			gridBagConstraints1.gridx = -1;  // Generated
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;  // Generated
			gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints2.gridy = 0;  // Generated
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;  // Generated
			gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints.gridy = 0;  // Generated
			ButtonPanel = new JPanel();
			ButtonPanel.setLayout(new GridBagLayout());  // Generated
			ButtonPanel.setMinimumSize(
					new Dimension(
							getWykonaj().getWidth()+getReset().getWidth(), 
							Math.max(getWykonaj().getHeight(),getReset().getHeight())
					)			
			);
			ButtonPanel.add(getReset(), gridBagConstraints2);  // Generated
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
			Scroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // Generated
			Scroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Generated
			Scroll.setViewportView(getAllFilterPanels());  // Generated
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
			gridLayout.setRows(getPanels().size());
			gridLayout.setColumns(1);  // Generated
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



	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);  // Generated
			jSplitPane.setTopComponent(getJPanel());  // Generated
			jSplitPane.setBottomComponent(getScroll());  // Generated
			jSplitPane.setDividerLocation(0.9f);
		}
		return jSplitPane;
	}



	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridy = 0;  // Generated
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;  // Generated
			gridBagConstraints4.ipadx = 0;  // Generated
			gridBagConstraints4.gridx = 0;  // Generated
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());  // Generated
			jPanel.add(getButtonPanel(), gridBagConstraints4);  // Generated
		}
		return jPanel;
	}
	
	
}

