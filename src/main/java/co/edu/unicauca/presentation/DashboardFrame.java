package co.edu.unicauca.presentation;

import co.edu.unicauca.domain.User;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame(User user) {
        setTitle("Tablero - " + user.getRole().getDisplayName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido, " + user.getFullName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        
        JLabel roleLabel = new JLabel("Rol: " + user.getRole().getDisplayName(), SwingConstants.CENTER);
        panel.add(roleLabel, BorderLayout.SOUTH);

        add(panel);
    }
}
