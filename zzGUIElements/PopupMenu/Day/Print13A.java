package zzGUIElements.PopupMenu.Day;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import zzDataBase.Day;
import zzDataBase.StaticContent;
import zzEx.ZZIllegalEx;
import zzEx.ZZInternalClassCastExceptionEx;
import zzGUIElements.AbstractGuiElements.AbstractPopupMenuItem;
import zzGUIElements.AbstractGuiElements.TreeElement;

public class Print13A extends AbstractPopupMenuItem {
	
	JFileChooser dialog = new JFileChooser();
	

	public Print13A(JTree tree) {
		super(tree);
		dialog.addChoosableFileFilter(new FileFilter(){			

			@Override
			public boolean accept(File f) {
				if(!f.exists())return false;
				if(f.isDirectory())return true;
				if(!f.getName().endsWith(".csv")) return false;
				return true;
			}

			@Override
			public String getDescription() {
				return "Plik csv";
			}			
		});
		dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	public void performAction(DefaultMutableTreeNode node) throws ZZIllegalEx, ZZInternalClassCastExceptionEx {
		try{
			TreeElement te = (TreeElement) node.getUserObject();
			Day d = te.getDay();
			BufferedWriter buffwr = null;
			if(JFileChooser.APPROVE_OPTION == dialog.showSaveDialog(StaticContent.sc.getUppmostFrame())){
				try{
					FileWriter fwr = new FileWriter (dialog.getSelectedFile());
					buffwr = new BufferedWriter(fwr);
					d.print13A(buffwr);
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
						"Anulowa³eœ. Program nic nie zrobi³",
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
		return 2000;
	}

	public boolean userCanPerformAction() {
		return true;
	}

	public String toString(){
		return "Wydrukuj dokument wg. wzoru 11A";
	}
}
