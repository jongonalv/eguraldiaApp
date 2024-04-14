package eguraldiaApp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Bilaketak egingo diren frame-aren pertsonalizazioa.
 */
public class bilaketaGUI {

	// Bilaketa lehioaren elementu printzipalak definitu
    private static JFrame frameBilatu = new JFrame();
    private static JPanel panelBilaketak = new JPanel();
    private static JPanel panelEmaitzak = new JPanel();
    private static JPanel panelBtn = new JPanel();
    private static JButton button1 = new JButton("Eguraldia ikusi");
    private static JTextField kokapenaBilatu = new JTextField("Bilatu...");

    /**
     * Bilaketa elementuak jartzeko frame-an.
     */
    public static void bilaketaElementuak() {
   
        // Panelaren layout-a konfiguratu
    	panelBilaketak.setLayout(new BorderLayout());

        // Kokapena bilatzeko textu kutxa goran jarri
    	panelBilaketak.add(kokapenaBilatu, BorderLayout.NORTH);
        kokapenaBilatu.setPreferredSize(new Dimension(100, 40)); // Tamaina ezarri
        
        // Textu kutxan bilatu... azaltzeko focus-a galtzen denean. Focus-a eginten dugunean (click) ezabatu egingo da
        kokapenaBilatu.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (kokapenaBilatu.getText().equals("Bilatu...")) {
                    kokapenaBilatu.setText("");
                    kokapenaBilatu.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (kokapenaBilatu.getText().isEmpty()) {
                    kokapenaBilatu.setText("Bilatu...");
                    kokapenaBilatu.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        // Bilaketaren emaitzak azalduko den panela konfiguratu
        panelEmaitzak.setLayout(new BoxLayout(panelEmaitzak, BoxLayout.Y_AXIS));

        // Emaitzen panela gehitu panel nagusiari
        panelBilaketak.add(panelEmaitzak, BorderLayout.CENTER);

        // Botoientzako panel berri bat sortu eta zentratu
        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER)); // Zentratzeko
        panelBtn.add(button1);
        panelBilaketak.add(panelBtn, BorderLayout.SOUTH);
        
        final Geokokapena[] aukeratutakoKokapena = new Geokokapena[1];

        // Listener-a emaitzak ateratzeko textu kutxan erabiltzaileak idazten duen bitartean
        kokapenaBilatu.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                eguneratu();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eguneratu();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eguneratu();
            }

            // Metodo honek, erabiltzaileak idazten duen bitartean, emaitzak eguneratuko ditu
            private void eguneratu() {

				String kokapena = kokapenaBilatu.getText().trim();

				// Textuaren tamaina 2 baino txikiagoa bada emaitza guztiak ezabatu (errorea ez azaltzeko)
				if (kokapena.length() <= 2) {
					panelEmaitzak.removeAll();
					panelBilaketak.revalidate();
					panelBilaketak.repaint();
					
				// Textua = "Bilatu..." bada emaitzak ezabatu (errorea ez azaltzeko)
				} else if (kokapena.equals("Bilatu...")) {
					panelEmaitzak.removeAll();
					panelBilaketak.revalidate();
					panelBilaketak.repaint();
					
				// Textuaren tamaina 2 baino handiagoa bada, emaitzak bistaratu
				} else {

					// Azaltzen diren kokapenak hemen gordeko dira, metodoari deituz
					ArrayList<Geokokapena> kokapenak = geokokapenakKudeatu.getKokapenaData(kokapena);

					// Aurreko emaitzak ezabatu
					panelEmaitzak.removeAll();
					
					// Kokapenak ez badira aurkitzen, errore mezutxo bat label batean bistaratu
					if (kokapenak == null || kokapenak.isEmpty()) {
						JLabel label = new JLabel("Ez da ezer aurkitu");
						panelEmaitzak.add(label);
					} else {

						// Bestela, geokokapen bakoitzeko label bat sortu eta goitik behera erakutsi
						// datu ezberdinekin
						for (Geokokapena geokokapena : kokapenak) {
							
							JLabel label = new JLabel();

							if (geokokapena.getName() != null && geokokapena.getAdmin1() != null
							 && geokokapena.getCountry() != null) {

								label.setText(geokokapena.getName() + " - " + geokokapena.getAdmin1() + " - "
										+ geokokapena.getCountry());
							}

							// Arratoia gainetik pasatzen den bakoitzean, background color-a aldatzeko
							// listener-a
							label.addMouseListener(new java.awt.event.MouseAdapter() {
								@Override
								public void mouseEntered(java.awt.event.MouseEvent evt) {
									label.setBackground(Color.LIGHT_GRAY);
								}

								@Override
								public void mouseExited(java.awt.event.MouseEvent evt) {
									label.setBackground(null); // Kolore normala ezarri ateratakoan
								}

								@Override
								public void mouseClicked(java.awt.event.MouseEvent evt) {
									kokapenaBilatu.setText(label.getText());
									aukeratutakoKokapena[0] = geokokapena;
									panelEmaitzak.removeAll();
									panelBilaketak.revalidate();
									panelBilaketak.repaint();
								}
							});
							
							// Botoia klikatzen denean, aukeratutako kokapenaren informazioa aldatzen da
						    button1.addActionListener(e -> {
						        if (aukeratutakoKokapena[0] != null) {	
						        	
						        	eguraldia eguraldia = new eguraldia();						        	
						            eguraldia = eguraldia.getEguraldiDatuak(aukeratutakoKokapena[0]);
						            
						            // Aukeratutako kokapenaren eta eguraldiaren informazioko jarri
						            eguraldiaGUI eguraldiaGUI = new eguraldiaGUI(eguraldia, aukeratutakoKokapena[0]);
						            
						            // Intefazeari deitu aldatzeko informazio guztia
						            eguraldiaGUI.txtKokapenaAldatu(aukeratutakoKokapena[0]);
						            eguraldiaGUI.eguraldiaAldatu(eguraldia, eguraldiaGUI.class);

						            frameBilatu.setVisible(false);
						        }
						    });

                            label.setOpaque(true);
                            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            
                            panelEmaitzak.add(label);
                        }
                    }

                    // Interfazea eguneratu
                    panelBilaketak.revalidate();
                    panelBilaketak.repaint();
                }
            }


        });
    }
    
    /**
     * Bilaketa GUI hasieratu.
     */
    public static void bilaketaGUIHasieratu() {
        
    	// Elementu guztiak jarri
        bilaketaElementuak();

        // Lehioa konfiguratu eta bistaratu
        frameBilatu.setSize(400, 300);
        frameBilatu.getContentPane().add(panelBilaketak);
        frameBilatu.setLocationRelativeTo(null);
        frameBilatu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameBilatu.setVisible(true);
        
        // Textu elementua husteko listener-a
        frameBilatu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	bueltatuTextua(panelBilaketak);
            }
        });
    }

    /**
     * Lehioa ixten bada, uneko egoerara bueltatu txtField-a.
     *
     * @param panelBilaketak the panel bilaketak
     */
    public static void bueltatuTextua(JPanel panelBilaketak) {
        Component[] components = panelBilaketak.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("Bilatu...");
                textField.setForeground(Color.LIGHT_GRAY);
            }
        }
    }
}