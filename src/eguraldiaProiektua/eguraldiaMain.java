package eguraldiaProiektua;

import javax.swing.UIManager;

public class eguraldiaMain {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}
		eguraldiaGUI.jarriElementuak();
	}
}