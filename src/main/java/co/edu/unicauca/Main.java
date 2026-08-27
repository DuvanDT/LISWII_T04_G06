package co.edu.unicauca;

import co.edu.unicauca.access.IUserRepository;
import co.edu.unicauca.access.SqliteUserRepository;
import co.edu.unicauca.domain.IPasswordHasher;
import co.edu.unicauca.domain.IPasswordValidator;
import co.edu.unicauca.domain.PasswordHasher;
import co.edu.unicauca.domain.PasswordValidator;
import co.edu.unicauca.domain.UserService;
import co.edu.unicauca.presentation.LoginFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inyección de dependencias
        IUserRepository repository = new SqliteUserRepository();
        IPasswordHasher passwordHasher = new PasswordHasher();
        IPasswordValidator passwordValidator = new PasswordValidator();
        UserService userService = new UserService(repository, passwordHasher, passwordValidator);

        // Lanzar interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(userService);
            loginFrame.setVisible(true);
        });
    }
}