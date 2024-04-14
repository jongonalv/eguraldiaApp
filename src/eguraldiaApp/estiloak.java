package eguraldiaApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class estiloak.
 */
public class estiloak {
	
	/**
	 * Botoi elegante bat, eguneko eta orduko botoiak dira batez ere.
	 * Botoi urdin bat letra txuriarekin, hover efektu batekin arratoia
	 * pasatzerakoan.
	 * 
	 * @param button --> konfiguratu nahi den botoia
	 * @return botoita konfiguratua
	 */
	public static JButton botoiElegantea(JButton button) {
        button.setBackground(new Color(52, 152, 219)); // Urdina
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente

        // Kolorea aldatu arratoia pasatzerakoan
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185)); // Urdin ilunago bat
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 152, 219)); // Kolore originalara bueltatu ateratakoan
            }
        });
        return button;
    }
	
	/**
	 * Kokapena ezabatzeko botoia, ikono pertsonalizatu batekin.
	 * Botoi gorri bat baita ere hover efektu batakin arratoia
	 * pasatzerakoan
	 *
	 * @param button the button
	 * @return the j button
	 */
	public static JButton ezabatuKokapena(JButton button) {
		
		URL ezabatuUrl = estiloak.class.getResource("/ikonoak/kokapena-.png");
		ImageIcon ezabatu = new ImageIcon(ezabatuUrl);
		Image ezabatuSize = ezabatu.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon etxeaIcon = new ImageIcon(ezabatuSize);
        button.setIcon(etxeaIcon);
		
        button.setBackground(new Color(213, 103, 103)); // Gorria
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(70, 70));
        
        // Hover efektua arratoia pasatzerakoan non kolorea ilunago jartzen da
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 102, 101)); // Kolorea aldatu
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(213, 103, 103)); // Kolore originalara bueltatu
            }
        });
        return button;
    }
	
	/**
	 * Kokapena gehitzeo botoia. Aurrekoaren berdina baino, berdea.
	 *
	 * @param button the button
	 * @return the j button
	 */
	public static JButton gehituKokapena(JButton button) {
		
		URL ezabatuUrl = estiloak.class.getResource("/ikonoak/kokapena+.png");
		ImageIcon ezabatu = new ImageIcon(ezabatuUrl);
		Image ezabatuSize = ezabatu.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon etxeaIcon = new ImageIcon(ezabatuSize);
        button.setIcon(etxeaIcon);		
		
        button.setBackground(new Color(136, 176, 75));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(70, 70));
        
        // Hover efektua
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(135, 167, 107));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(136, 176, 75));
            }
        });
        return button;
    }
	
	/**
	 * Etxea eta bilaketa botoiak redimensionatzeko 70 70 eta ikonoa jartzeko.
	 *
	 * @param button the button
	 * @param imagePath the image path
	 */
	public static void botoiaKonfiguratu(JButton button, String imagePath) {
	    URL imageURL = eguraldiaGUI.class.getResource(imagePath);
	    ImageIcon icon = new ImageIcon(imageURL);
	    Image image = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(image);
	    button.setIcon(scaledIcon);
	    button.setContentAreaFilled(false);
	    button.setBorderPainted(false);
	    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}
