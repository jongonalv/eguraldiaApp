package eguraldiaProiektua;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.Border;

public class eguraldiaGUI {
	
    private static JFrame frame = new JFrame("Eguraldia");
    
    private static JPanel panelKokapena = new JPanel();
    
    // Kokapenaren uneko eguraldia erakutsiko duen panelaren elementuak
    private static JPanel  panelUnekoEguraldia 	= new JPanel();
    private static JLabel txtKokapena			= new JLabel("Tolosako eguraldia");
    private static JButton btnEgunak			= new JButton("Egunak");
    private static JButton btnOrduak			= new JButton("Orduak");
    private static JButton btnKokapenaGehitu 	= new JButton(loadImage("src/assets/kokapena.png"));
    
    // Eguraldiaren datuak ikustkeko panelaren elementuak
    private static CardLayout cardLayout 		= new CardLayout();
    private static JPanel panelEguraldiaMain 	= new JPanel(cardLayout);
    
    // Eguraldia orduz ordu ikusteko panelaren elementuak
    private static JPanel panelEguraldiaOrduak 	= new JPanel();
    
    // Eguraldia egunez egun ikusteko panelaren elementuak
    private static JPanel panelEguraldiaEgunak	= new JPanel();

    public static void jarriElementuak() {
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        GridBagConstraints gbc3 = new GridBagConstraints();
        
        Border innerBorder = BorderFactory.createLineBorder(Color.lightGray, 2);
        
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        
        Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);

        // Panel Kokapena
        panelKokapena.setBorder(compoundBorder);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.40;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelKokapena.setSize(300, 300);
        
        frame.add(panelKokapena, gbc);
        
        // Panel EguraldiaMain grid kokapena
        panelEguraldiaMain.setBorder(compoundBorder);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 0.5;      
        panelEguraldiaMain.add(panelEguraldiaEgunak, "egunak");
        panelEguraldiaMain.add(panelEguraldiaOrduak, "orduak");
        
        // Panel EguraldiaEgunak elementuak
        panelEguraldiaEgunak.setLayout(new GridBagLayout());
        gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0;
        gbc2.fill = GridBagConstraints.BOTH;

        // 7 Egunen elementuak gehitzeko buklea
        for (int i = 1; i <= 7; i++) {
        	LocalDate data = LocalDate.now();
            JLabel label = new JLabel(data.plusDays(i).toString());
            label.setBorder(innerBorder);
            panelEguraldiaEgunak.add(label, gbc2);
            gbc2.gridx++;
        }  
        
        // Panel EguraldiaEgunak elementuak
        panelEguraldiaOrduak.setLayout(new GridBagLayout());
        gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0;
        gbc2.fill = GridBagConstraints.BOTH;

        // 7 Egunen elementuak gehitzeko buklea
        for (int i = 1; i <= 24; i++) {
            JLabel label = new JLabel("i:" + i);
            label.setBorder(innerBorder);
            panelEguraldiaOrduak.add(label, gbc2);
            gbc2.gridx++;
        }
       
        frame.add(panelEguraldiaMain, gbc);

        // Uneko eguraldia erakusten duen panelaren elementuak gehitu
        panelUnekoEguraldia.setBorder(compoundBorder);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 0.5;
        
        // Panel EguraldiaEgunak elementuak
        panelUnekoEguraldia.add(btnEgunak);
        panelUnekoEguraldia.add(btnOrduak);
        
        // Panel orduak eta panel egunen arteko aldaketa egiteko listener-ak
        btnEgunak.addActionListener(e -> {
		    cardLayout.show(panelEguraldiaMain, "egunak");
        });
        
        btnOrduak.addActionListener(e -> {
		    cardLayout.show(panelEguraldiaMain, "orduak");
        });
        
        frame.add(panelUnekoEguraldia, gbc);

        frame.setSize(1400, 760);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
	private static ImageIcon loadImage(String resourcePath) {
		try {
			// read the image file from the path given
			BufferedImage image = ImageIO.read(new File(resourcePath));

			// returns an image icon so that our component can render it
			return new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Could not find resource");
		return null;
	}
}
