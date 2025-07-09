package procesos;

import javax.swing.JOptionPane;

import modeloDao.UsuarioDao;
import vo.UsuarioVo;

public class GestionUsuario {

    private final UsuarioDao usuarioDao;

    public GestionUsuario() {
        this.usuarioDao = new UsuarioDao();
    }

    public void mostrarMenu() {
        String menu = """
                === GESTIÓN DE USUARIOS ===

                1. Registrar usuario
                2. Consultar usuario
                3. Mostrar todos
                4. Modificar usuario
                5. Eliminar usuario
                6. Salir
                """;

        int opcion = 0;

        while (opcion != 6) {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> consultar();
                    case 3 -> listar();
                    case 4 -> actualizar();
                    case 5 -> eliminar();
                    case 6 -> JOptionPane.showMessageDialog(null, "Cerrando gestión de usuarios");
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número.");
            }
        }
    }

    private void registrar() {
        UsuarioVo u = new UsuarioVo();
        u.setDocumento(JOptionPane.showInputDialog("Documento:"));
        u.setNombre(JOptionPane.showInputDialog("Nombre completo:"));
        u.setDireccion(JOptionPane.showInputDialog("Dirección:"));
        u.setTelefono(JOptionPane.showInputDialog("Teléfono:"));

        usuarioDao.guardarUsuario(u);
        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
    }

    private void consultar() {
        String doc = JOptionPane.showInputDialog("Documento del usuario a buscar:");
        UsuarioVo u = usuarioDao.consultarPorDocumento(doc);

        if (u != null) {
            JOptionPane.showMessageDialog(null, u.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    private void listar() {
        String listado = usuarioDao.listarUsuarios();
        JOptionPane.showMessageDialog(null, listado.isEmpty() ? "No hay usuarios registrados." : listado);
    }

    public void actualizar() {
        String doc = JOptionPane.showInputDialog("Documento del usuario a Editar:");
        UsuarioVo u = usuarioDao.consultarPorDocumento(doc);

        if (u != null) {
            u.setNombre(JOptionPane.showInputDialog("Nuevo nombre:", u.getNombre()));
            u.setDireccion(JOptionPane.showInputDialog("Nueva dirección:", u.getDireccion()));
            u.setTelefono(JOptionPane.showInputDialog("Nuevo teléfono:", u.getTelefono()));

            usuarioDao.actualizarUsuario(u);
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    public void eliminar() {
        String doc = JOptionPane.showInputDialog("Documento del usuario a eliminar:");
        usuarioDao.borrarUsuario(doc);
        JOptionPane.showMessageDialog(null, "Usuario eliminado si existía.");
    }
}
