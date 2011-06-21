package zzGUIElements.PopupMenu.Day;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.Day;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.TreeElement;


public class Print11A extends AbstractPopupMenuItem{
	
	private static final Print11ADialog dialog = new Print11ADialog(); 

	public Print11A(JTree tree) {
		super(tree);
	}

	public void performAction(DefaultMutableTreeNode node){
		try{
			TreeElement te = (TreeElement) node.getUserObject();
			Day d = te.getDay();
			int result = dialog.display(d);
			BufferedWriter buffwr = null;
			if(result == Print11ADialog.OK_RESULT){
				try{
				FileWriter fwr = new FileWriter (dialog.getFile());
				buffwr = new BufferedWriter(fwr);
				
				d.print11A(buffwr,dialog.getBoolVect(),dialog.getIntVect());
				}catch(IOException e){
					JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
							"B³¹d zapisu" +
							e.getClass().getName() + "\n" +
							e.getMessage(),
							"B³¹d",
							JOptionPane.WARNING_MESSAGE,
							null
							);
				}finally{
					if(buffwr!=null){
						buffwr.close();
					}
				}
			}else{
				JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
						"Mi³y u¿ytkowniku! \nAnulowa³eœ operacjê. Nic nie zosta³o zapisane ",
						"B³¹d",
						JOptionPane.WARNING_MESSAGE,
						null
						);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(StaticContent.sc.getUppmostFrame(),
					"B³¹d z gatunku tych u¿ytkowinko - niezale¿nych ;-)) + " +
					"\nweŸ opieprz programistê:\nDebugInfo:\n" +
					e.getClass().getName() + "\n" +
					e.getMessage(),
					"B³¹d",
					JOptionPane.WARNING_MESSAGE,
					null
					);
			e.printStackTrace();
			return;
		}
		
	}

	public Integer priority() {
		return 1000;
	}

	public boolean userCanPerformAction() {
		return true;
	}
	
	public String toString(){
		return "Zapisz raport wg. wzoru 11A";
	}


	
}
