package co.edu.unicauca.access;

import co.edu.unicauca.domain.User; // Crearemos esta clase en el paso 5

public interface IUserRepository {
    boolean save(User user);
    User findByLogin(String login);
}