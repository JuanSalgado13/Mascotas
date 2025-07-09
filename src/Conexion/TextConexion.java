package Conexion;

import properties.Credenciales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para obtener conexiones a la base de datos.
 * Retorna una nueva conexión cada vez que se llama a getConexion().
 */
public class TextConexion {

    static {
        try {
            Class.forName(Credenciales.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error al cargar el driver de MySQL.");
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(
                Credenciales.URL,
                Credenciales.USUARIO,
                Credenciales.CLAVE
            );
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
            return null;
        }
    }
}
