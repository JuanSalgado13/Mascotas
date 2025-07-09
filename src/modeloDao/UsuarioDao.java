package modeloDao;

import Conexion.TextConexion;
import vo.UsuarioVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public void guardarUsuario(UsuarioVo usuario) {
        String sql = "INSERT INTO usuarios (documento, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getTelefono());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UsuarioVo consultarPorDocumento(String documento) {
        String sql = "SELECT * FROM usuarios WHERE documento = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioVo u = new UsuarioVo();
                u.setDocumento(rs.getString("documento"));
                u.setNombre(rs.getString("nombre"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualizarUsuario(UsuarioVo usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, direccion = ?, telefono = ? WHERE documento = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDireccion());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getDocumento());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarUsuario(String documento) {
        String sql = "DELETE FROM usuarios WHERE documento = ?";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String listarUsuarios() {
        StringBuilder listado = new StringBuilder();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = TextConexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listado.append("Documento: ").append(rs.getString("documento")).append("\n");
                listado.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                listado.append("Dirección: ").append(rs.getString("direccion")).append("\n");
                listado.append("Teléfono: ").append(rs.getString("telefono")).append("\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado.toString();
    }
}
