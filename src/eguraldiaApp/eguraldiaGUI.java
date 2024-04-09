package eguraldiaApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class eguraldiaGUI {
	
	static Component elemento;

	// Lehioa
	private static JFrame frame = new JFrame("Eguraldia");

	// Gordeko diren kokapenak panela
	private static JPanel panelKokapena = new JPanel();
	private static JPanel panelbtnKokapenak	= new JPanel();
	private static JButton kokapenaGehitu = new JButton();
	private static JButton kokapenaEzabatu = new JButton();

	// Kokapenaren uneko eguraldia erakutsiko duen panelaren elementuak
	private static JPanel panelUnekoEguraldia = new JPanel(new BorderLayout());
	private static JPanel panelKokapenakEmaitza = new JPanel();
	private static JPanel panelDatuakJPanel = new JPanel();
	private static JPanel panelBtn	= new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private static JLabel txtKokapena = new JLabel();
	private static JTextField kokapenaBilatu = new JTextField("Bilatu...", 20);
	private static JComboBox<String> kokapenakAurkitu = new JComboBox<String>();
	private static JButton btnEgunak = new JButton("Egunak");
	private static JButton btnOrduak = new JButton("Orduak");
	private static JButton btnEtxea = new JButton();
	private static JButton btnBilatu = new JButton();
	private static JLabel tenperatura = createTextLabel(null, 15);

	// Eguraldiaren datuak ikustkeko panelaren elementuak
	private static CardLayout cardLayout = new CardLayout();
	private static JPanel panelEguraldiaMain = new JPanel(cardLayout);

	// Eguraldia orduz ordu ikusteko panelaren elementuak
	private static JPanel panelEguraldiaOrduak = new JPanel();
	private static JPanel panelLabels = new JPanel(new GridBagLayout());
	private static JScrollPane scrollPane = new JScrollPane(panelLabels);

	// Eguraldia egunez egun ikusteko panelaren elementuak
	private static JPanel panelEguraldiaEgunak = new JPanel();

	public static void jarriElementuak() {
		
		frame.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		Border innerBorder = BorderFactory.createLineBorder(Color.lightGray, 2);

		Border outerBorder = BorderFactory.createLineBorder(new Color(200, 230, 255), 15);

		Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);

		// Panel Kokapena
		panelKokapena.setBorder(compoundBorder);
		panelKokapena.setLayout(new BorderLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 0.35;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		
		estiloak.gehituKokapena(kokapenaGehitu);
		estiloak.ezabatuKokapena(kokapenaEzabatu);
		
		panelbtnKokapenak.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelbtnKokapenak.setBorder(new EmptyBorder(0, 0, 50, 0));
		panelbtnKokapenak.add(kokapenaGehitu);
		panelbtnKokapenak.add(Box.createRigidArea(new Dimension(50, 0)));
		panelbtnKokapenak.add(kokapenaEzabatu);		
		
		panelKokapena.add(panelbtnKokapenak, BorderLayout.SOUTH);
		frame.add(panelKokapena, gbc);

		// Panel EguraldiaMain grid kokapena
		panelEguraldiaMain.setBorder(compoundBorder);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.75;
		gbc.weighty = 0.3;

		// Panel EguraldiaEgunak elementuak
		panelEguraldiaEgunak.setLayout(new GridBagLayout());
		GridBagConstraints gbcEgunak = new GridBagConstraints();
		gbcEgunak.gridx = 0;
		gbcEgunak.gridy = 0;
		gbcEgunak.weightx = 1.0;
		gbcEgunak.weighty = 1.0;
		gbcEgunak.fill = GridBagConstraints.BOTH;

		// 7 Egunen elementuak gehitzeko buklea
		for (int i = 0; i <= 6; i++) {
			
			// Data sortu
		    LocalDate data = LocalDate.now().plusDays(i);
		    JPanel panelEgunak = new JPanel(new GridBagLayout());
		    panelEgunak.setBorder(innerBorder);
		    
		    // Panelarentzako grid bag bat sortu elementua ilarak eta zutabe ezberdinetan banatzeko
		    GridBagConstraints gbcPanelEgunak = new GridBagConstraints();
		    gbcPanelEgunak.gridx = 0;
		    gbcPanelEgunak.gridy = 0;
		    gbcPanelEgunak.weightx = 1.0;
		    gbcPanelEgunak.weighty = 1.0;
		    gbcPanelEgunak.fill = GridBagConstraints.BOTH;

		    // Data gehitzeko blokeko lehenengo ilaran (goran). Label bat sortu eta editatu.
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		    JLabel dataLabel = new JLabel(data.format(formatter));
		    dataLabel.setFont(new Font(dataLabel.getFont().getName(), Font.PLAIN, 20));
		    dataLabel.setHorizontalAlignment(SwingConstants.CENTER);	// Label-a zentratu
		    
		    // Dataren Label-a zentratzeko, gridbag bat sortu eta erdialdean kokatu
		    GridBagConstraints gbcDataLabel = new GridBagConstraints();
		    gbcDataLabel.gridx = 0;
		    gbcDataLabel.gridy = 0;
		    gbcDataLabel.gridwidth = GridBagConstraints.REMAINDER; // Panel-aren antxura guztia okupatu
		    panelEgunak.add(dataLabel, gbcDataLabel);

		    // Bigarren lerroa, euskeraz, dataren eguna idazteko
		    JLabel egunaLabel = new JLabel(astekoEgunak.values()[data.getDayOfWeek().getValue() - 1].getEuskera());
		    egunaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		    
		    // Egunaren label-a zentratzeko gridbag-a
		    GridBagConstraints gbcEgunaLabel = (GridBagConstraints) gbcPanelEgunak.clone();
		    gbcEgunaLabel.gridwidth = GridBagConstraints.REMAINDER;	// Panel-aren antxura guztia okupatu
		    gbcEgunaLabel.gridy++;	        
		    panelEgunak.add(egunaLabel, gbcEgunaLabel);

		    // Bi lerro gehitu direnez, bi gehitu hurrengo lerroeta pasatzeko
		    gbcPanelEgunak.gridy = gbcPanelEgunak.gridy + 2;

		    // Hurrengo etiketak gehitzeko, bi zutabe gehituz bakoitzari
		    for (int j = 0; j < 6; j++) {
		    	
		        // Zutabe 1
		        JLabel label1 = new JLabel("1");
		        label1.setHorizontalAlignment(SwingConstants.CENTER);
		        panelEgunak.add(label1, gbcPanelEgunak);

		        // Zutabe 2
		        gbcPanelEgunak.gridx++;
		        JLabel label2 = new JLabel("2");
		        label2.setHorizontalAlignment(SwingConstants.CENTER);
		        panelEgunak.add(label2, gbcPanelEgunak);
		        
		        // Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
		        gbcPanelEgunak.gridx = 0;
		        gbcPanelEgunak.gridy++;
		    }

		    // Panel-a gehitu egunaren eguraldia panelera
		    panelEguraldiaEgunak.add(panelEgunak, gbcEgunak);
		    gbcEgunak.gridx++;
		}

		// Panel EguraldiaOrduak elementuak
		panelEguraldiaOrduak.setLayout(new GridBagLayout());
		GridBagConstraints gbcOrduak = new GridBagConstraints();
		gbcOrduak.gridx = 0;
		gbcOrduak.gridy = 0;
		gbcOrduak.weightx = 1.0;
		gbcOrduak.weighty = 1.0;
		gbcOrduak.fill = GridBagConstraints.BOTH;

		// 24 Orduen elementuak gehitzeko buklea
		for (int i = 1; i <= 24; i++) {
			JLabel label = new JLabel("" + i);
			label.setBorder(innerBorder);
			label.setPreferredSize(new Dimension(100, 50));
			panelLabels.add(label, gbcOrduak);
			gbcOrduak.gridx++;
		}

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelEguraldiaOrduak.add(scrollPane, gbcOrduak);

		panelEguraldiaMain.add(panelEguraldiaEgunak, "egunak");
		panelEguraldiaMain.add(panelEguraldiaOrduak, "orduak");

		frame.add(panelEguraldiaMain, gbc);

		// Uneko eguraldia erakusten duen panelaren elementuak gehitu
		panelUnekoEguraldia.setBorder(compoundBorder);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.75;
		gbc.weighty = 0.1;

		// Panel EguraldiaEgunak elementuak
		estiloak.botoiElegantea(btnEgunak);
		estiloak.botoiElegantea(btnOrduak);
		
		
		
		// Etxearen botoia konfiguratu
		URL etxeaURL = eguraldiaGUI.class.getResource("/ikonoak/etxea.png");
		ImageIcon etxea = new ImageIcon(etxeaURL);
		Image etxeaSize = etxea.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon etxeaIcon = new ImageIcon(etxeaSize);
		btnEtxea.setIcon(etxeaIcon);
		btnEtxea.setContentAreaFilled(false);
		btnEtxea.setBorderPainted(false);
		btnEtxea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		// Bilaketaren botoia konfiguratu
		URL bilaketaURL = eguraldiaGUI.class.getResource("/ikonoak/bilatu.png");
		ImageIcon bilatu = new ImageIcon(bilaketaURL);
		Image bilatuSize = bilatu.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon bilatuIcon = new ImageIcon(bilatuSize);
		btnBilatu.setIcon(bilatuIcon);
		btnBilatu.setContentAreaFilled(false);
		btnBilatu.setBorderPainted(false);
		btnBilatu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
		panelKokapenakEmaitza.setLayout(new BoxLayout(panelKokapenakEmaitza, BoxLayout.X_AXIS));

		// Añadir txtKokapena al panel con un pequeño margen a la izquierda
		txtKokapena.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		panelKokapenakEmaitza.add(txtKokapena);

		// Añadir un espacio en blanco elástico para empujar los botones a la derecha
		panelKokapenakEmaitza.add(Box.createHorizontalGlue());

		// Añadir botones btnEtxea y btnBilatu al panel
		panelKokapenakEmaitza.add(btnEtxea);
		panelKokapenakEmaitza.add(btnBilatu);
        
        btnBilatu.addActionListener(e -> {
        	bilaketaGUI.bilaketaGUIHasieratu();
        });
        
        panelDatuakJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();

		for (int i = 0; i < 10; i++) {
		    
		    switch (i) {
		        case 0:
		        	elemento = createImageLabel("/ikonoak/lainotuta.png", 150, 140);
		            break;
		        case 1:
		        	elemento = createImageLabel("/ikonoak/temperatura.png", 40, 40);
		            break;
		        case 2:
		        	elemento = createTextLabel("14º", 15);
		            break;
		        case 3:
		        	elemento = createImageLabel("/ikonoak/ilunabarra.png", 50, 50);
		            break;
		        case 4:
		        	elemento = createTextLabel("19:20", 15);
		            break;
		        case 6:
		        	elemento = createImageLabel("/ikonoak/flecha.png", 38, 38);
		            break;
		        case 7:
		        	elemento = createTextLabel("5 km/h", 15);
		            break;
		        case 8:
		        	elemento = createImageLabel("/ikonoak/egunsentia.png", 50, 50);
		            break;
		        case 9:
		        	elemento = createTextLabel("07:20", 15);
		            break;
		        default:
		            elemento = new JLabel("");
		    }

			gbc2.gridy = i < 5 ? 0 : 1; // Fila

			// Ajustar las posiciones de las columnas dentro de cada fila
			gbc2.gridx = i % 5;

			// Ajustar la alineación de los elementos dentro de cada celda
			gbc2.anchor = GridBagConstraints.WEST;
			
			gbc2.insets.top = 30;

			// Establecer separación entre columnas según el índice de la columna
			if (i == 1 || i == 3) {
				gbc2.insets.right = 10; // Separación entre columnas 1 y 2, 3 y 4
			} else if (i == 0 || i == 2 || i == 4) {
				gbc2.insets.right = 120; // Separación entre columnas 0 y 1, 2 y 3, 4 y 5
			} else {
				gbc2.insets.right = 0; // Sin separación adicional para otras columnas
			}
			panelDatuakJPanel.add(elemento, gbc2);
		}


        panelBtn.add(btnEgunak);
        panelBtn.add(btnOrduak);

        // Agrega el panel principal al contenedor principal con los demás componentes
        panelUnekoEguraldia.add(panelKokapenakEmaitza, BorderLayout.NORTH);
        panelUnekoEguraldia.add(panelDatuakJPanel, BorderLayout.CENTER);
        panelUnekoEguraldia.add(panelBtn, BorderLayout.SOUTH);

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
		panelUnekoEguraldia.requestFocusInWindow();
	}
	
	public void txtKokapenaAldatu(String textua) {
		txtKokapena.setText(textua);
	}

	public void tenperaturaAldatu(String text, int size) {
		elemento = createTextLabel(text, size);
	}
	
    public static JLabel createImageLabel(String imagePath, int maxWidth, int maxHeight) {
        URL imageURL = eguraldiaGUI.class.getResource(imagePath);
        ImageIcon imageIcon = new ImageIcon(imageURL);

        Image img = imageIcon.getImage();
        Image scaledImg = img.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel label = new JLabel(scaledIcon);
        return label;
    }

    public static JLabel createTextLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, fontSize));
        return label;
    }

	enum astekoEgunak {
	    Astelehena("Astelehena"),
	    Asteartea("Asteartea"),
	    Asteazkena("Asteazkena"),
	    Osteguna("Osteguna"),
	    Ostirala("Ostirala"),
	    Larunbata("Larunbata"),
	    Igandea("Igandea");

	    private final String euskera;

	    astekoEgunak(String euskera) {
	        this.euskera = euskera;
	    }

	    public String getEuskera() {
	        return euskera;
	    }
	}
}