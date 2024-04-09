package eguraldiaApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class estiloak {
	
	public static JButton botoiElegantea(JButton button) {
        button.setBackground(new Color(52, 152, 219)); // Azul moderno claro
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Establecer una fuente moderna

        // Cambiar el color de fondo al pasar el mouse sobre el botón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185)); // Azul más oscuro al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 152, 219)); // Volver al color original al salir del botón
            }
        });

        return button;
    }
	
	public static JButton ezabatuKokapena(JButton button) {
		
		URL ezabatuUrl = estiloak.class.getResource("/ikonoak/kokapena-.png");
		ImageIcon ezabatu = new ImageIcon(ezabatuUrl);
		Image ezabatuSize = ezabatu.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon etxeaIcon = new ImageIcon(ezabatuSize);
        button.setIcon(etxeaIcon);
		
        button.setBackground(new Color(213, 103, 103)); // Color de fondo personalizado
        button.setForeground(Color.WHITE); // Color del texto
        button.setBorderPainted(false); // Ocultar el borde
        button.setFocusPainted(false); // Eliminar el resaltado del foco
        button.setPreferredSize(new Dimension(70, 70)); // Tamaño personalizado
        // Agregar efecto hover con un MouseListener
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 102, 101)); // Cambiar color al pasar el ratón por encima
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(213, 103, 103)); // Restaurar color al salir del botón
            }
        });
        return button;
    }
	
	public static JButton gehituKokapena(JButton button) {
		
		URL ezabatuUrl = estiloak.class.getResource("/ikonoak/kokapena+.png");
		ImageIcon ezabatu = new ImageIcon(ezabatuUrl);
		Image ezabatuSize = ezabatu.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon etxeaIcon = new ImageIcon(ezabatuSize);
        button.setIcon(etxeaIcon);		
		
        button.setBackground(new Color(136, 176, 75)); // Color de fondo personalizado
        button.setForeground(Color.WHITE); // Color del texto
        button.setBorderPainted(false); // Ocultar el borde
        button.setFocusPainted(false); // Eliminar el resaltado del foco
        button.setPreferredSize(new Dimension(70, 70)); // Tamaño personalizado
        // Agregar efecto hover con un MouseListener
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(135, 167, 107)); // Cambiar color al pasar el ratón por encima
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(136, 176, 75)); // Restaurar color al salir del botón
            }
        });
        return button;
    }
}
