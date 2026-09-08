# Sistema para la Gestión de Usuarios y Banco de Preguntas (Saber Pro)

**Universidad del Cauca**  
**Facultad de Ingeniería Electrónica y Telecomunicaciones**  
**Programa de Ingeniería de Sistemas**  
**Laboratorio de Ingeniería de Software II - Taller 4: Patrón Capas y Micro patrón MVC**  
**Periodo 2-2026**

---

## 👥 Integrantes
Integrante 1: Duvan Alexis Hoyos.
Integrante 2: Juan José Hurtado.

---

## 📌 Descripción del Proyecto

Este proyecto implementa la aplicación de escritorio monolítica en Java Swing para la gestión de usuarios del sistema de evaluación Saber Pro, cumpliendo con los requerimientos expresados en la guía de laboratorio.

Permite el registro de usuarios con roles específicos, validación rigurosa de complejidad de contraseñas, almacenamiento cifrado seguro con **Argon2id**, persistencia en **SQLite** y un tablero o menú contextual con opciones diferenciadas por el rol del usuario autenticado.

---

## 📐 Cumplimiento de Principios SOLID

El diseño de la arquitectura sigue el ejemplo 5 de Inversión de Dependencias (DIP) visto en la teoría del curso:

1. **Single Responsibility Principle (SRP):**
   - `User`: Representa la entidad de dominio.
   - `PasswordValidator`: Responsable exclusivamente de validar las reglas de complejidad de la contraseña.
   - `PasswordHasher`: Responsable únicamente del hashing y verificación segura utilizando Argon2.
   - `SqliteUserRepository`: Encargado de la persistencia de datos en SQLite.
   - `UserService`: Gestiona la lógica de negocio del registro y autenticación.
   - `LoginFrame`, `RegisterFrame`, `DashboardFrame`: Encargados de la capa de presentación (GUI Swing).

2. **Open/Closed Principle (OCP):**
   - Las abstracciones `IUserRepository`, `IPasswordHasher` y `IPasswordValidator` permiten extender o cambiar la implementación (por ejemplo, cambiar a PostgreSQL, MariaDB o BCrypt) sin modificar el código fuente de `UserService`.

3. **Liskov Substitution Principle (LSP):**
   - `SqliteUserRepository` sustituye limpiamente a `IUserRepository` garantizando el contrato de la interfaz sin comportamiento inesperado.

4. **Interface Segregation Principle (ISP):**
   - Las interfaces son pequeñas y orientadas a propósitos específicos (`IUserRepository`, `IPasswordHasher`, `IPasswordValidator`).

5. **Dependency Inversion Principle (DIP):**
   - El módulo de alto nivel (`UserService`) depende únicamente de interfaces/abstracciones y no de clases concretas de infraestructura. Las dependencias se inyectan a través de su constructor en `Main.java`.

---

## 🔑 Requisitos de Seguridad y Contraseñas

Durante el registro de un usuario, se solicitan los siguientes datos:
- **Nombre de usuario (Login)**
- **Nombre completo**
- **Rol del usuario:** Administrador, Autor de preguntas, Revisor, Docente, Estudiante.
- **Estado del usuario:** Activo / Inactivo.
- **Contraseña:** Debe cumplir con:
  - Mínimo 6 caracteres.
  - Al menos una letra mayúscula.
  - Al menos un dígito.
  - Al menos un carácter especial (ej. `@`, `#`, `$`, `!`, `%`, `*`).
- **Almacenamiento cifrado:** La contraseña nunca se guarda en texto plano; se utiliza **Argon2id** (`argon2-jvm`).

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 17+
- **GUI:** Java Swing
- **Gestor de Dependencias:** Apache Maven
- **Base de Datos:** SQLite (JDBC `sqlite-jdbc`)
- **Seguridad:** Argon2id (`argon2-jvm`)
- **Pruebas Unitarias:** JUnit 5 (Jupiter)

---

## 🚀 Compilación y Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/DuvanDT/LISWII_T04_G06
cd LISWII_T4_G06
```

### 2. Compilar el proyecto
```bash
mvn compile
```

### 3. Ejecutar las Pruebas Unitarias Automatizadas (JUnit 5)
```bash
mvn test
```

### 4. Ejecutar la Aplicación
```bash
mvn exec:java
```

---

## 👥 Usuarios de Prueba Sembrados Inicialmente

Si la base de datos `users.db` está vacía, la aplicación sembrará automáticamente los siguientes usuarios de prueba:

| Login | Contraseña | Rol | Estado |
|---|---|---|---|
| `admin` | `Admin123!` | Administrador | Activo |
| `autor1` | `Autor123!` | Autor de preguntas | Activo |
| `revisor1` | `Revis123!` | Revisor | Activo |
| `docente1` | `Docen123!` | Docente | Activo |
| `estudiante1` | `Estud123!` | Estudiante | Activo |
