package zzTests;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import zzGUIElements.zzGuiProductElements.FilterPanelLong;
import zzGUIElements.zzGuiProductElements.FilterPanelSquare;

public class FilterPaenLongTests extends JFrame {

	private JPanel jContentPane = null;
	private FilterPanelSquare filterPanelLong = null;

	/**
	 * This is the default constructor
	 */
	public FilterPaenLongTests() {
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
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getFilterPanelLong(), java.awt.BorderLayout.CENTER);  // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes filterPanelLong	
	 * 	
	 * @return zzGUIElements.zzGuiProductElements.FilterPanelLong	
	 */
	private FilterPanelSquare getFilterPanelLong() {
		if (filterPanelLong == null) {
			filterPanelLong = new FilterPanelSquare();
		}
		return filterPanelLong;
	}

}
