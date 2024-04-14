package eguraldiaApp;

import java.util.ArrayList;

import javax.swing.UIManager;

public class eguraldiaMain {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}
		eguraldiaGUI.jarriElementuak();
		serializazioa.kargatuKokapenak("C:/Users/Jon Gonzalez/Desktop/objektuakEguraldia/eguraldia.data");
	}
}