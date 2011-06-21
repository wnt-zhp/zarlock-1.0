package zzGUIElements.zzGuiProductElements;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import zzEx.ZZIllegalEx;
import zzEx.ZZInternalUnsupportedOperationExceptionEx;
import zzEx.ZZIoEx;
import zzGUIElements.BranchGenerator;
import zzGUIElements.AbstractGuiElements.TreeElement;
import zzProduct.Product;

public class ProductExpendituresDialogv2 extends JDialog {

	private JPanel jContentPane = null;
	private JSplitPane jSplitPane = null;
	private JScrollPane bottomScrollPane = null;
	private JScrollPane upperScrollPane = null;
	private JEditorPane editorPane = null;
	private JTree jTree = null;
	
	private DefaultTreeModel model = null;
	private Product p = null;

	public ProductExpendituresDialogv2(Dialog arg0, boolean arg1) throws HeadlessException {
		super(arg0, arg1);
		initialize();
	}

	public ProductExpendituresDialogv2(Frame arg0, boolean arg1) throws HeadlessException {
		super(arg0, arg1);
		initialize();
	}

	public ProductExpendituresDialogv2(Frame arg0) throws HeadlessException {
		super(arg0);
		initialize();
	}


	
	public void dispaly(Product p){
		DefaultMutableTreeNode node;
		try {
			node = BranchGenerator.expendituresBranch(p,getJTree());
			setRootNode(node);
			TreeElement element = (TreeElement) node.getUserObject();
			if(this.getOwner()!=null){
				setLocationRelativeTo(getOwner());
				setLocation(getOwner().getWidth()/2,getOwner().getHeight()/2);   
				if(element.hasAdditionalData())setEditorPane(element.getAdditionalData());
			}
			setVisible(true);
			this.p = p;
		} catch (ZZIllegalEx e) {
			e.printStackTrace();
		} catch (ZZIoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZZInternalUnsupportedOperationExceptionEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.addMouseListener(new mouseListener());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);  // Generated
			gridLayout.setColumns(1);  // Generated
			jContentPane = new JPanel();
			jContentPane.setLayout(gridLayout);  // Generated
			jContentPane.add(getJSplitPane(), null);  // Generated
		}
		return jContentPane;
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
			jSplitPane.setDividerLocation(this.getHeight()/2);  // Generated
			jSplitPane.setTopComponent(getUpperScrollPane());  // Generated
			jSplitPane.setBottomComponent(getBottomScrollPane());  // Generated
			jSplitPane.setPreferredSize(new java.awt.Dimension(320,240));  // Generated
		}
		return jSplitPane;
	}

	/**
	 * This method initializes bottomScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getBottomScrollPane() {
		if (bottomScrollPane == null) {
			bottomScrollPane = new JScrollPane();
			bottomScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Generated
			bottomScrollPane.setViewportView(getEditorPane());  // Generated
		}
		return bottomScrollPane;
	}

	/**
	 * This method initializes upperScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getUpperScrollPane() {
		if (upperScrollPane == null) {
			upperScrollPane = new JScrollPane();
			upperScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Generated
			upperScrollPane.setViewportView(getJTree());  // Generated
		}
		return upperScrollPane;
	}

	/**
	 * This method initializes editorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getEditorPane() {
		if (editorPane == null) {
			editorPane = new JEditorPane();
		}
		return editorPane;
	}
	
	private void setEditorPane(JEditorPane pane){
		bottomScrollPane.getViewport().removeAll();
		bottomScrollPane.getViewport().add(pane);
		bottomScrollPane.addMouseListener(new mouseListener());
		this.editorPane = pane;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree(getModel());
			jTree.addTreeSelectionListener(new treeListener());
			jTree.addMouseListener(new mouseListener());
		}
		return jTree;
	}

	private void setModel(DefaultTreeModel model) {
		this.model = model;
	}

	private DefaultTreeModel getModel() {
		if (model == null) model = new DefaultTreeModel(new DefaultMutableTreeNode());
		return model;
	}
	
	private void setRootNode(DefaultMutableTreeNode node){
		getModel().setRoot(node);
	}
	
	private class treeListener implements TreeSelectionListener{
		
		public void valueChanged(TreeSelectionEvent arg0) {
			TreeElement element;
			try{
				DefaultMutableTreeNode foo = (DefaultMutableTreeNode) getJTree().getLastSelectedPathComponent();
				element = (TreeElement) foo.getUserObject();
				if(element.hasAdditionalData()){
					System.out.println("Jo³ bro!");
					try{
						JEditorPane pane = element.getAdditionalData();
						if(pane != null) setEditorPane(pane);
					}catch(Exception e){}
				}      
			}catch(ClassCastException e){
				JOptionPane.showMessageDialog(getOwner(), "Powa¿ny b³¹d", "Wo³aj Jacka", JOptionPane.WARNING_MESSAGE);
				return;
			}catch(NullPointerException e){
				//To mo¿e siê zdarzyœ, ale jest OK.
			}
			
  
		}    
		
	} 
	

	private class mouseListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			maybeShowPopup(arg0);
			
		}

		public void mousePressed(MouseEvent arg0) {
			maybeShowPopup(arg0);
			
		}

		public void mouseReleased(MouseEvent arg0) {
			maybeShowPopup(arg0);
			
		}

		public void mouseEntered(MouseEvent arg0) {
			maybeShowPopup(arg0);
			
		}

		public void mouseExited(MouseEvent arg0) {
			maybeShowPopup(arg0);
		}
		
		private void maybeShowPopup(MouseEvent arg0){
			if(!arg0.isPopupTrigger()) return;
			TreeElement element;
			DefaultMutableTreeNode node;
			try{
				node = (DefaultMutableTreeNode) getJTree().getLastSelectedPathComponent();
				element = (TreeElement) node.getUserObject();

			}catch(ClassCastException e){
				JOptionPane.showMessageDialog(getOwner(), "Wo³aj Jacka", "Powa¿ny b³¹d",  JOptionPane.WARNING_MESSAGE);
				
				return;
			}catch(NullPointerException e){
				return; //getJTree().getLastSelectedPathComponent(); mo¿e zwróciæ null
				//Jest to przejaw normalnej dzia³alnoœci programu
			}
			
			if(element.hasAssociatedPopUpMenu()){
				try {
					element.getAssociatedPopUpMenu().show(
						ProductExpendituresDialogv2.this,
						arg0.getX(),
						arg0.getY(),
						(DefaultMutableTreeNode) getJTree().getLastSelectedPathComponent(),
						model
					);

				} catch (ZZInternalUnsupportedOperationExceptionEx e) {
					
					e.printStackTrace();
				} catch (ZZIllegalEx e) {
					
					e.printStackTrace();
				} 
			}

		}
	}

}
