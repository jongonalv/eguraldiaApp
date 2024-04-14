package eguraldiaApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * Eguraldiaren aplikazioa bistaratzeko klasea, eguraldiaGUI.
 */
public class eguraldiaGUI implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// Interfazean unean dauden eguraldia eta geokokapena
	private static Geokokapena aukeratutakoKokapena;
	
	private static eguraldia eguraldia;
	
	// Border dekoratiboak konfiguratu
	static Border innerBorder = BorderFactory.createLineBorder(Color.lightGray, 2);

	static Border outerBorder = BorderFactory.createLineBorder(new Color(200, 230, 255), 15);
	static Border outerBorder2 = BorderFactory.createLineBorder(new Color(180, 207, 255), 7);

	static Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
	static Border compoundBorder2 = BorderFactory.createCompoundBorder(outerBorder2, innerBorder);

	// Lehioa
	private static JFrame frame = new JFrame("Eguraldia");

	// Gordeko diren kokapenak panela
	private static JPanel panelKokapena 		= new JPanel();
	private static JPanel panelbtnKokapenak 	= new JPanel();
	protected static JPanel kontenedorPanel 	= new JPanel();
	private static JButton kokapenaGehitu 		= new JButton();
	private static JButton kokapenaEzabatu 		= new JButton();

	// Kokapenaren uneko eguraldia erakutsiko duen panelaren elementuak
	private static JPanel panelUnekoEguraldia 	= new JPanel(new BorderLayout());
	private static JPanel panelKokapenakEmaitza = new JPanel();
	private static JPanel panelDatuakJPanel 	= new JPanel();
	private static JPanel panelBtn 				= new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private static JLabel txtKokapena 			= new JLabel();
	private static JButton btnEgunak 			= new JButton("Egunak");
	private static JButton btnOrduak 			= new JButton("Orduak");
	private static JButton btnEtxea 			= new JButton();
	private static JButton btnBilatu 			= new JButton();
	private static JLabel[] unekoElementuak 	= new JLabel[10];

	// Eguraldiaren datuak ikustkeko panelaren elementuak
	private static CardLayout cardLayout 		= new CardLayout();
	private static JPanel panelEguraldiaMain 	= new JPanel(cardLayout);
	
	// Eguraldia orduz ordu ikusteko panelaren elementuak
	private static JPanel panelEguraldiaOrduak 	= new JPanel();
	private static JPanel panelLabels 			= new JPanel(new GridBagLayout());
	private static JScrollPane scrollPane 		= new JScrollPane(panelLabels);
	private static JLabel[] ikonoOrduLabel 		= new JLabel[24];
	private static JLabel[] temp 				= new JLabel[24];
	private static JLabel[] prezipitazioa 		= new JLabel[24];
	private static JLabel[] prezipitazioAukerak = new JLabel[24];
	private static JLabel[] haizeNorabidea 		= new JLabel[24];
	private static JLabel[] haizeAbiadura 		= new JLabel[24];

	// Eguraldia egunez egun ikusteko panelaren elementuak
	private static JPanel panelEguraldiaEgunak 	= new JPanel();
	private static JLabel dataLabel 			= new JLabel();
	private static JLabel egunaLabel 			= new JLabel();
	private static JLabel[] ikonoLabel 			= new JLabel[7];
	private static JLabel[] tempMax 			= new JLabel[7];
	private static JLabel[] tempMin 			= new JLabel[7];
	private static JLabel[] euria 				= new JLabel[7];
	private static JLabel[] elurra 				= new JLabel[7];
	private static JLabel[] egunsentia 			= new JLabel[7];
	private static JLabel[] ilunabarra 			= new JLabel[7];
	private static JLabel[] haizea 				= new JLabel[7];
	private static JLabel[] haizeaN 			= new JLabel[7];
	
	/**
	 * Interfazean, uneko momentuan, dagoen informazioa hartzeko eraikitzailea.
	 *
	 * @param eguraldia the eguraldia
	 * @param geokokapena the geokokapena
	 */
	public eguraldiaGUI(eguraldia eguraldia, Geokokapena geokokapena) {
		this.aukeratutakoKokapena = geokokapena;
		this.eguraldia = eguraldia;
	}
	
    /**
     * Metodo honek kokapen panel bat sortzen du. Panel bat gordetzerakoa,
     * pantailan bistaratu behar da panel berria kokapenaren informazioarekin,
     * honek, konfiguratu egiten du
     *
     * @param kokapena 	--> panel-aren kokapenaren informazioa
     * @param ikono 	--> kokapen horren, uneko eguraldiaren ikonoa
     * @return the 		--> sortutako panel berria
     */
    public static JPanel kokapenPanelSortu(Geokokapena kokapena, ImageIcon ikono) {
		
    	// Panel-a sortu eta konfiguratu dimentsio batekin eta border batekin
        JPanel panel = new JPanel();
        panel.setBorder(compoundBorder2);
        panel.setPreferredSize(new Dimension(200, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Kokapenaren datuekin konfiguratu panel-a
        JLabel label1 = new JLabel(kokapena.getName() + ", " + kokapena.getCountry());
        JLabel label2 = new JLabel();
        label2.setIcon(ikono);
        
        // Konponenteak gehitu
        panel.add(Box.createHorizontalGlue());
        panel.add(label1);
        panel.add(label2);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

	/**
	 * Frameko elementu guztiak kokatzeko metodoa, bai listener-ak, bai panelak... etc.
	 */
	public static void jarriElementuak() {

		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

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

		// Botoien itxurak konfiguratu
		estiloak.gehituKokapena(kokapenaGehitu);
		estiloak.ezabatuKokapena(kokapenaEzabatu);

		// Sortutako kokapenen panelak gordetzeko kontenedore bat sortu
		kontenedorPanel.setLayout(new GridLayout(5, 1, 0, 5)); // 5 kokapen, 5 filako gridlayout

		// kontenedoren panel-a zentratu
		panelKokapena.add(kontenedorPanel, BorderLayout.CENTER);

		// Map bat sortu paneleko kokapen objetu guztiak gordetzeko (panel-a gordeko da kokapenaren informazioarekin)
		Map<JPanel, Geokokapena> geokokapenaMap = new HashMap<>();

		// Kokapena gehitu listener-a jarri, pantailan dagoen kokapena harteko eta panelKokapenari gehitzeko
		kokapenaGehitu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	// Textu kutxa hutsik badago, mezu bat bidali
		        if (txtKokapena.getText().isEmpty()) {					
		            JOptionPane.showMessageDialog(frame, "Ez dago kokapenik pantailan", "Errorea",
		                    JOptionPane.WARNING_MESSAGE);
		        } else {
		        	
		            // Konprobatu ea 5 panel sortuta dauden kontenedorean (5 kokapen maximo)
		            if (kontenedorPanel.getComponentCount() < 5) {

		                // Frame-an dagoen eguraldiaren uneko informazioa lortu eta ikonoa lortu
		                String nombreImagen = getEguraldiKondizioa(eguraldia.getUnekoEguraldia().getWeatherCode()) + ".png";
		                ImageIcon imagenOriginal = new ImageIcon(getClass().getResource("/ikonoak/" + nombreImagen));
		                Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(70, 65, Image.SCALE_SMOOTH);
		                ImageIcon nuevoIcono = new ImageIcon(imagenEscalada);

		                // panel-a sortu kokapenarekin
		                JPanel sharedPanel = kokapenPanelSortu(aukeratutakoKokapena, nuevoIcono);

		                // panel-a gehitu kontenedorean
		                kontenedorPanel.add(sharedPanel);

		                // Asoziazioa gorde mapan panelaren-a eta bere kokapenaren-a
		                geokokapenaMap.put(sharedPanel, aukeratutakoKokapena);		            
		                boolean etxeaDa = false;
		                
		                // Aldaketak bistaratzeko, kokapena ondo gorde bada, bistaratu
		                if (serializazioa.gordeKokapenak(geokokapenaMap, "C:/Users/Jon Gonzalez/Desktop/objektuakEguraldia/eguraldia.data")) {
			                frame.revalidate();
			                frame.repaint();
		                }	else	{
		                	return;
		                }
		                
		                // Kokapenak gorde artxibo batean		            		                
		                for (Component component : kontenedorPanel.getComponents()) {
		                    if (component instanceof JPanel) {
		                        JPanel panel = (JPanel) component;
		                        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // kursorea aldatu

		                        // Panel bakoitzari listener bat gehitu klikatzerakoan frame osoa
		                        // eguneratzeko eta panel horren kokapenaren eguraldiaren informazioa bistaratzeko
		                        panel.addMouseListener(new MouseAdapter() {
		                            @Override
		                            public void mouseClicked(MouseEvent e) {
		                                if (SwingUtilities.isLeftMouseButton(e)) {
		                                	
		                                    // Klikatutako panelaren erlazionatutako kokapena lortzen da
		                                    Geokokapena kokapenaRelacionado = geokokapenaMap.get(panel);
		                                    
		                                    // Eguraldia lortzen da klikatutako panelaren-a
		                                    eguraldia eguraldiaKargatu = eguraldia.getEguraldiDatuak(kokapenaRelacionado);

		                                    // Informazio berria eguneratzen da
		                                    eguraldiaAldatu(eguraldiaKargatu, eguraldiaGUI.class);
		                                    txtKokapenaAldatu(kokapenaRelacionado);
		                                }
		                            }	            
		                        });
		                    }
		                }
		                
		            } else {
		            	
		                // Limitea iristen bada mezua erakutsi
		                JOptionPane.showMessageDialog(frame, "Bakarrik 5 kokapen ahal dira gorde", "Limitea",
		                        JOptionPane.WARNING_MESSAGE);
		            }
		        }
		    }
		});

		// Kokapen bat ezabatzeko listener-a
		kokapenaEzabatu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	// Erabiltzaileari esan nola ezabatu kokapen bat
		    	JOptionPane.showMessageDialog(null, "Ezabatzeko, eskubiko klik-a kokapen batean.");
		    	
		        // Panel bat dagoen aukeratuta ikusi
		        Component[] konponenteak = kontenedorPanel.getComponents();
		        final boolean[] ezabatuPanel = {false}; // Array bakarreko elementua erabili (bestela ez du futzionatzen)
		        
		        // Konponentze bakoitzeko aukeratutako panela ikusi den
		        for (Component konponente : konponenteak) {
		            final JPanel panel = (JPanel) konponente;
		            
		            // panel-a eskuineko klika egiterakoan ezabatzeko listener-a jarri konponenteetako panel guztiei
		            panel.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                    if (SwingUtilities.isRightMouseButton(e)) {
		                    	
		                    	// Panel-a ezabatu
		                        if (!ezabatuPanel[0]) {	                            
		                            kontenedorPanel.remove(panel);
		                            serializazioa.ezabatuKokapena("C:/Users/Jon Gonzalez/Desktop/objektuakEguraldia/eguraldia.data", panel);
		                            frame.revalidate();
		                            frame.repaint();
		                            
		                            ezabatuPanel[0] = true;
		                        }
		                    }
		                }
		            });
		            
		            if (ezabatuPanel[0]) { // panelen bat ezabatu bada, bukletik atera
		                break;
		            }
		        }
		    }
		});


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

			// Gaurko datau lortu
			LocalDate data = LocalDate.now().plusDays(i);
			
			// Egunen datuak gordetzeko panelak sortu
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");	// Formatua gehitu eguna/hilabetea
			dataLabel = new JLabel(data.format(formatter));
			dataLabel.setFont(new Font(dataLabel.getFont().getName(), Font.PLAIN, 20));
			dataLabel.setHorizontalAlignment(SwingConstants.CENTER); // Label-a zentratu

			// Dataren Label-a zentratzeko, gridbag bat sortu eta erdialdean kokatu
			GridBagConstraints gbcDataLabel = new GridBagConstraints();
			gbcDataLabel.gridx = 0;
			gbcDataLabel.gridy = 0;
			gbcDataLabel.gridwidth = GridBagConstraints.REMAINDER; // Panel-aren antxura guztia okupatu
			panelEgunak.add(dataLabel, gbcDataLabel);

			// Bigarren lerroa, euskeraz, dataren eguna idazteko
			egunaLabel = new JLabel(astekoEgunak.values()[data.getDayOfWeek().getValue() - 1].getEuskera());
			egunaLabel.setHorizontalAlignment(SwingConstants.CENTER);

			// Egunaren label-a zentratzeko gridbag-a
			GridBagConstraints gbcEgunaLabel = (GridBagConstraints) gbcPanelEgunak.clone();
			gbcEgunaLabel.gridwidth = GridBagConstraints.REMAINDER; // Panel-aren antxura guztia okupatu
			gbcEgunaLabel.gridy++;
			panelEgunak.add(egunaLabel, gbcEgunaLabel);

			// Hirugarren lerroa, non eguraldiaren ikonoa ageriko da
			ikonoLabel[i] = createImageLabel("", 1, 1);
			ikonoLabel[i].setHorizontalAlignment(SwingConstants.CENTER);

			// Egunaren label-a zentratzeko gridbag-a
			GridBagConstraints gbcIkonoLabel = (GridBagConstraints) gbcEgunaLabel.clone();
			gbcIkonoLabel.gridwidth = GridBagConstraints.REMAINDER; // Panel-aren antxura guztia okupatu
			gbcIkonoLabel.gridy++; // Mover el ikonoLabel a la siguiente fila
			panelEgunak.add(ikonoLabel[i], gbcIkonoLabel);

			// Bi lerro gehitu direnez, bi gehitu hurrengo lerroeta pasatzeko
			gbcPanelEgunak.gridy = gbcPanelEgunak.gridy + 3;

			// Hurrengo etiketak gehitzeko, bi zutabe gehituz bakoitzari

			// Lerro 1 Zutabe 1 temperatura maximoa gehitu
			tempMax[i] = createTextLabel("", 18);
			tempMax[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(tempMax[i], gbcPanelEgunak);

			// Lerro 1 Zutabe 2	temperatura minimoa gehitu
			gbcPanelEgunak.gridx++;
			tempMin[i] = createTextLabel("", 18);
			tempMin[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(tempMin[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;

			// Lerro 2 Zutabe 1 euriaren ikonoa gehitu
			JLabel label3 = createImageLabel("/ikonoak/euritantak.png", 20, 20);
			label3.setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(label3, gbcPanelEgunak);
			
			// Lerro 2 Zutabe 2 euriaren informazioa gehitzeko
			gbcPanelEgunak.gridx++;
			euria[i] = new JLabel("");
			euria[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(euria[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;

			// Lerro 3 Zutabe 1 elurraren ikonoa jarri
			JLabel label5 = createImageLabel("/ikonoak/elurraIcon.png", 20, 20);
			label5.setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(label5, gbcPanelEgunak);

			// Lerro 3 Zutabe 2 elurraren informazioa gehitzeko
			gbcPanelEgunak.gridx++;
			elurra[i] = new JLabel("");
			elurra[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(elurra[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;

			// Lerro 4 Zutabe 1 egunsentiaren ikonoa gehitu
			JLabel label7 = createImageLabel("/ikonoak/egunsentia.png", 25, 25);
			label7.setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(label7, gbcPanelEgunak);

			// Lerro 4 Zutabe 2 egunsentiaren informazioa bistaratu
			gbcPanelEgunak.gridx++;
			egunsentia[i] = new JLabel("");
			egunsentia[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(egunsentia[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;
			
			// Lerro 5 Zutabe 1 ilunabarraren ikonoa gehitu
			JLabel label9 = createImageLabel("/ikonoak/ilunabarra.png", 25, 25);
			label9.setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(label9, gbcPanelEgunak);

			// Lerro 5 Zutabe 2 ilunabarraren informazioa gehitzeko
			gbcPanelEgunak.gridx++;
			ilunabarra[i] = new JLabel("");
			ilunabarra[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(ilunabarra[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;

			// Lerro 6 Zutabe 1 haizearen noranzkoa bistarazi
			haizeaN[i] = createImageLabel("/ikonoak/flecha.png", 20, 20);
			haizeaN[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(haizeaN[i], gbcPanelEgunak);

			// Lerro 6 Zutabe 2 haizearen informazioa gehitzeko
			gbcPanelEgunak.gridx++;
			haizea[i] = new JLabel("");
			haizea[i].setHorizontalAlignment(SwingConstants.CENTER);
			panelEgunak.add(haizea[i], gbcPanelEgunak);

			// Hurrengo lerroa pasa eta zutabeak 0 bihurtu (gridx = 0)
			gbcPanelEgunak.gridx = 0;
			gbcPanelEgunak.gridy++;

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

		// Oraingo ordua lortu eta hh:oo formatua erabili
		LocalTime oraingoa = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");

		// 24 orduen panelak sortzeko buklea
		for (int i = 0; i <= 23; i++) {
			
			// Panel bat sortu bukle bakoitzeko dimentsio espezifiko batekin scrollPane azalztzeko
		    JPanel panelOrdua = new JPanel(new GridLayout(7, 1));
		    panelOrdua.setBorder(innerBorder);
		    panelOrdua.setPreferredSize(new Dimension(100, 50));
		    
		    // ordua 24 pasatzen bada, bueltatu aurrekoa
		    LocalTime ordua = oraingoa.plusHours(i);
		    if (ordua.isAfter(LocalTime.of(23, 0))) {
		    	ordua = ordua.minusHours(24);
		    }
		    String orduaString = ordua.format(formatter);
		    
		    // Ordua ezarri lehenengo ilaran
		    JLabel label1 = new JLabel(orduaString);
		    label1.setHorizontalAlignment(SwingConstants.CENTER);	// Zentratzeko
		    panelOrdua.add(label1);
		    
		    // Eguraldiaren ikonoa jartzeko
		    ikonoOrduLabel[i] = new JLabel("");
		    ikonoOrduLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(ikonoOrduLabel[i]);
		    
		    // Temperaturaren informazioa jartzeko
		    temp[i] = new JLabel("");
		    temp[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(temp[i]);
		    
		    // Prezipitazioaren informazioa jartzeko
		    prezipitazioa[i] = new JLabel("");
		    prezipitazioa[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(prezipitazioa[i]);
		    
		    // Prezipitazio probabilitatearen informazioa jartzeko
		    prezipitazioAukerak[i] = new JLabel("");
		    prezipitazioAukerak[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(prezipitazioAukerak[i]);
		    
		    // Haizearen noranzkoaren ikonoa jarri
		    haizeNorabidea[i] = new JLabel("");
		    haizeNorabidea[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(haizeNorabidea[i]);
		    
		    // Haizearen abiadura ezarri
		    haizeAbiadura[i] = new JLabel("");
		    haizeAbiadura[i].setHorizontalAlignment(SwingConstants.CENTER);
		    panelOrdua.add(haizeAbiadura[i]);
		    
		    // Panela gehitu
		    panelLabels.add(panelOrdua, gbcOrduak);
		    gbcOrduak.gridx++;
		}

		// Scrollpanel-a konfiguratu horizontalki scrolleatzeko panelen artean eta gehitu panel ordutara
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelEguraldiaOrduak.add(scrollPane, gbcOrduak);

		// Orduen eta egunen panelak panel nagusira gehitu izenarekin jarrita, cardLayout-arentzeko
		panelEguraldiaMain.add(panelEguraldiaEgunak, "egunak");
		panelEguraldiaMain.add(panelEguraldiaOrduak, "orduak");

		// Lehioari gehitu
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
		
		// Estiloak jarri botoieri
		estiloak.botoiElegantea(btnEgunak);
		estiloak.botoiElegantea(btnOrduak);
		
		// Etxea eta bilaketa botoiak konfiguratu ikonoak azaltzeko
		estiloak.botoiaKonfiguratu(btnEtxea, "/ikonoak/etxea.png");
		estiloak.botoiaKonfiguratu(btnBilatu, "/ikonoak/bilatu.png");

		// Goiko panelaren elementuak gehitu, kokapenaren informazioa azalduko dena
		panelKokapenakEmaitza.setLayout(new BoxLayout(panelKokapenakEmaitza, BoxLayout.X_AXIS));

		// 	Kokapena azalduko den textuari margen txiki bat gehitu ezkerrera eta gehitu
		txtKokapena.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		panelKokapenakEmaitza.add(txtKokapena);

		// Espazio txuri bat sortu botoiak ezkerrera mugitzeko
		panelKokapenakEmaitza.add(Box.createHorizontalGlue());

		// Botoiak gehitu panelean
		panelKokapenakEmaitza.add(btnEtxea);
		panelKokapenakEmaitza.add(btnBilatu);

		// Bilaketa botoiari listener-a gehitu bilaketaren frame-a bistaratzeko
		btnBilatu.addActionListener(e -> {
			bilaketaGUI.bilaketaGUIHasieratu();
		});
		
		btnEtxea.addActionListener(e ->  {
			
		});

		// Uneko eguraldiaren datuen panel-a sortu
		panelDatuakJPanel.setLayout(new GridBagLayout());
		
		// Elementuak ondo kokatzeko gbc bat sortu
		GridBagConstraints gbc2 = new GridBagConstraints();

		// 10 elementu daudenez, 10 elementuko bukle bat sortu
		for (int i = 0; i < 10; i++) {
			switch (i) {
			
			// Eguraldiaren ikonoa
			case 0:
				unekoElementuak[0] = createImageLabel("", 1, 1);
				break;
			
			// Tenperaturaren ikonoa
			case 1:
				unekoElementuak[1] = createImageLabel("/ikonoak/temperatura.png", 40, 40);
				break;
				
			// Uneko tenperaturaren informazioa
			case 2:
				unekoElementuak[2] = createTextLabel("", 15);
				break;
				
			// Ilunabarraren ikonoa
			case 3:
				unekoElementuak[3] = createImageLabel("/ikonoak/ilunabarra.png", 50, 50);
				break;
				
			// Ilunabarraren informazioa
			case 4:
				unekoElementuak[4] = createTextLabel("", 15);
				break;
				
			// Label bat hutsik espazioa egiteko
			case 5:
				unekoElementuak[5] = createTextLabel("", 15);
				break;
				
			// Haizearen noraznkoa ikonoa
			case 6:
				unekoElementuak[6] = createImageLabel("/ikonoak/flecha.png", 38, 38);
				break;
				
			// Haizearen informazioa
			case 7:
				unekoElementuak[7] = createTextLabel("", 15);
				break;
				
			// egunsentiaren ikonoa
			case 8:
				unekoElementuak[8] = createImageLabel("/ikonoak/egunsentia.png", 50, 50);
				break;
				
			// egunsentiaren informazioa
			case 9:
				unekoElementuak[9] = createTextLabel("", 15);
				break;
			default:
				unekoElementuak[i] = new JLabel("");
			}

			// Ze lerrotan kokatuko diren elementuak
			gbc2.gridy = i < 5 ? 0 : 1;
			
			// 5 elementuko kolumnak sortzeko
			gbc2.gridx = i % 5;

			gbc2.anchor = GridBagConstraints.WEST;

			// elementuko margen-ak ezartzeko, i=1 i=3 (elementu zenbakia) 10px-ko margin-a ezarri eskubita
			if (i == 1 || i == 3) {
				gbc2.insets.right = 10;
			} else if (i == 0 || i == 2 || i == 4) {
				gbc2.insets.right = 120;
			} else {
				gbc2.insets.right = 0;
			}

			// elementuak panelera gehitu
			panelDatuakJPanel.add(unekoElementuak[i], gbc2);
		}

		// botoiak gehitu botoien panelera
		panelBtn.add(btnEgunak);
		panelBtn.add(btnOrduak);

		// Sortu diren 3 panelak, unekoEguraldi panelera gehitu kokapen espezifiko batekin 
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

		// Lehiora gehitu
		frame.add(panelUnekoEguraldia, gbc);

		// Lehioa konfiguratu eta bisiblea egin
		frame.setSize(1400, 760);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panelUnekoEguraldia.requestFocusInWindow();
	}

	/**
	 * Kokapenaren textua aldatzeko metodoa. Kokapen bat
	 * pasatzen zaio eta honen informazioaren arabera txtKokapena aldatzen da.
	 *
	 * @param kokapena --> kokapen berria
	 */
	public static void txtKokapenaAldatu(Geokokapena kokapena) {
		String txt = "<html><div style='font-size: 26px;'>" + kokapena.getName() + "</div>"
                + "<div style='font-size: 10px; opacity: 0.6;'>" + kokapena.getCountry() + ", " + kokapena.getAdmin1() + "</div></html>";
		
		txtKokapena.setText(txt);
	}

	/**
	 * Eguraldia aldatzeko metodoa. Kokapen bat bilatzen denean, edo, gordetako
	 * kokapen bati klik egiten denean aktibatzen da eta lehio osoa aktualizatzen du
	 * informazio berria bistaratuz
	 *
	 * @param eguraldia --> eguraldi berria
	 * @param Klasea 	--> Klasea, ikonoaren source-ak espezifikatzeko
	 */
	public static void eguraldiaAldatu(eguraldia eguraldia, Class<?> Klasea) {
		
		// Egunsentiak eta ilunabarren formatua aldatzeko, bukaerako formatua jartzeko
		DateTimeFormatter formatoa = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter bukaeraF = DateTimeFormatter.ofPattern("HH:mm");

		// UNEKO EGURALDIA ALDATU
		
		// Ikonoa jarri eskalatuta, image erabiliz
		String irudia = getEguraldiKondizioa(eguraldia.getUnekoEguraldia().getWeatherCode()) + ".png";
		ImageIcon originala = new ImageIcon(Klasea.getResource("/ikonoak/" + irudia));
		Image irudiEskalatua = originala.getImage().getScaledInstance(150, 140, Image.SCALE_SMOOTH);
		ImageIcon ikonoBerria = new ImageIcon(irudiEskalatua);

		// Ikono berria jarri
		((JLabel) unekoElementuak[0]).setIcon(ikonoBerria);

		// Informazioa eguneratu uneko elementuetan, tenperatura eta haizea
		unekoElementuak[2].setText(Double.toString(eguraldia.getUnekoEguraldia().getTemperature2M()) + "º");
		unekoElementuak[7].setText(Double.toString(eguraldia.getUnekoEguraldia().getWindSpeed10M()) + " km/h");

		// Ilunabarra eta egunsentia lortu
		String ilunGaur = eguraldia.getEgunekoEguraldia().getSunset()[0];
		String egunGaur = eguraldia.getEgunekoEguraldia().getSunset()[0];

		// String-a localdatetime bihurtu
		LocalDateTime ordua = LocalDateTime.parse(egunGaur, formatoa);
		LocalDateTime ordua2 = LocalDateTime.parse(ilunGaur, formatoa);

		// Lehen sortutako datetimeformated-arekin, formatua aldatu ordua bakarrik azaltzeko
		String orduaFormateatuta = ordua.format(bukaeraF);
		String orduaFormateatuta2 = ordua2.format(bukaeraF);
		
		// Textuak eguneratu informazioa aldatzeko
		unekoElementuak[9].setText(orduaFormateatuta);
		unekoElementuak[4].setText(orduaFormateatuta2);

		// EGUNEKO EGURALDIA ALDATU, 7 egun 7 elementuentzeko
		for (int i = 0; i < 7; i++) {

			// Egunaren eguraldiaren ikonoa lortu
			String eguna = getEguraldiKondizioa(eguraldia.getEgunekoEguraldia().getWeatherCode()[i]) + ".png";
			ImageIcon egunaO = new ImageIcon(Klasea.getResource("/ikonoak/" + eguna));
			Image egunaEsk = egunaO.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
			ImageIcon egunaIcono = new ImageIcon(egunaEsk);

			// Ikonoa jarri
			((JLabel) ikonoLabel[i]).setIcon(egunaIcono);
			
			// Tenperatura, prezipitazioak, eta elurra eguneratu eguraldiaren informazioarekin
			tempMax[i].setText(Double.toString(eguraldia.getEgunekoEguraldia().getTemperature2MMax()[i]) + "º");
			tempMin[i].setText(Double.toString(eguraldia.getEgunekoEguraldia().getTemperature2MMin()[i]) + "º");
			euria[i].setText(Double.toString(eguraldia.getEgunekoEguraldia().getPrecipitationSum()[i]) + "mm");
			elurra[i].setText(Double.toString(eguraldia.getEgunekoEguraldia().getSnowfallSum()[i]) + "cm");

			// Ilunabarra eta egunsentia lortu
			String egun = eguraldia.getEgunekoEguraldia().getSunset()[i];
			String ilun = eguraldia.getEgunekoEguraldia().getSunrise()[i];
			
			// String-a localdatetime bihurtu
			LocalDateTime ordua3 = LocalDateTime.parse(egun, formatoa);
			LocalDateTime ordua4 = LocalDateTime.parse(ilun, formatoa);

			// Lehen sortutako datetimeformated-arekin, formatua aldatu ordua bakarrik azaltzeko
			String orduaFormateatuta3 = ordua3.format(bukaeraF);
			String orduaFormateatuta4 = ordua4.format(bukaeraF);

			// Textuak eguneratu informazioa aldatzeko
			egunsentia[i].setText(orduaFormateatuta4);
			ilunabarra[i].setText(orduaFormateatuta3);
			
			// Haizearen informazioa eguneratu
			haizea[i].setText(Double.toString(eguraldia.getEgunekoEguraldia().getWindSpeed10MMax()[i]) + " km/h");
			
			// Egunaren eguraldiaren ikonoa lortu
			String haizea = lortuNorabidea((int) eguraldia.getEgunekoEguraldia().getWindDirection10MDominant()[i]) + ".png";
			ImageIcon haizeaO = new ImageIcon(Klasea.getResource("/norabideak/" + haizea ));
			Image haizeaEsk = haizeaO.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			ImageIcon haizeaIkono = new ImageIcon(haizeaEsk);

			haizeaN[i].setIcon((Icon) haizeaIkono);
			
		}
		
		// ORDUKO EGURALDIA ALDATU, 24 datu, 24 aldiz iteratu
		for (int i = 0; i < 24; i++) {
			
			// Orduko eguraldiaren ikonoa lortu
			String eguna = getEguraldiKondizioa(eguraldia.getOrduEguraldia().getWeatherCode()[i]) + ".png";
			ImageIcon egunaO = new ImageIcon(Klasea.getResource("/ikonoak/" + eguna));
			Image egunaEsk = egunaO.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
			ImageIcon egunaIcono = new ImageIcon(egunaEsk);
			
			// ikono berria jarri
			((JLabel) ikonoOrduLabel[i]).setIcon(egunaIcono);
			
			// informazioa aldatu eguraldiaren arabera
			temp[i].setText(Double.toString(eguraldia.getOrduEguraldia().getTemperature2M()[i]) + "º");
			prezipitazioa[i].setText(Double.toString(eguraldia.getOrduEguraldia().getRain()[i]) + "mm");
			prezipitazioAukerak[i]
					.setText(Double.toString(eguraldia.getOrduEguraldia().getPrecipitationProbability()[i]) + "%");
			haizeAbiadura[i].setText(Double.toString(eguraldia.getOrduEguraldia().getWindSpeed10M()[i]) + "km/h");

			// Egunaren eguraldiaren ikonoa lortu
			String haizea = lortuNorabidea((int) eguraldia.getOrduEguraldia().getWindDirection10M()[i]) + ".png";
			ImageIcon haizeaO = new ImageIcon(Klasea.getResource("/norabideak/" + haizea));
			Image haizeaEsk = haizeaO.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			ImageIcon haizeaIkono = new ImageIcon(haizeaEsk);

			haizeNorabidea[i].setIcon((Icon) haizeaIkono);
		}
	}

	/**
	 * Label bat sortzeko metodoa irudi batekin, ikono dekoratiboak jartzeko.
	 *
	 * @param imagePath --> non dagoen irudia kokatua
	 * @param maxWidth  --> irudiaren antxura maximoa
	 * @param maxHeight --> irudiaren altuera maximoa
	 * @return the j label
	 */
	public static JLabel createImageLabel(String imagePath, int maxWidth, int maxHeight) {
		URL imageURL = eguraldiaGUI.class.getResource(imagePath);
		ImageIcon imageIcon = new ImageIcon(imageURL);

		Image img = imageIcon.getImage();
		Image scaledImg = img.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);

		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel label = new JLabel(scaledIcon);
		return label;
	}

	/**
	 * Textu label dekoratibo bat sortzeko metodoa non letra beltzeko eta tamaina
	 * espezifiko bateko label bat bueltatzen duen.
	 *
	 * @param text --> label-aren textua
	 * @param fontSize --> letraren tamaina
	 * @return the --> label berria
	 */
	public static JLabel createTextLabel(String text, int fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD, fontSize));
		return label;
	}

	/**
	 * Haizearen norabidea lortzeko enum-a.
	 */
	public enum Norabidea {

		NORTE,
		NORESTE,
		ESTE,
		SURESTE,
		SUR,
		SUROESTE,
		OESTE,
		NOROESTE
	}

	/**
	 * Graduen arabera, norabide bat edo beste lortu.
	 *
	 * @param eguraldiaren haizenNorabideak emango dituen graduak
	 * @return norabidea string moduan
	 */
	public static String lortuNorabidea(int grados) {
		
		Norabidea norabidea;

		if (grados >= 337.5 || grados < 22.5) {
			norabidea = Norabidea.NORTE;
		} else if (grados >= 22.5 && grados < 67.5) {
			norabidea = Norabidea.NORESTE;
		} else if (grados >= 67.5 && grados < 112.5) {
			norabidea = Norabidea.ESTE;
		} else if (grados >= 112.5 && grados < 157.5) {
			norabidea = Norabidea.SURESTE;
		} else if (grados >= 157.5 && grados < 202.5) {
			norabidea = Norabidea.SUR;
		} else if (grados >= 202.5 && grados < 247.5) {
			norabidea = Norabidea.SUROESTE;
		} else if (grados >= 247.5 && grados < 292.5) {
			norabidea = Norabidea.OESTE;
		} else {
			norabidea = Norabidea.NOROESTE;
		}

		return norabidea.name();
	}

	/**
	 * Eguraldiaren kondizioa lortzeko. Eguraldi objektu batek, weatherCode bat du,
	 * non API-an kondizio espezifiko bat da. Metodo honek, ze kondizio den kode
	 * hori ematen du.
	 *
	 * @param weatherCode --> Eguraldi objektu baten emantado kodea
	 * @return the weather --> kondizioa traduzituta
	 */
	
	public static eguraldiaKondizioa getEguraldiKondizioa(long weatherCode) {
		switch ((int) weatherCode) {
		case 0:
			return eguraldiaKondizioa.ARGITUTA;
		case 1, 2:
			return eguraldiaKondizioa.PARTZIALKILAINOTUTA;
		case 3:
			return eguraldiaKondizioa.LAINOTUTA;
		case 45, 48:
			return eguraldiaKondizioa.BEHELAINOA;
		case 51, 53, 55:
			return eguraldiaKondizioa.XIRIMIRI;
		case 56, 57:
			return eguraldiaKondizioa.EURIELUR;
		case 61, 63, 66, 67, 77, 80, 81, 82:
			return eguraldiaKondizioa.EURIA;
		case 65:
			return eguraldiaKondizioa.TXAPARRON;
		case 71, 73:
			return eguraldiaKondizioa.ELURGUTXI;
		case 75, 85, 86:
			return eguraldiaKondizioa.ELURRA;
		case 95, 96, 99:
			return eguraldiaKondizioa.EKAITZA;
		default:
			return eguraldiaKondizioa.UNKNOWN;
		}
	}

	/**
	 * Eguraldi kondizioa enum, kondizio espezifikoak ezartzeko erabilia.
	 */
	public enum eguraldiaKondizioa {
		
		/** The argituta. */
		ARGITUTA("Argituta"),
		
		/** The partzialkilainotuta. */
		PARTZIALKILAINOTUTA("PartzialkiLainotuta"),
		
		/** The lainotuta. */
		LAINOTUTA("Lainotuta"),
		
		/** The behelainoa. */
		BEHELAINOA("Behelainoa"),
		
		/** The xirimiri. */
		XIRIMIRI("Xirimiri"),
		
		/** The eurielur. */
		EURIELUR("EuriElur"),
		
		/** The euria. */
		EURIA("Euria"),
		
		/** The txaparron. */
		TXAPARRON("Txaparron"),
		
		/** The elurgutxi. */
		ELURGUTXI("ElurGutxi"),
		
		/** The elurra. */
		ELURRA("Elurra"),
		
		/** The ekaitza. */
		EKAITZA("Ekaitza"),
		
		/** The unknown. */
		UNKNOWN("Ezezaguna");
		
		/** The icon name. */
		private final String iconName;

		/**
		 * Eguraldi kondizio berri bat inizialitatu.
		 *
		 * @param iconName the icon name
		 */
		eguraldiaKondizioa(String iconName) {
			this.iconName = iconName;
		}

		/**
		 * Ikonoaren izena lortzen da, batez ere, gero irudia berdina izateko.
		 *
		 * @return the icon name
		 */
		public String getIconName() {
			return iconName;
		}

	}

	/**
	 * Asteko egunak traduzitzeko enum-a, javak ingelesez edo erderaz jartzen dituelako.
	 */
	enum astekoEgunak {
		
		/** The Astelehena. */
		Astelehena("Astelehena"),
		
		/** The Asteartea. */
		Asteartea("Asteartea"),
		
		/** The Asteazkena. */
		Asteazkena("Asteazkena"),
		
		/** The Osteguna. */
		Osteguna("Osteguna"),
		
		/** The Ostirala. */
		Ostirala("Ostirala"),
		
		/** The Larunbata. */
		Larunbata("Larunbata"),
		
		/** The Igandea. */
		Igandea("Igandea");

		/** The euskera. */
		private final String euskera;

		/**
		 * Asteko egun berri bat inizializatu.
		 *
		 * @param euskera the euskera
		 */
		astekoEgunak(String euskera) {
			this.euskera = euskera;
		}

		/**
		 * Gets the euskera.
		 *
		 * @return the euskera
		 */
		public String getEuskera() {
			return euskera;
		}
	}
}