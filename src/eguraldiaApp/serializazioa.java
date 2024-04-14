package eguraldiaApp;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * Serializazioa gauzatzeko metodo ezberdinak gortzeko klasea.
 */
public class serializazioa{

	/**
	 * Kokapenak gordetzeko metodoa, mapeatutako panelak pasatzen dira,
	 * eta konprobatze da lehenik ea kokapen hori sartuta dagoen lehendik 
	 * fitxategian. Ez balego sartuta, kokapena fitxategian gehitzen da.
	 * Kokapena ondo gordetzen bada true bueltatzen du eta bestela false.
	 *
	 * @param geokokapenaMap 	--> geokokapen panelen informazio erlazionatua
	 * @param filename 			--> fitxategiaren kokapena
	 * @return true, fitxategia gordetzen bada
	 */
	public static boolean gordeKokapenak(Map<JPanel, Geokokapena> geokokapenaMap, String fitxategia) {
		
	    // Map bat fitxategian irakurritako kokapenekin aurrerago konprobatzeko ea existitzen den
	    Map<JPanel, Geokokapena> existingMap = irakurriKokapenak(fitxategia);

	    // Man gorde kokapen panelak, irakurritako kokapenekin batera
	    if (existingMap != null) {
	        for (Map.Entry<JPanel, Geokokapena> entry : existingMap.entrySet()) {
	            JPanel existitzenPanel = entry.getKey();
	            Geokokapena existitzenGeokokapena = entry.getValue();
	            if (!panelExists(geokokapenaMap, existitzenPanel)) {
	                geokokapenaMap.put(existitzenPanel, existitzenGeokokapena);
	            } else {
	                JOptionPane.showMessageDialog(null, "Kokapen hori sartuta dago", "Kontuz", JOptionPane.WARNING_MESSAGE);
	                return false; // Kokapena sartuta dagoenez, false itzuli
	            }
	        }
	    }

	    // Artxibo osoa mapeatutakoa, idatzi
	    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fitxategia))) {
	        outputStream.writeObject(geokokapenaMap);
	        return true; // true itzuli gordetzerako balore guztiak
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(null, "Kokapena ez da ondo gorde, konprobatu fitxategiaren izena");
	        return false; // errore bat balego false itzuli
	    }
	}


	/**
	 * Panel-a existitzen den konprobatzeko. Mapan existitzen bada,
	 * erlazionatutako geokokapenarekin, true itzuli.
	 *
	 * @param geokokapenaMap 	--> erlazionatutako panelak/kokapenak
	 * @param panel 			--> beste panel-a
	 * @return true, existitzen bada
	 */
	public static boolean panelExists(Map<JPanel, Geokokapena> geokokapenaMap, JPanel panel) {
	    for (JPanel existitzenPanel : geokokapenaMap.keySet()) {
	        if (panelakBerdinakKonprobatu(existitzenPanel, panel)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Kokapen bat ezabatzeko metodoa. Lehenik kokapenEzabatu listener-a funtzionatu
	 * behar du, aukeratutako panela parametro modun pasatzen da, konprobatzen da ea
	 * fitxategian gordetako panel berdinarekin konparatu berdinak diren ala ez, berdinak
	 * badira, panel-a fitxategitik ezabatzen da.
	 *
	 * @param fitxategia -> fitxategiaren izena
	 * @param ezabatuBeharrekoPanela -> Aurreko listener-an aukeratutako panela
	 */
	public static void ezabatuKokapena(String fitxategia, JPanel ezabatuBeharrekoPanela) {
		
	    // Fitxategiaren informazioa irakurri eta mapan gorde
	    Map<JPanel, Geokokapena> existingMap = irakurriKokapenak(fitxategia);

	    // Lista bat sortu ezabatu nahi den panelak gordetzeji
	    List<JPanel> ezabatuPanelak = new ArrayList<>();

	    // Mapako elemetu guztiak konprobatu eta, fitxategian gordeta dagoen panel-a
	    // aukeratutako panelaren berdina bada ezabatu lista gehitu
	    for (JPanel panel : existingMap.keySet()) {
	        // Comparar propiedades relevantes de los paneles
	        if (panelakBerdinakKonprobatu(panel, ezabatuBeharrekoPanela)) {
	        	ezabatuPanelak.add(panel);
	        }
	    }

	    // Mapa berrian, ezabatu panelak
	    for (JPanel panel : ezabatuPanelak) {
	        existingMap.remove(panel);
	    }

	    // Fitxategia eguneratu mapa berriarenkin
	    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fitxategia))) {
	        outputStream.writeObject(existingMap);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * 2 panel antzekoak diren konprobatzeko metodoa. Batez ere,
	 * kokapenako gordeko diren panelentzako erabilgarria. JLabel bat bilatzen
	 * da bi paneletan, eta textuak berdinak badira, true itzultzen du, berdinak
	 * direla adierazten
	 *
	 * @param panel1 --> konprobatu nahi den lehen panela
	 * @param panel2 --> konprobatu nahi den bigarren panela
	 * @return true, berdinak direnean
	 */
	private static boolean panelakBerdinakKonprobatu(JPanel panel1, JPanel panel2) {

		// Paneleko konponenteak lortu
	    Component[] konponenteak1 = panel1.getComponents();
	    Component[] konponenteak2 = panel2.getComponents();
	    
	    JLabel label1 = null;
	    JLabel label2 = null;
	    
	 // Konponente guztiak iteratu label bat aurkitu arte
	    for (Component comp : konponenteak1) {
	        if (comp instanceof JLabel) {
	            label1 = (JLabel) comp;
	            break;
	        }
	    }
	    
	    // Konponente guztiak iteratu label bat aurkitu arte
	    for (Component comp : konponenteak2) {
	        if (comp instanceof JLabel) {
	            label2 = (JLabel) comp;
	            break;
	        }
	    }

	    // Testuak berdinak badira, true itzuli
	    if (label1 != null && label2 != null) {
	        String texto1 = label1.getText();
	        String texto2 = label2.getText();
	        return texto1.equals(texto2);
	    } else {
	        return false;
	    }
	}

	
    /**
     * Fitxategiaren datuak irakurtzen dira eta mapa batean gordetzen dira,
     * non paneleko geokokapen erlazionatutako informazioa gordetzen da.
     *
     * @param filename --> fitxategia
     * @return mapa, panelen geokokapen informazioarekin
     */
    public static Map<JPanel, Geokokapena> irakurriKokapenak(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Map<JPanel, Geokokapena> existingMap = (Map<JPanel, Geokokapena>) inputStream.readObject();
            return existingMap;
        } catch (FileNotFoundException e) {
        
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
	/**
	 * Kokapenak kargatzeko metodoa. Fitxategia irakurtzen da eta pantailan
	 * kokapen guztiak bistaratzen dira.
	 *
	 * @param filename --> fitxategia kokapenak gordetzen direnak
	 */
	public static void kargatuKokapenak(String filename) {
		
		// Fitxategiak 10 lerro baino gutxigo dituenean, suposatzen da ez dagoela ezer sartuta, beraz, abisu bat pasatzen da.
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int lerroak = 0;
            
            while (br.readLine() != null) {
            	lerroak++;
            }
            
            if (lerroak <= 10) {
                JOptionPane.showMessageDialog(null, "Ez dira kargatu daturik, kokapen bat sartu datuak gordetzeko.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		// Deserializazio prozeusa objektuak irakurtzeko eta kargatzeko
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
			
			// Maparen informazioa irakurri, fitxategiaren arabera
			Map<JPanel, Geokokapena> geokokapenaMap = (Map<JPanel, Geokokapena>) inputStream.readObject();

				// Panel-geokokapen bakoitzeko, panel-a gehitu eguraldiGUI-ko kontenedoreari
				for (Map.Entry<JPanel, Geokokapena> entryKokapena : geokokapenaMap.entrySet()) {

					JPanel panel = entryKokapena.getKey();

					eguraldiaGUI.kontenedorPanel.add(panel);

					// Panel bakoitzeko listener bat gehitu, geokokapen horreko eguraldia eguneratzeko klik egiterakoan
					for (Component component : eguraldiaGUI.kontenedorPanel.getComponents()) {
						if (component instanceof JPanel) {
							JPanel panela = (JPanel) component;
							panela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

							panela.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {

									if (SwingUtilities.isLeftMouseButton(e)) {
										
										// Panelaren kokapenaren informazioa lortu
										Geokokapena kokapenaErlazionatua = geokokapenaMap.get(panela);

										// Eguraldia kargatu klik egiterakoan panelaren gainean
										eguraldia eguraldiaKargatu = new eguraldia();
										eguraldiaKargatu = eguraldiaKargatu.getEguraldiDatuak(kokapenaErlazionatua);

										eguraldiaGUI.eguraldiaAldatu(eguraldiaKargatu, eguraldiaGUI.class);
										eguraldiaGUI.txtKokapenaAldatu(kokapenaErlazionatua);
									}
								}
							});
						}
					}
				}

			// Kontenedorea aktualizatu aldaketak bistaratzeko
			eguraldiaGUI.kontenedorPanel.revalidate();
			eguraldiaGUI.kontenedorPanel.repaint();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
