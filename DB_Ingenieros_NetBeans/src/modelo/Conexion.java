package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia esto si tu configuración es diferente
    private static final String USUARIO = "SYSTEM"; // Tu usuario de la base de datos
    private static final String CONTRASENA = "ITR2024"; // Tu contraseña de la base de datos

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}