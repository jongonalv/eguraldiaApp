package eguraldiaApp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class eguraldiaGUI {
	
    private static JFrame frame = new JFrame("Eguraldia");
    private static JPanel panelKokapena = new JPanel();
    private static JPanel panelUnekoEguraldia = new JPanel();
    private static JPanel panelEguraldia = new JPanel();

    public static void jarriElementuak() {
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Crea un borde interno
        Border innerBorder = BorderFactory.createLineBorder(Color.lightGray, 2);
        
        // Crea un borde externo
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); // Espacio exterior
        
        // Combina los bordes interno y externo
        Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerBorder);

        // Panel Kokapena
        panelKokapena.setBorder(compoundBorder);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.25;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(panelKokapena, gbc);

        // Panel Eguraldia
        panelEguraldia.setBorder(compoundBorder);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 0.5;
        frame.add(panelEguraldia, gbc);

        // Panel EguraldiaEgunak
        panelUnekoEguraldia.setBorder(compoundBorder);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.75;
        gbc.weighty = 0.5;
        frame.add(panelUnekoEguraldia, gbc);

        // Configuración del frame
        frame.setSize(1400, 760);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
