package modeloDao;

import Conexion.TextConexion;
import vo.AnimalVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDao {

    public void insertarAnimal(AnimalVo animal) {
        String sql = "INSERT INTO animales (nombre, propietario_id, especie, genero) VALUES (?, ?, ?, ?)";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, animal.getNombre());
            ps.setString(2, animal.getPropietarioId());
            ps.setString(3, animal.getEspecie());
            ps.setString(4, animal.getGenero());
            ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                throw new RuntimeException("No se puede guardar el animal: el propietario no existe.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public AnimalVo buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM animales WHERE nombre = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AnimalVo animal = new AnimalVo();
                animal.setNombre(rs.getString("nombre"));
                animal.setPropietarioId(rs.getString("propietario_id"));
                animal.setEspecie(rs.getString("especie"));
                animal.setGenero(rs.getString("genero"));
                return animal;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void modificarAnimal(AnimalVo animal) {
        String sql = "UPDATE animales SET especie = ?, genero = ?, propietario_id = ? WHERE nombre = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, animal.getEspecie());
            ps.setString(2, animal.getGenero());
            ps.setString(3, animal.getPropietarioId());
            ps.setString(4, animal.getNombre());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAnimal(String nombre) {
        String sql = "DELETE FROM animales WHERE nombre = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerListado() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT * FROM animales";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sb.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                sb.append("ID Propietario: ").append(rs.getString("propietario_id")).append("\n");
                sb.append("Especie: ").append(rs.getString("especie")).append("\n");
                sb.append("Género: ").append(rs.getString("genero")).append("\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
