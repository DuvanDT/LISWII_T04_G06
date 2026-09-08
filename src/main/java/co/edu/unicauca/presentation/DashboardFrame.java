package co.edu.unicauca.presentation;

import co.edu.unicauca.access.QuestionImplRepository;
import co.edu.unicauca.domain.QuestionRepository;
import co.edu.unicauca.domain.QuestionService;
import co.edu.unicauca.domain.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame(User user) {
        setTitle("Tablero - " + user.getRole().getDisplayName());
        setSize(480, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Bienvenido, " + user.getFullName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JLabel roleLabel = new JLabel("Rol: " + user.getRole().getDisplayName(), SwingConstants.CENTER);
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(roleLabel);

        JButton btnOpenQuestionBank = new JButton("Abrir Banco de Preguntas (Taller 4)");
        btnOpenQuestionBank.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOpenQuestionBank.setBackground(new Color(37, 99, 235));
        btnOpenQuestionBank.setForeground(Color.WHITE);
        btnOpenQuestionBank.setFocusPainted(false);
        btnOpenQuestionBank.addActionListener(e -> launchQuestionBankSuite());
        centerPanel.add(btnOpenQuestionBank);

        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
    }

    public static void launchQuestionBankSuite() {
        QuestionRepository questionRepository = new QuestionImplRepository();
        QuestionService questionService = new QuestionService(questionRepository);

        GUIQuestions guiQuestions = new GUIQuestions(questionService);
        GUIObserver1 guiObserver1 = new GUIObserver1(questionService);
        GUIObserver2 guiObserver2 = new GUIObserver2(questionService);

        // Posicionar las ventanas al lado de la principal
        Point loc = guiQuestions.getLocation();
        guiObserver1.setLocation(loc.x + guiQuestions.getWidth() + 10, loc.y);
        guiObserver2.setLocation(loc.x + guiQuestions.getWidth() + 10, loc.y + guiObserver1.getHeight() + 10);

        guiQuestions.setVisible(true);
        guiObserver1.setVisible(true);
        guiObserver2.setVisible(true);
    }
}

