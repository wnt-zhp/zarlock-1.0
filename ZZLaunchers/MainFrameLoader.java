package ZZLaunchers;

import javax.swing.UIManager;

import zzDataBase.StaticContent;
import zzGUIElements.MainFrame;
import zzUser.Priveleges;
import zzUser.User;

public class MainFrameLoader {

	public MainFrameLoader() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e){
			e.printStackTrace();
		}
		MainFrame frame = new MainFrame();
		StaticContent.sc.setUppmostFrame(frame);
		User.setName("Entropia");
		User.setPriveleges(Priveleges.ROOTUSER);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new MainFrameLoader();
	}

	

}
