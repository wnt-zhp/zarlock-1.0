package zzGUIElements.MealElements;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import zzGUIElements.zzGuiProductElements.ProductTableModel;
import zzGUIElements.zzGuiProductElements.TableSorter;
import zzId.ProdId;
import zzProduct.Product;
import zzProductBase.ProductBaseImmutable;

public class MealProductTable extends JPanel {

	private TableModel model;
	
	private JScrollPane pane = null;
	private JTable table = null;
	private Listener list;
	
	public interface Listener{
		void addProduct(ProdId id);
	}

	/**
	 * This is the default constructor
	 * @depreced - for VE
	 */
	public MealProductTable() {
		super();
		initialize();
	}
	
	

	public MealProductTable(MealProductTableModel model) {
		this.model = model;
		initialize();
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.add(getPane(), null);  // Generated
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * This method initializes pane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPane() {
		if (pane == null) {
			pane = new JScrollPane();
			pane.setViewportView(getTable());  // Generated
		}
		return pane;
	}

	/**
	 * This method initializes table	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTable() {
		if (table == null) {
			setTable(getModel());
			TableColumn col = table.getColumnModel().
			getColumn(MealProductTableModel.DODAJ_COLUMN_NUMBER);
			col.setCellEditor(new simpleRenderer());
			col.setCellRenderer(new simpleRenderer());
		}
		return table;
	}

	public void setModel(MealProductTableModel model) {
		
		this.model = model;
	}

	public TableModel getModel() {
		return model;
	}
	
	public void setList(Listener list) {
		this.list = list;
	}



	public Listener getList() {
		return list;
	}

	private class simpleRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{

		private JButton button;
		private ProdId id;
		
		
		public simpleRenderer() {
			super();
			button = new JButton();
			button.setText("Dodaj");
			button.addActionListener( 
					new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							list.addProduct(id);							
						}						
					}
				);
		}

		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
			// TODO Auto-generated method stub
			return button;
		}
		
		public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
			id = (ProdId)arg1;
			return button;
		}

		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return id;
		} 
		
	}

	void setTable(TableModel model) {
		TableSorter sorter = new TableSorter(model); //ADDED THIS
//		JTable table = new JTable(new MyTableModel());          //OLD
		JTable table = new JTable(sorter){

			@Override
			public String getToolTipText(MouseEvent e) {
				 String tip = null;
		        java.awt.Point p = e.getPoint();
		        int rowIndex = rowAtPoint(p);
		        int colIndex = columnAtPoint(p);
		        int realColumnIndex = convertColumnIndexToModel(colIndex);
		        
		        tip = this.getModel().getValueAt(rowIndex,realColumnIndex).toString();
		        
		        if(realColumnIndex==MealProductTableModel.DODAJ_COLUMN_NUMBER){
		      	  return super.getToolTipText(e);
		        }
		        
		        return tip;
			}
			
		};             //NEW
		sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
		
		this.table = table;
	}


}
